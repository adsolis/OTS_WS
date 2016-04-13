/**
 * Clase de Mapeo de los posibles Modulos que tienen en 
 * el menu un usuario en especifico
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author javier.gallegos
 * @since  15-01-2012
 */
public class ModulosMenu {

	private int idModulo;
	private String nombreModulo;
	private int idPadre;
	private String archivo;
	private int orden;
	public int getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}
	public String getNombreModulo() {
		return nombreModulo;
	}
	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}
	public int getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	
}
