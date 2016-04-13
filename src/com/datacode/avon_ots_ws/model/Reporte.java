/**
 * Mapeo de las propiedades del Objeto de la BD - PW_REPORTE
 * @author brenda.estrada
 * @since 11/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 11/01/2012
 * @category Map
 */
public class Reporte {
	//Atributos de Mapeo del Objeto
	private Integer idReporte = 0;
	private String nombre = "", descripcion = "", comentario = "", rutaTemplate= "", nombreTemplate = "";
	//Dependencias
	private Integer idPais = 0;
	private String descPais = "";
	private Integer idLDC = 0;
	private String descLDC = "";
	
	/**
	 * @return the idReporte
	 */
	public Integer getIdReporte() {
		return idReporte;
	}
	/**
	 * @param idReporte the idReporte to set
	 */
	public void setIdReporte(Integer idReporte) {
		this.idReporte = idReporte;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * @return the rutaTemplate
	 */
	public String getRutaTemplate() {
		return rutaTemplate;
	}
	/**
	 * @param rutaTemplate the rutaTemplate to set
	 */
	public void setRutaTemplate(String rutaTemplate) {
		this.rutaTemplate = rutaTemplate;
	}
	/**
	 * @return the nombreTemplate
	 */
	public String getNombreTemplate() {
		return nombreTemplate;
	}
	/**
	 * @param nombreTemplate the nombreTemplate to set
	 */
	public void setNombreTemplate(String nombreTemplate) {
		this.nombreTemplate = nombreTemplate;
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
	
	

}
