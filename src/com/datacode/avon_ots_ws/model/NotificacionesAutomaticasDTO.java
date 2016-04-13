package com.datacode.avon_ots_ws.model;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesAutomaticasDTO {

	private Integer idNotificacion = null;

	private String nombre = null;

	private String descripcion = null;

	private String texto = null;

	private String destinatarios = null;

	private List<String> listaDestinatarios = null;

	private String id;

	public Integer getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(Integer idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
		if (destinatarios == null) {
			setListaDestinatarios(null);
		} else if (destinatarios.trim().length() == 0) {
			setListaDestinatarios(null);
		} else if ("".equals(destinatarios)) {
			setListaDestinatarios(null);
		} else {
			String[] arregloDestinatarios = destinatarios.split(",");
			setListaDestinatarios(new ArrayList<String>());
			for (String destinatario : arregloDestinatarios) {
				getListaDestinatarios().add(destinatario.trim().toLowerCase());
			}
		}
	}

	public List<String> getListaDestinatarios() {
		return listaDestinatarios;
	}

	public void setListaDestinatarios(List<String> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
