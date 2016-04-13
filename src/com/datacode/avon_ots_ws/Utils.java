package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.datacode.avon_ots_ws.model.*;

/**
 * Clase de métodos de uso general
 * 
 * @author jorge.torner
 * @since 20-12-2011
 */
public class Utils {
	/**
	 * Formatos de fechas usados en la aplicación
	 */
	public static String formatoFechaCorta = "dd/MM/yyyy";
	public static String formatoFechaCortaHoraCorta = "dd/MM/yyyy HH:mm";
	public static String formatoFechaCortaHoraLarga = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Guarda un registro de log a partir de un mensaje de la BD o un mensaje
	 * personalizado
	 * 
	 * @author jorge.torner
	 * @since 20-12-2011
	 * @param p_cu
	 *            -Caso de uso
	 * @param p_clave
	 *            -Clave del caso de uso
	 * @param p_mensajePersonal
	 *            -Incidente ocurrido personalizado (en caso de no existir en la
	 *            BD el mensaje relacionado a p_cu y p_clave
	 * @param p_mensajeExcepcion
	 *            -Mensaje de excepción interna de código
	 * @param p_idUsuario
	 *            -Id del usuario
	 * @return Lista de String con el mensaje y titulo
	 */
	public static ArrayList<String> GuardarLogMensajeBD(String p_cu,
			String p_clave, String p_mensajePersonal,
			String p_mensajeExcepcion, int p_idUsuario) {
		ArrayList<String> lista = null;
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PWA_InsertarLog(?,?,?,?,?)}");
				cs.setObject("p_idUsuario", p_idUsuario == 0 ? null
						: p_idUsuario, Types.INTEGER);
				cs.setObject("p_casoUso", p_cu, Types.VARCHAR);
				cs.setObject("p_claveMensaje", p_clave, Types.VARCHAR);
				cs.setObject("p_incidente", p_mensajePersonal, Types.VARCHAR);
				cs.setObject("p_mensaje", p_mensajeExcepcion, Types.VARCHAR);

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				if (rs.next()) {
					lista = new ArrayList<String>();
					lista.add(rs.getString("DESCRIPCION"));
					lista.add(rs.getString("TITULO"));
				}
				rs.close();
				// for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
				// {
				// Object o = rs.getObject(i);
				// System.out.print(o + "(Type " + o.getClass().getName() +
				// ")\t");
				// }
			} catch (SQLException ex) {
				AccesoArchivos.GuardaArchivoLog(ex.getMessage() + " - "
						+ ex.getStackTrace().toString());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}
		return lista;
	}

	/**
	 * Método para guardar mensaje que conlleve parámetros en el log y lo trae
	 * de regreso paramostrarse en pantalla
	 * 
	 * @author jorge.torner
	 * @since 23/01/2012
	 * @param p_cu
	 *            -Caso de Uso
	 * @param p_clave
	 *            -Clave de mensaje
	 * @param p_mensajeExcepcion
	 *            -Error de excepción si es que hay
	 * @param p_idUsuario
	 *            -Id del usuario
	 * @param p_parametros
	 *            -Cadena de los parámetros a mandar en el mensaje. Cada
	 *            parámetro debe ir separado por el simbolo '&'. Por ejemplo la
	 *            cadena: "102&2011&Celaya" son tres parámetros: "102", "2011" y
	 *            "Celaya"
	 * @return String -Cadena con el mensaje guardado en log
	 */
	public static String GuardarLogMensajeParams(String p_cu, String p_clave,
			String p_mensajeExcepcion, int p_idUsuario, String p_parametros) {
		String mensaje = null;
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PWA_InsertarLog_Param(?,?,?,?,?,?)}");
				cs.setObject("P_ID_USUARIO", p_idUsuario == 0 ? null
						: p_idUsuario, Types.INTEGER);
				cs.setObject("P_CU", p_cu, Types.VARCHAR);
				cs.setObject("P_CLAVE", p_clave, Types.VARCHAR);
				cs.setObject("P_PARAMETROS", p_parametros, Types.VARCHAR);
				cs.setObject("P_ERROR_MSG", p_mensajeExcepcion, Types.VARCHAR);
				cs.registerOutParameter("SALIDA", Types.VARCHAR);
				// cs.setObject("SALIDA", mensaje, Types.VARCHAR);

				cs.execute();
				mensaje = cs.getString("SALIDA");
			} catch (SQLException ex) {
				AccesoArchivos.GuardaArchivoLog(ex.getMessage() + " - "
						+ ex.getStackTrace().toString());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}
		return mensaje;
	}

	/**
	 * Método para guardar al log de Replicación un mensaje que conlleve
	 * parámetros y lo trae de regreso usarse
	 * 
	 * @author jorge.torner
	 * @since 15/01/2014
	 * @param p_cu
	 *            -Caso de Uso
	 * @param p_claveOIncidente
	 *            - Clave de mensaje o el mensaje del incidente
	 * @param p_mensajeExcepcion
	 *            -Error de excepción si es que hay
	 * @param p_idReplicacion
	 *            -Id de la replicación si es que hay. En caso de no haber
	 *            mandar 0.
	 * @param p_parametros
	 *            -Cadena de los parámetros a mandar en el mensaje. Cada
	 *            parámetro debe ir separado por el simbolo '&'. Por ejemplo la
	 *            cadena: "102&2011&Celaya" son tres parámetros: "102", "2011" y
	 *            "Celaya"
	 * @return String -Cadena con el mensaje guardado en log
	 */
	public static String guardarLogReplicacionMensajeParams(String p_cu,
			String p_claveOIncidente, String p_mensajeExcepcion,
			int p_idReplicacion, String p_parametros) {
		String mensaje = null;
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_LogReplicacionInsertar_MensajeParam(?,?,?,?,?,?)}");
				cs.setObject("p_idReplicacion", p_idReplicacion, Types.INTEGER);
				cs.setObject("p_cu", p_cu, Types.VARCHAR);
				cs.setObject("p_claveOIncidente", p_claveOIncidente,
						Types.VARCHAR);
				cs.setObject("p_parametros", p_parametros, Types.VARCHAR);
				cs.setObject("p_mensajeErrorTecnico", p_mensajeExcepcion,
						Types.VARCHAR);
				cs.registerOutParameter("p_salida", Types.VARCHAR);

				cs.execute();
				mensaje = cs.getString("p_salida");
			} catch (SQLException ex) {
				AccesoArchivos.GuardaArchivoLog(ex.getMessage() + " - "
						+ ex.getStackTrace().toString());
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}
		return mensaje;
	}

	/**
	 * Guarda un registro de log a partir de un mensaje de la BD o un mensaje
	 * personalizado
	 * 
	 * @author jorge.torner
	 * @since 20-12-2011
	 * @param p_cu
	 *            -Caso de uso
	 * @param p_clave
	 *            -Clave del caso de uso
	 * @param p_guardarLog
	 *            -Indica si se va a guardar en el Log el mensaje solicitado.
	 *            Sólo en caso de verdadero, se deben ingresar los siguientes
	 *            parámetros para guardar log. En caso contrario no es necesario
	 * @param p_mensajePersonal
	 *            -Incidente ocurrido personalizado (en caso de no existir en la
	 *            BD el mensaje relacionado a p_cu y p_clave
	 * @param p_mensajeExcepcion
	 *            -Mensaje de excepción interna de código
	 * @param p_idUsuario
	 *            -Id del usuario
	 * @return Lista de String con el mensaje y titulo
	 */
	public static ArrayList<String> ObtenerMensajeBD(String p_cu,
			String p_clave, Boolean p_guardarLog, String p_mensajePersonal,
			String p_mensajeExcepcion, int p_idUsuario) {
		ArrayList<String> lista = null;
		if (p_guardarLog) {
			lista = GuardarLogMensajeBD(p_cu, p_clave, p_mensajePersonal,
					p_mensajeExcepcion, p_idUsuario);
		} else {
			Connection con = AccesoBD.AbrirConexionOTS();
			CallableStatement cs = null;
			if (con != null) {
				try {
					cs = con.prepareCall("{call SP_TRAE_MENSAJE(?,?)}");
					cs.setObject("P_CU", p_cu, Types.VARCHAR);
					cs.setObject("P_CLAVE", p_clave, Types.VARCHAR);

					ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
					if (rs.next()) {
						lista = new ArrayList<String>();
						lista.add(rs.getString("DESCRIPCION"));
						lista.add(rs.getString("TITULO"));
					}
					rs.close();
				} catch (SQLException ex) {
					Utils.GuardarLogMensajeBD("General Admin", "M9",
							"Error al consultar mensaje de la BD",
							ex.getMessage(), 0);
				} finally {
					AccesoBD.CerrarStatement(cs);
					AccesoBD.CerrarConexion(con);
				}
			}
		}
		return lista;
	}

	/**
	 * Método para validar un inicio de sesión
	 * 
	 * @author jorge.torner
	 * @since 03/01/2012
	 * @param usuario
	 *            -Nombre del usuario
	 * @param contrasenia
	 *            -Contraseña
	 * @return Estructura Usuario con valores si se valida la contraseña
	 */
	public static Usuario ValidarUsuario(String tipo, String usuario,
			String contrasenia) {
		Usuario usuarioEst = null;
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_ValidarUsuario(?,?,?,?)}");
				cs.setObject("P_TIPO_CU", tipo, Types.VARCHAR);
				cs.setObject("P_ID_USUARIO", 0, Types.INTEGER);
				cs.setObject("P_USUARIO", usuario, Types.VARCHAR);
				cs.setObject("P_CONTRASENIA", contrasenia, Types.VARCHAR);

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				if (rs.next()) {
					usuarioEst = new Usuario();
					usuarioEst.setUsuario(rs.getString("USUARIO"));
					usuarioEst.setIdUsuario(rs.getInt("ID_USUARIO"));
					usuarioEst.setIdTipoUsuario(rs.getInt("ID_TIPO_USUARIO"));
				}
				rs.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M4",
						"Error al validar el usuario", ex.getMessage(), 0);
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}

		return usuarioEst;
	}

	/**
	 * Carga la configuración de la BD
	 * 
	 * @author jorge.torner
	 * @since 03/01/2012
	 * @return Estructura de Configuración con valores
	 */
	public static Configuracion CargarConfiguracion() {
		Configuracion config = null;

		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PW_CONFIGURACION()}");

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				if (rs.next()) {
					config = new Configuracion();
					config.setIdCampania(rs.getInt("ID_CAMPANIA"));
					config.setAnioCampania(rs.getString("ANIO_CAMPANIA"));
					config.setNumCampania(rs.getString("NUM_CAMPANIA"));
					config.setIdLDC(rs.getInt("ID_LDC"));
					config.setDescripcionLDC(rs.getString("DESCRIPCION_LDC"));
					config.setRazonSocialLDC(rs.getString("RAZON_SOCIAL_LDC"));
					config.setIdPais(rs.getShort("ID_PAIS"));
					config.setClaveLDC(rs.getString("CLAVE"));
				}
				rs.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M3",
						"No se pudo cargar la configuración", ex.getMessage(),
						0);
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}

		return config;
	}

	/**
	 * Obtiene la descripcion del LDC.
	 * @return
	 */
	public static String obtieneDescripcionLDC() {
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_InformacionLDCObtener()}");
				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				if (rs.next()) {
					return rs.getString("DESCRIPCION_LDC");
				}
				rs.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M3",
						"No se pudo cargar la configuración", ex.getMessage(),
						0);
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}
		return "";
	}

	/**
	 * Obtiene la lista de LDC's
	 * 
	 * @author jorge.torner
	 * @since 19/06/2012
	 * @return Array Arreglo de LDC
	 */
	public static ArrayList<LDC> ObtenerListaLDC(int p_idLdc, int p_idUsuario) {
		ArrayList<LDC> listaLdc = new ArrayList<LDC>();
		LDC ldc = null;

		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PW_LDC(?)}");
				cs.setObject("P_ID_LDC", p_idLdc, Types.INTEGER);

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				while (rs.next()) {
					ldc = new LDC();
					ldc.setCLAVE(rs.getString("CLAVE"));
					ldc.setCODIGO_BARRAS_ENTRADA(rs
							.getString("CODIGO_BARRAS_ENTRADA"));
					ldc.setCODIGO_BARRAS_SALIDA(rs
							.getString("CODIGO_BARRAS_SALIDA"));
					ldc.setCONTRASENIA_CORREO(rs
							.getString("CONTRASENIA_CORREO"));
					ldc.setCORREO_ELECTRONICO(rs
							.getString("CORREO_ELECTRONICO"));
					ldc.setDESCRIPCION(rs.getString("DESCRIPCION"));
					ldc.setFACTOR_MAX(rs.getFloat("FACTOR_MAX"));
					ldc.setFACTOR_MIN(rs.getFloat("FACTOR_MIN"));
					ldc.setFECHA_ACTUALIZADO(rs.getString("FECHA_ACTUALIZADO"));
					ldc.setID_LDC(rs.getInt("ID_LDC"));
					ldc.setID_PAIS(rs.getShort("ID_PAIS"));
					ldc.setIP_SERVIDOR_SOS(rs.getString("IP_SERVIDOR_SOS"));
					ldc.setPOBLACION(rs.getString("POBLACION"));
					ldc.setPUERTO_CORREO(rs.getInt("PUERTO_CORREO"));
					ldc.setRAZON_SOCIAL(rs.getString("RAZON_SOCIAL"));
					ldc.setREGION(rs.getString("REGION"));
					ldc.setSERVIDOR_SMTP_CORREO(rs
							.getString("SERVIDOR_SMTP_CORREO"));
					ldc.setTIPO_SEGURIDAD_CORREO(rs
							.getString("TIPO_SEGURIDAD_CORREO"));
					ldc.setUSUARIO_ACTUALIZA(rs.getString("USUARIO_ACTUALIZA"));

					listaLdc.add(ldc);
				}
				rs.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M10",
						"No se pudo cargar la lista de LDC", ex.getMessage(),
						p_idUsuario);
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}

		return listaLdc;
	}

	/**
	 * Obtiene la fecha del servidor de ésta aplicación
	 * 
	 * @author jorge.torner
	 * @since 26/01/2012
	 * @param p_formatoFecha
	 *            - Formato de la fecha
	 * @return String Cadena con la fecha formateada
	 */
	public static String ObtenerFechaActual(String p_formatoFecha) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(p_formatoFecha);
		return sdf.format(cal.getTime());
	}

	public static String probarConexion() {
		String res = "";
		Connection con = AccesoBD.AbrirConexionOTS();
		if (con != null)
			res = "Conexión OTS exitosa";
		else
			res = "Conexión OTS fallida";

		Connection con2 = AccesoBD.AbrirConexionSOS();
		if (con2 != null)
			res += " - Conexión SOS exitosa";
		else
			res += " - Conexión SOS fallida";

		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PruebaFormatoFecha(?)}");
				cs.setObject("P_Fecha", "31/01/2012", Types.VARCHAR);
				cs.execute();
				res += " - Prueba de lenguaje exitosa";
			} catch (SQLException ex) {
				res += " - Prueba de lenguaje fallida - " + ex.getMessage();
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}

		return res;
	}

	public static String agregaComillas(String cadena) {
		if (cadena == null)
			return "null";
		else
			return "'" + cadena + "'";
	}

	public static String agregaComillasFecha(String cadena) {
		if (cadena == null)
			return "null";
		else
			return "{d '" + cadena + "'}";
	}

	public String ObtieneParametro(String parametro) {
		String res = "";

		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_ValorParametroObtener(?)}");
				cs.setObject("p_nombreParametro", parametro, Types.VARCHAR);
				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				if (rs.next()) {
					res = rs.getString("valorParametro");
				}
				rs.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("General Admin", "M3",
						"Ocurrio un problema al obtener el valor del parametro"
								+ parametro, ex.getMessage(), 0);
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}

		return res;
	}
}
