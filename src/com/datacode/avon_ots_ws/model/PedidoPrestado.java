package com.datacode.avon_ots_ws.model;

import java.util.Date;
import java.util.List;

public class PedidoPrestado {

	private String titulo;
	private String campania;
	private String zona;
	private Date fecha;
	private String entregado;
	private String codigo;
	private List<ModelTablaRelPedidosPrestados> listaPedidos;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEntregado() {
		return entregado;
	}
	public void setEntregado(String entregado) {
		this.entregado = entregado;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<ModelTablaRelPedidosPrestados> getListaPedidos() {
		return listaPedidos;
	}
	public void setListaPedidos(List<ModelTablaRelPedidosPrestados> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	
}
