/**
 * @author jose.ponce
 * @since 04/04/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jose.ponce
 *
 */
public class PersonaRecibe {
	//Atributos
	private int idPersonaRecibe=0;
	private String descripcion="";
	private String fechaActualiza="";
	private String usuarioActualiza="";
	
	
	public int getIdPersonaRecibe() {
		return idPersonaRecibe;
	}
	public void setIdPersonaRecibe(int idPersonaRecibe) {
		this.idPersonaRecibe = idPersonaRecibe;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechaActualiza() {
		return fechaActualiza;
	}
	public void setFechaActualiza(String fechaActualiza) {
		this.fechaActualiza = fechaActualiza;
	}
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
}
