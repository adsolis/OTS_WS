/**
 * Mapeo de las propiedades del Objeto de la BD - PW_INFORMANTE
 * @author brenda.estrada
 * @since 10/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 10/01/2012
 * @category Map
 */
public class Informante {
	//Atributos de Mapeo del Objeto
	private Integer idInformante = 0;
	private String descripcion = "";
	//Dependencias
	private Integer idTipoInformante = 0;
	private String descTipoInformante = "";
	private Integer idLDC = 0;
	private String descLDC = "";
	private Integer idPais = 0;
	private String descPais = "";
	
	/**
	 * @return the idInformante
	 */
	public Integer getIdInformante() {
		return idInformante;
	}
	/**
	 * @param idInformante the idInformante to set
	 */
	public void setIdInformante(Integer idInformante) {
		this.idInformante = idInformante;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the idTipoInformante
	 */
	public Integer getIdTipoInformante() {
		return idTipoInformante;
	}
	/**
	 * @param idTipoInformante the idTipoInformante to set
	 */
	public void setIdTipoInformante(Integer idTipoInformante) {
		this.idTipoInformante = idTipoInformante;
	}
	/**
	 * @return the descTipoInformante
	 */
	public String getDescTipoInformante() {
		return descTipoInformante;
	}
	/**
	 * @param descTipoInformante the descTipoInformante to set
	 */
	public void setDescTipoInformante(String descTipoInformante) {
		this.descTipoInformante = descTipoInformante;
	}
	/**
	 * @return the idLDC
	 */
	public Integer getIdLDC() {
		return idLDC;
	}
	/**
	 * @param idLDC the idLDC to set
	 */
	public void setIdLDC(Integer idLDC) {
		this.idLDC = idLDC;
	}
	/**
	 * @return the descLDC
	 */
	public String getDescLDC() {
		return descLDC;
	}
	/**
	 * @param descLDC the descLDC to set
	 */
	public void setDescLDC(String descLDC) {
		this.descLDC = descLDC;
	}
	/**
	 * @return the idPais
	 */
	public Integer getIdPais() {
		return idPais;
	}
	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
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

}
