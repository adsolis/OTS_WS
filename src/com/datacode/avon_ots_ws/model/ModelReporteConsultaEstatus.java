package com.datacode.avon_ots_ws.model;

public class ModelReporteConsultaEstatus {

	private String zona;
	private String nombre;
	private String idOrden;
	private String posiciones;
	private String pagos;
	private String item;
	private String tipoItem;
	private String estatusItem;
	private String fechaEstatus;
	private String codigoBarras;
	private String usuario;
	private String ultimoEstatus;
	private byte[] foto;
	private double monto;
	private String hhadmin;
	private String ultimoEstatusNum;
	private String clave_orden;

	public String getClave_orden() {
		return clave_orden;
	}

	public void setClave_orden(String clave_orden) {
		this.clave_orden = clave_orden;
	}

	public String getHhadmin() {
		return hhadmin;
	}

	public void setHhadmin(String hhadmin) {
		this.hhadmin = hhadmin;
	}

	public String getUltimoEstatusNum() {
		return ultimoEstatusNum;
	}

	public void setUltimoEstatusNum(String ultimoEstatusNum) {
		this.ultimoEstatusNum = ultimoEstatusNum;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(String idOrden) {
		this.idOrden = idOrden;
	}

	public String getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(String posiciones) {
		this.posiciones = posiciones;
	}

	public String getPagos() {
		return pagos;
	}

	public void setPagos(String pagos) {
		this.pagos = pagos;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public String getEstatusItem() {
		return estatusItem;
	}

	public void setEstatusItem(String estatusItem) {
		this.estatusItem = estatusItem;
	}

	public String getFechaEstatus() {
		return fechaEstatus;
	}

	public void setFechaEstatus(String fechaEstatus) {
		this.fechaEstatus = fechaEstatus;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUltimoEstatus() {
		return ultimoEstatus;
	}

	public void setUltimoEstatus(String ultimoEstatus) {
		this.ultimoEstatus = ultimoEstatus;
	}

}
