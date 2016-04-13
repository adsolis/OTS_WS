package com.datacode.avon_ots_ws.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class LiquidacionRepartoWeb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String valor = "";

	private String etiqueta = "";

	private String libreCobro = "";

	private Integer cantidadPagosEntrega = null;

	private Double montoPagadoTotalEntrega = null;

	private Integer cantidadPagosVentanilla = null;

	private Double montoPagadoTotalVentanilla = null;

	private String idEstatus = "";

	private Boolean roboCajas = null;

	private String robo = "";

	private String secuenciaEfectivo = "";

	private String idZona = "";

	private String idRuta = "";

	private String idOrdenSegmentada = "";

	private String descripcionZona = "";

	private String descripcionRuta = "";

	private String registro = "";

	private Integer registroNumerico = null;

	private String nombre = "";

	private String orden = "";

	private String liquidado = "";

	private String domicilio = "";

	private Double montoPrevio = null;

	private Integer idOrden = null;

	private String quienRecibe = "";

	private String claveOrden = "";

	private String campania = "";

	private Integer idRazonDevolucion = null;

	private String descripcionRazonDevolucion = "";

	private Integer idTipoInformante = null;

	private String descripcionTipoInformante = "";

	private Boolean checked = null;

	private String codigoBarras = "";

	private String descripcionItem = "";

	private String tipoItem = "";

	private String tipoDePago = "";

	private String descripcionPago = "";

	private String folioPago = "";

	private String fechaPago = "";

	private String montoPago = "";

	private String montoPagoMostrar = "";

	private Integer idItem = null;

	private Integer idQuienRecibe = null;

	private String descripcionQuienRecibe = "";

	private Integer idTipoDePago = null;

	private String descripcionTipoDePago = "";

	private String descripcionTipoDePagoCorta = "";

	private Integer idBanco = null;

	private String claveBanco = "";

	private String descripcionBanco = "";

	private String descripcionBancoCorta = "";

	private Integer idPagoEntrega = null;

	private Double montoPagado = null;

	private String montoPagadoMostrar = "";

	private Boolean pagoActivo = null;

	private Boolean eliminarPago = null;

	private Boolean agregarPago = null;

	private String comentariosDevolucion = "";

	private Double montoCobrar = null;

	private Double remanente = null;

	private Double montoTotal = null;

	private String comentariosLiquidacion = "";

	private Integer idUsuario = null;

	private Integer idRepresentante = null;

	private Integer accion = null;

	private Integer idCampania = null;

	private Integer blokFlag = null;

	private Boolean deshabilitarEdicion = null;

	private String rutaSecuencia = "";

	private Double montoOrden = null;

	private String montoOrdenMostrar = "";

	private String liquidacion = "";

	private Integer cantidadItems = null;

	private Integer cantidadCajas = null;

	private Integer cantidadPremiosUnitarios = null;

	private String primeraDevolucion = "";

	private String comentario = "";

	private String ordenSegmentada = "";

	private String comentariosEntregaMostrar = "";

	private Integer idPersonaRecibeMostrar = null;

	private String comentariosNoEntregaMostrar = "";

	private Integer idRazonDevolucionMostrar = null;

	private Integer idInformanteMostrar = null;

	private String mensajeLiqPend = "";

	private Integer resultadoLiqPend = null;

	private String bloq = "";

	private String digitosModif = "";

	private Integer digitosModifNumerico = null;

	private String liqbanco = "";

	private String devolucionMostrar = "";

	private String cashSequence = "";

	private Boolean hijackedCash = null;

	private String valorAdeudaOriginalMostrar = "";

	private Double valorAdeudaOriginal = null;

	private String valorAdeudaRepresentanteMostrar = "";

	private Double valorAdeudaRepresentante = null;

	private Double valorModif = null;

	private String valorModifMostrar = "";

	private String fsc = "";

	private String ean13 = "";

	private String estatusItem = "";

	private Integer claveTipoLiquidacion = null;

	private String remitos = "";

	private DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');
		return new DecimalFormat("$ ###,##0.00", simbolos);
	}

	public void validarMostrarValores() {
		if (getValorAdeudaOriginal() != null) {
			setMontoOrden(getValorAdeudaOriginal());
			setValorModif(getValorAdeudaRepresentante());
		} else {
			setValorModif(null);
			if (getValorAdeudaRepresentante() != null && getValorAdeudaRepresentante() > 0D) {
				setMontoOrden(getValorAdeudaRepresentante());	
			} else {
				setMontoOrden(0D);
			}
		}
		validarLibreCobro();
		if (getCashSequence() != null) {
			if (getCashSequence().length() > 15) {
				setSecuenciaEfectivo(getCashSequence().substring(0,15));
			} else {
				setSecuenciaEfectivo(getCashSequence());
			}
		} else {
			setSecuenciaEfectivo("");
		}
		if (getRoboCajas() && getHijackedCash()) {
			setRobo("Caja,Efectivo");
		} else if (getRoboCajas() && !getHijackedCash()) {
			setRobo("Caja");
		} else if (!getRoboCajas() && getHijackedCash()) {
			setRobo("Efectivo");
		} else {
			setRobo("");
		}
	}

	private void validarLibreCobro() {
		if (getLibreCobro() != null && getLibreCobro().length() > 0) {
			setTipoDePago(getLibreCobro());
		}
	}

	public String getMontoOrdenMostrar() {
		return montoOrdenMostrar;
	}

	public void setMontoOrdenMostrar(String montoOrdenMostrar) {
		this.montoOrdenMostrar = montoOrdenMostrar;
	}

	public String getOrdenSegmentada() {
		return ordenSegmentada;
	}

	public void setOrdenSegmentada(String ordenSegmentada) {
		this.ordenSegmentada = ordenSegmentada;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getPrimeraDevolucion() {
		return primeraDevolucion;
	}

	public void setPrimeraDevolucion(String primeraDevolucion) {
		this.primeraDevolucion = primeraDevolucion;
	}

	public Integer getCantidadItems() {
		return cantidadItems;
	}

	public void setCantidadItems(Integer cantidadItems) {
		this.cantidadItems = cantidadItems;
	}

	public Integer getCantidadCajas() {
		return cantidadCajas;
	}

	public void setCantidadCajas(Integer cantidadCajas) {
		this.cantidadCajas = cantidadCajas;
	}

	public Integer getCantidadPremiosUnitarios() {
		return cantidadPremiosUnitarios;
	}

	public void setCantidadPremiosUnitarios(Integer cantidadPremiosUnitarios) {
		this.cantidadPremiosUnitarios = cantidadPremiosUnitarios;
	}

	public String getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(String liquidacion) {
		this.liquidacion = liquidacion;
	}

	public Double getMontoOrden() {
		return montoOrden;
	}

	public void setMontoOrden(Double montoOrden) {
		this.montoOrden = montoOrden;
		if (getMontoOrden() == null) {
			setMontoOrdenMostrar(getDecimalFormat().format(0D));
		} else {
			setMontoOrdenMostrar(getDecimalFormat().format(getMontoOrden()));
		}
	}

	public String getRutaSecuencia() {
		return rutaSecuencia;
	}

	public void setRutaSecuencia(String rutaSecuencia) {
		this.rutaSecuencia = rutaSecuencia;
	}

	public String getIdZona() {
		return idZona;
	}

	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}

	public String getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(String idRuta) {
		this.idRuta = idRuta;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	public String getDescripcionRuta() {
		return descripcionRuta;
	}

	public void setDescripcionRuta(String descripcionRuta) {
		this.descripcionRuta = descripcionRuta;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
		if (getRegistro() != null && getRegistro().length() > 0) {
			try {
				setRegistroNumerico(Integer.valueOf(getRegistro()));
			} catch(NumberFormatException e) {
				setRegistroNumerico(null);
			}
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getLiquidado() {
		return liquidado;
	}

	public void setLiquidado(String liquidado) {
		this.liquidado = liquidado;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Double getMontoPrevio() {
		return montoPrevio;
	}

	public void setMontoPrevio(Double montoPrevio) {
		this.montoPrevio = montoPrevio;
	}

	public Integer getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public Integer getIdRazonDevolucion() {
		return idRazonDevolucion;
	}

	public void setIdRazonDevolucion(Integer idRazonDevolucion) {
		this.idRazonDevolucion = idRazonDevolucion;
	}

	public String getDescripcionRazonDevolucion() {
		return descripcionRazonDevolucion;
	}

	public void setDescripcionRazonDevolucion(String descripcionRazonDevolucion) {
		this.descripcionRazonDevolucion = descripcionRazonDevolucion;
	}

	public Integer getIdTipoInformante() {
		return idTipoInformante;
	}

	public void setIdTipoInformante(Integer idTipoInformante) {
		this.idTipoInformante = idTipoInformante;
	}

	public String getDescripcionTipoInformante() {
		return descripcionTipoInformante;
	}

	public void setDescripcionTipoInformante(String descripcionTipoInformante) {
		this.descripcionTipoInformante = descripcionTipoInformante;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public String getTipoDePago() {
		return tipoDePago;
	}

	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public String getDescripcionPago() {
		return descripcionPago;
	}

	public void setDescripcionPago(String descripcionPago) {
		this.descripcionPago = descripcionPago;
	}

	public String getFolioPago() {
		return folioPago;
	}

	public void setFolioPago(String folioPago) {
		this.folioPago = folioPago;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(String montoPago) {
		this.montoPago = montoPago;
		if (montoPago != null) {
			try {
				this.setMontoPagoMostrar(getDecimalFormat().format(Double.valueOf(montoPago.trim())));
			} catch (Exception e) {
				this.setMontoPagoMostrar("");
			}
		} else {
			this.setMontoPagoMostrar("");
		}
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Integer getIdQuienRecibe() {
		return idQuienRecibe;
	}

	public void setIdQuienRecibe(Integer idQuienRecibe) {
		this.idQuienRecibe = idQuienRecibe;
	}

	public String getDescripcionQuienRecibe() {
		return descripcionQuienRecibe;
	}

	public void setDescripcionQuienRecibe(String descripcionQuienRecibe) {
		this.descripcionQuienRecibe = descripcionQuienRecibe;
	}

	public Integer getIdTipoDePago() {
		return idTipoDePago;
	}

	public void setIdTipoDePago(Integer idTipoDePago) {
		this.idTipoDePago = idTipoDePago;
	}

	public String getDescripcionTipoDePago() {
		return descripcionTipoDePago;
	}

	public void setDescripcionTipoDePago(String descripcionTipoDePago) {
		this.descripcionTipoDePago = descripcionTipoDePago;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public String getClaveBanco() {
		return claveBanco;
	}

	public void setClaveBanco(String claveBanco) {
		this.claveBanco = claveBanco;
	}

	public String getDescripcionBanco() {
		return descripcionBanco;
	}

	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}

	public Integer getIdPagoEntrega() {
		return idPagoEntrega;
	}

	public void setIdPagoEntrega(Integer idPagoEntrega) {
		this.idPagoEntrega = idPagoEntrega;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
		if (montoPagado != null) {
			try {
				this.setMontoPagadoMostrar(getDecimalFormat().format(montoPagado));
			} catch (Exception e) {
				this.setMontoPagadoMostrar("");
			}
		} else {
			this.setMontoPagadoMostrar("");
			this.montoPagado = 0D;
		}
		setMontoPago(getDecimalFormat().format(this.getMontoPagado()));
	}

	public Boolean getPagoActivo() {
		return pagoActivo;
	}

	public void setPagoActivo(Boolean pagoActivo) {
		this.pagoActivo = pagoActivo;
	}

	public Boolean getEliminarPago() {
		return eliminarPago;
	}

	public void setEliminarPago(Boolean eliminarPago) {
		this.eliminarPago = eliminarPago;
	}

	public Boolean getAgregarPago() {
		return agregarPago;
	}

	public void setAgregarPago(Boolean agregarPago) {
		this.agregarPago = agregarPago;
	}

	public String getComentariosDevolucion() {
		return comentariosDevolucion;
	}

	public void setComentariosDevolucion(String comentariosDevolucion) {
		this.comentariosDevolucion = comentariosDevolucion;
	}

	public Double getMontoCobrar() {
		return montoCobrar;
	}

	public void setMontoCobrar(Double montoCobrar) {
		this.montoCobrar = montoCobrar;
	}

	public Double getRemanente() {
		return remanente;
	}

	public void setRemanente(Double remanente) {
		this.remanente = remanente;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getComentariosLiquidacion() {
		return comentariosLiquidacion;
	}

	public void setComentariosLiquidacion(String comentariosLiquidacion) {
		this.comentariosLiquidacion = comentariosLiquidacion;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public Integer getAccion() {
		return accion;
	}

	public void setAccion(Integer accion) {
		this.accion = accion;
	}

	public Integer getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
	}

	public Integer getBlokFlag() {
		return blokFlag;
	}

	public void setBlokFlag(Integer blokFlag) {
		this.blokFlag = blokFlag;
		setDeshabilitarEdicion(blokFlag != null && blokFlag == 1);
	}

	public String getDescripcionTipoDePagoCorta() {
		return descripcionTipoDePagoCorta;
	}

	public void setDescripcionTipoDePagoCorta(String descripcionTipoDePagoCorta) {
		this.descripcionTipoDePagoCorta = descripcionTipoDePagoCorta;
	}

	public String getDescripcionBancoCorta() {
		return descripcionBancoCorta;
	}

	public void setDescripcionBancoCorta(String descripcionBancoCorta) {
		this.descripcionBancoCorta = descripcionBancoCorta;
	}

	public String getIdOrdenSegmentada() {
		return idOrdenSegmentada;
	}

	public void setIdOrdenSegmentada(String idOrdenSegmentada) {
		this.idOrdenSegmentada = idOrdenSegmentada;
	}

	public Boolean getDeshabilitarEdicion() {
		return deshabilitarEdicion;
	}

	public void setDeshabilitarEdicion(Boolean deshabilitarEdicion) {
		this.deshabilitarEdicion = deshabilitarEdicion;
	}

	public String getComentariosEntregaMostrar() {
		return comentariosEntregaMostrar;
	}

	public void setComentariosEntregaMostrar(String comentariosEntregaMostrar) {
		this.comentariosEntregaMostrar = comentariosEntregaMostrar;
	}

	public Integer getIdPersonaRecibeMostrar() {
		return idPersonaRecibeMostrar;
	}

	public void setIdPersonaRecibeMostrar(Integer idPersonaRecibeMostrar) {
		this.idPersonaRecibeMostrar = idPersonaRecibeMostrar;
	}

	public String getComentariosNoEntregaMostrar() {
		return comentariosNoEntregaMostrar;
	}

	public void setComentariosNoEntregaMostrar(String comentariosNoEntregaMostrar) {
		this.comentariosNoEntregaMostrar = comentariosNoEntregaMostrar;
	}

	public Integer getIdRazonDevolucionMostrar() {
		return idRazonDevolucionMostrar;
	}

	public void setIdRazonDevolucionMostrar(Integer idRazonDevolucionMostrar) {
		this.idRazonDevolucionMostrar = idRazonDevolucionMostrar;
	}

	public Integer getIdInformanteMostrar() {
		return idInformanteMostrar;
	}

	public void setIdInformanteMostrar(Integer idInformanteMostrar) {
		this.idInformanteMostrar = idInformanteMostrar;
	}

	public String getMontoPagoMostrar() {
		return montoPagoMostrar;
	}

	public void setMontoPagoMostrar(String montoPagoMostrar) {
		this.montoPagoMostrar = montoPagoMostrar;
	}

	public String getMontoPagadoMostrar() {
		return montoPagadoMostrar;
	}

	public void setMontoPagadoMostrar(String montoPagadoMostrar) {
		this.montoPagadoMostrar = montoPagadoMostrar;
	}

	public String getMensajeLiqPend() {
		return mensajeLiqPend;
	}

	public void setMensajeLiqPend(String mensajeLiqPend) {
		this.mensajeLiqPend = mensajeLiqPend;
	}

	public Integer getResultadoLiqPend() {
		return resultadoLiqPend;
	}

	public void setResultadoLiqPend(Integer resultadoLiqPend) {
		this.resultadoLiqPend = resultadoLiqPend;
	}

	public String getBloq() {
		return bloq;
	}

	public void setBloq(String bloq) {
		this.bloq = bloq;
	}

	public String getDigitosModif() {
		return digitosModif;
	}

	public void setDigitosModif(String digitosModif) {
		this.digitosModif = digitosModif;
		if (getDigitosModif() != null && getDigitosModif().length() > 0) {
			try {
				setDigitosModifNumerico(Integer.valueOf(getDigitosModif()));
			} catch(NumberFormatException e) {
				setDigitosModifNumerico(null);
			}
		}
	}

	public String getLiqbanco() {
		return liqbanco;
	}

	public void setLiqbanco(String liqbanco) {
		this.liqbanco = liqbanco;
	}

	public String getDevolucionMostrar() {
		return devolucionMostrar;
	}

	public void setDevolucionMostrar(String devolucionMostrar) {
		this.devolucionMostrar = devolucionMostrar;
	}

	public Boolean getRoboCajas() {
		return roboCajas;
	}

	public void setRoboCajas(Boolean roboCajas) {
		this.roboCajas = roboCajas;
	}

	public String getCashSequence() {
		return cashSequence;
	}

	public void setCashSequence(String cashSequence) {
		this.cashSequence = cashSequence;
	}

	public Boolean getHijackedCash() {
		return hijackedCash;
	}

	public void setHijackedCash(Boolean hijackedCash) {
		this.hijackedCash = hijackedCash;
	}

	public String getValorAdeudaOriginalMostrar() {
		return valorAdeudaOriginalMostrar;
	}

	public void setValorAdeudaOriginalMostrar(String valorAdeudaOriginalMostrar) {
		this.valorAdeudaOriginalMostrar = valorAdeudaOriginalMostrar;
	}

	public Double getValorAdeudaOriginal() {
		return valorAdeudaOriginal;
	}

	public void setValorAdeudaOriginal(Double valorAdeudaOriginal) {
		this.valorAdeudaOriginal = valorAdeudaOriginal;
		if (getValorAdeudaOriginal() == null) {
			setValorAdeudaOriginalMostrar("");
		} else {
			setValorAdeudaOriginalMostrar(getDecimalFormat().format(getValorAdeudaOriginal()));
		}
	}

	public String getValorAdeudaRepresentanteMostrar() {
		return valorAdeudaRepresentanteMostrar;
	}

	public void setValorAdeudaRepresentanteMostrar(
			String valorAdeudaRepresentanteMostrar) {
		this.valorAdeudaRepresentanteMostrar = valorAdeudaRepresentanteMostrar;
	}

	public Double getValorAdeudaRepresentante() {
		return valorAdeudaRepresentante;
	}

	public void setValorAdeudaRepresentante(Double valorAdeudaRepresentante) {
		this.valorAdeudaRepresentante = valorAdeudaRepresentante;
		if (getValorAdeudaRepresentante() == null) {
			setValorAdeudaRepresentanteMostrar("");
		} else {
			setValorAdeudaRepresentanteMostrar(getDecimalFormat().format(getValorAdeudaRepresentante()));
		}
	}

	public String getValorModifMostrar() {
		return valorModifMostrar;
	}

	public void setValorModifMostrar(String valorModifMostrar) {
		this.valorModifMostrar = valorModifMostrar;
	}

	public Double getValorModif() {
		return valorModif;
	}

	public void setValorModif(Double valorModif) {
		this.valorModif = valorModif;
		if (getValorModif() == null) {
			setValorModifMostrar("");
		} else {
			setValorModifMostrar(getDecimalFormat().format(getValorModif()));
		}
	}

	public String getFsc() {
		return fsc;
	}

	public void setFsc(String fsc) {
		this.fsc = fsc;
	}

	public String getEan13() {
		return ean13;
	}

	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}

	public String getEstatusItem() {
		return estatusItem;
	}

	public void setEstatusItem(String estatusItem) {
		this.estatusItem = estatusItem;
	}

	public Integer getRegistroNumerico() {
		return registroNumerico;
	}

	public void setRegistroNumerico(Integer registroNumerico) {
		this.registroNumerico = registroNumerico;
	}

	public Integer getDigitosModifNumerico() {
		return digitosModifNumerico;
	}

	public void setDigitosModifNumerico(Integer digitosModifNumerico) {
		this.digitosModifNumerico = digitosModifNumerico;
	}

	public String getQuienRecibe() {
		return quienRecibe;
	}

	public void setQuienRecibe(String quienRecibe) {
		this.quienRecibe = quienRecibe;
	}

	public String getLibreCobro() {
		return libreCobro;
	}

	public void setLibreCobro(String libreCobro) {
		this.libreCobro = libreCobro;
	}

	public String getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(String idEstatus) {
		this.idEstatus = idEstatus;
	}

	public Integer getCantidadPagosEntrega() {
		return cantidadPagosEntrega;
	}

	public void setCantidadPagosEntrega(Integer cantidadPagosEntrega) {
		this.cantidadPagosEntrega = cantidadPagosEntrega;
	}

	public Double getMontoPagadoTotalEntrega() {
		return montoPagadoTotalEntrega;
	}

	public void setMontoPagadoTotalEntrega(Double montoPagadoTotalEntrega) {
		this.montoPagadoTotalEntrega = montoPagadoTotalEntrega;
	}

	public Integer getCantidadPagosVentanilla() {
		return cantidadPagosVentanilla;
	}

	public void setCantidadPagosVentanilla(Integer cantidadPagosVentanilla) {
		this.cantidadPagosVentanilla = cantidadPagosVentanilla;
	}

	public Double getMontoPagadoTotalVentanilla() {
		return montoPagadoTotalVentanilla;
	}

	public void setMontoPagadoTotalVentanilla(Double montoPagadoTotalVentanilla) {
		this.montoPagadoTotalVentanilla = montoPagadoTotalVentanilla;
	}

	public String getRobo() {
		return robo;
	}

	public void setRobo(String robo) {
		this.robo = robo;
	}

	public String getSecuenciaEfectivo() {
		return secuenciaEfectivo;
	}

	public void setSecuenciaEfectivo(String secuenciaEfectivo) {
		this.secuenciaEfectivo = secuenciaEfectivo;
	}

	public Integer getClaveTipoLiquidacion() {
		return claveTipoLiquidacion;
	}

	public void setClaveTipoLiquidacion(Integer claveTipoLiquidacion) {
		this.claveTipoLiquidacion = claveTipoLiquidacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getRemitos() {
		return remitos;
	}

	public void setRemitos(String remitos) {
		this.remitos = remitos;
	}

}
