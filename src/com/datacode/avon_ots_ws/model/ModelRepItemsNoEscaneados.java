package com.datacode.avon_ots_ws.model;

public class ModelRepItemsNoEscaneados {

	private String ruta;
	private String nombreChofer;
	private String registro;
	private String codItem;
	private String descrItem;
	private String campania;
	private String zona;
	private String claveOrden;
	private String fsc;
	private String ean13;
	
	public ModelRepItemsNoEscaneados () {
		this.ruta = "";
		this.nombreChofer = "";
		this.registro = "";
		this.codItem = "";
		this.descrItem = "";
		this.zona = "";
		this.campania = "";
		this.claveOrden = "";
		this.fsc = "";
		this.ean13 = "";
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getCodItem() {
		return codItem;
	}

	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}

	public String getDescrItem() {
		return descrItem;
	}

	public void setDescrItem(String descrItem) {
		this.descrItem = descrItem;
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
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

}
