/**
 * @author jose.ponce
 * @since 17/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jose.ponce
 *
 */
public class Bancos {
	
	private Integer IdBanco=0;
	private String Clave="0";
	private String Nombre="";
	private String lastupd_ts = "";
	private String fechaActualizado = "", usuarioActualiza = "";
	
	
	public Integer getIdBanco() {
		return IdBanco;
	}
	public void setIdBanco(Integer idBanco) {
		IdBanco = idBanco;
	}
	public String getClave() {
		return Clave;
	}
	public void setClave(String clave) {
		Clave = clave;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
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
