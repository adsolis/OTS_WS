package com.datacode.avon_ots_ws.model;

public class ModelReporteItemsFaltantesDetalleCajas {

	private String ruta;
	private String registro;
	private String nombre;
	private String orden;
	private String codigoBarras;
	private String estatus;
	private int cantidadCajas;
	private int faltantes;

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
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
