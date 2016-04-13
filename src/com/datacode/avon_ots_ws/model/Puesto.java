/**
 * Mapeo de las propiedades del Objeto de la BD - PW_PUESTO
 * @author brenda.estrada
 * @since 10/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 10/01/2012
 * @category Map
 */
public class Puesto {
	//Atributos de Mapeo del Objeto
	private Integer idPuesto = 0;
	private String clave = "";
	private String descripcion = "";
	//Dependencias
	private Integer idLDC = 0;
	private String descLDC = "";
	
	private Integer idPais = 0;
	private String descPais = "";
	
	/**
	 * @return the idPuesto
	 */
	public Integer getIdPuesto() {
		return idPuesto;
	}
	/**
	 * @param idPuesto the idPuesto to set
	 */
	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
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
