/**
 *  Contiene los metodos necesarios para funcionalidad del CU001_04 - Consultar Catalogos de AVON
 * @author brenda.estrada
 * @since 19/01/2012
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.datacode.avon_ots_ws.model.Bancos;
import com.datacode.avon_ots_ws.model.Campania;
import com.datacode.avon_ots_ws.model.Division;
import com.datacode.avon_ots_ws.model.EstadoOrden;
import com.datacode.avon_ots_ws.model.Orden;
import com.datacode.avon_ots_ws.model.Premios;
import com.datacode.avon_ots_ws.model.ProgramacionReparto;
import com.datacode.avon_ots_ws.model.RazonesDevolucion;
import com.datacode.avon_ots_ws.model.Representante;
import com.datacode.avon_ots_ws.model.TipoLiquidacion;
import com.datacode.avon_ots_ws.model.TipoPago;
import com.datacode.avon_ots_ws.model.TipoSiniestro;
import com.datacode.avon_ots_ws.model.Unitarios;
import com.datacode.avon_ots_ws.model.Zona;

/**
 * @author brenda.estrada
 * @since 19/01/2012
 */
public class ConsultarCatalogosController {
	//Propiedades Persistencia
	PreparedStatement ps = null;
	CallableStatement cs = null;
	ResultSet rst = null;
	String query = "";
	Connection con = AccesoBD.AbrirConexionOTS();
	//Var para guardar mensaje personalizado
	String msg = "";
	String v_operacion = "";
	String v_estatus = "";
	
	
	/* *******************************************************************************************************************************************  */
	/* ***************************************************  Cargar Listas de Valores  ************************************************************* */
	/* *******************************************************************************************************************************************  */
	
	/**
	 * Obtiene los valores existentes de Bancos de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto Bancos
	 */
	public Bancos[] getBancosExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Bancos> arrData = new ArrayList<Bancos>();
		v_operacion = "1";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Bancos data = new Bancos();
	            	data.setIdBanco(rst.getInt(1));
	            	data.setClave((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setNombre((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setLastupd_ts((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setFechaActualizado((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setUsuarioActualiza((rst.getString(6)==null)?"":rst.getString(6));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M1", "Surgió un error al obtener los datos de Bancos.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Bancos[])arrData.toArray(new Bancos[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Campanias de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto Campania
	 */
	public Campania[] getCampaniasExistentes(String pAnio, String pAnioCampania, String pZona){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Campania> arrData = new ArrayList<Campania>();
		v_operacion = "2";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?,?,?,?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_TRABAJA", "", Types.VARCHAR);
	        	cs.setObject("P_PAGO_EFECTIVO", "", Types.VARCHAR);
	        	cs.setObject("P_ANIO_CAMPANIA", pAnioCampania, Types.VARCHAR);
	        	cs.setObject("P_CAMPANIA", pAnio, Types.VARCHAR);
	        	cs.setObject("P_REGISTRO", "", Types.VARCHAR);
	        	cs.setObject("P_NOMBRE_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_CVERUTA", "", Types.VARCHAR);
	        	cs.setObject("P_CVEORDEN", "", Types.VARCHAR);
	        	cs.setObject("P_CODIGO_BARRAS", "", Types.VARCHAR);
	        	cs.setObject("P_ZONA", pZona, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Campania data = new Campania();
	            	data.setIdCampania(rst.getInt(1));
	            	data.setAnioCampania((rst.getInt(2)==0)?0:rst.getInt(2));
	            	data.setCampania((rst.getInt(3)==0)?0:rst.getInt(3));
	            	data.setFechaInicio((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setFechaFin((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setLastUpdTs((rst.getString(6)==null)?"":rst.getString(6));
	            	data.setFechaActualizado((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setUsuarioActualiza((rst.getString(8)==null)?"":rst.getString(8));
	            	data.setIdZona((rst.getInt(9)==0)?0:rst.getInt(9));
	            	data.setDescZona((rst.getString(10)==null)?"":rst.getString(10));
	            	arrData.add(data); 
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M2", "Surgió un error al obtener los datos de Campania.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Campania[])arrData.toArray(new Campania[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Division de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto Division
	 */
	public Division[] getDivisionesExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Division> arrData = new ArrayList<Division>();
		v_operacion = "3";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Division data = new Division();
	            	data.setIdDivision(rst.getInt(1));
	            	data.setNombre((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setAreaGeografica((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setAdmor((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setAsistente((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setLastupd_ts((rst.getString(6)==null)?"":rst.getString(6));
	            	data.setFechaActualizado((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setUsuarioActualiza((rst.getString(8)==null)?"":rst.getString(8));
	            	arrData.add(data); 
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M3", "Surgió un error al obtener los datos de División.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Division[])arrData.toArray(new Division[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Zonas de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto Zona
	 */
	public Zona[] getZonasExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Zona> arrData = new ArrayList<Zona>();
		v_operacion = "4";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Zona data = new Zona();
	            	data.setIdZona(rst.getString(1));
	            	data.setZona((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setAnioCampaniaActual((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setCampania((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setLastUpdTs((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setFechaActualizado((rst.getString(6)==null)?"":rst.getString(6));
	            	data.setUsuarioActualiza((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setIdLDC((rst.getString(8)==null)?"":rst.getString(8));
	            	data.setIdDivision((rst.getString(9)==null)?"":rst.getString(9));
	            	data.setTipoZona((rst.getString(10)==null)?"":rst.getString(10));
	            	data.setNombreGerenteZonal((rst.getString(11)==null)?"":rst.getString(11));
	            	data.setDescDivision((rst.getString(12)==null)?"":rst.getString(12));
	            	data.setDescLDC((rst.getString(13)==null)?"":rst.getString(13));
	            	arrData.add(data); 
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M4", "Surgió un error al obtener los datos de Zonas.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Zona[])arrData.toArray(new Zona[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Representantes de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto Representante
	 */
	public Representante[] getRepresentantesExistentes(String pRegistro, String pCampania,
			String pAnioCampania, String pZona, String pRuta, int pagina, int tamPagina){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Representante> arrData = new ArrayList<Representante>();
		v_operacion = "5";
		int inicio = ((pagina * tamPagina) - tamPagina) + 1;
		int fin = pagina * tamPagina;
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_TRABAJA", "", Types.VARCHAR);
	        	cs.setObject("P_PAGO_EFECTIVO", "", Types.VARCHAR);
	        	cs.setObject("P_ANIO_CAMPANIA", pAnioCampania, Types.VARCHAR);
	        	cs.setObject("P_CAMPANIA", pCampania, Types.VARCHAR);
	        	cs.setObject("P_REGISTRO", pRegistro, Types.VARCHAR);
	        	cs.setObject("P_NOMBRE_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_CVERUTA", pRuta, Types.VARCHAR);
	        	cs.setObject("P_CVEORDEN", "", Types.VARCHAR);
	        	cs.setObject("P_CODIGO_BARRAS", "", Types.VARCHAR);
	        	cs.setObject("P_ZONA", pZona, Types.VARCHAR);
	        	cs.setObject("p_inicio", inicio, Types.INTEGER);
	        	cs.setObject("p_fin", fin, Types.INTEGER);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Representante data = new Representante();
	            	data.setIdRepresentante(rst.getInt(1));
	            	data.setIdZona((rst.getInt(2)==0)?0:rst.getInt(2));
	            	data.setRegistro((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setNombre((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setDomicilio((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setEstado((rst.getString(6)==null)?"":rst.getString(6));
	            	data.setEstatus((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setMontoActual((rst.getInt(8)==0)?0:rst.getInt(8));
	            	data.setMontoPrevio((rst.getInt(9)==0)?0:rst.getInt(9));
	            	data.setTelefono((rst.getString(10)==null)?"":rst.getString(10));
	            	data.setCoPostal((rst.getString(11)==null)?"":rst.getString(11));
	            	data.setPagoEfectivo((rst.getByte(12)==0)?0:rst.getByte(12));
	            	data.setTrabaja((rst.getByte(13)==0)?0:rst.getByte(13));
	            	data.setLastUpdTs((rst.getString(14)==null)?"":rst.getString(14));
	            	data.setFechaActualizado((rst.getString(15)==null)?"":rst.getString(15));
	            	data.setUsuarioActualiza((rst.getString(16)==null)?"":rst.getString(16));
	            	data.setPoblacion((rst.getString(17)==null)?"":rst.getString(17));
	            	data.setDescZona((rst.getString(18)==null)?"":rst.getString(18));
	            	data.setCveRuta((rst.getString(19)==null)?"":rst.getString(19));
	            	if(rst.getString(7).equals("A")){
	            		data.setIdEstatus(1);
	            		data.setEstatus("Activo");
	            	}else{
	            		data.setIdEstatus(2);
	            		data.setEstatus("Inactivo");
	            	}
	            	if(rst.getByte(12) == 0){
	            		data.setSiPagoEfectivo("No");
	            	}else{
	            		data.setSiPagoEfectivo("Si");
	            	}
	            	if(rst.getByte(13) == 0){
	            		data.setSiTrabaja("No");
	            	}else{
	            		data.setSiTrabaja("Si");
	            	}
	            	if (rst.getByte("TIENE_DOMICILIO_INCORRECTO") == 1) {
	            		data.setDomIncorrecto(true);
	            	} else {
	            		data.setDomIncorrecto(false);
	            	}
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M5", "Surgió un error al obtener los datos de Representante.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Representante[])arrData.toArray(new Representante[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Tipos de Pago de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto TipoPago
	 */
	public TipoPago[] getTiposPagoExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<TipoPago> arrData = new ArrayList<TipoPago>();
		v_operacion = "6";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	TipoPago data = new TipoPago();
	            	data.setIdTipoPuesto(rst.getInt(1));
	            	data.setDescripcion((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setFechaActualizado((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setUsuarioActualiza((rst.getString(4)==null)?"":rst.getString(4));
	            	arrData.add(data); 
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M6", "Surgió un error al obtener los datos de Tipos de Pagos.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (TipoPago[])arrData.toArray(new TipoPago[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Ordenes de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto Orden
	 */
	public Orden[] getOrdenesExistentes(String pRegistro, String pAnioCampania, String pCampania, String pRuta, String pOrden){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Orden> arrData = new ArrayList<Orden>();
		v_operacion = "7";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?,?,?,?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_TRABAJA", "", Types.VARCHAR);
	        	cs.setObject("P_PAGO_EFECTIVO", "", Types.VARCHAR);
	        	cs.setObject("P_ANIO_CAMPANIA", pAnioCampania, Types.VARCHAR);
	        	cs.setObject("P_CAMPANIA", pCampania, Types.VARCHAR);
	        	cs.setObject("P_REGISTRO", pRegistro, Types.VARCHAR);
	        	cs.setObject("P_NOMBRE_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_CVERUTA", pRuta, Types.VARCHAR);
	        	cs.setObject("P_CVEORDEN", pOrden, Types.VARCHAR);
	        	cs.setObject("P_CODIGO_BARRAS", "", Types.VARCHAR);
	        	cs.setObject("P_ZONA", "", Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Orden data = new Orden();
	            	data.setIdOrden(rst.getInt(1));
	            	data.setIdRepresentante(rst.getInt(2));
	            	data.setIdLDC((rst.getInt(3)==0)?0:rst.getInt(3));
	            	data.setNoCajas((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setLastupd_ts((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setIdPrimeraOrden((rst.getInt(6)==0)?0:rst.getInt(6)); //ID
	            	data.setCveOrden((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setFecha_actualizado((rst.getString(8)==null)?"":rst.getString(8));
	            	data.setUsuario_actualiza((rst.getString(9)==null)?"":rst.getString(9));
	            	data.setIdCampania((rst.getInt(10)==0)?0:rst.getInt(10));
	            	data.setIdEstatus((rst.getInt(11)==0)?0:rst.getInt(11)); //No se muestra
	            	data.setDescRepresentante((rst.getString(12)==null)?"":rst.getString(12));
	            	data.setDescLDC((rst.getString(13)==null)?"":rst.getString(13));
	            	data.setAnio((rst.getString(14)==null)?"":rst.getString(14));
	            	data.setDescCampania((rst.getString(15)==null)?"":rst.getString(15));
	            	data.setCveRuta((rst.getString(16)==null)?"":rst.getString(16));
	            	if(((rst.getInt(6)==0)?0:rst.getInt(6)) == 1){
	            		data.setDescPrimeraOrden("Si");
	            	}else{
	            		data.setDescPrimeraOrden("No");
	            	}
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M7", "Surgió un error al obtener los datos de Orden.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Orden[])arrData.toArray(new Orden[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Razones de Devolución de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto RazonesDevolucion
	 */
	public RazonesDevolucion[] getRazonesDevolucionExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<RazonesDevolucion> arrData = new ArrayList<RazonesDevolucion>();
		v_operacion = "8";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	RazonesDevolucion data = new RazonesDevolucion();
	            	data.setIdRazonDevolucion(rst.getInt(1));
	            	data.setDescripcion((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setLastupd_ts((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setFechaActualizado((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setUsuarioActualiza((rst.getString(5)==null)?"":rst.getString(5));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M8", "Surgió un error al obtener los datos de Razones de Devolución.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (RazonesDevolucion[])arrData.toArray(new RazonesDevolucion[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Tipos de Liquidación de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto TipoLiquidacion
	 */
	public TipoLiquidacion[] getTiposLiquidacionExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<TipoLiquidacion> arrData = new ArrayList<TipoLiquidacion>();
		v_operacion = "9";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	TipoLiquidacion data = new TipoLiquidacion();
	            	data.setIdTipoLiquidacion(rst.getInt(1));
	            	data.setClave((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setDescripcion((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setLastupd_ts((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setFechaActualizado((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setUsuarioActualiza((rst.getString(6)==null)?"":rst.getString(6));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M9", "Surgió un error al obtener los datos de Tipos de Liquidación.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (TipoLiquidacion[])arrData.toArray(new TipoLiquidacion[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Estados de Orden de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto EstadoOrden
	 */
	public EstadoOrden[] getEstadosOrdenExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<EstadoOrden> arrData = new ArrayList<EstadoOrden>();
		v_operacion = "10";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	EstadoOrden data = new EstadoOrden();
	            	data.setIdEdoOrden(rst.getInt(1));
	            	data.setTipoDlv((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setDescripcion((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setLastupd_ts((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setFechaActualizado((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setUsuarioActualiza((rst.getString(6)==null)?"":rst.getString(6));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M10", "Surgió un error al obtener los datos de Estados de Orden.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (EstadoOrden[])arrData.toArray(new EstadoOrden[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Tipo de Siniestro de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto TipoSiniestro
	 */
	public TipoSiniestro[] getTiposSiniestroExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<TipoSiniestro> arrData = new ArrayList<TipoSiniestro>();
		v_operacion = "11";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	TipoSiniestro data = new TipoSiniestro();
	            	data.setIdTipoSiniestro(rst.getInt(1));
	            	data.setClave((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setDescripcion((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setLastupd_ts((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setFechaActualizado((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setUsuarioActualiza((rst.getString(6)==null)?"":rst.getString(6));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M11", "Surgió un error al obtener los datos de Tipo de Siniestro.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (TipoSiniestro[])arrData.toArray(new TipoSiniestro[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Unitarios de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto Unitarios
	 */
	public Unitarios[] getUnitariosExistentes(String pCampania, String pAnioCampania, String pOrden, String pCodigo){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Unitarios> arrData = new ArrayList<Unitarios>();
		v_operacion = "12";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?,?,?,?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_TRABAJA", "", Types.VARCHAR);
	        	cs.setObject("P_PAGO_EFECTIVO", "", Types.VARCHAR);
	        	cs.setObject("P_ANIO_CAMPANIA", pAnioCampania, Types.VARCHAR);
	        	cs.setObject("P_CAMPANIA", pCampania, Types.VARCHAR);
	        	cs.setObject("P_REGISTRO", "", Types.VARCHAR);
	        	cs.setObject("P_NOMBRE_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_CVERUTA", "", Types.VARCHAR);
	        	cs.setObject("P_CVEORDEN", pOrden, Types.VARCHAR);
	        	cs.setObject("P_CODIGO_BARRAS", pCodigo, Types.VARCHAR);
	        	cs.setObject("P_ZONA", "", Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Unitarios data = new Unitarios();
	            	data.setOrden((rst.getString(1)==null)?"":rst.getString(1));
	            	data.setCodigo((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setDescripcion((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setClave((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setAnio((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setCampania((rst.getString(6)==null)?"":rst.getString(6));
	            	data.setFechaActualizado((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setUsuarioActualiza((rst.getString(8)==null)?"":rst.getString(8));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M12", "Surgió un error al obtener los datos de Unitarios.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Unitarios[])arrData.toArray(new Unitarios[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Premios de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto Premios
	 */
	public Premios[] getPremiosExistentes(String pCampania, String pAnioCampania, String pOrden, String pCodigo){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Premios> arrData = new ArrayList<Premios>();
		v_operacion = "13";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?,?,?,?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_TRABAJA", "", Types.VARCHAR);
	        	cs.setObject("P_PAGO_EFECTIVO", "", Types.VARCHAR);
	        	cs.setObject("P_ANIO_CAMPANIA", pAnioCampania, Types.VARCHAR);
	        	cs.setObject("P_CAMPANIA", pCampania, Types.VARCHAR);
	        	cs.setObject("P_REGISTRO", "", Types.VARCHAR);
	        	cs.setObject("P_NOMBRE_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_CVERUTA", "", Types.VARCHAR);
	        	cs.setObject("P_CVEORDEN", pOrden, Types.VARCHAR);
	        	cs.setObject("P_CODIGO_BARRAS", pCodigo, Types.VARCHAR);
	        	cs.setObject("P_ZONA", "", Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Premios data = new Premios();
	            	data.setClaveOrden((rst.getString(1)==null)?"":rst.getString(1));
	            	data.setCode((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setDescripcion((rst.getString(3)==null)?"":rst.getString(3));
	            	data.setRegistro((rst.getString(4)==null)?"":rst.getString(4));
	            	data.setAnio((rst.getString(5)==null)?"":rst.getString(5));
	            	data.setCampania((rst.getString(6)==null)?"":rst.getString(6));
	            	data.setFechaActualizado((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setUsuarioActualiza((rst.getString(8)==null)?"":rst.getString(8));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M13", "Surgió un error al obtener los datos de Premios.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Premios[])arrData.toArray(new Premios[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Programación de Reparto de la BD
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Objeto ProgramacionReparto
	 */
	public ProgramacionReparto[] getProgramacionRepartoExistentes(String pCampania, String pAnio, String pZona){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<ProgramacionReparto> arrData = new ArrayList<ProgramacionReparto>();
		v_operacion = "14";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?,?,?,?,?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_TRABAJA", "", Types.VARCHAR);
	        	cs.setObject("P_PAGO_EFECTIVO", "", Types.VARCHAR);
	        	cs.setObject("P_ANIO_CAMPANIA", pAnio, Types.VARCHAR);
	        	cs.setObject("P_CAMPANIA", pCampania, Types.VARCHAR);
	        	cs.setObject("P_REGISTRO", "", Types.VARCHAR);
	        	cs.setObject("P_NOMBRE_REPRESENTANTE", "", Types.VARCHAR);
	        	cs.setObject("P_CVERUTA", "", Types.VARCHAR);
	        	cs.setObject("P_CVEORDEN", "", Types.VARCHAR);
	        	cs.setObject("P_CODIGO_BARRAS", "", Types.VARCHAR);
	        	cs.setObject("P_ZONA", pZona, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	ProgramacionReparto data = new ProgramacionReparto();
	            	data.setIdProgramacionReparto(rst.getInt(1));
	            	data.setIdZona(rst.getInt(2));
	            	data.setIdCampania((rst.getInt(3)==0)?0:rst.getInt(3));
	            	data.setIdLDC((rst.getInt(4)==0)?0:rst.getInt(4));
	            	data.setIdDivision((rst.getInt(5)==0)?0:rst.getInt(5));
	            	data.setNoCuartoAnio((rst.getString(6)==null)?"":rst.getString(6));
	            	data.setAnio((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setMail((rst.getString(8)==null)?"":rst.getString(8));
	            	data.setFeFacturacion((rst.getString(9)==null)?"":rst.getString(9));
	            	data.setFeLlegadaLDC((rst.getString(10)==null)?"":rst.getString(10));
	            	data.setFeDiaRepartoPrimero((rst.getString(11)==null)?"":rst.getString(11));
	            	data.setFeDiaRepartoUltimo((rst.getString(12)==null)?"":rst.getString(12));
	            	data.setFeUltimoDiaBodega((rst.getString(13)==null)?"":rst.getString(13));
	            	data.setFeCierre((rst.getString(14)==null)?"":rst.getString(14));
	            	data.setFeActualizado((rst.getString(15)==null)?"":rst.getString(15));
	            	data.setUsuarioActualiza((rst.getString(16)==null)?"":rst.getString(16));
	            	data.setDescZona((rst.getString(17)==null)?"":rst.getString(17));
	            	data.setAnioCampania((rst.getString(18)==null)?"":rst.getString(18));
	            	data.setDescCampania((rst.getString(19)==null)?"":rst.getString(19));
	            	data.setDescDivision((rst.getString(20)==null)?"":rst.getString(20));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.04", "M14", "Surgió un error al obtener los datos de Programación de Reparto.", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (ProgramacionReparto[])arrData.toArray(new ProgramacionReparto[arrData.size()]);
	}
	
	/**
	 * Actualiza el registro seleccionado del CAtalogo de REpresentante de AVON
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @param idUser
	 * @param idRepresentante
	 * @param siTrabaja
	 * @param siPagoEfectivo
	 * @return Mensaje de EXito o FALLA
	 */
	public String actualizarRepresentante(Integer idUser, Integer idRepresentante, Integer siTrabaja, Integer siPagoEfectivo,
			boolean actualizarDomIncorrecto){
		v_operacion = "15";
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?,?,?,?)}");
				cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_REPRESENTANTE", idRepresentante, Types.INTEGER);
				cs.setObject("P_PAGO_EFECTIVO", siPagoEfectivo, Types.BIT);
				cs.setObject("P_TRABAJA", siTrabaja, Types.BIT);
				cs.execute();
				if (actualizarDomIncorrecto) {
					v_operacion = "17";
					cs = con.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?,?,?,?)}");
					cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
					cs.setObject("P_ID_REPRESENTANTE", idRepresentante, Types.INTEGER);
					cs.setObject("P_PAGO_EFECTIVO", siPagoEfectivo, Types.BIT);
					cs.setObject("P_TRABAJA", siTrabaja, Types.BIT);
					cs.execute();
				}
				msg = "Se actualizó correctamente el registro.";
			}
		}catch (SQLException ex){
		//Al surgir una excepcion se guarda en el log de la BD
		Utils.GuardarLogMensajeBD("CUADMIN001.04", "M15", "Surgió un error al actualizar en la tabla Representante.", ex.getMessage(), idUser);
		msg = "Error al actualizar los datos de Representante.";
		}
		finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
} //
