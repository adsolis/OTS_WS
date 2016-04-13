/**
 * @author brenda.estrada
 * @since 02/02/2012
 */
package com.datacode.avon_ots_ws.model;

import java.util.List;

/**
 * @author brenda.estrada
 * @since 02/02/2012
 */
public class NoAceptacionReparto {

	// Atributos Encabezado Reporte
	private String idZona = "", idCampania = "", descZona = "",
			descCampania = "", porcentaAceptacion = "", miVentaNAR = "",
			ordenPendienteEntregar = "";
	// Detalle de Reporte
	private List<NoAceptacionRepartoDetalle> lstNoAceptacionReparto = null;

	
	/**
	 * @return the idZona
	 */
	public String getIdZona() {
		return idZona;
	}

	/**
	 * @param idZona
	 *            the idZona to set
	 */
	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}

	/**
	 * @return the idCampania
	 */
	public String getIdCampania() {
		return idCampania;
	}

	/**
	 * @param idCampania
	 *            the idCampania to set
	 */
	public void setIdCampania(String idCampania) {
		this.idCampania = idCampania;
	}

	/**
	 * @return the descZona
	 */
	public String getDescZona() {
		return descZona;
	}

	/**
	 * @param descZona
	 *            the descZona to set
	 */
	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}

	/**
	 * @return the descCampania
	 */
	public String getDescCampania() {
		return descCampania;
	}

	/**
	 * @param descCampania
	 *            the descCampania to set
	 */
	public void setDescCampania(String descCampania) {
		this.descCampania = descCampania;
	}

	/**
	 * @return the porcentaAceptacion
	 */
	public String getPorcentaAceptacion() {
		return porcentaAceptacion;
	}

	/**
	 * @param porcentaAceptacion
	 *            the porcentaAceptacion to set
	 */
	public void setPorcentaAceptacion(String porcentaAceptacion) {
		this.porcentaAceptacion = porcentaAceptacion;
	}

	/**
	 * @return the miVentaNAR
	 */
	public String getMiVentaNAR() {
		return miVentaNAR;
	}

	/**
	 * @param miVentaNAR
	 *            the miVentaNAR to set
	 */
	public void setMiVentaNAR(String miVentaNAR) {
		this.miVentaNAR = miVentaNAR;
	}

	/**
	 * @return the ordenPendienteEntregar
	 */
	public String getOrdenPendienteEntregar() {
		return ordenPendienteEntregar;
	}

	/**
	 * @param ordenPendienteEntregar
	 *            the ordenPendienteEntregar to set
	 */
	public void setOrdenPendienteEntregar(String ordenPendienteEntregar) {
		this.ordenPendienteEntregar = ordenPendienteEntregar;
	}

	/**
	 * @return the lstNoAceptacionReparto
	 */
	public List<NoAceptacionRepartoDetalle> getLstNoAceptacionReparto() {
		return lstNoAceptacionReparto;
	}

	/**
	 * @param lstNoAceptacionReparto
	 *            the lstNoAceptacionReparto to set
	 */
	public void setLstNoAceptacionReparto(
			List<NoAceptacionRepartoDetalle> lstNoAceptacionReparto) {
		this.lstNoAceptacionReparto = lstNoAceptacionReparto;
	}

}
