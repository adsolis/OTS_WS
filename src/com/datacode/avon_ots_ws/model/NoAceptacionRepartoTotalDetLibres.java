/**
 * @author brenda.estrada
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 *
 */
public class NoAceptacionRepartoTotalDetLibres {
	//Atributos de Detalle de Reporte - NAR Total 
	private String ordenLibreCobroNoEntregado = "", 	
				   sumValorPedidoLibre= "",	
				   totalLibreCobro ="",
				   porcentAceptacionLibresRed  = "";
	


	public String getOrdenLibreCobroNoEntregado() {
		return ordenLibreCobroNoEntregado;
	}

	public void setOrdenLibreCobroNoEntregado(String ordenLibreCobroNoEntregado) {
		this.ordenLibreCobroNoEntregado = ordenLibreCobroNoEntregado;
	}

	public String getSumValorPedidoLibre() {
		return sumValorPedidoLibre;
	}

	public void setSumValorPedidoLibre(String sumValorPedidoLibre) {
		this.sumValorPedidoLibre = sumValorPedidoLibre;
	}

	public String getTotalLibreCobro() {
		return totalLibreCobro;
	}

	public void setTotalLibreCobro(String totalLibreCobro) {
		this.totalLibreCobro = totalLibreCobro;
	}

	public String getPorcentAceptacionLibresRed() {
		return porcentAceptacionLibresRed;
	}

	public void setPorcentAceptacionLibresRed(String porcentAceptacionLibresRed) {
		this.porcentAceptacionLibresRed = porcentAceptacionLibresRed;
	}

}
