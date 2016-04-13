package com.datacode.avon_ots_ws.model;
/**
 *
 *  @since 27/12/2011
 *
 */



/**
 * @author jessica.leon
 * @since 27/12/2011
 */
public class Campania {

	private int idCampania;
	private int anioCampania;
	private int campania;
	private String fechaInicio;
	private String fechaFin;
	private String lastUpdTs;
	private String fechaActualizado;
	private String usuarioActualiza;
	//b.estrada: se agrego Zona
	private int idZona;
	private String descZona;

	public Campania(){
		
		idCampania = 0;
		anioCampania = 0;
		campania = 0;
		fechaInicio = null;
		fechaFin = null;
		lastUpdTs = null;
		fechaActualizado = null;
		usuarioActualiza = null;
		idZona = 0;
		descZona = null;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @return the idCampania
	 */
	public int getIdCampania() {
		return idCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param idCampania the idCampania to set
	 */
	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @return the anioCampania
	 */
	public int getAnioCampania() {
		return anioCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param anioCampania the anioCampania to set
	 */
	public void setAnioCampania(int anioCampania) {
		this.anioCampania = anioCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @return the campania
	 */
	public int getCampania() {
		return campania;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param campania the campania to set
	 */
	public void setCampania(int campania) {
		this.campania = campania;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @return the lastUpdTs
	 */
	public String getLastUpdTs() {
		return lastUpdTs;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param lastUpdTs the lastUpdTs to set
	 */
	public void setLastUpdTs(String lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @return the fechaActualizado
	 */
	public String getFechaActualizado() {
		return fechaActualizado;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param fechaActualizado the fechaActualizado to set
	 */
	public void setFechaActualizado(String fechaActualizado) {
		this.fechaActualizado = fechaActualizado;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}

	/**
	 * @author jessica.leon
	 * @since 09/01/2012
	 * @param usuarioActualiza the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}

	/**
	 * @return the idZona
	 */
	public int getIdZona() {
		return idZona;
	}

	/**
	 * @param idZona the idZona to set
	 */
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	/**
	 * @return the descZona
	 */
	public String getDescZona() {
		return descZona;
	}

	/**
	 * @param descZona the descZona to set
	 */
	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}
	
	
}
