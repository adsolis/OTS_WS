package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

import com.datacode.avon_ots_ws.model.Campania;
import com.datacode.avon_ots_ws.model.ModelRutaEspecial;
import com.datacode.avon_ots_ws.model.ModelRutasEspecialesItems;

public class ArmadoRutasEspecialesController {

	private CallableStatement callableStatement;
	private Connection connection;

	public ArmadoRutasEspecialesController() {

	}

	public ModelRutaEspecial generarFolio(int idUsuario, String folio,
			int idRutaEspecial) {
		ModelRutaEspecial registro = new ModelRutaEspecial();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesArmadoGeneraFolio()}");

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {

					registro.setClaveRutaEspecial(rs
							.getString("CLAVE_RUTA_ESPECIAL"));
					registro.setIdRutaEspecial(rs.getInt("ID_RUTA_ESPECIAL"));

				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ArmadoRutasEspeciales",
						"M11",
						"Surgió un error al obtener el folio de la claverutaespecial",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return registro;
	}

	public List<ModelRutaEspecial> obtieneRegistros(int idUsuario,
			String registro) {
		List<ModelRutaEspecial> regs = new ArrayList<ModelRutaEspecial>();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesArmadoObtenerRegistros(?)}");
				callableStatement.setObject("P_REGISTRO", registro,
						Types.VARCHAR);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelRutaEspecial r = new ModelRutaEspecial();
					r.setIdOrden(rs.getInt("ID_ORDEN"));
					r.setClaveOrden(rs.getString("CLAVE_ORDEN"));
					r.setNombre(rs.getString("NOMBRE"));
					r.setRegistro(rs.getString("REGISTRO"));
					String b = rs.getString("BLOCKED_STATUS");
					if (b == null || "0".equals(b)) {
						r.setCampania("0");
					} else if ("-99".equals(b)) {
						r.setCampania("N");
					} else if ("1".equals(b)) {
						r.setCampania("1");
					} else if ("2".equals(b)) {
						r.setCampania("2");
					}

					regs.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("ArmadoRutasEspeciales", "M11",
						"Surgió un error al obtener los registros validos",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return regs;
	}

	public List<ModelRutasEspecialesItems> obtieneItems(int idUsuario,
			String registros, int tipo) {
		List<ModelRutasEspecialesItems> regs = new ArrayList<ModelRutasEspecialesItems>();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesArmadoObtenerItems(?,?)}");
				callableStatement.setObject("P_REGISTROS", registros,
						Types.VARCHAR);
				callableStatement.setObject("P_TIPO", tipo, Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				if (tipo == 1) {
					while (rs.next()) {
						ModelRutasEspecialesItems r = new ModelRutasEspecialesItems();
						r.setCantidadSolicitada(rs
								.getInt("CANTIDAD_SOLICITADA"));
						r.setClaveOrden(rs.getString("CLAVE_ORDEN"));
						r.setCodigoBarras(rs.getString("CODIGO_BARRAS"));
						r.setDescripcion(rs.getString("DESCRIPCION"));
						r.setIdItem(rs.getInt("ID_ITEM"));
						r.setTipo("CAJA");
						regs.add(r);
					}
				} else {
					while (rs.next()) {
						ModelRutasEspecialesItems r = new ModelRutasEspecialesItems();
						r.setCantidadSolicitada(rs
								.getInt("CANTIDAD_SOLICITADA"));
						r.setClaveOrden(rs.getString("CLAVE_ORDEN"));
						r.setDescripcion(rs.getString("DESCRIPCION"));
						r.setIdItem(rs.getInt("ID_ITEM"));
						r.setCodigo_FSC(rs.getString("CODIGO_FSC"));
						r.setCodigo_FSC_EAN13(rs.getString("CODIGO_FSC_EAN13"));
						r.setTipo("UNIT/PREMIO");
						regs.add(r);
					}
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("ArmadoRutasEspeciales", "M11",
						"Surgió un error al obtener los registros validos",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return regs;
	}

	public Boolean guardarRuta(int idUsuario, int idRutaEspecial, String folio,
			String campania, String ordenes, String cajas) {

		connection = AccesoBD.AbrirConexionOTS();
		boolean res = true;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesArmadoGuardarRuta(?,?,?,?,?,?)}");
				callableStatement.setObject("P_IDRUTAESPECIAL", idRutaEspecial,
						Types.INTEGER);
				callableStatement.setObject("P_FOLIO", folio, Types.VARCHAR);
				callableStatement.setObject("P_CAMPANIA", campania,
						Types.VARCHAR);
				callableStatement.setObject("P_IDORDENES", ordenes,
						Types.VARCHAR);
				callableStatement.setObject("P_IDCAJAS", cajas, Types.VARCHAR);
				callableStatement.setObject("P_IDUSUARIO", idUsuario,
						Types.INTEGER);

				AccesoBD.execute(callableStatement);

			} catch (SQLException ex) {
				res = false;
				Utils.GuardarLogMensajeBD("ArmadoRutasEspeciales", "M11",
						"Surgió un error al guardar la ruta", ex.getMessage(),
						idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return res;
	}

	public Boolean guardarRutaUnitarios(int idUsuario, int idRutaEspecial,
			String codigoFSC, String codigoFSC_EAN13, int cantidad) {

		connection = AccesoBD.AbrirConexionOTS();
		boolean res = true;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesArmadoGuardarRutaPU(?,?,?,?,?)}");
				callableStatement.setObject("P_IDRUTAESPECIAL", idRutaEspecial,
						Types.INTEGER);
				callableStatement.setObject("P_CODIGO_FSC", codigoFSC,
						Types.VARCHAR);
				callableStatement.setObject("P_CODIGO_FSC_EAN13_PU",
						codigoFSC_EAN13, Types.VARCHAR);
				callableStatement.setObject("P_CANTIDAD", cantidad,
						Types.VARCHAR);
				callableStatement.setObject("P_IDUSUARIO", idUsuario,
						Types.INTEGER);

				AccesoBD.execute(callableStatement);

			} catch (SQLException ex) {
				res = false;
				Utils.GuardarLogMensajeBD(
						"ArmadoRutasEspeciales",
						"M11",
						"Surgió un error al guardar la los unitarios de la ruta",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return res;
	}

	public Campania[] getCampanias(String tipoCasoUso, Integer idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<Campania> campanias = new ArrayList<Campania>();
		CallableStatement cs = null;

		if (connection != null) {
			try {
				cs = connection.prepareCall("{call SP_PW_CAMPANIA(?,?,?,?)}");
				cs.setObject("P_TIPO_CU", tipoCasoUso, Types.VARCHAR);
				cs.setObject("P_ID_ZONA", 0, Types.INTEGER);
				cs.setObject("P_ID_LDC", 0, Types.INTEGER);
				cs.setObject("P_ID_RUTA", 0, Types.INTEGER);

				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {

					Campania campania = new Campania();
					campania.setDescZona(resultSet.getString("CAMPANIA"));
					campanias.add(campania);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M1",
						"Error al seleccionar campañas existentes",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(connection);
			}
		}
		return (Campania[]) campanias.toArray(new Campania[campanias.size()]);
	}

	public List<ModelRutaEspecial> consultarRutasEspeciales(int idUsuario,
			String claveRuta, String campania) {
		List<ModelRutaEspecial> regs = new ArrayList<ModelRutaEspecial>();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesConsultaRutas(?,?)}");
				callableStatement.setObject("CLAVE_RUTA_ESPECIAL", claveRuta,
						Types.VARCHAR);
				callableStatement
						.setObject("CAMPANIA", campania, Types.VARCHAR);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				while (rs.next()) {
					ModelRutaEspecial r = new ModelRutaEspecial();
					r.setClaveOrden(rs.getString("CLAVE_RUTA_ESPECIAL"));
					r.setFechaCreacion(rs.getDate("FECHA_CREACION"));
					r.setCampania(rs.getString("CAMPANIA"));
					r.setNumOrdenes(rs.getInt("NUM_ORDENES"));
					r.setIdRutaEspecial(rs.getInt("ID_RUTA_ESPECIAL"));
					r.setFechaCreacionS(df.format(r.getFechaCreacion()));
					regs.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("ArmadoRutasEspeciales", "M11",
						"Surgió un error al obtener las rutas especiales",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return regs;
	}

	public String eliminarRuta(int idUsuario, int idRuta) {

		connection = AccesoBD.AbrirConexionOTS();
		String resultado = "";
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesEliminarRuta(?,?)}");
				callableStatement.setObject("ID_RUTA_ESPECIAL", idRuta,
						Types.INTEGER);
				callableStatement.setObject("ID_USUARIO", idUsuario,
						Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				if (rs.next()) {
					resultado = rs.getString("RESULTADO");
				}

				rs.close();

			} catch (SQLException ex) {
				resultado = ex.getMessage();
				Utils.GuardarLogMensajeBD("ArmadoRutasEspeciales", "M11",
						"Surgió un error al obtener las rutas especiales",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return resultado;
	}

	public List<ModelRutasEspecialesItems> consultarRutasEspecialesDetalle(
			int idRutaEspecial, int idUsuario) {
		List<ModelRutasEspecialesItems> regs = new ArrayList<ModelRutasEspecialesItems>();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesConsultaRutaEspecialDetalleCajas(?)}");
				callableStatement.setObject("ID_RUTA_ESPECIAL", idRutaEspecial,
						Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				while (rs.next()) {
					ModelRutasEspecialesItems r = new ModelRutasEspecialesItems();

					r.setRegistro(rs.getString("REGISTRO"));
					r.setNombre(rs.getString("NOMBRE"));
					r.setClaveOrden(rs.getString("CLAVE_ORDEN"));
					r.setCodigoBarras(rs.getString("CODIGO_BARRAS"));
					r.setCodigo_FSC(rs.getString("CODIGO_FSC"));
					r.setCodigo_FSC_EAN13(rs.getString("CODIGO_FSC_EAN13"));
					r.setDescripcion(rs.getString("DESCRIPCION"));
					r.setIdTipoOrigen(rs.getInt("ID_TIPO_ITEM_ORIGEN"));
					String b = rs.getString("BLOCKED_STATUS");
					if (b == null || "0".equals(b)) {
						r.setTipo("0");
					} else if ("-99".equals(b)) {
						r.setTipo("N");
					} else if ("1".equals(b)) {
						r.setTipo("1");
					} else if ("2".equals(b)) {
						r.setTipo("2");
					}
					regs.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ArmadoRutasEspeciales",
						"M11",
						"Surgió un error al obtener el detalle de las cajas de la ruta especial",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return regs;
	}

	public List<ModelRutasEspecialesItems> consultarRutasEspecialesDetalleUnitarios(
			int idRutaEspecial, int idUsuario) {
		List<ModelRutasEspecialesItems> regs = new ArrayList<ModelRutasEspecialesItems>();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesConsultaRutaEspecialDetalleUnitarios(?)}");
				callableStatement.setObject("ID_RUTA_ESPECIAL", idRutaEspecial,
						Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				while (rs.next()) {
					ModelRutasEspecialesItems r = new ModelRutasEspecialesItems();

					r.setCodigo_FSC(rs.getString("CODIGO_FSC"));
					r.setCodigo_FSC_EAN13(rs.getString("CODIGO_FSC_EAN13"));
					r.setDescripcion(rs.getString("DESCRIPCION"));
					r.setCantidadAsignada(rs.getInt("CANTIDAD_ASIGNADO"));
					r.setCantidadFacturada(rs.getInt("FACTURADA"));
					r.setRegistro(rs.getString("REGISTRO"));
					r.setClaveOrden(rs.getString("CLAVE_ORDEN"));

					regs.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ArmadoRutasEspeciales",
						"M11",
						"Surgió un error al obtener el detalle de unitarios de la ruta especial",
						ex.getMessage(), idUsuario);

			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return regs;
	}

}
