/**
 * @author jorge.torner
 * @since 03/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * Clase que define la estructura de Usuario
 * @author jorge.torner
 * @since 03/01/2012
 */
public class Usuario {
	private int idUsuario;
	private String usuario;
	private int idTipoUsuario;
	//Mapeo del Objeto PW_USUARIO
	private Integer idPais = 0;
	private String descPais = "";
	private Integer idLDC = 0;
	private String descLDC = "";
	private Integer idPerfil = 0;
	private String descPerfil = "",	descTipoUsuario ="";
	private Integer idEmpleado = 0;
	private String descEmpleado = "";
	private String password = "", comentario = "";
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
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
	 * @return the descPerfil
	 */
	public String getDescPerfil() {
		return descPerfil;
	}
	/**
	 * @param descPerfil the descPerfil to set
	 */
	public void setDescPerfil(String descPerfil) {
		this.descPerfil = descPerfil;
	}
	/**
	 * @return the descTipoUsuario
	 */
	public String getDescTipoUsuario() {
		return descTipoUsuario;
	}
	/**
	 * @param descTipoUsuario the descTipoUsuario to set
	 */
	public void setDescTipoUsuario(String descTipoUsuario) {
		this.descTipoUsuario = descTipoUsuario;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
}
