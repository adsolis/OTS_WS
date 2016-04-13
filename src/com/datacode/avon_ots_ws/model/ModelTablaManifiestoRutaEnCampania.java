/**
 * @author jorge.torner
 * @since 30/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jorge.torner
 * @since 30/01/2012
 */
public class ModelTablaManifiestoRutaEnCampania {
	private String account;
	private String name;
	private String cajas;
	private String unitarios;
	private String premios;
	private String secuenciaEntrega;
	private String visitado;
	private String entregado;
	private String address1;
	private String address2;
	private String address3;
	private String toPay;
	
	
	/**
	 * @return the visitado
	 */
	public String getVisitado() {
		return visitado;
	}
	/**
	 * @param visitado the visitado to set
	 */
	public void setVisitado(String visitado) {
		this.visitado = visitado;
	}
	/**
	 * @return the entregado
	 */
	public String getEntregado() {
		return entregado;
	}
	/**
	 * @param entregado the entregado to set
	 */
	public void setEntregado(String entregado) {
		this.entregado = entregado;
	}
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}
	/**
	 * @param address3 the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	/**
	 * @return the toPay
	 */
	public String getToPay() {
		return toPay;
	}
	/**
	 * @param toPay the toPay to set
	 */
	public void setToPay(String toPay) {
		this.toPay = toPay;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCajas() {
		return cajas;
	}
	public void setCajas(String cajas) {
		this.cajas = cajas;
	}
	public String getUnitarios() {
		return unitarios;
	}
	public void setUnitarios(String unitarios) {
		this.unitarios = unitarios;
	}
	public String getPremios() {
		return premios;
	}
	public void setPremios(String premios) {
		this.premios = premios;
	}
	public String getSecuenciaEntrega() {
		return secuenciaEntrega;
	}
	public void setSecuenciaEntrega(String secuenciaEntrega) {
		this.secuenciaEntrega = secuenciaEntrega;
	}
}
