/**
 * @author jorge.torner
 * @since 11/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * Clase que define la entidad PW_RECEPCION_PREMIOS_REACO_DETALLE
 * @author jorge.torner
 * @since 11/01/2012
 */
public class RecepcionAjustesReacoPremio {

	private long idRecepcionAjustesReacoPremio;
	private int cantidad;
	private String descripcion;
	private String programa;
	private int idCampania;
	private String claveCampania;
	
	public long getIdRecepcionAjustesReacoPremio() {
		return idRecepcionAjustesReacoPremio;
	}
	public void setIdRecepcionAjustesReacoPremio(
			long idRecepcionAjustesReacoPremio) {
		this.idRecepcionAjustesReacoPremio = idRecepcionAjustesReacoPremio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public int getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}
	public String getClaveCampania() {
		return claveCampania;
	}
	public void setClaveCampania(String claveCampania) {
		this.claveCampania = claveCampania;
	}
}
