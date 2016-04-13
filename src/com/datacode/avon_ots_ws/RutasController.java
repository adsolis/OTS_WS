/**
 * Metodos necesarios para fucnionalidad de catalogo de Rutas
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

import com.datacode.avon_ots_ws.model.Paises;
import com.datacode.avon_ots_ws.model.Rutas;

/**
 * @author brenda.estrada
 * @since  21-12-2011
 */
public class RutasController {
	//Instancia de clase de Utils
	Utils util = new Utils();
	//Propiedades Modelo
	private List<Rutas> rutas;
	public Rutas ruta;
	//Propiedades JSF view - Manejo de Eventos
	PreparedStatement ps = null;
	CallableStatement cs = null;
	ResultSet rst = null;
	String query = "";
	//Var para guardar mensaje personalizado
	String msg = "";
	String v_operacion = "";
	
	/**
	 * Obtiene las rutas existentes
	 * @return un array de Tipo Ruta con los datos.
	 */
	public Rutas[] getRutasExistentes(String pIdRepresentante){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<Rutas> arrRutas = new ArrayList<Rutas>();
		v_operacion = "30";
		try{
//			cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
//        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
//            rst = AccesoBD.executeRetrieveResultSet(cs);
//            
//            while (rst.next()) {
//            	Rutas ruts = new Rutas();
//            	ruts.setIdRuta(rst.getString(1));
//            	ruts.setCveRuta(rst.getString(2));
//            	ruts.setDescRuta(rst.getString(3));
//            	ruts.setFechaUpd(rst.getString(4));
//            	ruts.setUserUpd(rst.getString(5));
//            	ruts.setIdZona(rst.getString(6));
//            	ruts.setDescZona(rst.getString(7));
//            	ruts.setIdPais(rst.getString(8));
//            	ruts.setDescPais(rst.getString(9));
//            	ruts.setIdTipoRuta(rst.getString(10));
//            	ruts.setDescTipoRuta(rst.getString(11));
//            	ruts.setIdLdc(rst.getString(12));
//            	ruts.setDescLdc(rst.getString(13));
//            	arrRutas.add(ruts);
//            }
			cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?,?,?,?,?,?,?,?)}");
        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
        	cs.setObject("P_ID_PERFIL", null, Types.VARCHAR);
        	cs.setObject("P_NOSERIE", "", Types.VARCHAR);
        	cs.setObject("P_FASIGNADA", "", Types.VARCHAR);
        	cs.setObject("P_FDENEGADA", "", Types.VARCHAR);
        	cs.setObject("P_DESCRUTA", "", Types.VARCHAR);
        	cs.setObject("P_NOMBRE_REPRESENTANTE", "", Types.VARCHAR);
        	cs.setObject("P_DESCZONA", pIdRepresentante, Types.VARCHAR);
            rst = AccesoBD.executeRetrieveResultSet(cs);
              
            while (rst.next()) {
            	Rutas ruts = new Rutas();
            	ruts.setIdRuta(rst.getString(1));
            	ruts.setCveRuta(rst.getString(2));
            	ruts.setDescRuta((rst.getString(3)==null)?"":rst.getString(3));
            	ruts.setFechaUpd((rst.getString(4)==null)?"":rst.getString(4));
            	ruts.setUserUpd((rst.getString(5)==null)?"":rst.getString(5));
            	ruts.setIdZona((rst.getString(6)==null)?"":rst.getString(6));
            	ruts.setDescZona((rst.getString(7)==null)?"":rst.getString(7));
            	ruts.setIdPais((rst.getString(8)==null)?"":rst.getString(8));
            	ruts.setDescPais((rst.getString(9)==null)?"":rst.getString(9));
            	ruts.setIdTipoRuta((rst.getString(10)==null)?"":rst.getString(10));
            	ruts.setDescTipoRuta((rst.getString(11)==null)?"":rst.getString(11));
            	ruts.setIdLdc((rst.getString(12)==null)?"":rst.getString(12));
            	ruts.setDescLdc((rst.getString(13)==null)?"":rst.getString(13));
            	arrRutas.add(ruts);
            }
            cs.close();
		}catch (SQLException ex){
			//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M1", "Surgió un error al consultar los datos de Rutas por Zona.", ex.getMessage(), 0);
        }
        finally{
        	AccesoBD.CerrarConexion(con);
        }
		return (Rutas[])arrRutas.toArray(new Rutas[arrRutas.size()]);
	}
	
	/**
	 *  Array de los paises existentes
	 * @return array tipo Paises
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Paises[] getPaises(){
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList arrPaises = new ArrayList();
		v_operacion = "29";
		try{
			//Ejecuta query
			cs = con.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
        	cs.setObject("P_CATALOGO", v_operacion, Types.VARCHAR);
            rst = AccesoBD.executeRetrieveResultSet(cs);
            
            while (rst.next()) {
            	Paises paises = new Paises();
            	paises.setIdPais(rst.getString(1));
            	paises.setDescPais(rst.getString(2));
            	paises.setCvePais(rst.getString(3));
            	paises.setFechaUpd(rst.getString(4));
            	paises.setUserUpd(rst.getString(5));
            	arrPaises.add(paises);
            }
            cs.close();
		}catch (SQLException ex){
			//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M1", "Surgió un error al consultar los datos.", ex.getMessage(), 0);
        }
        finally{
        	AccesoBD.CerrarConexion(con);
        }
		return (Paises[])arrPaises.toArray(new Paises[arrPaises.size()]);
	}
	
	
	/**
	 * Realiza la inserción de datos a BD en la tabla Rutas
	 * @param p_IdUser - Id de usuario en session
	 * @param p_Desc - Descripcion de la Ruta
	 * @param p_Cve - Clave de la Ruta
	 * @param p_IdPais - Id del Pais
	 * @param p_IdZona - Id de la Zona
	 * @param p_IdTipoRuta - Id de Tipo de Ruta
	 * @param p_Idldc - Id de LDC
	 * @return Mensaje de exito o falla de inserción
	 */
	public String insertarRuta(Integer p_IdUser, String p_Desc, String p_Cve, String p_IdPais, 
			String p_IdZona, String p_IdTipoRuta, String p_Idldc, String poblacion,
			Double km, int noPromOrdenes, String tiPromEfectivo, String tiPromTotal, String tipoRiesgo, Integer p_DiaReparto, Boolean p_ActivoReparto){
		//Persistencia
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		String mensaje = null;
		Boolean v_repartoSinHH = false;
		v_operacion = "I";
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRuta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_RUTA", 0, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	        	cs.setObject("P_DESCRIPCION", p_Desc, Types.VARCHAR);
	        	cs.setObject("P_CLAVE", p_Cve, Types.VARCHAR);
	        	cs.setObject("P_ID_PAIS", p_IdPais, Types.VARCHAR);
	        	cs.setObject("P_ID_ZONA", p_IdZona, Types.VARCHAR);
	        	cs.setObject("P_ID_TIPORUTA", p_IdTipoRuta, Types.VARCHAR);
	        	cs.setObject("P_ID_LDC", p_Idldc, Types.VARCHAR);
	        	cs.setObject("P_POBLACION_COLONIA", poblacion, Types.VARCHAR);
	        	cs.setObject("P_KILOMETRAJE_PROMEDO", km, Types.DECIMAL);
	        	cs.setObject("P_NUMERO_PROMEDIO_ORDENES", noPromOrdenes, Types.INTEGER);
	        	cs.setObject("P_TIEMPO_PROMEDIO_EFECTIVO_REPARTO", tiPromEfectivo, Types.VARCHAR);
	        	cs.setObject("P_TIEMPO_PROMEDIO_TOTAL_REPARTO", tiPromTotal, Types.VARCHAR);
	        	cs.setObject("P_TIPO_RIESGO", tipoRiesgo, Types.VARCHAR);
	        	cs.setObject("P_DIA_REPARTO", p_DiaReparto, Types.INTEGER);
	        	cs.setObject("P_ACTIVO_REPARTO", p_ActivoReparto, Types.BIT);
	        	cs.setObject("P_REPARTO_SIN_HH", v_repartoSinHH, Types.BIT);
	        	cs.registerOutParameter("P_MENSAJE",Types.VARCHAR);
	        	cs.execute();
	        	
	        	msg = cs.getString("P_MENSAJE");
	        	return msg;
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M1", "Surgió un error al insertar en la tabla Rutas", ex.getMessage(), p_IdUser);
        	return "Surgió un error al insertar en la tabla Rutas";
        }
        finally{
        	AccesoBD.CerrarConexion(con);
        }
		return msg;
	}
	
	/**
	 * Realiza la actualizacion a un registro de la tabla PW_RUTAS
	 * @param p_IdRuta - Id de la Ruta Seleccionada
	 * @param p_IdUser - Id de usuario en session
	 * @param p_Desc - Descripcion de la Ruta
	 * @param p_Cve - Clave de la Ruta
	 * @param p_IdPais - Id del Pais
	 * @param p_IdZona - Id de la Zona
	 * @param p_IdTipoRuta - Id de Tipo de Ruta
	 * @param p_Idldc - Id de LDC
	 * @return Mensaje de exito o falla de actualización
	 */
	public String actualizarRuta(Integer p_IdRuta, Integer p_IdUser, String p_Desc, String p_Cve, String p_IdPais, 
			String p_IdZona, String p_IdTipoRuta, String p_Idldc, String poblacion,
			Double km, int noPromOrdenes, String tiPromEfectivo, String tiPromTotal, String tipoRiesgo, Integer p_DiaReparto, Boolean p_ActivoReparto, Boolean p_RepartoSinHH){
		//Persistencia
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		CallableStatement cs2 = null;
		v_operacion = "U";
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRuta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_RUTA", p_IdRuta, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	        	cs.setObject("P_DESCRIPCION", p_Desc, Types.VARCHAR);
	        	cs.setObject("P_CLAVE", p_Cve, Types.VARCHAR);
	        	cs.setObject("P_ID_PAIS", p_IdPais, Types.VARCHAR);
	        	cs.setObject("P_ID_ZONA", p_IdZona, Types.VARCHAR);
	        	cs.setObject("P_ID_TIPORUTA", p_IdTipoRuta, Types.VARCHAR);
	        	cs.setObject("P_ID_LDC", p_Idldc, Types.VARCHAR);
	        	cs.setObject("P_POBLACION_COLONIA", poblacion, Types.VARCHAR);
	        	cs.setObject("P_KILOMETRAJE_PROMEDO", km, Types.DECIMAL);
	        	cs.setObject("P_NUMERO_PROMEDIO_ORDENES", noPromOrdenes, Types.INTEGER);
	        	cs.setObject("P_TIEMPO_PROMEDIO_EFECTIVO_REPARTO", tiPromEfectivo, Types.VARCHAR);
	        	cs.setObject("P_TIEMPO_PROMEDIO_TOTAL_REPARTO", tiPromTotal, Types.VARCHAR);
	        	cs.setObject("P_TIPO_RIESGO", tipoRiesgo, Types.VARCHAR);
	        	cs.setObject("P_DIA_REPARTO", p_DiaReparto, Types.INTEGER);
	        	cs.setObject("P_ACTIVO_REPARTO", p_ActivoReparto, Types.BIT);
	        	cs.setObject("P_REPARTO_SIN_HH", p_RepartoSinHH, Types.BIT);
	        	cs.registerOutParameter("P_MENSAJE",Types.VARCHAR);
	        	cs.execute();
	        	
	        	msg = cs.getString("P_MENSAJE");
	        	if(p_RepartoSinHH == true){
	        		cs2 = con.prepareCall("{call SP_PW_INSERTAR_SMS_NO_RECEPCION_Y_SIN_INCIDENTE(?,?,?)}");
	        		cs2.setObject("P_ID_ZONA", p_IdZona, Types.VARCHAR);
					cs2.setObject("P_ID_RUTA", p_IdRuta, Types.INTEGER);
		        	cs2.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
		        	cs2.execute();
	        	}
	        	return msg;
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "00", "Surgió un error al actualizar el registro "+p_IdRuta+" en la tabla Rutas", ex.getMessage(), p_IdUser);
        	return "Surgió un error al actualizar el registro en la tabla Rutas";
        }
        finally{
        	AccesoBD.CerrarConexion(con);
        }
		return msg;
	}
	
	/**
	 * Eliminar registro de la tabla PW_RUTAS
	 * @param p_IdRuta - Id ruta seleccionada
	 * @param p_IdUser - Id Usuario de session
	 * @return Mensaje de exito o falla al eliminar 
	 */
	public String eliminarRuta(Integer p_IdRuta, Integer p_IdUser){
		//Persistencia
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		v_operacion = "D";
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRuta(?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_RUTA", p_IdRuta, Types.INTEGER);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	        	cs.setObject("P_DESCRIPCION", "", Types.VARCHAR);
	        	cs.setObject("P_CLAVE", "", Types.VARCHAR);
	        	cs.setObject("P_ID_PAIS", 0, Types.VARCHAR);
	        	cs.setObject("P_ID_ZONA", 0, Types.VARCHAR);
	        	cs.setObject("P_ID_TIPORUTA", 0, Types.VARCHAR);
	        	cs.setObject("P_ID_LDC", 0, Types.VARCHAR);
				//Ejecuta el procedimiento
	        	cs.execute();
	        	return msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "00", "Surgió un error al eliminar el registro "+p_IdRuta+" en la tabla Rutas", ex.getMessage(), p_IdUser);
        	return "Surgió un error al eliminar el registro en la tabla Rutas";
        }
        finally{
        	AccesoBD.CerrarConexion(con);
        }
		return msg;
	}
	
	/**
	 * 
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param idZona
	 * @param idLDC
	 * @return Rutas[]
	 *
	 */
	
	public Rutas[] getRutasPorLDC(Integer idZona,Integer idLDC, String tipoCasoUso,Integer campania,Integer idUsuario){
		
		Connection connection = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		List<Rutas> arrRutas = new ArrayList<Rutas>();
		String tipoCU = tipoCasoUso;
		Rutas ruta = null;
		
		if(connection != null){
			try{
				cs = connection.prepareCall("{call SP_PW_RUTAS(?,?,?,?)}");
				cs.setObject("P_TIPO_CU",tipoCU, Types.VARCHAR);
				cs.setObject("P_ID_ZONA",idZona, Types.INTEGER); 
				cs.setObject("P_ID_LDC",idLDC, Types.INTEGER);
				cs.setObject("P_ID_CAMPANIA",campania,Types.INTEGER);
								
				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	while(rs.next())
	        	{
	        		ruta = new Rutas();
                    ruta.setIdRuta(rs.getString("ID_RUTA"));	   
	        		ruta.setCveRuta(rs.getString("CLAVE_RUTA"));
	        		ruta.setDescTipoRuta(rs.getString("TIPO"));
                    arrRutas.add(ruta);
	        	}
	        	rs.close();
			}
			catch (SQLException ex){
				Utils.GuardarLogMensajeBD(tipoCasoUso, "M3", "Error al seleccionar rutas existentes", ex.getMessage(),idUsuario);
			}
			finally{
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return (Rutas[])arrRutas.toArray(new Rutas[arrRutas.size()]);
	}
		
	/**
	 * @return the rutas
	 */
	public List<Rutas> getRutas() {
		return rutas;
	}


	/**
	 * @param rutas the rutas to set
	 */
	public void setRutas(List<Rutas> rutas) {
		this.rutas = rutas;
	}


	/**
	 * @return the ruta
	 */
	public Rutas getRuta() {
		return ruta;
	}


	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(Rutas ruta) {
		this.ruta = ruta;
	}

}
