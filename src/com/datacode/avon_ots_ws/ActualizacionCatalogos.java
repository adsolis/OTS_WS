/**
 *
 *  @since 12/01/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.Catalogo;
import com.datacode.avon_ots_ws.model.ModelEstatusCatalogo;
import com.datacode.avon_ots_ws.model.ReporteConsultaBuenVecinoModel;

/**
 * @author jessica.leon
 * @since 12/01/2012
 * 
 */
public class ActualizacionCatalogos {

	// private String logUltimaEjecucion;
	private TransaccionesCatalogo transaccionesCatalogo = null;
	private Catalogo catalogo;
	private int idUser;
	private int idLDC;
	private CallableStatement callableStatement;
	private Connection connection;

	public ActualizacionCatalogos() {

	}

	public void realizarActualizacionCatalogo(int idParamCarga, int idLDC,
			int idUser) {

		transaccionesCatalogo = new TransaccionesCatalogo();
		catalogo = transaccionesCatalogo.consultarParametrosCarga(idParamCarga);
		String logUltimaEjecucion = null;
		this.idUser = idUser;
		this.idLDC = idLDC;

		if (catalogo != null) {
			logUltimaEjecucion = "Inicia proceso de obtención del catalogo "
					+ catalogo.getDescripcion();
			transaccionesCatalogo.cerrarCarga(1, logUltimaEjecucion,
					catalogo.getLastUpdTs());
			Utils.GuardarLogMensajeBD(
					"ADMIN",
					"ADMIN_1.01",
					"Inicia proceso de obtención del catalogo "
							+ catalogo.getDescripcion(),
					"Inicia proceso de obtención del catalogo "
							+ catalogo.getDescripcion(), 1);
			if (isNombreOrigen(catalogo.getNombreOrigen(),
					catalogo.getDescripcion())) {
				generaObjetoPorTipoOrigen(catalogo.getTipoOrigen());
			} else {
				logUltimaEjecucion = ". No se especificó una fuente de origen valida. Carga no se realizara ";
				cerrarCarga(0, logUltimaEjecucion, catalogo.getDescripcion());
			}
		}
	}

	private boolean isNombreOrigen(String nombreOrigen, String descripcion) {

		String vacio = "";
		boolean existe = true;

		if (vacio.equals(nombreOrigen)) {
			existe = false;
		}
		return existe;
	}

	private void generaObjetoPorTipoOrigen(int origen) {
		switch (origen) {
		case 1:
			// ActualizacionCatalogoDesdeArchivo actualizacionArchivo = new
			// ActualizacionCatalogoDesdeArchivo();
			// actualizacionArchivo.iniciarCargaCatalogos();
			break;
		case 2:
			ActualizacionCatalogoDesdeTabla actualizacionTabla = new ActualizacionCatalogoDesdeTabla(
					this);
			actualizacionTabla.iniciarCargaCatalogos();
			break;
		default:
			break;
		}
	}

	public int checarDisponibilidadServicio() {
		int catalogosActivos = 0;
		transaccionesCatalogo = new TransaccionesCatalogo();
		catalogosActivos = transaccionesCatalogo.consultarCatalogosActivos();
		return catalogosActivos;
	}

	public List<Catalogo> consultarEstatusActualizacion() {
		List<Catalogo> catalogos = new ArrayList<Catalogo>();
		transaccionesCatalogo = new TransaccionesCatalogo();
		catalogos = transaccionesCatalogo.consultarEstatusCatalogos();
		return catalogos;
	}

	public boolean isStoreProcedure(String nombreSP) {
		String vacio = "";
		boolean existe = true;

		if (vacio.equals(nombreSP)) {
			existe = false;
		}
		return existe;
	}

	public void cerrarCarga(int estatus, String logUltimaEjecucion,
			String descripcion) {
		String ultimaEjecucion = "No se obtuvo información del catalogo"
				.concat(descripcion);
		ultimaEjecucion = ultimaEjecucion.concat(logUltimaEjecucion);
		transaccionesCatalogo.cerrarCarga(estatus, ultimaEjecucion,
				catalogo.getLastUpdTs());
	}

	/**
	 * @author jessica.leon
	 * @since 30/01/2012
	 * @return the transaccionesCatalogo
	 */
	public TransaccionesCatalogo getTransaccionesCatalogo() {
		return transaccionesCatalogo;
	}

	/**
	 * @author jessica.leon
	 * @since 30/01/2012
	 * @param transaccionesCatalogo
	 *            the transaccionesCatalogo to set
	 */
	public void setTransaccionesCatalogo(
			TransaccionesCatalogo transaccionesCatalogo) {
		this.transaccionesCatalogo = transaccionesCatalogo;
	}

	/**
	 * @author jessica.leon
	 * @since 30/01/2012
	 * @return the catalogo
	 */
	public Catalogo getCatalogo() {
		return catalogo;
	}

	/**
	 * @author jessica.leon
	 * @since 30/01/2012
	 * @param catalogo
	 *            the catalogo to set
	 */
	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	/**
	 * @author jessica.leon
	 * @since 30/01/2012
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @author jessica.leon
	 * @since 30/01/2012
	 * @param idUser
	 *            the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * @author jessica.leon
	 * @since 30/01/2012
	 * @return the idLDC
	 */
	public int getIdLDC() {
		return idLDC;
	}

	/**
	 * @author jessica.leon
	 * @since 30/01/2012
	 * @param idLDC
	 *            the idLDC to set
	 */
	public void setIdLDC(int idLDC) {
		this.idLDC = idLDC;
	}

	public List<ModelEstatusCatalogo> obtenerEstatusReplicacion(int idUsuario) {
		List<ModelEstatusCatalogo> regs = new ArrayList<ModelEstatusCatalogo>();
		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Actualizacion_Manual_Consultar_Estatus()}");

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelEstatusCatalogo r = new ModelEstatusCatalogo();
					r.setAvance(rs.getString("AVANCE"));
					r.setNombreOrigen(rs.getString("NOMBRE_ORIGEN"));
					r.setEstatus(rs.getString("ESTATUS"));
					r.setIdReplicacionTabla(rs.getInt("ID_REPLICACIONES_TABLA"));
					r.setTipo(rs.getString("TIPO_REPLICACION"));
					regs.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("obtenerEstatusReplicacion", "M11",
						"Surgió un error al obtenerEstatusReplicacion",
						ex.getMessage(), idUsuario);

			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return regs;
	}

	public String actualizarFechaEjecucion(int idUsuario) {
		String mensaje = "";
		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Actualizacion_Manual_Actualizar_Fecha_Ejecucion()}");

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					mensaje = rs.getString("ERROR");
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("actualizarFechaEjecucion", "M11",
						"Surgió un error al actualizar la fecha ejecucion",
						ex.getMessage(), idUsuario);

			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return mensaje;
	}
}
