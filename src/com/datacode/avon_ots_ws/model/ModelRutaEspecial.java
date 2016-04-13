package com.datacode.avon_ots_ws.model;

import java.util.Date;

public class ModelRutaEspecial {

	private String claveRutaEspecial;
	private int idRutaEspecial;
	private String registro;
	private String nombre;
	private String claveOrden;
	private int idOrden;
	private int numOrdenes;
	private Date fechaCreacion;
	private String fechaCreacionS;
	private String campania;

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public int getNumOrdenes() {
		return numOrdenes;
	}

	public void setNumOrdenes(int numOrdenes) {
		this.numOrdenes = numOrdenes;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaCreacionS() {
		return fechaCreacionS;
	}

	public void setFechaCreacionS(String fechaCreacionS) {
		this.fechaCreacionS = fechaCreacionS;
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

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public String getClaveRutaEspecial() {
		return claveRutaEspecial;
	}

	public void setClaveRutaEspecial(String claveRutaEspecial) {
		this.claveRutaEspecial = claveRutaEspecial;
	}

	public int getIdRutaEspecial() {
		return idRutaEspecial;
	}

	public void setIdRutaEspecial(int idRutaEspecial) {
		this.idRutaEspecial = idRutaEspecial;
	}
}
