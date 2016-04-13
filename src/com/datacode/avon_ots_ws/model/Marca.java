/**
 * Clase de Mapeo con el objeto en la Base de datos - PW_MARCA
 * @author brenda.estrada
 * @since 05/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 05/01/2012
 * @category Map
 */
public class Marca {
	//Atributos del Objeto
	private String idMarca = "";
	private String descMarca = "";
	
	/**
	 * @return the idMarca
	 */
	public String getIdMarca() {
		return idMarca;
	}

	/**
	 * @param idMarca the idMarca to set
	 */
	public void setIdMarca(String idMarca) {
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

}
