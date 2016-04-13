package com.datacode.avon_ots_ws.model;

import java.util.Date;
import java.util.List;

public class ModelRepRecepcionCarga {

	private String porteador;
	private String poblacion;
	private String cTransportista;
	private String operador;
	private String campania;
	private String zonaH;
	private String campaniaH;
	private String fecha;
	private String horaLlegadaReal;
	private String horaLlegadaProg;
	private String fechaLlegadaReal;
	private String fechaLlegadaProg;
	
	private String zona;
	private int ordEnvGerZon;
	private int cancel;
	private int relacion;
	private int cods;
	private int cajasOrd;
	private int premios;
	private int cajasPre;
	private int totOrdEnvGerZona;
	private int totCancel;
	private int totRelacion;
	private int totCods;
	private int totCajasOrd;
	private int totPremios;
	private int totCajasPre;
	
	private int panoram;
	private int portaf;
	private int papGerenZonal;
	private int papPortead;
	private int otros;
	private int totCajas;
	private String observaciones;
	//private List<TablaOrdenesXZona> listaOrdenesFaltantes;
	//private List<TablaPapeleo> listaPapeleo;
	private List<TablaCajasMaltratadas> listaCajasMaltratadas;
	private List<TablaCargaRecibida> listaCajaRecibida;
	private List<TablaInfoCodFaltantes> listaCodFaltantes;
	
	
	public String getPorteador() {
		return porteador;
	}
	public void setPorteador(String porteador) {
		this.porteador = porteador;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getcTransportista() {
		return cTransportista;
	}
	public void setcTransportista(String cTransportista) {
		this.cTransportista = cTransportista;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public String getZonaH() {
		return zonaH;
	}
	public void setZonaH(String zonaH) {
		this.zonaH = zonaH;
	}
	public String getCampaniaH() {
		return campaniaH;
	}
	public void setCampaniaH(String campaniaH) {
		this.campaniaH = campaniaH;
	}
	public List<TablaCajasMaltratadas> getListaCajasMaltratadas() {
		return listaCajasMaltratadas;
	}
	public void setListaCajasMaltratadas(
			List<TablaCajasMaltratadas> listaCajasMaltratadas) {
		this.listaCajasMaltratadas = listaCajasMaltratadas;
	}
	public List<TablaCargaRecibida> getListaCajaRecibida() {
		return listaCajaRecibida;
	}
	public void setListaCajaRecibida(List<TablaCargaRecibida> listaCajaRecibida) {
		this.listaCajaRecibida = listaCajaRecibida;
	}
	public List<TablaInfoCodFaltantes> getListaCodFaltantes() {
		return listaCodFaltantes;
	}
	public void setListaCodFaltantes(List<TablaInfoCodFaltantes> listaCodFaltantes) {
		this.listaCodFaltantes = listaCodFaltantes;
	}
	/*public List<TablaOrdenesXZona> getListaOrdenesFaltantes() {
		return listaOrdenesFaltantes;
	}
	public void setListaOrdenesFaltantes(
			List<TablaOrdenesXZona> listaOrdenesFaltantes) {
		this.listaOrdenesFaltantes = listaOrdenesFaltantes;
	}
	public List<TablaPapeleo> getListaPapeleo() {
		return listaPapeleo;
	}
	public void setListaPapeleo(List<TablaPapeleo> listaPapeleo) {
		this.listaPapeleo = listaPapeleo;
	}*/
	public int getOrdEnvGerZon() {
		return ordEnvGerZon;
	}
	public void setOrdEnvGerZon(int ordEnvGerZon) {
		this.ordEnvGerZon = ordEnvGerZon;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public int getRelacion() {
		return relacion;
	}
	public void setRelacion(int relacion) {
		this.relacion = relacion;
	}
	public int getTotOrdEnvGerZona() {
		return totOrdEnvGerZona;
	}
	public void setTotOrdEnvGerZona(int totOrdEnvGerZona) {
		this.totOrdEnvGerZona = totOrdEnvGerZona;
	}
	public int getTotCancel() {
		return totCancel;
	}
	public void setTotCancel(int totCancel) {
		this.totCancel = totCancel;
	}
	public int getTotRelacion() {
		return totRelacion;
	}
	public void setTotRelacion(int totRelacion) {
		this.totRelacion = totRelacion;
	}
	public int getTotCods() {
		return totCods;
	}
	public void setTotCods(int totCods) {
		this.totCods = totCods;
	}
	public int getTotCajasOrd() {
		return totCajasOrd;
	}
	public void setTotCajasOrd(int totCajasOrd) {
		this.totCajasOrd = totCajasOrd;
	}
	public int getTotPremios() {
		return totPremios;
	}
	public void setTotPremios(int totPremios) {
		this.totPremios = totPremios;
	}
	public int getTotCajasPre() {
		return totCajasPre;
	}
	public void setTotCajasPre(int totCajasPre) {
		this.totCajasPre = totCajasPre;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public int getCods() {
		return cods;
	}
	public void setCods(int cods) {
		this.cods = cods;
	}
	public int getCajasOrd() {
		return cajasOrd;
	}
	public void setCajasOrd(int cajasOrd) {
		this.cajasOrd = cajasOrd;
	}
	public int getPremios() {
		return premios;
	}
	public void setPremios(int premios) {
		this.premios = premios;
	}
	public int getCajasPre() {
		return cajasPre;
	}
	public void setCajasPre(int cajasPre) {
		this.cajasPre = cajasPre;
	}
	public int getTotCajas() {
		return totCajas;
	}
	public void setTotCajas(int totCajas) {
		this.totCajas = totCajas;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @return the horaLlegadaReal
	 */
	public String getHoraLlegadaReal() {
		return horaLlegadaReal;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @param horaLlegadaReal the horaLlegadaReal to set
	 */
	public void setHoraLlegadaReal(String horaLlegadaReal) {
		this.horaLlegadaReal = horaLlegadaReal;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @return the horaLlegadaProg
	 */
	public String getHoraLlegadaProg() {
		return horaLlegadaProg;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @param horaLlegadaProg the horaLlegadaProg to set
	 */
	public void setHoraLlegadaProg(String horaLlegadaProg) {
		this.horaLlegadaProg = horaLlegadaProg;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @return the fechaLlegadaReal
	 */
	public String getFechaLlegadaReal() {
		return fechaLlegadaReal;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @param fechaLlegadaReal the fechaLlegadaReal to set
	 */
	public void setFechaLlegadaReal(String fechaLlegadaReal) {
		this.fechaLlegadaReal = fechaLlegadaReal;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @return the fechaLlegadaProg
	 */
	public String getFechaLlegadaProg() {
		return fechaLlegadaProg;
	}
	/**
	 * @author jessica.leon
	 * @since 09/07/2012
	 * @param fechaLlegadaProg the fechaLlegadaProg to set
	 */
	public void setFechaLlegadaProg(String fechaLlegadaProg) {
		this.fechaLlegadaProg = fechaLlegadaProg;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @return the panoram
	 */
	public int getPanoram() {
		return panoram;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @param panoram the panoram to set
	 */
	public void setPanoram(int panoram) {
		this.panoram = panoram;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @return the portaf
	 */
	public int getPortaf() {
		return portaf;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @param portaf the portaf to set
	 */
	public void setPortaf(int portaf) {
		this.portaf = portaf;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @return the papGerenZonal
	 */
	public int getPapGerenZonal() {
		return papGerenZonal;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @param papGerenZonal the papGerenZonal to set
	 */
	public void setPapGerenZonal(int papGerenZonal) {
		this.papGerenZonal = papGerenZonal;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @return the papPortead
	 */
	public int getPapPortead() {
		return papPortead;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @param papPortead the papPortead to set
	 */
	public void setPapPortead(int papPortead) {
		this.papPortead = papPortead;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @return the otros
	 */
	public int getOtros() {
		return otros;
	}
	/**
	 * @author jessica.leon
	 * @since 10/07/2012
	 * @param otros the otros to set
	 */
	public void setOtros(int otros) {
		this.otros = otros;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
