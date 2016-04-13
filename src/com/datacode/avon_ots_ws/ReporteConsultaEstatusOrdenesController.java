package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_ws.model.ModelReporteConsultaEstatus;

public class ReporteConsultaEstatusOrdenesController {

	private CallableStatement callableStatement;
	private Connection connection;

	public ReporteConsultaEstatusOrdenesController() {

	}

	public List<ModelReporteConsultaEstatus> obtenerConsultaEstatusOrdenes(
			int idUsuario, int anio, int mes, String registro) {
		List<ModelReporteConsultaEstatus> registros = new ArrayList<ModelReporteConsultaEstatus>();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteEstatusOrdenesporRepresentante(?,?,?)}");
				callableStatement.setObject("p_anio", anio, Types.INTEGER);
				callableStatement.setObject("p_mes", mes, Types.INTEGER);
				callableStatement.setObject("p_registro", registro,
						Types.VARCHAR);
				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				SimpleDateFormat df = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm:ss");

				while (rs.next()) {
					ModelReporteConsultaEstatus r = new ModelReporteConsultaEstatus();
					r.setCodigoBarras(rs.getString("codigo_barras"));
					r.setEstatusItem(rs.getString("descestatusitem"));
					r.setFechaEstatus(df.format(rs.getDate("fecha_estatus")));
					Timestamp tiempo = rs.getTimestamp("fecha_estatus");
					r.setFechaEstatus(df.format(new Date(tiempo.getTime())));
					r.setIdOrden(rs.getString("id_orden"));
					r.setItem(rs.getString("descitem"));
					r.setNombre(rs.getString("nombre"));
					r.setPagos(rs.getString("pagos"));
					r.setPosiciones(rs.getString("posiciones"));
					r.setTipoItem(rs.getString("desctipoitem"));
					r.setUltimoEstatus(rs.getString("ultimo_estatus"));
					r.setUsuario(rs.getString("usuario_actualiza"));
					r.setZona(rs.getString("zona"));
					r.setMonto(rs.getDouble("monto"));
					r.setUltimoEstatusNum(rs.getString("ultimo_id_estatus"));
					r.setClave_orden(rs.getString("clave_orden"));
					r.setHhadmin(rs.getString("hhadmin"));
					if (dividirReg(r.getPosiciones())) {
						// vamos por la foto
						CallableStatement cs1 = null;
						cs1 = connection
								.prepareCall("{call SP_ReporteEstatusOrdenesporRepresentanteObtenerFoto(?)}");
						cs1.setObject("p_idOrden", r.getIdOrden(),
								Types.INTEGER);
						ResultSet rs1 = AccesoBD.executeRetrieveResultSet(cs1);
						while (rs1.next()) {

							r.setFoto(rs1.getBytes("foto"));
						}

					}
					registros.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesConsultaEstatusOrdenes",
						"M11",
						"Surgió un error al obtener los datos del reporte Consulta estatus Ordenes",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return registros;
	}

	private Boolean dividirReg(String valores) {
		String[] datos = valores.split("\\|");
		Boolean res = false;
		if (datos != null && datos.length == 7) {
			if (datos[5].equals("SI")) {
				res = true;
			}

		}

		return res;
	}

}
