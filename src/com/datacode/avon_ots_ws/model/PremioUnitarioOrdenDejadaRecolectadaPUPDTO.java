package com.datacode.avon_ots_ws.model;

public class PremioUnitarioOrdenDejadaRecolectadaPUPDTO {

	String fsc;
	String ean13;
	Integer cantidad;
	Integer cantidadStatus;

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
	public Integer getCantidadStatus() {
		return cantidadStatus;
	}
	public void setCantidadStatus(Integer cantidadStatus) {
		this.cantidadStatus = cantidadStatus;
	}
	
}
