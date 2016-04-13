/**
 *
 *  @since 14/08/2012
 *
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jessica.leon
 * 
 */
public class ModelReporteResumen {
	private String campania;
	private double productividadDescarga;
	private double productividadEnrutado;
	private int rutasNormales;
	private int rutasEspeciales;
	private int ordenConRepartoDir;
	private int ordenSinRepartoDir;
	private int primerasOrdenes;
	private int cajas;
	private int premios;
	private int bultosMatPromocional;
	private double factorCajaOrden;

	// REPARTO
	private String repInicioRutaPromedio;
	private String repPrimeraVisitaProm;
	private String repUltimaVisitaPromedio;
	private String repFinRutaPromedio;
	private double repKmGlobal;
	private double repKmEfectivo;
	private String repTiempoPromVisita;
	private double repProductividadOrHr;
	private double repRendimientoProm;

	// ENTREGADOS EN REPARTO
	private int entPrimeraOrden;
	private int entOrdenPtoEntrega;
	private int entOrdenEstablecidas;
	private int entCajasTotales;
	private int entPremios;
	private int entMaterialPromocinal;

	// % ACEPTACION
	private double aceptacionPrimeraOrden;
	private double aceptacionPremios;
	private double aceptacionTotal;

	// DEVOLUCION
	private int devOrdenes;
	private int devCajas;
	private int devPremios;
	private int devMatPromocional;
	
	// DIFERENCIALES
	private int difOrdenes;
	private int difCajas;
	private int difPremios;
	private int difMatPromocional;
	
	// CAUSAS DE NO ACEPTACIÓN EN REPARTO
	private double cauNoViveAhi;
	private double cauNoPago;
	private double cauNoDejoFicha;
	private double cauCambioDom;
	private double cauCerradoTotal;
	private double cauDifEnCobro;
	private double cauFueraZona;
	private double cauNoMetioPedido;
	private double cauDomIncompleto;
	private double cauNoEsperaReparto;
	private double cauExtravioFicha;
	private double cauOtro;
	private double cauTotal;

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public double getProductividadDescarga() {
		return productividadDescarga;
	}

	public void setProductividadDescarga(double productividadDescarga) {
		this.productividadDescarga = productividadDescarga;
	}

	public double getProductividadEnrutado() {
		return productividadEnrutado;
	}

	public void setProductividadEnrutado(double productividadEnrutado) {
		this.productividadEnrutado = productividadEnrutado;
	}

	public int getRutasNormales() {
		return rutasNormales;
	}

	public void setRutasNormales(int rutasNormales) {
		this.rutasNormales = rutasNormales;
	}

	public int getRutasEspeciales() {
		return rutasEspeciales;
	}

	public void setRutasEspeciales(int rutasEspeciales) {
		this.rutasEspeciales = rutasEspeciales;
	}

	public int getOrdenConRepartoDir() {
		return ordenConRepartoDir;
	}

	public void setOrdenConRepartoDir(int ordenConRepartoDir) {
		this.ordenConRepartoDir = ordenConRepartoDir;
	}

	public int getOrdenSinRepartoDir() {
		return ordenSinRepartoDir;
	}

	public void setOrdenSinRepartoDir(int ordenSinRepartoDir) {
		this.ordenSinRepartoDir = ordenSinRepartoDir;
	}

	public int getPrimerasOrdenes() {
		return primerasOrdenes;
	}

	public void setPrimerasOrdenes(int primerasOrdenes) {
		this.primerasOrdenes = primerasOrdenes;
	}

	public int getCajas() {
		return cajas;
	}

	public void setCajas(int cajas) {
		this.cajas = cajas;
	}

	public int getPremios() {
		return premios;
	}

	public void setPremios(int premios) {
		this.premios = premios;
	}

	public int getBultosMatPromocional() {
		return bultosMatPromocional;
	}

	public void setBultosMatPromocional(int bultosMatPromocional) {
		this.bultosMatPromocional = bultosMatPromocional;
	}

	public double getFactorCajaOrden() {
		return factorCajaOrden;
	}

	public void setFactorCajaOrden(double factorCajaOrden) {
		this.factorCajaOrden = factorCajaOrden;
	}

	public String getRepInicioRutaPromedio() {
		return repInicioRutaPromedio;
	}

	public void setRepInicioRutaPromedio(String repInicioRutaPromedio) {
		this.repInicioRutaPromedio = repInicioRutaPromedio;
	}

	public String getRepPrimeraVisitaProm() {
		return repPrimeraVisitaProm;
	}

	public void setRepPrimeraVisitaProm(String repPrimeraVisitaProm) {
		this.repPrimeraVisitaProm = repPrimeraVisitaProm;
	}

	public String getRepUltimaVisitaPromedio() {
		return repUltimaVisitaPromedio;
	}

	public void setRepUltimaVisitaPromedio(String repUltimaVisitaPromedio) {
		this.repUltimaVisitaPromedio = repUltimaVisitaPromedio;
	}

	public String getRepFinRutaPromedio() {
		return repFinRutaPromedio;
	}

	public void setRepFinRutaPromedio(String repFinRutaPromedio) {
		this.repFinRutaPromedio = repFinRutaPromedio;
	}

	public double getRepKmGlobal() {
		return repKmGlobal;
	}

	public void setRepKmGlobal(double repKmGlobal) {
		this.repKmGlobal = repKmGlobal;
	}

	public double getRepKmEfectivo() {
		return repKmEfectivo;
	}

	public void setRepKmEfectivo(double repKmEfectivo) {
		this.repKmEfectivo = repKmEfectivo;
	}

	public String getRepTiempoPromVisita() {
		return repTiempoPromVisita;
	}

	public void setRepTiempoPromVisita(String repTiempoPromVisita) {
		this.repTiempoPromVisita = repTiempoPromVisita;
	}

	public double getRepProductividadOrHr() {
		return repProductividadOrHr;
	}

	public void setRepProductividadOrHr(double repProductividadOrHr) {
		this.repProductividadOrHr = repProductividadOrHr;
	}

	public double getRepRendimientoProm() {
		return repRendimientoProm;
	}

	public void setRepRendimientoProm(double repRendimientoProm) {
		this.repRendimientoProm = repRendimientoProm;
	}

	public int getEntPrimeraOrden() {
		return entPrimeraOrden;
	}

	public void setEntPrimeraOrden(int entPrimeraOrden) {
		this.entPrimeraOrden = entPrimeraOrden;
	}

	public int getEntOrdenPtoEntrega() {
		return entOrdenPtoEntrega;
	}

	public void setEntOrdenPtoEntrega(int entOrdenPtoEntrega) {
		this.entOrdenPtoEntrega = entOrdenPtoEntrega;
	}

	public int getEntOrdenEstablecidas() {
		return entOrdenEstablecidas;
	}

	public void setEntOrdenEstablecidas(int entOrdenEstablecidas) {
		this.entOrdenEstablecidas = entOrdenEstablecidas;
	}

	public int getEntCajasTotales() {
		return entCajasTotales;
	}

	public void setEntCajasTotales(int entCajasTotales) {
		this.entCajasTotales = entCajasTotales;
	}

	public int getEntPremios() {
		return entPremios;
	}

	public void setEntPremios(int entPremos) {
		this.entPremios = entPremos;
	}

	public int getEntMaterialPromocinal() {
		return entMaterialPromocinal;
	}

	public void setEntMaterialPromocinal(int entMaterialPromocinal) {
		this.entMaterialPromocinal = entMaterialPromocinal;
	}

	public double getAceptacionPrimeraOrden() {
		return aceptacionPrimeraOrden;
	}

	public void setAceptacionPrimeraOrden(double aceptacionPrimeraOrden) {
		this.aceptacionPrimeraOrden = aceptacionPrimeraOrden;
	}

	public double getAceptacionPremios() {
		return aceptacionPremios;
	}

	public void setAceptacionPremios(double aceptacionPremios) {
		this.aceptacionPremios = aceptacionPremios;
	}

	public double getAceptacionTotal() {
		return aceptacionTotal;
	}

	public void setAceptacionTotal(double aceptacionTotal) {
		this.aceptacionTotal = aceptacionTotal;
	}

	public int getDevOrdenes() {
		return devOrdenes;
	}

	public void setDevOrdenes(int devOrdenes) {
		this.devOrdenes = devOrdenes;
	}

	public int getDevCajas() {
		return devCajas;
	}

	public void setDevCajas(int devCajas) {
		this.devCajas = devCajas;
	}

	public int getDevPremios() {
		return devPremios;
	}

	public void setDevPremios(int devPremios) {
		this.devPremios = devPremios;
	}

	public int getDevMatPromocional() {
		return devMatPromocional;
	}

	public void setDevMatPromocional(int devMatPromocional) {
		this.devMatPromocional = devMatPromocional;
	}

	public int getDifOrdenes() {
		return difOrdenes;
	}

	public void setDifOrdenes(int difOrdenes) {
		this.difOrdenes = difOrdenes;
	}

	public int getDifCajas() {
		return difCajas;
	}

	public void setDifCajas(int difCajas) {
		this.difCajas = difCajas;
	}

	public int getDifPremios() {
		return difPremios;
	}

	public void setDifPremios(int difPremios) {
		this.difPremios = difPremios;
	}

	public int getDifMatPromocional() {
		return difMatPromocional;
	}

	public void setDifMatPromocional(int difMatPromocional) {
		this.difMatPromocional = difMatPromocional;
	}

	public double getCauNoViveAhi() {
		return cauNoViveAhi;
	}

	public void setCauNoViveAhi(double cauNoViveAhi) {
		this.cauNoViveAhi = cauNoViveAhi;
	}

	public double getCauNoPago() {
		return cauNoPago;
	}

	public void setCauNoPago(double cauNoPago) {
		this.cauNoPago = cauNoPago;
	}

	public double getCauNoDejoFicha() {
		return cauNoDejoFicha;
	}

	public void setCauNoDejoFicha(double cauNoDejoFicha) {
		this.cauNoDejoFicha = cauNoDejoFicha;
	}

	public double getCauCambioDom() {
		return cauCambioDom;
	}

	public void setCauCambioDom(double cauCambioDom) {
		this.cauCambioDom = cauCambioDom;
	}

	public double getCauCerradoTotal() {
		return cauCerradoTotal;
	}

	public void setCauCerradoTotal(double cauCerradoTotal) {
		this.cauCerradoTotal = cauCerradoTotal;
	}

	public double getCauDifEnCobro() {
		return cauDifEnCobro;
	}

	public void setCauDifEnCobro(double cauDifEnCobro) {
		this.cauDifEnCobro = cauDifEnCobro;
	}

	public double getCauFueraZona() {
		return cauFueraZona;
	}

	public void setCauFueraZona(double cauFueraZona) {
		this.cauFueraZona = cauFueraZona;
	}

	public double getCauNoMetioPedido() {
		return cauNoMetioPedido;
	}

	public void setCauNoMetioPedido(double cauNoMetioPedido) {
		this.cauNoMetioPedido = cauNoMetioPedido;
	}

	public double getCauDomIncompleto() {
		return cauDomIncompleto;
	}

	public void setCauDomIncompleto(double cauDomIncompleto) {
		this.cauDomIncompleto = cauDomIncompleto;
	}

	public double getCauNoEsperaReparto() {
		return cauNoEsperaReparto;
	}

	public void setCauNoEsperaReparto(double cauNoEsperaReparto) {
		this.cauNoEsperaReparto = cauNoEsperaReparto;
	}

	public double getCauExtravioFicha() {
		return cauExtravioFicha;
	}

	public void setCauExtravioFicha(double cauExtravioFicha) {
		this.cauExtravioFicha = cauExtravioFicha;
	}

	public double getCauOtro() {
		return cauOtro;
	}

	public void setCauOtro(double cauOtro) {
		this.cauOtro = cauOtro;
	}

	public double getCauTotal() {
		return cauTotal;
	}

	public void setCauTotal(double cauTotal) {
		this.cauTotal = cauTotal;
	}
}
