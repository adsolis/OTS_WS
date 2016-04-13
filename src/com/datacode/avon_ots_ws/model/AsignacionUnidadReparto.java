/**
 * Mapeo de las propiedades del Objeto de la BD - PW_ASIGNACION_UNIDAD_REPARTO
 * @author brenda.estrada
 * @since 10/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 *  Mapeo de las propiedades del Objeto de la BD - PW_ASIGNACION_UNIDAD_REPARTO
 * @author brenda.estrada
 * @since 10/01/2012
 * @category Map
 */
public class AsignacionUnidadReparto {
		//Atributos de Mapeo del Objeto
		private Integer idAsignacionUnidadReparto = 0;
		private String noSerie = "";
		private String fAsignada = "";
		private String fDenegada ="";
		//Dependencias
		private Integer idEmpleado = 0;
		private String descEmpleado = "";
		
		private Integer idUnidadReparto = 0;
		private String descUnidadReparto = "";
		
		private Integer idLDC = 0;
		private String descLDC = "";
		
		private Integer idPais = 0;
		private String descPais = "";
		private Integer idEstatus = 0;
		private String descEstatus = "";
	
	/**
	 * @return the idAsignacionUnidadReparto
	 */
	public Integer getIdAsignacionUnidadReparto() {
		return idAsignacionUnidadReparto;
	}
	/**
	 * @param idAsignacionUnidadReparto the idAsignacionUnidadReparto to set
	 */
	public void setIdAsignacionUnidadReparto(Integer idAsignacionUnidadReparto) {
		this.idAsignacionUnidadReparto = idAsignacionUnidadReparto;
	}
	/**
	 * @return the noSerie
	 */
	public String getNoSerie() {
		return noSerie;
	}
	/**
	 * @param noSerie the noSerie to set
	 */
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
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
	 * @return the idUnidadReparto
	 */
	public Integer getIdUnidadReparto() {
		return idUnidadReparto;
	}
	/**
	 * @param idUnidadReparto the idUnidadReparto to set
	 */
	public void setIdUnidadReparto(Integer idUnidadReparto) {
		this.idUnidadReparto = idUnidadReparto;
	}
	/**
	 * @return the descUnidadReparto
	 */
	public String getDescUnidadReparto() {
		return descUnidadReparto;
	}
	/**
	 * @param descUnidadReparto the descUnidadReparto to set
	 */
	public void setDescUnidadReparto(String descUnidadReparto) {
		this.descUnidadReparto = descUnidadReparto;
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


	/**
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}


	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}


	/**
	 * @return the descEstatus
	 */
	public String getDescEstatus() {
		return descEstatus;
	}


	/**
	 * @param descEstatus the descEstatus to set
	 */
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

}
