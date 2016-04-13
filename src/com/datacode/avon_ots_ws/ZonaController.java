/**
 *
 *  @since 28/12/2011
 *
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.datacode.avon_ots_ws.model.Zona;

/**
 * @author jessica.leon
 * @since 28/12/2011
 * 
 */
public class ZonaController {

	private Connection connection;

	public ZonaController() {

		this.connection = null;

	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param tipoCasoUso
	 * @param idLDC
	 * @param idUsuario
	 * @return
	 *
	 */
	public Zona[] getZonas(String tipoCasoUso,int idLDC,int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<Zona> zonas = new ArrayList<Zona>();
		CallableStatement cs = null;

		if (connection != null) {
			try {
				cs = connection.prepareCall("{call SP_PW_ZONA(?,?)}");
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_TIPO_CU", tipoCasoUso, Types.VARCHAR);

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				while (rs.next()) {
					Zona zona = new Zona();
					zona.setIdZona(rs.getString("ID_ZONA"));
					zona.setZona(rs.getString("ZONA"));
					zona.setAnioCampaniaActual(rs.getString("ANIO_CAMPANIA_ACTUAL"));
					zona.setCampania(rs.getString("CAMPANIA"));
					zona.setLastUpdTs(rs.getString("LASTUPD_TS"));
					zona.setFechaActualizado(rs.getString("FECHA_ACTUALIZADO"));
					zona.setUsuarioActualiza(rs.getString("USUARIO_ACTUALIZA"));
					zona.setIdLDC(rs.getString("ID_LDC"));
					zona.setIdDivision(rs.getString("ID_DIVISION"));
					zona.setTipoZona(rs.getString("TIPO_ZONA"));
					zona.setNombreGerente(rs.getString("NOMBRE_GERENTE_ZONAL"));
					zonas.add(zona);
				}
				rs.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M2",
						"Error al seleccionar zonas existentes",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return (Zona[]) zonas.toArray(new Zona[zonas.size()]);
	}
}
