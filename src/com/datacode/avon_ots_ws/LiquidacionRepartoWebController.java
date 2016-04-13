package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.datacode.avon_ots_ws.model.LiquidacionRepartoWeb;

public class LiquidacionRepartoWebController {

	private static final String LIQUIDACION_REPARTO = "LiquidacionReparto";

	public List<String> obtenerListaCampanias(int p_idUsuario) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		List<String> v_campanias = new ArrayList<String>();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_PW_CAMPANIA(?,?,?,?)}");
				v_cs.setObject("P_TIPO_CU", LIQUIDACION_REPARTO, Types.VARCHAR);
				v_cs.setObject("P_ID_ZONA", null, Types.INTEGER);
				v_cs.setObject("P_ID_LDC", null, Types.INTEGER);
				v_cs.setObject("P_ID_RUTA", null, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					v_campanias.add(v_rs.getString("CAMPANIA"));
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M1", "Error en obtenerListaCampanias", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M1", "Error en obtenerListaCampanias", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}			
		}
		return v_campanias;
	}

	public List<LiquidacionRepartoWeb> obtenerTiposLiquidacion(int p_idUsuario) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		List<LiquidacionRepartoWeb> v_tiposLiquidacion = new ArrayList<LiquidacionRepartoWeb>();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerTiposLiquidacion()}");
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					LiquidacionRepartoWeb v_tipoLiq = new LiquidacionRepartoWeb();
					v_tipoLiq.setValor(v_rs.getString("CLAVE"));
					v_tipoLiq.setEtiqueta(v_rs.getString("DESCRIPCION").trim());
					v_tiposLiquidacion.add(v_tipoLiq);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M2", "Error en obtenerTiposLiquidacion", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M2", "Error en obtenerTiposLiquidacion", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}			
		}
		return v_tiposLiquidacion;
	}

	public List<LiquidacionRepartoWeb> obtenerListaZonas(int p_idUsuario) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		List<LiquidacionRepartoWeb> v_zonas = new ArrayList<LiquidacionRepartoWeb>();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_PW_ZONA(?,?)}");
				v_cs.setObject("P_ID_LDC", null, Types.INTEGER);
				v_cs.setObject("P_TIPO_CU", LIQUIDACION_REPARTO, Types.VARCHAR);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					LiquidacionRepartoWeb v_zona = new LiquidacionRepartoWeb();
					v_zona.setIdZona(v_rs.getString("ID_ZONA"));
					v_zona.setDescripcionZona(v_rs.getString("ZONA"));
					v_zonas.add(v_zona);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M2", "Error en obtenerListaZonas", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M2", "Error en obtenerListaZonas", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}			
		}
		return v_zonas;
	}

	public List<LiquidacionRepartoWeb> obtenerListaRutas(LiquidacionRepartoWeb p_zona, int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_rutas = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_PW_RUTAS(?,?,?,?)}");
				v_cs.setObject("P_TIPO_CU", LIQUIDACION_REPARTO, Types.VARCHAR);
				v_cs.setObject("P_ID_ZONA", Integer.valueOf(p_zona.getIdZona()), Types.INTEGER);
				v_cs.setObject("P_ID_CAMPANIA", null, Types.INTEGER);
				v_cs.setObject("P_ID_LDC", null, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					LiquidacionRepartoWeb v_ruta = new LiquidacionRepartoWeb();
					v_ruta.setIdRuta(v_rs.getString("ID_RUTA"));
					v_ruta.setDescripcionRuta(v_rs.getString("RUTA"));
					v_rutas.add(v_ruta);
				}
				v_rs.close();
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M3", "Error en obtenerListaRutas", e.getMessage(), p_idUsuario);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M3", "Error en obtenerListaRutas", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M3", "Error en obtenerListaRutas", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}			
		}
		return v_rutas;
	}

	public List<LiquidacionRepartoWeb> obtenerRepresentantes(String p_registro, String p_nombre,
			String p_campania, String p_idZona, int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_representantes = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_BuscaRepresentantes(?,?,?,?)}");
				v_cs.setObject("P_NOMBRE", p_nombre, Types.VARCHAR);
				v_cs.setObject("P_REGISTRO", p_registro, Types.VARCHAR);
				v_cs.setObject("P_CAMPANIA", p_campania, Types.VARCHAR);
				v_cs.setObject("P_ID_ZONA", p_idZona, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					LiquidacionRepartoWeb v_representante = new LiquidacionRepartoWeb();
					v_representante.setRegistro(v_rs.getString("ACCOUNT"));
					v_representante.setNombre(v_rs.getString("NOMBRE"));
					v_representante.setDomicilio(v_rs.getString("DIRECCION"));
					v_representante.setIdZona(String.valueOf(v_rs.getInt("ID_ZONA")));
					v_representante.setDescripcionZona(v_rs.getString("ZONA"));
//					v_representante.setMontoPrevio(v_rs.getDouble("MONTO_A_COBRAR"));
					v_representante.setIdRepresentante(v_rs.getInt("ID_REPRESENTANTE"));
					v_representantes.add(v_representante);
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M4", "Error en obtenerRepresentantes", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M4", "Error en obtenerRepresentantes", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_representantes;
	}

	/**
	 * Metodo que obtiene la lista de ordenes para su liquidacion en WEB.
	 *
	 * @param p_campania the p_campania
	 * @param p_idZona the p_id zona
	 * @param p_idRuta the p_id ruta
	 * @param p_registro the p_registro
	 * @param p_idOrdenSegmentada the p_id orden segmentada
	 * @param p_nombre the p_nombre
	 * @return the list
	 */
	public List<LiquidacionRepartoWeb> obtenerListaOrdenes(String p_campania, Integer p_idZona, Integer p_idRuta,
			String p_registro, Integer p_idOrdenSegmentada, String p_nombre, int p_idUsuario, Integer p_idOrden) {
		List<LiquidacionRepartoWeb> v_ordenes = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		//llamar aqui metodo que trae nombre de bd
		String v_archivoConfig = "AvonWsApp.properties";
		String v_bdSOS = "";
		AccesoArchivos v_accesoArch = new AccesoArchivos();
		if(v_accesoArch.CargarArchivoPropiedades(v_archivoConfig)){
			v_bdSOS = v_accesoArch.ObtenerPropiedad("bd_SOS");
		}
		//Aqui termina
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				Integer v_registroParametro = null;
				if (p_registro != null && p_registro.length() > 0) {
					v_registroParametro = Integer.valueOf(p_registro);
				}
				v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerOrdenes(?,?,?,?,?,?,?,?)}");
				v_cs.setObject("p_Campania", p_campania, Types.VARCHAR);
				v_cs.setObject("p_idZona", p_idZona, Types.INTEGER);
				v_cs.setObject("p_idRuta", p_idRuta, Types.INTEGER);
				v_cs.setObject("p_registro", v_registroParametro, Types.NUMERIC);
				v_cs.setObject("p_bdSos", v_bdSOS, Types.VARCHAR);
				v_cs.setObject("p_idOrdenSegmentada", p_idOrdenSegmentada, Types.INTEGER);
				v_cs.setObject("p_nombre", p_nombre, Types.VARCHAR);
				v_cs.setObject("p_idOrden", p_idOrden, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				while (v_rs.next()) {
					LiquidacionRepartoWeb v_orden = new LiquidacionRepartoWeb();
					v_orden.setRoboCajas((v_rs.getInt("ROBO_CAJAS") == 1) ? true : false);
					v_orden.setDevolucionMostrar(v_rs.getString("DEVOLUCION_MOSTRAR"));
					v_orden.setIdRepresentante(v_rs.getInt("ID_REPRESENTANTE"));
					v_orden.setRegistro(v_rs.getString("REGISTRO"));
					v_orden.setNombre(v_rs.getString("NOMBRE"));
					v_orden.setIdOrden(v_rs.getInt("ID_ORDEN"));
					v_orden.setRutaSecuencia(v_rs.getString("RUTA_SECUENCIA"));
					v_orden.setMontoOrden(v_rs.getDouble("MONTO_ORDEN"));
					v_orden.setTipoDePago(v_rs.getString("TIPO_PAGO"));
					v_orden.setLiquidacion(v_rs.getString("LIQUIDACION"));
					v_orden.setFechaPago(v_rs.getString("FECHA_PAGO"));
					try {
						v_orden.setMontoPagado(v_rs.getString("MONTO_PAGADO") == null ? null : Double.valueOf(v_rs.getString("MONTO_PAGADO")));
					} catch (NumberFormatException e) {
						v_orden.setMontoPagado(null);
					}
					v_orden.setCantidadItems(v_rs.getInt("ITEMS"));
					v_orden.setCantidadCajas(v_rs.getInt("CAJAS"));
					v_orden.setCantidadPremiosUnitarios(v_rs.getInt("PREMIOS_UNITARIOS"));
					v_orden.setPrimeraDevolucion(v_rs.getString("PRIMERA_DEVOLUCION"));
					v_orden.setComentario(v_rs.getString("COMENTARIO"));
					v_orden.setDescripcionZona(v_rs.getString("ZONA"));
					v_orden.setIdZona(v_rs.getString("ID_ZONA"));
					v_orden.setIdRuta(v_rs.getString("ID_RUTA"));
					v_orden.setOrdenSegmentada(v_rs.getString("ORDEN_SEGMENTADA"));
					v_orden.setCampania(v_rs.getString("CAMPANIA"));
					v_orden.setIdCampania(v_rs.getInt("ID_CAMPANIA"));
					v_orden.setBlokFlag(v_rs.getInt("BLOCK_FLAG"));
					v_orden.setBloq(v_rs.getString("BLOQ"));
					v_orden.setDigitosModif(v_rs.getString("DIGITOS_MODIF"));
					v_orden.setLiqbanco(v_rs.getString("LIQBANCO"));
					v_orden.setComentariosEntregaMostrar(v_rs.getString("COMENTARIOS_ENTREGA_MOSTRAR"));
					v_orden.setIdPersonaRecibeMostrar(v_rs.getInt("ID_PERSONA_RECIBE_MOSTRAR"));
					v_orden.setComentariosNoEntregaMostrar(v_rs.getString("COMENTARIOS_NO_ENTREGA_MOSTRAR"));
					v_orden.setIdRazonDevolucionMostrar(v_rs.getInt("ID_RAZON_DEVOLUCION_MOSTRAR"));
					v_orden.setIdInformanteMostrar(v_rs.getInt("ID_INFORMANTE_MOSTRAR"));
					v_orden.setHijackedCash((v_rs.getInt("HIJACKED_CASH") == 1 ? true : false));
					v_orden.setCashSequence(v_rs.getString("CASH_SEQUENCE"));
					try {
						v_orden.setValorAdeudaOriginal(v_rs.getString("VALOR_ADEUDA_ORIGINAL_MOSTRAR") == null ? null : Double.valueOf(v_rs.getString("VALOR_ADEUDA_ORIGINAL_MOSTRAR")));
					} catch (NumberFormatException e) {
						v_orden.setValorAdeudaOriginal(null);
					}
					v_orden.setValorAdeudaRepresentante(v_rs.getDouble("VALOR_ADEUDA_REPRESENTANTE_MOSTRAR"));
					v_orden.setQuienRecibe(v_rs.getString("QUIEN_RECIBE"));
					v_orden.setIdEstatus(v_rs.getString("ID_ESTATUS_ORDEN") == null ? "" : v_rs.getString("ID_ESTATUS_ORDEN").trim());
					v_orden.setCantidadPagosEntrega(v_rs.getInt("CANTIDAD_PAGOS_ENTREGA"));
					v_orden.setMontoPagadoTotalEntrega(v_rs.getDouble("MONTO_PAGADO_TOTAL_ENTREGA"));
					v_orden.setCantidadPagosVentanilla(v_rs.getInt("CANTIDAD_PAGOS_VENTANILLA"));
					v_orden.setMontoPagadoTotalVentanilla(v_rs.getDouble("MONTO_PAGADO_TOTAL_VENTANILLA"));
					v_orden.setLibreCobro(v_rs.getString("LIBRE_COBRO"));
					v_orden.setRemitos(v_rs.getString("REMITOS_COLUMN"));
					v_orden.validarMostrarValores();
					v_ordenes.add(v_orden);
				}
				v_rs.close();
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M5", "Error en obtenerListaOrdenes", e.getMessage(), p_idUsuario);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M5", "Error en obtenerListaOrdenes", e.getMessage(), p_idUsuario);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M5", "Error en obtenerListaOrdenes", e.getMessage(), p_idUsuario);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}
		}
		return v_ordenes;
	}

	public List<LiquidacionRepartoWeb> obtenerListaRazonesDevolucion(int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_razonesDevolucion = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_PWA_Consultar_Catalogos_Avon(?)}");
			v_cs.setObject("P_CATALOGO", "16", Types.VARCHAR);
            ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
            while (v_rs.next()) {
            	LiquidacionRepartoWeb v_razonDevolucion = new LiquidacionRepartoWeb();
            	v_razonDevolucion.setIdRazonDevolucion(v_rs.getInt("ID_RAZON_DEVOLUCION"));
            	v_razonDevolucion.setDescripcionRazonDevolucion((v_rs.getString("DESCRIPCION") == null) ? "" : v_rs.getString("DESCRIPCION"));
            	v_razonesDevolucion.add(v_razonDevolucion);
            }
            v_rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M6", "Error en obtenerListaRazonesDevolucion", e.getMessage(), p_idUsuario);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M6", "Error en obtenerListaRazonesDevolucion", e.getMessage(), p_idUsuario);
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
		return v_razonesDevolucion;
	}

	public List<LiquidacionRepartoWeb> obtenerListaTipoInformantes(int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_informantes = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_PWA_ObtenerDatosCatalogo(?)}");
			v_cs.setObject("P_CATALOGO", "31", Types.VARCHAR);
        	ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
        	while (v_rs.next()) {
        		LiquidacionRepartoWeb v_informante = new LiquidacionRepartoWeb();
        		v_informante.setIdTipoInformante(v_rs.getInt("ID_TIPO_INFORMANTE"));
        		v_informante.setDescripcionTipoInformante((v_rs.getString("DESCRIPCION") == null) ? "" : v_rs.getString("DESCRIPCION"));
        		v_informantes.add(v_informante);
        	}
        	v_rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M7", "Error en obtenerListaTipoInformantes", e.getMessage(), p_idUsuario);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M7", "Error en obtenerListaTipoInformantes", e.getMessage(), p_idUsuario);
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
		return v_informantes;
	}

	public List<LiquidacionRepartoWeb> obtenerItemsPorOrden(LiquidacionRepartoWeb p_orden, int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_items = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerItemsOrden(?)}");
			v_cs.setObject("p_idOrden", p_orden.getIdOrden(), Types.VARCHAR);
        	ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
        	 while (v_rs.next()) {
        		 LiquidacionRepartoWeb v_item = new LiquidacionRepartoWeb();
        		 v_item.setIdItem(v_rs.getInt("ID_ITEM"));
        		 v_item.setCodigoBarras(v_rs.getString("CODIGO_BARRAS"));
        		 v_item.setDescripcionItem(v_rs.getString("DESCRIPCION"));
        		 v_item.setTipoItem(v_rs.getString("TIPO"));
        		 v_item.setIdEstatus(v_rs.getString("ID_ESTATUS"));
        		 v_item.setFsc(v_rs.getString("FSC").trim());
        		 v_item.setEan13(v_rs.getString("EAN13").trim());
        		 v_item.setEstatusItem(v_rs.getString("ESTATUS").trim());
        		 v_items.add(v_item);
        	 }
        	 v_rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M8", "Error en obtenerItemsPorOrden", e.getMessage(), p_idUsuario);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M8", "Error en obtenerItemsPorOrden", e.getMessage(), p_idUsuario);
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
		return v_items;
	}

	public List<LiquidacionRepartoWeb> obtenerListaQuienRecibe(int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_listaQuienRecibe = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerQuienRecibe}");
        	ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
        	 while (v_rs.next()) {
        		 LiquidacionRepartoWeb v_quienRecibe = new LiquidacionRepartoWeb();
        		 v_quienRecibe.setIdQuienRecibe(v_rs.getInt("ID_PERSONA_RECIBE"));
        		 v_quienRecibe.setDescripcionQuienRecibe(v_rs.getString("DESCRIPCION"));
        		 v_listaQuienRecibe.add(v_quienRecibe);
        	 }
        	 v_rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M9", "Error en obtenerListaQuienRecibe", e.getMessage(), p_idUsuario);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M9", "Error en obtenerListaQuienRecibe", e.getMessage(), p_idUsuario);
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
		return v_listaQuienRecibe;
	}

	public List<LiquidacionRepartoWeb> obtenerListaTiposDePago(int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_tiposDePago = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerTiposPago}");
        	ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
        	 while (v_rs.next()) {
        		 LiquidacionRepartoWeb v_tipoDePago = new LiquidacionRepartoWeb();
        		 v_tipoDePago.setIdTipoDePago(v_rs.getInt("ID_TIPO_PAGO"));
        		 v_tipoDePago.setDescripcionTipoDePagoCorta(v_rs.getString("DESCRIPCION_CORTA"));
        		 v_tipoDePago.setDescripcionTipoDePago(v_rs.getString("DESCRIPCION"));
        		 v_tiposDePago.add(v_tipoDePago);
        	 }
        	 v_rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M10", "Error en obtenerListaTiposDePago", e.getMessage(), p_idUsuario);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M10", "Error en obtenerListaTiposDePago", e.getMessage(), p_idUsuario);
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
		return v_tiposDePago;
	}

	public List<LiquidacionRepartoWeb> obtenerListaBancos(int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_listaBancos = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerBancos}");
        	ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
        	 while (v_rs.next()) {
        		 LiquidacionRepartoWeb v_banco = new LiquidacionRepartoWeb();
        		 v_banco.setIdBanco(v_rs.getInt("ID_BANCO"));
        		 v_banco.setDescripcionBanco(v_rs.getString("NOMBRE"));
        		 v_banco.setDescripcionBancoCorta(v_rs.getString("NOMBRE_CORTO"));
        		 v_banco.setClaveBanco(v_rs.getString("CLAVE"));
        		 v_listaBancos.add(v_banco);
        	 }
        	 v_rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M11", "Error en obtenerListaBancos", e.getMessage(), p_idUsuario);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M11", "Error en obtenerListaBancos", e.getMessage(), p_idUsuario);
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
		return v_listaBancos;
	}

	public List<LiquidacionRepartoWeb> obtenerListaPagosOrden(LiquidacionRepartoWeb p_orden, int p_idUsuario) {
		List<LiquidacionRepartoWeb> v_pagos = new ArrayList<LiquidacionRepartoWeb>();
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerPagosOrden(?)}");
			v_cs.setObject("P_ID_ORDEN", p_orden.getIdOrden(), Types.INTEGER);
        	ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
        	 while (v_rs.next()) {
        		 LiquidacionRepartoWeb v_pago = new LiquidacionRepartoWeb();
        		 v_pago.setIdPagoEntrega(v_rs.getInt("ID_PAGO"));
        		 v_pago.setIdBanco(v_rs.getInt("ID_BANCO"));
        		 v_pago.setDescripcionBanco(v_rs.getString("DESCRIPCION_BANCO"));
        		 v_pago.setDescripcionBancoCorta(v_rs.getString("DESCRIPCION_BANCO_CORTA"));
        		 v_pago.setDescripcionItem(v_rs.getString("DESCRIPCION_BANCO"));
        		 v_pago.setIdTipoDePago(v_rs.getInt("ID_TIPO_PAGO"));
        		 v_pago.setDescripcionTipoDePago(v_rs.getString("DESCRIPCION_TIPO_PAGO"));
        		 v_pago.setDescripcionTipoDePagoCorta(v_rs.getString("DESCRIPCION_TIPO_PAGO_CORTA"));
        		 v_pago.setMontoPagado(v_rs.getDouble("MONTO_PAGADO"));
        		 v_pago.setFolioPago(v_rs.getString("FOLIO"));
        		 v_pago.setFechaPago(v_rs.getString("FECHA_PAGO"));
        		 v_pago.setPagoActivo(true);
        		 v_pagos.add(v_pago);
        	 }
        	 v_rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M12", "Error en obtenerListaPagosOrden", e.getMessage(), p_idUsuario);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M12", "Error en obtenerListaPagosOrden", e.getMessage(), p_idUsuario);
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
		return v_pagos;
	}

	public LiquidacionRepartoWeb actualizarInformacionOrden(LiquidacionRepartoWeb p_orden, int p_idUsuario) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_ActualizarInformacionOrden(?,?,?,?)}");
			v_cs.setObject("P_ID_ORDEN", p_orden.getIdOrden(), Types.INTEGER);
			v_cs.setObject("P_COMENTARIOS", p_orden.getComentario(), Types.VARCHAR);
			v_cs.setObject("P_ROBO_CAJAS", (p_orden.getRoboCajas() != null && p_orden.getRoboCajas() ? 1 : 0), Types.INTEGER);
			v_cs.setObject("P_ID_USUARIO", p_idUsuario, Types.INTEGER);
			ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
			if (v_rs.next()) {
        		return consultarOrdenEspecifica(p_orden, p_idUsuario);
        	} else {
        		return null;
        	}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M13", "Error en actualizarInformacionOrden", e.getMessage(), p_idUsuario);
			return null;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M13", "Error en actualizarInformacionOrden", e.getMessage(), p_idUsuario);
			return null;
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
	}

	public LiquidacionRepartoWeb devolverOrden(LiquidacionRepartoWeb p_orden, LiquidacionRepartoWeb[] p_itemsOrden, int p_idUsuario) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			String v_idItems = "";
			for (LiquidacionRepartoWeb v_item : p_itemsOrden) {
				v_idItems += (v_item.getIdItem() + ",");
    		}
			if (v_idItems.length() > 0) {
				v_idItems = v_idItems.substring(0, (v_idItems.length() - 1));
			}
			v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_DevolverOrden(?,?,?,?,?,?,?,?,?,?,?,?)}");
			v_cs.setObject("P_ID_ORDEN", p_orden.getIdOrden(), Types.INTEGER);
			v_cs.setObject("P_ID_RAZON_DEVOLUCION", p_orden.getIdRazonDevolucion(), Types.INTEGER);
			v_cs.setObject("P_ID_INFORMANTE", p_orden.getIdTipoInformante() == null || p_orden.getIdTipoInformante() < 1 ? null : p_orden.getIdTipoInformante(), Types.INTEGER);
			v_cs.setObject("P_COMENTARIOS", p_orden.getComentariosDevolucion(), Types.VARCHAR);
			v_cs.setObject("P_ID_USUARIO", p_idUsuario, Types.INTEGER);
			v_cs.setObject("P_ID_REPRESENTANTE", p_orden.getIdRepresentante(), Types.INTEGER);
			v_cs.setObject("P_ID_RUTA", p_orden.getIdRuta(), Types.INTEGER);
			v_cs.setObject("P_ID_ZONA", p_orden.getIdZona(), Types.INTEGER);
			v_cs.setObject("P_ID_ITEMS", v_idItems, Types.VARCHAR);
			v_cs.setObject("P_ID_CAMPANIA", p_orden.getIdCampania(), Types.INTEGER);
			v_cs.setObject("P_ROBO_CAJAS", (p_orden.getRoboCajas() ? 1 : 0), Types.INTEGER);
			v_cs.setObject("P_CLAVE_TIPO_LIQUIDACION", p_orden.getClaveTipoLiquidacion(), Types.INTEGER);
        	ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
        	if (v_rs.next()) {
        		return consultarOrdenEspecifica(p_orden, p_idUsuario);
        	} else {
        		return null;
        	}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M13", "Error en devolverOrden", e.getMessage(), p_idUsuario);
			return null;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M13", "Error en devolverOrden", e.getMessage(), p_idUsuario);
			return null;
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
	}

	public LiquidacionRepartoWeb liquidarOrden(LiquidacionRepartoWeb p_orden, LiquidacionRepartoWeb[] p_itemsOrden, LiquidacionRepartoWeb[] p_pagos, Integer p_idUsuario) {
		String v_idItems = "";
		String v_eliminarPagos = "";
		String v_agregarPagos1 = "";
		String v_agregarPagos2 = "";
		String v_agregarPagos3 = "";
		String v_separador = "¬";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		int v_contadorAgregar = 1;
		for (LiquidacionRepartoWeb v_item : p_itemsOrden) {
			v_idItems += (v_item.getIdItem() + ",");
		}
		for (LiquidacionRepartoWeb v_iter : p_pagos) {
			if (v_iter != null && v_iter.getIdPagoEntrega() != null) {
    			Date fechaPagoItem = new Date();
				try {
					fechaPagoItem = df.parse(v_iter.getFechaPago());
				} catch (ParseException e1) {
				}
				v_agregarPagos1 += v_contadorAgregar + v_separador + (v_iter.getIdBanco() == null || v_iter.getIdBanco() == 0 ? "" : v_iter.getIdBanco()) + v_separador + v_iter.getIdTipoDePago() + "|";
				v_agregarPagos2 += v_contadorAgregar + v_separador + v_iter.getMontoPagado() + v_separador + v_iter.getFolioPago() + "|";
				if (p_orden.getClaveTipoLiquidacion() == 2) {
					v_agregarPagos3 += v_contadorAgregar + v_separador + (new SimpleDateFormat("dd/MM/yyyy", Locale.US)).format(fechaPagoItem) + "|";
				} else {
					v_agregarPagos3 += v_contadorAgregar + v_separador + (new SimpleDateFormat("yyyy.MM.dd", Locale.US)).format(fechaPagoItem) + "|";
				}
				v_contadorAgregar++;
    		}
		}
		if (v_eliminarPagos.length() > 0) {
			v_eliminarPagos = v_eliminarPagos.substring(0, (v_eliminarPagos.length() - 1));
		}
		if (v_agregarPagos1.length() > 0) {
			v_agregarPagos1 = v_agregarPagos1.substring(0, (v_agregarPagos1.length() - 1));
		}
		if (v_agregarPagos2.length() > 0) {
			v_agregarPagos2 = v_agregarPagos2.substring(0, (v_agregarPagos2.length() - 1));
		}
		if (v_agregarPagos3.length() > 0) {
			v_agregarPagos3 = v_agregarPagos3.substring(0, (v_agregarPagos3.length() - 1));
		}
		if (v_idItems.length() > 0) {
			v_idItems = v_idItems.substring(0, (v_idItems.length() - 1));
		}
		Date fechaPago = new Date();
		try {
			fechaPago = df.parse(p_orden.getFechaPago());
		} catch (ParseException e1) {
		}
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		CallableStatement v_cs = null;
		try {
			v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_LiquidarOrden(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			v_cs.setObject("P_ID_ORDEN", p_orden.getIdOrden(), Types.INTEGER);
			v_cs.setObject("P_ID_USUARIO", p_idUsuario, Types.INTEGER);
			v_cs.setObject("P_ID_PERSONA_RECIBE", p_orden.getIdQuienRecibe(), Types.INTEGER);
			v_cs.setObject("P_ID_REPRESENTANTE", p_orden.getIdRepresentante(), Types.INTEGER);
			v_cs.setObject("P_FECHA_LIQUIDACION", (new SimpleDateFormat("yyyy.MM.dd", Locale.US)).format(fechaPago), Types.VARCHAR);
			v_cs.setObject("P_COMENTARIOS_SI_ENTREGADA", p_orden.getComentariosLiquidacion(), Types.VARCHAR);
			v_cs.setObject("P_ID_ZONA", Integer.valueOf(p_orden.getIdZona()), Types.INTEGER);
			v_cs.setObject("P_ID_RUTA", Integer.valueOf(p_orden.getIdRuta()), Types.INTEGER);
			v_cs.setObject("P_ID_CAMPANIA", p_orden.getIdCampania(), Types.INTEGER);
			v_cs.setObject("P_ID_ITEMS", v_idItems, Types.VARCHAR);
			v_cs.setObject("P_AGREGAR_PAGOS_1", v_agregarPagos1, Types.VARCHAR);
			v_cs.setObject("P_AGREGAR_PAGOS_2", v_agregarPagos2, Types.VARCHAR);
			v_cs.setObject("P_AGREGAR_PAGOS_3", v_agregarPagos3, Types.VARCHAR);
			v_cs.setObject("P_ELIMINAR_PAGOS", v_eliminarPagos, Types.VARCHAR);
			v_cs.setObject("P_BLOQUEAR", 0, Types.INTEGER);
			v_cs.setObject("P_CASH_SEQUENCE", p_orden.getCashSequence(), Types.VARCHAR);
			v_cs.setObject("P_HIJACKED_CASH", (p_orden.getHijackedCash() ? 1 : 0), Types.INTEGER);
			v_cs.setObject("P_CLAVE_TIPO_LIQUIDACION", p_orden.getClaveTipoLiquidacion(), Types.INTEGER);
        	ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
        	if (v_rs.next()) {
        		return consultarOrdenEspecifica(p_orden, p_idUsuario);
        	} else {
        		return null;
        	}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M14", "Error en liquidarOrden", e.getMessage(), p_idUsuario);
			return null;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
					"M14", "Error en liquidarOrden", e.getMessage(), p_idUsuario);
			return null;
		} finally {
			AccesoBD.CerrarConexion(v_conn);
		}
	}

	public LiquidacionRepartoWeb enviarLiquidacionesPendientes(int p_idUsuario, String p_usuario) {
		Connection v_conn = AccesoBD.AbrirConexionOTS();
		LiquidacionRepartoWeb respuesta = new LiquidacionRepartoWeb();
		CallableStatement v_cs = null;
		if (v_conn != null) {
			try {
				v_cs = v_conn.prepareCall("{call SP_LiquidacionRepartoWeb_EnviarLiquidacionesPendientes(?,?)}");
				v_cs.setObject("P_USUARIO", p_usuario, Types.VARCHAR);
				v_cs.setObject("P_ID_USUARIO", p_idUsuario, Types.INTEGER);
				ResultSet v_rs = AccesoBD.executeRetrieveResultSet(v_cs);
				if (v_rs.next()) {
					respuesta.setMensajeLiqPend(v_rs.getString("MENSAJE_EJECUCION"));
					respuesta.setResultadoLiqPend(v_rs.getInt("RESULTADO_EJECUCION"));
				}
				v_rs.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M15", "Error en enviarLiquidacionesPendientes", e.getMessage(), p_idUsuario);
				respuesta.setMensajeLiqPend("Ocurrió un error al enviar las liquidaciones pendientes");
				respuesta.setResultadoLiqPend(0);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				Utils.GuardarLogMensajeBD("UCW010_1:Corregir pagos en entregas hechas en reparto y liquidar",
						"M15", "Error en enviarLiquidacionesPendientes", e.getMessage(), p_idUsuario);
				respuesta.setMensajeLiqPend("Ocurrió un error al enviar las liquidaciones pendientes");
				respuesta.setResultadoLiqPend(0);
			} finally {
				AccesoBD.CerrarConexion(v_conn);
			}			
		}
		return respuesta;
	}

	private LiquidacionRepartoWeb consultarOrdenEspecifica(LiquidacionRepartoWeb p_orden, int p_idUsuario) {
		List<LiquidacionRepartoWeb> ordenes = new ArrayList<LiquidacionRepartoWeb>();
		ordenes = this.obtenerListaOrdenes(null, null, null, null, null, null, p_idUsuario, p_orden.getIdOrden());
		if (ordenes != null && ordenes.size() > 0) {
			return ordenes.get(0);
		} else {
			return null;
		}
	}

}
