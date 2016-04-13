/**
 * @author jorge.torner
 * @since 11/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * Clase relacionada con la entidad PW_RECEPCION_AJUSTES_REACO_DETALLE
 * @author jorge.torner
 * @since 11/01/2012
 */
public class RecepcionAjustesReacoDetalle {
	private long idRecepcionAjustesReacoDetalle;
	private short idSubcategoriaProducto;
	private String descripcionSubcategoriaProducto;
	private int cantidadBuenEstado;
	private int cantidadMalEstado;
	
	public long getIdRecepcionAjustesReacoDetalle() {
		return idRecepcionAjustesReacoDetalle;
	}
	public void setIdRecepcionAjustesReacoDetalle(
			long idRecepcionAjustesReacoDetalle) {
		this.idRecepcionAjustesReacoDetalle = idRecepcionAjustesReacoDetalle;
	}
	public short getIdSubcategoriaProducto() {
		return idSubcategoriaProducto;
	}
	public void setIdSubcategoriaProducto(short idSubcategoriaProducto) {
		this.idSubcategoriaProducto = idSubcategoriaProducto;
	}
	public String getDescripcionSubcategoriaProducto() {
		return descripcionSubcategoriaProducto;
	}
	public void setDescripcionSubcategoriaProducto(
			String descripcionSubcategoriaProducto) {
		this.descripcionSubcategoriaProducto = descripcionSubcategoriaProducto;
	}
	public int getCantidadBuenEstado() {
		return cantidadBuenEstado;
	}
	public void setCantidadBuenEstado(int cantidadBuenEstado) {
		this.cantidadBuenEstado = cantidadBuenEstado;
	}
	public int getCantidadMalEstado() {
		return cantidadMalEstado;
	}
	public void setCantidadMalEstado(int cantidadMalEstado) {
		this.cantidadMalEstado = cantidadMalEstado;
	}
}
