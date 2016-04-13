/**
 * 
 */
package com.datacode.avon_ots_ws.model;

import java.util.List;

/**
 * @author brenda.estrada
 *
 */
public class NoAceptacionRepartoTotal {
	// Atributos Encabezado Reporte
		private String idZona = "", 	idCampania = "", 			descZona = "",
				descCampania = "", 		porcentaAceptacion = "", 	cantidadOrdenes = "",
				importeTotalZona = "", 	totalOrdenes = "", 
				cantidadLibresCobro = "",	porcentaAceptacionLibreCobro = "", sumatoriaTotalOrdenesLibreCobro, 
				ordenesLibresCobroTotales;
				
	//Atributos de Detalle
	private List<NoAceptacionRepartoTotalDetRED> lstDetalle1 = null;
	private List<NoAceptacionRepartoTotalDetLibres> lstDetalle2 = null;
	
	
	public String getIdZona() {
		return idZona;
	}
	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}
	public String getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(String idCampania) {
		this.idCampania = idCampania;
	}
	public String getDescZona() {
		return descZona;
	}
	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}
	public String getDescCampania() {
		return descCampania;
	}
	public void setDescCampania(String descCampania) {
		this.descCampania = descCampania;
	}
	public String getPorcentaAceptacion() {
		return porcentaAceptacion;
	}
	public void setPorcentaAceptacion(String porcentaAceptacion) {
		this.porcentaAceptacion = porcentaAceptacion;
	}
	public String getCantidadOrdenes() {
		return cantidadOrdenes;
	}
	public void setCantidadOrdenes(String cantidadOrdenes) {
		this.cantidadOrdenes = cantidadOrdenes;
	}
	public String getImporteTotalZona() {
		return importeTotalZona;
	}
	public void setImporteTotalZona(String importeTotalZona) {
		this.importeTotalZona = importeTotalZona;
	}
	public String getTotalOrdenes() {
		return totalOrdenes;
	}
	public void setTotalOrdenes(String totalOrdenes) {
		this.totalOrdenes = totalOrdenes;
	}
	public String getCantidadLibresCobro() {
		return cantidadLibresCobro;
	}
	public void setCantidadLibresCobro(String cantidadLibresCobro) {
		this.cantidadLibresCobro = cantidadLibresCobro;
	}
	public String getPorcentaAceptacionLibreCobro() {
		return porcentaAceptacionLibreCobro;
	}
	public void setPorcentaAceptacionLibreCobro(String porcentaAceptacionLibreCobro) {
		this.porcentaAceptacionLibreCobro = porcentaAceptacionLibreCobro;
	}
	public List<NoAceptacionRepartoTotalDetRED> getLstDetalle1() {
		return lstDetalle1;
	}
	public void setLstDetalle1(List<NoAceptacionRepartoTotalDetRED> lstDetalle1) {
		this.lstDetalle1 = lstDetalle1;
	}
	public List<NoAceptacionRepartoTotalDetLibres> getLstDetalle2() {
		return lstDetalle2;
	}
	public void setLstDetalle2(List<NoAceptacionRepartoTotalDetLibres> lstDetalle2) {
		this.lstDetalle2 = lstDetalle2;
	}
	public String getSumatoriaTotalOrdenesLibreCobro() {
		return sumatoriaTotalOrdenesLibreCobro;
	}
	public void setSumatoriaTotalOrdenesLibreCobro(
			String sumatoriaTotalOrdenesLibreCobro) {
		this.sumatoriaTotalOrdenesLibreCobro = sumatoriaTotalOrdenesLibreCobro;
	}
	public String getOrdenesLibresCobroTotales() {
		return ordenesLibresCobroTotales;
	}
	public void setOrdenesLibresCobroTotales(String ordenesLibresCobroTotales) {
		this.ordenesLibresCobroTotales = ordenesLibresCobroTotales;
	}
}
