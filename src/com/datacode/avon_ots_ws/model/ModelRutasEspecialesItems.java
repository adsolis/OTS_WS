package com.datacode.avon_ots_ws.model;

public class ModelRutasEspecialesItems {

	private String claveOrden;
	private int idItem;
	private String codigoBarras;
	private String descripcion;
	private int cantidadSolicitada;
	private String tipo;
	private String codigo_FSC;
	private String codigo_FSC_EAN13;
	private String registro;
	private String nombre;
	private int idTipoOrigen;
	private int cantidadAsignada;
	private int cantidadFacturada;

	public int getCantidadAsignada() {
		return cantidadAsignada;
	}

	public void setCantidadAsignada(int cantidadAsignada) {
		this.cantidadAsignada = cantidadAsignada;
	}

	public int getCantidadFacturada() {
		return cantidadFacturada;
	}

	public void setCantidadFacturada(int cantidadFacturada) {
		this.cantidadFacturada = cantidadFacturada;
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

	public int getIdTipoOrigen() {
		return idTipoOrigen;
	}

	public void setIdTipoOrigen(int idTipoOrigen) {
		this.idTipoOrigen = idTipoOrigen;
	}

	public String getCodigo_FSC() {
		return codigo_FSC;
	}

	public void setCodigo_FSC(String codigo_FSC) {
		this.codigo_FSC = codigo_FSC;
	}

	public String getCodigo_FSC_EAN13() {
		return codigo_FSC_EAN13;
	}

	public void setCodigo_FSC_EAN13(String codigo_FSC_EAN13) {
		this.codigo_FSC_EAN13 = codigo_FSC_EAN13;
	}

	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public String getClaveOrden() {
		return claveOrden;
	}

	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
