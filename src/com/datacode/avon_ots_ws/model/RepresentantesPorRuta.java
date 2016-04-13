/**
 * Mapeo de las propiedades del Objeto de la BD - PW_REPRESENTANTES_POR_RUTA
 * @author brenda.estrada
 * @since 11/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 11/01/2012
 * @category Map
 */
public class RepresentantesPorRuta {
	//Atributos obj
	private Integer idRepresentanteRuta;
	private String fAlta = "";
	private String fBaja = "";
	private String seqEntregaAnterior ="";
	private String seqEntregaReciente ="";
	//Dependencias
	private Integer idRepresentante = 0;
	private String descRepresentante = "";
	private Integer idRuta = 0;
	private String descRuta = "";
	
	private String idZona = "";
	private String descZona = "";

	/**
	 * @return the idRepresentanteRuta
	 */
	public Integer getIdRepresentanteRuta() {
		return idRepresentanteRuta;
	}


	/**
	 * @param idRepresentanteRuta the idRepresentanteRuta to set
	 */
	public void setIdRepresentanteRuta(Integer idRepresentanteRuta) {
		this.idRepresentanteRuta = idRepresentanteRuta;
	}


	/**
	 * @return the fAlta
	 */
	public String getfAlta() {
		return fAlta;
	}


	/**
	 * @param fAlta the fAlta to set
	 */
	public void setfAlta(String fAlta) {
		this.fAlta = fAlta;
	}


	/**
	 * @return the fBaja
	 */
	public String getfBaja() {
		return fBaja;
	}


	/**
	 * @param fBaja the fBaja to set
	 */
	public void setfBaja(String fBaja) {
		this.fBaja = fBaja;
	}


	/**
	 * @return the seqEntregaAnterior
	 */
	public String getSeqEntregaAnterior() {
		return seqEntregaAnterior;
	}


	/**
	 * @param seqEntregaAnterior the seqEntregaAnterior to set
	 */
	public void setSeqEntregaAnterior(String seqEntregaAnterior) {
		this.seqEntregaAnterior = seqEntregaAnterior;
	}


	/**
	 * @return the seqEntregaReciente
	 */
	public String getSeqEntregaReciente() {
		return seqEntregaReciente;
	}


	/**
	 * @param seqEntregaReciente the seqEntregaReciente to set
	 */
	public void setSeqEntregaReciente(String seqEntregaReciente) {
		this.seqEntregaReciente = seqEntregaReciente;
	}


	/**
	 * @return the idRepresentante
	 */
	public Integer getIdRepresentante() {
		return idRepresentante;
	}


	/**
	 * @param idRepresentante the idRepresentante to set
	 */
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}


	/**
	 * @return the descRepresentante
	 */
	public String getDescRepresentante() {
		return descRepresentante;
	}


	/**
	 * @param descRepresentante the descRepresentante to set
	 */
	public void setDescRepresentante(String descRepresentante) {
		this.descRepresentante = descRepresentante;
	}


	/**
	 * @return the idRuta
	 */
	public Integer getIdRuta() {
		return idRuta;
	}


	/**
	 * @param idRuta the idRuta to set
	 */
	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}


	/**
	 * @return the descRuta
	 */
	public String getDescRuta() {
		return descRuta;
	}


	/**
	 * @param descRuta the descRuta to set
	 */
	public void setDescRuta(String descRuta) {
		this.descRuta = descRuta;
	}


	public String getIdZona() {
		return idZona;
	}


	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}


	public String getDescZona() {
		return descZona;
	}


	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}

}
