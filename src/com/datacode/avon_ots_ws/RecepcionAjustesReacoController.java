/**
 * @author jorge.torner
 * @since 11/01/2012
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.datacode.avon_ots_ws.model.RecepcionAjustesReaco;
import com.datacode.avon_ots_ws.model.RecepcionAjustesReacoDetalle;
import com.datacode.avon_ots_ws.model.RecepcionAjustesReacoPremio;

/**
 * Clase con métodos para la inserción, modificación, eliminación y consulta de la entidad PW_RECEPCION_AJUSTES_REACO y relacionadas
 * @author jorge.torner
 * @since 11/01/2012
 */
public class RecepcionAjustesReacoController {
	/**
	 * Obtiene un arreglo de RecepcionAjustes de acuerdo a los parámetros recibidos
	 * @author jorge.torner
	 * @since 11/01/2012
	 * @param p_idCampania -Id de la campaña
	 * @param p_idZona -Id de la zona
	 * @param p_idUsuario -Id del usuario
	 * @return Arreglo del tipo RecepcionAjustesReaco
	 */
	public RecepcionAjustesReaco[] obtenerListaRecepcionAjustes(int p_idCampania, int p_idZona, int p_idUsuario){
		ArrayList<RecepcionAjustesReaco> recepcionAjustes = new ArrayList<RecepcionAjustesReaco>();
		
		Connection con = AccesoBD.AbrirConexionOTS();
        CallableStatement cs = null;
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_RECEPCION_AJUSTES_REACO(?,?,?,?,?,?,?,?)}");
	        	cs.setObject("p_tipo", "CONSULTA_GRID", Types.VARCHAR);
	        	cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", 0, Types.BIGINT);
	        	cs.setObject("p_ID_CAMPANIA", p_idCampania, Types.INTEGER);
	        	cs.setObject("p_ID_ZONA", p_idZona, Types.INTEGER);
	        	cs.setObject("p_idUsuario", p_idUsuario, Types.INTEGER);
	        	cs.setObject("p_TOT_CAJAS_BELLEZA", 0, Types.SMALLINT);
	        	cs.setObject("p_TOT_CAJAS_CASAMODA", 0, Types.SMALLINT);
	        	cs.setObject("p_TOT_CAJAS_PREMIOS", 0, Types.SMALLINT);

	        	ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	while(rs.next())
	        	{
	        		RecepcionAjustesReaco recAjuste = new RecepcionAjustesReaco();
	        		recAjuste.setIdRecepcionAjustesReaco(rs.getLong("ID_RECEPCION_AJUSTES_REACO"));
	        		recAjuste.setIdCampania(rs.getInt("ID_CAMPANIA"));
	        		recAjuste.setIdZona(rs.getInt("ID_ZONA"));
	        		recAjuste.setClaveCampania(rs.getString("CLAVE_CAMPANIA"));
	        		recAjuste.setZona(rs.getString("ZONA"));
	        		recAjuste.setGerenteZonal(rs.getString("NOMBRE_GERENTE_ZONAL"));
	        		recAjuste.setFechaRecepcion(rs.getDate("FECHA_RECEPCION_AJUSTE"));
	        		recAjuste.setQuienRecibio(rs.getString("QUIEN_RECIBIO"));
	        		
	        		recepcionAjustes.add(recAjuste);
	        	}
	        	rs.close();
	        }
	        catch (SQLException ex){
	        	Utils.GuardarLogMensajeBD("CUADMIN002.01.01", "M1", "No se pudieron consultar los Ajustes", ex.getMessage(), p_idUsuario);
	        }
	        finally{
	        	AccesoBD.CerrarStatement(cs);
	        	AccesoBD.CerrarConexion(con);
	        }
		}
		
		return recepcionAjustes.toArray(new RecepcionAjustesReaco[0]);
	}
	
	/**
	 * Obtiene la Recepcion de Ajuste indicado por el parámetro
	 * @author jorge.torner
	 * @since 11/01/2012
	 * @param p_idRecepcionAjustesReaco -Id de Recepción de Ajuste
	 * @param p_idUsuario -Id del usuario
	 * @return Objeto del tipo RecepcionAjustesReaco
	 */
	public RecepcionAjustesReaco obtenerRecepcionAjuste(long p_idRecepcionAjustesReaco, int p_idUsuario){
		RecepcionAjustesReaco recAjuste = null;
		ArrayList<RecepcionAjustesReacoDetalle> recAjusteDetalles = new ArrayList<RecepcionAjustesReacoDetalle>();
		ArrayList<RecepcionAjustesReacoPremio> recAjustePremios = new ArrayList<RecepcionAjustesReacoPremio>();
		
		Connection con = AccesoBD.AbrirConexionOTS();
        CallableStatement cs = null;
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_RECEPCION_AJUSTES_REACO(?,?,?,?,?,?,?,?)}");
	        	cs.setObject("p_tipo", "CONSULTA_MODIF", Types.VARCHAR);
	        	cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", p_idRecepcionAjustesReaco, Types.BIGINT);
	        	cs.setObject("p_ID_CAMPANIA", 0, Types.INTEGER);
	        	cs.setObject("p_ID_ZONA", 0, Types.INTEGER);
	        	cs.setObject("p_idUsuario", p_idUsuario, Types.INTEGER);
	        	cs.setObject("p_TOT_CAJAS_BELLEZA", 0, Types.SMALLINT);
	        	cs.setObject("p_TOT_CAJAS_CASAMODA", 0, Types.SMALLINT);
	        	cs.setObject("p_TOT_CAJAS_PREMIOS", 0, Types.SMALLINT);

	        	ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	//Aquí se obtiene el Ajuste y sus detalles
	        	while(rs.next())
	        	{
	        		if(recAjuste == null){
	        			recAjuste = new RecepcionAjustesReaco();
	        			recAjuste.setIdRecepcionAjustesReaco(rs.getLong("ID_RECEPCION_AJUSTES_REACO"));
	        			recAjuste.setIdCampania(rs.getInt("ID_CAMPANIA"));
	        			recAjuste.setIdZona(rs.getInt("ID_ZONA"));
	        			recAjuste.setClaveCampania(rs.getString("CLAVE_CAMPANIA"));
	        			recAjuste.setZona(rs.getString("ZONA"));
	        			recAjuste.setGerenteZonal(rs.getString("NOMBRE_GERENTE_ZONAL"));
	        			recAjuste.setFechaRecepcion(rs.getDate("FECHA_RECEPCION_AJUSTE"));
	        			recAjuste.setQuienRecibio(rs.getString("QUIEN_RECIBIO"));
	        			recAjuste.setTotalCajasBelleza(rs.getShort("TOT_CAJAS_BELLEZA"));
	        			recAjuste.setTotalCajasCasaModa(rs.getShort("TOT_CAJAS_CASAMODA"));
	        			recAjuste.setTotalCajasPremios(rs.getShort("TOT_CAJAS_PREMIOS"));
	        		}
	        		RecepcionAjustesReacoDetalle recAjusteDetalle = new RecepcionAjustesReacoDetalle();
	        		recAjusteDetalle.setIdRecepcionAjustesReacoDetalle(rs.getLong("ID_RECEPCION_AJUSTES_REACO_DETALLE"));
	        		recAjusteDetalle.setIdSubcategoriaProducto(rs.getShort("ID_SUB_CATEGORIA_PRODUCTO"));
	        		recAjusteDetalle.setDescripcionSubcategoriaProducto(rs.getString("DESCRIPCION_SUB_CATEGORIA"));
	        		recAjusteDetalle.setCantidadBuenEstado(rs.getInt("CANTIDAD_BUEN_ESTADO"));
	        		recAjusteDetalle.setCantidadMalEstado(rs.getInt("CANTIDAD_MAL_ESTADO"));
	        		
	        		recAjusteDetalles.add(recAjusteDetalle);
	        	}
	        	rs.close();
	        	
	        	//Si hubo ajuste entonces se obtienen sus premios
		        if(recAjuste != null){
		        	cs = con.prepareCall("{call SP_PWA_RECEPCION_PREMIOS_REACO_DETALLE(?,?,?,?,?,?)}");
		        	cs.setObject("p_tipo", "CONSULTA", Types.VARCHAR);
		        	cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", p_idRecepcionAjustesReaco, Types.BIGINT);
		        	cs.setObject("p_CANTIDAD", 0, Types.INTEGER);
		        	cs.setObject("p_DESCRIPCION", "", Types.VARCHAR);
		        	cs.setObject("p_PROGRAMA", "", Types.VARCHAR);
		        	cs.setObject("p_ID_CAMPANIA", 0, Types.INTEGER);

		        	rs = AccesoBD.executeRetrieveResultSet(cs);
		        	while(rs.next()){
		        		RecepcionAjustesReacoPremio recAjustePremio = new RecepcionAjustesReacoPremio();
		        		recAjustePremio.setIdRecepcionAjustesReacoPremio(rs.getLong("ID_RECEPCION_PREMIOS_REACO_DETALLE"));
		        		recAjustePremio.setCantidad(rs.getInt("CANTIDAD"));
		        		recAjustePremio.setDescripcion(rs.getString("DESCRIPCION"));
		        		recAjustePremio.setPrograma(rs.getString("PROGRAMA"));
		        		recAjustePremio.setIdCampania(rs.getInt("ID_CAMPANIA"));
		        		recAjustePremio.setClaveCampania(rs.getString("CLAVE_CAMPANIA"));
		        		
		        		recAjustePremios.add(recAjustePremio);
		        	}
		        	rs.close();
		        	
		        	//Se agregan los detalles y premios al ajuste
		        	recAjuste.setDetalles(recAjusteDetalles.toArray(new RecepcionAjustesReacoDetalle[0]));
		        	recAjuste.setPremios(recAjustePremios.toArray(new RecepcionAjustesReacoPremio[0]));
		        }
	        }
	        catch (SQLException ex){
	        	Utils.GuardarLogMensajeBD("CUADMIN002.01.01", "M2", "No se pudo consultar el Ajuste", ex.getMessage(), p_idUsuario);
	        }
	        finally{
	        	AccesoBD.CerrarStatement(cs);
	        	AccesoBD.CerrarConexion(con);
	        }
	        
		}
		
		return recAjuste;
	}
	
	/**
	 * Elimina una Recepción de Ajuste
	 * @author jorge.torner
	 * @since 11/01/2012
	 * @param p_idRecepcionAjustesReaco -Id de Recepción de Ajuste
	 * @param p_idUsuario -Id del usuario
	 * @return Regresa cadena con mensaje de error si ocurrió alguno, cadena vacía de lo contrario 
	 */
	public String eliminarRecepcionAjuste(long p_idRecepcionAjustesReaco, int p_idUsuario){
		String mensaje = "";
		
		Connection con = AccesoBD.AbrirConexionOTS();
        CallableStatement cs = null;
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_RECEPCION_AJUSTES_REACO(?,?,?,?,?,?,?,?)}");
	        	cs.setObject("p_tipo", "ELIMINACION", Types.VARCHAR);
	        	cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", p_idRecepcionAjustesReaco, Types.BIGINT);
	        	cs.setObject("p_ID_CAMPANIA", 0, Types.INTEGER);
	        	cs.setObject("p_ID_ZONA", 0, Types.INTEGER);
	        	cs.setObject("p_idUsuario", p_idUsuario, Types.INTEGER);
	        	cs.setObject("p_TOT_CAJAS_BELLEZA", 0, Types.SMALLINT);
	        	cs.setObject("p_TOT_CAJAS_CASAMODA", 0, Types.SMALLINT);
	        	cs.setObject("p_TOT_CAJAS_PREMIOS", 0, Types.SMALLINT);

//	        	cs.executeUpdate();
	        	ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	if(rs.next())
	        	{
	        		int res = rs.getInt("RESULTADO");
	        		if(res == 0)
	        			mensaje = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M5", true, "Recepción de ajustes de zona y campaña seleccionadas ya fue enviada a reacondicionamiento", "", p_idUsuario).get(0);
	        	}
	        	rs.close();
	        }
	        catch (SQLException ex){
	        	mensaje = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M4", true, "No se pudo eliminar el Ajuste", ex.getMessage(), p_idUsuario).get(0);
	        }
	        finally{
	        	AccesoBD.CerrarStatement(cs);
	        	AccesoBD.CerrarConexion(con);
	        }
		}
		
		return mensaje;
	}
	
	/**
	 * Guarda un ajuste nuevo o cambios de uno existente
	 * @author jorge.torner
	 * @since 12/01/2012
	 * @param p_tipo -Tipo de guardado: 'INSERCION' o 'MODIFICACION'
	 * @param recepAjuste -Objeto de tipo RecepcionAjustesReaco con los datos del guardado 
	 * @param p_idUsuario -Id del usuario
	 * @return Cadena con mensaje de error en caso de existir, cadena vacía de lo contrario
	 */
	public String guardarRecepcionAjuste(String p_tipo, RecepcionAjustesReaco recepAjuste, int p_idUsuario){
		long idRecepcionAjuste = recepAjuste.getIdRecepcionAjustesReaco();
		String mensaje = "";
		
		Connection con = AccesoBD.AbrirConexionOTS();
        CallableStatement cs = null;
		if(con != null){
	        try{
	        	//AJUSTE
	        	cs = con.prepareCall("{call SP_PWA_RECEPCION_AJUSTES_REACO(?,?,?,?,?,?,?,?)}");
	        	cs.setObject("p_tipo", p_tipo, Types.VARCHAR);
	        	cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", recepAjuste.getIdRecepcionAjustesReaco(), Types.BIGINT);
	        	cs.setObject("p_ID_CAMPANIA", recepAjuste.getIdCampania(), Types.INTEGER);
	        	cs.setObject("p_ID_ZONA", recepAjuste.getIdZona(), Types.INTEGER);
	        	cs.setObject("p_idUsuario", p_idUsuario, Types.INTEGER);
	        	cs.setObject("p_TOT_CAJAS_BELLEZA", recepAjuste.getTotalCajasBelleza(), Types.SMALLINT);
	        	cs.setObject("p_TOT_CAJAS_CASAMODA", recepAjuste.getTotalCajasCasaModa(), Types.SMALLINT);
	        	cs.setObject("p_TOT_CAJAS_PREMIOS", recepAjuste.getTotalCajasPremios(), Types.SMALLINT);

	        	ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	if(rs.next())
	        	{
	        		idRecepcionAjuste = rs.getLong("ID_RECEPCION_AJUSTES_REACO");
	        	}
	        	rs.close();
	        	
	        	//Ahora insertamos los detalles, premios y cajas, en cada caso el sp borra e inserta de nuevo
	        	if(idRecepcionAjuste != 0){
	        		//Se insertan DETALLES
	        		for(RecepcionAjustesReacoDetalle recepAjusteDet : recepAjuste.getDetalles()){
	        			cs = con.prepareCall("{call SP_PWA_RECEPCION_AJUSTES_REACO_DETALLE(?,?,?,?,?)}");
	        			cs.setObject("p_tipo", p_tipo, Types.VARCHAR);
	        			cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", idRecepcionAjuste, Types.BIGINT);
	        			cs.setObject("p_ID_SUB_CATEGORIA_PRODUCTO", recepAjusteDet.getIdSubcategoriaProducto(), Types.SMALLINT);
	        			cs.setObject("p_CANTIDAD_BUEN_ESTADO", recepAjusteDet.getCantidadBuenEstado(), Types.INTEGER);
	        			cs.setObject("p_CANTIDAD_MAL_ESTADO", recepAjusteDet.getCantidadMalEstado(), Types.INTEGER);

	        			cs.executeUpdate();
	        			AccesoBD.CerrarStatement(cs);
	        		}
	        		
	        		//PREMIOS
	        		//Si es tipo MODIFICACION primero eliminamos premios
	        		if(p_tipo.equals("MODIFICACION")){
	        			cs = con.prepareCall("{call SP_PWA_RECEPCION_PREMIOS_REACO_DETALLE(?,?,?,?,?,?)}");
	        			cs.setObject("p_tipo", "ELIMINACION", Types.VARCHAR);
	        			cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", idRecepcionAjuste, Types.BIGINT);
	        			cs.setObject("p_CANTIDAD", 0, Types.INTEGER);
	        			cs.setObject("p_DESCRIPCION", "", Types.VARCHAR);
	        			cs.setObject("p_PROGRAMA", "", Types.VARCHAR);
	        			cs.setObject("p_ID_CAMPANIA", 0, Types.INTEGER);

	        			cs.executeUpdate();
	        			AccesoBD.CerrarStatement(cs);
	        		}
	        		//Insertamos premios
	        		if(recepAjuste.getPremios() != null){
		        		for(RecepcionAjustesReacoPremio recepAjustePremio : recepAjuste.getPremios()){
		        			cs = con.prepareCall("{call SP_PWA_RECEPCION_PREMIOS_REACO_DETALLE(?,?,?,?,?,?)}");
		        			cs.setObject("p_tipo", p_tipo, Types.VARCHAR);
		        			cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", idRecepcionAjuste, Types.BIGINT);
		        			cs.setObject("p_CANTIDAD", recepAjustePremio.getCantidad(), Types.INTEGER);
		        			cs.setObject("p_DESCRIPCION", recepAjustePremio.getDescripcion(), Types.VARCHAR);
		        			cs.setObject("p_PROGRAMA", recepAjustePremio.getPrograma(), Types.VARCHAR);
		        			cs.setObject("p_ID_CAMPANIA", recepAjustePremio.getIdCampania(), Types.INTEGER);
	
		        			cs.executeUpdate();
		        			AccesoBD.CerrarStatement(cs);
		        		}
	        		}
	        		
	        		//Se insertan CAJAS
	        		cs = con.prepareCall("{call SP_PWA_RECEPCION_AJUSTES_REACO_CAJAS(?,?,?,?,?,?)}");
	        		cs.setObject("p_tipo", p_tipo, Types.VARCHAR);
	        		cs.setObject("p_ID_RECEPCION_AJUSTES_REACO", idRecepcionAjuste, Types.BIGINT);
	        		cs.setObject("p_TOT_CAJAS_BELLEZA", recepAjuste.getTotalCajasBelleza(), Types.SMALLINT);
	        		cs.setObject("p_TOT_CAJAS_CASAMODA", recepAjuste.getTotalCajasCasaModa(), Types.SMALLINT);
	        		cs.setObject("p_TOT_CAJAS_PREMIOS", recepAjuste.getTotalCajasPremios(), Types.SMALLINT);
	        		cs.setObject("p_prefijoCodigoBarras", recepAjuste.getPrefijoCodigoBarras(), Types.VARCHAR);

	        		cs.executeUpdate();
	        		
	        		//DEVOLVEMOS EL ID
	        		mensaje = String.valueOf(idRecepcionAjuste);
	        		//Es todo.
	        	}
	        	else{//Si regresa 0, ent no pasó validación
	        		mensaje = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M5", true, "Recepción de ajustes de zona y campaña seleccionadas ya fue enviada a reacondicionamiento", "", p_idUsuario).get(0);
	        	}
	        }
	        catch (SQLException ex){
	        	mensaje = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M3", true, "No se pudo guardar el Ajuste", ex.getMessage(), p_idUsuario).get(0);
	        }
	        finally{
	        	AccesoBD.CerrarStatement(cs);
	        	AccesoBD.CerrarConexion(con);
	        }
		}
		
		return mensaje;
	}
}
