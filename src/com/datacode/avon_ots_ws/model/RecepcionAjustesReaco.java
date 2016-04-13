/**
 * @author jorge.torner
 * @since 11/01/2012
 */
package com.datacode.avon_ots_ws.model;

import java.util.Date;

/**
 * Clase relacionada con la entidad PW_RECEPCION_AJUSTES_REACO
 * @author jorge.torner
 * @since 11/01/2012
 */
public class RecepcionAjustesReaco {
	private long idRecepcionAjustesReaco;
	private int idZona;
	private int idCampania;
	private String zona;
	private String claveCampania;
	private short totalCajasBelleza;
	private short totalCajasCasaModa;
	private short totalCajasPremios;
	private Date fechaRecepcion;
	private String gerenteZonal;
	private String quienRecibio;
	private RecepcionAjustesReacoDetalle[] detalles;
	private RecepcionAjustesReacoPremio[] premios;
	private String prefijoCodigoBarras;
	
	public long getIdRecepcionAjustesReaco() {
		return idRecepcionAjustesReaco;
	}
	public void setIdRecepcionAjustesReaco(long idRecepcionAjustesReaco) {
		this.idRecepcionAjustesReaco = idRecepcionAjustesReaco;
	}
	public int getIdZona() {
		return idZona;
	}
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}
	public int getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getClaveCampania() {
		return claveCampania;
	}
	public void setClaveCampania(String claveCampania) {
		this.claveCampania = claveCampania;
	}
	public short getTotalCajasBelleza() {
		return totalCajasBelleza;
	}
	public void setTotalCajasBelleza(short totalCajasBelleza) {
		this.totalCajasBelleza = totalCajasBelleza;
	}
	public short getTotalCajasCasaModa() {
		return totalCajasCasaModa;
	}
	public void setTotalCajasCasaModa(short totalCajasCasaModa) {
		this.totalCajasCasaModa = totalCajasCasaModa;
	}
	public short getTotalCajasPremios() {
		return totalCajasPremios;
	}
	public void setTotalCajasPremios(short totalCajasPremios) {
		this.totalCajasPremios = totalCajasPremios;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public String getGerenteZonal() {
		return gerenteZonal;
	}
	public void setGerenteZonal(String gerenteZonal) {
		this.gerenteZonal = gerenteZonal;
	}
	public String getQuienRecibio() {
		return quienRecibio;
	}
	public void setQuienRecibio(String quienRecibio) {
		this.quienRecibio = quienRecibio;
	}
	public RecepcionAjustesReacoDetalle[] getDetalles() {
		return detalles;
	}
	public void setDetalles(RecepcionAjustesReacoDetalle[] detalles) {
		this.detalles = detalles;
	}
	public RecepcionAjustesReacoPremio[] getPremios() {
		return premios;
	}
	public void setPremios(RecepcionAjustesReacoPremio[] premios) {
		this.premios = premios;
	}
	public String getPrefijoCodigoBarras() {
		return prefijoCodigoBarras;
	}
	public void setPrefijoCodigoBarras(String prefijoCodigoBarras) {
		this.prefijoCodigoBarras = prefijoCodigoBarras;
	}
}
