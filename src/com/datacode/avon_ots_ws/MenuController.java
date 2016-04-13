/**
 * 
 *  @since 15/01/2012
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

import com.datacode.avon_ots_ws.model.AccionesModulos;
import com.datacode.avon_ots_ws.model.ModulosMenu;



/**
 * @author javier.gallegos
 * @since 15/01/2012
 * 
 */
public class MenuController {

	private ResultSet resultSet;
	private CallableStatement callableStatement;
	private Connection connection;

	public MenuController() {
		
	}

	public List<ModulosMenu> obtenerModulosMenuUsuario(int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<ModulosMenu> modulos = new ArrayList<ModulosMenu>();
		ModulosMenu modulo = null;

		if (connection != null) {
			try {
				callableStatement = connection.prepareCall("{call SP_Obtener_Lista_Modulos_Menu(?)}");
				callableStatement.setObject("P_ID_USUARIO",idUsuario,Types.INTEGER);
				resultSet = AccesoBD.executeRetrieveResultSet(callableStatement);
				
				while (resultSet.next()) {
					modulo = new ModulosMenu();
					modulo.setArchivo(resultSet.getString("ARCHIVO"));
					modulo.setIdModulo(resultSet.getInt("ID_MODULO"));
					modulo.setIdPadre(resultSet.getInt("PADRE"));
					modulo.setNombreModulo(resultSet.getString("NOMBRE"));
					modulo.setOrden(resultSet.getInt("ORDEN"));
					modulos.add(modulo);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(" ", "M1", "Surgió un error al obtener los modulos del Usuario", ex.getMessage(), idUsuario);
	        	System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return modulos;
	}
	
	public List<AccionesModulos> obtenerAccionesModulosUsuario(int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<AccionesModulos> acciones = new ArrayList<AccionesModulos>();
		AccionesModulos accion = null;

		if (connection != null) {
			try {
				callableStatement = connection.prepareCall("{call SP_Obtener_Lista_Acciones_Modulo(?)}");
				callableStatement.setObject("P_ID_USUARIO",idUsuario,Types.INTEGER);
				resultSet = AccesoBD.executeRetrieveResultSet(callableStatement);
				
				while (resultSet.next()) {
					accion = new AccionesModulos();
					accion.setIdAccion(resultSet.getInt("ID_ACCION"));
					accion.setIdModulo(resultSet.getInt("ID_MODULO"));
					accion.setIdPerfil(resultSet.getInt("ID_PERFIL"));
					accion.setIdUsuario(resultSet.getInt("ID_USUARIO"));
					accion.setNombreAccion(resultSet.getString("NOMBRE"));
					acciones.add(accion);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(" ", "M1", "Surgió un error al obtener las acciones por modulo del Usuario", ex.getMessage(), idUsuario);
	        	System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return acciones;
	}
	
	
}
