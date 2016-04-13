/**
 * Clase de Mapeo con el objeto en la Base de datos - PW_MODELO
 * @author brenda.estrada
 * @since 09/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 09/01/2012
 * @category Map
 */
public class Modelo {
	//Atributos del Obj
	private Integer idModelo = 0;
	private String descModelo = "";
	private String frecuencia = "";
	//Dependencias
	private Integer idMarca = 0;
	private String descMarca = "";
	
	/**
	 * @return the descModelo
	 */
	public String getDescModelo() {
		return descModelo;
	}
	/**
	 * @param descModelo the descModelo to set
	 */
	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}
	/**
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}
	/**
	 * @param frecuencia the frecuencia to set
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	/**
	 * @return the idMarca
	 */
	public Integer getIdMarca() {
		return idMarca;
	}
	/**
	 * @param idMarca the idMarca to set
	 */
	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}
	/**
	 * @return the descMarca
	 */
	public String getDescMarca() {
		return descMarca;
	}
	/**
	 * @param descMarca the descMarca to set
	 */
	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}


	/**
	 * @return the idModelo
	 */
	public Integer getIdModelo() {
		return idModelo;
	}


	/**
	 * @param idModelo the idModelo to set
	 */
	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}
	
	

}
