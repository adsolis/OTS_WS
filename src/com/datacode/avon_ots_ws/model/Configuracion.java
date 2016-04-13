/**
 * @author jorge.torner
 * @since 03/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * Clase de propiedades de configuración para devolver el web service
 * @author jorge.torner
 * @since 03/01/2012
 */
public class Configuracion {
	private int idCampania;
	private String anioCampania;
	private String numCampania;
	private int idLDC;
	private String descripcionLDC;
	private String razonSocialLDC;
	private short idPais;
	private int idUsuario;
	private String usuario;
	private String claveLDC;

	public int getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}
	public String getAnioCampania() {
		return anioCampania;
	}
	public void setAnioCampania(String anioCampania) {
		this.anioCampania = anioCampania;
	}
	public String getNumCampania() {
		return numCampania;
	}
	public void setNumCampania(String numCampania) {
		this.numCampania = numCampania;
	}
	public int getIdLDC() {
		return idLDC;
	}
	public void setIdLDC(int idLDC) {
		this.idLDC = idLDC;
	}
	public String getDescripcionLDC() {
		return descripcionLDC;
	}
	public void setDescripcionLDC(String descripcionLDC) {
		this.descripcionLDC = descripcionLDC;
	}
	public String getRazonSocialLDC() {
		return razonSocialLDC;
	}
	public void setRazonSocialLDC(String razonSocialLDC) {
		this.razonSocialLDC = razonSocialLDC;
	}
	public short getIdPais() {
		return idPais;
	}
	public void setIdPais(short idPais) {
		this.idPais = idPais;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClaveLDC() {
		return claveLDC;
	}
	public void setClaveLDC(String claveLDC) {
		this.claveLDC = claveLDC;
	}
	
}
