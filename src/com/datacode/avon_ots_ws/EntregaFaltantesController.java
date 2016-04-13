package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.EntregaFaltantes;

public class EntregaFaltantesController {

	/**
	 * Método que obtiene la lista de representantes.
	 * @param p_registro
	 * @param p_nombre
	 * @param p_idUsuario
	 * @return
	 */
	public List<EntregaFaltantes> obtenerRepresentantes(String p_registro, String p_nombre, int p_idUsuario) {
		List<EntregaFaltantes> v_representantes = new ArrayList<EntregaFaltantes>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_EntregaFaltantes_ObtenerRepresentantes(?,?)}");
				v_cs.setObject("p_registro", p_registro, Types.VARCHAR);
				v_cs.setObject("p_nombre", p_nombre, Types.VARCHAR);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					EntregaFaltantes v_representante = new EntregaFaltantes();
					v_representante.setRegistroRepresentante(v_rs.getString("ACCOUNT"));
					v_representante.setNombreRepresentante(v_rs.getString("NOMBRE"));
					v_representante.setDomicilio(v_rs.getString("DIRECCION"));
					v_representante.setIdZona(String.valueOf(v_rs.getInt("ID_ZONA")));
					v_representante.setDescripcionZona(v_rs.getString("ZONA"));
					v_representante.setIdRepresentante(v_rs.getInt("ID_REPRESENTANTE"));
					v_representantes.add(v_representante);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW021_5:EntregaFaltantes", "M1", "Error en obtenerRepresentantes", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_representantes;
	}

	/**
	 * Método que obtiene la lista de órdenes por representante.
	 * @param p_idRepresentante
	 * @param p_idUsuario
	 * @return
	 */
	public List<EntregaFaltantes> obtenerOrdenes(Integer p_idRepresentante, int p_idUsuario) {
		List<EntregaFaltantes> v_ordenes = new ArrayList<EntregaFaltantes>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_EntregaFaltantes_ObtenerOrdenes(?)}");
				v_cs.setObject("p_idRepresentante", p_idRepresentante, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					EntregaFaltantes v_orden = new EntregaFaltantes();
					v_orden.setDescripcionZona(v_rs.getString("ZONA"));
					v_orden.setDescripcionCampania(v_rs.getString("CAMPANIA"));
					v_orden.setIdOrden(v_rs.getInt("ID_ORDEN"));
					v_orden.setClaveOrden(v_rs.getString("CLAVE_ORDEN"));
					v_orden.setNombreRepresentante(v_rs.getString("NOMBRE"));
					v_ordenes.add(v_orden);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW021_5:EntregaFaltantes", "M2", "Error en obtenerOrdenes", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_ordenes;
	}

	/**
	 * Método que obtiene la lista de cajas por orden.
	 * @param p_idOrden
	 * @param p_idUsuario
	 * @return
	 */
	public List<EntregaFaltantes> obtenerCajas(Integer p_idOrden, int p_idUsuario) {
		List<EntregaFaltantes> v_cajas = new ArrayList<EntregaFaltantes>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_EntregaFaltantes_ObtenerCajas(?)}");
				v_cs.setObject("p_idOrden", p_idOrden, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					EntregaFaltantes v_caja = new EntregaFaltantes();
					v_caja.setIdItem(v_rs.getInt("ID_ITEM"));
					v_caja.setCodigoBarras(v_rs.getString("CODIGO_BARRAS"));
					v_caja.setDescripcionItem(v_rs.getString("DESCRIPCION_ITEM"));
					v_caja.setSeleccionado(false);
					v_cajas.add(v_caja);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW021_5:EntregaFaltantes", "M3", "Error en obtenerCajas", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_cajas;
	}

	/**
	 * Método que obtiene la lista de premios y unitarios por orden.
	 * @param p_idOrden
	 * @param p_idUsuario
	 * @return
	 */
	public List<EntregaFaltantes> obtenerPremiosUnitarios(Integer p_idOrden, int p_idUsuario) {
		List<EntregaFaltantes> v_premiosUnitarios = new ArrayList<EntregaFaltantes>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_EntregaFaltantes_ObtenerPremiosUnitarios(?)}");
				v_cs.setObject("p_idOrden", p_idOrden, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					EntregaFaltantes v_premioUnitario = new EntregaFaltantes();
					v_premioUnitario.setIdItem(v_rs.getInt("ID_ITEM"));
					v_premioUnitario.setFSC(v_rs.getString("FSC"));
					v_premioUnitario.setEAN13(v_rs.getString("EAN13"));
					v_premioUnitario.setDescripcionItem(v_rs.getString("DESCRIPCION_ITEM"));
					v_premiosUnitarios.add(v_premioUnitario);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW021_5:EntregaFaltantes", "M4", "Error en obtenerPremiosUnitarios", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_premiosUnitarios;
	}

	/**
	 * Método que realiza la entrega de ítems.
	 * @param p_items
	 * @param p_idUsuario
	 * @param p_usuario
	 * @return
	 */
	public int entregarItems(EntregaFaltantes[] p_items, int p_idUsuario, String p_usuario) {
		int v_cantidad = 0;
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				for (EntregaFaltantes item : p_items) {
					v_cs = v_conn.prepareCall("{call SP_EntregaFaltantes_EntregarCajas(?,?)}");
					v_cs.setObject("p_idItem", item.getIdItem(), Types.INTEGER);
					v_cs.setObject("p_usuarioActualiza", p_usuario, Types.VARCHAR);
					AccesoBD.executeRetrieveResultSet(v_cs);
					v_cantidad++;
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				Utils.GuardarLogMensajeBD("UCW021_5:EntregaFaltantes", "M5", "Error en entregarItems", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_cantidad;
	}

}
