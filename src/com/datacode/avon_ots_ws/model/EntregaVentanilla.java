/**
 * @author jose.ponce
 * @since 20/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jose.ponce
 *
 */
public class EntregaVentanilla {

	private String campania="";
	private int id_campania=0;
	private int id_orden= 0;
	private String orden="";
	private int total_cajas=0;
	private int total_unitarios=0;
	private int total_premios=0;
	private String estatus_reparto="";
	private String causa_devolucion="";
	private String registro="";
	private String nombre="";
	private int id_item= 0;
	private String domicilio;
	private int id_zona;
	private String zona;
	private Double monto_previo;
	private String codigo_barras;
	private String codigo_fsc;
	private String codigo_ean13;
	private String tipo_item;
	private String estatus_item;
	private int total_item;
	private String escaneados;
	//Detalle de pago
	private int id_pago_entrega;
	private int id_banco;
	private String banco;
	private int id_tipo_pago;
	private String tipo_pago;
	private double monto_pagado;
	private String folios;
	private String fecha_pago;
	
	/*
	 * ********* ENCAPSULAMIENTO **********
	 */
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public int getId_orden() {
		return id_orden;
	}
	public void setId_orden(int id_orden) {
		this.id_orden = id_orden;
	}
	public int getId_item() {
		return id_item;
	}
	public void setId_item(int id_item) {
		this.id_item = id_item;
	}
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public int getId_campania() {
		return id_campania;
	}
	public void setId_campania(int id_campania) {
		this.id_campania = id_campania;
	}
	public int getTotal_cajas() {
		return total_cajas;
	}
	public void setTotal_cajas(int total_cajas) {
		this.total_cajas = total_cajas;
	}
	public int getTotal_unitarios() {
		return total_unitarios;
	}
	public void setTotal_unitarios(int total_unitarios) {
		this.total_unitarios = total_unitarios;
	}
	public int getTotal_premios() {
		return total_premios;
	}
	public void setTotal_premios(int total_premios) {
		this.total_premios = total_premios;
	}
	public String getEstatus_reparto() {
		return estatus_reparto;
	}
	public void setEstatus_reparto(String estatus_reparto) {
		this.estatus_reparto = estatus_reparto;
	}
	public String getCausa_devolucion() {
		return causa_devolucion;
	}
	public void setCausa_devolucion(String causa_devolucion) {
		this.causa_devolucion = causa_devolucion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public int getId_zona() {
		return id_zona;
	}
	public void setId_zona(int id_zona) {
		this.id_zona = id_zona;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public Double getMonto_previo() {
		return monto_previo;
	}
	public void setMonto_previo(Double monto_previo) {
		this.monto_previo = monto_previo;
	}
	
	public String getCodigo_barras() {
		return codigo_barras;
	}
	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
	}
	
	public String getCodigoFSC() {
		return codigo_fsc;
	}
	public void setCodigoFSC(String codigo_fsc) {
		this.codigo_fsc = codigo_fsc;
	}
	
	public String getCodigoEAN13() {
		return codigo_ean13;
	}
	public void setCodigoEAN13(String codigo_ean13) {
		this.codigo_ean13 = codigo_ean13;
	}
	
	public String getTipo_item() {
		return tipo_item;
	}
	public void setTipo_item(String tipo_item) {
		this.tipo_item = tipo_item;
	}
	public String getEstatus_item() {
		return estatus_item;
	}
	public void setEstatus_item(String estatus_item) {
		this.estatus_item = estatus_item;
	}
	public int getTotal_item() {
		return total_item;
	}
	public void setTotal_item(int total_item) {
		this.total_item = total_item;
	}
	public String getEscaneados() {
		return escaneados;
	}
	public void setEscaneados(String escaneados) {
		this.escaneados = escaneados;
	}
	public int getId_pago_entrega() {
		return id_pago_entrega;
	}
	public void setId_pago_entrega(int id_pago_entrega) {
		this.id_pago_entrega = id_pago_entrega;
	}
	public int getId_banco() {
		return id_banco;
	}
	public void setId_banco(int id_banco) {
		this.id_banco = id_banco;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public int getId_tipo_pago() {
		return id_tipo_pago;
	}
	public void setId_tipo_pago(int id_tipo_pago) {
		this.id_tipo_pago = id_tipo_pago;
	}
	public String getTipo_pago() {
		return tipo_pago;
	}
	public void setTipo_pago(String tipo_pago) {
		this.tipo_pago = tipo_pago;
	}
	public double getMonto_pagado() {
		return monto_pagado;
	}
	public void setMonto_pagado(double monto_pagado) {
		this.monto_pagado = monto_pagado;
	}
	public String getFolios() {
		return folios;
	}
	public void setFolios(String folios) {
		this.folios = folios;
	}
}