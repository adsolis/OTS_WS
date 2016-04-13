/**
 * Clase de Mapeo con el objeto en la Base de datos - PW_LDC
 * @author brenda.estrada
 * @since 05/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 05/01/2012
 * @category Map
 */
public class ParametrosLDC {
	//Atributos del Objeto
	private Integer idLdc = 0 ;
	private String desc = "";
	private String razonSocial = "";
	private String mail = "";
	private String clave = "";
	private String password = "";
	private String serverSMTP = "";
	private String puerto = "";
	private String tipoSeguridad = "";
	private String factorMax = "";
	private String factorMin = "";
	private String codigoBarrasEntrada = "";
	private String codigoBarrasSalida = "";
	private String poblacion = "";
	private String region = "";
	private byte[] logoLDC;
	private byte[] logoAVON;
	private String ipServerSOS = "";
	//Dependencias
	private Integer idPais = 0;
	private String descPais = "";
	
	/**
	 * @return the idLdc
	 */
	public Integer getIdLdc() {
		return idLdc;
	}


	/**
	 * @param idLdc the idLdc to set
	 */
	public void setIdLdc(Integer idLdc) {
		this.idLdc = idLdc;
	}


	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}


	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}


	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}


	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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
	 * @return the serverSMTP
	 */
	public String getServerSMTP() {
		return serverSMTP;
	}


	/**
	 * @param serverSMTP the serverSMTP to set
	 */
	public void setServerSMTP(String serverSMTP) {
		this.serverSMTP = serverSMTP;
	}


	/**
	 * @return the puerto
	 */
	public String getPuerto() {
		return puerto;
	}


	/**
	 * @param puerto the puerto to set
	 */
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}


	/**
	 * @return the tipoSeguridad
	 */
	public String getTipoSeguridad() {
		return tipoSeguridad;
	}


	/**
	 * @param tipoSeguridad the tipoSeguridad to set
	 */
	public void setTipoSeguridad(String tipoSeguridad) {
		this.tipoSeguridad = tipoSeguridad;
	}


	/**
	 * @return the factorMax
	 */
	public String getFactorMax() {
		return factorMax;
	}


	/**
	 * @param factorMax the factorMax to set
	 */
	public void setFactorMax(String factorMax) {
		this.factorMax = factorMax;
	}


	/**
	 * @return the factorMin
	 */
	public String getFactorMin() {
		return factorMin;
	}


	/**
	 * @param factorMin the factorMin to set
	 */
	public void setFactorMin(String factorMin) {
		this.factorMin = factorMin;
	}


	/**
	 * @return the codigoBarrasEntrada
	 */
	public String getCodigoBarrasEntrada() {
		return codigoBarrasEntrada;
	}


	/**
	 * @param codigoBarrasEntrada the codigoBarrasEntrada to set
	 */
	public void setCodigoBarrasEntrada(String codigoBarrasEntrada) {
		this.codigoBarrasEntrada = codigoBarrasEntrada;
	}


	/**
	 * @return the codigoBarrasSalida
	 */
	public String getCodigoBarrasSalida() {
		return codigoBarrasSalida;
	}


	/**
	 * @param codigoBarrasSalida the codigoBarrasSalida to set
	 */
	public void setCodigoBarrasSalida(String codigoBarrasSalida) {
		this.codigoBarrasSalida = codigoBarrasSalida;
	}


	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}


	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}


	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}


	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}


	/**
	 * @return the ipServerSOS
	 */
	public String getIpServerSOS() {
		return ipServerSOS;
	}


	/**
	 * @param ipServerSOS the ipServerSOS to set
	 */
	public void setIpServerSOS(String ipServerSOS) {
		this.ipServerSOS = ipServerSOS;
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
	 * @return the logoLDC
	 */
	public byte[] getLogoLDC() {
		return logoLDC;
	}


	/**
	 * @param logoLDC the logoLDC to set
	 */
	public void setLogoLDC(byte[] logoLDC) {
		this.logoLDC = logoLDC;
	}


	/**
	 * @return the logoAVON
	 */
	public byte[] getLogoAVON() {
		return logoAVON;
	}


	/**
	 * @param logoAVON the logoAVON to set
	 */
	public void setLogoAVON(byte[] logoAVON) {
		this.logoAVON = logoAVON;
	}

}
