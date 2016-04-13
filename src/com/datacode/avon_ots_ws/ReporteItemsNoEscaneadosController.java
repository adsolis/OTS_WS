package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.ModelRepItemsNoEscaneados;
import com.datacode.avon_ots_ws.model.ModelReporteRecoleccionRemitos;

public class ReporteItemsNoEscaneadosController {

	private CallableStatement callableStatement;
	private Connection connection;

	public ReporteItemsNoEscaneadosController() {

	}

	public List<ModelRepItemsNoEscaneados> obtenerConsultaItemsNoEscaneados (
			int idUsuario, int zona, int campania) {
		List<ModelRepItemsNoEscaneados> registros = new ArrayList<ModelRepItemsNoEscaneados>();
		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteItemsNoEscaneados(?,?)}");
				callableStatement.setObject("p_zona", zona, Types.INTEGER);
				callableStatement.setObject("p_campania", campania,
						Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelRepItemsNoEscaneados r = new ModelRepItemsNoEscaneados();
					r.setRuta(rs.getString("RUTA"));
					r.setNombreChofer(rs.getString("NOMBRE_CHOFER"));
					r.setRegistro(rs.getString("REGISTRO"));
					r.setCodItem(rs.getString("CODIGO_BARRAS"));
					r.setDescrItem(rs.getString("DESCRIPCION"));
					r.setCampania(rs.getString("CAMPANIA"));
					r.setZona(rs.getString("ZONA"));
					r.setClaveOrden(rs.getString("CLAVE_ORDEN"));
					r.setFsc(rs.getString("CODIGO_FSC"));
					r.setEan13(rs.getString("CODIGO_FSC_EAN13"));
					registros.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"obtenerConsultaItemsNoEscaneados",
						"M11",
						"Surgió un error al obtener los datos del reporte items no escaneados",
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
