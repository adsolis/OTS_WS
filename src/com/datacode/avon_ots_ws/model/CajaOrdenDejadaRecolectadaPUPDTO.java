package com.datacode.avon_ots_ws.model;

public class CajaOrdenDejadaRecolectadaPUPDTO {

	Integer zona;
	String campania;
	Long orden;
	Long registro;
	String item;
	String codigoBarras;
	Integer dejadoPUP;

	public Integer getZona() {
		return zona;
	}
	public void setZona(Integer zona) {
		this.zona = zona;
	}
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public Long getOrden() {
		return orden;
	}
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	public Long getRegistro() {
		return registro;
	}
	public void setRegistro(Long registro) {
		this.registro = registro;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Integer getDejadoPUP() {
		return dejadoPUP;
	}
	public void setDejadoPUP(Integer dejadoPUP) {
		this.dejadoPUP = dejadoPUP;
	}
	
}
