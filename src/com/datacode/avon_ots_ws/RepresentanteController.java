/**
 *
 *  @since 04/01/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.Representante;

/**
 * @author jessica.leon
 * @since 04/01/2012
 * 
 */
public class RepresentanteController {

	private ResultSet resultSet;
	private CallableStatement callableStatement;
	private Connection connection;

	public RepresentanteController() {

	}

	public Representante[] obtenerRepresentantesSinRutaAsignada(int idZona,
			int idCampania, String tipoCU, String tipo, int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<Representante> representantes = new ArrayList<Representante>();
		Representante representante = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Ret_Lista_Representantes_PreEnrutado(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_TIPO", tipo, Types.VARCHAR);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					representante = new Representante();
					representante.setIdRepresentante(resultSet
							.getInt("ID_REPRESENTANTE"));
					representante.setRegistro(resultSet.getString("ACCOUNT"));
					representante.setNombre(resultSet.getString("NOMBRE"));
					representante
							.setDomicilio(resultSet.getString("DOMICILIO"));
					representante.setEstado(resultSet.getString("ESTADO"));
					representante
							.setPoblacion(resultSet.getString("POBLACION"));
					representante.setDomicilioAlterno(resultSet
							.getString("DOMICILIO_ALTERNO"));
					representantes.add(representante);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						tipoCU,
						"M1",
						"Surgió un error al seleccionar representantes sin ruta asignada",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return (Representante[]) representantes
				.toArray(new Representante[representantes.size()]);
	}

	public String actualizarPreenrutadoDeRepresentante(int idLDC,
			int idRepresentante, int idZona, int idRuta, int secuenciaActual,
			int secuenciaAnterior, int idUsuario, String tipoCU,
			String tipoEnrutado) {

		connection = AccesoBD.AbrirConexionOTS();
		String mensaje = null;

		try {
			if (connection != null) {
				callableStatement = connection
						.prepareCall("{call SP_Actualiza_PreEnrutado(?,?,?,?,?,?,?)}");
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				callableStatement.setObject("P_ID_REPRESENTANTE",
						idRepresentante, Types.INTEGER);
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_RUTA", idRuta, Types.INTEGER);
				callableStatement.setObject("P_SEC_ACTUAL", secuenciaActual,
						Types.INTEGER);
				callableStatement.setObject("P_SEC_ANTERIOR",
						secuenciaAnterior, Types.INTEGER);
				callableStatement.setObject("P_TIPO_ENRUTADO", tipoEnrutado,
						Types.VARCHAR);
				callableStatement.execute();
				mensaje = "Se realizo la inserción correctamente";
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			Utils.GuardarLogMensajeBD(
					tipoCU,
					"M2",
					"Surgió un error al insertar en la tabla PW_REPRESENTANTES_POR_RUTA",
					ex.getMessage(), idUsuario);
			return "Surgió un error al insertar en la tabla PW_REPRESENTANTES_POR_RUTA ";
		} finally {
			AccesoBD.CerrarConexion(connection);
		}
		return mensaje;
	}

	public Representante[] seleccionarRepresentantesConOrden(int idZona,
			int idCampania, int idRuta, String registro, String tipoCU, int idUsuario) {
		connection = AccesoBD.AbrirConexionOTS();
		List<Representante> representantes = new ArrayList<Representante>();
		Representante representante = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ConsultarRepresentantesSecuenciaEnRuta(?,?,?,?)}");
				if (idZona == 0) {
					callableStatement.setObject("P_ID_ZONA", null, Types.INTEGER);
				} else {
					callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				}
				
				if (idCampania == 0) {
					callableStatement.setObject("P_ID_CAMPANIA", null,
							Types.INTEGER);
				} else {
					callableStatement.setObject("P_ID_CAMPANIA", idCampania,
							Types.INTEGER);
				}
				
				if (idRuta == 0) {
					callableStatement.setObject("P_ID_RUTA", null, Types.INTEGER);
				} else {
					callableStatement.setObject("P_ID_RUTA", idRuta, Types.INTEGER);
				}
				
				if (registro == null || registro.isEmpty()) {
					callableStatement.setObject("p_registro", null, Types.VARCHAR);
				} else {
					callableStatement.setObject("p_registro", registro, Types.VARCHAR);
				}
				
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					representante = new Representante();
					representante.setIdRepresentante(resultSet
							.getInt("ID_REPRESENTANTE"));
					representante.setRegistro(resultSet.getString("REGISTRO"));
					representante.setNombre(resultSet.getString("NOMBRE"));
					representante
							.setDomicilio(resultSet.getString("DOMICILIO"));
					representante.getRepresentantePorRuta().setDescRuta(
							resultSet.getString("CLAVE_RUTA"));
					representante
							.getRepresentantePorRuta()
							.setSeqEntregaAnterior(
									resultSet
											.getString("SECUENCIA_ENTREGA_ANTERIOR"));
					representante
							.getRepresentantePorRuta()
							.setSeqEntregaReciente(
									resultSet
											.getString("SECUENCIA_ENTREGA_RECIENTE"));
					representantes.add(representante);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(tipoCU, "M3",
						"Surgió un error al seleccionar representantes",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return (Representante[]) representantes
				.toArray(new Representante[representantes.size()]);
	}

	public String actualizarSecuenciaRepresentante(int idRepresentante,
			int nuevaSecuencia, String tipoCU, int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		String mensaje = null;

		try {
			if (connection != null) {
				callableStatement = connection
						.prepareCall("{call SP_ActualizarSecuenciaDeRepresentante(?,?,?)}");
				callableStatement.setObject("P_ID_REPRESENTANTE",
						idRepresentante, Types.INTEGER);
				callableStatement.setObject("P_SECUENCIA_NUEVA",
						nuevaSecuencia, Types.INTEGER);
				callableStatement.setObject("P_ID_USUARIO", idUsuario,
						Types.INTEGER);
				callableStatement.execute();
				mensaje = "Se realizo la inserción correctamente";
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			Utils.GuardarLogMensajeBD(
					tipoCU,
					"M4",
					"Surgió un error al actualizar en la tabla PW_REPRESENTANTES_POR_RUTA",
					ex.getMessage(), idUsuario);
			return "Surgió un error al actualizar el registro de la representante";
		} finally {
			AccesoBD.CerrarConexion(connection);
		}
		return mensaje;
	}
}
