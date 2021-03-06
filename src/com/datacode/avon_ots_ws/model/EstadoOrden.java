/**
 * Mapeo de las propiedades del Objeto de la BD - // PW_PFDELIVER //
 * @author brenda.estrada
 * @since 18/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 18/01/2012
 * @category Map
 */
public class EstadoOrden {
	//Atributos del Obj
	private Integer idEdoOrden = 0;
	private String  tipoDlv = "", 	descripcion = "", lastupd_ts = "",
					fechaActualizado = "", usuarioActualiza = "";

	/**
	 * @return the idEdoOrden
	 */
	public Integer getIdEdoOrden() {
		return idEdoOrden;
	}

	/**
	 * @param idEdoOrden the idEdoOrden to set
	 */
	public void setIdEdoOrden(Integer idEdoOrden) {
		this.idEdoOrden = idEdoOrden;
	}

	/**
	 * @return the tipoDlv
	 */
	public String getTipoDlv() {
		return tipoDlv;
	}

	/**
	 * @param tipoDlv the tipoDlv to set
	 */
	public void setTipoDlv(String tipoDlv) {
		this.tipoDlv = tipoDlv;
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
