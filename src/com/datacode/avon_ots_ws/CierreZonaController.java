/**
 * @author jose.ponce
 * @since 05/01/2012
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.datacode.avon_ots_ws.model.CierreZona;

/**
 * @author jose.ponce
 * @since 05/01/2012
 */
public class CierreZonaController {
	PreparedStatement ps = null;
	CallableStatement cs = null;
	ResultSet rst = null;
	private String mensaje;
	private Connection con;
	/**
	 * 
	 * @author jose.ponce
	 * @since 11/01/2012
	 * @return Array tipo CierreZona
	 * @param Id_Usuario
	 */
	public CierreZona[] getCierreZona(Integer Id_Zona, Integer Id_Campania, Integer Tipo_Item, int Id_Usuario){
		con = AccesoBD.AbrirConexionOTS();
		ArrayList<CierreZona> arrCierre = new ArrayList<CierreZona>();
		
		if(con != null){
	        try{
		
	        	cs = con.prepareCall("{call sp_Ret_Lista_Det_Orden_Premio(?,?,?)}");
	        	cs.setObject("p_ID_ZONA", Id_Zona, Types.INTEGER);
	        	cs.setObject("p_ID_CAMPANIA", Id_Campania, Types.INTEGER);
	        	cs.setObject("P_TIPO_ITEM", Tipo_Item, Types.INTEGER);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	CierreZona cierre = new CierreZona();
	            	cierre.setRegistro(rst.getString(2));//Account
	            	cierre.setId_orden(rst.getInt(4));//Id_Orden
	            	cierre.setId_item(rst.getInt(5));//Id_Item
	            	cierre.setCodigo_barras(rst.getString(1));//Codigo de barras
	            	cierre.setDescripcion(rst.getString(8));//tipo_item.descripcion
	            	cierre.setClave(rst.getString(3));//clave_orden
	            	cierre.setEstatus(rst.getString(7));//estatus_item.descripcion
	            	cierre.setCantidad(rst.getInt(9));//cantidad
	            	cierre.setEscaneados(rst.getString(10));//escaneados
	            	cierre.setNombre(rst.getString("NOMBRE"));
	            	cierre.setRuta(rst.getString("CLAVE_RUTA"));
	            	cierre.setSecuencia(rst.getString("SECUENCIA_ENTREGA_ANTERIOR"));
	            	arrCierre.add(cierre);
	            }
	            cs.close();
			}catch (SQLException ex){
	        	System.out.println(ex.getMessage());
	        	Utils.GuardarLogMensajeBD("CUADMIN002.02.01", "M5", "Excepción al regresar tabla para cierre de zona.", ex.getMessage(), Id_Usuario);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (CierreZona[])arrCierre.toArray(new CierreZona[arrCierre.size()]);
	}

	public String ActualizaItem(Integer Id_Orden, Integer Id_Item, String Clave_Orden, Integer Id_Estatus, String Usuario, int Id_Usuario,
			Integer Id_Campania, Integer Id_Zona){
		con=AccesoBD.AbrirConexionOTS();
		mensaje="";
		if(con != null){
			try{
				cs = con.prepareCall("{call sp_Actualiza_Item_CerrarZona(?,?,?,?,?,?,?,?)}");
	        	cs.setObject("p_ORDEN", Id_Orden, Types.INTEGER);
	        	cs.setObject("p_ITEM", Id_Item, Types.INTEGER);
	        	cs.setObject("p_CLAVE", Clave_Orden, Types.VARCHAR);
	        	cs.setObject("p_ESTATUS", Id_Estatus, Types.INTEGER);
	        	cs.setObject("p_USUARIO", Usuario, Types.VARCHAR);
	        	cs.setObject("p_ID_CAMPANIA",Id_Campania , Types.INTEGER);
	        	cs.setObject("p_ID_ZONA", Id_Zona, Types.INTEGER);
	        	cs.setObject("p_ID_USUARIO", Id_Usuario, Types.INTEGER);
	        	cs.executeUpdate();
	            //rst = AccesoBD.executeRetrieveResultSet(cs);
	            
	            mensaje="Actualización Correcta";
	            cs.close();
			}catch(SQLException sqlex)
			{
				Utils.GuardarLogMensajeBD("CUADMIN002.02.01", "M4", "Excepción al actualizar cierre de zona.", sqlex.getMessage(), Id_Usuario);
				return "Excepción; Revise log para detalles";
			}finally{
				AccesoBD.CerrarConexion(con);
			}
		}
		return mensaje;
	}
}
