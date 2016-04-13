/**
 *
 *  @since 14/02/2012
 *
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jessica.leon
 *
 */
public class ModelTablaAnalisisEfectivoLiquidacion {

	private String bodegaSub;
	private double importe;
	private String fecha;
	private String banco;
	/**
	 * @return the bodegaSub
	 */
	public String getBodegaSub() {
		return bodegaSub;
	}
	/**
	 * @param bodegaSub the bodegaSub to set
	 */
	public void setBodegaSub(String bodegaSub) {
		this.bodegaSub = bodegaSub;
	}
	/**
	 * @return the importe
	 */
	public double getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(double importe) {
		this.importe = importe;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}
	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}
}
