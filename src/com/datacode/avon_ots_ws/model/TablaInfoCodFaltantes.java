package com.datacode.avon_ots_ws.model;

public class TablaInfoCodFaltantes {

	private String registro;
	private int totalCajas;
	private int totalRecibido;
	private int cajasFaltantes;
	private short numCajaFaltante;
	private String observaciones;
	
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public int getTotalCajas() {
		return totalCajas;
	}
	public void setTotalCajas(int totalCajas) {
		this.totalCajas = totalCajas;
	}
	public int getTotalRecibido() {
		return totalRecibido;
	}
	public void setTotalRecibido(int totalRecibido) {
		this.totalRecibido = totalRecibido;
	}
	public int getCajasFaltantes() {
		return cajasFaltantes;
	}
	public void setCajasFaltantes(int cajasFaltantes) {
		this.cajasFaltantes = cajasFaltantes;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public short getNumCajaFaltante() {
		return numCajaFaltante;
	}
	public void setNumCajaFaltante(short numCajaFaltante) {
		this.numCajaFaltante = numCajaFaltante;
	}
	
}
