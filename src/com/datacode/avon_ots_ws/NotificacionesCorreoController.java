package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.NotificacionesCorreoDTO;

public class NotificacionesCorreoController {

	/**
	 * Obtener notificaciones vencidas.
	 *
	 * @return the list
	 */
	public List<NotificacionesCorreoDTO> obtenerNotificacionesCorreoVencidas() {
		List<NotificacionesCorreoDTO> v_notificaciones = new ArrayList<NotificacionesCorreoDTO>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_call = null;
		if (v_conn != null) {
			try {
				v_call = v_conn.prepareCall("{call SP_NotificacionesCorreoVencidasObtener()}");
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_call);
				while (v_rs.next()) {
					NotificacionesCorreoDTO v_notificacion = new NotificacionesCorreoDTO();
					v_notificacion.setIdNotificacionCorreo(v_rs.getInt("ID_NOTIFICACION_CORREO"));
					v_notificacion.setNombre(v_rs.getString("NOMBRE"));
					v_notificacion.setDescripcion(v_rs.getString("DESCRIPCION"));
					v_notificacion.setDestinatarios(v_rs.getString("DESTINATARIOS"));
					v_notificacion.setAsunto(v_rs.getString("ASUNTO"));
					v_notificacion.setCuerpo(v_rs.getString("CUERPO"));
					v_notificacion.setInicioIntervaloEjecucion(v_rs.getString("INICIO_INTERVALO_EJECUCION"));
					v_notificacion.setFinIntervaloEjecucion(v_rs.getString("FIN_INTERVALO_EJECUCION"));
					v_notificaciones.add(v_notificacion);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD(
					"NotificacionesCorreoController-obtenerNotificacionesCorreoVencidas",
					"M1", "Error en obtenerNotificacionesCorreoVencidas", e.getMessage(), 1);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_notificaciones;
	}

	/**
	 * Obtener tabla store.
	 *
	 * @param idUsuario the id usuario
	 * @param stored the stored
	 * @return the notificaciones automaticas dto
	 */
	public NotificacionesCorreoDTO obtenerTablaStore(int idUsuario, String stored) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		StringBuilder texto = new StringBuilder();
		int cont = 0;
		if (v_conn != null) {
			try {
				cs = v_conn.prepareCall("{call " + stored + "()}");
				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				boolean ponerHeader = true;
				texto.append("<table border='1'>");
				while (rs.next()) {
					cont++;
					if (ponerHeader) {
						texto.append("<tr>");
						for (int i = 1; i <= columnsNumber; i++) {
							texto.append("<td><b>");
							texto.append(rsmd.getColumnName(i));
							texto.append("</b></td>");
						}
						texto.append("</tr>");
						ponerHeader = false;
					}
					texto.append("<tr>");
					for (int i = 1; i <= columnsNumber; i++) {
						texto.append("<td>");
						texto.append(rs.getString(i));
						texto.append("</td>");
					}
					texto.append("</tr>");
				}
				texto.append("</table>");
				rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD(
					"NotificacionesCorreoController-obtenerTablaStore",
					"M2", "Error en obtenerTablaStore", e.getMessage(), idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		NotificacionesCorreoDTO c = new NotificacionesCorreoDTO();
		c.setId("" + cont);
		c.setDescripcion(texto.toString());
		return c;
	}

	/**
	 * Obtener string stored.
	 *
	 * @param idUsuario the id usuario
	 * @param stored the stored
	 * @return the notificaciones automaticas dto
	 */
	public String obtenerStringStored(int idUsuario, String stored) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		StringBuilder texto = new StringBuilder();
		if (v_conn != null) {
			try {
				cs = v_conn.prepareCall("{call " + stored + "()}");
				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				texto.append("");
				while (rs.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
						texto.append(rs.getString(i));
					}
				}
				rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD(
					"NotificacionesCorreoController-obtenerTablaStore",
					"M2", "Error en obtenerTablaStore", e.getMessage(), idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return texto.toString();
	}

	public boolean actualizarFechaUltimaEjecucion(NotificacionesCorreoDTO p_notificacionCorreo, int p_idUsuario, int p_actualizar) {
		boolean v_res = false;
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_call = null;
		if (v_conn != null) {
			try {
				v_call = v_conn.prepareCall("{call SP_NotificacionesCorreoActualizarUltimaEjecucion(?,?)}");
				v_call.setObject("p_idNotificacionCorreo", p_notificacionCorreo.getIdNotificacionCorreo(), Types.INTEGER);
				v_call.setObject("p_actualizar", p_actualizar, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_call);
				if (v_rs.next()) {
					v_res = true;
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD(
					"NotificacionesCorreoController-actualizarFechaUltimaEjecucion",
					"M3", "Error en actualizarFechaUltimaEjecucion", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_res;
	}

}
