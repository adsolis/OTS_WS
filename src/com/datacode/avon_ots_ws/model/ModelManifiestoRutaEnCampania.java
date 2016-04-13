/**
 * @author jorge.torner
 * @since 30/01/2012
 */
package com.datacode.avon_ots_ws.model;

import java.util.List;

/**
 * @author jorge.torner
 * @since 30/01/2012
 */
public class ModelManifiestoRutaEnCampania {
	//Encabezado
	private String zona;
	private String ruta;
	private String poblacionPrincipal;
	private String fecha;
	private String campania_Anio;
	private String nombreChofer;
	private String nombreAyudante;
	
	//Detalle
	private List<ModelTablaManifiestoRutaEnCampania> listaManifiesto;
	
	//Pie
	private String ordenesTotales;
	private String cajasTotales;
	private String unitariosTotales;
	private String premiosTotales;
	
	/**
	 * @return the campania_Anio
	 */
	public String getCampania_Anio() {
		return campania_Anio;
	}
	/**
	 * @param campania_Anio the campania_Anio to set
	 */
	public void setCampania_Anio(String campania_Anio) {
		this.campania_Anio = campania_Anio;
	}
	/**
	 * @return the nombreChofer
	 */
	public String getNombreChofer() {
		return nombreChofer;
	}
	/**
	 * @param nombreChofer the nombreChofer to set
	 */
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}
	/**
	 * @return the nombreAyudante
	 */
	public String getNombreAyudante() {
		return nombreAyudante;
	}
	/**
	 * @param nombreAyudante the nombreAyudante to set
	 */
	public void setNombreAyudante(String nombreAyudante) {
		this.nombreAyudante = nombreAyudante;
	}
	/**
	 * @return the ordenesTotales
	 */
	public String getOrdenesTotales() {
		return ordenesTotales;
	}
	/**
	 * @param ordenesTotales the ordenesTotales to set
	 */
	public void setOrdenesTotales(String ordenesTotales) {
		this.ordenesTotales = ordenesTotales;
	}
	/**
	 * @return the cajasTotales
	 */
	public String getCajasTotales() {
		return cajasTotales;
	}
	/**
	 * @param cajasTotales the cajasTotales to set
	 */
	public void setCajasTotales(String cajasTotales) {
		this.cajasTotales = cajasTotales;
	}
	/**
	 * @return the unitariosTotales
	 */
	public String getUnitariosTotales() {
		return unitariosTotales;
	}
	/**
	 * @param unitariosTotales the unitariosTotales to set
	 */
	public void setUnitariosTotales(String unitariosTotales) {
		this.unitariosTotales = unitariosTotales;
	}
	/**
	 * @return the premiosTotales
	 */
	public String getPremiosTotales() {
		return premiosTotales;
	}
	/**
	 * @param premiosTotales the premiosTotales to set
	 */
	public void setPremiosTotales(String premiosTotales) {
		this.premiosTotales = premiosTotales;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	/**
	 * @return the poblacionPrincipal
	 */
	public String getPoblacionPrincipal() {
		return poblacionPrincipal;
	}
	/**
	 * @param poblacionPrincipal the poblacionPrincipal to set
	 */
	public void setPoblacionPrincipal(String poblacionPrincipal) {
		this.poblacionPrincipal = poblacionPrincipal;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the listaManifiesto
	 */
	public List<ModelTablaManifiestoRutaEnCampania> getListaManifiesto() {
		return listaManifiesto;
	}
	/**
	 * @param listaManifiesto the listaManifiesto to set
	 */
	public void setListaManifiesto(
			List<ModelTablaManifiestoRutaEnCampania> listaManifiesto) {
		this.listaManifiesto = listaManifiesto;
	}
}
