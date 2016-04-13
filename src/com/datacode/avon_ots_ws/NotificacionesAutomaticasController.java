package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.NotificacionesAutomaticasDTO;

public class NotificacionesAutomaticasController {

	/**
	 * Obtener notificaciones vencidas.
	 *
	 * @return the list
	 */
	public List<NotificacionesAutomaticasDTO> obtenerNotificacionesVencidas() {
		List<NotificacionesAutomaticasDTO> v_notificaciones = new ArrayList<NotificacionesAutomaticasDTO>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_call = null;
		if (v_conn != null) {
			try {
				v_call = v_conn.prepareCall("{call SP_NotificacionesObtener(?)}");
				v_call.setObject("p_tipoConsulta", "EJECUTAR", Types.VARCHAR);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_call);
				while (v_rs.next()) {
					NotificacionesAutomaticasDTO v_notificacion = new NotificacionesAutomaticasDTO();
					v_notificacion.setIdNotificacion(v_rs.getInt("ID_NOTIFICACION"));
					v_notificacion.setNombre(v_rs.getString("NOMBRE"));
					v_notificacion.setDescripcion(v_rs.getString("DESCRIPCION"));
					v_notificacion.setTexto(v_rs.getString("TEXTO"));
					v_notificacion.setDestinatarios(v_rs.getString("DESTINATARIOS"));
					v_notificaciones.add(v_notificacion);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD(
					"UCS057_1:Depurar automáticamente BD OTS LDC y OTSe.",
					"M1", "Error en obtenerNotificacionesVencidas", e.getMessage(), 1);
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
	public NotificacionesAutomaticasDTO obtenerTablaStore(int idUsuario, String stored) {
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
					"UCS057_1:Depurar automáticamente BD OTS LDC y OTSe.",
					"M2", "Error en obtenerTablaStore", e.getMessage(), idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		NotificacionesAutomaticasDTO c = new NotificacionesAutomaticasDTO();
		c.setId("" + cont);
		c.setDescripcion(texto.toString());
		return c;
	}

	public boolean actualizarFechaUltimaEjecucion(NotificacionesAutomaticasDTO p_notificacion, int p_idUsuario) {
		boolean v_res = false;
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_call = null;
		if (v_conn != null) {
			try {
				v_call = v_conn.prepareCall("{call SP_NotificacionesActualizarUltimaEjecucion(?)}");
				v_call.setObject("p_idNotificacion", p_notificacion.getIdNotificacion(), Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_call);
				if (v_rs.next()) {
					v_res = true;
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD(
					"UCS057_1:Depurar automáticamente BD OTS LDC y OTSe.",
					"M3", "Error en actualizarFechaUltimaEjecucion", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_res;
	}

	public boolean insertarCorreoEnviado(String p_remitente, String p_destinatarios, String p_asunto,
			String p_textoCuerpo, int p_enviado, int p_idUsuario, int idLDC) {
		boolean v_res = false;
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_call = null;
		if (v_conn != null) {
			try {
				v_call = v_conn.prepareCall("{call SP_NotificacionesCorreoEnviado(?,?,?,?,?,?,?,?,?)}");
				v_call.setObject("p_tipoCorreo", p_enviado, Types.INTEGER);
				v_call.setObject("p_idLlegadaProgramada", null, Types.INTEGER);
				v_call.setObject("p_de", p_remitente, Types.VARCHAR);
				v_call.setObject("p_para", p_destinatarios, Types.VARCHAR);
				v_call.setObject("p_asunto", p_asunto, Types.VARCHAR);
				v_call.setObject("p_textoCuerpo", p_textoCuerpo, Types.VARCHAR);
				v_call.setObject("p_usuarioActualiza", "DATACODE", Types.VARCHAR);
				v_call.setObject("p_idReporte", null, Types.INTEGER);
				v_call.setObject("p_idLDC", idLDC, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_call);
				if (v_rs.next()) {
					v_res = true;
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD(
					"UCS057_1:Depurar automáticamente BD OTS LDC y OTSe.",
					"M4", "Error en insertarCorreoEnviado", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_res;
	}

}
