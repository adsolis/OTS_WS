/**
 * 
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 *
 */
public class NoAceptacionRepartoTotalDetRED {
	//Atributos de Detalle de Reporte - NAR
	private String noRed = "",				porcentajeAceptacionRed  = "",
				   ordenDevolucion = "",	sumValorPedidoRed = "",
				   totalOrdenes = "";
	private String ordenLibreCobroNoEntregado = "", 	
			   sumValorPedidoLibre= "",	
			   totalLibreCobro ="",
			   porcentAceptacionLibresRed  = "";
	

	public String getNoRed() {
		return noRed;
	}


	public void setNoRed(String noRed) {
		this.noRed = noRed;
	}


	public String getPorcentajeAceptacionRed() {
		return porcentajeAceptacionRed;
	}


	public void setPorcentajeAceptacionRed(String porcentajeAceptacionRed) {
		this.porcentajeAceptacionRed = porcentajeAceptacionRed;
	}


	public String getOrdenDevolucion() {
		return ordenDevolucion;
	}


	public void setOrdenDevolucion(String ordenDevolucion) {
		this.ordenDevolucion = ordenDevolucion;
	}


	public String getSumValorPedidoRed() {
		return sumValorPedidoRed;
	}


	public void setSumValorPedidoRed(String sumValorPedidoRed) {
		this.sumValorPedidoRed = sumValorPedidoRed;
	}


	public String getTotalOrdenes() {
		return totalOrdenes;
	}


	public void setTotalOrdenes(String totalOrdenes) {
		this.totalOrdenes = totalOrdenes;
	}
	
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
