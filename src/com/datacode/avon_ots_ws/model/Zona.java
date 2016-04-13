/**
 *
 *  @since 27/12/2011
 *
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jessica.leon
 * @since 27/12/2011
 * @version  Se agrego descLDC, descDivision y nombreGerenteZonal en GET y SET
 * @since	 18-01-2012
 */
public class Zona {
	
	private String idZona;
	private String zona;
	private String anioCampaniaActual;
	private String campania;
	private String lastUpdTs;
	private String fechaActualizado;
	private String usuarioActualiza;
	private String idLDC, descLDC = "";
	private String idDivision, descDivision = "";
	private String tipoZona;
	private String nombreGerenteZonal;
	
	
	public Zona(){
		
		idZona = null;
		zona = null;
		anioCampaniaActual = null;
		campania = null;
		lastUpdTs = null;
		fechaActualizado = null;
		usuarioActualiza = null;
		idLDC = null;		descLDC = "";
		idDivision = null;	descDivision = "";
		tipoZona = null;
		nombreGerenteZonal = null;
		
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the idZona
	 */
	public String getIdZona() {
		return idZona;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param idZona the idZona to set
	 */
	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the anioCampaniaActual
	 */
	public String getAnioCampaniaActual() {
		return anioCampaniaActual;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param anioCampaniaActual the anioCampaniaActual to set
	 */
	public void setAnioCampaniaActual(String anioCampaniaActual) {
		this.anioCampaniaActual = anioCampaniaActual;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the campania
	 */
	public String getCampania() {
		return campania;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param campania the campania to set
	 */
	public void setCampania(String campania) {
		this.campania = campania;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the lastUpdTs
	 */
	public String getLastUpdTs() {
		return lastUpdTs;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param lastUpdTs the lastUpdTs to set
	 */
	public void setLastUpdTs(String lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the fechaActualizado
	 */
	public String getFechaActualizado() {
		return fechaActualizado;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param fechaActualizado the fechaActualizado to set
	 */
	public void setFechaActualizado(String fechaActualizado) {
		this.fechaActualizado = fechaActualizado;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param usuarioActualiza the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the idLDC
	 */
	public String getIdLDC() {
		return idLDC;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param idLDC the idLDC to set
	 */
	public void setIdLDC(String idLDC) {
		this.idLDC = idLDC;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the idDivision
	 */
	public String getIdDivision() {
		return idDivision;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param idDivision the idDivision to set
	 */
	public void setIdDivision(String idDivision) {
		this.idDivision = idDivision;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the tipoZona
	 */
	public String getTipoZona() {
		return tipoZona;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param tipoZona the tipoZona to set
	 */
	public void setTipoZona(String tipoZona) {
		this.tipoZona = tipoZona;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @return the nombreGerente
	 */
	public String getNombreGerente() {
		return nombreGerenteZonal;
	}


	/**
	 * @author jessica.leon
	 * @since 28/12/2011
	 * @param nombreGerente the nombreGerente to set
	 */
	public void setNombreGerente(String nombreGerenteZonal) {
		this.nombreGerenteZonal = nombreGerenteZonal;
	}


	/**
	 * @return the descLDC
	 */
	public String getDescLDC() {
		return descLDC;
	}


	/**
	 * @param descLDC the descLDC to set
	 */
	public void setDescLDC(String descLDC) {
		this.descLDC = descLDC;
	}


	/**
	 * @return the descDivision
	 */
	public String getDescDivision() {
		return descDivision;
	}


	/**
	 * @param descDivision the descDivision to set
	 */
	public void setDescDivision(String descDivision) {
		this.descDivision = descDivision;
	}


	/**
	 * @return the nombreGerenteZonal
	 */
	public String getNombreGerenteZonal() {
		return nombreGerenteZonal;
	}


	/**
	 * @param nombreGerenteZonal the nombreGerenteZonal to set
	 */
	public void setNombreGerenteZonal(String nombreGerenteZonal) {
		this.nombreGerenteZonal = nombreGerenteZonal;
	}
	
	
	

}
