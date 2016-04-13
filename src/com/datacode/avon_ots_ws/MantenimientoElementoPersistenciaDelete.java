/**
 * Contiene los metodos para Eliminar registros de los CATALOGOS
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
 * @author brenda.estrada
 * @since 05/01/2012
 * @ejb.persistence Delete
 */
public class MantenimientoElementoPersistenciaDelete {

	//Propiedades Persistencia
	PreparedStatement ps = null;
	ResultSet rst = null;
	String query = "";
	Connection con = AccesoBD.AbrirConexionOTS();
	CallableStatement cs = null;
	
	//Var para guardar mensaje personalizado
	String msg = ""; //CVE de mensaje del 91 al 120
	String v_operacion = "D";
	
	
	/**
	 * Eliminar registro de la tabla Unidad de Reparto
	 * @author brenda.estrada
	 * @since 05/01/2012
	 * @param p_IdUnidadReparto
	 * @param p_IdUser
	 * @return  Mensaje de exito o falla al eliminar
	 */
	public String eliminarUnidadReparto(Integer p_IdUnidadReparto, Integer p_IdUser){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoUnidadReparto(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_UNIDADREPARTO", p_IdUnidadReparto, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M91", "Surgió un error al eliminar el registro en la tabla Unidad de Reparto", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro de Unidad de Reparto porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	
	/**
	 * Eliminar registro de la tabla de Rutas Alternas
	 * @author Moises Hernandez
	 * @since 10/05/2014
	 * @param idRuta
	 * @param idUser
	 * @return  Mensaje de exito o falla al eliminar
	 */
	public String eliminarRutaAlterna(int idRuta, int idUser){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRutasAlternas(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_RELACION_RUTA_OTS_SOS", idRuta, Types.BIGINT);
	        	cs.execute();
	        	msg = "El registro fue eliminado correctamente.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M31", "Surgió un error al borrar en la tabla Unidad Reparto", ex.getMessage(), idUser);
        	return "Surgió un error al borrar en la tabla de rutas alternas";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	
	/**
	 * Eliminar registro de la tabla Modelo
	 * @author brenda.estrada
	 * @since 05/01/2012
	 * @param p_IdModelo
	 * @param p_IdUser
	 * @return  Mensaje de exito o falla al eliminar
	 */
	public String eliminarModelo(Integer p_IdModelo, Integer p_IdUser){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoModelo(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_MODELO", p_IdModelo, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M92", "Surgió un error al eliminar el registro en la tabla Modelo.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro en la tabla Modelo porque es probable que tenga dependencias con otros registros.";
        }
		finally{	AccesoBD.CerrarConexion(con);	}
		return null;
	}
	/**
	 * Eliminar registro de la tabla Marca
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @param p_IdMarca
	 * @param p_IdUser
	 * @return  Mensaje de exito o falla al eliminar
	 */
	public String eliminarMarca(Integer p_IdMarca, Integer p_IdUser){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoMarca(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_MARCA", p_IdMarca, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M93", "Surgió un error al eliminar el registro.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
		finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_ASIGNACION_UNIDAD_REPARTO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param p_IdAsignacion- ID Asignacion de Unidad de Reparto
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarAsignacionUnidadReparto(Integer p_IdUser, String p_IdAsignacion){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoAsignacionUnidadReparto(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
	        	cs.setObject("P_ID_ASIGNACION_UNIDADREPARTO", p_IdAsignacion, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M94", "Surgió un error al eliminar en la tabla Asignacion de Unidad de Reparto.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Eliminar registro de la tabla PW_RUTAS
	 * @param p_IdRuta - Id ruta seleccionada
	 * @param p_IdUser - Id Usuario de session
	 * @return Mensaje de exito o falla al eliminar 
	 */
	public String eliminarRuta(Integer p_IdRuta, Integer p_IdUser){
		
		v_operacion = "D";
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRuta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("P_OPERACION",v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_RUTA", p_IdRuta, Types.INTEGER);
	        	cs.setObject("P_ID_USUARIO", p_IdUser, Types.INTEGER);
	            cs.setObject("P_DESCRIPCION","", Types.VARCHAR);
	        	cs.setObject("P_CLAVE","", Types.VARCHAR);
	        	cs.setObject("P_ID_PAIS","", Types.VARCHAR);
	        	cs.setObject("P_ID_ZONA","", Types.VARCHAR);
	        	cs.setObject("P_ID_TIPORUTA","", Types.VARCHAR);
	        	cs.setObject("P_ID_LDC","", Types.VARCHAR);
	        	cs.setObject("P_POBLACION_COLONIA","", Types.VARCHAR);
	        	cs.setObject("P_KILOMETRAJE_PROMEDO",0.0, Types.DECIMAL);
	        	cs.setObject("P_NUMERO_PROMEDIO_ORDENES",0, Types.INTEGER);
	        	cs.setObject("P_TIEMPO_PROMEDIO_EFECTIVO_REPARTO","", Types.VARCHAR);
	        	cs.setObject("P_TIEMPO_PROMEDIO_TOTAL_REPARTO","", Types.VARCHAR);
	        	cs.setObject("P_TIPO_RIESGO","", Types.VARCHAR);	
	        	cs.setObject("P_DIA_REPARTO", 0, Types.INTEGER);
	        	cs.setObject("P_ACTIVO_REPARTO", false, Types.BIT);
	        	cs.setObject("P_REPARTO_SIN_HH", false, Types.BIT);
				cs.registerOutParameter("P_MENSAJE",Types.VARCHAR);
				//Ejecuta el procedimiento
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M95", "Surgió un error al eliminar el registro en la tabla Rutas", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
		finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_TIPO_RUTA
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idTipoRuta	- ID Tipo de Ruta
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarTipoRuta(Integer p_IdUser, String idTipoRuta){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoTipoRuta(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_TIPO_RUTA", idTipoRuta, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M96", "Surgió un error al eliminar en la tabla Tipo de Ruta.", ex.getMessage(), p_IdUser);
        	return msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_REPRESENTANTES_POR_RUTA
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idRepresentaRuta- ID Representante de Ruta
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarRepresentanteRuta(Integer p_IdUser, String idRepresentaRuta){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoRepresentantePorRuta(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_REPRESENTANTE_RUTA", idRepresentaRuta, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M97", "Surgió un error al eliminar en la tabla Representante por Ruta.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_ASIGNACION_RUTA_CHOFER
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 			- ID del Usuario de Session de App
	 * @param idAsignaRutaChofer
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarAsignacionRutaChofer(Integer p_IdUser, String idAsignaRutaChofer){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoAsignacionRutaChofer(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_ASIGNACION_RUTA_CHOFER", idAsignaRutaChofer, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M98", "Surgió un error al eliminar en la tabla Asignación Ruta Chofer.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_DISPOSITIVO_MOVIL
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 			- ID del Usuario de Session de App
	 * @param idDispositivo
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarDispositivoMovil(Integer p_IdUser, String idDispositivo){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoDispositivoMovil(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_DISPOSITIVO_MOVIL", idDispositivo, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M99", "Surgió un error al eliminar en la tabla Dispositivo Movil.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_EMPLEADO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idEmpleado
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarEmpleado(Integer p_IdUser, String idEmpleado){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoEmpleado(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_EMPLEADO", idEmpleado, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M100", "Surgió un error al eliminar en la tabla Empleado.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_PUESTO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idPuesto
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarPuesto(Integer p_IdUser, String idPuesto){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoPuesto(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_PUESTO", idPuesto, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M101", "Surgió un error al eliminar en la tabla Puesto.", ex.getMessage(), p_IdUser);
        	return msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_LINEA_TRANSPORTE
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idLineaTransporte
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarLineaTransporte(Integer p_IdUser, String idLineaTransporte){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoLineaTransporte(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_LINEA_TRANSPORTE", idLineaTransporte, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M102", "Surgió un error al eliminar en la tabla Linea de Transporte.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_INFORMANTE
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idInformante
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarInformante(Integer p_IdUser, String idInformante){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoInformante(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_INFORMANTE", idInformante, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M103", "Surgió un error al eliminar en la tabla Informante.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_SUB_BODEGA_ALMACEN
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idSubbodega
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarSubBodegaAlmacen(Integer p_IdUser, String idSubbodega){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoSubBodegaAlmacen(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_SUB_BODEGA_ALMACEN", idSubbodega, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M104", "Surgió un error al eliminar en la tabla SubBodega Almacen.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_DESTINATARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idDestinatario
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarDestinatario(Integer p_IdUser, String idDestinatario){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoDestinatario(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_DESTINATARIO", idDestinatario, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M105", "Surgió un error al eliminar en la tabla Destinatario.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_TIPO_DESTINATARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idTipoDestinatario
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarTipoDestinatario(Integer p_IdUser, String idTipoDestinatario){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoTipoDestinatario(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_TIPO_DESTINATARIO", idTipoDestinatario, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M106", "Surgió un error al eliminar en la tabla Tipo Destinatario.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_REPORTE_TIPO_DESTINATARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idReporteTipoDestinatario
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarReporteTipoDestinatario(Integer p_IdUser, String idReporteTipoDestinatario){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoReporteTipoDestinatario(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_REPORTE_TIPO_DESTINATARIO", idReporteTipoDestinatario, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M107", "Surgió un error al eliminar en la tabla Reporte Tipo Destinatario.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_REPORTE
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser
	 * @param idReporte
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarReporte(Integer p_IdUser, String idReporte){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoReporte(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_REPORTE", idReporte, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
	    	//Al surgir una excepcion se guarda en el log de la BD
	    	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M108", "Surgió un error al eliminar en la tabla Reporte.", ex.getMessage(), p_IdUser);
	    	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
	    }
	    finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_LDC
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser
	 * @param idLDC
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarLDC(Integer p_IdUser, String idLDC){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoLDC(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_LDC", idLDC, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
	    	//Al surgir una excepcion se guarda en el log de la BD
	    	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M109", "Surgió un error al eliminar en la tabla LDC.", ex.getMessage(), p_IdUser);
	    	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
	    }
	    finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_USUARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idUserCatalogo
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarUsuario(Integer p_IdUser, String idUserCatalogo){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoUsuario(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO_CAT", idUserCatalogo, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M110", "Surgió un error al eliminar en la tabla Usuario.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_PERFIL
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idPerfil
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarPerfil(Integer p_IdUser, String idPerfil){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoPerfil(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_PERFIL", idPerfil, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M111", "Surgió un error al eliminar en la tabla Perfil.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}
	/**
	 * Realiza la eliminación de datos a BD en la tabla PW_USUARIO
	 * @author brenda.estrada
	 * @since 11/01/2012
	 * @param p_IdUser 		- ID del Usuario de Session de App
	 * @param idDenominacion
	 * @return Mensaje de Exito o Falla al eliminar
	 */
	public String eliminarDenominaciones(Integer p_IdUser, String idDenominacion){
		try{
			if(con != null){
				cs = con.prepareCall("{call SP_PWA_CatalogoDenominaciones(?,?)}");
				cs.setObject("P_OPERACION", v_operacion, Types.VARCHAR);
				cs.setObject("P_ID_DENOMINACION", idDenominacion, Types.INTEGER);
	        	cs.execute();
	        	msg = "Se eliminó correctamente el registro.";
			}
		}catch (SQLException ex){
        	//Al surgir una excepcion se guarda en el log de la BD
        	Utils.GuardarLogMensajeBD("CUADMIN001.02", "M112", "Surgió un error al eliminar en la tabla Denominaciones.", ex.getMessage(), p_IdUser);
        	msg = "No se eliminó el registro porque es probable que tenga dependencias con otros registros.";
        }
        finally{	AccesoBD.CerrarConexion(con);	}
		return msg;
	}

}
