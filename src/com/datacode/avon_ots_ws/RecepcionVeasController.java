/**
 *
 *  @since 29/12/2011
 *
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author jessica.leon
 * @since 29/12/2011
 *
 */
public class RecepcionVeasController {

	private Connection connection;
	private String mensaje;
	private String idRecepcionVeas;
	
	public RecepcionVeasController(){
		
		this.connection = null;
		idRecepcionVeas = null;
	}
	
	/**
	 * 
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @param idCampania
	 * @param idZona
	 * @param idUsuario
	 * @return
	 *
	 */
	public String insertarRecepcionVeas(Integer idCampania,Integer idZona,int idUsuario,String tipoCU){
		
		connection = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		
		
		try{
			if(connection != null){
				cs = connection.prepareCall("{call SP_InsertarRecepcionVea(?,?,?)}");
				cs.setObject("P_ID_CAMPANIA",idCampania,Types.INTEGER);
				cs.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				cs.setObject("P_ID_USUARIO", idUsuario, Types.INTEGER);
				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	
				if(rs.next())
	        	{
	        		this.idRecepcionVeas = rs.getString("ID_RECEPCION_VEAS");
	        	}
	        	rs.close();
	        	
	        	mensaje = "Se realizo la inserción correctamente";
			}
		}catch (SQLException ex){
        	System.out.println(ex.getMessage());
        	Utils.GuardarLogMensajeBD("CU002.01.02", "M4", "Surgió un error al insertar en la tabla PW_RECEPCION_VEAS_REACO", ex.getMessage(), idUsuario);
        	return "Surgió un error al insertar en la tabla PW_RECEPCION_VEAS_REACO";	
        }
        finally{
        	AccesoBD.CerrarConexion(connection);
        }
		return this.idRecepcionVeas;
	}
	
	/**
	 * 
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @param descripcion
	 * @param codigoBarras
	 * @param entrego
	 * @param recibio
	 * @param idEstatus
	 * @return
	 *
	 */
	public String insertarRecepcionVeaDetalle(String idRecepcionVeas,String descripcion,String codigoBarras,
												String entrego, String recibio,Integer idEstatus,Integer idUsuario,String tipoCU){
	   
		connection = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		mensaje = null;
		
		try{
			if(connection != null){
				cs = connection.prepareCall("{call SP_InsertarRecepcionVeaDetalle(?,?,?,?,?,?)}");
				cs.setObject("P_ID_RECEPCION_VEAS_REACO",idRecepcionVeas,Types.INTEGER);
				cs.setObject("P_DESCRIPCION",descripcion,Types.VARCHAR);
				cs.setObject("P_CODIGO_BARRAS",codigoBarras,Types.VARCHAR);
				cs.setObject("P_ENTREGO",entrego,Types.VARCHAR);
				cs.setObject("P_RECIBIO",recibio,Types.VARCHAR);
				cs.setObject("P_ID_ESTATUS",idEstatus,Types.INTEGER);
				cs.execute();
	        	mensaje = "Se realizo la inserción correctamente";
			}
		}catch (SQLException ex){
        	System.out.println(ex.getMessage());
        	Utils.GuardarLogMensajeBD("CU002.01.02", "M5", "Surgió un error al insertar en la tabla PW_RECEPCION_VEAS_REACO_DETALLE", ex.getMessage(), idUsuario);
        	return "Surgió un error al insertar en la tabla PW_RECEPCION_VEAS_REACO_DETALLE";
        }
        finally{
        	AccesoBD.CerrarConexion(connection);
        }
		return mensaje;
	}
}
