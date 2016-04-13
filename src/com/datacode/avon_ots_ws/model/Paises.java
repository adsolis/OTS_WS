/**
 * Clase de Mapeo con el objeto en la Base de datos - PW_PAISES
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since  26-12-2011
 */
public class Paises {

	//Atributos del Objeto
	private String idPais = "";
	private String cvePais = "";
	private String  descPais = "";
	private String fechaUpd = "";
	private String userUpd = "";
	
	
	
	/**
	 * @return the descPais
	 */
	public String getDescPais() {
		return descPais;
	}
	/**
	 * @param descPais the descPais to set
	 */
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}
	/**
	 * @return the cvePais
	 */
	public String getCvePais() {
		return cvePais;
	}
	/**
	 * @param cvePais the cvePais to set
	 */
	public void setCvePais(String cvePais) {
		this.cvePais = cvePais;
	}
	/**
	 * @return the fechaUpd
	 */
	public String getFechaUpd() {
		return fechaUpd;
	}
	/**
	 * @param fechaUpd the fechaUpd to set
	 */
	public void setFechaUpd(String fechaUpd) {
		this.fechaUpd = fechaUpd;
	}
	/**
	 * @return the userUpd
	 */
	public String getUserUpd() {
		return userUpd;
	}
	/**
	 * @param userUpd the userUpd to set
	 */
	public void setUserUpd(String userUpd) {
		this.userUpd = userUpd;
	}
	/**
	 * @return the idPais
	 */
	public String getIdPais() {
		return idPais;
	}
	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}
}
