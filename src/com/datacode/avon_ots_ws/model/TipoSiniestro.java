/**
 * Mapeo de las propiedades del Objeto de la BD - // PW_TIPO_SINIESTRO //
 * @author brenda.estrada
 * @since 18/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 18/01/2012
 * @category Map
 */
public class TipoSiniestro {
	//Atributos del Obj
	private Integer idTipoSiniestro = 0;
	private String  clave = "", 	descripcion = "", lastupd_ts = "",
			fechaActualizado = "", usuarioActualiza = "";
	
	/**
	 * @return the idTipoSiniestro
	 */
	public Integer getIdTipoSiniestro() {
		return idTipoSiniestro;
	}

	/**
	 * @param idTipoSiniestro the idTipoSiniestro to set
	 */
	public void setIdTipoSiniestro(Integer idTipoSiniestro) {
		this.idTipoSiniestro = idTipoSiniestro;
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
