/**
 * Mapeo de las propiedades del Objeto de la BD - PW_SUB_BODEGA_ALMACEN
 * @author brenda.estrada
 * @since 10/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 10/01/2012
 * @category Map
 */
public class SubBodegaAlmacen {
	//Atributos de Mapeo del Objeto
	private Integer idSubbodegaAlmacen = 0;
	private String clave = "";
	private String descripcion = "";
	private String telefono = "";
	private String domicilio = "";
	//Dependencias
	private Integer idPais = 0;
	private String descPais = "";
	private Integer idLDC = 0;
	private String descLDC = "";
	private Integer idZona = 0;
	private String descZona = "";
	private Integer idUserResponsableSubbodega = 0;
	private String descUserResponsableSubbodega = "";
	private String correoResponsable="";
	
	public String getCorreoResponsable() {
		return correoResponsable;
	}
	public void setCorreoResponsable(String correoResponsable) {
		this.correoResponsable = correoResponsable;
	}
	/**
	 * @return the idSubbodegaAlmacen
	 */
	public Integer getIdSubbodegaAlmacen() {
		return idSubbodegaAlmacen;
	}
	/**
	 * @param idSubbodegaAlmacen the idSubbodegaAlmacen to set
	 */
	public void setIdSubbodegaAlmacen(Integer idSubbodegaAlmacen) {
		this.idSubbodegaAlmacen = idSubbodegaAlmacen;
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
	 * @return the idZona
	 */
	public Integer getIdZona() {
		return idZona;
	}
	/**
	 * @param idZona the idZona to set
	 */
	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}
	/**
	 * @return the descZona
	 */
	public String getDescZona() {
		return descZona;
	}
	/**
	 * @param descZona the descZona to set
	 */
	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}
	/**
	 * @return the idUserResponsableSubbodega
	 */
	public Integer getIdUserResponsableSubbodega() {
		return idUserResponsableSubbodega;
	}
	/**
	 * @param idUserResponsableSubbodega the idUserResponsableSubbodega to set
	 */
	public void setIdUserResponsableSubbodega(Integer idUserResponsableSubbodega) {
		this.idUserResponsableSubbodega = idUserResponsableSubbodega;
	}
	/**
	 * @return the descUserResponsableSubbodega
	 */
	public String getDescUserResponsableSubbodega() {
		return descUserResponsableSubbodega;
	}
	/**
	 * @param descUserResponsableSubbodega the descUserResponsableSubbodega to set
	 */
	public void setDescUserResponsableSubbodega(String descUserResponsableSubbodega) {
		this.descUserResponsableSubbodega = descUserResponsableSubbodega;
	}
}
