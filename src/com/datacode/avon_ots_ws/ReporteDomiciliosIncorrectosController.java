package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_ws.model.ModelReporteDomiciliosIncorrectos;

public class ReporteDomiciliosIncorrectosController {

	private CallableStatement callableStatement;
	private Connection connection;

	public ReporteDomiciliosIncorrectosController() {

	}

	public List<ModelReporteDomiciliosIncorrectos> obtenerRepresentantesDomiciliosIncorrectos(
			int idUsuario) {
		List<ModelReporteDomiciliosIncorrectos> registros = new ArrayList<ModelReporteDomiciliosIncorrectos>();
		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteObtenerRepresentantesDomiciliosIncorrectos()}");

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelReporteDomiciliosIncorrectos r = new ModelReporteDomiciliosIncorrectos();
					r.setRegistro(rs.getString("REGISTRO"));
					r.setRuta(rs.getString("CLAVE_RUTA"));
					r.setZona(rs.getString("ZONA"));
					r.setDomicilioActual(rs.getString("DOMICILIO_ACTUAL"));
					r.setDomicilioCorrecto(rs.getString("DOMICILIO_CORRECTO"));
					r.setEmail(rs.getString("EMAIL"));
					r.setNombre(rs.getString("NOMBRE"));

					Timestamp time = rs.getTimestamp("FECHA_REPORTE");
					Date fecha = new Date(time.getTime());
					r.setFechaReporte(fecha);
					SimpleDateFormat df = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm:ss");
					r.setFechaReporteS(df.format(fecha));
					registros.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"obtenerConsultaDomiciliosIncorrectos",
						"M11",
						"Surgió un error al obtener los datos del reporte domicilios Incorrectos",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return registros;
	}

}
