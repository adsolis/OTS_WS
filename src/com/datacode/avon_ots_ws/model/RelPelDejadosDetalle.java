package com.datacode.avon_ots_ws.model;

public class RelPelDejadosDetalle {

	private int numero;
	private String registro;
	private String nombre;
	private String ruta;
	private double importe;
	private int cajas;
	private int premios;
	private int unitarios;
	private int causaDev;
	public int getPremios() {
		return premios;
	}
	public void setPremios(int premios) {
		this.premios = premios;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public int getCajas() {
		return cajas;
	}
	public void setCajas(int cajas) {
		this.cajas = cajas;
	}
	public int getUnitarios() {
		return unitarios;
	}
	public void setUnitarios(int unitarios) {
		this.unitarios = unitarios;
	}
	public int getCausaDev() {
		return causaDev;
	}
	public void setCausaDev(int causaDev) {
		this.causaDev = causaDev;
	}
}
