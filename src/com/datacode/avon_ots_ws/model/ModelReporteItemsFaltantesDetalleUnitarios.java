package com.datacode.avon_ots_ws.model;

public class ModelReporteItemsFaltantesDetalleUnitarios {

	private String claveOrden;
	private String codigoFSC;
	private String codigoEAN13;
	private String descripcion;
	private String tipoItem;
	private int cantidadCajas;
	private int faltantes;

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}

	public String getCodigoFSC() {
		return codigoFSC;
	}

	public void setCodigoFSC(String codigoFSC) {
		this.codigoFSC = codigoFSC;
	}

	public String getCodigoEAN13() {
		return codigoEAN13;
	}

	public void setCodigoEAN13(String codigoEAN13) {
		this.codigoEAN13 = codigoEAN13;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public int getCantidadCajas() {
		return cantidadCajas;
	}

	public void setCantidadCajas(int cantidadCajas) {
		this.cantidadCajas = cantidadCajas;
	}

	public int getFaltantes() {
		return faltantes;
	}

	public void setFaltantes(int faltantes) {
		this.faltantes = faltantes;
	}
}
