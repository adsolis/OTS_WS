package com.datacode.avon_ots_ws.model;

import java.io.Serializable;

public class RecolectarRemitosVentanilla implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idZona = null;

	private String descripcionZona = null;

	private String campania = null;

	private Integer account = null;

	private String nombre = null;

	private Integer idRemito = null;

	private Integer cantidadRecolectar = null;

	private Integer cantidadRecolectada = null;

	private Integer idCausaNoRecoleccion = null;

	private String descripcionCausaNoRecoleccion = null;

	public String getIdZona() {
		return idZona;
	}

	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(Integer idRemito) {
		this.idRemito = idRemito;
	}

	public Integer getCantidadRecolectar() {
		return cantidadRecolectar;
	}

	public void setCantidadRecolectar(Integer cantidadRecolectar) {
		this.cantidadRecolectar = cantidadRecolectar;
	}

	public Integer getCantidadRecolectada() {
		return cantidadRecolectada;
	}

	public void setCantidadRecolectada(Integer cantidadRecolectada) {
		this.cantidadRecolectada = cantidadRecolectada;
	}

	public Integer getIdCausaNoRecoleccion() {
		return idCausaNoRecoleccion;
	}

	public void setIdCausaNoRecoleccion(Integer idCausaNoRecoleccion) {
		this.idCausaNoRecoleccion = idCausaNoRecoleccion;
	}

	public String getDescripcionCausaNoRecoleccion() {
		return descripcionCausaNoRecoleccion;
	}

	public void setDescripcionCausaNoRecoleccion(
			String descripcionCausaNoRecoleccion) {
		this.descripcionCausaNoRecoleccion = descripcionCausaNoRecoleccion;
	}

}
