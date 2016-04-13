package com.datacode.avon_ots_ws.model;

import java.io.Serializable;

public class EntregaFaltantes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String registroRepresentante = "";

	private String nombreRepresentante = "";

	private String domicilio = "";

	private String idZona = "";

	private String descripcionZona = "";

	private String descripcionCampania = "";

	private Integer idRepresentante = null;

	private Integer IdOrden = null;

	private String claveOrden = "";

	private Integer idItem = null;

	private String descripcionItem = "";

	private String codigoBarras = "";

	private String FSC = "";

	private String EAN13 = "";

	private Integer facturados = null;

	private Integer entrega;

	private Boolean seleccionado;

	public String getRegistroRepresentante() {
		return registroRepresentante;
	}

	public void setRegistroRepresentante(String registroRepresentante) {
		this.registroRepresentante = registroRepresentante;
	}

	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getIdZona() {
		return idZona;
	}

	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public String getDescripcionCampania() {
		return descripcionCampania;
	}

	public void setDescripcionCampania(String descripcionCampania) {
		this.descripcionCampania = descripcionCampania;
	}

	public Integer getIdOrden() {
		return IdOrden;
	}

	public void setIdOrden(Integer idOrden) {
		IdOrden = idOrden;
	}

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getFSC() {
		return FSC;
	}

	public void setFSC(String fSC) {
		FSC = fSC;
	}

	public String getEAN13() {
		return EAN13;
	}

	public void setEAN13(String eAN13) {
		EAN13 = eAN13;
	}

	public Integer getFacturados() {
		return facturados;
	}

	public void setFacturados(Integer facturados) {
		this.facturados = facturados;
	}

	public Boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public Integer getEntrega() {
		return entrega;
	}

	public void setEntrega(Integer entrega) {
		this.entrega = entrega;
	}

}
