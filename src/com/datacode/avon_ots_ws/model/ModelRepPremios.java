package com.datacode.avon_ots_ws.model;

import java.util.List;

public class ModelRepPremios {

	private List<ModelRepTablaPremios> listaPremios;
	private String campaniaH;
	private String zonaH;
	public List<ModelRepTablaPremios> getListaPremios() {
		return listaPremios;
	}
	public void setListaPremios(List<ModelRepTablaPremios> listaPremios) {
		this.listaPremios = listaPremios;
	}
	public String getCampaniaH() {
		return campaniaH;
	}
	public void setCampaniaH(String campaniaH) {
		this.campaniaH = campaniaH;
	}
	public String getZonaH() {
		return zonaH;
	}
	public void setZonaH(String zonaH) {
		this.zonaH = zonaH;
	}
}
