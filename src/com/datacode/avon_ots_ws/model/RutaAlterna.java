/**
 * Mapeo de las propiedades de la tabla PW_RELACION_RUTAS_OTS_SOS
 * @author Moises Hernandez
 * @since 09/05/2014
 * @category Map
 */
package com.datacode.avon_ots_ws.model;


public class RutaAlterna {
	
	private int idRutaAlterna;
	private String rutaOTS;
	private String rutaSOS;
	private int idZona;
	private String zona;
	private int orden;
	
	public RutaAlterna() {
		
	}
	
	public RutaAlterna(int idRutaAlterna, String rutaOTS, 
			String rutaSOS,int idZona, String zona, int orden) {
		
		this.idRutaAlterna = idRutaAlterna;
		this.rutaOTS = rutaOTS;
		this.rutaSOS = rutaSOS;
		this.idZona = idZona;
		this.zona = zona;
		this.orden = orden;
		
	}

	public int getIdRutaAlterna() {
		return idRutaAlterna;
	}

	public void setIdRutaAlterna(int idRutaAlterna) {
		this.idRutaAlterna = idRutaAlterna;
	}

	public String getRutaOTS() {
		return rutaOTS;
	}

	public void setRutaOTS(String rutaOTS) {
		this.rutaOTS = rutaOTS;
	}

	public String getRutaSOS() {
		return rutaSOS;
	}

	public void setRutaSOS(String rutaSOS) {
		this.rutaSOS = rutaSOS;
	}

	public int getIdZona() {
		return idZona;
	}

	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

}
