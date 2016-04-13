/**
 * Mapeo de las propiedades del Objeto de la BD - PW_DENOMINACIONES
 * @author brenda.estrada
 * @since 11/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 11/01/2012
 * @category Map
 */
public class Denominaciones {
	//Atributos de Mapeo del Objeto
	private Integer idDenominacion = 0 , idTipo = 0;
	private String denominacion = "", tipo = "";
	
	
	/**
	 * @return the idDenominacion
	 */
	public Integer getIdDenominacion() {
		return idDenominacion;
	}
	/**
	 * @param idDenominacion the idDenominacion to set
	 */
	public void setIdDenominacion(Integer idDenominacion) {
		this.idDenominacion = idDenominacion;
	}
	/**
	 * @return the denominacion
	 */
	public String getDenominacion() {
		return denominacion;
	}
	/**
	 * @param denominacion the denominacion to set
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the idTipo
	 */
	public Integer getIdTipo() {
		return idTipo;
	}
	/**
	 * @param idTipo the idTipo to set
	 */
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

}
