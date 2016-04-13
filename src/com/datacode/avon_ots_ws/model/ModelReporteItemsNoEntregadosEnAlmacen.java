package com.datacode.avon_ots_ws.model;

import java.util.Date;

public class ModelReporteItemsNoEntregadosEnAlmacen {

	private String nombreRepresentante;
	private String registro;
	private String ruta;
	private String numeroOrden;
	private String descItem;
	private String codigoItem;
	private String nombreChofer;
	private Date fechaHoraDevolucion;
	private String fechaHoraDevolucionS;
	private String zona;
	private String campania;

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getDescItem() {
		return descItem;
	}

	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}

	public String getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public Date getFechaHoraDevolucion() {
		return fechaHoraDevolucion;
	}

	public void setFechaHoraDevolucion(Date fechaHoraDevolucion) {
		this.fechaHoraDevolucion = fechaHoraDevolucion;
	}

	public String getFechaHoraDevolucionS() {
		return fechaHoraDevolucionS;
	}

	public void setFechaHoraDevolucionS(String fechaHoraDevolucionS) {
		this.fechaHoraDevolucionS = fechaHoraDevolucionS;
	}
}
