/**
 * Mapeo de las propiedades del Objeto de la BD - // PW_PROGRAMACION_REPARTO //
 * @author brenda.estrada
 * @since 18/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 18/01/2012
 * @category Map
 */
public class ProgramacionReparto {
	//Atributos del Obj
	private Integer idProgramacionReparto = 0;
	private String  noCuartoAnio = "", 	anio = "" , mail = "",
					feFacturacion = "", feLlegadaLDC="", feDiaRepartoPrimero = "",
					feDiaRepartoUltimo = "", feUltimoDiaBodega = "", feCierre = "", feActualizado = "", anioCampania = "",
					usuarioActualiza = "";
	//Dependencias
	private Integer idZona = 0;			private String descZona = "";
	private Integer idCampania = 0;		private String descCampania = "";
	private Integer idLDC = 0;			private String descLDC = "";
	private Integer idDivision = 0;		private String descDivision = "";
	

	/**
	 * @return the idProgramacionReparto
	 */
	public Integer getIdProgramacionReparto() {
		return idProgramacionReparto;
	}

	/**
	 * @param idProgramacionReparto the idProgramacionReparto to set
	 */
	public void setIdProgramacionReparto(Integer idProgramacionReparto) {
		this.idProgramacionReparto = idProgramacionReparto;
	}

	/**
	 * @return the noCuartoAnio
	 */
	public String getNoCuartoAnio() {
		return noCuartoAnio;
	}

	/**
	 * @param noCuartoAnio the noCuartoAnio to set
	 */
	public void setNoCuartoAnio(String noCuartoAnio) {
		this.noCuartoAnio = noCuartoAnio;
	}

	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the feFacturacion
	 */
	public String getFeFacturacion() {
		return feFacturacion;
	}

	/**
	 * @param feFacturacion the feFacturacion to set
	 */
	public void setFeFacturacion(String feFacturacion) {
		this.feFacturacion = feFacturacion;
	}

	/**
	 * @return the feLlegadaLDC
	 */
	public String getFeLlegadaLDC() {
		return feLlegadaLDC;
	}

	/**
	 * @param feLlegadaLDC the feLlegadaLDC to set
	 */
	public void setFeLlegadaLDC(String feLlegadaLDC) {
		this.feLlegadaLDC = feLlegadaLDC;
	}

	/**
	 * @return the feDiaRepartoPrimero
	 */
	public String getFeDiaRepartoPrimero() {
		return feDiaRepartoPrimero;
	}

	/**
	 * @param feDiaRepartoPrimero the feDiaRepartoPrimero to set
	 */
	public void setFeDiaRepartoPrimero(String feDiaRepartoPrimero) {
		this.feDiaRepartoPrimero = feDiaRepartoPrimero;
	}

	/**
	 * @return the feDiaRepartoUltimo
	 */
	public String getFeDiaRepartoUltimo() {
		return feDiaRepartoUltimo;
	}

	/**
	 * @param feDiaRepartoUltimo the feDiaRepartoUltimo to set
	 */
	public void setFeDiaRepartoUltimo(String feDiaRepartoUltimo) {
		this.feDiaRepartoUltimo = feDiaRepartoUltimo;
	}

	/**
	 * @return the feUltimoDiaBodega
	 */
	public String getFeUltimoDiaBodega() {
		return feUltimoDiaBodega;
	}

	/**
	 * @param feUltimoDiaBodega the feUltimoDiaBodega to set
	 */
	public void setFeUltimoDiaBodega(String feUltimoDiaBodega) {
		this.feUltimoDiaBodega = feUltimoDiaBodega;
	}

	/**
	 * @return the feCierre
	 */
	public String getFeCierre() {
		return feCierre;
	}

	/**
	 * @param feCierre the feCierre to set
	 */
	public void setFeCierre(String feCierre) {
		this.feCierre = feCierre;
	}

	/**
	 * @return the feActualizado
	 */
	public String getFeActualizado() {
		return feActualizado;
	}

	/**
	 * @param feActualizado the feActualizado to set
	 */
	public void setFeActualizado(String feActualizado) {
		this.feActualizado = feActualizado;
	}

	/**
	 * @return the idZona
	 */
	public Integer getIdZona() {
		return idZona;
	}

	/**
	 * @param idZona the idZona to set
	 */
	public void setIdZona(Integer idZona) {
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

	/**
	 * @return the idCampania
	 */
	public Integer getIdCampania() {
		return idCampania;
	}

	/**
	 * @param idCampania the idCampania to set
	 */
	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
	}

	/**
	 * @return the descCampania
	 */
	public String getDescCampania() {
		return descCampania;
	}

	/**
	 * @param descCampania the descCampania to set
	 */
	public void setDescCampania(String descCampania) {
		this.descCampania = descCampania;
	}

	/**
	 * @return the idLDC
	 */
	public Integer getIdLDC() {
		return idLDC;
	}

	/**
	 * @param idLDC the idLDC to set
	 */
	public void setIdLDC(Integer idLDC) {
		this.idLDC = idLDC;
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
	 * @return the idDivision
	 */
	public Integer getIdDivision() {
		return idDivision;
	}

	/**
	 * @param idDivision the idDivision to set
	 */
	public void setIdDivision(Integer idDivision) {
		this.idDivision = idDivision;
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
	 * @return the anioCampania
	 */
	public String getAnioCampania() {
		return anioCampania;
	}

	/**
	 * @param anioCampania the anioCampania to set
	 */
	public void setAnioCampania(String anioCampania) {
		this.anioCampania = anioCampania;
	}

	/**
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}

	/**
	 * @param usuarioActualiza the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}

}
