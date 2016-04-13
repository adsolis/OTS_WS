/**
 *
 *  @since 27/12/2011
 *
 */
package com.datacode.avon_ots_ws;

import com.datacode.avon_ots_ws.model.Campania;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jessica.leon
 * @since 27/12/2011
 * 
 */
public class CampaniaController {

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	String query;
	Connection connection;

	public CampaniaController() {

		this.preparedStatement = null;
		this.resultSet = null;
		this.query = null;
		this.connection = null;
	}

	public Campania[] getCampanias(String tipoCasoUso, Integer idLDC,
			Integer idZona, Integer idRuta, Integer idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<Campania> campanias = new ArrayList<Campania>();
		CallableStatement cs = null;

		if (connection != null) {
			try {
				cs = connection.prepareCall("{call SP_PW_CAMPANIA(?,?,?,?)}");
				cs.setObject("P_TIPO_CU", tipoCasoUso, Types.VARCHAR);
				cs.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_ID_RUTA", idRuta, Types.INTEGER);

				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {

					Campania campania = new Campania();
					campania.setIdCampania(resultSet.getInt("ID_CAMPANIA"));
					campania.setAnioCampania(resultSet.getInt("ANIO_CAMPANIA"));
					campania.setCampania(resultSet.getInt("CAMPANIA"));
					campania.setFechaInicio(resultSet.getString("FECHA_INICIA"));
					campania.setFechaFin(resultSet.getString("FECHA_FIN"));
					campania.setLastUpdTs(resultSet.getString("LASTUPD_TS"));
					campania.setFechaActualizado(resultSet
							.getString("FECHA_ACTUALIZADO"));
					campania.setUsuarioActualiza(resultSet
							.getString("USUARIO_ACTUALIZA"));
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

	public Campania[] getUltimasCampaniasSinID(String tipoCasoUso,
			Integer idUsuario) {

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

					campania.setUsuarioActualiza(resultSet
							.getString("campania"));

					campanias.add(campania);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M1",
						"Error al seleccionar ultimas campañas existentes",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(connection);
			}
		}
		return (Campania[]) campanias.toArray(new Campania[campanias.size()]);
	}
}
