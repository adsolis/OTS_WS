/**
 * Mapeo de las propiedades del Objeto de la BD - PW_LINEA_TRANSPORTE
 * @author brenda.estrada
 * @since 10/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 10/01/2012
 */
public class LineaTransporte {
	//Atributos de Mapeo del Objeto
	private Integer idLineaTransporte = 0;
	private String clave = "";
	private String descripcion = "";
	private String telefono = "";
	private String domicilio = "";
	//Dependencias
	private Integer idPais = 0;
	private String descPais = "";
	

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
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}
	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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
	/**
	 * @return the idLineaTransporte
	 */
	public Integer getIdLineaTransporte() {
		return idLineaTransporte;
	}
	/**
	 * @param idLineaTransporte the idLineaTransporte to set
	 */
	public void setIdLineaTransporte(Integer idLineaTransporte) {
		this.idLineaTransporte = idLineaTransporte;
	}
}
