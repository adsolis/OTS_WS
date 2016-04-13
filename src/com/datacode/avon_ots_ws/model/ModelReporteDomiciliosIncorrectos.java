package com.datacode.avon_ots_ws.model;

import java.util.Date;

public class ModelReporteDomiciliosIncorrectos {

	private String zona;
	private String ruta;
	private String registro;
	private String nombre;
	private String domicilioActual;
	private String domicilioCorrecto;
	private Date fechaReporte;
	private String email;
	private String fechaReporteS;

	public String getFechaReporteS() {
		return fechaReporteS;
	}

	public void setFechaReporteS(String fechaReporteS) {
		this.fechaReporteS = fechaReporteS;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

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

	public String getDomicilioActual() {
		return domicilioActual;
	}

	public void setDomicilioActual(String domicilioActual) {
		this.domicilioActual = domicilioActual;
	}

	public String getDomicilioCorrecto() {
		return domicilioCorrecto;
	}

	public void setDomicilioCorrecto(String domicilioCorrecto) {
		this.domicilioCorrecto = domicilioCorrecto;
	}

	public Date getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(Date fechaReporte) {
		this.fechaReporte = fechaReporte;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
