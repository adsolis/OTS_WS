package com.datacode.avon_ots_ws.model;

public class EntregaVentanillaMasiva {
	//Representante
	String registro = "";
	String nombre = "";
	String direccion = "";
	String cajas = "";
	String unitarios = "";
	String premios = "";
	String montoCobrar = "";
	String idOrden = "";
	String idZona = "";
	String zona = "";
	String numeroOrden="";
	
	public String getNumeroOrden() {
		return numeroOrden;
	}
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getMontoCobrar() {
		return montoCobrar;
	}
	public void setMontoCobrar(String montoCobrar) {
		this.montoCobrar = montoCobrar;
	}	
	public String getCajas() {
		return cajas;
	}
	public void setCajas(String cajas) {
		this.cajas = cajas;
	}
	public String getUnitarios() {
		return unitarios;
	}
	public void setUnitarios(String unitarios) {
		this.unitarios = unitarios;
	}
	public String getPremios() {
		return premios;
	}
	public void setPremios(String premios) {
		this.premios = premios;
	}
	public String getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(String idOrden) {
		this.idOrden = idOrden;
	}	
	public String getIdZona() {
		return idZona;
	}
	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}


	//DetallesOrden
	String idItem = "";
	String codigo = "";
	String codigoFSC = "";
	String codigoEAN13 = "";
	String tipo = "";
	String descripcion = "";
	String estatus = "";
	String cantidad = "";
	String escaneado = "";

	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigoFSC() {
		return codigoFSC;
	}
	public void setCodigoFSC(String codigo) {
		this.codigoFSC = codigo;
	}
	
	public String getCodigoEAN13() {
		return codigoEAN13;
	}
	public void setCodigoEAN13(String codigo) {
		this.codigoEAN13 = codigo;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getEscaneado() {
		return escaneado;
	}
	public void setEscaneado(String escaneado) {
		this.escaneado = escaneado;
	}

		
}