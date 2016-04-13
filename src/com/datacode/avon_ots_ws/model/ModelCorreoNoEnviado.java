package com.datacode.avon_ots_ws.model;

public class ModelCorreoNoEnviado {

	private int idCorreoPendiente;
	private String de;
	private String para;
	private String asunto;
	private String texto;
	private int idReporte;
	private String nombreReporte;
	private String nombreArchivo;

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public int getIdCorreoPendiente() {
		return idCorreoPendiente;
	}

	public void setIdCorreoPendiente(int idCorreoPendiente) {
		this.idCorreoPendiente = idCorreoPendiente;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}

}
