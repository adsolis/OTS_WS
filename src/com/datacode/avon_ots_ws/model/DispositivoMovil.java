/**
 * Mapeo de las propiedades del Objeto de la BD - PW_DISPOSITIVO_MOVIL
 * @author brenda.estrada
 * @since 12/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 11/01/2012
 * @category Map
 */
public class DispositivoMovil {
	//Atributos de Mapeo del Objeto
	private Integer idDispositivoMovil = 0;
	private String noSerie = "";
	private String marca = "";
	private String modelo ="";
	private String macAdress ="";
	private String direccionIP ="";
	//Dependencias
	private Integer idLDC = 0;
	private String descLDC = "";
	private Integer idPais = 0;
	private String descPais = "";
	private Integer idEstatus = 0;
	private String descEstatus = "";
	
	
	/**
	 * @return the idDispositivoMovil
	 */
	public Integer getIdDispositivoMovil() {
		return idDispositivoMovil;
	}
	/**
	 * @param idDispositivoMovil the idDispositivoMovil to set
	 */
	public void setIdDispositivoMovil(Integer idDispositivoMovil) {
		this.idDispositivoMovil = idDispositivoMovil;
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
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return the macAdress
	 */
	public String getMacAdress() {
		return macAdress;
	}
	/**
	 * @param macAdress the macAdress to set
	 */
	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}
	/**
	 * @return the direccionIP
	 */
	public String getDireccionIP() {
		return direccionIP;
	}
	/**
	 * @param direccionIP the direccionIP to set
	 */
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
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
