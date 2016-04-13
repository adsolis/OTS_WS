/**
 * @author brenda.estrada
 * @since 13/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 13/01/2012
 */
public class TipoUsuario {
	private Integer idTipoUsuario = 0;
	private String descTipoUsuario = "", cveTipoUsuario = ""; 
	/**
	 * @return the idTipoUsuario
	 */
	public Integer getIdTipoUsuario() {
		return idTipoUsuario;
	}
	/**
	 * @param idTipoUsuario the idTipoUsuario to set
	 */
	public void setIdTipoUsuario(Integer idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	/**
	 * @return the descTipoUsuario
	 */
	public String getDescTipoUsuario() {
		return descTipoUsuario;
	}
	/**
	 * @param descTipoUsuario the descTipoUsuario to set
	 */
	public void setDescTipoUsuario(String descTipoUsuario) {
		this.descTipoUsuario = descTipoUsuario;
	}
	/**
	 * @return the cveTipoUsuario
	 */
	public String getCveTipoUsuario() {
		return cveTipoUsuario;
	}
	/**
	 * @param cveTipoUsuario the cveTipoUsuario to set
	 */
	public void setCveTipoUsuario(String cveTipoUsuario) {
		this.cveTipoUsuario = cveTipoUsuario;
	}
	
}
