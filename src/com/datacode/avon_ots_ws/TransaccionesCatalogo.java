/**
 *
 *  @since 13/01/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.Catalogo;

/**
 * @author jessica.leon
 * @since 13/01/2012
 * 
 */
public class TransaccionesCatalogo {

	private Catalogo catalogo;

	public TransaccionesCatalogo() {

	}

	public Catalogo consultarParametrosCarga(int paramCarga) {

		Connection connection = ConexionExterna.crearConexion();
		CallableStatement cs = null;
		ResultSet resultSetParamCargas = null;

		if (connection != null) {
			try {
				cs = connection.prepareCall("{call SP_PW_PARAM_CARGAS(?,?)}");
				cs.setObject("P_ID_CARGA", paramCarga, Types.INTEGER);
				cs.setObject("P_DESCRIPCION", null, Types.VARCHAR);
				resultSetParamCargas = AccesoBD.executeRetrieveResultSet(cs);
				setPropiedadesCatalogo(resultSetParamCargas);
				resultSetParamCargas.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ADMIN",
						"ADMIN_1.01",
						"Surgio un error al seleccionar datos en la tabla PW_PARAM_CARGAS",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return catalogo;
	}

	public Catalogo consultarParametrosCargaPorDescripcion(String descripcion) {

		Connection connection = ConexionExterna.crearConexion();
		CallableStatement cs = null;
		ResultSet resultSetParamCargas = null;

		if (connection != null) {
			try {
				cs = connection.prepareCall("{call SP_PW_PARAM_CARGAS(?,?)}");
				cs.setObject("P_ID_CARGA", 0, Types.INTEGER);
				cs.setObject("P_DESCRIPCION", descripcion, Types.VARCHAR);
				resultSetParamCargas = AccesoBD.executeRetrieveResultSet(cs);
				setPropiedadesCatalogo(resultSetParamCargas);
				resultSetParamCargas.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ADMIN",
						"ADMIN_1.01",
						"Surgio un error al seleccionar datos en la tabla PW_PARAM_CARGAS",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return catalogo;
	}

	public void setPropiedadesCatalogo(ResultSet resultSetParamCargas)
			throws SQLException {

		while (resultSetParamCargas.next()) {
			catalogo = new Catalogo();
			catalogo.setIdCarga(resultSetParamCargas.getInt("ID_CARGA"));
			catalogo.setDescripcion(resultSetParamCargas
					.getString("DESCRIPCION"));
			catalogo.setComentarios(resultSetParamCargas
					.getString("COMENTARIOS"));
			catalogo.setCadenaConexOrigen(resultSetParamCargas
					.getString("CADENA_CONEX_ORIGEN"));
			catalogo.setCadenaConexDestino(resultSetParamCargas
					.getString("CADENA_CONEX_DESTINO"));
			catalogo.setTipoOrigen(resultSetParamCargas.getInt("ORIGEN"));
			catalogo.setNombreOrigen(resultSetParamCargas
					.getString("NOMBRE_ORIGEN"));
			catalogo.setRutaOrigen(resultSetParamCargas
					.getString("RUTA_ORIGEN"));
			catalogo.setNombreDestino(resultSetParamCargas
					.getString("NOMBRE_DESTINO"));
			catalogo.setLastUpdTs(resultSetParamCargas.getString("LASTUPD_TS"));
			catalogo.setFechaEjecucion(resultSetParamCargas
					.getString("FECHA_EJECUCION"));
			catalogo.setEstatusEjecucion(resultSetParamCargas
					.getString("ESTATUS_EJECUCION"));
			catalogo.setLogUltimaEjecucion(resultSetParamCargas
					.getString("LOG_ULT_EJECUCION"));
			catalogo.setNombreStoreProcedure(resultSetParamCargas
					.getString("NOMBRE_SP"));
		}
	}

	public Connection conectarBDExterna(String cadenaConexion,
			String descripcion) {

		Connection conexion = ConexionExterna.abrirConexion(cadenaConexion);

		return conexion;
	}

	public ResultSet ejecutarConsultaBDExterna(Connection conexion) {

		PreparedStatement ps = null;
		String query = null;
		ResultSet resultSetOrigen = null;

		try {
			query = "SELECT * FROM " + catalogo.getNombreOrigen()
					+ " WHERE LASTUPD_TS > CONVERT(datetime,'"
					+ catalogo.getLastUpdTs() + "',121)"
					+ " ORDER BY LASTUPD_TS ASC";
			ps = conexion.prepareStatement(query);
			resultSetOrigen = ps.executeQuery();

			return resultSetOrigen;

		} catch (SQLException e) {
			Utils.GuardarLogMensajeBD("ADMIN", "ADMIN_1.01",
					"Surgio un error al seleccionar datos en la tabla externa "
							+ catalogo.getNombreOrigen(), e.getMessage(), 1);
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	public void ejecutarInsertTablaDestino(List<String> sqlInserts,
			String cadenaConexionDestino, String nombreDestino) {

		Connection conexion = null;
		CallableStatement cs = null;
		conexion = ConexionExterna.abrirConexion(cadenaConexionDestino);

		if (conexion != null) {
			try {
				eliminarRegistrosTablaTemporal(nombreDestino);
				for (String sqlInsert : sqlInserts) {
					cs = conexion
							.prepareCall("{call SP_ActualizarTablaTemporal(?)}");
					cs.setObject("P_INSERT", sqlInsert, Types.VARCHAR);
					cs.execute();
				}
				AccesoBD.CerrarConexion(conexion);
			} catch (SQLException e) {
				Utils.GuardarLogMensajeBD("ADMIN", "ADMIN",
						"Surgio un error al insertar en la tabla "
								+ nombreDestino, e.getMessage(), 1);
				e.printStackTrace();
			} finally {

			}
		}
	}

	public void eliminarRegistrosTablaTemporal(String nombreTabla) {

		Connection connection = ConexionExterna.crearConexion();
		CallableStatement cs = null;

		if (connection != null) {
			try {
				cs = connection
						.prepareCall("{call SP_EliminarRegistrosTablaTemporal(?)}");
				cs.setObject("P_NOMBRE_TABLA", nombreTabla, Types.VARCHAR);
				cs.execute();
			} catch (SQLException ex) {

				Utils.GuardarLogMensajeBD("ADMIN", "ADMIN",
						"Error al ejecutar SP_EliminarRegistrosTablaTemporal",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}
	}

	public void ejecutarStoreProcedure(String nombreStoreProcedure,
			String lastUpdTs, int idCarga, int idLDC, int idUser) {

		Connection connection = ConexionExterna.crearConexion();

		CallableStatement cs = null;
		String logUltimaEjecucion = null;

		String cadenaStore = "{call " + nombreStoreProcedure + "(?,?,?,?)}";

		if (connection != null) {
			try {
				cs = connection.prepareCall(cadenaStore);
				cs.setObject("P_ID_CATALOGO", idCarga, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_ID_USUARIO", idUser, Types.INTEGER);
				cs.registerOutParameter("p_Ret", java.sql.Types.VARCHAR);
				cs.execute();
				logUltimaEjecucion = cs.getString("p_Ret");

				if (logUltimaEjecucion == null) {
					logUltimaEjecucion = "Carga ejecutada con éxito";
				}
				
				cerrarCarga(0, logUltimaEjecucion, lastUpdTs);

			} catch (SQLException ex) {
				cerrarCarga(
						0,
						"Error al ejecutar store procedure. No se realizó la carga completamente.",
						lastUpdTs);
				Utils.GuardarLogMensajeBD("ADMIN", "ADMIN",
						"Error al ejecutar " + nombreStoreProcedure,
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}
	}

	public void cerrarCarga(int estatus, String mensaje, String lastUpdTs) {

		Connection connection = ConexionExterna.crearConexion();
		CallableStatement cs = null;

		if (connection != null) {
			try {
				cs = connection.prepareCall("{call SP_Cerrar_Carga(?,?,?,?)}");
				cs.setObject("P_ID_CARGA", catalogo.getIdCarga(), Types.INTEGER);
				cs.setObject("P_LASTUPD_TS", lastUpdTs, Types.VARCHAR);
				cs.setObject("P_ESTATUS", estatus, Types.INTEGER);
				cs.setObject("P_MENSAJE_TEXTO", mensaje, Types.VARCHAR);
				cs.execute();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("ADMIN", "ADMIN",
						"Error al ejecutar SP_Cerrar_Carga", ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}
	}

	public void ejecutarProcedimientoInsercion(String nombreDestino,
			String rutaOrigen, String nombreArchivo) {

		Connection connection = ConexionExterna.crearConexion();
		CallableStatement cs = null;
		String fecha = Utils
				.ObtenerFechaActual(Utils.formatoFechaCortaHoraLarga);

		if (connection != null) {
			try {
				cs = connection
						.prepareCall("{call SP_InsertarTablaTemporal(?,?,?)}");
				cs.setObject("p_ruta_origen", rutaOrigen, Types.VARCHAR);
				cs.setObject("p_nombre_origen", nombreArchivo, Types.VARCHAR);
				cs.setObject("p_tabla_destino_tmp", nombreDestino,
						Types.VARCHAR);
				cs.execute();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("ADMIN", "ADMIN",
						"Error al ejecutar SP_InsertarTablaTemporal",
						ex.getMessage(), 1);
				cerrarCarga(0, "Error al insertar datos en la tabla "+ nombreDestino + " desde el archivo " + nombreArchivo +". " +
								"Revisar log de transacciones para ver detalles del error", fecha);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}
	}

	public int consultarCatalogosActivos() {
		int catalogosActivos = 0;

		Connection connection = ConexionExterna.crearConexion();
		CallableStatement cs = null;
		ResultSet resultSet = null;

		if (connection != null) {
			try {
				cs = connection
						.prepareCall("{call SP_ConsultarCatalogosActivos()}");
				resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					catalogosActivos = resultSet.getInt("ACTUALIZACIONES");
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ADMIN",
						"ADMIN001.01",
						"Surgio un error al ejecutar SP_COnsultarCatalogosActivos",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}

		return catalogosActivos;
	}

	public List<Catalogo> consultarEstatusCatalogos() {

		List<Catalogo> catalogos = new ArrayList<Catalogo>();
		Connection connection = ConexionExterna.crearConexion();
		CallableStatement cs = null;
		ResultSet resultSetParamCargas = null;

		if (connection != null) {
			try {
				cs = connection
						.prepareCall("{call SP_ConsultarEstatusActualizacionCatalogos()}");
				resultSetParamCargas = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSetParamCargas.next()) {
					Catalogo catalogo = new Catalogo();
					catalogo.setDescripcion(resultSetParamCargas
							.getString("DESCRIPCION"));
					catalogo.setLastUpdTs(resultSetParamCargas
							.getString("LASTUPD_TS"));
					catalogo.setFechaEjecucion(resultSetParamCargas
							.getString("FECHA_EJECUCION"));
					catalogo.setEstatusEjecucion(resultSetParamCargas
							.getString("ESTATUS_EJECUCION"));
					catalogo.setLogUltimaEjecucion(resultSetParamCargas
							.getString("LOG_ULT_EJECUCION"));
					catalogos.add(catalogo);
				}

				resultSetParamCargas.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ADMIN",
						"ADMIN_1.01",
						"Surgio un error al seleccionar datos en la tabla PW_PARAM_CARGAS",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return catalogos;
	}
}
