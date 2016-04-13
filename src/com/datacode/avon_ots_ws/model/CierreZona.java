/**
 * @author jose.ponce
 * @since 05/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jose.ponce
 *
 */
public class CierreZona {

	private String registro= "";
	private int id_orden= 0;
	private int id_item= 0;
	private String codigo_barras= "";
	private String descripcion= "";
	private String clave= "";
	private String estatus= "";
	private int cantidad= 0;
	private String escaneados="";
	private String nombre;
	private String ruta;
	private String secuencia;
	
	
	public CierreZona(){
	
	}

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

	public String getCodigo_barras() {
		return codigo_barras;
	}

	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getEscaneados() {
		return escaneados;
	}

	public void setEscaneados(String escaneados) {
		this.escaneados = escaneados;
	}

	/**
	 * @author jessica.leon
	 * @since 12/04/2012
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @author jessica.leon
	 * @since 12/04/2012
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @author jessica.leon
	 * @since 12/04/2012
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * @author jessica.leon
	 * @since 12/04/2012
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * @author jessica.leon
	 * @since 12/04/2012
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * @author jessica.leon
	 * @since 12/04/2012
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
}
