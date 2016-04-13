package com.datacode.avon_ots_ws.model;

public class ModelEstatusCatalogo {

	private int idReplicacionTabla;
	private String nombreOrigen;
	private String avance;
	private String estatus;
	private String tipo;

	public int getIdReplicacionTabla() {
		return idReplicacionTabla;
	}

	public void setIdReplicacionTabla(int idReplicacionTabla) {
		this.idReplicacionTabla = idReplicacionTabla;
	}

	public String getNombreOrigen() {
		return nombreOrigen;
	}

	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}

	public String getAvance() {
		return avance;
	}

	public void setAvance(String avance) {
		this.avance = avance;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
