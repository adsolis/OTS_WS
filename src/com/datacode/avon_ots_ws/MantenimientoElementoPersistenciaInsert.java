/**
 * Metodos de Insercion a la BD para Catalogos
 * @author brenda.estrada
 * @since 05/01/2012
 */
package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Contiene los metodos de Insercion a la BD para CATALOGOS
 * @author brenda.estrada
 * @since 05/01/2012
 * @ejb.persistence Insert
 */
public class MantenimientoElementoPersistenciaInsert {
	//Propiedades Persistencia
	PreparedStatement ps = null;
	ResultSet rst = null;
	String query = "";
	Connection con = AccesoBD.AbrirConexionOTS();
	CallableStatement cs = null;
	//Var para guardar mensaje personalizado
	String msg = ""; //Claves de MSG del 31 al 60
	String v_operacion = "I";
	
	
	/**
	 * Realiza la inserción de datos a BD en la tabla Unidad de Reparto
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
	public String insertarUnidadReparto(Integer p_IdUser, String p_IdUnidadReparto, String p_IdPais, String p_Idldc, String p_IdMarca, String p_Anio,  
		   String p_NoSerie, String p_NoEconomico, String p_Color, String p_Placas, String p_Capacidad, String p_Rendimiento, String p_Activo, String p_CodBarras, Integer p_kilometraje, Integer p_insertarKilometraje, Integer p_entregando){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoUnidadReparto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_UNIDADREPARTO", 0, Types.INTEGER);
	        	cs.setObject("P_ID_PAIS", p_IdPais, Types.INTEGER);
	        	cs.setObject("P_ID_LDC", p_Idldc, Types.INTEGER);
	        	cs.setObject("P_ID_MARCA", p_IdMarca, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	        	cs.setObject("P_ANIO", p_Anio, Types.INTEGER);
	        	cs.setObject("P_NUMERO_SERIE", p_NoSerie, Types.VARCHAR);
	        	cs.setObject("P_NUMERO_ECONOMICO", p_NoEconomico, Types.VARCHAR);
	        	cs.setObject("P_COLOR", p_Color, Types.VARCHAR);
	        	cs.setObject("P_PLACAS", p_Placas, Types.VARCHAR);
	        	cs.setObject("P_CAPACIDAD_CARGA_CAJAS", p_Capacidad, Types.VARCHAR);
	        	cs.setObject("P_RENDIMIENTO_PROMEDIO", p_Rendimiento, Types.VARCHAR);
	        	cs.setObject("P_ACTIVO", p_Activo, Types.BOOLEAN);
	        	cs.setObject("P_CODIGO_BARRAS", p_CodBarras, Types.VARCHAR);
	        	cs.setObject("P_KILOMETRAJE", p_kilometraje, Types.INTEGER);
	        	cs.setObject("P_INSERTAR_KILOMETRAJE", p_insertarKilometraje, Types.INTEGER);
	        	cs.setObject("P_ENTREGANDO", p_entregando, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M31", "Surgió un error al insertar en la tabla Unidad Reparto", ex.getMessage(), p_IdUser);
        	return "Surgió un error al insertar en la tabla Unidad Reparto";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_RELACION_RUTAS_OTS_SOS
	 * @author Moises Hernandez
	 * @since 09/05/2014
	 * @param idZona
	 * @param rutaOTS
	 * @param rutaSOS
	 * @param orden
	 * @return Mensaje de Exito o Falla al Insertar
	 */
	public String insertarRutaAlterna(int idZona, String rutaOTS, String rutaSOS, int orden, int p_IdUser){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRutasAlternas(?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_RELACION_RUTA_OTS_SOS", 0, Types.INTEGER);
				cs.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				cs.setObject("P_RUTA_OTS", rutaOTS, Types.VARCHAR);
				cs.setObject("P_RUTA_SOS", rutaSOS, Types.VARCHAR);
				cs.setObject("P_ORDEN", orden, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M31", "Surgió un error al insertar en la tabla Unidad Reparto", ex.getMessage(), p_IdUser);
        	return "Surgió un error al insertar en la tabla de rutas alternas";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_MODELO
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param p_IdUser
	 * @param p_IdModelo
	 * @param desc
	 * @param p_IdMarca
	 * @param p_frecuencia
	 * @return String de Exito o Falla
	 */
	public String insertarModelo(Integer p_IdUser, String p_IdModelo, String p_desc, String p_IdMarca, String p_frecuencia){	
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoModelo(?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_MODELO", 0, Types.INTEGER);
	        	cs.setObject("P_DESCRIPCION", p_desc, Types.VARCHAR);
	        	cs.setObject("P_FRECUENCIA", p_frecuencia, Types.VARCHAR);
	        	cs.setObject("P_ID_MARCA", p_IdMarca, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizo la inserción correctamente";
			}
		}catch (SQLException ex){
	    	//Al surgir una excepcion se guarda en el log de la BD
	    	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M32", "Surgió un error al insertar en la tabla Modelo.", ex.getMessage(), p_IdUser);
	    	msg =  "Surgió un error al insertar en la tabla de Modelo.";
	    }
	    finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_MARCA
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param p_IdUser
	 * @param p_IdMarca
	 * @param p_DescMarca
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarMarca(Integer p_IdUser, String p_IdMarca, String p_DescMarca){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoMarca(?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_MARCA", 0, Types.INTEGER);
	        	cs.setObject("P_DESCRIPCION", p_DescMarca, Types.VARCHAR);
	        	cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M33", "Surgió un error al insertar en la tabla Marca.", ex.getMessage(), p_IdUser);
        	msg =  "Surgió un error al insertar en la tabla de Marca.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_ASIGNACION_UNIDAD_REPARTO
	 * @author brenda.estrada
	 * @since 13/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param p_IdAsignacion- ID Asignacion de Unidad de Reparto
	 * @param p_idPais
	 * @param p_idLDC
	 * @param idEmpleado
	 * @param idUnidadReparto
	 * @param noSerie
	 * @param fAsignada
	 * @param fDenegada
	 * @param idEstatus
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarAsignacionUnidadReparto(Integer p_IdUser, String p_IdAsignacion, String p_idPais, String p_idLDC,
												  String idEmpleado, String idUnidadReparto, String noSerie, String fAsignada, 
												  String fDenegada, String idEstatus){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoAsignacionUnidadReparto(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	        	cs.setObject("P_ID_ASIGNACION_UNIDADREPARTO", p_IdAsignacion, Types.INTEGER);
	        	cs.setObject("P_ID_PAIS", p_idPais, Types.INTEGER);
	        	cs.setObject("P_ID_LDC", p_idLDC, Types.INTEGER);
        		cs.setObject("P_ID_EMPLEADO" , idEmpleado, Types.INTEGER);
				cs.setObject("P_ID_UNIDAD_REPARTO", idUnidadReparto, Types.INTEGER);
				cs.setObject("P_NUMERO_SERIE", noSerie, Types.VARCHAR);
	        	cs.setObject("P_FECHA_ASIGNADA", fAsignada, Types.VARCHAR); 
    			cs.setObject("P_FECHA_DENEGADA", fDenegada, Types.VARCHAR);
    			cs.setObject("P_ACTIVO", idEstatus, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M34", "Surgió un error al insertar en la tabla Asignacion de Unidad de Reparto.", ex.getMessage(), p_IdUser);
        	msg =  "Error al insertar los datos de Asignacion de Unidad de Reparto.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
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
	public String insertarRuta(Integer p_IdUser, String idRuta, String p_Desc, String p_Cve, String p_IdPais, 
			String p_IdZona, String p_IdTipoRuta, String p_Idldc, Integer p_DiaReparto, Boolean p_ActivoReparto){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRuta(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_RUTA", idRuta, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	        	cs.setObject("P_DESCRIPCION", p_Desc, Types.VARCHAR);
	        	cs.setObject("P_CLAVE", p_Cve, Types.VARCHAR);
	        	cs.setObject("P_ID_PAIS", p_IdPais, Types.VARCHAR);
	        	cs.setObject("P_ID_ZONA", p_IdZona, Types.VARCHAR);
	        	cs.setObject("P_ID_TIPORUTA", p_IdTipoRuta, Types.VARCHAR);
	        	cs.setObject("P_ID_LDC", p_Idldc, Types.VARCHAR);
	        	cs.setObject("P_DIA_REPARTO", p_DiaReparto, Types.SMALLINT);
	        	cs.setObject("P_ACTIVO_REPARTO", p_ActivoReparto, Types.BIT);
	        	cs.execute();
	        	msg = "Se realizo la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M35", "Surgió un error al insertar en la tabla Rutas", ex.getMessage(), p_IdUser);
        	msg =  "Surgió un error al insertar los datos de Rutas";
        }
        finally{	AccesoBD.CerrarConexion(con); 	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_TIPO_RUTA
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idTipoRuta	- ID Tipo de Ruta
	 * @param idPais		- ID Pais
	 * @param idLDC			- ID LDC
	 * @param cve			- Clave
	 * @param desc			- Descripcion
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarTipoRuta(Integer p_IdUser, String idTipoRuta, String idPais, String idLDC,
									String cve, String desc){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoTipoRuta(?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_TIPO_RUTA", idTipoRuta, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M36", "Surgió un error al insertar en la tabla Tipo de Ruta.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Tipo de Ruta.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_REPRESENTANTES_POR_RUTA
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idRepresentaRuta- ID Representante de Ruta
	 * @param idRuta
	 * @param idRepresentante
	 * @param fAlta
	 * @param fBaja
	 * @param sAnterior
	 * @param sReciente
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarRepresentanteRuta(Integer p_IdUser, String idRepresentaRuta, String idRuta, String idRepresentante, 
										    String fAlta, String fBaja, String sAnterior, String sReciente){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRepresentantePorRuta(?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_REPRESENTANTE_RUTA", idRepresentaRuta, Types.INTEGER);
				cs.setObject("P_ID_RUTA", idRuta, Types.INTEGER);
				cs.setObject("P_ID_REPRESENTANTE", idRepresentante, Types.INTEGER);
				cs.setObject("P_FECHA_ALTA", fAlta, Types.VARCHAR);
				cs.setObject("P_FECHA_BAJA", fBaja, Types.VARCHAR);
				cs.setObject("P_SECUENCIA_ENTREGA_ANTERIOR", sAnterior, Types.INTEGER);
				cs.setObject("P_SECUENCIA_ENTREGA_RECIENTE", sReciente, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M37", "Surgió un error al insertar en la tabla Representante por Ruta.", ex.getMessage(), p_IdUser);
        	msg =  "Error al insertar los datos de Representante por Ruta.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_ASIGNACION_RUTA_CHOFER
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 			- ID del Usuario de Session de App
	 * @param idAsignaRutaChofer
	 * @param idRuta
	 * @param idPais
	 * @param idZona
	 * @param idLDC
	 * @param idEmpleado
	 * @param fAsignada
	 * @param fAlta
	 * @param fDenegada
	 * @param tipoAsignacion
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarAsignacionRutaChofer(Integer p_IdUser, String idAsignaRutaChofer, String idRuta, String idPais, 
			String idZona, String idLDC, String idEmpleado,String fAsignada, String fAlta, String fDenegada, String tipoAsignacion){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoAsignacionRutaChofer(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_ASIGNACION_RUTA_CHOFER", idAsignaRutaChofer, Types.INTEGER);
				cs.setObject("P_ID_RUTA", idRuta, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_ID_EMPLEADO", idEmpleado, Types.INTEGER);
				cs.setObject("P_FECHA_ASIGNADA", fAsignada, Types.VARCHAR);
				cs.setObject("P_FECHA_DENEGADA", fDenegada, Types.VARCHAR);
				cs.setObject("P_TIPO_ASIGNACION", tipoAsignacion, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M38", "Surgió un error al insertar en la tabla Asignación Ruta Chofer.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Asignación Ruta Chofer.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_DISPOSITIVO_MOVIL
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 			- ID del Usuario de Session de App
	 * @param idDispositivo
	 * @param idPais
	 * @param idLDC
	 * @param noSerie
	 * @param marca
	 * @param modelo
	 * @param mac
	 * @param ip
	 * @param idEstatus
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarDispositivoMovil(Integer p_IdUser, String idDispositivo, String idPais, 
			String idLDC, String noSerie,String marca, String modelo, String mac, String ip, String idEstatus){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoDispositivoMovil(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_DISPOSITIVO_MOVIL", idDispositivo, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_NUMERO_SERIE", noSerie, Types.VARCHAR);
				cs.setObject("P_MARCA", marca, Types.VARCHAR);
				cs.setObject("P_MODELO", modelo, Types.VARCHAR);
				cs.setObject("P_MAC_ADDRESS", mac, Types.VARCHAR);
				cs.setObject("P_DIRECCION_IP", ip, Types.VARCHAR);
				cs.setObject("P_ACTIVO", idEstatus, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M39", "Surgió un error al insertar en la tabla Dispositivo Movil.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Asignación Ruta Chofer.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_EMPLEADO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idEmpleado
	 * @param idPuesto
	 * @param nombre
	 * @param idPais
	 * @param idLDC
	 * @param dom
	 * @param app
	 * @param apm
	 * @param nac
	 * @param sexo
	 * @param edoCivil
	 * @param rfc
	 * @param tel
	 * @param mil
	 * @param fIngreso
	 * @param idEstatus
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarEmpleado(Integer p_IdUser, String idEmpleado, String idPuesto, String nombre, String idPais, 
			String idLDC, String dom,String app, String apm, String nac, String sexo, String edoCivil, String rfc,
			String tel, String mail, String fIngreso, String idEstatus){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoEmpleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_EMPLEADO", idEmpleado, Types.INTEGER);
				cs.setObject("P_ID_PUESTO", idPuesto, Types.INTEGER);
				cs.setObject("P_NOMBRE", nombre, Types.VARCHAR);
				cs.setObject("P_DOMICILIO", dom, Types.VARCHAR);
				cs.setObject("P_AP_PATERNO", app, Types.VARCHAR);
				cs.setObject("P_AP_MATERNO", apm, Types.VARCHAR);
				cs.setObject("P_FECHA_NACIMIENTO", nac, Types.VARCHAR);
				cs.setObject("P_SEXO", sexo, Types.VARCHAR);
				cs.setObject("P_ESTADO_CIVIL", edoCivil, Types.VARCHAR);
				cs.setObject("P_RFC", rfc, Types.VARCHAR);
				cs.setObject("P_FECHA_INGRESO", fIngreso, Types.VARCHAR);
				cs.setObject("P_ESTATUS", idEstatus, Types.INTEGER);
				cs.setObject("P_TELEFONO", tel, Types.VARCHAR);
				cs.setObject("P_CORREO_ELECTRONICO", mail, Types.VARCHAR);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M40", "Surgió un error al insertar en la tabla Empleado.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Empleado.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_PUESTO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idPuesto
	 * @param idPais
	 * @param idLDC
	 * @param cve
	 * @param desc
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarPuesto(Integer p_IdUser, String idPuesto, String idPais, String idLDC,
			String cve, String desc){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoPuesto(?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_PUESTO", idPuesto, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M41", "Surgió un error al insertar en la tabla Puesto.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Puesto.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_LINEA_TRANSPORTE
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idLineaTransporte
	 * @param idPais
	 * @param tel
	 * @param cve
	 * @param desc
	 * @param dom
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarLineaTransporte(Integer p_IdUser, String idLineaTransporte, String idPais, String tel,
			String cve, String desc, String dom){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoLineaTransporte(?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_LINEA_TRANSPORTE", idLineaTransporte, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
				cs.setObject("P_TELEFONO", tel, Types.VARCHAR);
				cs.setObject("P_DOMICILIO", dom, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M42", "Surgió un error al insertar en la tabla Linea de Transporte.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Linea de Transporte.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_INFORMANTE
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idInformante
	 * @param idPais
	 * @param idTipoInformante
	 * @param idLDC
	 * @param desc
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarInformante(Integer p_IdUser, String idInformante, String idPais, String idTipoInformante,
			String idLDC, String desc){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoInformante(?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_INFORMANTE", idInformante, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_TIPO_INFORMANTE", idTipoInformante, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M43", "Surgió un error al insertar en la tabla Informante.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Informante.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_SUB_BODEGA_ALMACEN
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idSubbodega
	 * @param idZona
	 * @param idPais
	 * @param idLDC
	 * @param cve
	 * @param tel
	 * @param dom
	 * @param desc
	 * @param idUserResponsableSub
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarSubBodegaAlmacen(Integer p_IdUser, String idSubbodega, String idZona, String idPais, String idLDC,
				String cve, String tel, String dom, String desc, String idUserResponsableSub){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoSubBodegaAlmacen(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_SUB_BODEGA_ALMACEN", idSubbodega, Types.INTEGER);
				cs.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				cs.setObject("P_TELEFONO", tel, Types.VARCHAR);
				cs.setObject("P_DOMICILIO", dom, Types.VARCHAR);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO_RESPONSABLE_SUBBODEGA", idUserResponsableSub, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M44", "Surgió un error al insertar en la tabla SubBodega Almacen.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de SubBodega Almacen.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_DESTINATARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idDestinatario
	 * @param idPais
	 * @param idLDC
	 * @param cve
	 * @param nom
	 * @param app
	 * @param apm
	 * @param mail
	 * @param idTipoDestinatario
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarDestinatario(Integer p_IdUser, String idDestinatario, String idPais, String idLDC,
				String cve, String nom, String app, String apm, String mail, String idTipoDestinatario){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoDestinatario(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_DESTINATARIO", idDestinatario, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				cs.setObject("P_NOMBRE", nom, Types.VARCHAR);
				cs.setObject("P_AP_PATERNO", app, Types.VARCHAR);
				cs.setObject("P_AP_MATERNO", apm, Types.VARCHAR);
				cs.setObject("P_CORREO_ELECTRONICO", mail, Types.VARCHAR);
				cs.setObject("P_ID_TIPO_DESTINATARIO", idTipoDestinatario, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M44", "Surgió un error al insertar en la tabla Destinatario.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Destinatario.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_TIPO_DESTINATARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idTipoDestinatario
	 * @param idDestinatario
	 * @param idPais
	 * @param idLDC
	 * @param cve
	 * @param desc
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarTipoDestinatario(Integer p_IdUser, String idTipoDestinatario, 
				String idPais, String idLDC, String cve, String desc){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoTipoDestinatario(?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_TIPO_DESTINATARIO", idTipoDestinatario, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M45", "Surgió un error al insertar en la tabla Tipo Destinatario.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Tipo Destinatario.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_REPORTE_TIPO_DESTINATARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idReporteTipoDestinatario
	 * @param idTipoDestinatario
	 * @param cve
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarReporteTipoDestinatario(Integer p_IdUser, String idReporteTipoDestinatario, String idTipoDestinatario, String cve){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoReporteTipoDestinatario(?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_REPORTE_TIPO_DESTINATARIO", idReporteTipoDestinatario, Types.INTEGER);
				cs.setObject("P_ID_TIPO_DESTINATARIO", idTipoDestinatario, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M46", "Surgió un error al insertar en la tabla Reporte Tipo Destinatario.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Reporte Tipo Destinatario.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_REPORTE
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser
	 * @param idReporte
	 * @param idPais
	 * @param idLDC
	 * @param nom
	 * @param desc
	 * @param comm
	 * @param rutaTemplate
	 * @param nomTemplate
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarReporte(Integer p_IdUser, String idReporte, String idPais, String idLDC,
			String nom, String desc, String comm, String rutaTemplate, String nomTemplate){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoReporte(?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_REPORTE", idReporte, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_NOMBRE", nom, Types.VARCHAR);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
				cs.setObject("P_COMENTARIO", comm, Types.VARCHAR);
				cs.setObject("P_RUTA_TEMPLATE", rutaTemplate, Types.VARCHAR);
				cs.setObject("P_NOMBRE_TEMPLATE", nomTemplate, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
	    	//Al surgir una excepcion se guarda en el log de la BD
	    	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M47", "Surgió un error al insertar en la tabla Reporte.", ex.getMessage(), p_IdUser);
	    	msg = "Error al insertar los datos de Reporte.";
	    }
	    finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_LDC
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser
	 * @param idLDC
	 * @param desc
	 * @param razonSocial
	 * @param mail
	 * @param cve
	 * @param pass
	 * @param serverSMTP
	 * @param puerto
	 * @param tipoSeguridad
	 * @param factorMax
	 * @param factorMin
	 * @param codBarrasSal
	 * @param codBarrasEnt
	 * @param poblacion
	 * @param region
	 * @param idPais
	 * @param logAvon
	 * @param logLDC
	 * @param serverSOS
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarLDC(Integer p_IdUser, String idLDC, String desc, String razonSocial, 
			String mail, String cve, String pass, String serverSMTP, String  puerto,
			String tipoSeguridad, String factorMax, String factorMin, String codBarrasSal, String  codBarrasEnt,
			String poblacion, String region, String idPais, byte[] logAVON, byte[] logLDC, String serverSOS){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoLDC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
				cs.setObject("P_RAZON_SOCIAL", razonSocial, Types.VARCHAR);
				cs.setObject("P_CORREO_ELECTRONICO", mail, Types.VARCHAR);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				cs.setObject("P_CONTRASENIA_CORREO", pass, Types.VARCHAR);
				cs.setObject("P_SERVIDOR_SMTP_CORREO", serverSMTP, Types.VARCHAR);
				cs.setObject("P_PUERTO_CORREO", puerto, Types.VARCHAR);
				cs.setObject("P_TIPO_SEGURIDAD_CORREO", tipoSeguridad, Types.VARCHAR);
				cs.setObject("P_FACTOR_MAX", factorMax, Types.DOUBLE);
				cs.setObject("P_FACTOR_MIN", factorMin, Types.DOUBLE);
				cs.setObject("P_CODIGO_BARRAS_SALIDA", codBarrasSal, Types.VARCHAR);
				cs.setObject("P_CODIGO_BARRAS_ENTRADA", codBarrasEnt, Types.VARCHAR);
				cs.setObject("P_POBLACION", poblacion, Types.VARCHAR);
				cs.setObject("P_REGION", region, Types.VARCHAR);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_LOGO_LDC", logAVON, Types.BLOB);
				cs.setObject("P_LOGO_AVON", logLDC, Types.BLOB);
				cs.setObject("P_IP_SERVIDOR_SOS", serverSOS, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
	    	//Al surgir una excepcion se guarda en el log de la BD
	    	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M48", "Surgió un error al insertar en la tabla LDC.", ex.getMessage(), p_IdUser);
	    	msg = "Error al insertar los datos de LDC.";
	    }
	    finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_USUARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idUserCatalogo
	 * @param idPais
	 * @param idPerfil
	 * @param idTipoUsuario
	 * @param idLDC
	 * @param user
	 * @param pass
	 * @param comm
	 * @param idEmpleado
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarUsuario(Integer p_IdUser, String idUserCatalogo, String idPais, String idPerfil,
			String idTipoUsuario, String idLDC, String user, String pass, String comm, String idEmpleado){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoUsuario(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_USUARIO_CAT", idUserCatalogo, Types.INTEGER);
				cs.setObject("P_ID_PAIS", idPais, Types.INTEGER);
				cs.setObject("P_ID_PERFIL", idPerfil, Types.INTEGER);
				cs.setObject("P_ID_TIPO_USUARIO", idTipoUsuario, Types.INTEGER);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				cs.setObject("P_USUARIO", user, Types.VARCHAR);
				cs.setObject("P_CONTRASENA", pass, Types.VARCHAR);
				cs.setObject("P_COMENTARIOS", comm, Types.VARCHAR);
				cs.setObject("P_ID_EMPLEADO", idEmpleado, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	msg = Utils.GuardarLogMensajeBD("CUADMIN001.02", "M49", "Surgió un error al insertar en la tabla Usuario.", ex.getMessage(), p_IdUser).get(0);
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_PERFIL
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idPerfil
	 * @param cve
	 * @param desc
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarPerfil(Integer p_IdUser, String idPerfil, String cve, String desc){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoPerfil(?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_PERFIL", idPerfil, Types.INTEGER);
				cs.setObject("P_CLAVE", cve, Types.VARCHAR);
				cs.setObject("P_DESCRIPCION", desc, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M50", "Surgió un error al insertar en la tabla Perfil.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Perfil.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la inserción de datos a BD en la tabla PW_USUARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idDenominacion
	 * @param denominacion
	 * @param tipo
	 * @return Mensaje de Exito o Falla
	 */
	public String insertarDenominaciones(Integer p_IdUser, String idDenominacion, String denominacion, String tipo){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoDenominaciones(?,?,?,?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
				cs.setObject("P_ID_DENOMINACION", idDenominacion, Types.INTEGER);
				cs.setObject("P_DENOMINACION", denominacion, Types.VARCHAR);
				cs.setObject("P_TIPO", tipo, Types.VARCHAR);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M51", "Surgió un error al insertar en la tabla Denominaciones.", ex.getMessage(), p_IdUser);
        	msg = "Error al insertar los datos de Denominaciones.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la insercion en la BD en tabla [PW_PERFIL_MODULO]
	 * @author brenda.estrada
	 * @since 31/01/2012
	 * @param idUser
	 * @param idModulo
	 * @param idAccion
	 * @param idPerfil
	 * @return String [Mensaje de Exito o Falla]
	 */
	public String insertarPermisosPorPerfil(Integer idUser, Integer idModulo, Integer idAccion, Integer idPerfil){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoPermisos(?,?,?,?)}");
				cs.setObject("P_ID_PERFIL", idPerfil, Types.INTEGER);
				cs.setObject("P_ID_USUARIO", idUser, Types.INTEGER);
				cs.setObject("P_ID_MODULO", idModulo, Types.INTEGER);
				cs.setObject("P_ID_ACCION", idAccion, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se realizó la inserción correctamente";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M52", "Surgió un error al insertar en la tabla Perfil-Modulo.", ex.getMessage(), idUser);
        	msg = "Error al insertar los datos de Permisos por Perfil.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
}
