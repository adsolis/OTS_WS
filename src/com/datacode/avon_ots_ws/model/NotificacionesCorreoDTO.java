package com.datacode.avon_ots_ws.model;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesCorreoDTO {

	private Integer idNotificacionCorreo = null;

	private String nombre = null;

	private String descripcion = null;

	private String asunto = null;

	private String cuerpo = null;

	private String destinatarios = null;

	private String inicioIntervaloEjecucion = null;

	private String finIntervaloEjecucion = null;

	private List<String> listaDestinatarios = null;

	private String id = null;

	public Integer getIdNotificacionCorreo() {
		return idNotificacionCorreo;
	}

	public void setIdNotificacionCorreo(Integer idNotificacionCorreo) {
		this.idNotificacionCorreo = idNotificacionCorreo;
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

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getInicioIntervaloEjecucion() {
		return inicioIntervaloEjecucion;
	}

	public void setInicioIntervaloEjecucion(String inicioIntervaloEjecucion) {
		this.inicioIntervaloEjecucion = inicioIntervaloEjecucion;
	}

	public String getFinIntervaloEjecucion() {
		return finIntervaloEjecucion;
	}

	public void setFinIntervaloEjecucion(String finIntervaloEjecucion) {
		this.finIntervaloEjecucion = finIntervaloEjecucion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
