/**
 * @author jose.ponce
 * @since 16/01/2012
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

import com.datacode.avon_ots_ws.model.Bancos;
import com.datacode.avon_ots_ws.model.EntregaVentanilla;
import com.datacode.avon_ots_ws.model.TipoPago;
import com.datacode.avon_ots_ws.model.PersonaRecibe;

/**
 * @author jose.ponce
 *
 */
public class EntregaVentanillaController {

	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	CallableStatement cs = null;
	private Connection con;
	private String mensaje;
	
	/**
	 * Obtenemos los tipos de pago de acuerdo a los resultados del SP
	 * @author jose.ponce
	 * @since 17/01/2012
	 */
	public TipoPago[] getTipoPago(int idUsuario){
		con = AccesoBD.AbrirConexionOTS();
		List<TipoPago> TiposPago = new ArrayList<TipoPago>();
		CallableStatement cs = null;

		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PW_TIPO_PAGO}");
				
				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					TipoPago tipo_pago = new TipoPago();
					tipo_pago.setIdTipoPuesto(resultSet.getInt("ID_TIPO_PAGO"));
					tipo_pago.setDescripcion(resultSet.getString("DESCRIPCION"));
					TiposPago.add(tipo_pago);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M1", "Error al seleccionar tipos de pago", ex.getMessage(),idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (TipoPago[]) TiposPago.toArray(new TipoPago[TiposPago.size()]);
	}
	
	/**
	 * Obtenemos el catalogo de persona recibe
	 * @author jose.ponce
	 * @since 04/04/2012
	 */
	public PersonaRecibe[] getPersonaRecibe(int idUsuario){
		con = AccesoBD.AbrirConexionOTS();
		List<PersonaRecibe> lstPersonaRecibe = new ArrayList<PersonaRecibe>();
		CallableStatement cs = null;

		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PW_PERSONA_RECIBE}");
				
				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					PersonaRecibe persona_recibe = new PersonaRecibe();
					persona_recibe.setIdPersonaRecibe(resultSet.getInt("ID_PERSONA_RECIBE"));
					persona_recibe.setDescripcion(resultSet.getString("DESCRIPCION"));
					lstPersonaRecibe.add(persona_recibe);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M1", "Error al seleccionar persona recibe", ex.getMessage(),idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (PersonaRecibe[]) lstPersonaRecibe.toArray(new PersonaRecibe[lstPersonaRecibe.size()]);
	}
	
	/**
	 * Obtenemos los bancos de acuerdo a los resultados del SP
	 * @author jose.ponce
	 * @since 17/01/2012
	 */
	public Bancos[] getBancos(int idUsuario){
		con = AccesoBD.AbrirConexionOTS();
		List<Bancos> Banco = new ArrayList<Bancos>();
		CallableStatement cs = null;

		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PW_BANCOS}");
				
				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					Bancos catBanco = new Bancos();
					catBanco.setIdBanco(resultSet.getInt("ID_BANCO"));
					catBanco.setClave(resultSet.getString("CLAVE"));
					catBanco.setNombre(resultSet.getString("NOMBRE"));
					Banco.add(catBanco);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M1", "Error al seleccionar tipos de pago", ex.getMessage(),idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (Bancos[]) Banco.toArray(new Bancos[Banco.size()]);
	}

	/**
	 * Método que nos devuelve las representantes de acuerdo al nombre o registro
	 * @author jose.ponce
	 * @since 24/01/2012
	 */
	public EntregaVentanilla[] getRepresentantes(String registro, String nombre, int idUsuario){
		con = AccesoBD.AbrirConexionOTS();
		List<EntregaVentanilla> Repre = new ArrayList<EntregaVentanilla>();
		CallableStatement cs = null;

		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PW_Busca_Representante(?,?)}");
				cs.setObject("p_REGISTRO", registro, Types.VARCHAR);
				cs.setObject("p_NOMBRE", nombre, Types.VARCHAR);
				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					EntregaVentanilla catRepre = new EntregaVentanilla();
					catRepre.setRegistro(resultSet.getString("ACCOUNT"));
					catRepre.setNombre(resultSet.getString("NOMBRE"));
					catRepre.setDomicilio(resultSet.getString("DIRECCION"));
					catRepre.setId_zona(resultSet.getInt("ID_ZONA"));
					catRepre.setZona(resultSet.getString("ZONA"));
					catRepre.setMonto_previo(resultSet.getDouble("MONTO_A_COBRAR"));
					Repre.add(catRepre);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M14", "Error al cargar representantes", ex.getMessage(),idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (EntregaVentanilla[]) Repre.toArray(new EntregaVentanilla[Repre.size()]);
	}
	
	/**
	 * Método que nos devuelve las ordenes con las que cuenta la representante
	 * @author jose.ponce
	 * @since 20/01/2012
	 */
	public EntregaVentanilla[] getOrdenesRepresentante(String registro, String nombre, int idUsuario){
		con = AccesoBD.AbrirConexionOTS();
		List<EntregaVentanilla> OrdRep = new ArrayList<EntregaVentanilla>();
		CallableStatement cs = null;

		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_Actualiza_Grid_Ordenes(?,?)}");
				cs.setObject("p_REGISTRO", registro, Types.VARCHAR);
				cs.setObject("p_NOMBRE", nombre, Types.VARCHAR);
				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					EntregaVentanilla catOrdRep = new EntregaVentanilla();
					catOrdRep.setCampania(resultSet.getString("CAMPANIA"));
					catOrdRep.setId_campania(resultSet.getInt("ID_CAMPANIA"));
					catOrdRep.setId_orden(resultSet.getInt("ID_ORDEN"));
					catOrdRep.setOrden(resultSet.getString("ORDEN"));
					catOrdRep.setTotal_cajas(resultSet.getInt("CAJAS"));
					catOrdRep.setTotal_premios(resultSet.getInt("PREMIOS"));
					catOrdRep.setTotal_unitarios(resultSet.getInt("UNITARIOS"));
					catOrdRep.setEstatus_reparto(resultSet.getString("ESTATUS_REPARTO"));
					catOrdRep.setCausa_devolucion(resultSet.getString("CAUSA_DEVOLUCION"));
					//se agrega nuevo campo para tomar el valor del monto previo de la tabla orden
					catOrdRep.setMonto_previo(resultSet.getDouble("MONTO_A_COBRAR"));
					OrdRep.add(catOrdRep);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M15", "Error al cargar ordenes de representante", ex.getMessage(),idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (EntregaVentanilla[]) OrdRep.toArray(new EntregaVentanilla[OrdRep.size()]);
	}
	
	/**
	 * Método que devuelve los items pertenecientes a la orden
	 * @author jose.ponce
	 * @since 30/01/2012
	 */
	public EntregaVentanilla[] getDetalleOrden(Integer idOrden, int idUsuario){
		con = AccesoBD.AbrirConexionOTS();
		List<EntregaVentanilla> ItemsOrd = new ArrayList<EntregaVentanilla>();
		CallableStatement cs = null;

		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PW_Detalle_Orden(?)}");
				cs.setObject("p_idOrden", idOrden, Types.INTEGER);
				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					EntregaVentanilla catItemOrd = new EntregaVentanilla();
					catItemOrd.setId_item(resultSet.getInt("ID_ITEM"));
					catItemOrd.setCodigo_barras(resultSet.getString("CODIGO"));
					catItemOrd.setCodigoFSC(resultSet.getString("CODIGO_FSC"));
					catItemOrd.setCodigoEAN13(resultSet.getString("CODIGO_FSC_EAN13"));
					catItemOrd.setTipo_item(resultSet.getString("TIPO"));
					catItemOrd.setEstatus_item(resultSet.getString("ESTATUS"));
					catItemOrd.setTotal_item(resultSet.getInt("CANTIDAD"));
					catItemOrd.setEscaneados(resultSet.getString("ESCANEADO"));
					ItemsOrd.add(catItemOrd);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M16", "Error al cargar detalle de la orden", ex.getMessage(),idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (EntregaVentanilla[]) ItemsOrd.toArray(new EntregaVentanilla[ItemsOrd.size()]);
	}
	
	/**
	 * Método que nos devuelve el detalle de pago de la orden
	 * @author jose.ponce
	 * @since 31/01/2012
	 */
	public EntregaVentanilla[] getDetallePago(Integer idOrden, int idUsuario){
		con = AccesoBD.AbrirConexionOTS();
		List<EntregaVentanilla> DetOrdPag = new ArrayList<EntregaVentanilla>();
		CallableStatement cs = null;

		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PW_Det_Pag_Ord(?)}");
				cs.setObject("p_idOrden", idOrden, Types.INTEGER);
				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					EntregaVentanilla detPag = new EntregaVentanilla();
					detPag.setId_pago_entrega(resultSet.getInt("ID_PAGO_ENTREGA"));
					detPag.setId_banco(resultSet.getInt("ID_BANCO"));
					detPag.setBanco(resultSet.getString("BANCO"));
					detPag.setId_tipo_pago(resultSet.getInt("ID_TIPO_PAGO"));
					detPag.setTipo_pago(resultSet.getString("TIPO_PAGO"));
					detPag.setMonto_pagado(resultSet.getDouble("MONTO_PAGADO"));
					detPag.setFolios(resultSet.getString("FOLIOS"));
					detPag.setFecha_pago(resultSet.getString("FECHA_PAGO"));
					DetOrdPag.add(detPag);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M17", "Error al cargar detalle de pago de la orden", ex.getMessage(),idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (EntregaVentanilla[]) DetOrdPag.toArray(new EntregaVentanilla[DetOrdPag.size()]);
	}
	
	/**
	 * Método que actualiza los items a estatus entregado en ventanilla.
	 * @author jose.ponce
	 * @since 02/02/2012
	 */
	public String EntregaVentanilla(Integer Id_Orden, Integer Id_Item, String Usuario, Integer Id_Campania,
			Integer Id_Zona, Integer Id_Usuario, Integer Id_Persona_Recibe, String Persona_Recibe, String Comentarios){
		con=AccesoBD.AbrirConexionOTS();
		mensaje="";
		if(con != null){
			try{
				cs = con.prepareCall("{call SP_PW_Upd_Ent_Ventanilla(?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("p_ORDEN", Id_Orden, Types.INTEGER);
	        	cs.setObject("p_ITEM", Id_Item, Types.INTEGER);
	        	cs.setObject("p_USUARIO", Usuario, Types.VARCHAR);
	        	cs.setObject("p_ID_CAMPANIA",Id_Campania , Types.INTEGER);
	        	cs.setObject("p_ID_ZONA", Id_Zona, Types.INTEGER);
	        	cs.setObject("p_ID_USUARIO", Id_Usuario, Types.INTEGER);
	        	cs.setObject("p_ID_PERSONA_RECIBE", Id_Persona_Recibe, Types.INTEGER);
	        	cs.setObject("p_COMENTARIOS_RECIBE", Persona_Recibe, Types.VARCHAR);
	        	cs.setObject("p_COMENTARIOS_ENTREGA", Comentarios, Types.VARCHAR);
	        	//resultSet = AccesoBD.executeRetrieveResultSet(cs);
	        	cs.execute();
	            
	            mensaje="Actualización Correcta";
	            cs.close();
			}catch(SQLException sqlex)
			{
				Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M18", "Excepción al actualizar items.", sqlex.getMessage(), Id_Usuario);
				return "Excepción; Revise log para detalles";
			}finally{
				AccesoBD.CerrarConexion(con);
			}
		}
		return mensaje;
	}
	
	/**
	 * Método que inserta el detalle de pago de la orden entregada en ventanilla
	 * @author jose.ponce
	 * @since 02/02/2012
	 */
	public String PagoVentanilla(Integer Id_Orden, Integer Id_Campania, Integer Id_Zona, Integer Id_Banco, Integer Id_Tipo_Pago,
			String Monto, String Folios, String Fecha, String Usuario, int Id_Usuario){
		con=AccesoBD.AbrirConexionOTS();
		mensaje="";
		if(con != null){
			try{
				cs = con.prepareCall("{call SP_PW_Inserta_Pago_Ventanilla(?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("p_ID_ORDEN", Id_Orden, Types.INTEGER);
	        	cs.setObject("p_ID_CAMPANIA", Id_Campania, Types.INTEGER);
	        	cs.setObject("p_ID_ZONA", Id_Zona, Types.INTEGER);
	        	cs.setObject("P_ID_BANCO", Id_Banco, Types.INTEGER);
	        	cs.setObject("p_ID_TIPO_PAGO",Id_Tipo_Pago , Types.INTEGER);
	        	cs.setObject("p_MONTO", Monto, Types.VARCHAR);
	        	cs.setObject("p_FOLIOS", Folios, Types.VARCHAR);
	        	cs.setObject("p_FECHA", Fecha, Types.VARCHAR);
	        	cs.setObject("p_USUARIO", Usuario, Types.VARCHAR);
	        	//resultSet = AccesoBD.executeRetrieveResultSet(cs);
	        	cs.execute();
	            mensaje="Actualización Correcta";
	            cs.close();
			}catch(SQLException sqlex)
			{
				Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M18", "Excepción al actualizar items.", sqlex.getMessage(), Id_Usuario);
				return "Excepción; Revise log para detalles";
			}finally{
				AccesoBD.CerrarConexion(con);
			}
		}
		return mensaje;
	}
}
