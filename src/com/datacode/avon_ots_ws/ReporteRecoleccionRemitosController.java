package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.ModelReporteRecoleccionRemitos;
import com.datacode.avon_ots_ws.model.ReporteConsultaBuenVecinoModel;

public class ReporteRecoleccionRemitosController {

	private CallableStatement callableStatement;
	private Connection connection;

	public ReporteRecoleccionRemitosController() {

	}

	public List<ModelReporteRecoleccionRemitos> obtenerConsultaRecoleccionRemitos(
			int idUsuario, int zona, int ruta, int campania) {
		List<ModelReporteRecoleccionRemitos> registros = new ArrayList<ModelReporteRecoleccionRemitos>();
		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteRecoleccionDeRemitos(?,?,?)}");
				callableStatement.setObject("p_zona", zona, Types.INTEGER);
				callableStatement.setObject("p_ruta", ruta, Types.INTEGER);
				callableStatement.setObject("p_campania", campania,
						Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelReporteRecoleccionRemitos r = new ModelReporteRecoleccionRemitos();
					r.setCampania(rs.getString("CAMPAIGN"));
					r.setEstatus(rs.getString("ESTATUS"));
					if (r.getEstatus() == null) {
						r.setEstatus("");
					}
					r.setFecha(rs.getString("FECHA_RECOLECCION_REMITO"));
					r.setRecibido(rs.getString("RECIBIDA"));
					r.setRecolectar(rs.getString("ARECOLECTAR"));
					r.setRegistro(rs.getString("REGISTRO"));
					r.setRuta(rs.getString("CLAVE_RUTA"));
					r.setZona(rs.getString("ZONE"));
					r.setComentarios(rs.getString("COMENTARIOS"));
					if(r.getComentarios()==null)
					{
						r.setComentarios("");
					}
					registros.add(r);
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

		return registros;
	}

}
