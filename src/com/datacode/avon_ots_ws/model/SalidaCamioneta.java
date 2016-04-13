package com.datacode.avon_ots_ws.model;

import java.util.List;

public class SalidaCamioneta {

	private String campania;
	private String ordenes;
	private String cajas;
	private String unitarios;
	private String premios;
	private String nombreChofer;
	private String nombreAyudante;
	private String fechaSalidaReparto;
	private String hrSalidaReparto;
	private String hrPrimerVisita;
	private String hrUltimaVisita;
	private String hrRegresoBodega;
	private double kmSalidaReparto;
	private double kmPrimerVisita;
	private double kmUltimaVisita;
	private double kmRegresoBodega;
	private String zona;
	private String ruta;
	private List<DesgloceEfectivo> desgloceBilletes;
	private List<DesgloceEfectivo> desgloceMoneda;
	private List<LiquidacionRep> liquidaciones;
	
	
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public String getOrdenes() {
		return ordenes;
	}
	public void setOrdenes(String ordenes) {
		this.ordenes = ordenes;
	}
	public String getCajas() {
		return cajas;
	}
	public void setCajas(String cajas) {
		this.cajas = cajas;
	}
	public String getUnitarios() {
		return unitarios;
	}
	public void setUnitarios(String unitarios) {
		this.unitarios = unitarios;
	}
	public String getPremios() {
		return premios;
	}
	public void setPremios(String premios) {
		this.premios = premios;
	}
	public String getNombreChofer() {
		return nombreChofer;
	}
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}
	public String getNombreAyudante() {
		return nombreAyudante;
	}
	public void setNombreAyudante(String nombreAyudante) {
		this.nombreAyudante = nombreAyudante;
	}
	public String getFechaSalidaReparto() {
		return fechaSalidaReparto;
	}
	public void setFechaSalidaReparto(String fechaSalidaReparto) {
		this.fechaSalidaReparto = fechaSalidaReparto;
	}
	public String getHrSalidaReparto() {
		return hrSalidaReparto;
	}
	public void setHrSalidaReparto(String hrSalidaReparto) {
		this.hrSalidaReparto = hrSalidaReparto;
	}
	public String getHrPrimerVisita() {
		return hrPrimerVisita;
	}
	public void setHrPrimerVisita(String hrPrimerVisita) {
		this.hrPrimerVisita = hrPrimerVisita;
	}
	public String getHrUltimaVisita() {
		return hrUltimaVisita;
	}
	public void setHrUltimaVisita(String hrUltimaVisita) {
		this.hrUltimaVisita = hrUltimaVisita;
	}
	public String getHrRegresoBodega() {
		return hrRegresoBodega;
	}
	public void setHrRegresoBodega(String hrRegresoBodega) {
		this.hrRegresoBodega = hrRegresoBodega;
	}
	public double getKmSalidaReparto() {
		return kmSalidaReparto;
	}
	public void setKmSalidaReparto(double kmSalidaReparto) {
		this.kmSalidaReparto = kmSalidaReparto;
	}
	public double getKmPrimerVisita() {
		return kmPrimerVisita;
	}
	public void setKmPrimerVisita(double kmPrimerVisita) {
		this.kmPrimerVisita = kmPrimerVisita;
	}
	public double getKmUltimaVisita() {
		return kmUltimaVisita;
	}
	public void setKmUltimaVisita(double kmUltimaVisita) {
		this.kmUltimaVisita = kmUltimaVisita;
	}
	public double getKmRegresoBodega() {
		return kmRegresoBodega;
	}
	public void setKmRegresoBodega(double kmRegresoBodega) {
		this.kmRegresoBodega = kmRegresoBodega;
	}
	public List<DesgloceEfectivo> getDesgloceBilletes() {
		return desgloceBilletes;
	}
	public void setDesgloceBilletes(List<DesgloceEfectivo> desgloceBilletes) {
		this.desgloceBilletes = desgloceBilletes;
	}
	public List<DesgloceEfectivo> getDesgloceMoneda() {
		return desgloceMoneda;
	}
	public void setDesgloceMoneda(List<DesgloceEfectivo> desgloceMoneda) {
		this.desgloceMoneda = desgloceMoneda;
	}
	public List<LiquidacionRep> getLiquidaciones() {
		return liquidaciones;
	}
	public void setLiquidaciones(List<LiquidacionRep> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
