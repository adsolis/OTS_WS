/**
 * @author jorge.torner
 * @since 23/01/2012
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.datacode.avon_ots_ws.model.SubBodegaAlmacen;

/**
 * @author jorge.torner
 * @since 23/01/2012
 */
public class SubBodegaAlmacenController {
	/**
	 * Método para obtener las subbodegas pudiendo filtrar por parámetros
	 * @author jorge.torner
	 * @since 23/01/2012
	 * @param p_idSubBodega -Id subbodega a filtrar. 0 si no se quiere filtrar por éste parámetro. 
	 * @param p_idZona -Id de la zona a filtrar. 0 si no se quiere filtrar por éste parámetro.
	 * @param p_idPais -Id del país a filtrar. 0 si no se quiere filtrar por éste parámetro.
	 * @param p_idLDC -Id del LDC a filtrar. 0 si no se quiere filtrar por éste parámetro.
	 * @return SubBodegaAlmacen[] Arreglo de las subbodegas obtenidas
	 */
	public SubBodegaAlmacen[] obtenerSubBodegaAlmacen(int p_idSubBodega, int p_idZona, int p_idPais, int p_idLDC, int p_idUsuario){
		ArrayList<SubBodegaAlmacen> arrSubBod = new ArrayList<SubBodegaAlmacen>();
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_CatalogoSubBodegaAlmacen(?,?,?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_OPERACION", "S", Types.VARCHAR);
	        	cs.setObject("P_ID_SUB_BODEGA_ALMACEN", p_idSubBodega, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO", 0, Types.INTEGER);
	        	cs.setObject("P_ID_ZONA", p_idZona, Types.INTEGER);
	        	cs.setObject("P_ID_PAIS", p_idPais, Types.INTEGER);
	        	cs.setObject("P_ID_LDC", p_idLDC, Types.INTEGER);
	        	cs.setObject("P_CLAVE", "", Types.VARCHAR);
	        	cs.setObject("P_TELEFONO", "", Types.VARCHAR);
	        	cs.setObject("P_DOMICILIO", "", Types.VARCHAR);
	        	cs.setObject("P_DESCRIPCION", "", Types.VARCHAR);
	        	cs.setObject("P_ID_USUARIO_RESPONSABLE_SUBBODEGA", 0, Types.INTEGER);
	        	
	        	
	            ResultSet rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	SubBodegaAlmacen subBod = new SubBodegaAlmacen();
	            	subBod.setIdSubbodegaAlmacen(rst.getInt("ID_SUB_BODEGA_ALMACEN"));
	            	subBod.setIdZona(rst.getInt("ID_ZONA"));
	            	subBod.setIdPais(rst.getInt("ID_PAIS"));
	            	subBod.setIdLDC(rst.getInt("ID_LDC"));
	            	subBod.setClave(rst.getString("CLAVE"));		subBod.setTelefono(rst.getString("TELEFONO"));
	            	subBod.setDomicilio(rst.getString("DOMICILIO"));	subBod.setDescripcion(rst.getString("DESCRIPCION"));
	            	subBod.setIdUserResponsableSubbodega(rst.getInt("ID_USUARIO_RESPONSABLE_SUBBODEGA"));
	            	arrSubBod.add(subBod);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M13", "Surgió un error al obtener los datos de SubBodega Almacen.", ex.getMessage(), p_idUsuario);
	        }
	        finally{	
	        	AccesoBD.CerrarStatement(cs);
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return arrSubBod.toArray(new SubBodegaAlmacen[0]);
	}
}
