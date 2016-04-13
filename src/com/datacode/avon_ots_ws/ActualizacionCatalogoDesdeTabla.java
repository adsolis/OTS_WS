/**
 *
 *  @since 30/01/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.Catalogo;

/**
 * @author jessica.leon
 * @since 30/01/2012
 * 
 */
public class ActualizacionCatalogoDesdeTabla{


	private TransaccionesCatalogo transaccionesCatalogo;
	private Catalogo catalogo;
	private ActualizacionCatalogos actualizacion;
	private String lastUpdateTs;
	
	public ActualizacionCatalogoDesdeTabla(ActualizacionCatalogos actualizacion) {
			
		    this.actualizacion = actualizacion; 
			transaccionesCatalogo = actualizacion.getTransaccionesCatalogo();
			catalogo = actualizacion.getCatalogo();
	}

	public void iniciarCargaCatalogos() {

		String logUltimaEjecucion = null;
		List<String> inserts = null;

		if (verificarOrigenDatos(catalogo.getCadenaConexOrigen(),catalogo.getDescripcion())) {
			Connection conexion = transaccionesCatalogo.conectarBDExterna(catalogo.getCadenaConexOrigen(), catalogo.getDescripcion());
			if (conexion != null) {
				ResultSet resultSetOrigen = transaccionesCatalogo.ejecutarConsultaBDExterna(conexion);
				inserts = generarCadenaInsert(resultSetOrigen,catalogo.getNombreDestino());
				AccesoBD.CerrarConexion(conexion);
				if (inserts != null && inserts.size() > 0) {
					transaccionesCatalogo.ejecutarInsertTablaDestino(inserts,catalogo.getCadenaConexDestino(),catalogo.getNombreDestino());
					if (actualizacion.isStoreProcedure(catalogo.getNombreStoreProcedure())) {
						transaccionesCatalogo.ejecutarStoreProcedure(catalogo.getNombreStoreProcedure(),lastUpdateTs, catalogo.getIdCarga(), actualizacion.getIdLDC(),actualizacion.getIdUser());
					} else {
						logUltimaEjecucion = "No se especifico un stored procedure válida. "
								+ "El servicio solamente copio los datos a una tabla temporal";
						actualizacion.cerrarCarga(0,logUltimaEjecucion,catalogo.getDescripcion());
					}
				} else {
					logUltimaEjecucion = ". No existe informacion para actualizar el catalogo.";
					actualizacion.cerrarCarga(0, logUltimaEjecucion,
							catalogo.getDescripcion());
				}
			} else {
				logUltimaEjecucion = ". No se pudo conectar al servidor de la base de datos de origen";
				actualizacion.cerrarCarga(0, logUltimaEjecucion, catalogo.getDescripcion());
			}
		} else {
			logUltimaEjecucion = ". Cadena de conexión a la base de datos de origen no encontrada";
			actualizacion.cerrarCarga(0, logUltimaEjecucion, catalogo.getDescripcion());
		}
	}
	
	private boolean verificarOrigenDatos(String cadenaConexOrigen,
			String descripcion) {

		String vacio = "";
		boolean existe = true;

		if (vacio.equals(cadenaConexOrigen)) {
			existe = false;
		}
		return existe;
	}
	
	private List<String> generarCadenaInsert(ResultSet resultSetOrigen,
			String nombreDestino) {

		ResultSetMetaData rSetMetaData = null;
		int numeroColumnas = 0;
		StringBuilder sqlInsert = null;
		List<String> sqlInserts = new ArrayList<String>();
		String tipoDato = null;
		Formateador formateador = new Formateador();
		
		try {
			rSetMetaData = resultSetOrigen.getMetaData();
			numeroColumnas = rSetMetaData.getColumnCount();
			
			while (resultSetOrigen.next()) {
				sqlInsert = new StringBuilder();
				for (int i = 1; i <= numeroColumnas; i++) {
					tipoDato = rSetMetaData.getColumnTypeName(i);
					
					if (i == 1) {
						sqlInsert.append("INSERT INTO ").append(nombreDestino).append(" VALUES (");
					} else {
						sqlInsert.append(",");
					}
					sqlInsert.append(formateador.formatearDato(tipoDato,resultSetOrigen.getString(i)));
				}
				sqlInsert.append(")");
				sqlInserts.add(sqlInsert.toString());
				lastUpdateTs = resultSetOrigen.getString("LASTUPD_TS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sqlInserts;
	}
}
