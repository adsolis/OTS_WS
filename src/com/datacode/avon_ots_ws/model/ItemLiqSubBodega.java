/**
 * @author jorge.torner
 * @since 23/01/2012
 */
package com.datacode.avon_ots_ws.model;

import java.util.Date;

/**
 * Clase del modelo de las consultas para los grids de Liquidación de SubBodega 
 * @author jorge.torner
 * @since 23/01/2012
 */
public class ItemLiqSubBodega {
	private String registroRep;
	private String nombreRep;
	private long idOrden;
	private String claveOrden;
	private long idItem;
	private String codigoBarras;
	private String descripcionItem;
	private short idTipoItem;
	private String tipoItem;
	private int cantidad;
	private int cantEntregada;
	private int cantRecibida;
	private Date fechaEntrega;
	private String causaFaltante;
	
	
	public String getRegistroRep() {
		return registroRep;
	}
	public void setRegistroRep(String registroRep) {
		this.registroRep = registroRep;
	}
	public String getNombreRep() {
		return nombreRep;
	}
	public void setNombreRep(String nombreRep) {
		this.nombreRep = nombreRep;
	}
	public long getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}
	public String getClaveOrden() {
		return claveOrden;
	}
	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
	}
	public long getIdItem() {
		return idItem;
	}
	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getDescripcionItem() {
		return descripcionItem;
	}
	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}
	public String getTipoItem() {
		return tipoItem;
	}
	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCantEntregada() {
		return cantEntregada;
	}
	public void setCantEntregada(int cantEntregada) {
		this.cantEntregada = cantEntregada;
	}
	public int getCantRecibida() {
		return cantRecibida;
	}
	public void setCantRecibida(int cantRecibida) {
		this.cantRecibida = cantRecibida;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getCausaFaltante() {
		return causaFaltante;
	}
	public void setCausaFaltante(String causaFaltante) {
		this.causaFaltante = causaFaltante;
	}
	public short getIdTipoItem() {
		return idTipoItem;
	}
	public void setIdTipoItem(short idTipoItem) {
		this.idTipoItem = idTipoItem;
	}
}
