/**
 *
 *  @since 24/01/2012
 *
 */
package com.datacode.avon_ots_ws.model;

import java.util.List;

/**
 * @author jessica.leon
 * 
 */
public class ModelHistorialPorRepresentante {

	private String zona;
	private String idRepresentante;
	private String registro;
	private String nombre;
	private String direccion;
	private String direccion1;
	private String direccion2;
	private String red;
	private List<ModelTablaHistorialPorRepresentante> detalleRepresentante;

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona
	 *            the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * @param registro
	 *            the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the direccion1
	 */
	public String getDireccion1() {
		return direccion1;
	}

	/**
	 * @param direccion1
	 *            the direccion1 to set
	 */
	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}

	/**
	 * @return the direccion2
	 */
	public String getDireccion2() {
		return direccion2;
	}

	/**
	 * @param direccion2
	 *            the direccion2 to set
	 */
	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}

	/**
	 * @return the red
	 */
	public String getRed() {
		return red;
	}

	/**
	 * @param red
	 *            the red to set
	 */
	public void setRed(String red) {
		this.red = red;
	}

	/**
	 * @return the detalleRepresentante
	 */
	public List<ModelTablaHistorialPorRepresentante> getDetalleRepresentante() {
		return detalleRepresentante;
	}

	/**
	 * @param detalleRepresentante
	 *            the detalleRepresentante to set
	 */
	public void setDetalleRepresentante(
			List<ModelTablaHistorialPorRepresentante> detalleRepresentante) {
		this.detalleRepresentante = detalleRepresentante;
	}

	/**
	 * @author jessica.leon
	 * @since 05/07/2012
	 * @return the idRepresentante
	 */
	public String getIdRepresentante() {
		return idRepresentante;
	}

	/**
	 * @author jessica.leon
	 * @since 05/07/2012
	 * @param idRepresentante the idRepresentante to set
	 */
	public void setIdRepresentante(String idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
}
