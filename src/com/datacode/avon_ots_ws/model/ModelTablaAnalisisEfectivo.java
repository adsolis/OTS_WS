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
public class ModelTablaAnalisisEfectivo {

	private String	ruta;
	private double  efectivoRecolectado;
	private String	fechaRecepcionEfectivo;
	private String	bancoDeposito;
	private double depositoGlobal;
	private double totalEfectivo;
	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	/**
	 * @return the fechaRecepcionEfectivo
	 */
	public String getFechaRecepcionEfectivo() {
		return fechaRecepcionEfectivo;
	}
	/**
	 * @param fechaRecepcionEfectivo the fechaRecepcionEfectivo to set
	 */
	public void setFechaRecepcionEfectivo(String fechaRecepcionEfectivo) {
		this.fechaRecepcionEfectivo = fechaRecepcionEfectivo;
	}
	/**
	 * @author jessica.leon
	 * @since 12/07/2012
	 * @return the efectivoRecolectado
	 */
	public double getEfectivoRecolectado() {
		return efectivoRecolectado;
	}
	/**
	 * @author jessica.leon
	 * @since 12/07/2012
	 * @param efectivoRecolectado the efectivoRecolectado to set
	 */
	public void setEfectivoRecolectado(double efectivoRecolectado) {
		this.efectivoRecolectado = efectivoRecolectado;
	}
	/**
	 * @author jessica.leon
	 * @since 12/07/2012
	 * @return the bancoDeposito
	 */
	public String getBancoDeposito() {
		return bancoDeposito;
	}
	/**
	 * @author jessica.leon
	 * @since 12/07/2012
	 * @param bancoDeposito the bancoDeposito to set
	 */
	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}
	/**
	 * @author jessica.leon
	 * @since 12/07/2012
	 * @return the depositoGlobal
	 */
	public double getDepositoGlobal() {
		return depositoGlobal;
	}
	/**
	 * @author jessica.leon
	 * @since 12/07/2012
	 * @param depositoGlobal the depositoGlobal to set
	 */
	public void setDepositoGlobal(double depositoGlobal) {
		this.depositoGlobal = depositoGlobal;
	}
	/**
	 * @author jessica.leon
	 * @since 12/07/2012
	 * @return the totalEfectivo
	 */
	public double getTotalEfectivo() {
		return totalEfectivo;
	}
	/**
	 * @author jessica.leon
	 * @since 12/07/2012
	 * @param totalEfectivo the totalEfectivo to set
	 */
	public void setTotalEfectivo(double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}
}
