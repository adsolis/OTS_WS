/**
 * Contiene los metodos necesarios para funcionalidad del CU02 - Mantenimiento de Elemento
 * @author brenda.estrada
 * @since 04/01/2012
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.datacode.avon_ots_ws.model.AsignacionRutaChofer;
import com.datacode.avon_ots_ws.model.AsignacionUnidadReparto;
import com.datacode.avon_ots_ws.model.Denominaciones;
import com.datacode.avon_ots_ws.model.Destinatario;
import com.datacode.avon_ots_ws.model.DispositivoMovil;
import com.datacode.avon_ots_ws.model.Empleado;
import com.datacode.avon_ots_ws.model.Informante;
import com.datacode.avon_ots_ws.model.LineaTransporte;
import com.datacode.avon_ots_ws.model.Marca;
import com.datacode.avon_ots_ws.model.Modelo;
import com.datacode.avon_ots_ws.model.ModuloAccion;
import com.datacode.avon_ots_ws.model.ParametrosLDC;
import com.datacode.avon_ots_ws.model.Perfil;
import com.datacode.avon_ots_ws.model.Puesto;
import com.datacode.avon_ots_ws.model.Reporte;
import com.datacode.avon_ots_ws.model.ReporteTipoDestinatario;
import com.datacode.avon_ots_ws.model.Representante;
import com.datacode.avon_ots_ws.model.RepresentantesPorRuta;
import com.datacode.avon_ots_ws.model.RutaAlterna;
import com.datacode.avon_ots_ws.model.Rutas;
import com.datacode.avon_ots_ws.model.SubBodegaAlmacen;
import com.datacode.avon_ots_ws.model.TipoDestinatario;
import com.datacode.avon_ots_ws.model.TipoInformante;
import com.datacode.avon_ots_ws.model.TipoRuta;
import com.datacode.avon_ots_ws.model.TipoUsuario;
import com.datacode.avon_ots_ws.model.UnidadReparto;
import com.datacode.avon_ots_ws.model.Usuario;

/**
 * 
 * @author brenda.estrada
 * @since 04/01/2012
 */
public class MantenimientoElementoController {
	
	//Propiedades Persistencia
	PreparedStatement ps = null;
	CallableStatement cs = null;
	ResultSet rst = null;
	String query = "";
	//Var para guardar mensaje personalizado
	String msg = "";
	String v_operacion = "";
	String v_estatus = "";
	
	//Instancas de Persistencia
	MantenimientoElementoPersistenciaInsert mtoInsert = new MantenimientoElementoPersistenciaInsert();
	MantenimientoElementoPersistenciaDelete mtoDel = new MantenimientoElementoPersistenciaDelete();
	MantenimientoElementoPersistenciaUpdate mtoUpd = new MantenimientoElementoPersistenciaUpdate();
	
	
	/* *******************************************************************************************************************************************  */
	/* ***************************************************  Cargar Listas de Valores  ************************************************************* */
	/* *******************************************************************************************************************************************  */
	
	
	/**
	 * Obtiene los valores existentes de Unidad de Reparto de la BD 
	 * @author brenda.estrada
	 * @since 04/01/02012
	 * @return Objeto tipo UnidadReparto
	 */
	public UnidadReparto[] getUnidadesRepartoExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<UnidadReparto> arrUnidades = new ArrayList<UnidadReparto>();
		v_operacion = "1";
		if(con != null){
	        try{
		
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	UnidadReparto unis = new UnidadReparto();
	            	unis.setIdUnidadReparto(rst.getInt(1));
	            	unis.setIdMarca(rst.getInt(2));
	            	unis.setDescMarca(rst.getString(3));
	            	unis.setIdLDC(rst.getInt(4));
	            	unis.setDescLDC(rst.getString(5));
	            	unis.setIdPais(rst.getInt(6));
	            	unis.setDescPais(rst.getString(7));
	            	unis.setAnio(rst.getString(8));
	            	unis.setNoSerie(rst.getString(9));
	            	unis.setNoEconomico(rst.getString(10));
	            	unis.setColor(rst.getString(11));
	            	unis.setPlacas(rst.getString(12));
	            	unis.setCapacidadNoCajas(rst.getString(13));
	            	unis.setRendimiento(rst.getString(14));
	            	unis.setIdEstatus(rst.getInt(15));
	            	unis.setEntregando(rst.getInt("ENTREGANDO"));
	            	unis.setKilometraje(Long.valueOf(String.valueOf(Double.valueOf(rst.getString("KILOMETRAJE").trim()).intValue())));
	            	if(rst.getInt(15) == 1){
	            		unis.setDescEstatus("Activo");
	            	}else{
	            		unis.setDescEstatus("Inactivo");
	            	}
	            	unis.setCodigoBarras(rst.getString(16));
	            	arrUnidades.add(unis);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M1", "Surgió un error al obtener los datos de la tabla Unidad de Reparto", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (UnidadReparto[])arrUnidades.toArray(new UnidadReparto[arrUnidades.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Marca de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Marca
	 */
	public Marca[] getMarcasExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Marca> arrMarca = new ArrayList<Marca>();
		v_operacion = "3";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	Marca unis = new Marca();
	            	unis.setIdMarca(rst.getString(1));
	            	unis.setDescMarca(rst.getString(2));
	            	arrMarca.add(unis);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M2", "Surgió un error al obtener los datos de la tabla Marca", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Marca[])arrMarca.toArray(new Marca[arrMarca.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Marca de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Marca
	 */
	public Modelo[] getModelosExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Modelo> arrModel = new ArrayList<Modelo>();
		v_operacion = "2";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	            while (rst.next()) {
	            	Modelo data = new Modelo();
	            	data.setIdModelo(rst.getInt(1));
	            	data.setDescModelo(rst.getString(2));
	            	data.setFrecuencia(rst.getString(3));
	            	data.setIdMarca(rst.getInt(4));
	            	data.setDescMarca(rst.getString(5));
	            	arrModel.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M3", "Surgió un error al obtener los datos de la tabla Modelo", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (Modelo[])arrModel.toArray(new Modelo[arrModel.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Asignacion de Unidad de Reparto de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Marca
	 */
	public AsignacionUnidadReparto[] getAsignacionesUnidadRepartoExistentes(String noSerie, String asignada, String denegada){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<AsignacionUnidadReparto> arrAsigUnidadReparto = new ArrayList<AsignacionUnidadReparto>();
		v_operacion = "4";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_PERFIL", null, Types.VARCHAR);
	        	cs.setObject("P_NOSERIE", noSerie, Types.VARCHAR);
	        	cs.setObject("P_FASIGNADA", asignada, Types.VARCHAR);
	        	cs.setObject("P_FDENEGADA", denegada, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	              
	            while (rst.next()) {
	            	AsignacionUnidadReparto data = new AsignacionUnidadReparto();
	            	data.setIdAsignacionUnidadReparto(rst.getInt(1));
	            	data.setIdEmpleado(rst.getInt(2));		data.setDescEmpleado(rst.getString(3));
	            	data.setIdUnidadReparto(rst.getInt(4));	data.setDescUnidadReparto(rst.getString(5));
	            	data.setIdPais(rst.getInt(6));			data.setDescPais(rst.getString(7));
	            	data.setIdLDC(rst.getInt(8));			data.setDescLDC(rst.getString(9));
	            	data.setNoSerie(rst.getString(10));		data.setfAsignada(rst.getString(11));
	            	data.setfDenegada((rst.getString(12)==null)?"":rst.getString(12));	data.setIdEstatus(rst.getInt(13));	
	            	if(rst.getInt(13) == 1){
	            		data.setDescEstatus("Activo");
	            	}else{
	            		data.setDescEstatus("Inactivo");
	            	}
	            	arrAsigUnidadReparto.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M4", "Surgió un error al obtener los datos de la tabla Asignacion Unidad Reparto", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (AsignacionUnidadReparto[])arrAsigUnidadReparto.toArray(new AsignacionUnidadReparto[arrAsigUnidadReparto.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Asignacion de Unidad de Reparto de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Marca
	 */
	public Rutas[] getObtenerRutasExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Rutas> arrRutas = new ArrayList<Rutas>();
		v_operacion = "5";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	              
	            while (rst.next()) {
	            	Rutas ruts = new Rutas();
	            	ruts.setIdRuta(rst.getString(1));
	            	ruts.setIdPais((rst.getString(2)==null)?"":rst.getString(2));
	            	ruts.setDescPais((rst.getString(3)==null)?"":rst.getString(3));
	            	ruts.setIdTipoRuta((rst.getString(4)==null)?"":rst.getString(4));
	            	ruts.setDescTipoRuta((rst.getString(5)==null)?"":rst.getString(5));
	            	ruts.setIdZona((rst.getString(6)==null)?"":rst.getString(6));
	            	ruts.setDescZona((rst.getString(7)==null)?"":rst.getString(7));
	            	ruts.setIdLdc((rst.getString(8)==null)?"":rst.getString(8));
	            	ruts.setDescLdc((rst.getString(9)==null)?"":rst.getString(9));
	            	ruts.setCveRuta(rst.getString(10));
	            	ruts.setDescRuta((rst.getString(11)==null)?"":rst.getString(11));
	            	ruts.setPoblacionColonia((rst.getString(12)==null)?"":rst.getString(12));
	            	ruts.setKmPromedio((rst.getDouble(13)==0)?0:rst.getDouble(13));
	            	ruts.setNoPromedioOrdenes((rst.getInt(14)==0)?0:rst.getInt(14));
	            	ruts.setTiempoPromEfectivoReparto((rst.getString(15)==null)?"":rst.getString(15));
	            	ruts.setTiempoPromTotalReparto((rst.getString(16)==null)?"":rst.getString(16));
	            	if(rst.getString(17)!=null){
	            		ruts.setTipoRiesgo((rst.getString(17)==null)?"":rst.getString(17));
	            		if(rst.getString(17).equals("Alto")){
	            			ruts.setIdTipoRiesgo(1);
	            		}else if(rst.getString(17).equals("Bajo")) {
	            			ruts.setIdTipoRiesgo(2);
	            		}else{
	            			ruts.setIdTipoRiesgo(3);
	            		}
	            	}else{
	            		ruts.setIdTipoRiesgo(0);
	            	}
	            	ruts.setDiaReparto((rst.getString(18)== null)?0:rst.getInt(18));
	            	ruts.setActivoReparto((rst.getString(19)==null)?false:rst.getBoolean(19));
	            	ruts.setIdCampaniaReparoSinHH((rst.getInt(20)==0)?0:rst.getInt(20));
	            	arrRutas.add(ruts);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M30", "Surgió un error al obtener los datos de la tabla Rutas.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Rutas[])arrRutas.toArray(new Rutas[arrRutas.size()]);
	}
	
	/**
	 * Obtiene los valores existentes de Tipo de Ruta de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Marca
	 */
	public TipoRuta[] getTiposRutaExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<TipoRuta> arrTipoRuta = new ArrayList<TipoRuta>();
		v_operacion = "6";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	            
	            while (rst.next()) {
	            	TipoRuta data = new TipoRuta();
	            	data.setIdTipoRuta(rst.getInt(1));
	            	data.setClave(rst.getString(2));		data.setDescripcion(rst.getString(3));
	            	data.setIdPais(rst.getInt(4));			data.setDescPais(rst.getString(5));
	            	data.setIdLDC(rst.getInt(6));			data.setDescLDC(rst.getString(7));
	            	arrTipoRuta.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M5", "Surgió un error al obtener los datos de la tabla Tipo Ruta", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (TipoRuta[])arrTipoRuta.toArray(new TipoRuta[arrTipoRuta.size()]);
	}
	/**
	 * Obtiene los valores existentes de Representante por Ruta de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Marca
	 */
	public RepresentantesPorRuta[] getRepresentantesPorRutaExistentes(String pRuta, String pRepresentante, String pZona){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<RepresentantesPorRuta> arrRepRuta = new ArrayList<RepresentantesPorRuta>();
		v_operacion = "7";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_PERFIL", null, Types.VARCHAR);
	        	cs.setObject("P_NOSERIE", null, Types.VARCHAR);
	        	cs.setObject("P_FASIGNADA", null, Types.VARCHAR);
	        	cs.setObject("P_FDENEGADA", null, Types.VARCHAR);
	        	cs.setObject("P_DESCRUTA", pRuta, Types.VARCHAR);
	        	cs.setObject("P_NOMBRE_REPRESENTANTE", pRepresentante, Types.VARCHAR);
	        	cs.setObject("P_DESCZONA", pZona, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	RepresentantesPorRuta data = new RepresentantesPorRuta();
	            	data.setIdRepresentanteRuta(rst.getInt(1));
	            	data.setIdRuta(rst.getInt(2));			data.setDescRuta(rst.getString(3));
	            	data.setIdRepresentante(rst.getInt(4));	data.setDescRepresentante(rst.getString(5));
	            	data.setfAlta(rst.getString(6));		data.setfBaja((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setSeqEntregaAnterior((rst.getString(8)==null)?"":rst.getString(8));	
	            	data.setSeqEntregaReciente((rst.getString(9)==null)?"":rst.getString(9));
	            	data.setIdZona((rst.getString(10)==null)?"":rst.getString(10));
	            	data.setDescZona((rst.getString(11)==null)?"":rst.getString(11));
	            	arrRepRuta.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M6", "Surgió un error al obtener los datos de Representante por Ruta", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (RepresentantesPorRuta[])arrRepRuta.toArray(new RepresentantesPorRuta[arrRepRuta.size()]);
	}
	/**
	 * Obtiene los valores existentes de Asignaciones Ruta Chofer de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Marca
	 */
	public AsignacionRutaChofer[] getAsignacionesRutaChoferExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<AsignacionRutaChofer> arrAsigRuta = new ArrayList<AsignacionRutaChofer>();
		v_operacion = "8";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	       
	            while (rst.next()) {
	            	AsignacionRutaChofer data = new AsignacionRutaChofer();
	            	data.setIdAsignacionRutaChofer(rst.getInt(1));
	            	data.setIdRuta(rst.getInt(2));			data.setDescRuta(rst.getString(3));
	            	data.setIdPais(rst.getInt(4));			data.setDescPais(rst.getString(5));
	            	data.setIdZona(rst.getInt(6));			data.setDescZona(rst.getString(7));
	            	data.setIdLDC(rst.getInt(8));			data.setDescLDC(rst.getString(9));
	            	data.setIdEmpleado(rst.getInt(10));		data.setDescEmpleado(rst.getString(11));
	            	data.setfAsignada(rst.getString(12));		data.setfDenegada((rst.getString(13)==null)?"":rst.getString(13));
	            	if(rst.getString(14) == null){
	            		data.setTipoAsignacion("Principal");
	            		data.setIdTipoAsignacion(1);
	            	}else{
	            		data.setTipoAsignacion(rst.getString(14));
		            	if(rst.getString(14).equals("Principal")){
		            		data.setIdTipoAsignacion(1);
		            	}else{
		            		data.setIdTipoAsignacion(2);
		            	}
	            	}
	            	arrAsigRuta.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M7", "Surgió un error al obtener los datos de Asignaciones Ruta Chofer.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (AsignacionRutaChofer[])arrAsigRuta.toArray(new AsignacionRutaChofer[arrAsigRuta.size()]);
	}
	/**
	 * Obtiene los valores existentes de Dispositivo Movil - HandHeld de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo DispositivoMovil
	 */
	public DispositivoMovil[] getDispositivosMovilesExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<DispositivoMovil> arrDispositivo = new ArrayList<DispositivoMovil>();
		v_operacion = "9";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	        
	            while (rst.next()) {
	            	DispositivoMovil data = new DispositivoMovil();
	            	data.setIdDispositivoMovil(rst.getInt(1));
	            	data.setIdLDC(rst.getInt(2));			data.setDescLDC(rst.getString(3));
	            	data.setIdPais(rst.getInt(4));			data.setDescPais(rst.getString(5));
	            	data.setNoSerie((rst.getString(6)==null)?"":rst.getString(6));		data.setMarca((rst.getString(7)==null)?"":rst.getString(7));
	            	data.setModelo((rst.getString(8)==null)?"":rst.getString(8));		data.setMacAdress(rst.getString(9));
	            	data.setDireccionIP(rst.getString(10)); data.setIdEstatus(rst.getInt(11));
	            	if(rst.getInt(11) == 1){
	            		data.setDescEstatus("Activo");
	            	}else{
	            		data.setDescEstatus("Inactivo");
	            	}
	            	arrDispositivo.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M8", "Surgió un error al obtener los datos de Dispositivo Movil.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (DispositivoMovil[])arrDispositivo.toArray(new DispositivoMovil[arrDispositivo.size()]);
	}
	/**
	 * Obtiene los valores existentes de Empleado de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Empleado
	 */
	public Empleado[] getEmpleadosExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Empleado> arrData = new ArrayList<Empleado>();
		v_operacion = "10";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	            
	            while (rst.next()) {
	            	Empleado data = new Empleado();
	            	data.setIdEmpleado(rst.getInt(1));
	            	data.setIdPuesto(rst.getInt(2));		data.setDescPuesto(rst.getString(3));
	            	data.setNombre(rst.getString(4));		data.setDomicilio(rst.getString(5));
	            	data.setApPaterno(rst.getString(6));	data.setApMaterno(rst.getString(7));
	            	data.setFeNacimiento((rst.getString(8)==null)?"":rst.getString(8));	
	            	if(rst.getString(9) == null){
	            		data.setSexo("");
	            		data.setIdSexo(0);
	            	}else{
	            		data.setSexo(rst.getString(9));
		            	if(rst.getString(9).equals("M")){
		            		data.setIdSexo(1);
		            	}else{
		            		data.setIdSexo(2);
		            	}
	            	}
	            	if(rst.getString(10) == null){
	            		data.setIdEdoCivil(0);
	            	}else{
		            	data.setEdoCivil(rst.getString(10));
		            	if(rst.getString(10).equals("S")){
		            		data.setIdEdoCivil(1);
		            	}else{
		            		data.setIdEdoCivil(2);
		            	}
	            	}
	            	data.setRfc((rst.getString(11)==null)?"":rst.getString(11));	data.setFeIngreso(rst.getString(12));
	            	data.setIdEstatus(rst.getInt(13));
	            	if(rst.getInt(13) == 1){
	            		data.setDescEstatus("Activo");
	            	}else{
	            		data.setDescEstatus("Inactivo");
	            	}
	            	data.setTelefono((rst.getString(14)==null)?"":rst.getString(14));	data.setMail((rst.getString(15)==null)?"":rst.getString(15));
	            	data.setIdLDC(rst.getInt(16));			data.setDescLDC(rst.getString(17));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M9", "Surgió un error al obtener los datos de Empleado.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Empleado[])arrData.toArray(new Empleado[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes de Puesto de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Puesto
	 */
	public Puesto[] getPuestosExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Puesto> arrData = new ArrayList<Puesto>();
		v_operacion = "11";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	       
	            while (rst.next()) {
	            	Puesto data = new Puesto();
	            	data.setIdPuesto(rst.getInt(1));
	            	data.setIdPais(rst.getInt(2));		data.setDescPais(rst.getString(3));
	            	data.setIdLDC(rst.getInt(4));		data.setDescLDC(rst.getString(5));
	            	data.setClave(rst.getString(6));	data.setDescripcion(rst.getString(7));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M10", "Surgió un error al obtener los datos de Puesto.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Puesto[])arrData.toArray(new Puesto[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes de Linea Transporte de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo LineaTransporte
	 */
	public LineaTransporte[] getLineasTransporteExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<LineaTransporte> arrData = new ArrayList<LineaTransporte>();
		v_operacion = "12";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	       
	            while (rst.next()) {
	            	LineaTransporte data = new LineaTransporte();
	            	data.setIdLineaTransporte(rst.getInt(1));
	            	data.setIdPais(rst.getInt(2));		data.setDescPais(rst.getString(3));
	            	data.setClave(rst.getString(4));	data.setDescripcion(rst.getString(5));
	            	data.setTelefono(rst.getString(6));	data.setDomicilio(rst.getString(7));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M11", "Surgió un error al obtener los datos de Linea Transporte.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (LineaTransporte[])arrData.toArray(new LineaTransporte[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes Informante de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Informante
	 */
	public Informante[] getInformantesExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Informante> arrData = new ArrayList<Informante>();
		v_operacion = "13";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	         
	            while (rst.next()) {
	            	Informante data = new Informante();
	            	data.setIdInformante(rst.getInt(1));
	            	data.setIdTipoInformante(rst.getInt(2));	data.setDescTipoInformante(rst.getString(3));
	            	data.setIdPais(rst.getInt(4));		data.setDescPais(rst.getString(5));
	            	data.setIdLDC(rst.getInt(6));		data.setDescLDC(rst.getString(7));
	            	data.setDescripcion(rst.getString(8));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M12", "Surgió un error al obtener los datos de Informante.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Informante[])arrData.toArray(new Informante[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes SubBodega de Almacen de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo SubBodegaAlmacen
	 */
	public SubBodegaAlmacen[] getSubBodegasDeAlmacenExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<SubBodegaAlmacen> arrData = new ArrayList<SubBodegaAlmacen>();
		v_operacion = "14";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	SubBodegaAlmacen data = new SubBodegaAlmacen();
	            	data.setIdSubbodegaAlmacen(rst.getInt(1));
	            	data.setIdZona(rst.getInt(2));			data.setDescZona(rst.getString(3));
	            	data.setIdPais(rst.getInt(4));			data.setDescPais(rst.getString(5));
	            	data.setIdLDC(rst.getInt(6));			data.setDescLDC(rst.getString(7));
	            	data.setClave(rst.getString(8));		data.setTelefono(rst.getString(9));
	            	data.setDomicilio(rst.getString(10));	data.setDescripcion(rst.getString(11));
	            	data.setIdUserResponsableSubbodega(rst.getInt(12));		data.setDescUserResponsableSubbodega(rst.getString(13));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M13", "Surgió un error al obtener los datos de SubBodega Almacen.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (SubBodegaAlmacen[])arrData.toArray(new SubBodegaAlmacen[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes Destinatario de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Destinatario
	 */
	public Destinatario[] getDestinatariosExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Destinatario> arrData = new ArrayList<Destinatario>();
		v_operacion = "15";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	         
	            while (rst.next()) {
	            	Destinatario data = new Destinatario();
	            	data.setIdDestinatario(rst.getInt(1));
	            	data.setIdPais(rst.getInt(2));			data.setDescPais(rst.getString(3));
	            	data.setIdLDC(rst.getInt(4));			data.setDescLDC(rst.getString(5));
	            	data.setClave(rst.getString(6));		data.setNombre(rst.getString(7));
	            	data.setApPaterno(rst.getString(8));	data.setApMaterno(rst.getString(9));	data.setMail((rst.getString(10)==null)?"":rst.getString(10));
	            	data.setIdTipoDestinatario((rst.getInt(11)==0)?0:rst.getInt(11));			data.setDescTipoDestinatario((rst.getString(12)==null)?"":rst.getString(12));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M14", "Surgió un error al obtener los datos de Destinatario.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Destinatario[])arrData.toArray(new Destinatario[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes Tipo de Destinatario de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo TipoDestinatario
	 */
	public TipoDestinatario[] getTipoDestinatarioExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<TipoDestinatario> arrData = new ArrayList<TipoDestinatario>();
		v_operacion = "16";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	TipoDestinatario data = new TipoDestinatario();
	            	data.setIdTipoDestinatario(rst.getInt(1));
	            	data.setIdPais(rst.getInt(2));			data.setDescPais(rst.getString(3));
	            	data.setIdLDC(rst.getInt(4));			data.setDescLDC(rst.getString(5));
	            	data.setClave(rst.getString(6));		data.setDescripcion(rst.getString(7));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M15", "Surgió un error al obtener los datos de Tipo de Destinatario.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (TipoDestinatario[])arrData.toArray(new TipoDestinatario[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes Reporte Tipo de Destinatario de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo ReporteTipoDestinatario
	 */
	public ReporteTipoDestinatario[] getReportesTipoDestinatarioExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<ReporteTipoDestinatario> arrData = new ArrayList<ReporteTipoDestinatario>();
		v_operacion = "17";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	           
	            while (rst.next()) {
	            	ReporteTipoDestinatario data = new ReporteTipoDestinatario();
	            	data.setIdReporteTipoDestinatario(rst.getInt(1));
	            	data.setIdTipoDestinatario(rst.getInt(2));		
	            	data.setDescTipoDestinatario(rst.getString(3));
	            	data.setClave(rst.getString(4));
	            	data.setIdReporte(rst.getInt("ID_REPORTE"));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M16", "Surgió un error al obtener los datos de Reporte Tipo de Destinatario.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (ReporteTipoDestinatario[])arrData.toArray(new ReporteTipoDestinatario[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes Reporte de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Reporte
	 */
	public Reporte[] getReportesExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Reporte> arrData = new ArrayList<Reporte>();
		v_operacion = "18";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	         
	            while (rst.next()) {
	            	Reporte data = new Reporte();
	            	data.setIdReporte(rst.getInt(1));
	            	data.setIdPais(rst.getInt(2));			data.setDescPais(rst.getString(3));
	            	data.setIdLDC(rst.getInt(4));			data.setDescLDC(rst.getString(5));
	            	data.setNombre(rst.getString(6));		data.setDescripcion(rst.getString(7));
	            	data.setComentario(rst.getString(8));	data.setRutaTemplate(rst.getString(9));
	            	data.setNombreTemplate(rst.getString(10));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M17", "Surgió un error al obtener los datos de Reporte.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Reporte[])arrData.toArray(new Reporte[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes de LDC de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo ParametroLDC
	 */
	public ParametrosLDC[] getParamsLDCExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<ParametrosLDC> arrLdcs = new ArrayList<ParametrosLDC>();
		v_operacion = "19";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	ParametrosLDC unis = new ParametrosLDC();
	            	unis.setIdLdc(rst.getInt(1));			unis.setDesc(rst.getString(2));
	            	unis.setRazonSocial(rst.getString(3));
	            	unis.setMail(rst.getString(4));			unis.setClave(rst.getString(5));
	            	unis.setPassword(rst.getString(6));		unis.setServerSMTP(rst.getString(7));
	            	unis.setPuerto(rst.getString(8));		unis.setTipoSeguridad(rst.getString(9));
	            	unis.setFactorMax(rst.getString(10));	unis.setFactorMin(rst.getString(11));
	            	unis.setCodigoBarrasSalida(rst.getString(12));	unis.setCodigoBarrasEntrada(rst.getString(13));
	            	unis.setPoblacion(rst.getString(14));	unis.setRegion(rst.getString(15));
	            	unis.setIdPais(rst.getInt(16));			unis.setDescPais(rst.getString(17));
	            	unis.setLogoLDC(rst.getBytes(18));		unis.setLogoAVON(rst.getBytes(19));
	            	unis.setIpServerSOS(rst.getString(20));
	            	arrLdcs.add(unis);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M18", "Surgió un error al obtener los datos de la tabla LDC", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con); 	}
	    }
		return (ParametrosLDC[])arrLdcs.toArray(new ParametrosLDC[arrLdcs.size()]);
	}
	/**
	 * Obtiene los valores existentes Usuarios de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Usuario
	 */
	public Usuario[] getUsuariosExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Usuario> arrData = new ArrayList<Usuario>();
		v_operacion = "20";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	Usuario data = new Usuario();
	            	data.setIdUsuario(rst.getInt(1));
	            	data.setIdPais(rst.getInt(2));			data.setDescPais(rst.getString(3));
	            	data.setIdPerfil(rst.getInt(4));		data.setDescPerfil(rst.getString(5));
	            	data.setIdTipoUsuario(rst.getInt(6));	data.setDescTipoUsuario(rst.getString(7));
	            	data.setIdLDC(rst.getInt(8));			data.setDescLDC(rst.getString(9));
	            	data.setUsuario(rst.getString(10));		data.setPassword(rst.getString(11));		data.setComentario(rst.getString(12));
	            	data.setIdEmpleado(rst.getInt(13));		data.setDescEmpleado(rst.getString(14));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M19", "Surgió un error al obtener los datos de Usuario.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Usuario[])arrData.toArray(new Usuario[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes Perfiles de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Perfil
	 */
	public Perfil[] getPerfilesExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Perfil> arrData = new ArrayList<Perfil>();
		v_operacion = "21";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	         
	            while (rst.next()) {
	            	Perfil data = new Perfil();
	            	data.setIdPerfil(rst.getInt(1));
	            	data.setClave(rst.getString(2));	data.setDescripcion(rst.getString(3));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M20", "Surgió un error al obtener los datos de Perfil.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Perfil[])arrData.toArray(new Perfil[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes Denominaciones de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Denominaciones
	 */
	public Denominaciones[] getDenominacionesExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Denominaciones> arrData = new ArrayList<Denominaciones>();
		v_operacion = "22";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	Denominaciones data = new Denominaciones();
	            	data.setIdDenominacion(rst.getInt(1));
	            	data.setDenominacion(rst.getString(2));		
	            	if(rst.getString(3).equals("M")){
	            		data.setTipo("Moneda");
	            		data.setIdTipo(2);
	            	}else{
	            		data.setTipo("Billete");
	            		data.setIdTipo(1);
	            	}
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M21", "Surgió un error al obtener los datos de Denominaciones.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Denominaciones[])arrData.toArray(new Denominaciones[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes Usuarios Chofer de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Usuario
	 */
	public Usuario[] getUsuariosChoferExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Usuario> arrData = new ArrayList<Usuario>();
		v_operacion = "28";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	Usuario data = new Usuario();
	            	data.setIdUsuario(rst.getInt(1));		data.setIdPais(rst.getInt(2));
	            	data.setIdPerfil(rst.getInt(3));		data.setIdTipoUsuario(rst.getInt(4));
	            	data.setIdLDC(rst.getInt(5));
	            	data.setUsuario(rst.getString(6));		data.setPassword(rst.getString(7));
	            	data.setComentario(rst.getString(8));	data.setIdEmpleado(rst.getInt(11));
	            	data.setDescEmpleado(rst.getString(12));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M23", "Surgió un error al obtener los datos de Usuarios Chofer.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Usuario[])arrData.toArray(new Usuario[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes Tipo Informante de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo TipoInformante
	 */
	public TipoInformante[] getTipoInformantesExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<TipoInformante> arrData = new ArrayList<TipoInformante>();
		v_operacion = "24";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	TipoInformante data = new TipoInformante();
	            	data.setIdTipoInformante(rst.getInt(1));	data.setDescTipoInformante(rst.getString(2));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M25", "Surgió un error al obtener los datos de Tipo Informante.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (TipoInformante[])arrData.toArray(new TipoInformante[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes Usuarios Chofer de la BD 
	 * @author brenda.estrada
	 * @since 05/01/02012
	 * @return Objeto tipo Usuario
	 */
	public Representante[] getRepresentantesExistentes(String pRuta, String pRepresentante, String pZona){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Representante> arrData = new ArrayList<Representante>();
		v_operacion = "23";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?,?,?,?,?,?,?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_PERFIL", null, Types.VARCHAR);
	        	cs.setObject("P_NOSERIE", null, Types.VARCHAR);
	        	cs.setObject("P_FASIGNADA", null, Types.VARCHAR);
	        	cs.setObject("P_FDENEGADA", null, Types.VARCHAR);
	        	cs.setObject("P_DESCRUTA", pRuta, Types.VARCHAR);
	        	cs.setObject("P_NOMBRE_REPRESENTANTE", pRepresentante, Types.VARCHAR);
	        	cs.setObject("P_DESCZONA", pZona, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	Representante data = new Representante();
	            	data.setIdRepresentante(rst.getInt(1));		data.setRegistro(rst.getString(2));
	            	data.setNombre(rst.getString(3));			data.setDomicilio(rst.getString(4));
	            	arrData.add(data);
	            }	
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M24", "Surgió un error al obtener los datos de Representantes.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Representante[])arrData.toArray(new Representante[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes TipoUsuario de la BD 
	 * @author brenda.estrada
	 * @since 13/01/02012
	 * @return Objeto tipo TipoUsuario
	 */
	public TipoUsuario[] getTipoUsuariosExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<TipoUsuario> arrData = new ArrayList<TipoUsuario>();
		v_operacion = "25";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	TipoUsuario data = new TipoUsuario();
	            	data.setIdTipoUsuario(rst.getInt(1));		data.setDescTipoUsuario(rst.getString(2));
	            	data.setCveTipoUsuario(rst.getString(3));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M26", "Surgió un error al obtener los datos de Tipo Usuario.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (TipoUsuario[])arrData.toArray(new TipoUsuario[arrData.size()]);
	}
	/**
	 * Obtiene los valores existentes Usuario Responsable de SubBodega de la BD 
	 * @author brenda.estrada
	 * @since 13/01/02012
	 * @return Objeto tipo Usuario
	 */
	public Usuario[] getUsuariosResponsableSubBodega(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Usuario> arrData = new ArrayList<Usuario>();
		v_operacion = "26";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	Usuario data = new Usuario();
	            	data.setIdUsuario(rst.getInt(1));		data.setUsuario(rst.getString(10));
	            	data.setIdEmpleado(rst.getInt(13));	data.setDescEmpleado(rst.getString(14));
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M27", "Surgió un error al obtener los datos de Usuario Responsable de Sub-Bodega.", ex.getMessage(), 0);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (Usuario[])arrData.toArray(new Usuario[arrData.size()]);
	}
	
	/**
	 * Obtiene los valores existentes ModuloAccion de la BD 
	 * @param  idPerfil
	 * @author brenda.estrada
	 * @since 30/01/02012
	 * @return Object List[ModuloAccion]
	 */
	public ModuloAccion[] getPermisosPorModulo(Integer idPerfil){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<ModuloAccion> arrData = new ArrayList<ModuloAccion>();
		v_operacion = "27";
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?,?)}");
	        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_PERFIL", idPerfil, Types.INTEGER);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
	          
	            while (rst.next()) {
	            	ModuloAccion data = new ModuloAccion();
	            	data.setIdModuloAccion(rst.getInt(1));
	            	data.setDescModuloAccion((rst.getString(2)==null)?"":rst.getString(2));
	            	data.setIdModulo(rst.getInt(3));		data.setDescModulo(rst.getString(4));
	            	data.setIdAccion(rst.getInt(5));		data.setDescAccion(rst.getString(6));
	            	data.setTienePermiso(rst.getString(7));
	            	if(rst.getString(7).equals("SI")){
	            		data.setPermiso(true);
	            	}else{
	            		data.setPermiso(false);
	            	}
	            	arrData.add(data);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M28", "Surgió un error al obtener los datos de Permisos.", ex.getMessage(), 1);
	        }
	        finally{	AccesoBD.CerrarConexion(con);	}
	    }
		return (ModuloAccion[])arrData.toArray(new ModuloAccion[arrData.size()]);
	}
	
	/*******************************************************************************
	 ********************* Metodos catalogo rutas alternas *************************
	 ******************************************************************************/
	
	/**
	 * Obtiene los valores existentes de Rutas Alternas de la BD 
	 * @author Moises Hernandez
	 * @since 09/05/2014
	 * @return Objeto tipo RutaAlterna
	 */
	public RutaAlterna[] getRutasAlternasExistentes(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<RutaAlterna> listaRutasAlternas = new ArrayList<RutaAlterna>();
		v_operacion = "1";
		if(con != null){
	        try{
		
	        	cs = con.prepareCall("{call SP_PWA_ConsultaRutasAlternas(?)}");
	        	cs.setObject("P_OPERACION", "consulta", Types.VARCHAR);
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	RutaAlterna rutaAlterna = new RutaAlterna();
	            	rutaAlterna.setIdRutaAlterna(rst.getInt("ID_RELACION_RUTA_OTS_SOS"));
	            	rutaAlterna.setIdZona(rst.getInt("ID_ZONA"));
	            	rutaAlterna.setZona(rst.getString("ZONA"));
	            	rutaAlterna.setRutaOTS(rst.getString("RUTA_OTS"));
	            	rutaAlterna.setRutaSOS(rst.getString("RUTA_SOS"));
	            	rutaAlterna.setOrden(rst.getInt("ORDEN"));
	            	listaRutasAlternas.add(rutaAlterna);
	            }
	            cs.close();
			}catch (SQLException ex){
				//Guarda en el Log
	        	Utils.GuardarLogMensajeBD("UCW049_1", "M1", "Surgió un error al obtener los datos de la tabla PW_RELACION_RUTAS_OTS_SOS", ex.getMessage(), 0);
	        }
	        finally{
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		return (RutaAlterna[])listaRutasAlternas.toArray(new RutaAlterna[listaRutasAlternas.size()]);
	}
	
	public String validaRutaAlternaDuplicada(int idRuta, int idZona, 
			String rutaOTS, String rutaSOS, int orden) {
		
		String res = "";
		int duplicado = 0;
		
		Connection con = AccesoBD.AbrirConexionOTS();
		if(con != null){
	        try{
		
	        	cs = con.prepareCall("{call SP_PWA_ConsultaRutasAlternas(?,?,?,?,?,?)}");
	        	cs.setObject("P_OPERACION", "duplZonaRutaSOS", Types.VARCHAR);
	        	cs.setObject("P_ID_RELACION_RUTA_OTS_SOS", idRuta, Types.BIGINT);
	        	cs.setObject("P_RUTA_OTS", rutaOTS, Types.VARCHAR);
	        	cs.setObject("P_RUTA_SOS", rutaSOS, Types.VARCHAR);
	        	cs.setObject("P_ORDEN", orden, Types.INTEGER);
	        	cs.setObject("P_ID_ZONA", idZona, Types.INTEGER);
	        	
	            rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
	            	duplicado = rst.getInt("TOTAL");
	            }
	            
	            cs.close();
	            

	    		if (duplicado != 0) {
	    			res = "Ruta SOS ya existe en la zona seleccionada.";
	    		} else {
	    			cs = con.prepareCall("{call SP_PWA_ConsultaRutasAlternas(?,?,?,?,?,?)}");
		        	cs.setObject("P_OPERACION", "duplOrdenRutaOTS", Types.VARCHAR);
		        	cs.setObject("P_ID_RELACION_RUTA_OTS_SOS", idRuta, Types.BIGINT);
		        	cs.setObject("P_RUTA_OTS", rutaOTS, Types.VARCHAR);
		        	cs.setObject("P_RUTA_SOS", rutaSOS, Types.VARCHAR);
		        	cs.setObject("P_ORDEN", orden, Types.INTEGER);
		        	cs.setObject("P_ID_ZONA", idZona, Types.INTEGER);
		        	
		            rst = AccesoBD.executeRetrieveResultSet(cs);
					
		            while (rst.next()) {
		            	duplicado = rst.getInt("TOTAL");
		            }
		            
		            cs.close();
		            
		            if (duplicado != 0) {
		    			res = "No se puede repetir un orden de relación para la Ruta OTS seleccionada.";
		    		}
	    		}
	            
			} catch (SQLException ex) {
	        	Utils.GuardarLogMensajeBD("UCW049_1", "M1", 
	        			"Surgió un error al obtener los datos de la tabla PW_RELACION_RUTAS_OTS_SOS", 
	        			ex.getMessage(), 0);
	        	res = "Error al validar la ruta alterna duplicada. La operación no se realizó.";
	        } finally {
	        	AccesoBD.CerrarConexion(con);
	        }
	    }
		
		return res;
		
	}

	/* *******************************************************************************************************************************************  */
	/* ***************************************************  Operaciones - Eliminar Insertar Actualizar  ************************** */
	/* *******************************************************************************************************************************************  */
	
	/* ******** Catalogo 1 - Unidad de Reparto ***************************************************************************************************  */
	/**
	 * Llama el metodo que realiza la insercion a la BD.
	 * @author brenda.estrada
	 * @since 05/01/2012
	 * @param p_IdUser - Id usuario
	 * @param p_IdUnidadReparto - Id unidad de reparto
	 * @param p_IdPais - Id Pais
	 * @param p_Idldc - Id LDC
	 * @param p_IdMarca - Id Marca
	 * @param p_Anio - Anio
	 * @param p_NoSerie - Numero de Serie
	 * @param p_NoEconomico - Numero Economico
	 * @param p_Color 
	 * @param p_Placas
	 * @param p_Capacidad - Capacidad de Carga en Numero de Cajas
	 * @param p_Rendimiento - Rendimiento en Promedio
	 * @param p_Activo - Estatus
	 * @param p_CodBarras - Codigo de Barras
	 * @return Mensaje de Exito o Falla al Insertar
	 */
	public String insertarCatalog1(Integer p_IdUser, String p_IdUnidadReparto, String p_IdPais, String p_Idldc, 
								   String p_IdMarca, String p_Anio, String p_NoSerie, String p_NoEconomico, 
								   String p_Color, String p_Placas, String p_Capacidad, String p_Rendimiento, 
								   String p_Activo, String p_CodBarras, Integer p_kilometraje, Integer p_insertarKilometraje, Integer p_entregando){
		msg = mtoInsert.insertarUnidadReparto(p_IdUser, p_IdUnidadReparto, p_IdPais, p_Idldc, p_IdMarca, 
											  p_Anio, p_NoSerie, p_NoEconomico, p_Color, p_Placas, 
											  p_Capacidad, p_Rendimiento, p_Activo, p_CodBarras, p_kilometraje, p_insertarKilometraje, p_entregando);
		return msg;
	}
	
	public String insertarRutaAlterna(int idZona, String rutaOTS,
			String rutaSOS, int orden, int idUsuario) {

		msg = validaRutaAlternaDuplicada(0, idZona, rutaOTS, rutaSOS, orden);
		
		if (msg.isEmpty()) {
			msg = mtoInsert.insertarRutaAlterna(idZona, rutaOTS, rutaSOS,
					orden, idUsuario);
		}
		
		return msg;
	}
	
	/**
	 * Llama el metodo que realiza la actualizacion a la BD.
	 * @author brenda.estrada
	 * @since 05/01/2012
	 * @param p_IdUser - Id usuario
	 * @param p_IdUnidadReparto - Id unidad de reparto
	 * @param p_IdPais - Id Pais
	 * @param p_Idldc - Id LDC
	 * @param p_IdMarca - Id Marca
	 * @param p_Anio - Anio
	 * @param p_NoSerie - Numero de Serie
	 * @param p_NoEconomico - Numero Economico
	 * @param p_Color 
	 * @param p_Placas
	 * @param p_Capacidad - Capacidad de Carga en Numero de Cajas
	 * @param p_Rendimiento - Rendimiento en Promedio
	 * @param p_Activo - Estatus
	 * @param p_CodBarras - Codigo de Barras
	 * @return Mensaje de Exito o Falla al Actualizar
	 */
	public String actualizarCatalog1(Integer p_IdUser, String p_IdUnidadReparto, String p_IdPais, String p_Idldc, 
			   String p_IdMarca, String p_Anio, String p_NoSerie, String p_NoEconomico, 
			   String p_Color, String p_Placas, String p_Capacidad, String p_Rendimiento, 
			   String p_Activo, String p_CodBarras, Integer p_kilometraje, Integer p_insertarKilometraje, Integer p_entregando){
		msg = mtoUpd.actualizarUnidadReparto(p_IdUser, p_IdUnidadReparto, p_IdPais, p_Idldc, p_IdMarca, p_Anio, 
				p_NoSerie, p_NoEconomico, p_Color, p_Placas, p_Capacidad, p_Rendimiento, p_Activo, p_CodBarras, p_kilometraje, p_insertarKilometraje, p_entregando);
		return msg;
	}
	
	public String actualizarRutaAlterna(int idRuta, int idZona, String rutaOTS,
			String rutaSOS, int orden, int idUsuario) {

		msg = validaRutaAlternaDuplicada(idRuta, idZona, rutaOTS, rutaSOS, orden);
		
		if (msg.isEmpty()) {
			msg = mtoUpd.actualizarRutaAlterna(idRuta, idZona, rutaOTS, rutaSOS,
					orden, idUsuario);
		}

		return msg;
	}
	
	/**
	 * Llama el metodo para eliminar registro de la tabla.
	 * @author brenda.estrada
	 * @since 05/01/2012
	 * @param p_IdUnidadReparto
	 * @param p_IdUser
	 * @return Mensaje de exito o falla al eliminar
	 */
	public String eliminarCatalog1(Integer p_IdUser, Integer p_IdUnidadReparto){
		msg = mtoDel.eliminarUnidadReparto(p_IdUnidadReparto, p_IdUser);
		
		return msg;
	}
	
	public String eliminarRutaAlterna(int idRuta, int idUsuario) {
		msg = mtoDel.eliminarRutaAlterna(idRuta, idUsuario);
		
		return msg;
	}
	
	/* ******** Catalogo 2 - Modelo de Camioneta  ***************************************************************************************************  */
	/**
	 * Llama el metodo de Insertar Modelo para guardar los datos obtenidos de la vista
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param p_IdUser
	 * @param p_IdModelo
	 * @param p_desc
	 * @param p_frecuencia
	 * @param p_IdMarca
	 * @return Mensaje de exito o falla al insertar
	 */
	public String insertarCatalog2(Integer p_IdUser, String p_IdModelo, String p_desc, String p_frecuencia, String p_IdMarca){
		msg = mtoInsert.insertarModelo(p_IdUser, p_IdModelo, p_desc, p_IdMarca, p_frecuencia);
		return msg;
	}
	/**
	 * Llama el metodo de Actualizar Modelo para guardar los datos obtenidos de la vista
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param p_IdUser
	 * @param p_IdModelo
	 * @param p_desc
	 * @param p_frecuencia
	 * @param p_IdMarca
	 * @return Mensaje de exito o falla al actualizar
	 */
	public String actualizarCatalog2(Integer p_IdUser, String p_IdModelo, String p_desc, String p_frecuencia, String p_IdMarca){
		msg = mtoUpd.actualizarModelo(p_IdUser, p_IdModelo, p_desc, p_IdMarca, p_frecuencia);
		return msg;
	}
	/**
	 * Llama el metodo de Eliminar Modelo para borrar registro
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param p_IdUser
	 * @param p_IdModelo
	 * @return Mensaje de exito o falla al eliminar
	 */
	public String eliminarCatalog2(Integer p_IdUser, Integer p_IdModelo){
		msg = mtoDel.eliminarModelo(p_IdModelo, p_IdUser);
		return msg;
	}
	
	/* ******** Catalogo 3 - Marca de Camioneta   ***************************************************************************************************  */
	/**
	 * Llama el metodo de Insertar Marca para guardar los datos obtenidos de la vista
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param idUser
	 * @param descMarca
	 * @return  Mensaje de exito o falla al insertar
	 */
	public String insertarCatalog3(Integer p_IdUser, String p_IdMarca, String p_DescMarca){
		msg = mtoInsert.insertarMarca(p_IdUser, p_IdMarca, p_DescMarca);
		return msg;
	}
	/**
	 * 
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param p_IdUser
	 * @param p_DescMarca
	 * @param p_IdMarca
	 * @return Mensaje de exito o falla al actualizar
	 */
	public String actualizarCatalog3(Integer p_IdUser, String p_DescMarca, String p_IdMarca){
		msg = mtoUpd.actualizarMarca(p_IdUser, p_IdMarca, p_DescMarca);
		return msg;
	}
	/**
	 * Llama el metodo de Eliminar Modelo para borrar registro
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param p_IdUser
	 * @param p_IdMarca
	 * @return Mensaje de exito o falla al eliminar
	 */
	public String eliminarCatalog3(Integer p_IdUser, Integer p_IdMarca){
		msg = mtoDel.eliminarMarca(p_IdMarca, p_IdUser);
		return msg;
	}
	
	/* ******** Catalogo 4 - Asignación de Unidad de Reparto*****************************************************************************************  */
	public String insertarCatalog4(Integer p_IdUser, String p_IdAsignacion, String p_idPais, String p_idLDC,
			  String idEmpleado, String idUnidadReparto, String noSerie, String fAsignada, 
			  String fDenegada, String idEstatus){
		msg = mtoInsert.insertarAsignacionUnidadReparto(p_IdUser, p_IdAsignacion, p_idPais, p_idLDC, idEmpleado, idUnidadReparto, noSerie, fAsignada, fDenegada, idEstatus);
		return msg;
	}
	public String actualizarCatalog4(Integer p_IdUser, String p_IdAsignacion, String p_idPais, String p_idLDC,
			  String idEmpleado, String idUnidadReparto, String noSerie, String fAsignada, 
			  String fDenegada, String idEstatus){
		msg = mtoUpd.actualizarAsignacionUnidadReparto(p_IdUser, p_IdAsignacion, p_idPais, p_idLDC, idEmpleado, idUnidadReparto, noSerie, fAsignada, fDenegada, idEstatus);
		return msg;
	}
	public String eliminarCatalog4(Integer p_IdUser, String p_IdAsignacion){
		msg = mtoDel.eliminarAsignacionUnidadReparto(p_IdUser, p_IdAsignacion);
		return msg;
	}
	
	/* ******** Catalogo 5 - Rutas  *****************************************************************************************************************  */
	public String insertarCatalog5(Integer p_IdUser, String idRuta, String p_Desc, String p_Cve, String p_IdPais, 
			String p_IdZona, String p_IdTipoRuta, String p_Idldc, Integer p_DiaReparto, Boolean p_ActivoReparto){
		msg = mtoInsert.insertarRuta(p_IdUser, idRuta, p_Desc, p_Cve, p_IdPais, p_IdZona, p_IdTipoRuta, p_Idldc, p_DiaReparto, p_ActivoReparto);
		return msg;
	}
	public String actualizarCatalog5(Integer p_IdUser, Integer idRuta, String p_Desc, String p_Cve, String p_IdPais, 
			String p_IdZona, String p_IdTipoRuta, String p_Idldc, Integer p_DiaReparto, Boolean p_ActivoReparto, Boolean p_RepartoSinHH){
		msg = mtoUpd.actualizarRuta(idRuta, p_IdUser, p_Desc, p_Cve, p_IdPais, p_IdZona, p_IdTipoRuta, p_Idldc, p_DiaReparto, p_ActivoReparto, p_RepartoSinHH);
		return msg;
	}
	public String eliminarCatalog5(Integer p_IdUser, Integer idRuta){
		msg = mtoDel.eliminarRuta(idRuta, p_IdUser);
		return msg;
	}
	
	/* ******** Catalogo 6 - Tipo de Ruta  ***********************************************************************************************************  */
	public String insertarCatalog6(Integer p_IdUser, String idTipoRuta, String idPais, String idLDC,
			String cve, String desc){
		msg = mtoInsert.insertarTipoRuta(p_IdUser, idTipoRuta, idPais, idLDC, cve, desc);
		return msg;
	}
	public String actualizarCatalog6(Integer p_IdUser, String idTipoRuta, String idPais, String idLDC,
			String cve, String desc){
		msg = mtoUpd.actualizarTipoRuta(p_IdUser, idTipoRuta, idPais, idLDC, cve, desc);
		return msg;
	}
	public String eliminarCatalog6(Integer p_IdUser, String idTipoRuta){
		msg = mtoDel.eliminarTipoRuta(p_IdUser, idTipoRuta);
		return msg;
	}
	
	/* ******** Catalogo 7 - Representantes por Ruta **************************************************************************************************  */
	public String insertarCatalog7(Integer p_IdUser, String idRepresentaRuta, String idRuta, String idRepresentante, 
		    String fAlta, String fBaja, String sAnterior, String sReciente){
		msg = mtoInsert.insertarRepresentanteRuta(p_IdUser, idRepresentaRuta, idRuta, idRepresentante, fAlta, fBaja, sAnterior, sReciente);
		return msg;
	}
	public String actualizarCatalog7(Integer p_IdUser, String idRepresentaRuta, String idRuta, String idRepresentante, 
		    String fAlta, String fBaja, String sAnterior, String sReciente){
		msg = mtoUpd.actualizarRepresentanteRuta(p_IdUser, idRepresentaRuta, idRuta, idRepresentante, fAlta, fBaja, sAnterior, sReciente);
		return msg;
	}
	public String eliminarCatalog7(Integer p_IdUser, String idRepresentaRuta){
		msg = mtoDel.eliminarRepresentanteRuta(p_IdUser, idRepresentaRuta);
		return msg;
	}
	
	/* ******** Catalogo 8 - Asignacion de Rutas  ******************************************************************************************************  */
	public String insertarCatalog8(Integer p_IdUser, String idAsignaRutaChofer, String idRuta, String idPais, 
			String idZona, String idLDC, String idEmpleado,String fAsignada, String fAlta, String fDenegada, String tipoAsignacion){
		msg = mtoInsert.insertarAsignacionRutaChofer(p_IdUser, idAsignaRutaChofer, idRuta, idPais, idZona, idLDC, idEmpleado, fAsignada, fAlta, fDenegada, tipoAsignacion);
		return msg;
	}
	public String actualizarCatalog8(Integer p_IdUser, String idAsignaRutaChofer, String idRuta, String idPais, 
			String idZona, String idLDC, String idEmpleado,String fAsignada, String fAlta, String fDenegada, String tipoAsignacion){
		msg = mtoUpd.actualizarAsignacionRutaChofer(p_IdUser, idAsignaRutaChofer, idRuta, idPais, idZona, idLDC, idEmpleado, fAsignada, fAlta, fDenegada, tipoAsignacion);
		return msg;
	}
	public String eliminarCatalog8(Integer p_IdUser, String idAsignaRutaChofer){
		msg = mtoDel.eliminarAsignacionRutaChofer(p_IdUser, idAsignaRutaChofer);
		return msg;
	}
	
	/* ******** Catalogo 9 - haNDheld - Dispositivo Movil***********************************************************************************************  */
	public String insertarCatalog9(Integer p_IdUser, String idDispositivo, String idPais, 
			String idLDC, String noSerie,String marca, String modelo, String mac, String ip, String idEstatus){
		msg = mtoInsert.insertarDispositivoMovil(p_IdUser, idDispositivo, idPais, idLDC, noSerie, marca, modelo, mac, ip, idEstatus);
		return msg;
	}
	public String actualizarCatalog9(Integer p_IdUser, String idDispositivo, String idPais, 
			String idLDC, String noSerie,String marca, String modelo, String mac, String ip, String idEstatus){
		msg = mtoUpd.actualizarDispositivoMovil(p_IdUser, idDispositivo, idPais, idLDC, noSerie, marca, modelo, mac, ip, idEstatus);
		return msg;
	}
	public String eliminarCatalog9(Integer p_IdUser, String idDispositivo){
		msg = mtoDel.eliminarDispositivoMovil(p_IdUser, idDispositivo);
		return msg;
	}
	
	/* ******** Catalogo 10 - Empleado ******************************************************************************************************************  */
	public String insertarCatalog10(Integer p_IdUser, String idEmpleado, String idPuesto, String nombre, String idPais, 
			String idLDC, String dom,String app, String apm, String nac, String sexo, String edoCivil, String rfc,
			String tel, String mail, String fIngreso, String idEstatus){
		msg = mtoInsert.insertarEmpleado(p_IdUser, idEmpleado, idPuesto, nombre, idPais, idLDC, dom, app, apm, nac, sexo, edoCivil, rfc, tel, mail, fIngreso, idEstatus);
		return msg;
	}
	public String actualizarCatalog10(Integer p_IdUser, String idEmpleado, String idPuesto, String nombre, String idPais, 
			String idLDC, String dom,String app, String apm, String nac, String sexo, String edoCivil, String rfc,
			String tel, String mail, String fIngreso, String idEstatus){
		msg = mtoUpd.actualizarEmpleado(p_IdUser, idEmpleado, idPuesto, nombre, idPais, idLDC, dom, app, apm, nac, sexo, edoCivil, rfc, tel, mail, fIngreso, idEstatus);
		return msg;
	}
	public String eliminarCatalog10(Integer p_IdUser, String idEmpleado){
		msg = mtoDel.eliminarEmpleado(p_IdUser, idEmpleado);
		return msg;
	}
	
	/* ******** Catalogo 11 - Puesto *********************************************************************************************************************  */
	public String insertarCatalog11(Integer p_IdUser, String idPuesto, String idPais, String idLDC,
			String cve, String desc){
		msg = mtoInsert.insertarPuesto(p_IdUser, idPuesto, idPais, idLDC, cve, desc);
		return msg;
	}
	public String actualizarCatalog11(Integer p_IdUser, String idPuesto, String idPais, String idLDC,
			String cve, String desc){
		msg = mtoUpd.actualizarPuesto(p_IdUser, idPuesto, idPais, idLDC, cve, desc);
		return msg;
	}
	public String eliminarCatalog11(Integer p_IdUser, String idPuesto){
		msg = mtoDel.eliminarPuesto(p_IdUser, idPuesto);
		return msg;
	}
	
	/* ******** Catalogo 12 - Linea Transporte ***********************************************************************************************************  */
	public String insertarCatalog12(Integer p_IdUser, String idLineaTransporte, String idPais, String tel,
			String cve, String desc, String dom){
		msg = mtoInsert.insertarLineaTransporte(p_IdUser, idLineaTransporte, idPais, tel, cve, desc, dom);
		return msg;
	}
	public String actualizarCatalog12(Integer p_IdUser, String idLineaTransporte, String idPais, String tel,
			String cve, String desc, String dom){
		msg = mtoUpd.actualizarLineaTransporte(p_IdUser, idLineaTransporte, idPais, tel, cve, desc, dom);
		return msg;
	}
	public String eliminarCatalog12(Integer p_IdUser, String idLineaTransporte){
		msg = mtoDel.eliminarLineaTransporte(p_IdUser, idLineaTransporte);
		return msg;
	}
	
	/* ******** Catalogo 13 - Informante ******************************************************************************************************************  */
	public String insertarCatalog13(Integer p_IdUser, String idInformante, String idPais, String idTipoInformante,
			String idLDC, String desc){
		msg = mtoInsert.insertarInformante(p_IdUser, idInformante, idPais, idTipoInformante, idLDC, desc);
		return msg;
	}
	public String actualizarCatalog13(Integer p_IdUser, String idInformante, String idPais, String idTipoInformante,
			String idLDC, String desc){
		msg = mtoUpd.actualizarInformante(p_IdUser, idInformante, idPais, idTipoInformante, idLDC, desc);
		return msg;
	}
	public String eliminarCatalog13(Integer p_IdUser, String idInformante){
		msg = mtoDel.eliminarInformante(p_IdUser, idInformante);
		return msg;
	}
	
	/* ******** Catalogo 14 - SubBodega *************** ***************************************************************************************************  */
	public String insertarCatalog14(Integer p_IdUser, String idSubbodega, String idZona, String idPais, String idLDC,
			String cve, String tel, String dom, String desc, String idUserResponsableSub){
		msg = mtoInsert.insertarSubBodegaAlmacen(p_IdUser, idSubbodega, idZona, idPais, idLDC, cve, tel, dom, desc, idUserResponsableSub);
		return msg;
	}
	public String actualizarCatalog14(Integer p_IdUser, String idSubbodega, String idZona, String idPais, String idLDC,
			String cve, String tel, String dom, String desc, String idUserResponsableSub){
		msg = mtoUpd.actualizarSubBodegaAlmacen(p_IdUser, idSubbodega, idZona, idPais, idLDC, cve, tel, dom, desc, idUserResponsableSub);
		return msg;
	}
	public String eliminarCatalog14(Integer p_IdUser, String idSubbodega){
		msg = mtoDel.eliminarSubBodegaAlmacen(p_IdUser, idSubbodega);
		return msg;
	}
	
	/* ******** Catalogo 15 - Destinatario ******************************************************************************************************************  */
	public String insertarCatalog15(Integer p_IdUser, String idDestinatario, String idPais, String idLDC,
			String cve, String nom, String app, String apm, String mail, String idTipoDestinatario){
		msg = mtoInsert.insertarDestinatario(p_IdUser, idDestinatario, idPais, idLDC, cve, nom, app, apm, mail, idTipoDestinatario);
		return msg;
	}
	public String actualizarCatalog15(Integer p_IdUser, String idDestinatario, String idPais, String idLDC,
			String cve, String nom, String app, String apm, String mail, String idTipoDestinatario){
		msg = mtoUpd.actualizarDestinatario(p_IdUser, idDestinatario, idPais, idLDC, cve, nom, app, apm, mail, idTipoDestinatario);
		return msg;
	}
	public String eliminarCatalog15(Integer p_IdUser, String idDestinatario){
		msg = mtoDel.eliminarDestinatario(p_IdUser, idDestinatario);
		return msg;
	}
	
	/* ******** Catalogo 16 - Tipo Destinatario *************************************************************************************************************  */
	public String insertarCatalog16(Integer p_IdUser, String idTipoDestinatario, 
			String idPais, String idLDC, String cve, String desc){
		msg = mtoInsert.insertarTipoDestinatario(p_IdUser, idTipoDestinatario, idPais, idLDC, cve, desc);
		return msg;
	}
	public String actualizarCatalog16(Integer p_IdUser, String idTipoDestinatario, 
			String idDestinatario, String idPais, String idLDC, String cve, String desc){
		msg = mtoUpd.actualizarTipoDestinatario(p_IdUser, idTipoDestinatario, idPais, idLDC, cve, desc); 
		return msg;
	}
	public String eliminarCatalog16(Integer p_IdUser, String idTipoDestinatario){
		msg = mtoDel.eliminarTipoDestinatario(p_IdUser, idTipoDestinatario);
		return msg;
	}
	
	/* ******** Catalogo 17 - Reporte por Tipo Destinatario **************************************************************************************************  */
	public String insertarCatalog17(Integer p_IdUser, String idReporteTipoDestinatario, String idTipoDestinatario, String cve){
		if (!validaduplicadoReporteTipoDestinatario(p_IdUser, idReporteTipoDestinatario, idTipoDestinatario, cve)) {
			msg = mtoInsert.insertarReporteTipoDestinatario(p_IdUser, idReporteTipoDestinatario, idTipoDestinatario, cve);
		} else {
			msg = "El reporte ya está asignado al destinatario, favor de validar";
		}
		return msg;
	}
	public String actualizarCatalog17(Integer p_IdUser, String idReporteTipoDestinatario, String idTipoDestinatario, String cve){
		if (!validaduplicadoReporteTipoDestinatario(p_IdUser, idReporteTipoDestinatario, idTipoDestinatario, cve)) {
			msg = mtoUpd.actualizarReporteTipoDestinatario(p_IdUser, idReporteTipoDestinatario, idTipoDestinatario, cve);
		} else {
			msg = "El reporte ya está asignado al destinatario, favor de validar";
		}
		return msg;
	}
	public String eliminarCatalog17(Integer p_IdUser, String idReporteTipoDestinatario){
		msg = mtoDel.eliminarReporteTipoDestinatario(p_IdUser, idReporteTipoDestinatario);
		return msg;
	}
	
	private boolean validaduplicadoReporteTipoDestinatario(Integer p_IdUser, String idReporteTipoDestinatario, String idTipoDestinatario, String cve){
		boolean duplicado = false;
		Connection con = AccesoBD.AbrirConexionOTS();
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoReporteTipoDestinatario(?,?,?,?,?)}");
				cs.setObject("P_OPERACION", "DUP", Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_REPORTE_TIPO_DESTINATARIO", idReporteTipoDestinatario, Types.INTEGER);
				cs.setObject("P_ID_TIPO_DESTINATARIO", idTipoDestinatario, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				rst = AccesoBD.executeRetrieveResultSet(cs);
				
	            while (rst.next()) {
					if (rst.getInt("TOTAL") != 0) {
		            	duplicado = true;
		            }
	            }
	            
	            cs.close();
			}
		} catch (SQLException ex) {
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M46", "Surgió un error al validar si el registro estaba duplicado en la tabla Reporte Tipo Destinatario.", ex.getMessage(), p_IdUser);
        } finally{	
        	AccesoBD.CerrarConexion(con);	
        }
		
		return duplicado;
	}
	
	/* ******** Catalogo 18 - Reporte - PW_REPORTE ******** ***************************************************************************************************  */
	public String insertarCatalog18(Integer p_IdUser, String idReporte, String idPais, String idLDC,
			String nom, String desc, String comm, String rutaTemplate, String nomTemplate){
		msg = mtoInsert.insertarReporte(p_IdUser, idReporte, idPais, idLDC, nom, desc, comm, rutaTemplate, nomTemplate);
		return msg;
	}
	public String actualizarCatalog18(Integer p_IdUser, String idReporte, String idPais, String idLDC,
			String nom, String desc, String comm, String rutaTemplate, String nomTemplate){
		msg = mtoUpd.actualizarReporte(p_IdUser, idReporte, idPais, idLDC, nom, desc, comm, rutaTemplate, nomTemplate);
		return msg;
	}
	public String eliminarCatalog18(Integer p_IdUser, String idReporte){
		msg = mtoDel.eliminarReporte(p_IdUser, idReporte);
		return msg;
	}
	
	/* ******** Catalogo 19 - Parametros - CEDIS **************************************************************************************************************  */
	public String insertarCatalog19(Integer p_IdUser, String idLDC, String desc, String razonSocial, 
			String mail, String cve, String pass, String serverSMTP, String  puerto,
			String tipoSeguridad, String factorMax, String factorMin, String codBarrasSal, String  codBarrasEnt,
			String poblacion, String region, String idPais, byte[] logAVON, byte[] logLDC, String serverSOS){
		msg = mtoInsert.insertarLDC(p_IdUser, idLDC, desc, razonSocial, mail, cve, pass, serverSMTP, 
				puerto, tipoSeguridad, factorMax, factorMin, codBarrasSal, codBarrasEnt, poblacion, region, idPais, logAVON, logLDC, serverSOS);
		return msg;
	}
	public String actualizarCatalog19(Integer p_IdUser, String idLDC, String desc, String razonSocial, 
			String mail, String cve, String pass, String serverSMTP, String  puerto,
			String tipoSeguridad, String factorMax, String factorMin, String codBarrasSal, String  codBarrasEnt,
			String poblacion, String region, String idPais, byte[] logAvon, byte[] logLDC, String serverSOS){
		msg = mtoUpd.actualizarLDC(p_IdUser, idLDC, desc, razonSocial, mail, cve, pass, serverSMTP, puerto, tipoSeguridad, factorMax, factorMin
				, codBarrasSal, codBarrasEnt, poblacion, region, idPais, logAvon, logLDC, serverSOS);
		return msg;
	}
	public String eliminarCatalog19(Integer p_IdUser, String idLDC){
		msg = mtoDel.eliminarLDC(p_IdUser, idLDC);
		return msg;
	}
	
	/* ******** Catalogo 20 - Usuario ************************************************************************************************************************  */
	public String insertarCatalog20(Integer p_IdUser, String idUserCatalogo, String idPais, String idPerfil,
			String idTipoUsuario, String idLDC, String user, String pass, String comm, String idEmpleado){
		msg = mtoInsert.insertarUsuario(p_IdUser, idUserCatalogo, idPais, idPerfil, idTipoUsuario, idLDC, user, pass, comm, idEmpleado);
		return msg;
	}
	public String actualizarCatalog20(Integer p_IdUser, String idUserCatalogo, String idPais, String idPerfil,
			String idTipoUsuario, String idLDC, String user, String pass, String comm, String idEmpleado){
		msg = mtoUpd.actualizarUsuario(p_IdUser, idUserCatalogo, idPais, idPerfil, idTipoUsuario, idLDC, user, pass, comm, idEmpleado);
		return msg;
	}
	public String eliminarCatalog20(Integer p_IdUser, String idUserCatalogo){
		msg = mtoDel.eliminarUsuario(p_IdUser, idUserCatalogo);
		return msg;
	}
	
	/* ******** Catalogo 21 - Perfil ***************************************************************************************************************************  */
	public String insertarCatalog21(Integer p_IdUser, String idPerfil, String cve, String desc){
		msg = mtoInsert.insertarPerfil(p_IdUser, idPerfil, cve, desc);
		return msg;
	}
	public String actualizarCatalog21(Integer p_IdUser, String idPerfil, String cve, String desc){
		msg = mtoUpd.actualizarPerfil(p_IdUser, idPerfil, cve, desc);
		return msg;
	}
	public String eliminarCatalog21(Integer p_IdUser, String idPerfil){
		msg = mtoDel.eliminarPerfil(p_IdUser, idPerfil);
		return msg;
	}
	
	/* ******** Catalogo 22 - Denominacion **********************************************************************************************************************  */
	public String insertarCatalog22(Integer p_IdUser, String idDenominacion, String denominacion, String tipo){
		msg = mtoInsert.insertarDenominaciones(p_IdUser, idDenominacion, denominacion, tipo);
		return msg;
	}
	public String actualizarCatalog22(Integer p_IdUser, String idDenominacion, String denominacion, String tipo){
		msg = mtoUpd.actualizarDenominaciones(p_IdUser, idDenominacion, denominacion, tipo);
		return msg;
	}
	public String eliminarCatalog22(Integer p_IdUser, String idDenominacion){
		msg = mtoDel.eliminarDenominaciones(p_IdUser, idDenominacion);
		return msg;
	}
	
	/* ******** Permisos por Perfil - Catalogo 21 **********************************************************************************************************************  */
	/**
	 * Ejecuta el metodo que Realiza la inserción de Permisos
	 * @author brenda.estrada
	 * @since 31/01/2012
	 * @param idUser
	 * @param idModulo
	 * @param idAccion
	 * @param idPerfil
	 * @return String [Exito o Excepcion]
	 */
	public String insertarPermisos(Integer idUser, Integer idModulo, Integer idAccion, Integer idPerfil){
		msg = mtoInsert.insertarPermisosPorPerfil(idUser, idModulo, idAccion, idPerfil);
		return msg;
	}
	
}
