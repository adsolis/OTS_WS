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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.datacode.avon_ots_ws.model.ItemLiqSubBodega;

/**
 * Clase del controller para el CUADMIN002.01.03 de Liquidación de SubBodega
 * @author jorge.torner
 * @since 23/01/2012
 */
public class LiquidacionSubBodegaController {
	/**
	 * Método para obtener la información de los grids de Liquidación de SubBodega
	 * @author jorge.torner
	 * @since 23/01/2012
	 * @param p_tipo -Tipo de consulta: "ORDENES", "PREMIOS" ó "INVENTARIO"
	 * @param p_idZona -Id de la zona
	 * @param p_idCampania -Id de la campaña
	 * @param p_idSubBodega -Id de la SubBodega
	 * @param p_idUsuario -Id del usuario
	 * @return ItemLiqSubBodega[] Información del grid correspondiente
	 */
	public ItemLiqSubBodega[] obtenerGridLiqSubBodega(String p_tipo, int p_idZona, int p_idCampania, int p_idSubBodega, int p_idUsuario){
		ArrayList<ItemLiqSubBodega> lItems = new ArrayList<ItemLiqSubBodega>();

		Connection con = AccesoBD.AbrirConexionOTS();
        CallableStatement cs = null;
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerItemsLiquidacionSubBodega(?,?,?,?)}");
	        	cs.setObject("P_TIPO", p_tipo, Types.VARCHAR);
	        	cs.setObject("P_ID_ZONA", p_idZona, Types.INTEGER);
	        	cs.setObject("P_ID_CAMPANIA", p_idCampania, Types.INTEGER);
	        	cs.setObject("P_ID_SUBBODEGA", p_idSubBodega, Types.INTEGER);

	        	ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	while(rs.next())
	        	{
	        		ItemLiqSubBodega itemLiq = new ItemLiqSubBodega();
	        		
	        		itemLiq.setCantidad(rs.getInt("CANTIDAD"));
	        		itemLiq.setCodigoBarras(rs.getString("CODIGO_BARRAS"));
	        		itemLiq.setDescripcionItem(rs.getString("DESCRIPCION_ITEM"));
	        		itemLiq.setIdItem(rs.getInt("ID_ITEM"));
	        		itemLiq.setIdOrden(rs.getLong("ID_ORDEN"));
	        		itemLiq.setNombreRep(rs.getString("NOMBRE"));
	        		itemLiq.setClaveOrden(rs.getString("CLAVE_ORDEN"));
	        		itemLiq.setRegistroRep(rs.getString("REGISTRO"));
	        		itemLiq.setTipoItem(rs.getString("TIPO_ITEM"));
	        		itemLiq.setIdTipoItem(rs.getShort("ID_TIPO_ITEM"));
	        		
	        		lItems.add(itemLiq);
	        	}
	        	rs.close();
	        }
	        catch (SQLException ex){
	        	Utils.GuardarLogMensajeParams("CUADMIN002.01.03", "M1", ex.getMessage(), p_idUsuario, p_tipo);
	        }
	        finally{
	        	AccesoBD.CerrarStatement(cs);
	        	AccesoBD.CerrarConexion(con);
	        }
		}
		
		return lItems.toArray(new ItemLiqSubBodega[0]);
	}
	
	public String registrarLiquidacionSubBodega(int p_idZona, int p_idCampania, int p_idSubBodega, int p_idUsuarioSupervisor, int p_idUsuarioAlmacen, int p_idUsuario, ItemLiqSubBodega[] p_arrOrdenes, ItemLiqSubBodega[] p_arrPremios){
		String respuesta = "";
		long idLiquidacionSubBodega = 0;
		
		Connection con = AccesoBD.AbrirConexionOTS();
        CallableStatement cs = null;
		if(con != null){
	        try{
	        	//Se registra primero la liquidación
	        	cs = con.prepareCall("{call SP_PWA_RegistrarLiquidacionSubBodega(?,?,?,?,?,?)}");
	        	cs.setObject("P_ID_USUARIO", p_idUsuario, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO_SUPERVISOR", p_idUsuarioSupervisor, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO_ALMACEN", p_idUsuarioAlmacen, Types.INTEGER);
	        	cs.setObject("P_ID_ZONA", p_idZona, Types.INTEGER);
	        	cs.setObject("P_ID_CAMPANIA", p_idCampania, Types.INTEGER);
	        	cs.setObject("P_ID_SUB_BODEGA_ALMACEN", p_idSubBodega, Types.INTEGER);

	        	ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	while(rs.next())
	        	{
	        		idLiquidacionSubBodega = rs.getLong("ID_LIQUIDACION_SUB_BODEGA");
	        	}
	        	rs.close();
	        	AccesoBD.CerrarStatement(cs);
	        	
	        	// Luego se iteran los arreglos de ordenes y premios para registrar sus estatus
	        	for(int i = 0; i<2; i++){
	        		ItemLiqSubBodega[] arrGuardar = null;
	        		if(i == 0)
	        			arrGuardar = p_arrOrdenes;	//Se itera el de Ordenes
	        		else
	        			arrGuardar = p_arrPremios;	//Se itera el de Premios
	        		
	        		if(arrGuardar != null){
			        	for(ItemLiqSubBodega item : arrGuardar){
			        		short idEstatusNuevo = 0;
			        		//Obtenemos estatus del Item
			        		if(item.getCantEntregada() > 0)
			        			idEstatusNuevo = 17; // ENTREGADO EN SUB BODEGA
			        		else
			        			idEstatusNuevo = 15; // DEVUELTO A CEDI
			        		
			        		//Obtenemos fecha en formato adecuado
			        		String fechaEntrega = Utils.ObtenerFechaActual(Utils.formatoFechaCorta);
			        		Format formatter = new SimpleDateFormat(Utils.formatoFechaCorta);
							if(item.getFechaEntrega() != null)
								fechaEntrega = formatter.format(item.getFechaEntrega());
			        		
			        		cs = con.prepareCall("{call SP_PWA_ActualizaLiquidacionSubBodegaItem(?,?,?,?,?,?,?,?)}");
				        	cs.setObject("P_ID_LIQUIDACION_SUB_BODEGA", idLiquidacionSubBodega, Types.BIGINT);
				        	cs.setObject("P_ID_ITEM", item.getIdItem(), Types.BIGINT);
				        	cs.setObject("P_ID_TIPO_ITEM", item.getIdTipoItem(), Types.SMALLINT);
				        	cs.setObject("P_ID_ESTATUS_NUEVO", idEstatusNuevo, Types.SMALLINT);
				        	cs.setObject("P_FECHA_ENTREGA", fechaEntrega, Types.VARCHAR);
				        	cs.setObject("P_ID_USUARIO", p_idUsuario, Types.INTEGER);
				        	cs.setObject("P_ID_CAMPANIA", p_idCampania, Types.INTEGER);
				        	cs.setObject("P_CAUSA_FALTANTE", item.getCausaFaltante(), Types.VARCHAR);
				        	
				        	cs.executeUpdate();
			        	}
	        		}
	        	}
	        	
	        }
	        catch (SQLException ex){
	        	respuesta = Utils.ObtenerMensajeBD("CUADMIN002.01.03", "M1", true, "No se pudo registrar la Liquidación de Sub bodega", ex.getMessage(), p_idUsuario).get(0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
		}
		
		return respuesta;
	}
}
