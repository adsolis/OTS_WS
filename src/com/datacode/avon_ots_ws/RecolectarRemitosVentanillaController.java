package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.RecolectarRemitosVentanilla;

public class RecolectarRemitosVentanillaController {

	private static final String NOMBRE_MODULO = "RemitosVentanilla";

	/**
	 * Método que obtiene la lista de campañas.
	 * 
	 */
	public List<String> obtenerListaCampanias(int p_idUsuario) {
		List<String> v_campanias = new ArrayList<String>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_PW_CAMPANIA(?,?,?,?)}");
				v_cs.setObject("P_TIPO_CU", NOMBRE_MODULO, Types.VARCHAR);
				v_cs.setObject("P_ID_ZONA", null, Types.INTEGER);
				v_cs.setObject("P_ID_LDC", null, Types.INTEGER);
				v_cs.setObject("P_ID_RUTA", null, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					v_campanias.add(v_rs.getString("CAMPANIA"));
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M1", "Error en obtenerListaCampanias", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M1", "Error en obtenerListaCampanias", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
			
		}
		return v_campanias;
	}

	/**
	 * Método que obtiene la lista de zonas.
	 */
	public List<RecolectarRemitosVentanilla> obtenerListaZonas(int p_idUsuario) {
		List<RecolectarRemitosVentanilla> v_zonas = new ArrayList<RecolectarRemitosVentanilla>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_PW_ZONA(?,?)}");
				v_cs.setObject("P_ID_LDC", null, Types.INTEGER);
				v_cs.setObject("P_TIPO_CU", NOMBRE_MODULO, Types.VARCHAR);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					RecolectarRemitosVentanilla v_zona = new RecolectarRemitosVentanilla();
					v_zona.setIdZona(v_rs.getString("ID_ZONA"));
					v_zona.setDescripcionZona(v_rs.getString("ZONA"));
					v_zonas.add(v_zona);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M2", "Error en obtenerListaZonas", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M2", "Error en obtenerListaZonas", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
			
		}
		return v_zonas;
	}

	/**
	 * Método que obtiene la lista de causas de no recolección de remitos.
	 */
	public List<RecolectarRemitosVentanilla> obtenerCausasNoRecoleccion(int p_idUsuario) {
		List<RecolectarRemitosVentanilla> v_causas = new ArrayList<RecolectarRemitosVentanilla>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_RecolectarRemitosVentanilla_ObtenerRazonesNoRecoleccion()}");
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					RecolectarRemitosVentanilla v_causa = new RecolectarRemitosVentanilla();
					v_causa.setIdCausaNoRecoleccion(v_rs.getInt("ID_ESTATUS_REMITO"));
					v_causa.setDescripcionCausaNoRecoleccion(v_rs.getString("DESCRIPCION"));
					v_causas.add(v_causa);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M3", "Error en obtenerCausasNoRecoleccion", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M3", "Error en obtenerCausasNoRecoleccion", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_causas;
	}

	/**
	 * Método que obtiene la lista de representantes con remitos.
	 */
	public List<RecolectarRemitosVentanilla> obtenerRepresentantes(Integer p_campania, Integer p_idZona, int p_idUsuario) {
		List<RecolectarRemitosVentanilla> v_representantes = new ArrayList<RecolectarRemitosVentanilla>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_RecolectarRemitosVentanilla_ObtenerRepresentantes(?,?)}");
				v_cs.setObject("p_Campania", p_campania, Types.INTEGER);
				v_cs.setObject("p_IdZona", p_idZona, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					RecolectarRemitosVentanilla v_representante = new RecolectarRemitosVentanilla();
					v_representante.setAccount(v_rs.getInt("ACCOUNT"));
					v_representante.setNombre(v_rs.getString("NOMBRE"));
					v_representante.setIdRemito(v_rs.getInt("ID_REMITO"));
					v_representante.setCampania(v_rs.getString("CAMPANIA"));
					v_representante.setIdZona(v_rs.getString("ID_ZONA"));
					v_representante.setDescripcionZona(v_rs.getString("DESCRIPCION_ZONA"));
					v_representante.setCantidadRecolectar(v_rs.getInt("CANTIDAD_A_RECOLECTAR"));
					v_representante.setCantidadRecolectada(v_rs.getInt("CANTIDAD_RECOLECTADA"));
					v_representantes.add(v_representante);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M4", "Error en obtenerRepresentantes", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M4", "Error en obtenerRepresentantes", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_representantes;
	}

	/**
	 * Metodo que verifica si una zona ya esta cerrada.
	 * 
	 * @param p_campania
	 * @param p_idZona
	 * @param p_idUsuario
	 * @return
	 */
	public boolean verificarCierreDeZona(Integer p_campania, Integer p_idZona, int p_idUsuario) {
		boolean zonaCerrada = false;
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_RecolectarRemitosVentanilla_VerificarCierreZona(?,?)}");
				v_cs.setObject("p_Campania", p_campania, Types.INTEGER);
				v_cs.setObject("p_IdZona", p_idZona, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				if (v_rs.next()) {
					if (v_rs.getInt("CANTIDAD") > 0) {
						zonaCerrada = true;
					} else {
						zonaCerrada = false;
					}
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M4", "Error en verificarCierreDeZona", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M4", "Error en verificarCierreDeZona", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return zonaCerrada;
	}

	/**
	 * Método que realiza la recolección de remitos.
	 */
	public boolean recolectarRemitos(Integer p_cantidadRecolectados, String p_comentarios, Integer p_causaNoRecoleccion,
			RecolectarRemitosVentanilla p_remito, String p_usuario, int p_idUsuario) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				int v_estatus = 0;
				String v_tipoRecoleccion = "";
				if (p_causaNoRecoleccion != 0) {
					v_estatus = p_causaNoRecoleccion;
					v_tipoRecoleccion = "NO_RECOLECCION";
				} else {
					v_tipoRecoleccion = "RECOLECCION";
				}
				v_cs = v_conn.prepareCall("{call SP_RecolectarRemitosVentanilla_RecolectarRemitos(?,?,?,?,?,?)}");
				v_cs.setObject("p_tipoRecoleccion", v_tipoRecoleccion, Types.VARCHAR);
				v_cs.setObject("p_collectedQuantity", p_cantidadRecolectados, Types.INTEGER);
				v_cs.setObject("p_comments", p_comentarios, Types.VARCHAR);
				v_cs.setObject("p_usuarioActualiza", p_usuario, Types.VARCHAR);
				v_cs.setObject("p_idRemito", p_remito.getIdRemito(), Types.INTEGER);
				v_cs.setObject("p_returnStatus", v_estatus, Types.INTEGER);
				AccesoBD.executeRetrieveResultSet(v_cs);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M5", "Error en recolectarRemitos", e.getMessage(), p_idUsuario);
				return false;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW038_7:RecolectarRemitosVentanilla", "M5", "Error en recolectarRemitos", e.getMessage(), p_idUsuario);
				return false;
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return true;
	}

}
