/**
 * @author jorge.torner
 * @since 16/01/2012
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;


/**
 * Clase para las consultas de generación de códigos de barras
 * @author jorge.torner
 * @since 16/01/2012
 */
public class ImprimirCodigosBarrasController {

	/**
	 * Método para obtener los códigos de barras de algún tipo
	 * @author jorge.torner
	 * @since 16/01/2012
	 * @param p_tipo -Tipo de códigos
	 * @param p_id -Id para la consulta
	 * @param p_idUsuario -Id del usuario
	 * @return String[] Arreglo de códigos obtenidos
	 */
	public String[] obtenerCodigosBarrasAGenerar(String p_tipo, long p_id, String p_lstIdsGeneracionCodigos, int p_idUsuario){
		ArrayList<String> codigos = new ArrayList<String>();
		
		Connection con = AccesoBD.AbrirConexionOTS();
        CallableStatement cs = null;
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerCodigosBarrasAGenerar(?,?,?)}");
	        	cs.setObject("p_tipo", p_tipo, Types.VARCHAR);
	        	cs.setObject("p_ID", p_id, Types.BIGINT);
	        	cs.setObject("p_lstIds", p_lstIdsGeneracionCodigos, Types.VARCHAR);

	        	ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	while(rs.next())
	        	{	
	        		codigos.add(rs.getString("CODIGO_BARRAS"));
	        	}
	        	rs.close();
	        }
	        catch (SQLException ex){
	        	Utils.GuardarLogMensajeBD("General Admin", "M5", "Error al consultar los códigos de barras", ex.getMessage(), p_idUsuario);
	        }
	        finally{
	        	AccesoBD.CerrarStatement(cs);
	        	AccesoBD.CerrarConexion(con);
	        }
		}
		
		return codigos.toArray(new String[0]);
	}
}
