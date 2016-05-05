package com.datacode.avon_ots_ws.model;

public class PremioUnitarioOrdenDejadaRecolectadaPUPDTO {

	private String fsc;
	private String ean13;
	private Integer cantidad;
	private Integer dejadoPUP;
	private Integer recolectadoPUP;
	private Integer cantidadStatus;

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
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getDejadoPUP() {
		return dejadoPUP;
	}
	public void setDejadoPUP(Integer dejadoPUP) {
		this.dejadoPUP = dejadoPUP;
	}
	public Integer getRecolectadoPUP() {
		return recolectadoPUP;
	}
	public void setRecolectadoPUP(Integer recolectadoPUP) {
		this.recolectadoPUP = recolectadoPUP;
	}
	public Integer getCantidadStatus() {
		return cantidadStatus;
	}
	public void setCantidadStatus(Integer cantidadStatus) {
		this.cantidadStatus = cantidadStatus;
	}
	
}
