package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.ReporteConsultaBuenVecinoModel;

public class ReporteConsultaBuenVecinoController {

	private CallableStatement callableStatement;
	private Connection connection;

	public ReporteConsultaBuenVecinoController() {

	}

	public List<ReporteConsultaBuenVecinoModel> obtenerConsultaBuenVecino(
			int idUsuario, int zona, int ruta, String registro) {
		List<ReporteConsultaBuenVecinoModel> registros = new ArrayList<ReporteConsultaBuenVecinoModel>();
		connection = AccesoBD.AbrirConexionOTS();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteRepresentantesBuenVecino(?,?,?)}");
				callableStatement.setObject("p_zona", zona, Types.INTEGER);
				callableStatement.setObject("p_ruta", ruta, Types.INTEGER);
				callableStatement.setObject("p_registro", registro,
						Types.VARCHAR);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ReporteConsultaBuenVecinoModel r = new ReporteConsultaBuenVecinoModel();
					r.setClaveRuta(rs.getString("CLAVE_RUTA"));
					r.setDomicilio(rs.getString("domicilio"));
					r.setDomicilioAlterno(rs.getString("domicilio_alterno"));
					r.setFechaModificacionDomAlterno(rs
							.getDate("fechamodificaciondomicilioalterno"));
					r.setIdRuta(rs.getInt("id_ruta"));
					r.setIdZona(rs.getInt("id_zona"));
					r.setNombre(rs.getString("nombre"));
					r.setRegistro(rs.getString("registro"));
					if (rs.getDate("fechamodificaciondomicilioalterno") != null) {
						r.setFechaModificacionDomAlternoS(df.format(rs
								.getDate("fechamodificaciondomicilioalterno")));
					} else {
						r.setFechaModificacionDomAlternoS("");
					}
					if (rs.getString("entregarendomicilioalterno") == null
							|| rs.getString("entregarendomicilioalterno")
									.equals("0")) {
						r.setEntregaEnDomicilioalterno("No Vigente");
					} else {
						r.setEntregaEnDomicilioalterno("Vigente");
					}
					r.setCampaniaCambio(rs.getString("campaniaCambio"));
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
