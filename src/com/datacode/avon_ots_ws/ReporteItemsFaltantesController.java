package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.ModelReporteItemsFaltantesDetalleCajas;
import com.datacode.avon_ots_ws.model.ModelReporteItemsFaltantesDetalleUnitarios;
import com.datacode.avon_ots_ws.model.ModelReporteItemsFaltantesTotales;
import com.datacode.avon_ots_ws.model.ModelReporteRecoleccionRemitos;
import com.datacode.avon_ots_ws.model.ReporteConsultaBuenVecinoModel;

public class ReporteItemsFaltantesController {

	private CallableStatement callableStatement;
	private Connection connection;

	public ReporteItemsFaltantesController() {

	}

	public ModelReporteItemsFaltantesTotales obtenerTotales(int idUsuario,
			int zona, int campania) {
		ModelReporteItemsFaltantesTotales res = new ModelReporteItemsFaltantesTotales();
		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteItemsFaltantesTotales(?,?)}");
				callableStatement.setObject("p_idZona", zona, Types.INTEGER);
				callableStatement.setObject("p_idCampania", campania,
						Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					res.setCajasFacturadas(rs.getInt("CAJASFACTURADAS"));
					res.setCajasFaltantes(rs.getInt("CAJASPENDIENTES"));
					res.setPremiosFacturados(rs.getInt("PREMIOSFACTURADOS"));
					res.setPremiosFaltantes(rs.getInt("PREMIOSPENDIENTES"));
					res.setUnitariosFacturados(rs.getInt("UNITARIOSFACTURADOS"));
					res.setUnitariosFaltantes(rs.getInt("UNITARIOSPENDIENTES"));
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"obtenerConsultaRecoleccionRemitos",
						"M11",
						"Surgió un error al obtener los datos del reporte recoleccion remitos",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return res;
	}

	public List<ModelReporteItemsFaltantesDetalleUnitarios> obtenerDetalleUnitarios(
			int idUsuario, int zona, int campania) {
		List<ModelReporteItemsFaltantesDetalleUnitarios> res = new ArrayList<ModelReporteItemsFaltantesDetalleUnitarios>();
		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteItemsFaltantesDetalleUnitarios(?,?)}");
				callableStatement.setObject("p_idZona", zona, Types.INTEGER);
				callableStatement.setObject("p_idCampania", campania,
						Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelReporteItemsFaltantesDetalleUnitarios r = new ModelReporteItemsFaltantesDetalleUnitarios();
					r.setCantidadCajas(rs.getInt("CANTIDAD_CAJAS"));
					r.setFaltantes(rs.getInt("FALTANTES"));
					r.setClaveOrden(rs.getString("CLAVE_ORDEN"));
					r.setCodigoEAN13(rs.getString("CODIGO_FSC_EAN13"));
					r.setCodigoFSC(rs.getString("CODIGO_FSC"));
					r.setDescripcion(rs.getString("DESCRIPCION"));
					r.setTipoItem(rs.getString("TIPO_ITEM"));
					res.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"obtenerConsultaRecoleccionRemitos",
						"M11",
						"Surgió un error al obtener los datos del reporte recoleccion remitos",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return res;
	}

	public List<ModelReporteItemsFaltantesDetalleCajas> obtenerDetalleCajas(
			int idUsuario, int zona, int campania) {
		List<ModelReporteItemsFaltantesDetalleCajas> res = new ArrayList<ModelReporteItemsFaltantesDetalleCajas>();
		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteItemsFaltantesDetalleCajas(?,?)}");
				callableStatement.setObject("p_idZona", zona, Types.INTEGER);
				callableStatement.setObject("p_idCampania", campania,
						Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelReporteItemsFaltantesDetalleCajas r = new ModelReporteItemsFaltantesDetalleCajas();
					r.setCantidadCajas(rs.getInt("CANTIDAD_CAJAS"));
					r.setFaltantes(rs.getInt("FALTANTES"));
					r.setRuta(rs.getString("CLAVE_RUTA"));
					r.setRegistro(rs.getString("REGISTRO"));
					r.setNombre(rs.getString("NOMBRE"));
					r.setOrden(rs.getString("CLAVE_ORDEN"));
					r.setCodigoBarras(rs.getString("CODIGO_BARRAS"));
					r.setEstatus(rs.getString("ID_ESTATUS"));
					res.add(r);

				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"obtenerConsultaRecoleccionRemitos",
						"M11",
						"Surgió un error al obtener los datos del reporte recoleccion remitos",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return res;
	}

}
