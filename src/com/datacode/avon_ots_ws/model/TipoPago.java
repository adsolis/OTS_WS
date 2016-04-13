/**
 * @author jose.ponce
 * @since 16/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jose.ponce
 * @author brenda.estrada
 * @since  18-01-2012
 */
public class TipoPago {
	
	private Integer IdTipoPuesto=0;
	private String Descripcion="", fechaActualizado = "", usuarioActualiza = "";
	
	
	public Integer getIdTipoPuesto() {
		return IdTipoPuesto;
	}
	public void setIdTipoPuesto(Integer idTipoPuesto) {
		IdTipoPuesto = idTipoPuesto;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
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
