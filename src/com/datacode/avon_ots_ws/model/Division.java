/**
 * Mapeo de las propiedades del Objeto de la BD - PW_DIVISION
 * @author brenda.estrada
 * @since 18/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author   brenda.estrada
 * @since 	 18/01/2012
 * @category Map
 */
public class Division {
	//Atributos del Objeto
	private Integer idDivision = 0;
	private String  nombre = "",	areaGeografica = "", 
					admor = "", asistente = "", lastupd_ts= "",
					fechaActualizado ="", usuarioActualiza="";

	/**
	 * @return the idDivision
	 */
	public Integer getIdDivision() {
		return idDivision;
	}

	/**
	 * @param idDivision the idDivision to set
	 */
	public void setIdDivision(Integer idDivision) {
		this.idDivision = idDivision;
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
	 * @return the areaGeografica
	 */
	public String getAreaGeografica() {
		return areaGeografica;
	}

	/**
	 * @param areaGeografica the areaGeografica to set
	 */
	public void setAreaGeografica(String areaGeografica) {
		this.areaGeografica = areaGeografica;
	}

	/**
	 * @return the admor
	 */
	public String getAdmor() {
		return admor;
	}

	/**
	 * @param admor the admor to set
	 */
	public void setAdmor(String admor) {
		this.admor = admor;
	}

	/**
	 * @return the asistente
	 */
	public String getAsistente() {
		return asistente;
	}

	/**
	 * @param asistente the asistente to set
	 */
	public void setAsistente(String asistente) {
		this.asistente = asistente;
	}

	/**
	 * @return the lastupd_ts
	 */
	public String getLastupd_ts() {
		return lastupd_ts;
	}

	/**
	 * @param lastupd_ts the lastupd_ts to set
	 */
	public void setLastupd_ts(String lastupd_ts) {
		this.lastupd_ts = lastupd_ts;
	}

	/**
	 * @return the fechaActualizado
	 */
	public String getFechaActualizado() {
		return fechaActualizado;
	}

	/**
	 * @param fechaActualizado the fechaActualizado to set
	 */
	public void setFechaActualizado(String fechaActualizado) {
		this.fechaActualizado = fechaActualizado;
	}

	/**
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}

	/**
	 * @param usuarioActualiza the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
	
}
