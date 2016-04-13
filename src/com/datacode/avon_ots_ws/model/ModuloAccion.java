/**
 * @author brenda.estrada
 * @since 31/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 31/01/2012
 * @category Map
 */
public class ModuloAccion {
	//Atributos del Obj
	private Integer idModuloAccion = 0;
	private String descModuloAccion ="";
	private Integer idModulo = 0;
	private String descModulo = "";
	
	private Integer idAccion = 0;
	private String descAccion = "";
	
	private String fechaActualiza = "", usuarioActualiza = "";
	
	private String tienePermiso = "";
	private Boolean permiso = true;
	
	private Integer idPerfil = 0;
	

	/**
	 * @return the idModuloAccion
	 */
	public Integer getIdModuloAccion() {
		return idModuloAccion;
	}

	/**
	 * @param idModuloAccion the idModuloAccion to set
	 */
	public void setIdModuloAccion(Integer idModuloAccion) {
		this.idModuloAccion = idModuloAccion;
	}

	/**
	 * @return the idModulo
	 */
	public Integer getIdModulo() {
		return idModulo;
	}

	/**
	 * @param idModulo the idModulo to set
	 */
	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	/**
	 * @return the descModulo
	 */
	public String getDescModulo() {
		return descModulo;
	}

	/**
	 * @param descModulo the descModulo to set
	 */
	public void setDescModulo(String descModulo) {
		this.descModulo = descModulo;
	}

	/**
	 * @return the idAccion
	 */
	public Integer getIdAccion() {
		return idAccion;
	}

	/**
	 * @param idAccion the idAccion to set
	 */
	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	/**
	 * @return the descAccion
	 */
	public String getDescAccion() {
		return descAccion;
	}

	/**
	 * @param descAccion the descAccion to set
	 */
	public void setDescAccion(String descAccion) {
		this.descAccion = descAccion;
	}

	/**
	 * @return the fechaActualiza
	 */
	public String getFechaActualiza() {
		return fechaActualiza;
	}

	/**
	 * @param fechaActualiza the fechaActualiza to set
	 */
	public void setFechaActualiza(String fechaActualiza) {
		this.fechaActualiza = fechaActualiza;
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

	/**
	 * @return the tienePermiso
	 */
	public String getTienePermiso() {
		return tienePermiso;
	}

	/**
	 * @param tienePermiso the tienePermiso to set
	 */
	public void setTienePermiso(String tienePermiso) {
		this.tienePermiso = tienePermiso;
	}

	/**
	 * @return the permiso
	 */
	public Boolean getPermiso() {
		return permiso;
	}

	/**
	 * @param permiso the permiso to set
	 */
	public void setPermiso(Boolean permiso) {
		this.permiso = permiso;
	}

	/**
	 * @return the idPerfil
	 */
	public Integer getIdPerfil() {
		return idPerfil;
	}

	/**
	 * @param idPerfil the idPerfil to set
	 */
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	/**
	 * @return the descModuloAccion
	 */
	public String getDescModuloAccion() {
		return descModuloAccion;
	}

	/**
	 * @param descModuloAccion the descModuloAccion to set
	 */
	public void setDescModuloAccion(String descModuloAccion) {
		this.descModuloAccion = descModuloAccion;
	}

}
