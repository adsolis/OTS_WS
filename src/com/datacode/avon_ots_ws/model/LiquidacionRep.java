package com.datacode.avon_ots_ws.model;

public class LiquidacionRep {

	private String concepto;
	private double cods;
	private int cajas;
	private int premios;
	private int unitarios;
	private String recibio;
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public double getCods() {
		return cods;
	}
	public void setCods(double cods) {
		this.cods = cods;
	}
	public int getCajas() {
		return cajas;
	}
	public void setCajas(int cajas) {
		this.cajas = cajas;
	}
	public String getRecibio() {
		return recibio;
	}
	public void setRecibio(String recibio) {
		this.recibio = recibio;
	}
	public int getPremios() {
		return premios;
	}
	public void setPremios(int premios) {
		this.premios = premios;
	}
	public int getUnitarios() {
		return unitarios;
	}
	public void setUnitarios(int unitarios) {
		this.unitarios = unitarios;
	}
}
