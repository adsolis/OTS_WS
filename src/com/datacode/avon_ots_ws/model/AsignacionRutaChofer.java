/**
 * Mapeo de las propiedades del Objeto de la BD - PW_ASIGNACION_RUTA_CHOFER
 * @author brenda.estrada
 * @since 11/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 11/01/2012
 * @category Map
 */
public class AsignacionRutaChofer {
	
	//Atributos de Mapeo del Objeto
	private Integer idAsignacionRutaChofer = 0;
	private String fAsignada = "";
	private String fDenegada ="";
	private Integer idTipoAsignacion = 0;
	private String tipoAsignacion ="";
	//Dependencias
	private Integer idRuta = 0;
	private String descRuta = "";
	private Integer idPais = 0;
	private String descPais = "";
	private Integer idZona = 0;
	private String descZona = "";
	private Integer idLDC = 0;
	private String descLDC = "";
	private Integer idEmpleado = 0;
	private String descEmpleado = "";
		

	/**
	 * @return the idAsignacionRutaChofer
	 */
	public Integer getIdAsignacionRutaChofer() {
		return idAsignacionRutaChofer;
	}


	/**
	 * @param idAsignacionRutaChofer the idAsignacionRutaChofer to set
	 */
	public void setIdAsignacionRutaChofer(Integer idAsignacionRutaChofer) {
		this.idAsignacionRutaChofer = idAsignacionRutaChofer;
	}


	/**
	 * @return the fAsignada
	 */
	public String getfAsignada() {
		return fAsignada;
	}


	/**
	 * @param fAsignada the fAsignada to set
	 */
	public void setfAsignada(String fAsignada) {
		this.fAsignada = fAsignada;
	}


	/**
	 * @return the fDenegada
	 */
	public String getfDenegada() {
		return fDenegada;
	}


	/**
	 * @param fDenegada the fDenegada to set
	 */
	public void setfDenegada(String fDenegada) {
		this.fDenegada = fDenegada;
	}


	/**
	 * @return the tipoAsignacion
	 */
	public String getTipoAsignacion() {
		return tipoAsignacion;
	}


	/**
	 * @param tipoAsignacion the tipoAsignacion to set
	 */
	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}


	/**
	 * @return the idRuta
	 */
	public Integer getIdRuta() {
		return idRuta;
	}


	/**
	 * @param idRuta the idRuta to set
	 */
	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}


	/**
	 * @return the descRuta
	 */
	public String getDescRuta() {
		return descRuta;
	}


	/**
	 * @param descRuta the descRuta to set
	 */
	public void setDescRuta(String descRuta) {
		this.descRuta = descRuta;
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
	 * @return the idEmpleado
	 */
	public Integer getIdEmpleado() {
		return idEmpleado;
	}


	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	/**
	 * @return the descEmpleado
	 */
	public String getDescEmpleado() {
		return descEmpleado;
	}


	/**
	 * @param descEmpleado the descEmpleado to set
	 */
	public void setDescEmpleado(String descEmpleado) {
		this.descEmpleado = descEmpleado;
	}


	/**
	 * @return the idTipoAsignacion
	 */
	public Integer getIdTipoAsignacion() {
		return idTipoAsignacion;
	}


	/**
	 * @param idTipoAsignacion the idTipoAsignacion to set
	 */
	public void setIdTipoAsignacion(Integer idTipoAsignacion) {
		this.idTipoAsignacion = idTipoAsignacion;
	}

}
