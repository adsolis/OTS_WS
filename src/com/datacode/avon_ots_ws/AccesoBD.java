package com.datacode.avon_ots_ws;

/**
 * Clase de métodos de acceso a la BD
 * @author jorge.torner
 * @since 20-12-2011
 */
import java.sql.*;
import java.util.Properties;

public class AccesoBD {
	private static String archivoConfig = "AvonWsApp.properties";

	/**
	 * Abre una conexión a la base de datos de OTS
	 * 
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @return Connection Una conexión abierta
	 */
	static Connection AbrirConexionOTS() {
		Connection conexion = null;

		// Cargamos el archivo de propiedades para obtener datos de conexión y
		// formar cadena
		AccesoArchivos accesoArch = new AccesoArchivos();
		if (accesoArch.CargarArchivoPropiedades(archivoConfig)) {

			String servidor = accesoArch.ObtenerPropiedad("servidor_OTS");
			// String puerto = accesoArch.ObtenerPropiedad("puerto_OTS");
			String bd = accesoArch.ObtenerPropiedad("bd_OTS");
			String usr = accesoArch.ObtenerPropiedad("usr_OTS");
			String pwd = accesoArch.ObtenerPropiedad("pwd_OTS");

			Properties info = new Properties();
			info.setProperty("user", usr);
			info.setProperty("password", pwd);
			// info.setProperty("language", "Spanish");

			// String cadenaCon = "jdbc:sqlserver://" + servidor +
			// ";databaseName=" + bd + ";language=Spanish";
			String cadenaCon = "jdbc:sqlserver://" + servidor
					+ ";databaseName=" + bd;
			// String cadenaCon = "jdbc:sqlserver://" + servidor + ":" + puerto
			// + ";databaseName=" + bd;
			try {
				// Get connection
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				// connection =
				// DriverManager.getConnection("jdbc:sqlserver://qro-jat\\sqlexpress/ControlCotizaciones:1433",
				// "sa", "datacode");
				// conexion = DriverManager.getConnection(cadenaCon, usr, pwd);
				conexion = DriverManager.getConnection(cadenaCon, info);

				// if (conexion != null) {
				// System.out.println("Successfully connected");
				// // Meta data
				// DatabaseMetaData meta = conexion.getMetaData();
				// System.out.println("\nDriver Information");
				// System.out.println("Driver Name: " + meta.getDriverName());
				// System.out.println("Driver Version: " +
				// meta.getDriverVersion());
				// System.out.println("nDatabase Information ");
				// System.out.println("Database Name: " +
				// meta.getDatabaseProductName());
				// System.out.println("Database Version: " +
				// meta.getDatabaseProductVersion());
				// }
			} catch (ClassNotFoundException ex) {
				AccesoArchivos
						.GuardaArchivoLog("Error al establecer conexión a la base de datos: "
								+ ex.getMessage());
			} catch (SQLException ex) {
				AccesoArchivos
						.GuardaArchivoLog("Error al establecer conexión a la base de datos: "
								+ ex.getMessage());
			}
		}

		return conexion;
	}

	/**
	 * Cierra la conexión
	 * 
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @param con
	 * @return
	 */
	static Connection CerrarConexion(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * Abre una conexión a la base de datos de SOS
	 * 
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @return Connection Una conexión abierta
	 */
	static Connection AbrirConexionSOS() {
		Connection conexion = null;

		// Cargamos el archivo de propiedades para obtener datos de conexión y
		// formar cadena
		AccesoArchivos accesoArch = new AccesoArchivos();
		if (accesoArch.CargarArchivoPropiedades(archivoConfig)) {

			String servidor = accesoArch.ObtenerPropiedad("servidor_SOS");
			// String puerto = accesoArch.ObtenerPropiedad("puerto_SOS");
			String bd = accesoArch.ObtenerPropiedad("bd_SOS");
			String usr = accesoArch.ObtenerPropiedad("usr_SOS");
			String pwd = accesoArch.ObtenerPropiedad("pwd_SOS");

			// String cadenaCon = "jdbc:sqlserver://" + servidor + ":" + puerto
			// + ";databaseName=" + bd;
			String cadenaCon = "jdbc:sqlserver://" + servidor
					+ ";databaseName=" + bd;
			try {
				// Get connection
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				// connection =
				// DriverManager.getConnection("jdbc:sqlserver://qro-jat\\sqlexpress/ControlCotizaciones:1433",
				// "sa", "datacode");
				conexion = DriverManager.getConnection(cadenaCon, usr, pwd);

			} catch (ClassNotFoundException e) {
				System.out
						.println("Error al establecer conexión a la base de datos SOS: "
								+ e.getMessage());
			} catch (SQLException e) {
				System.out
						.println("Error al establecer conexión a la base de datos SOS: "
								+ e.getMessage());
			}
		}

		return conexion;
	}

	/**
	 * Cierra un CallableStatement.
	 * 
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @param cs
	 *            CallableStatement
	 * @return CallableStatement Regresa el CallableStatement cerrado.
	 */
	static CallableStatement CerrarStatement(CallableStatement cs) {
		try {
			if (cs != null) {
				cs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cs;
	}

	/**
	 * Método para obtener el primer resultset de un Stored Procedure ignorando
	 * los Update, Insert o Delete que haya antes de la consulta final del SP.
	 * Se debe tomar en cuenta que el SP devuelva sólo una consulta y sea la que
	 * se quiere obtener en código, pues de haber más, se obtendría la primera
	 * que genera el SP en vez de la última. Éste método sólo se debe usar con
	 * un SP que devuelva una consulta. Para los que no devuelven consultas se
	 * debe usar execute o executeUpdate del mismo objeto CallableStatement.
	 * 
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @param cs
	 *            -Callable statement que invoca al SP con sus parámetros
	 * @return ResultSet Con el resultado de la primer consulta que tenga el SP
	 * @throws SQLException
	 */
	static ResultSet executeRetrieveResultSet(CallableStatement cs)
			throws SQLException {
		if (cs == null) {
			throw new IllegalArgumentException(
					"The callable statement specified was null.");
		}
		// Execute the statement...
		boolean resultSetFound;
		try {
			resultSetFound = cs.execute();
		} catch (Exception e) {
			SQLException sqle = new SQLException(
					"Failed to execute the statement: " + e);
			sqle.initCause(e);
			throw sqle;
		}
		// Retrieve the first result set from the statement...
		try {
			while (!resultSetFound) {
				resultSetFound = cs.getMoreResults();
				// Check if there are no more results...
				if (!resultSetFound && cs.getUpdateCount() == -1) {
					// No more result sets are available. Stop searching...
					break;
				}
				// Else continue looking for a result set...
			}
			if (resultSetFound) {
				ResultSet rs = cs.getResultSet();
				if (rs == null) {
					throw new IllegalStateException(
							"Unexpectedly failed to retrieve a result set from statement.");
				}
				return rs;
			} else {
				throw new SQLException(
						"Scanned all results returned by the statement but did not find a result set.");
			}
		} catch (Exception e) {
			SQLException sqle = new SQLException(
					"Executed the statement but failed to retrieve a result set: "
							+ e);
			sqle.initCause(e);
			throw sqle;
		}
	}

	static boolean execute(CallableStatement cs) throws SQLException {
		if (cs == null) {
			throw new IllegalArgumentException(
					"The callable statement specified was null.");
		}
		// Execute the statement...
		boolean resultSetFound;
		try {
			resultSetFound = cs.execute();
		} catch (Exception e) {
			SQLException sqle = new SQLException(
					"Failed to execute the statement: " + e);
			sqle.initCause(e);
			throw sqle;
		}
		return resultSetFound;
	}
}
