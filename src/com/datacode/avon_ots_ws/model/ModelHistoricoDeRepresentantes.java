/**
 *
 *  @since 26/01/2012
 *
 */
package com.datacode.avon_ots_ws.model;

import java.util.List;

/**
 * @author jessica.leon
 *
 */
public class ModelHistoricoDeRepresentantes {

	private String zona;
	private String campania;
	private List<ModelTablaHistoricoDeRepresentantes> detalleRepresentantes;
	
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the campania
	 */
	public String getCampania() {
		return campania;
	}
	/**
	 * @param campania the campania to set
	 */
	public void setCampania(String campania) {
		this.campania = campania;
	}
	/**
	 * @return the detalleRepresentantes
	 */
	public List<ModelTablaHistoricoDeRepresentantes> getDetalleRepresentantes() {
		return detalleRepresentantes;
	}
	/**
	 * @param detalleRepresentantes the detalleRepresentantes to set
	 */
	public void setDetalleRepresentantes(
			List<ModelTablaHistoricoDeRepresentantes> detalleRepresentantes) {
		this.detalleRepresentantes = detalleRepresentantes;
	}	
}
