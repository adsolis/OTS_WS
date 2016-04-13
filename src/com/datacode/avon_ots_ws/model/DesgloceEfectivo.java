package com.datacode.avon_ots_ws.model;

public class DesgloceEfectivo {

	private String  denominacion;
	private int cantidad;
	private double total;
	private double subTotal;
	private String billete_Moneda;
	
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public String getBillete_Moneda() {
		return billete_Moneda;
	}
	public void setBillete_Moneda(String billete_Moneda) {
		this.billete_Moneda = billete_Moneda;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
