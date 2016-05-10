package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import com.datacode.avon_ots_ws.model.Archivo;
import com.datacode.avon_ots_ws.model.DatosCorreo;
import com.datacode.avon_ots_ws.model.LiquidacionRepartoDTO;
import com.datacode.avon_ots_ws.model.ModelCorreoNoEnviado;

/**
 * Clase para el manejo de correos electrónicos
 * 
 * @author jorge.torner
 * @since 27/12/2011
 */
public class Correo {

	private ResultSet resultSet;
	private CallableStatement callableStatement;
	private Connection connection;

	public Correo() {

	}

	/**
	 * Método para el envío de correos electrónicos
	 * 
	 * @author jorge.torner
	 * @since 27/12/2011
	 * @param de
	 *            -Quién envía el correo (dirección correo remitente)
	 * @param para
	 *            -Para quiénes es el correo (direcciónes de correos destino).
	 *            Pueden ir varias direcciones separadas por comas
	 * @param asunto
	 *            -El asunto del correo
	 * @param textoCorreo
	 *            -El texto del correo
	 * @param esHtml
	 *            -Verdadero si se quiere enviar como html
	 * @param servidorSmtp
	 *            -El servidor smtp que va a enviar el correo
	 * @param usuario
	 *            -Usuario de la cuenta de correo remitente
	 * @param contrasenia
	 *            -Contraseña de la cuenta de correo remitente
	 * @param habilitaSsl
	 *            -Verdadero si el servidor smtp requiere autenticación ssl
	 * @param puerto
	 *            -Puerto para el servidor smtp
	 * @param adjuntos
	 *            -Archivos adjuntos para el correo
	 * @return Regresa una cadena vacía si no ocurrió ningún error, ó una cadena
	 *         con mensaje de error en caso de existir
	 */
	public String EnviarCorreo(String de, String para, String asunto,
			String textoCorreo, Boolean esHtml, String servidorSmtp,
			String usuario, String contrasenia, Boolean habilitaSsl,
			int puerto, Archivo[] adjuntos) {
		String res = "";

		// Validaciones
		if (de.equals("") || para.equals("") || servidorSmtp.equals("")) {
			res = "No se puede hacer el envío de correo si falta algún dato necesario como: remitente, destinatario o servidor";
		}

		// Asignamos propiedades de configuración de servidor
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", servidorSmtp);

		if (puerto != 0)
			prop.setProperty("mail.smtp.port", String.valueOf(puerto));
		if (habilitaSsl)
			prop.setProperty("mail.smtp.starttls.enable", "true");

		if (servidorSmtp.contains("gmail")) {
			prop.setProperty("mail.smtp.socketFactory.port",
					String.valueOf(puerto));
			prop.setProperty("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
		}

		try {
			// Añadimos datos generales de correo
			SMTPAuthenticator auth = new SMTPAuthenticator();
			auth.setUsr(usuario);
			auth.setCtr(contrasenia);
			Session session = Session.getInstance(prop, auth);
			session.setDebug(true);
			Transport transport = session.getTransport();

			MimeMessage mensaje = new MimeMessage(session);
			mensaje.setFrom(new InternetAddress(de));
			mensaje.setRecipients(Message.RecipientType.TO, para);
			mensaje.setSubject(asunto);

			// Armamos cuerpo de correo
			MimeMultipart partesCorreo = new MimeMultipart("related");
			BodyPart cuerpoCorreo = new MimeBodyPart();
			if (esHtml)
				cuerpoCorreo.setContent(textoCorreo, "text/html");
			else
				cuerpoCorreo.setContent(textoCorreo, "text/plain");

			partesCorreo.addBodyPart(cuerpoCorreo);

			// Armamos adjuntos
			// BodyPart adjunto;
			if (adjuntos != null) {
				for (int i = 0; i < adjuntos.length; i++) {
					BodyPart adjunto = new MimeBodyPart();
					ByteArrayDataSource ds = new ByteArrayDataSource(
							adjuntos[i].getArchivo(),
							(adjuntos[i].getTipoContenido().equals("")) ? "text/plain"
									: adjuntos[i].getTipoContenido());
					adjunto.setDataHandler(new DataHandler(ds));

					adjunto.setFileName(adjuntos[i].getNombre());

					partesCorreo.addBodyPart(adjunto);
				}
			}

			// Agregamos partes del correo
			mensaje.setContent(partesCorreo);

			// Enviando correo
			transport.connect();
			transport.sendMessage(mensaje,
					mensaje.getRecipients(Message.RecipientType.TO));
			transport.close();
			// Transport.send(mensaje);
			System.out.println("Mensaje enviado!");

		} catch (MessagingException ex) {
			// ex.printStackTrace();
			Utils.GuardarLogMensajeBD("EnviarCorreo", "M1",
					"Surgió un error al enviar correo a los destinatarios",
					ex.toString(), 1);
			res = "Error, correo no enviado: " + ex.toString();
		} catch (Exception ex) {
			res = "Error, correo no enviado: " + ex.toString();
		}

		return res;
	}

	/**
	 * Clase para autentificar en el envío de correos
	 * 
	 * @author jorge.torner
	 * @since 27/12/2011
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		private String usr;
		private String ctr;

		public String getUsr() {
			return usr;
		}

		public void setUsr(String usr) {
			this.usr = usr;
		}

		public String getCtr() {
			return ctr;
		}

		public void setCtr(String ctr) {
			this.ctr = ctr;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			String username = getUsr();
			String password = getCtr();
			return new PasswordAuthentication(username, password);
		}
	}

	public DatosCorreo obtenerDatosCorreoCuentaMaestra(int idLDC) {
		DatosCorreo datos = new DatosCorreo();
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ObtenerDatos_Correo_CuentaMaestra(?)}");
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					datos.setCuenta(resultSet.getString("CORREO_ELECTRONICO"));
					datos.setHabilitaSSL(new Boolean(resultSet
							.getString("TIPO_SEGURIDAD_CORREO")));
					datos.setPassword(resultSet.getString("CONTRASENIA_CORREO"));
					datos.setPuerto(resultSet.getInt("PUERTO_CORREO"));
					datos.setServidor(resultSet
							.getString("SERVIDOR_SMTP_CORREO"));
					datos.setUsuario(resultSet.getString("CORREO_ELECTRONICO"));
					datos.setRazonSocial(resultSet.getString("RAZON_SOCIAL"));
					datos.setClavePorteo(resultSet.getString("CLAVE"));

				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(" ", "M1",
						"Surgió un error al obtener los Datos del correo",
						ex.getMessage(), idLDC);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return datos;
	}

	public int registrarEnvioMailReporteSubbodega(int idUsuario,
			int idCorreoEnviado) {
		int idReporte = -1;
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_REGISTRAR_ENVIO_REPORTE_SUB_BODEGA(?,?)}");
				callableStatement.setObject("P_ID_USUARIO", idUsuario,
						Types.INTEGER);
				callableStatement.setObject("P_ID_CORREO_ENVIADO",
						idCorreoEnviado, Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					idReporte = resultSet.getInt("IDNUEVO");

				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						" ",
						"M1",
						"Surgió un error al Registrar el envio de Reporte a Subodega",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return idReporte;
	}

	/*public List<Integer> obtenerListaLiquidacionesMail() {
		connection = AccesoBD.AbrirConexionOTS();
		List<Integer> listaLiq = new ArrayList<Integer>();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Obtener_Lista_Liquidaciones_Mail}");
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				while (resultSet.next()) {
					listaLiq.add(resultSet.getInt("ID_SALIDA_REPARTO"));
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						" ",
						"M1",
						"Surgió un error al obtener la lista de liquidaciones a enviar : obtenerListaLiquidacionesMail",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaLiq;
	}*/
	
	public List<LiquidacionRepartoDTO> obtenerListaLiquidacionesMail() {
		connection = AccesoBD.AbrirConexionOTS();
		List<LiquidacionRepartoDTO> listaLiq = new ArrayList<LiquidacionRepartoDTO>();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Obtener_Lista_Liquidaciones_Mail}");
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				while (resultSet.next()) {
					LiquidacionRepartoDTO lr = new LiquidacionRepartoDTO();
					lr.setIdSalidaReparto(resultSet.getInt("ID_SALIDA_REPARTO"));
					lr.setEstatusCorreo(resultSet.getString("ESTATUS_CORREO"));
					lr.setEstatusCorreoDejadasPUP(resultSet.getString("ESTATUS_CORREO_DEJADAS_PUP"));
					lr.setEstatusCorreoRecolectadasPUP(resultSet.getString("ESTATUS_CORREO_RECOLECTADAS_PUP"));
					listaLiq.add(lr);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						" ",
						"M1",
						"SurgiÃ³ un error al obtener la lista de liquidaciones a enviar : obtenerListaLiquidacionesMail",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaLiq;
	}
	
	public String actualizarStatusLiquidacionesMail(String statusNuevo,
			int idSalidaReparto, String tipoLiquidacion) {
		connection = AccesoBD.AbrirConexionOTS();
		String error = "";
		if (connection != null) {
			try {
				callableStatement = connection
					.prepareCall("{call SP_Actualizar_Status_Liquidaciones_Mail(?,?,?)}");
				
				callableStatement.setObject("TIPO_LIQUIDACION",
						Integer.valueOf(tipoLiquidacion), Types.INTEGER);
				callableStatement.setObject("P_STATUS",
						statusNuevo, Types.VARCHAR);
				callableStatement.setObject("P_ID_SALIDA_REPARTO",
						idSalidaReparto, Types.INTEGER);
				
				callableStatement.execute();
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						" ",
						"M1",
						"SurgiÃ³ un error al actualizar el status de la lista de liquidaciones a enviar : actualizarStatusLiquidacionesMail",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
				error = ex.getStackTrace().toString();
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return error;
	}

	/*public String actualizarStatusLiquidacionesMail(String statusNuevo,
			int idSalidaReparto) {
		connection = AccesoBD.AbrirConexionOTS();
		String error = "";
		if (connection != null) {
			try {
				// TipoLiquidacion:
				// 1 = SUBBODEGA
				// 2 = ORDENES DEJADAS
				// 3 = ORDENES RECOLECTADAS
				callableStatement = connection
					.prepareCall("{call SP_Actualizar_Status_Liquidaciones_Mail(?,?,?)}");
					callableStatement.setObject("P_STATUS", statusNuevo,
							Types.VARCHAR);
					callableStatement.setObject("TIPO_LIQUIDACION", 1,
							Types.VARCHAR);
					callableStatement.setObject("P_ID_SALIDA_REPARTO ", idSalidaReparto,
							Types.VARCHAR);
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						" ",
						"M1",
						"Surgió un error al actualizar el status de la lista de liquidaciones a enviar : actualizarStatusLiquidacionesMail",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
				error = ex.getStackTrace().toString();
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return error;
	}*/

	/*public String actualizarStatusLiquidacionesMail(String statusNuevo,
			int idSalidaReparto) {
		connection = AccesoBD.AbrirConexionOTS();
		String error = "";
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Actualizar_Status_Liquidaciones_Mail(?,?)}");
				callableStatement.setObject("P_STATUS_NUEVO", statusNuevo,
						Types.VARCHAR);
				callableStatement.setObject("P_ID_SALIDA_REPARTO",
						idSalidaReparto, Types.INTEGER);
				callableStatement.execute();

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						" ",
						"M1",
						"Surgió un error al actualizar el status de la lista de liquidaciones a enviar : actualizarStatusLiquidacionesMail",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
				error = ex.getStackTrace().toString();
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return error;
	}*/

	public int registrarEnvioMail(String usuario, int idllegadaProgramada,
			String de, String para, String asunto, String texto) {
		int idCorreo = -1;
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_RegistrarCorreoEnviado(?,?,?,?,?,?)}");
				callableStatement.setObject("P_ID_LLEGADA_PROGRAMADA",
						idllegadaProgramada, Types.BIGINT);
				callableStatement.setObject("P_DE", de, Types.VARCHAR);
				callableStatement.setObject("P_PARA", para, Types.VARCHAR);
				callableStatement.setObject("P_ASUNTO", asunto, Types.VARCHAR);
				callableStatement.setObject("P_TEXTO", texto, Types.VARCHAR);
				callableStatement
						.setObject("P_USUARIO", usuario, Types.VARCHAR);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					idCorreo = resultSet.getInt("IDCORREO");

				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(" ", "M1",
						"Surgió un error al Registrar el envio del mail",
						ex.getMessage(), idllegadaProgramada);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return idCorreo;
	}

	public String actualizaEnviadoReporteSubBodega(String listaItems,
			int idReporteMailDejadoSubBodega, int idCampania) {
		String error = "";
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_Actualiza_Enviado_Reporte_Sub_Bodega(?,?,?)}");

				callableStatement.setObject("p_LISTA_ITEMS", listaItems,
						Types.VARCHAR);
				callableStatement.setObject("p_ID_CAMPANIA", idCampania,
						Types.VARCHAR);
				callableStatement.setObject(
						"p_ID_REPORTE_EMAIL_DEJADO_SUB_BODEGA",
						idReporteMailDejadoSubBodega, Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					error = resultSet.getString("ERROR");

				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(" ", "M1",
						"Surgió un error al Registrar el envio del mail",
						ex.getMessage(), idReporteMailDejadoSubBodega);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return error;
	}

	/***
	 * 
	 * @author Javier Gallegos
	 * 
	 *         metodo que permite registrar los correos no enviados en la
	 *         pantalla de reportes, para ser enviados despues mediante un
	 *         proceso automático
	 * @param idUsuario
	 * @param de
	 * @param para
	 * @param asunto
	 * @param textoCuerpo
	 * @param idReporte
	 * @return regresa el id generado en la tabla PW_CORREOS_PENDIENTES que sera
	 *         utilizado para guardar el archivo fisicamente regresa 0 si
	 *         ocurrió un error
	 */
	public int registrarCorreoNoEnviado(int idUsuario, String de, String para,
			String asunto, String textoCuerpo, int idReporte,
			String nombreArchivo) {
		int nuevo = 0;
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_REGISTRAR_CORREO_NO_ENVIADO(?,?,?,?,?,?,?)}");
				callableStatement.setObject("P_ID_USUARIO", idUsuario,
						Types.INTEGER);
				callableStatement.setObject("P_ID_REPORTE", idReporte,
						Types.INTEGER);
				callableStatement.setObject("P_DE", de, Types.VARCHAR);
				callableStatement.setObject("P_PARA", para, Types.VARCHAR);
				callableStatement.setObject("P_ASUNTO", asunto, Types.VARCHAR);
				callableStatement.setObject("P_TEXTO_CUERPO", textoCuerpo,
						Types.VARCHAR);
				callableStatement.setObject("P_NOMBRE_ARCHIVO", nombreArchivo,
						Types.VARCHAR);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					nuevo = resultSet.getInt("insertado");

				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD("UCW001_2", "M1",
						"Surgió un error al Registrar el Correo no enviado",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return nuevo;
	}

	/***
	 * @autor Javier Gallegos
	 * @return devuelve los correos que se tienen almacenados en la tabla
	 *         PW_CORREOS_PENDIENTES, para ser enviados
	 */
	public List<ModelCorreoNoEnviado> obtenerCorreosNoEnviados() {
		connection = AccesoBD.AbrirConexionOTS();
		List<ModelCorreoNoEnviado> listaLiq = new ArrayList<ModelCorreoNoEnviado>();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_EncolarCorreosConsultaCorreosNoEnviados}");
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				while (resultSet.next()) {
					ModelCorreoNoEnviado r = new ModelCorreoNoEnviado();
					r.setAsunto(resultSet.getString("asunto"));
					r.setDe(resultSet.getString("de"));
					r.setIdCorreoPendiente(resultSet
							.getInt("ID_CORREO_PENDIENTE"));
					r.setIdReporte(resultSet.getInt("ID_REPORTE"));
					r.setPara(resultSet.getString("PARA"));
					r.setTexto(resultSet.getString("TEXTO_CUERPO"));
					r.setNombreReporte(resultSet.getString("NOMBRE_REPORTE"));
					r.setNombreArchivo(resultSet.getString("ARCHIVO_CORREO"));
					listaLiq.add(r);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"UCW001_2",
						"M1",
						"Surgió un error al obtener la lista de liquidaciones a enviar : obtenerListaLiquidacionesMail",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaLiq;
	}

	/***
	 * @author Javier Gallegos
	 * 
	 *         Metodo que permite eliminar de la tabla PW_CORREOS_PENDIENTES los
	 *         correos que ya fueron enviados
	 * 
	 * @param idCorreoPendiente
	 *            parametro del id en la tabla PW_CORREOS_PENDIENTES
	 * @return devuelve verdadero si fue exitosa la eliminacion, false en caso
	 *         contrario
	 */
	public boolean eliminarCorreoNoEnviado(int idCorreoPendiente) {
		connection = AccesoBD.AbrirConexionOTS();
		boolean exito = true;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_EncolarCorreosEliminaCorreosEnviados(?)}");
				callableStatement.setObject("P_ID_CORREO_PENDIENTE",
						idCorreoPendiente, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				if (resultSet.next()) {
					exito = resultSet.getBoolean("exito");
				}
				resultSet.close();
			} catch (SQLException ex) {
				exito = false;
				Utils.GuardarLogMensajeBD(
						"UCW001_2",
						"M1",
						"Surgió un error al eliminar el registro en la tabla PW_CORREOS_PENDIENTES",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return exito;
	}
}
