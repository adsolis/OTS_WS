package com.datacode.avon_ots_ws;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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
import com.datacode.avon_ots_ws.model.ModelRutaEspecialRemitos;
import com.datacode.avon_ots_ws.model.ModelRutasEspecialesItems;

import com.datacode.avon_ots_ws.model.ModelRutaEspecialRemitos;

public class ArmadoRutasRemitosController {
	
	private CallableStatement callableStatement;
	private Connection connection;

	public ArmadoRutasRemitosController() {

	}

	
	/**
	 * Metodo para obtener los remitos en base a la busqueda del registro en la
	 * generacion de la ruta especial
	 * @param idUsuario
	 * @param registro
	 * @return Lista de ModelRutaEspecialRemito
	 */
	public List<ModelRutaEspecialRemitos> obtieneRemitos(int idUsuario,
			String registro, String orden) {
		List<ModelRutaEspecialRemitos> regs = new ArrayList<ModelRutaEspecialRemitos>();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RutasEspecialesArmadoObtenerRemitos(?,?)}");
				callableStatement.setObject("P_REGISTROS", registro,
						Types.VARCHAR);
				callableStatement.setObject("P_ORDENES", Integer.parseInt(orden), Types.INTEGER);

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelRutaEspecialRemitos r = new ModelRutaEspecialRemitos();
					r.setNombre(rs.getString("NOMBRE"));
					r.setRegistro(rs.getString("REGISTRO"));
					r.setIdRemito(rs.getLong("ID_REMITO"));
					r.setCantidadRecolectar(rs.getInt("QUANTITY_TO_COLLECT"));
					regs.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("ArmadoRutasEspecialesRemitos", "M11",
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
	
	public List<ModelRutaEspecialRemitos> actualizarRamitos(int idUsuario, String tipo, String codigo,
			                                                long idRemito) {
		
		List<ModelRutaEspecialRemitos> regs = new ArrayList<ModelRutaEspecialRemitos>();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RegistrarRemitoValidadoAcusado(?,?,?,?)}");
				
				callableStatement.setObject("P_TIPO", tipo, Types.VARCHAR);
				callableStatement.setObject("P_CODIGO", codigo, Types.VARCHAR);
				callableStatement.setObject("P_ID_REMITO", idRemito, Types.BIGINT);
				callableStatement.setObject("P_ID_USUARIO", idUsuario, Types.INTEGER);
				

				ResultSet rs = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (rs.next()) {
					ModelRutaEspecialRemitos r = new ModelRutaEspecialRemitos();
					r.setNombre(rs.getString("NOMBRE"));
					r.setRegistro(rs.getString("REGISTRO"));
					r.setIdRemito(rs.getLong("ID_REMITO"));
					r.setCantidadRecolectar(rs.getInt("QUANTITY_TO_COLLECT"));
					regs.add(r);
				}

				rs.close();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("ArmadoRutasEspecialesRemitos", "M11",
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

}
