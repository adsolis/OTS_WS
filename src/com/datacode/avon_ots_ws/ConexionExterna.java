/**
 *
 *  @since 12/01/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.Zona;

/**
 * @author jessica.leon
 * @since 12/01/2012
 * 
 */
public class ConexionExterna {

	private static Connection conexion = null;
	private static int noIntentosConexion = 3;
	private static String archivoConfig = "AvonWsApp.properties";

	public static Connection crearConexion() {

		AccesoArchivos accesoArch = new AccesoArchivos();
		if (accesoArch.CargarArchivoPropiedades(archivoConfig)) {

			String servidor = accesoArch.ObtenerPropiedad("servidor_AC");
			String bd = accesoArch.ObtenerPropiedad("bd_AC");
			String usr = accesoArch.ObtenerPropiedad("usr_AC");
			String pwd = accesoArch.ObtenerPropiedad("pwd_AC");

			// jdbc:sqlserver://192.168.0.3\sqlexpress;databaseName=porteo_cly;user=sa;password=datacode

			String cadenaCon = "jdbc:sqlserver://" + servidor
					+ ";databaseName=" + bd + ";user=" + usr + ";password="
					+ pwd;

			conexion = abrirConexion(cadenaCon);
		}
		return conexion;
	}

	public static Connection abrirConexion(String cadenaConexion) {

		for (int noConexion = 0; noConexion < noIntentosConexion; noConexion++) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conexion = DriverManager.getConnection(cadenaConexion);
				return conexion;
			} catch (ClassNotFoundException e) {
				System.out
						.println("Error al establecer conexión a la base de datos externa: "
								+ e.getMessage());
			} catch (SQLException e) {
				System.out
						.println("Error al establecer conexión a la base de datos externa: "
								+ e.getMessage());
			}
		}
		return conexion;
	}

	public static Connection cerrarConexion(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}