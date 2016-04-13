/**
 * Mapeo de las propiedades del Objeto de la BD - PW_DESTINATARIO
 * @author brenda.estrada
 * @since 10/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 10/01/2012
 * @category Map
 */
public class Destinatario {
	//Atributos de Mapeo del Objeto
	private Integer idDestinatario = 0;
	private String clave = "", nombre = "", apPaterno = "",  apMaterno = "", mail = "";
	//Dependencias
	private Integer idPais = 0;
	private String descPais = "";
	private Integer idLDC = 0;
	private String descLDC = "";
	private Integer idTipoDestinatario = 0;
	private String descTipoDestinatario= "";
	
	/**
	 * @return the idDestinatario
	 */
	public Integer getIdDestinatario() {
		return idDestinatario;
	}
	/**
	 * @param idDestinatario the idDestinatario to set
	 */
	public void setIdDestinatario(Integer idDestinatario) {
		this.idDestinatario = idDestinatario;
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
	 * @return the apPaterno
	 */
	public String getApPaterno() {
		return apPaterno;
	}
	/**
	 * @param apPaterno the apPaterno to set
	 */
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	/**
	 * @return the apMaterno
	 */
	public String getApMaterno() {
		return apMaterno;
	}
	/**
	 * @param apMaterno the apMaterno to set
	 */
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	 * @return the idTipoDestinatario
	 */
	public Integer getIdTipoDestinatario() {
		return idTipoDestinatario;
	}
	/**
	 * @param idTipoDestinatario the idTipoDestinatario to set
	 */
	public void setIdTipoDestinatario(Integer idTipoDestinatario) {
		this.idTipoDestinatario = idTipoDestinatario;
	}
	/**
	 * @return the descTipoDestinatario
	 */
	public String getDescTipoDestinatario() {
		return descTipoDestinatario;
	}
	/**
	 * @param descTipoDestinatario the descTipoDestinatario to set
	 */
	public void setDescTipoDestinatario(String descTipoDestinatario) {
		this.descTipoDestinatario = descTipoDestinatario;
	}
	
	
}
