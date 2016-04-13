package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import com.datacode.avon_ots_ws.model.ModelAnalisisEfectivo;
import com.datacode.avon_ots_ws.model.ModelHistorialPorRepresentante;
import com.datacode.avon_ots_ws.model.ModelRepRecepcionCarga;
import com.datacode.avon_ots_ws.model.ModelRepTablaPremios;
import com.datacode.avon_ots_ws.model.ModelReporteItemsNoEntregadosEnAlmacen;
import com.datacode.avon_ots_ws.model.ModelReporteResumen;
import com.datacode.avon_ots_ws.model.ModelTablaAnalisisEfectivo;
import com.datacode.avon_ots_ws.model.ModelTablaAnalisisEfectivoLiquidacion;
import com.datacode.avon_ots_ws.model.ModelTablaHistorialPorRepresentante;
import com.datacode.avon_ots_ws.model.ModelTablaHistoricoDeRepresentantes;
import com.datacode.avon_ots_ws.model.DesgloceEfectivo;
import com.datacode.avon_ots_ws.model.DestinatarioReporte;
import com.datacode.avon_ots_ws.model.ModelDescargaEnrutado;
import com.datacode.avon_ots_ws.model.ModelReparto;
import com.datacode.avon_ots_ws.model.ModelTablaRelPedidosPrestados;
import com.datacode.avon_ots_ws.model.RelPedDejadosHeader;
import com.datacode.avon_ots_ws.model.ItemSubBodega;
import com.datacode.avon_ots_ws.model.LiquidacionRep;
import com.datacode.avon_ots_ws.model.NoAceptacionReparto;
import com.datacode.avon_ots_ws.model.NoAceptacionRepartoDetalle;
import com.datacode.avon_ots_ws.model.NoAceptacionRepartoTotal;
import com.datacode.avon_ots_ws.model.NoAceptacionRepartoTotalDetLibres;
import com.datacode.avon_ots_ws.model.NoAceptacionRepartoTotalDetRED;
import com.datacode.avon_ots_ws.model.PedidoPrestado;
import com.datacode.avon_ots_ws.model.RelPelDejadosDetalle;
import com.datacode.avon_ots_ws.model.Reporte;
import com.datacode.avon_ots_ws.model.RepresentanteReporte;
import com.datacode.avon_ots_ws.model.SalidaCamioneta;
import com.datacode.avon_ots_ws.model.SubBodegaAlmacen;
import com.datacode.avon_ots_ws.model.ModelManifiestoRutaEnCampania;
import com.datacode.avon_ots_ws.model.ModelTablaManifiestoRutaEnCampania;
import com.datacode.avon_ots_ws.model.TablaCajasMaltratadas;
import com.datacode.avon_ots_ws.model.TablaCargaRecibida;
import com.datacode.avon_ots_ws.model.TablaInfoCodFaltantes;

public class ReportesController {

	private ResultSet resultSet;
	private CallableStatement callableStatement;
	private Connection connection;

	public ReportesController() {

	}

	public String obtenerObservacionesReporteRecepcionCarga(Integer idZona,
			Integer idCampania, int idUsuario) {
		String observaciones = "";

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteRecepcionCargaObtenerObservaciones(?,?)}");
				callableStatement.setObject("p_idCampania", idCampania,
						Types.INTEGER);
				callableStatement.setObject("p_idZona", idZona, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					observaciones = resultSet.getString("OBSERVACIONES");
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M3",
						"Surgió un error al obtener las observaciones del reporte de recepcion de carga",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return observaciones;
	}

	public boolean actualizaObservacionesReporteRecepcionCarga(Integer idZona,
			Integer idCampania, int idUsuario, String observaciones) {
		boolean resultado = true;

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ReporteRecepcionCargaActualizarObservaciones(?,?,?)}");
				callableStatement.setObject("p_idCampania", idCampania,
						Types.INTEGER);
				callableStatement.setObject("p_idZona", idZona, Types.INTEGER);
				callableStatement.setObject("p_observaciones", observaciones,
						Types.VARCHAR);
				boolean res = AccesoBD.execute(callableStatement);

				resultSet.close();
			} catch (SQLException ex) {
				resultado = false;
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M3",
						"Surgió un error al actualizar las observaciones del reporte de recepcion de carga",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<PedidoPrestado> obtenerEncabezadoPedidosEntregadosGerenteZonal(
			Integer idZona, Integer idCampania, int idUsuario) {
		List<PedidoPrestado> listaPedidosPrestados = new ArrayList<PedidoPrestado>();
		PedidoPrestado pedidoPrestado = null;
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_Reporte_PedidosEntregadosGteZonal_Encabezado(?,?)}");
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					pedidoPrestado = new PedidoPrestado();
					pedidoPrestado.setZona(resultSet.getString("ZONA"));
					pedidoPrestado.setCampania(resultSet
							.getString("CAMPANIA_ANIO"));
					pedidoPrestado.setEntregado(resultSet
							.getString("NOMBRE_GERENTE_ZONAL"));
					listaPedidosPrestados.add(pedidoPrestado);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M3",
						"Surgió un error al obtener los Pedidos Prestados. Método: obtenerEncabezadoPedidosEntregadosGerenteZonal",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaPedidosPrestados;
	}

	public List<ModelTablaRelPedidosPrestados> obtenerPedidosEntregadosGerenteZonal(
			Integer idZona, Integer idCampania, int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<ModelTablaRelPedidosPrestados> pedidosPrestados = new ArrayList<ModelTablaRelPedidosPrestados>();
		ModelTablaRelPedidosPrestados pedidoPrestado = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_Reporte_PedidosEntregadosGteZonal_Detalle(?,?)}");
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					pedidoPrestado = new ModelTablaRelPedidosPrestados();
					pedidoPrestado.setCont(resultSet.getInt("NUM"));
					pedidoPrestado.setRegistro(resultSet.getString("REGISTRO"));
					pedidoPrestado.setNombre(resultSet.getString("NOMBRE"));
					pedidoPrestado.setRuta(resultSet.getString("CLAVE_RUTA"));
					pedidoPrestado.setSalidaReparto(resultSet
							.getString("FECHA_SALIDA_REPARTO"));
					pedidoPrestado.setCajas(resultSet.getInt("CANTIDAD_CAJAS"));
					pedidoPrestado.setUnitarios(resultSet.getInt("UNITARIOS"));
					pedidoPrestado.setImporte(resultSet.getDouble("IMPORTE"));
					pedidoPrestado.setPremio(resultSet.getInt("PREMIOS"));
					pedidoPrestado.setCausa(resultSet.getString("CAUSA"));
					pedidosPrestados.add(pedidoPrestado);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M3",
						"Surgió un error al obtener los Pedidos Prestados. Método: obtenerPedidosPrestados",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return pedidosPrestados;
	}

	public List<SubBodegaAlmacen> obtenerListaSubbodegasItemsDejados(
			int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<SubBodegaAlmacen> resultado = new ArrayList<SubBodegaAlmacen>();
		SubBodegaAlmacen res = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Obtener_Lista_Subbodegas_Items_Dejados}");
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new SubBodegaAlmacen();

					res.setIdSubbodegaAlmacen(resultSet
							.getInt("ID_SUB_BODEGA_ALMACEN"));
					res.setClave(resultSet.getString("CLAVE_SUBODEGA"));
					res.setDescripcion(resultSet.getString("NOMBRE_SUBBODEGA"));
					res.setCorreoResponsable(resultSet
							.getString("CORREO_RESPONSABLE"));
					res.setDescUserResponsableSubbodega(resultSet
							.getString("NOMBRE_RESPONSABLE"));
					res.setIdUserResponsableSubbodega(resultSet
							.getInt("ID_USUARIO_RES"));
					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M4",
						"Surgió un error al obtener las subbodegas con items dejados. Método: obtenerListaSubbodegasItemsDejados",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<ItemSubBodega> obtenerListaItemsDejadosXSubbodega(
			int idSubBodega, int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<ItemSubBodega> resultado = new ArrayList<ItemSubBodega>();
		ItemSubBodega res = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Obtener_Lista_Items_Dejados_XSubbodega(?)}");
				callableStatement.setObject("P_ID_SUBBODEGA_ALMACEN",
						idSubBodega, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new ItemSubBodega();
					res.setAccount(resultSet.getString("ACCOUNT"));
					res.setCampania(resultSet.getString("CAMPANIA"));
					res.setCodigo(resultSet.getString("CODIGO_BARRAS"));
					res.setIdTipoItem(resultSet.getInt("ID_TIPO_ITEM"));
					res.setNombreRepresentante(resultSet
							.getString("NOMBRE_REPRESENTANTE"));
					res.setOrden(resultSet.getString("ORDEN"));
					res.setTipoItem(resultSet.getString("TIPO_DE_ITEM"));
					res.setZona(resultSet.getString("CLAVE_ZONA"));
					res.setMontoCobrar(resultSet.getDouble("MONTO_A_COBRAR"));
					res.setDescripcion(resultSet.getString("DESCRIPCION"));
					res.setTipoArchivo(resultSet.getString("TIPO_ARCHIVO"));
					res.setIdItem(resultSet.getInt("ID_ITEM"));
					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M5",
						"Surgió un error al obtener las subbodegas con items dejados. Método: obtenerListaItemsDejadosXSubbodega",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<SubBodegaAlmacen> devuelveSubBodegasSalidaReparto(
			int idUsuario, int idSalidaReparto) {

		connection = AccesoBD.AbrirConexionOTS();
		List<SubBodegaAlmacen> resultado = new ArrayList<SubBodegaAlmacen>();
		SubBodegaAlmacen res = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PW_DEVUELVE_SUB_BODEGAS_SALIDA_REPARTO(?)}");
				callableStatement.setObject("p_ID_SALIDA_REPARTO",
						idSalidaReparto, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new SubBodegaAlmacen();

					res.setIdSubbodegaAlmacen(resultSet.getInt("ID_SUB_BODEGA"));
					res.setDescripcion(resultSet.getString("DESCRIPCION"));
					res.setDescUserResponsableSubbodega(resultSet
							.getString("USUARIO"));

					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M4",
						"Surgió un error al obtener las subbodegas con items dejados. Método: obtenerListaSubbodegasItemsDejados",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<ItemSubBodega> devuelveOrdenesDejadasSubBodega(int idUsuario,
			int idSalidaReparto, int idSubbodega) {

		connection = AccesoBD.AbrirConexionOTS();
		List<ItemSubBodega> resultado = new ArrayList<ItemSubBodega>();
		ItemSubBodega res = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PW_DEVUELVE_ORDENES_DEJADAS_SUB_BODEGA(?,?)}");
				callableStatement.setObject("p_ID_SALIDA_REPARTO",
						idSalidaReparto, Types.INTEGER);
				callableStatement.setObject("p_ID_SUB_BODEGA", idSubbodega,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new ItemSubBodega();
					res.setZona(resultSet.getString("ZONA"));
					res.setAccount(resultSet.getString("ACCOUNT"));
					res.setCampania(resultSet.getString("CAMPAÑA"));
					res.setOrden(resultSet.getString("ORDEN"));
					res.setTipoItem(resultSet.getString("TIPO_DE_ITEM"));
					res.setCodigo(resultSet.getString("CODIGO"));
					res.setMontoCobrar(resultSet.getDouble("MONTO_A_COBRAR"));
					res.setDescripcion(resultSet.getString("DESCRIPCION"));
					res.setQuantityToCollect(resultSet
							.getInt("QUANTITY_TO_COLLECT"));
					res.setCollectedQuantity(resultSet
							.getInt("COLLECTED_QUANTITY"));
					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M4",
						"Surgió un error al OBTENER LAS ORDENES DEJADAS EN SUBBODEGA. Método: devuelveOrdenesDejadasSubBodega",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<DestinatarioReporte> obtenerDestinatariosReportes(
			String nombreReporte, int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<DestinatarioReporte> resultado = new ArrayList<DestinatarioReporte>();
		DestinatarioReporte res = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_DEVOLVER_DESTINATARIOS_POR_REPORTE(?)}");
				callableStatement.setObject("P_NOMBRE_REPORTE", nombreReporte,
						Types.VARCHAR);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new DestinatarioReporte();
					res.setMail(resultSet.getString("CORREO_ELECTRONICO"));
					res.setReporte(resultSet.getString("REPORTE"));
					res.setTipoDestinatario(resultSet
							.getString("TIPO_DESTINATARIO"));

					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M6",
						"Surgió un error al obtener los Destinatarios. Método: obtenerDestinatariosReportes",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<DestinatarioReporte> obtenerDestinatariosReportesPorTipoReporte(
			String p_cveReporteTipo, int p_idLDC, int p_idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<DestinatarioReporte> resultado = new ArrayList<DestinatarioReporte>();
		DestinatarioReporte res = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_ObtenerDestinatariosTipoReporte(?,?)}");
				callableStatement.setObject("P_CLAVE_REPORTE_TIPO",
						p_cveReporteTipo, Types.VARCHAR);
				callableStatement.setObject("P_ID_LDC", p_idLDC, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new DestinatarioReporte();
					res.setMail(resultSet.getString("CORREO_ELECTRONICO"));
					res.setTipoDestinatario(resultSet
							.getString("TIPO_DESTINATARIO"));

					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M6",
						"Surgió un error al obtener los Destinatarios. Método: obtenerDestinatariosReportesPorTipoReporte",
						ex.getMessage(), p_idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<RepresentanteReporte> obtenerRepresentantesReportes(
			String account, int idLDC, int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<RepresentanteReporte> resultado = new ArrayList<RepresentanteReporte>();
		RepresentanteReporte res = null;

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_Devolver_ConsultaRepresentante(?,?)}");
				callableStatement.setObject("P_IDLDC", idLDC, Types.INTEGER);
				callableStatement
						.setObject("P_ACCOUNT", account, Types.VARCHAR);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new RepresentanteReporte();
					res.setBancoFolio(resultSet.getString("BANCO_FOLIOS"));
					res.setCampania(resultSet.getString("CAMPANIA"));
					res.setCausaDevolucion(resultSet
							.getString("CAUSA_DEVOLUCION"));
					res.setDireccionRepresentante(resultSet
							.getString("DIRECCION"));
					res.setEntregadoEn(resultSet.getString("ENTREGADO_EN"));
					res.setFechaDevolucion(resultSet
							.getDate("FECHA_DEVOLUCION"));
					res.setFechaEntrega(resultSet.getDate("FECHA_SI_ENTREGADA"));
					res.setFechaPago(resultSet.getDate("FECHA_PAGO"));
					res.setGps(resultSet.getString("GPS_ENTREGA"));
					res.setIdRepresentante(resultSet.getInt("ID_REPRESENTANTE"));
					res.setMontoPagado(resultSet.getDouble("MONTO_PAGADO"));
					res.setNombreRepresentante(resultSet.getString("NOMBRE"));
					res.setOrder(resultSet.getString("CLAVE_ORDEN"));
					res.setTipoPago(resultSet.getString("TIPO_PAGO"));
					res.setFotoDomicilio(resultSet.getBytes("FOTO_DOMICILIO"));
					res.setRegistroRepresentante(resultSet
							.getString("REGISTRO"));

					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M7",
						"Surgió un error al obtener los Representantes. Método: obtenerRepresentantesReportes",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<ModelReporteItemsNoEntregadosEnAlmacen> obtenerItemsNoEntregadosEnAlmacen(
			String idRuta, String idCampania, String idZona, int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<ModelReporteItemsNoEntregadosEnAlmacen> resultado = new ArrayList<ModelReporteItemsNoEntregadosEnAlmacen>();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_Devolver_ConsultaItemsNoEntregadosEnAlmacen(?,?,?)}");
				callableStatement.setObject("P_IDZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_IDRUTA", idRuta, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					ModelReporteItemsNoEntregadosEnAlmacen res = new ModelReporteItemsNoEntregadosEnAlmacen();
					res.setCodigoItem(resultSet.getString("codigo_barras"));
					res.setDescItem(resultSet.getString("itemdesc"));
					res.setFechaHoraDevolucion(resultSet
							.getDate("fecha_estatus"));
					res.setFechaHoraDevolucionS(df.format(new Date(resultSet
							.getTimestamp("fecha_estatus").getTime())));
					res.setNombreChofer(resultSet.getString("usuario"));
					res.setNombreRepresentante(resultSet.getString("nombrerep"));
					res.setNumeroOrden(resultSet.getString("numeroorden"));
					res.setRegistro(resultSet.getString("registro"));
					res.setRuta(resultSet.getString("rutades"));
					res.setCampania(resultSet.getString("campania"));
					res.setZona(resultSet.getString("zona"));

					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M7",
						"Surgió un error al obtener los iItems no entregados en almacen. Método: obtenerItemsNoEntregadosEnAlmacen",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene el encabezado y pie del reporte de Manifiesto de Rutas
	 * 
	 * @author jorge.torner
	 * @since 31/01/2012
	 * @param p_idRuta
	 *            - Id de la ruta
	 * @param p_idCampania
	 *            - Id de la campaña
	 * @param p_idUsuario
	 *            - Id del usuario
	 * @param p_idLDC
	 *            - Id del cedis
	 * @return ModelManifiestoRutaEnCampania
	 */
	public ModelManifiestoRutaEnCampania obtenerReporteManifiestoRutaEnCampania(
			int p_idRuta, int p_idCampania, int p_idUsuario, int p_idLDC) {
		ModelManifiestoRutaEnCampania rep = null;

		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PWA_Reporte_ManifiestoRutaEnCampania_Encabezado(?,?,?)}");
				cs.setObject("P_ID_CAMPANIA", p_idCampania, Types.INTEGER);
				cs.setObject("P_ID_RUTA", p_idRuta, Types.INTEGER);
				cs.setObject("P_ID_LDC", p_idLDC, Types.INTEGER);

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				if (rs.next()) {
					rep = new ModelManifiestoRutaEnCampania();
					rep.setCajasTotales(rs.getString("CAJAS_TOTALES"));
					rep.setCampania_Anio(rs.getString("CAMPANIA_ANIO"));
					rep.setFecha(rs.getString("FECHA"));
					rep.setNombreAyudante(rs.getString("NOMBRE_AYUDANTE"));
					rep.setNombreChofer(rs.getString("NOMBRE_CHOFER"));
					rep.setOrdenesTotales(rs.getString("ORDENES_TOTALES"));
					rep.setPoblacionPrincipal(rs
							.getString("POBLACION_PRINCIPAL"));
					rep.setPremiosTotales(rs.getString("PREMIOS_TOTALES"));
					rep.setRuta(rs.getString("RUTA"));
					rep.setUnitariosTotales(rs.getString("UNITARIOS_TOTALES"));
					rep.setZona(rs.getString("ZONA"));
				}
				rs.close();
				AccesoBD.CerrarStatement(cs);

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M1",
						"No se pudo consultar la información del reporte. Método: obtenerReporteManifiestoRutaEnCampania",
						ex.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}

		return rep;
	}

	/**
	 * Obtiene el detalle del reporte de Manifiesto de Rutas
	 * 
	 * @author jorge.torner
	 * @since 31/01/2012
	 * @param p_idRuta
	 *            - Id de la ruta
	 * @param p_idCampania
	 *            - Id de la campaña
	 * @param p_idUsuario
	 *            - Id del usuario
	 * @param p_idLDC
	 *            - Id del cedis
	 * @return ModelTablaManifiestoRutaEnCampania[]
	 */
	public ModelTablaManifiestoRutaEnCampania[] obtenerReporteManifiestoRutaEnCampania_Detalle(
			int p_idRuta, int p_idCampania, int p_idUsuario, int p_idLDC) {
		List<ModelTablaManifiestoRutaEnCampania> listaRep = new ArrayList<ModelTablaManifiestoRutaEnCampania>();
		Connection con = AccesoBD.AbrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_PWA_Reporte_ManifiestoRutaEnCampania_Detalle(?,?,?)}");
				cs.setObject("P_ID_CAMPANIA", p_idCampania, Types.INTEGER);
				cs.setObject("P_ID_RUTA", p_idRuta, Types.INTEGER);
				cs.setObject("P_ID_LDC", p_idLDC, Types.INTEGER);

				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
				while (rs.next()) {
					ModelTablaManifiestoRutaEnCampania det = new ModelTablaManifiestoRutaEnCampania();
					det.setAccount(rs.getString("ACCOUNT"));
					det.setAddress1(rs.getString("ADDRESS1"));
					det.setAddress2(rs.getString("ADDRESS2"));
					det.setAddress3(rs.getString("ADDRESS3"));
					det.setCajas(rs.getString("CANT_CAJAS"));
					det.setEntregado(rs.getString("ENTREGADO"));
					det.setName(rs.getString("NAME"));
					det.setPremios(rs.getString("CANT_PREMIOS"));
					det.setSecuenciaEntrega(rs.getString("SECUENCIA_ENTREGA"));
					det.setToPay(rs.getString("TO_PAY"));
					det.setUnitarios(rs.getString("CANT_UNITARIOS"));
					det.setVisitado(rs.getString("VISITADO"));

					listaRep.add(det);
				}
				rs.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M2",
						"No se pudo consultar la información del reporte. Método: obtenerReporteManifiestoRutaEnCampania_Detalle",
						ex.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarStatement(cs);
				AccesoBD.CerrarConexion(con);
			}
		}

		return listaRep.toArray(new ModelTablaManifiestoRutaEnCampania[0]);
	}

	public SalidaCamioneta obtenerHeadersSalidaCamioneta(int zona, int ruta,
			int campania, Date fecha, int idLDC, int idUsuario) {

		java.sql.Date fechaS = null;

		if (fecha != null) {
			long t = fecha.getTime();
			fechaS = new java.sql.Date(t);

		}
		connection = AccesoBD.AbrirConexionOTS();
		SalidaCamioneta resultado = new SalidaCamioneta();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_CONTROL_SALIDA_CAMIONETA_A(?,?,?,?,?)}");
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				callableStatement.setObject("P_ID_ZONA", zona, Types.INTEGER);
				callableStatement.setObject("P_ID_RUTA", ruta, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_FECHA", fechaS, Types.DATE);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				int cont = 0;
				while (resultSet.next()) {

					switch (cont) {
					case 0:
						resultado.setCampania(resultSet.getString("VALOR"));
						break;
					case 1:
						resultado.setOrdenes(resultSet.getString("VALOR"));
						break;
					case 2:
						resultado.setCajas(resultSet.getString("VALOR"));
						break;
					case 3:
						resultado.setPremios(resultSet.getString("VALOR"));
						break;
					case 4:
						resultado.setUnitarios(resultSet.getString("VALOR"));
						break;
					case 5:
						resultado.setNombreChofer(resultSet.getString("VALOR"));
						break;
					case 6:
						resultado.setNombreAyudante(resultSet
								.getString("VALOR"));
						break;
					case 7:
						resultado.setZona(resultSet.getString("VALOR"));
						break;
					case 8:
						resultado.setRuta(resultSet.getString("VALOR"));
						break;
					case 9:
						resultado.setFechaSalidaReparto(resultSet
								.getString("VALOR"));
						break;
					}
					cont++;
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M8",
						"Surgió un error al obtener los headers de la salida camioneta. Método obtenerHeadersSalidaCamioneta",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public SalidaCamioneta obtenerKilometrosHorasSalidaCamioneta(int zona,
			int ruta, int campania, Date fecha, int idUsuario) {
		java.sql.Date fechaS = null;

		if (fecha != null) {
			long t = fecha.getTime();
			fechaS = new java.sql.Date(t);
		}
		connection = AccesoBD.AbrirConexionOTS();
		SalidaCamioneta resultado = new SalidaCamioneta();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_CONTROL_SALIDA_CAMIONETA_C(?,?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", zona, Types.INTEGER);
				callableStatement.setObject("P_ID_RUTA", ruta, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_FECHA", fechaS, Types.DATE);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				int cont = 0;
				while (resultSet.next()) {

					switch (cont) {
					case 0:
						resultado.setHrPrimerVisita(resultSet
								.getString("VALOR"));
						resultado.setKmPrimerVisita(resultSet
								.getDouble("VALORDOUBLE"));
						break;
					case 1:
						resultado.setHrRegresoBodega(resultSet
								.getString("VALOR"));
						resultado.setKmRegresoBodega(resultSet
								.getDouble("VALORDOUBLE"));
						break;
					case 2:
						resultado.setHrSalidaReparto(resultSet
								.getString("VALOR"));
						resultado.setKmSalidaReparto(resultSet
								.getDouble("VALORDOUBLE"));
						break;
					case 3:
						resultado.setHrUltimaVisita(resultSet
								.getString("VALOR"));
						resultado.setKmUltimaVisita(resultSet
								.getDouble("VALORDOUBLE"));
						break;

					}
					cont++;
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M9",
						"Surgió un error al obtener los headers de la salida camioneta. Método: obtenerKilometrosHorasSalidaCamioneta",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<DesgloceEfectivo> obtenerDesgloceEfectivo(int zona, int ruta,
			int campania, Date fecha, int idUsuario) {
		java.sql.Date fechaS = null;

		if (fecha != null) {
			long t = fecha.getTime();
			fechaS = new java.sql.Date(t);

		}
		connection = AccesoBD.AbrirConexionOTS();
		List<DesgloceEfectivo> resultado = new ArrayList<DesgloceEfectivo>();
		DesgloceEfectivo res = null;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_CONTROL_SALIDA_CAMIONETA_E(?,?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", zona, Types.INTEGER);
				callableStatement.setObject("P_ID_RUTA", ruta, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_FECHA", fechaS, Types.DATE);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new DesgloceEfectivo();
					res.setCantidad(resultSet.getInt("CANTIDAD"));
					res.setDenominacion(resultSet.getString("DENOMINACION"));
					res.setBillete_Moneda(resultSet.getString("BILLETE_MONEDA"));
					// res.setTotal(resultSet.getDouble("TOTAL"));
					res.setSubTotal(resultSet.getDouble("SUBTOTAL"));

					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M10",
						"Surgió un error al obtener el desgloce de efectivo. Método: obtenerDesgloceEfectivo",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	public List<LiquidacionRep> obtenerLiquidacionesSalidaCamioneta(int zona,
			int ruta, int campania, Date fecha, int idUsuario) {
		java.sql.Date fechaS = null;

		if (fecha != null) {
			long t = fecha.getTime();
			fechaS = new java.sql.Date(t);
		}
		connection = AccesoBD.AbrirConexionOTS();
		List<LiquidacionRep> resultado = new ArrayList<LiquidacionRep>();
		LiquidacionRep res = null;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_CONTROL_SALIDA_CAMIONETA_F(?,?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", zona, Types.INTEGER);
				callableStatement.setObject("P_ID_RUTA", ruta, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_FECHA", fechaS, Types.DATE);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new LiquidacionRep();
					res.setCajas(resultSet.getInt("CAJAS"));
					res.setCods(resultSet.getDouble("CODS"));
					res.setConcepto(resultSet.getString("CONCEPTO"));
					res.setPremios(resultSet.getInt("PREMIOS"));
					res.setUnitarios(resultSet.getInt("UNITARIOS"));
					res.setRecibio(resultSet.getString("RECIBIO"));
					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los Representantes. Método: obtenerLiquidacionesSalidaCamioneta",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	/***
	 * @author Javier.Gallegos metodo que consulta el detalle de los pedidos
	 *         dejados dejados en subbodega
	 * @param ruta
	 *            id de la ruta
	 * @param campania
	 *            id de la campania
	 * @param idUsuario
	 *            id del usuario
	 * @return HeaderRelPedDejados header de los pedidos dejados
	 */
	public List<RelPelDejadosDetalle> obtenerRelPedDejadosDetalle(int ruta,
			int campania, int idLdc, int idUsuario, int idSubBodega) {

		connection = AccesoBD.AbrirConexionOTS();
		List<RelPelDejadosDetalle> resultado = new ArrayList<RelPelDejadosDetalle>();
		RelPelDejadosDetalle res = null;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_Reporte_PedidosDejadosSubBodega_Detalle(?,?,?,?)}");
				callableStatement.setObject("P_ID_RUTA", ruta, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLdc, Types.INTEGER);
				callableStatement.setObject("P_ID_SUBBODEGA", idSubBodega,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res = new RelPelDejadosDetalle();
					res.setCajas(resultSet.getInt("CANT_CAJAS"));
					res.setCausaDev(resultSet.getInt("CAUSA_DEVOLUCION"));
					res.setImporte(resultSet.getDouble("IMPORTE"));
					res.setNombre(resultSet.getString("NOMBRE"));
					res.setNumero(resultSet.getInt("NUM"));
					res.setPremios(resultSet.getInt("CANT_PREMIOS"));
					res.setRegistro(resultSet.getString("REGISTRO"));
					res.setRuta(resultSet.getString("RUTA"));
					res.setUnitarios(resultSet.getInt("CANT_UNITARIOS"));
					resultado.add(res);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al tratar de obtener los pedidos dejados ",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}

	/***
	 * @author Javier.Gallegos metodo que consulta el header de los pedidos
	 *         dejados en subbodega
	 * @param ruta
	 *            id de la ruta
	 * @param campania
	 *            id de la campania
	 * @param idUsuario
	 *            id del usuario
	 * @return HeaderRelPedDejados header de los pedidos dejados
	 */
	public RelPedDejadosHeader obtieneHeaderRelPedDejados(int ruta,
			int campania, int idLdc, int idUsuario, int idSubBodega) {

		connection = AccesoBD.AbrirConexionOTS();
		RelPedDejadosHeader res = new RelPedDejadosHeader();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_Reporte_PedidosDejadosSubBodega_Encabezado(?,?,?,?)}");
				callableStatement.setObject("P_ID_RUTA", ruta, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLdc, Types.INTEGER);
				callableStatement.setObject("P_ID_SUBBODEGA", idSubBodega,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					res.setZona((resultSet.getString("ZONA")));
					res.setRuta((resultSet.getString("RUTA")));
					res.setCampania((resultSet.getString("CAMPANIA_ANIO")));
					res.setNombreAyudante((resultSet
							.getString("NOMBRE_AYUDANTE")));
					res.setNombreChofer((resultSet.getString("NOMBRE_CHOFER")));
					res.setNombreEntrega((resultSet
							.getString("NOMBRE_QUIEN_ENTREGA")));
					res.setNombreRecibe((resultSet
							.getString("NOMBRE_QUIEN_RECIBE")));
					res.setPoblacion((resultSet
							.getString("POBLACION_PRINCIPAL")));
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener EL HEADER DEL REPORTE pEDIDOS DEJADOS",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return res;
	}

	/***
	 * @author Javier.Gallegos metodo que consulta los datos de descarga y
	 *         enrutado
	 * @param ruta
	 *            id de la ruta
	 * @param campania
	 *            id de la campania
	 * @param idUsuario
	 *            id del usuario
	 * @return HeaderRelPedDejados header de los pedidos dejados
	 */
	public List<ModelDescargaEnrutado> obtieneDatosDescargaEnrutado(
			Integer zona, Integer campania, Integer anioCampania,
			int idUsuario, int idLDC) {

		connection = AccesoBD.AbrirConexionOTS();
		List<ModelDescargaEnrutado> res = new ArrayList<ModelDescargaEnrutado>();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_Reporte_DescargaYEnrutado(?,?,?,?)}");
				callableStatement.setObject("P_ZONA", zona, Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				callableStatement.setObject("P_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_ANIO_CAMPANIA", anioCampania,
						Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					ModelDescargaEnrutado r = new ModelDescargaEnrutado();
					r.setCampania(resultSet.getString("CAMPANIA"));
					r.setFechaLlegadaProg(resultSet
							.getString("FECHA_LLEGADA_PROGRAMADA"));
					r.setHrLlegadaProg(resultSet
							.getString("HORA_LLEGADA_PROGRAMADA"));
					r.setFechaLlegadaReal(resultSet
							.getString("FECHA_LLEGADA_REAL"));
					r.setHrLlegadaReal(resultSet.getString("HORA_LLEGADA_REAL"));
					r.setFechaSalidaReal(resultSet
							.getString("FECHA_SALIDA_REAL_AVON"));
					r.setHrSalidaReal(resultSet
							.getString("HORA_SALIDA_REAL_AVON"));
					r.setTiempoEnCedis(resultSet.getString("TIEMPO_EN_CEDIS"));
					r.setZona(resultSet.getString("ZONA"));
					r.setOrdenesZonaLlegada(resultSet
							.getString("ORDENES_ZONA_LLEGADA"));
					r.setCajasZonaLlegada(resultSet
							.getString("CAJAS_ZONA_LLEGADA"));
					r.setCajasOtraZonaLlegada(resultSet
							.getString("CAJAS_OTRA_ZONA_LLEGADA"));
					r.setPremiosZonaLlegada(resultSet
							.getString("PREMIOS_ZONA_LLEGADA"));
					r.setOtrosZonaLlegada(resultSet
							.getString("OTROS_ZONA_LLEGADA"));
					r.setTotalLlegada(resultSet.getString("TOTAL_LLEGADA"));
					r.setOrdenesZonaDescargados(resultSet
							.getString("ORDENES_ZONA_DESCARGADOS"));
					r.setCajasZonaDescargados(resultSet
							.getString("CAJAS_ZONA_DESCARGADOS"));
					r.setPremiosZonaDescargados(resultSet
							.getString("PREMIOS_ZONA_DESCARGADOS"));
					r.setOtrosZonaDescargados(resultSet
							.getString("OTROS_ZONA_DESCARGADOS"));
					r.setOrdenesOtraZonaDescargados(resultSet
							.getString("ORDENES_OTRA_ZONA_DESCARGADOS"));
					r.setCajasOtraZonaDescargados(resultSet
							.getString("CAJAS_OTRA_ZONA_DESCARGADOS"));
					r.setPremiosOtraZonaDescargados(resultSet
							.getString("PREMIOS_OTRA_ZONA_DESCARGADOS"));
					r.setOtrosOtraZonaDescargados(resultSet
							.getString("OTROS_OTRA_ZONA_DESCARGADOS"));
					r.setTotalDescargados(resultSet
							.getString("TOTAL_DESCARGADOS"));
					r.setStatusCarga(resultSet.getString("STATUS_CARGA"));
					r.setComentarios(resultSet.getString("COMENTARIOS"));
					r.setHrIniDesgarga(resultSet
							.getString("HORA_INICIO_DESCARGA"));
					r.setHrFinDescarga(resultSet.getString("HORA_FIN_DESCARGA"));
					r.setTiempoTotalDescarga(resultSet
							.getString("TIEMPO_TOTAL_DESCARGA"));
					r.setCantidadPersonasDescarga(resultSet
							.getString("CANTIDAD_PERSONAS_DESCARGA"));
					r.setProductividadDescarga(resultSet
							.getString("PRODUCTIVIDAD_DESCARGA"));
					r.setHrIniEnrutado(resultSet
							.getString("HORA_INICIO_ENRUTADO"));
					r.setHrFinEnrutado(resultSet.getString("HORA_FIN_ENRUTADO"));
					r.setTiempoTotalEnrutado(resultSet
							.getString("TIEMPO_TOTAL_ENRUTADO"));
					r.setCantidadPersonasEnrutado(resultSet
							.getString("CANTIDAD_PERSONAS_ENRUTADO"));
					r.setProductividadEnrutado(resultSet
							.getString("PRODUCTIVIDAD_ENRUTADO"));
					res.add(r);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Descarga y Enrutado",
						ex.getMessage(), idUsuario);
				res = null;
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return res;
	}

	/***
	 * @author Javier Gallegos
	 * 
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<ModelReparto> obtieneDatosReparto(Integer zona, int campania,
			int anioCampania, int idLdc, int idUsuario) {

		connection = AccesoBD.AbrirConexionOTS();
		List<ModelReparto> res = new ArrayList<ModelReparto>();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_REPARTO_ZONA_CAMPANIA(?,?,?,?,?)}");
				callableStatement.setObject("P_ZONA", zona, Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLdc, Types.INTEGER);
				callableStatement.setObject("P_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_ANIO_CAMPANIA", anioCampania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_USUARIO", idUsuario,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					ModelReparto r = new ModelReparto();
					r.setZona(resultSet.getString("ZONA"));
					r.setAnio(resultSet.getString("ANIO_CAMPANIA"));
					r.setCampania(resultSet.getString("CAMPANIA"));
					r.setRuta(resultSet.getString("CLAVE_RUTA"));
					r.setPoblacion(resultSet.getString("POBLACION_COLONIA"));
					r.setTipoRuta(resultSet.getString("DESCRIPCION"));
					r.setFechaReparto(resultSet
							.getString("FECHA_PRIMER_DIA_REPARTO"));
					r.setDiaReparto(resultSet.getString("DIA_DE_REPARTO"));
					r.setNombreChofer(resultSet.getString("NOMBRE_CHOFER"));
					r.setNombreAyudante(resultSet.getString("NOMBRE_AYUDANTE"));

					r.setEnvOrdenesCReparto(resultSet
							.getInt("ORDENES_REPARTO_DIRECTO"));
					r.setEnvOrdenesSReparto(resultSet
							.getInt("ORDENES_SIN_REPARTO_DIRECTO"));
					r.setEnvPrimerasOrdenes(resultSet
							.getInt("PRIMERAS_ORDENES"));
					r.setEnvOrdenesTotales(resultSet.getInt("ORDENES_TOTALES"));
					r.setEnvCajasTotales(resultSet.getInt("CAJAS_TOTALES"));
					r.setEnvPremios(resultSet
							.getInt("PREMIOS_UNITARIOS_EN_ENTREGA"));

					r.setKmInicioRuta(resultSet.getLong("KM_INICIO_RUTA"));
					r.setHrInicioRuta(resultSet.getString("INICIO_RUTA_FECHA"));

					r.setKmPrimeraEntrega(resultSet.getLong("KM_1ER_ENTREGA"));
					r.setHrPrimeraEntrega(resultSet
							.getString("HORA_1ER_ENTREGA"));

					r.setKmUltimaEntrega(resultSet.getLong("KM_ULTIMA_ENTREGA"));
					r.setHrUltimaEntrega(resultSet
							.getString("HORA_ULTIMA_ENTREGA"));

					r.setKmFinRuta(resultSet.getLong("KM_FIN_RUTA"));
					r.setHrFinRuta(resultSet.getString("FIN_RUTA_HORA"));

					r.setTiempoRepartoGlobal(resultSet
							.getString("TIEMPO_REPARTO_GLOBAL"));
					r.setKmRepartoGlobal(resultSet.getLong("KM_REPARTO_GLOBAL"));

					r.setTiempoRepartoEfectivo(resultSet
							.getString("TIEMPO_REPARTO_EFECTIVO"));
					r.setKmRepartoEfectivo(resultSet
							.getLong("KM_REPARTO_EFECTIVO"));

					r.setTiempoArrastre(resultSet.getString("TIEMPO_ARRASTRE"));
					r.setKmArrastre(resultSet.getLong("KM_ARRASTRE"));

					r.setProduMinutos(resultSet
							.getString("TIEMPO_VISITA_PRODUCTIVIDAD"));
					r.setProduOrdHr(resultSet
							.getString("ORDEN_HORA_PRODUCTIVIDAD"));

					r.setLitros(resultSet.getDouble("LITROS_GASOLINA"));
					r.setKmLitros(resultSet.getDouble("KM_POR_LITRO"));

					r.setEntPrimeraOrden(resultSet
							.getInt("CANTIDAD_PRIMERA_ORDEN_ENTREGADA"));
					r.setEntOrdenesEstablecidas(resultSet
							.getInt("ORDENES_ESTABLECIDAS"));
					r.setEntCajasTotales(resultSet
							.getInt("CAJAS_TOTALES_REPARTIDAS"));
					r.setEntPremios(resultSet
							.getInt("PREMIOS_UNITARIOS_ENTREGADOS"));

					r.setAcpPremios(resultSet
							.getInt("% ACEPTACION PREMIOS UNITARIOS"));
					r.setAcpPrimeraOrden(resultSet
							.getInt("% ACEPTACION_1A_ORDEN"));
					r.setAcpTotal(resultSet.getInt("% TOTAL ACEPTACION"));

					r.setDevOrdenes(resultSet.getInt("ORDENES_DEVUELTAS"));
					r.setDevCajas(resultSet.getInt("CAJAS_DEVUELTAS"));
					r.setDevPremios(resultSet
							.getInt("PREMIOS_UNITARIOS_DEVUELTOS"));

					r.setDifOrdenes(resultSet.getInt("ORDENES_DIFERENCIAS"));
					r.setDifCajas(resultSet.getInt("CAJAS_DIFERENCIAS"));
					r.setDifPremios(resultSet.getInt("PREMIOS_DIFERENCIA"));

					r.setCauNoViveAhi(resultSet.getInt("CAUSA_NO_VIVE_AHI"));
					r.setCauNoPago(resultSet.getInt("CAUSA_NO_PAGO"));
					r.setCauNoDejoFicha(resultSet.getInt("CAUSA_NO_DEJO_FICHA"));
					r.setCauCambioDom(resultSet
							.getInt("CAUSA_CAMBIO_DOMICILIO"));
					r.setCauCerradoTotal(resultSet
							.getInt("CAUSA_CERRADO_TOTAL"));
					r.setCauDifEnCobro(resultSet
							.getInt("CAUSA_DIFERENCIA_COBRO"));
					r.setCauFueraZona(resultSet.getInt("CAUSA_FUERA_ZONA"));
					r.setCauNoMetioPedido(resultSet
							.getInt("CAUSA_NO_METIO_PEDIDO"));
					r.setCauDomIncompleto(resultSet
							.getInt("CAUSA_DOMICILIO_INCOMPLETO"));
					r.setCauNoEsperaReparto(resultSet
							.getInt("CAUSA_NO_ESPERA_REPARTO_HOY"));
					r.setCauExtravioFicha(resultSet
							.getInt("CAUSA_EXTRAVIO_FICHA"));
					// r.setCauOtros(resultSet.getInt("CAUSA_EXTRAVIO_FICHA"));

					res.add(r);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Reparto",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return res;
	}

	/**
	 * Obtiene los valores existentes de NAR de la BD
	 * 
	 * @author brenda.estrada
	 * @since 02/02/2012
	 * @return Object [NoAceptacionReparto]
	 */
	public NoAceptacionReparto[] getObtenerNoAceptacionRepartoExistentes(
			int idCampania, int idZona, int idUser) {
		// BLOQUE DE PRUEBA
		// List<NoAceptacionReparto> arrData = new
		// ArrayList<NoAceptacionReparto>();
		// NoAceptacionReparto narEnc = new NoAceptacionReparto();
		// narEnc.setMiVentaNAR("200000");
		// narEnc.setOrdenPendienteEntregar("200013");
		// narEnc.setPorcentaAceptacion("90%");
		// arrData.add(narEnc);

		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<NoAceptacionReparto> arrData = new ArrayList<NoAceptacionReparto>();
		if (con != null) {
			try {
				callableStatement = con
						.prepareCall("{call SP_PWA_REPORTE_LISTA_NAR_GLOBAL(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_TIPO", "ENCABEZADO",
						Types.VARCHAR);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					NoAceptacionReparto data = new NoAceptacionReparto();
					data.setOrdenPendienteEntregar((resultSet
							.getString("ORD_PEND_POR_ENTREGAR") == null) ? ""
							: resultSet.getString("ORD_PEND_POR_ENTREGAR"));
					data.setMiVentaNAR((resultSet
							.getString("MI_VENTA_NO_ACEPTADA_EN_REPARTO") == null) ? ""
							: resultSet
									.getString("MI_VENTA_NO_ACEPTADA_EN_REPARTO"));
					data.setPorcentaAceptacion((resultSet
							.getString("PORCENT_ACEPTACION") == null) ? ""
							: resultSet.getString("PORCENT_ACEPTACION"));
					data.setLstNoAceptacionReparto(null);
					arrData.add(data);
				}
				callableStatement.close();
			} catch (SQLException ex) {
				// Guarda en el Log
				Utils.GuardarLogMensajeBD(
						"Reportes_Admin",
						"M1",
						"Surgió un error al obtener los datos de No Aceptación de Reparto.",
						ex.getMessage(), idUser);
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (NoAceptacionReparto[]) arrData
				.toArray(new NoAceptacionReparto[arrData.size()]);
	}

	/**
	 * Obtiene los valores del Detalle de la lista NAR de la BD
	 * 
	 * @author brenda.estrada
	 * @since 02/02/2012
	 * @return Object [NoAceptacionRepartoDetalle]
	 */
	public List<NoAceptacionRepartoDetalle> getDetalleNoAceptacionReparto(
			int idCampania, int idZona, int idUser) {
		// BLOQUE DE PRUEBA
		// ArrayList<NoAceptacionRepartoDetalle> arrData = new
		// ArrayList<NoAceptacionRepartoDetalle>();
		// Datos del Detalle
		// for (int i = 0; i < 100; i++) {
		// NoAceptacionRepartoDetalle data = new NoAceptacionRepartoDetalle();
		// data.setNo("1" + i);
		// data.setRegistro("REGistro1101" + i);
		// data.setNombre("Maria Sanhjuana Hernandez Sanchez" + i);
		// data.setDireccion("Mi calle el no 4 Int 9, Colonia Lomas" + i);
		// data.setEntreCalles("Entre la calle dela escuela" + i);
		// data.setPoblacion("Queretaro");
		// data.setValorCOD("Valor COD" + 1);
		// data.setValorPedido("Valor Pedido" + i);
		// data.setRed("RED" + i);
		// data.setCajas("1" + i);
		// data.setUnitarios("3" + i);
		// data.setGeoReferencia("REferencia GEo" + i);
		// data.setHrVisita("10:0" + i);
		// data.setCausaDevolucion("Incendio" + i);
		// arrData.add(data);
		// }
		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<NoAceptacionRepartoDetalle> arrData = new ArrayList<NoAceptacionRepartoDetalle>();
		if (con != null) {
			try {
				callableStatement = con
						.prepareCall("{call  SP_PWA_REPORTE_LISTA_NAR_GLOBAL(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_TIPO", "DETALLE", Types.VARCHAR);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					NoAceptacionRepartoDetalle data = new NoAceptacionRepartoDetalle();
					data.setNo((resultSet.getString("ID_LISTA_NAR_GLOBAL") == null) ? ""
							: resultSet.getString("ID_LISTA_NAR_GLOBAL"));
					data.setRegistro((resultSet.getString("REGISTRO") == null) ? ""
							: resultSet.getString("REGISTRO"));
					data.setNombre((resultSet.getString("NOMBRE") == null) ? ""
							: resultSet.getString("NOMBRE"));
					data.setDireccion((resultSet
							.getString("CALLE_NUMERO_COLONIA") == null) ? ""
							: resultSet.getString("CALLE_NUMERO_COLONIA"));
					data.setEntreCalles((resultSet.getString("ENTRE_CALLES") == null) ? ""
							: resultSet.getString("ENTRE_CALLES"));
					data.setPoblacion((resultSet.getString("POBLACION") == null) ? ""
							: resultSet.getString("POBLACION"));
					data.setValorCOD((resultSet.getString("VALOR_COD") == null) ? ""
							: resultSet.getString("VALOR_COD"));
					data.setValorPedido((resultSet.getString("SLIP_VALUE") == null) ? ""
							: resultSet.getString("SLIP_VALUE"));
					data.setRed((resultSet.getString("RED") == null) ? ""
							: resultSet.getString("RED"));
					data.setCajas((resultSet.getString("CAJAS") == null) ? ""
							: resultSet.getString("CAJAS"));
					data.setUnitarios((resultSet.getString("PREMIOS_UNITARIOS") == null) ? ""
							: resultSet.getString("PREMIOS_UNITARIOS"));
					data.setGeoReferencia((resultSet.getString("GEOREFERENCIA") == null) ? ""
							: resultSet.getString("GEOREFERENCIA"));
					data.setHrVisita((resultSet.getString("FECHA_VISITA") == null) ? ""
							: resultSet.getString("FECHA_VISITA"));
					data.setCausaDevolucion((resultSet
							.getString("CAUSA_DEVOLUCION") == null) ? ""
							: resultSet.getString("CAUSA_DEVOLUCION"));
					arrData.add(data);
				}
				callableStatement.close();
			} catch (SQLException ex) {
				// Guarda en el Log
				Utils.GuardarLogMensajeBD(
						"Reportes_Admin",
						"M1",
						"Surgió un error al obtener los datos del Detalle de la Lista de No Aceptación de Reparto.",
						ex.getMessage(), idUser);
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return arrData;
	}

	/**
	 * Obtiene los valores existentes de NAR Total de la BD
	 * 
	 * @author brenda.estrada
	 * @since 21/02/2012
	 * @return Object [NoAceptacionRepartoTotal]
	 */
	public NoAceptacionRepartoTotal[] getObtenerNARTotalExistentes(
			int idCampania, int idZona, int idUser) {
		// BLOQUE DE PRUEBA
		// List<NoAceptacionRepartoTotal> arrData = new
		// ArrayList<NoAceptacionRepartoTotal>();
		// NoAceptacionRepartoTotal narEnc = new NoAceptacionRepartoTotal();
		// narEnc.setPorcentaAceptacion("20%");
		// narEnc.setCantidadOrdenes("213");
		// narEnc.setImporteTotalZona("$9000.23");
		// narEnc.setTotalOrdenes("5600");
		// narEnc.setCantidadLibresCobro("$28300.00");
		// narEnc.setPorcentaAceptacionLibreCobro("20%");
		// arrData.add(narEnc);
		// Termina BLOQUE de PRUEBA

		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<NoAceptacionRepartoTotal> arrData = new ArrayList<NoAceptacionRepartoTotal>();
		if (con != null) {
			try {
				callableStatement = con
						.prepareCall("{call SP_PWA_REPORTE_LISTA_NAR_TOTAL(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_TIPO", "ENCABEZADO",
						Types.VARCHAR);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				while (resultSet.next()) {
					NoAceptacionRepartoTotal narEnc = new NoAceptacionRepartoTotal();
					narEnc.setPorcentaAceptacion((resultSet
							.getString("PORCENT_ACEPTACION_ZONA") == null) ? ""
							: resultSet.getString("PORCENT_ACEPTACION_ZONA"));
					narEnc.setCantidadOrdenes((resultSet
							.getString("CANTIDAD_ORDENES_DEV_ZONA") == null) ? ""
							: resultSet.getString("CANTIDAD_ORDENES_DEV_ZONA"));
					narEnc.setImporteTotalZona((resultSet
							.getString("IMPORTE_TOTAL_ZONA") == null) ? ""
							: resultSet.getString("IMPORTE_TOTAL_ZONA"));
					narEnc.setTotalOrdenes((resultSet
							.getString("TOTAL_ORDENES_ZONA") == null) ? ""
							: resultSet.getString("TOTAL_ORDENES_ZONA"));
					narEnc.setCantidadLibresCobro((resultSet
							.getString("CANT_ORDENES_LIBRES_COBRO_DEV_ZONA") == null) ? ""
							: resultSet
									.getString("CANT_ORDENES_LIBRES_COBRO_DEV_ZONA"));
					narEnc.setPorcentaAceptacionLibreCobro((resultSet
							.getString("PORCENT_ACEPTACION_LIBRES_COBRO_ZONA") == null) ? ""
							: resultSet
									.getString("PORCENT_ACEPTACION_LIBRES_COBRO_ZONA"));
					narEnc.setSumatoriaTotalOrdenesLibreCobro(resultSet
							.getString("SUMATORIA_TOTAL_VALOR_PEDIDO_LIBRES_COBRO_DEVUELTAS"));
					narEnc.setOrdenesLibresCobroTotales(resultSet
							.getString("CANT_ORDENES_LIBRES_COBRO_TOTALES"));
					arrData.add(narEnc);
				}
				callableStatement.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"Reportes_Admin",
						"M1",
						"Surgió un error al obtener los datos de No Aceptación de Reparto.",
						ex.getMessage(), idUser);
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return (NoAceptacionRepartoTotal[]) arrData
				.toArray(new NoAceptacionRepartoTotal[arrData.size()]);
	}

	/**
	 * Obtiene los valores del Detalle de la lista NAR de la BD
	 * 
	 * @author brenda.estrada
	 * @since 21/02/2012
	 * @return Object [NoAceptacionRepartoDetalle]
	 */
	public List<NoAceptacionRepartoTotalDetRED> getDetalleNARTotalRED(
			int idCampania, int idZona, int idUser) {
		// BLOQUE DE PRUEBA
		// ArrayList<NoAceptacionRepartoTotalDetRED> arrData = new
		// ArrayList<NoAceptacionRepartoTotalDetRED>();
		// for (int i = 0; i < 100; i++) {
		// NoAceptacionRepartoTotalDetRED data = new
		// NoAceptacionRepartoTotalDetRED();
		// data.setNoRed("1" + i);
		// data.setPorcentajeAceptacionRed("10" + i);
		// data.setOrdenDevolucion("10" + i);
		// data.setSumValorPedidoRed("23" + i);
		// data.setTotalOrdenes("100" + i);
		// arrData.add(data);
		// }
		// FIN BLOQUE DE PRUEBA

		Connection con = AccesoBD.AbrirConexionOTS();
		ArrayList<NoAceptacionRepartoTotalDetRED> arrData = new ArrayList<NoAceptacionRepartoTotalDetRED>();
		if (con != null) {
			try {
				callableStatement = con
						.prepareCall("{call  SP_PWA_REPORTE_LISTA_NAR_TOTAL(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_TIPO", "DETALLE", Types.VARCHAR);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					NoAceptacionRepartoTotalDetRED data = new NoAceptacionRepartoTotalDetRED();
					data.setNoRed((resultSet.getString("RED") == null) ? ""
							: resultSet.getString("RED"));
					data.setPorcentajeAceptacionRed((resultSet
							.getString("PORCENT_ACEPTACION_RED") == null) ? ""
							: resultSet.getString("PORCENT_ACEPTACION_RED"));
					data.setOrdenDevolucion((resultSet
							.getString("ORDENES_DEVOLUCION") == null) ? ""
							: resultSet.getString("ORDENES_DEVOLUCION"));
					data.setSumValorPedidoRed((resultSet
							.getString("SUMATORIA_VALOR_PEDIDO_RED") == null) ? ""
							: resultSet.getString("SUMATORIA_VALOR_PEDIDO_RED"));
					data.setTotalOrdenes((resultSet.getString("TOTAL_ORDENES") == null) ? ""
							: resultSet.getString("TOTAL_ORDENES"));

					data.setOrdenLibreCobroNoEntregado((resultSet
							.getString("ORDENES_LIBRES_COBRO_DEVUELTAS") == null) ? ""
							: resultSet
									.getString("ORDENES_LIBRES_COBRO_DEVUELTAS"));
					data.setSumValorPedidoLibre((resultSet
							.getString("SUMATORIA_VALOR_PEDIDO_LIBRES_COBRO_DEVUELTAS") == null) ? ""
							: resultSet
									.getString("SUMATORIA_VALOR_PEDIDO_LIBRES_COBRO_DEVUELTAS"));
					data.setTotalLibreCobro((resultSet
							.getString("ORDENES_LIBRES_COBRO_TOTALES") == null) ? ""
							: resultSet
									.getString("ORDENES_LIBRES_COBRO_TOTALES"));
					data.setPorcentAceptacionLibresRed((resultSet
							.getString("PORCENT_ACEPTACION_LIBRES_COBRO") == null) ? ""
							: resultSet
									.getString("PORCENT_ACEPTACION_LIBRES_COBRO"));
					arrData.add(data);
				}
				callableStatement.close();
			} catch (SQLException ex) {
				// Guarda en el Log
				Utils.GuardarLogMensajeBD(
						"Reportes_Admin",
						"M1",
						"Surgió un error al obtener los datos del Detalle de la Lista de No Aceptación de Reparto Total.",
						ex.getMessage(), idUser);
			} finally {
				AccesoBD.CerrarConexion(con);
			}
		}
		return arrData;
	}

	/**
	 * Obtiene los valores del Detalle de la lista NAR Total de la BD
	 * 
	 * @author brenda.estrada
	 * @since 22/02/2012
	 * @return Object [NoAceptacionRepartoTotalDetLibres]
	 */
	public List<NoAceptacionRepartoTotalDetLibres> getDetalleNARTotalLibres(
			int idCampania, int idZona, int idUser) {
		// BLOQUE DE PRUEBAS
		ArrayList<NoAceptacionRepartoTotalDetLibres> arrData = new ArrayList<NoAceptacionRepartoTotalDetLibres>();
		for (int i = 0; i < 100; i++) {
			NoAceptacionRepartoTotalDetLibres data = new NoAceptacionRepartoTotalDetLibres();
			data.setOrdenLibreCobroNoEntregado("1" + i);
			data.setSumValorPedidoLibre("200" + i);
			data.setTotalLibreCobro("56000" + i);
			data.setPorcentAceptacionLibresRed("10" + i);
			arrData.add(data);
		}

		// Connection con = AccesoBD.AbrirConexionOTS();
		// ArrayList<NoAceptacionRepartoTotalDetLibres> arrData = new
		// ArrayList<NoAceptacionRepartoTotalDetLibres>();
		// if (con != null) {
		// try {
		// callableStatement =
		// con.prepareCall("{call  SP_PWR_ListaNAR_Global_Filas(?,?,?)}");
		// callableStatement.setObject("P_ID_CAMPANIA",
		// idCampania,Types.INTEGER);
		// callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
		// callableStatement.setObject("P_ID_USER", idUser, Types.INTEGER);
		// resultSet = AccesoBD.executeRetrieveResultSet(callableStatement);
		//
		// while (resultSet.next()) {
		// NoAceptacionRepartoTotalDetLibres data = new
		// NoAceptacionRepartoTotalDetLibres();
		// data.setOrdenLibreCobroNoEntregado((resultSet.getString(1) == null) ?
		// "": resultSet.getString(1));
		// data.setSumValorPedidoLibre((resultSet.getString(2) == null) ? "":
		// resultSet.getString(2));
		// data.setOrdenLibreCobroNoEntregado((resultSet.getString(3) == null) ?
		// "": resultSet.getString(3));
		// data.setPorcentAceptacionLibresRed((resultSet.getString(4) == null) ?
		// "": resultSet.getString(4));
		// arrData.add(data);
		// }
		// callableStatement.close();
		// } catch (SQLException ex) {
		// // Guarda en el Log
		// Utils.GuardarLogMensajeBD(
		// "Reportes_Admin",
		// "M1",
		// "Surgió un error al obtener los datos del Detalle de la Lista de No Aceptación de Reparto Total.",
		// ex.getMessage(), idUser);
		// } finally {
		// AccesoBD.CerrarConexion(con);
		// }
		// }
		return arrData;
	}

	public ModelAnalisisEfectivo obtenerEncabezadoAnalisisDeEfectivo(
			Integer idCampania, Integer idZona, int idRuta, int idUsuario,
			int idLdc) {

		ModelAnalisisEfectivo analisisEfectivo = null;
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_ANALISIS_EFECTIVO_ENCABEZADO(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLdc, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					analisisEfectivo = new ModelAnalisisEfectivo();
					analisisEfectivo.setPorteador(resultSet
							.getString("PORTEADOR"));
					analisisEfectivo.setPlaza(resultSet.getString("PLAZA"));
					analisisEfectivo.setZona(resultSet.getString("ZONA"));
					analisisEfectivo.setCampania(resultSet
							.getString("CAMPANIA"));
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Analisis de Efectivo",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return analisisEfectivo;
	}

	public List<ModelTablaAnalisisEfectivo> consultarPrimeraLiquidacionAnalisisEfectivo(
			Integer idCampania, Integer idZona, int idRuta, int idUsuario,
			int idLdc) {
		List<ModelTablaAnalisisEfectivo> listaAnalisisEfectivo = new ArrayList<ModelTablaAnalisisEfectivo>();
		ModelTablaAnalisisEfectivo primeraLiquidacion = null;

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_ANALISIS_EFECTIVO_1A_LIQUIDACION(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLdc, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					primeraLiquidacion = new ModelTablaAnalisisEfectivo();
					primeraLiquidacion.setRuta(resultSet.getString("RUTA"));
					primeraLiquidacion.setEfectivoRecolectado(resultSet
							.getDouble("EFECTIVO_RECOLECTADO"));
					primeraLiquidacion.setFechaRecepcionEfectivo(resultSet
							.getString("FECHA_RECEPCION_EFECTIVO"));
					primeraLiquidacion.setBancoDeposito(resultSet
							.getString("BANCO"));
					primeraLiquidacion.setDepositoGlobal(resultSet
							.getDouble("DEPOSITO_GLOBAL"));
					listaAnalisisEfectivo.add(primeraLiquidacion);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Analisis de Efectivo",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaAnalisisEfectivo;
	}

	public List<ModelTablaAnalisisEfectivoLiquidacion> consultarSegundaLiquidacionAnalisisEfectivo(
			Integer idCampania, Integer idZona, int idRuta, int idUsuario,
			int idLdc) {

		List<ModelTablaAnalisisEfectivoLiquidacion> listaAnalisisEfectivoLiquidacion = new ArrayList<ModelTablaAnalisisEfectivoLiquidacion>();
		ModelTablaAnalisisEfectivoLiquidacion segundaLiquidacion = null;
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_ANALISIS_EFECTIVO_2A_LIQUIDACION(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLdc, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					segundaLiquidacion = new ModelTablaAnalisisEfectivoLiquidacion();
					segundaLiquidacion.setBodegaSub(resultSet
							.getString("BODEGA_O_SUBBODEGA"));
					segundaLiquidacion.setImporte(resultSet
							.getDouble("IMPORTE"));
					segundaLiquidacion.setFecha(resultSet.getString("FECHA"));
					listaAnalisisEfectivoLiquidacion.add(segundaLiquidacion);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Analisis de Efectivo",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaAnalisisEfectivoLiquidacion;
	}

	public List<ModelTablaHistoricoDeRepresentantes> obtenerDatosReporteHistoricoRepresentantes(
			Integer zona, Integer campania, int idUsuario, int idLDC,
			Integer anioCampania) {

		List<ModelTablaHistoricoDeRepresentantes> tabla = new ArrayList<ModelTablaHistoricoDeRepresentantes>();
		ModelTablaHistoricoDeRepresentantes detalle = null;
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_HISTORIAL_REPRESENTANTE(?,?,?,?)}");
				callableStatement.setObject("P_ZONA", zona, Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				callableStatement.setObject("P_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_ANIO_CAMPANIA", anioCampania,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					detalle = new ModelTablaHistoricoDeRepresentantes();
					detalle.setCEDI(resultSet.getString("CEDI"));
					detalle.setRegistro(resultSet.getString("REGISTRO"));
					detalle.setClaveRuta(resultSet.getString("CLAVE_RUTA"));
					detalle.setNombre(resultSet.getString("NOMBRE"));
					detalle.setDireccion(resultSet.getString("DIRECCION"));
					detalle.setDireccion1(resultSet.getString("DIRECCION 1"));
					detalle.setDireccion2(resultSet.getString("DIRECCION 2"));
					detalle.setRed(resultSet.getString("RED"));
					detalle.setaPagar(resultSet.getString("A PAGAR"));
					detalle.setCajas(resultSet.getString("CAJAS"));
					detalle.setGeoreferenciaDomicilio(resultSet
							.getString("GEOREFERENCIA DOMICILIO"));
					detalle.setTipoDePago(resultSet.getString("TIPO_PAGO"));
					detalle.setFolios(resultSet.getString("FOLIOS"));
					detalle.setCantidadPago(resultSet.getString("PAGÓ"));
					detalle.setDiferencia(resultSet.getString("DIFERENCIA"));
					detalle.setFechaDeEntrega(resultSet
							.getString("FECHA DE ENTREGA"));
					detalle.setFechaDePago(resultSet.getString("FECHA DE PAGO"));
					detalle.setEntregado(resultSet.getString("ENTREGADO"));
					detalle.setSlipValue(resultSet.getString("SLIP VALUE"));
					detalle.setGeoreferenciaDeEntrega(resultSet
							.getString("GEOREFERENCIA DE ENTREGA"));
					detalle.setDevueltoA(resultSet.getString("DEVUELTO A"));
					detalle.setCausaDeDevolucion(resultSet
							.getString("CAUSA DE DEVOLUCION"));
					detalle.setFechaDeCierre(resultSet
							.getString("FECHA_CIERRE"));
					detalle.setFechaDevolucionFinal(resultSet
							.getString("FECHA DE DEVOLUCION FINAL"));
					detalle.setObservaciones(resultSet
							.getString("OBSERVACIONES"));

					// Modificacion, HIJACK, RETURN, CAUSA_DEVOLUCION_FINAL,
					// RETURN_VAL

					detalle.setModificacion(resultSet.getString("MODIFICACION"));
					detalle.setHijack(resultSet.getString("HIJACK"));
					detalle.setRepReturn(resultSet.getString("RETURN"));
					detalle.setReturnValue(resultSet.getString("RETURN_VAL"));
					detalle.setCausaDevolucionFinal(resultSet
							.getString("CAUSA_DEVOLUCION_FINAL"));

					tabla.add(detalle);
				}

				resultSet.close();
				// encabezado.setDetalleRepresentantes(tabla);
				// listaFinal.add(encabezado);

			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Historico de Representantes",
						ex.getMessage(), idUsuario);
				tabla = null;
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return tabla;
	}

	public ModelHistorialPorRepresentante obtenerEncabezadoHistorialPorRepresentante(
			int registro, int idUsuario) {
		ModelHistorialPorRepresentante encabezado = null;

		connection = AccesoBD.AbrirConexionOTS();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_HISTORIAL_REPRESENTANTE_ENCABEZADO(?)}");
				callableStatement.setObject("p_Registro", registro,
						Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					encabezado = new ModelHistorialPorRepresentante();
					encabezado.setIdRepresentante(resultSet
							.getString("ID_REPRESENTANTE"));
					encabezado.setRegistro(resultSet.getString("ACCOUNT"));
					encabezado.setNombre(resultSet.getString("NOMBRE"));
					encabezado.setDireccion(resultSet.getString("DIRECCION"));
					encabezado.setDireccion1(resultSet.getString("DIRECCION1"));
					encabezado.setDireccion2(resultSet.getString("DIRECCION2"));
					encabezado.setRed(resultSet.getString("RED"));
					encabezado.setZona(resultSet.getString("ZONA"));
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Historial de Representante",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return encabezado;
	}

	public List<ModelTablaHistorialPorRepresentante> obtenerDatosReporteHistorialPorRepresentante(
			String campania, int idUsuario, int idLDC, String registro,
			String anioCampania) {

		List<ModelTablaHistorialPorRepresentante> tabla = new ArrayList<ModelTablaHistorialPorRepresentante>();
		ModelTablaHistorialPorRepresentante detalleRepresentante = null;
		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_HISTORICO_REPRESENTANTE(?,?,?,?)}");
				// callableStatement.setObject("P_ID_ZONA", idZona,
				// Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				callableStatement.setObject("P_REGISTRO", registro,
						Types.VARCHAR);
				callableStatement.setObject("P_CAMPANIA", campania,
						Types.VARCHAR);
				callableStatement.setObject("P_ANIO_CAMPANIA", anioCampania,
						Types.VARCHAR);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					// encabezado.setCampania(resultSet.getString("CAMPAÑA"));
					detalleRepresentante = new ModelTablaHistorialPorRepresentante();
					detalleRepresentante.setCampania(resultSet
							.getString("CAMPAÑA"));
					detalleRepresentante.setaPagar(resultSet
							.getString("A PAGAR"));
					detalleRepresentante.setCajas(resultSet.getString("CAJAS"));
					detalleRepresentante.setGeoreferenciaDomicilio(resultSet
							.getString("GEOREFERENCIA DOMICILIO"));
					detalleRepresentante.setTipoDePago(resultSet
							.getString("TIPO_PAGO"));
					detalleRepresentante.setFolios(resultSet
							.getString("FOLIOS"));
					detalleRepresentante.setCantidadPago(resultSet
							.getString("PAGÓ"));
					detalleRepresentante.setDiferencia(resultSet
							.getString("DIFERENCIA"));
					detalleRepresentante.setFechaDeEntrega(resultSet
							.getString("FECHA DE ENTREGA"));
					detalleRepresentante.setFechaDePago(resultSet
							.getString("FECHA DE PAGO"));
					detalleRepresentante.setEntregado(resultSet
							.getString("ENTREGADO"));
					detalleRepresentante.setSlipValue(resultSet
							.getString("SLIP VALUE"));
					detalleRepresentante.setGeoreferenciaDeEntrega(resultSet
							.getString("GEOREFERENCIA DE ENTREGA"));
					detalleRepresentante.setDevueltoA(resultSet
							.getString("DEVUELTO A"));
					detalleRepresentante.setCausaDeDevolucion(resultSet
							.getString("CAUSA DE DEVOLUCION"));
					detalleRepresentante.setFechaDeCierre(resultSet
							.getString("FECHA_CIERRE"));
					detalleRepresentante.setFechaDevolucionFinal(resultSet
							.getString("FECHA DE DEVOLUCION FINAL"));
					detalleRepresentante.setObservaciones(resultSet
							.getString("OBSERVACIONES"));
					detalleRepresentante.setFotoDomicilio(resultSet
							.getBytes("FOTO DOMICILIO"));
					detalleRepresentante.setFotoIdentificacion(resultSet
							.getBytes("FOTO IDENTIFICACION"));

					detalleRepresentante.setModificacion(resultSet
							.getString("MODIFICACION"));
					detalleRepresentante.setHijack(resultSet
							.getString("HIJACK"));
					detalleRepresentante.setRepReturn(resultSet
							.getString("RETURN"));
					detalleRepresentante.setReturnValue(resultSet
							.getString("RETURN_VAL"));
					detalleRepresentante.setCausaDevolucionFinal(resultSet
							.getString("CAUSA_DEVOLUCION_FINAL"));

					tabla.add(detalleRepresentante);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Historial de Representante",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
				tabla = null;
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return tabla;
	}

	public List<ModelRepRecepcionCarga> consultarEncabezadoReporteControlRecepcionCarga(
			Integer idZona, Integer idCampania, int idLDC, int idUsuario) {

		ModelRepRecepcionCarga recepcionCarga = null;
		List<ModelRepRecepcionCarga> recepcionCargaList = new ArrayList<ModelRepRecepcionCarga>();

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_CONTROL_RECEPCION_CARGA_1(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					recepcionCarga = new ModelRepRecepcionCarga();
					recepcionCarga.setPorteador(resultSet
							.getString("PORTEADOR"));
					recepcionCarga.setPoblacion(resultSet
							.getString("POBLACION"));
					recepcionCarga.setcTransportista(resultSet
							.getString("CIA_TRANSPORTISTA"));
					recepcionCarga.setOperador(resultSet
							.getString("NOMBRE_OPERADOR"));
					recepcionCarga.setCampania(resultSet.getString("CAMPANIA"));
					recepcionCarga.setZonaH(resultSet.getString("ZONA"));
					recepcionCarga
							.setCampaniaH(resultSet.getString("CAMPANIA"));
					recepcionCarga.setFecha(resultSet.getString("FECHA"));
					recepcionCarga.setHoraLlegadaReal(resultSet
							.getString("HORA_LLEGADACARGA_REAL"));
					recepcionCarga.setHoraLlegadaProg(resultSet
							.getString("HORA_LLEGADACARGA_PROGRAMADA"));
					recepcionCarga.setFechaLlegadaReal(resultSet
							.getString("FECHA_LLEGADACARGA_REAL"));
					recepcionCarga.setFechaLlegadaProg(resultSet
							.getString("FECHA_LLEGADACARGA_PROGRAMADA"));

					// Valores para la tabla de OrdenesXZona
					recepcionCarga.setZona(resultSet.getString("ZONA"));
					recepcionCarga.setOrdEnvGerZon(resultSet
							.getInt("ORDENES_ORDENVGERENTEZONAL"));
					// recepcionCarga.setCancel(resultSet.getInt("")); NO ESTA
					// EN LA CONSULTA
					recepcionCarga.setRelacion(resultSet
							.getInt("ORDENES_RELAC"));
					recepcionCarga.setCods(resultSet.getInt("ORDENES_CODS"));
					recepcionCarga.setCajasOrd(resultSet
							.getInt("ORDENES_CAJAS"));
					recepcionCarga.setPremios(resultSet
							.getInt("PREMIOS_PREMIOS"));
					recepcionCarga.setCajasPre(resultSet
							.getInt("PREMIOS_CAJAS"));

					// COLUMNAS NO SE ENCUENTRAN EN LA CONSULTA, NECESARIO
					// MODIFICAR EL DISEÑO DEL REPORTE INICIO
					/*
					 * recepcionCarga.setTotOrdEnvGerZona(resultSet.getInt(""));
					 * recepcionCarga.setTotCancel(resultSet.getInt(""));
					 * recepcionCarga.setTotRelacion(resultSet.getInt(""));
					 * recepcionCarga.setTotCods(resultSet.getInt(""));
					 * recepcionCarga.setTotCajasOrd(resultSet.getInt(""));
					 * recepcionCarga.setTotPremios(resultSet.getInt(""));
					 * recepcionCarga.setCajasPre(resultSet.getInt(""));
					 */
					// COLUMNAS NO SE ENCUENTRAN EN LA CONSULTA, NECESARIO
					// MODIFICAR EL DISEÑO DEL REPORTE FIN

					// Valores para la tabla de Papeleo
					recepcionCarga.setPanoram(resultSet.getInt("PANORAMA"));
					// recepcionCarga.setPortaf(resultSet.getString("")); ESTA
					// COLUMNA NO SE ENCUENTRA EN LA CONSULTA
					recepcionCarga.setPapGerenZonal(resultSet
							.getInt("PAPELERIA_GTE_ZONAL"));
					recepcionCarga.setPapPortead(resultSet
							.getInt("PAPELERIA_PORTEADOR"));
					recepcionCarga.setOtros(resultSet.getInt("OTROS"));
					recepcionCarga.setTotCajas(resultSet.getInt("TOTAL_CAJAS"));
					recepcionCarga.setObservaciones(resultSet
							.getString("OBSERVACIONES"));

					recepcionCargaList.add(recepcionCarga);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Control de Recepción de Carga",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return recepcionCargaList;
	}

	public List<TablaInfoCodFaltantes> consultarInfoCodigosFaltantesReporteControlRecepcionCarga(
			Integer idZona, Integer idCampania, int idLDC, int idUsuario) {

		List<TablaInfoCodFaltantes> listaCodigosFaltantes = new ArrayList<TablaInfoCodFaltantes>();
		TablaInfoCodFaltantes codigoFaltante = null;

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_CONTROL_RECEPCION_CARGA_3(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					codigoFaltante = new TablaInfoCodFaltantes();
					codigoFaltante.setRegistro(resultSet.getString("REGISTRO"));
					codigoFaltante.setTotalCajas(resultSet
							.getInt("TOTAL_CAJAS"));
					codigoFaltante.setTotalRecibido(resultSet
							.getInt("TOTAL_RECIBIDO"));
					codigoFaltante.setCajasFaltantes(resultSet
							.getInt("CAJAS_FALTANTES"));
					codigoFaltante.setNumCajaFaltante(resultSet
							.getShort("NUM_CAJA_FALTANTE"));
					codigoFaltante.setObservaciones(resultSet
							.getString("OBSERVACIONES"));
					listaCodigosFaltantes.add(codigoFaltante);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Control de Recepción de Carga",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaCodigosFaltantes;
	}

	public List<TablaCajasMaltratadas> consultarCajasMaltratadasReporteControlRecepcionCarga(
			Integer idZona, Integer idCampania, int idLDC, int idUsuario) {

		List<TablaCajasMaltratadas> listaCajasMaltratadas = new ArrayList<TablaCajasMaltratadas>();
		TablaCajasMaltratadas cajaMaltratada = null;

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_CONTROL_RECEPCION_CARGA_4(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					cajaMaltratada = new TablaCajasMaltratadas();
					cajaMaltratada.setRegistro(resultSet.getString("REGISTRO"));
					cajaMaltratada.setNoCaja(resultSet.getInt("NUM_CAJA"));

					// NO SE ENCUENTRAN LAS COLUMNAS EN LA CONSULTA INICIO
					/*
					 * cajaMaltratada.setDespegada(resultSet.getString(""));
					 * cajaMaltratada.setAveriada(resultSet.getString(""));
					 * cajaMaltratada.setMojada(resultSet.getString(""));
					 */
					// NO SE ENCUENTRAN LAS COLUMNAS EN LA CONSULTA FIN

					listaCajasMaltratadas.add(cajaMaltratada);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Control de Recepción de Carga",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaCajasMaltratadas;
	}

	public List<TablaCargaRecibida> consultarCargaRecibidaReporteControlRecepcionCarga(
			Integer idZona, Integer idCampania, int idLDC, int idUsuario) {

		List<TablaCargaRecibida> listaCargasRecibidas = new ArrayList<TablaCargaRecibida>();
		TablaCargaRecibida cargaRecibida = null;

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_CONTROL_RECEPCION_CARGA_2(?,?,?)}");
				callableStatement.setObject("P_ID_ZONA", idZona, Types.INTEGER);
				callableStatement.setObject("P_ID_CAMPANIA", idCampania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLDC, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {

					cargaRecibida = new TablaCargaRecibida();
					cargaRecibida.setZona(resultSet.getString("ZONA"));
					cargaRecibida.setCods(resultSet.getInt("ORDENES_CODS"));
					cargaRecibida
							.setCajasOrd(resultSet.getInt("ORDENES_CAJAS"));
					cargaRecibida.setPremios(resultSet
							.getInt("PREMIOS_PREMIOS"));
					cargaRecibida
							.setCajasPre(resultSet.getInt("PREMIOS_CAJAS"));
					cargaRecibida.setPanoram(resultSet.getInt("PANORAMA"));
					cargaRecibida.setPortaf(resultSet.getInt("PORTAF"));
					cargaRecibida.setPapGerZonal(resultSet
							.getInt("PAP_GTE_ZONAL"));
					cargaRecibida.setPapPorteador(resultSet
							.getInt("PAP_PORTEADOR"));
					cargaRecibida.setOtros(resultSet.getInt("OTROS"));

					cargaRecibida.setTotCajas(resultSet.getInt("TOTAL_CAJAS"));

					// COLUMNAS NO SE ENCUENTRAN EN LA CONSULTA, NECESARIO
					// MODIFICAR EL DISEÑO DEL REPORTE INICIO
					/*
					 * cargaRecibida.setTotCods(resultSet.getInt(""));
					 * cargaRecibida.setTotCajasOrd(resultSet.getInt(""));
					 * cargaRecibida.setTotPremios(resultSet.getInt(""));
					 * cargaRecibida.setTotCajasPre(resultSet.getInt(""));
					 * cargaRecibida.setTotPanoram(resultSet.getInt(""));
					 * cargaRecibida.setTotPortaf(resultSet.getInt(""));
					 * cargaRecibida.setTotPapGerZonal(resultSet.getInt(""));
					 * cargaRecibida.setTotPapPorteador(resultSet.getInt(""));
					 * cargaRecibida.setTotOtros(resultSet.getInt(""));
					 * cargaRecibida.setgTotCajas(resultSet.getInt(""));
					 */
					// COLUMNAS NO SE ENCUENTRAN EN LA CONSULTA, NECESARIO
					// MODIFICAR EL DISEÑO DEL REPORTE INICIO FIN

					listaCargasRecibidas.add(cargaRecibida);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al obtener los datos del reporte Control de Recepción de Carga",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaCargasRecibidas;
	}

	public List<ModelRepTablaPremios> consultarDatosReportePremios(String zona,
			String campania, String numeroRegistro, String descripcionPremio,
			int idLdc, int idUsuario, String anioCampania) {

		List<ModelRepTablaPremios> listaPremios = new ArrayList<ModelRepTablaPremios>();
		ModelRepTablaPremios detallePremio = null;

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_REPORTE_PREMIOS(?,?,?,?,?,?)}");
				callableStatement.setObject("P_ID_LDC", idLdc, Types.INTEGER);
				callableStatement.setObject("P_ZONA", zona, Types.VARCHAR);
				callableStatement.setObject("P_CAMPANIA", campania,
						Types.VARCHAR);
				callableStatement.setObject("P_REGISTRO", numeroRegistro,
						Types.VARCHAR);
				callableStatement.setObject("P_DESC_PREMIO", descripcionPremio,
						Types.VARCHAR);
				callableStatement.setObject("P_ANIO", anioCampania,
						Types.VARCHAR);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					detallePremio = new ModelRepTablaPremios();
					detallePremio.setCedi(resultSet.getString("CEDI"));
					detallePremio.setZona(resultSet.getString("ZONA"));
					detallePremio
							.setPoblacion(resultSet.getString("POBLACION"));
					detallePremio.setCampania(resultSet.getString("CAMPAÑA"));
					detallePremio.setAccount(resultSet.getString("ACCOUNT"));
					detallePremio.setNombre(resultSet.getString("NOMBRE"));
					detallePremio.setRutaSecuenciaEntrega(resultSet
							.getString("RUTA Y SECUENCIA DE ENTREGA"));
					detallePremio.setLastUpdTs(resultSet
							.getString("LASTUPD_TS"));
					detallePremio.setCampaniaEnvio(resultSet
							.getString("CAMPAÑA ENVIO"));
					detallePremio.setCode(resultSet.getString("CODE"));
					detallePremio.setDescripcionPremio(resultSet
							.getString("DESCRIPCION DEL PREMIO"));
					detallePremio.setFechaEntregaDevolucion(resultSet
							.getString("FECHA DE ENTREGA O DEVOLUCION"));
					detallePremio
							.setEntregado(resultSet.getString("ENTREGADO"));
					detallePremio.setObservaciones(resultSet
							.getString("OBSERVACIONES"));
					detallePremio.setGeoreferenciaDomicilio(resultSet
							.getString("GEOREFERENCIA DOMICILIO"));
					detallePremio.setGeoreferenciaEntrega(resultSet
							.getString("GEOREFERENCIA DE ENTREGA"));
					detallePremio.setDevueltoA(resultSet
							.getString("DEVUELTO A"));
					detallePremio.setCausaDevolucion(resultSet
							.getString("CAUSA DE DEVOLUCION"));
					listaPremios.add(detallePremio);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M14",
						"Surgió un error al obtener los datos del reporte de premios",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
				listaPremios = null;
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaPremios;
	}

	public List<ModelReporteResumen> consultarDatosReporteResumen(int campania,
			int anioCampania, int idLdc, int idUsuario) {
		List<ModelReporteResumen> listaDetalles = new ArrayList<ModelReporteResumen>();
		ModelReporteResumen detalle = null;

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWA_Reporte_Resumen(?,?,?)}");
				callableStatement.setObject("P_CAMPANIA", campania,
						Types.INTEGER);
				callableStatement.setObject("P_ANIO", anioCampania,
						Types.INTEGER);
				callableStatement.setObject("P_ID_LDC", idLdc, Types.INTEGER);

				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					detalle = new ModelReporteResumen();
					detalle.setCampania(resultSet.getString("CAMPANIA"));
					detalle.setProductividadDescarga(resultSet
							.getDouble("PRODUCTIVIDAD_DESCARGA"));
					detalle.setProductividadEnrutado(resultSet
							.getDouble("PRODUCTIVIDAD_ENRUTADO"));
					detalle.setRutasNormales(resultSet.getInt("RUTAS_NORMALES"));
					detalle.setRutasEspeciales(resultSet
							.getInt("RUTAS_ESPECIALES"));
					detalle.setOrdenConRepartoDir(resultSet
							.getInt("ORDENES_REPARTO_DIRECTO"));
					detalle.setOrdenSinRepartoDir(resultSet
							.getInt("ORDENES_SIN_REPARTO_DIRECTO"));
					detalle.setPrimerasOrdenes(resultSet
							.getInt("PRIMERAS_ORDENES"));
					detalle.setCajas(resultSet.getInt("CAJAS"));
					detalle.setPremios(resultSet.getInt("PREMIOS"));
					detalle.setBultosMatPromocional(resultSet
							.getInt("BULTOS_MATERIAL_PROMOCIONAL"));

					detalle.setRepInicioRutaPromedio(resultSet
							.getString("REPARTO_INICIO_RUTA_PROMEDIO"));
					detalle.setRepPrimeraVisitaProm(resultSet
							.getString("REPARTO_PRIMER_VISITA_PROMEDIO"));
					detalle.setRepUltimaVisitaPromedio(resultSet
							.getString("REPARTO_ULTIMA_VISITA_PROMEDIO"));
					detalle.setRepFinRutaPromedio(resultSet
							.getString("REPARTO_FIN_RUTA_PROMEDIO"));
					detalle.setRepKmGlobal(resultSet
							.getDouble("REPARTO_KILOMETRAJE_GLOBAL"));
					detalle.setRepKmEfectivo(resultSet
							.getDouble("REPARTO_KILOMETRAJE_EFECTIVO"));
					detalle.setRepRendimientoProm(resultSet
							.getDouble("REPARTO_RENDIM_PROMEDIO"));

					detalle.setEntPrimeraOrden(resultSet
							.getInt("ENTREGADO_REPARTO_PRIMERAS_ORDENES"));
					detalle.setEntOrdenPtoEntrega(resultSet
							.getInt("ENTREGADO_REPARTO_ORDENES_PTO_ENTREGA"));
					detalle.setEntOrdenEstablecidas(resultSet
							.getInt("ENTREGADO_REPARTO_ORDENES_ESTABLECIDAS"));
					detalle.setEntCajasTotales(resultSet
							.getInt("ENTREGADO_REPARTO_CAJAS"));
					detalle.setEntPremios(resultSet
							.getInt("ENTREGADO_REPARTO_PREMIOS"));
					detalle.setEntMaterialPromocinal(0);

					detalle.setDevOrdenes(resultSet
							.getInt("DEVOLUCION_ORDENES"));
					detalle.setDevCajas(resultSet.getInt("DEVOLUCION_CAJA"));
					detalle.setDevPremios(resultSet
							.getInt("DEVOLUCION_PREMIOS"));
					detalle.setDevMatPromocional(0);

					detalle.setCauNoViveAhi(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_NO_VIVE_AHI"));
					detalle.setCauNoPago(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_NO_PAGO"));
					detalle.setCauNoDejoFicha(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_NO_DEJO_FICHA"));
					detalle.setCauCambioDom(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_CAMBIO_DOM"));
					detalle.setCauCerradoTotal(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_CERRADO_TOTAL"));
					detalle.setCauDifEnCobro(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_DIFERENCIA_COBRO"));
					detalle.setCauFueraZona(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_FUERA_ZONA"));
					detalle.setCauNoMetioPedido(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_NO_METIO_PEDIDO"));
					detalle.setCauDomIncompleto(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_DOM_INCOMPLETO"));
					detalle.setCauNoEsperaReparto(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_NO_ESPERA_REPARTO"));
					detalle.setCauExtravioFicha(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_EXTRAVIO_FICHA"));
					detalle.setCauOtro(resultSet
							.getDouble("PORCENT_NO_ACEPTACION_OTRO"));
					listaDetalles.add(detalle);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M15",
						"Surgió un error al obtener los datos del reporte de resumen",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaDetalles;
	}

	public List<Reporte> obtenerReportes(int idLdc, int idUsuario) {

		List<Reporte> listaReportes = new ArrayList<Reporte>();
		Reporte reporte = null;

		connection = AccesoBD.AbrirConexionOTS();

		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_PWR_ConsultarCatalogoReportes(?)}");
				callableStatement.setObject("P_ID_USUARIO", idUsuario,
						Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					reporte = new Reporte();
					reporte.setIdReporte(resultSet.getInt("ID_REPORTE"));
					reporte.setNombre(resultSet.getString("NOMBRE"));
					reporte.setDescripcion(resultSet.getString("DESCRIPCION"));
					listaReportes.add(reporte);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M3",
						"Surgió un error al obtener los datos del catálogo de reportes. Método: obtenerReportes",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaReportes;
	}
}
