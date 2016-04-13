package com.datacode.avon_ots_ws.model;

public class ModelReparto {

	private String anio;
	private String campania;
	private String zona;
	private String ruta;
	private String poblacion;
	private String tipoRuta;
	private String fechaReparto;
	private String diaReparto; 
	private String nombreChofer;
	private String nombreAyudante;
	private int envOrdenesCReparto;
	private int envOrdenesSReparto;
	private int envPrimerasOrdenes;
	private int envOrdenesTotales;
	private int envCajasTotales;
	private int envCajOrd;
	private int envPremios;
	private long kmInicioRuta;
	private long kmPrimeraEntrega;
	private long kmUltimaEntrega;
	private long kmFinRuta;
	private String hrInicioRuta;
	private String hrPrimeraEntrega;
	private String hrUltimaEntrega;
	private String hrFinRuta;
	private long kmRepartoGlobal;
	private long kmRepartoEfectivo;
	private long kmArrastre;
	private String tiempoRepartoGlobal;
	private String tiempoRepartoEfectivo;
	private String tiempoArrastre;
	private String produMinutos;
	private String produOrdHr;
	private double litros;
	private double kmLitros;
	private int entPrimeraOrden;
	private int entOrdenesEstablecidas;
	private int entCajasTotales;
	private int entPremios;
	private int acpPremios;
	private int acpPrimeraOrden;
	private double acpTotal;
	private int devOrdenes;
	private int devCajas;
	private int devPremios;
	private int difOrdenes;
	private int difCajas;
	private int difPremios;
	private int cauNoViveAhi;
	private int cauNoPago;
	private int cauNoDejoFicha;
	private int cauCambioDom;
	private int cauCerradoTotal;
	private int cauDifEnCobro;
	private int cauFueraZona;
	private int cauNoMetioPedido;
	private int cauDomIncompleto;
	private int cauNoEsperaReparto;
	private int cauExtravioFicha;
	private int cauDiferencia;
	private int cauOtros;
	public int getCauOtros() {
		return cauOtros;
	}
	public void setCauOtros(int cauOtros) {
		this.cauOtros = cauOtros;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getTipoRuta() {
		return tipoRuta;
	}
	public void setTipoRuta(String tipoRuta) {
		this.tipoRuta = tipoRuta;
	}
	public String getFechaReparto() {
		return fechaReparto;
	}
	public void setFechaReparto(String fechaReparto) {
		this.fechaReparto = fechaReparto;
	}
	public String getDiaReparto() {
		return diaReparto;
	}
	public void setDiaReparto(String diaReparto) {
		this.diaReparto = diaReparto;
	}
	public String getNombreChofer() {
		return nombreChofer;
	}
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}
	public String getNombreAyudante() {
		return nombreAyudante;
	}
	public void setNombreAyudante(String nombreAyudante) {
		this.nombreAyudante = nombreAyudante;
	}
	public int getEnvOrdenesCReparto() {
		return envOrdenesCReparto;
	}
	public void setEnvOrdenesCReparto(int envOrdenesCReparto) {
		this.envOrdenesCReparto = envOrdenesCReparto;
	}
	public int getEnvOrdenesSReparto() {
		return envOrdenesSReparto;
	}
	public void setEnvOrdenesSReparto(int envOrdenesSReparto) {
		this.envOrdenesSReparto = envOrdenesSReparto;
	}
	public int getEnvPrimerasOrdenes() {
		return envPrimerasOrdenes;
	}
	public void setEnvPrimerasOrdenes(int envPrimerasOrdenes) {
		this.envPrimerasOrdenes = envPrimerasOrdenes;
	}
	public int getEnvOrdenesTotales() {
		return envOrdenesTotales;
	}
	public void setEnvOrdenesTotales(int envOrdenesTotales) {
		this.envOrdenesTotales = envOrdenesTotales;
	}
	public int getEnvCajasTotales() {
		return envCajasTotales;
	}
	public void setEnvCajasTotales(int envCajasTotales) {
		this.envCajasTotales = envCajasTotales;
	}
	public int getEnvCajOrd() {
		return envCajOrd;
	}
	public void setEnvCajOrd(int envCajOrd) {
		this.envCajOrd = envCajOrd;
	}
	public int getEnvPremios() {
		return envPremios;
	}
	public void setEnvPremios(int envPremios) {
		this.envPremios = envPremios;
	}
	public long getKmInicioRuta() {
		return kmInicioRuta;
	}
	public void setKmInicioRuta(long kmInicioRuta) {
		this.kmInicioRuta = kmInicioRuta;
	}
	public long getKmPrimeraEntrega() {
		return kmPrimeraEntrega;
	}
	public void setKmPrimeraEntrega(long kmPrimeraEntrega) {
		this.kmPrimeraEntrega = kmPrimeraEntrega;
	}
	public long getKmUltimaEntrega() {
		return kmUltimaEntrega;
	}
	public void setKmUltimaEntrega(long kmUltimaEntrega) {
		this.kmUltimaEntrega = kmUltimaEntrega;
	}
	public long getKmFinRuta() {
		return kmFinRuta;
	}
	public void setKmFinRuta(long kmFinRuta) {
		this.kmFinRuta = kmFinRuta;
	}
	public String getHrInicioRuta() {
		return hrInicioRuta;
	}
	public void setHrInicioRuta(String hrInicioRuta) {
		this.hrInicioRuta = hrInicioRuta;
	}
	public String getHrPrimeraEntrega() {
		return hrPrimeraEntrega;
	}
	public void setHrPrimeraEntrega(String hrPrimeraEntrega) {
		this.hrPrimeraEntrega = hrPrimeraEntrega;
	}
	public String getHrUltimaEntrega() {
		return hrUltimaEntrega;
	}
	public void setHrUltimaEntrega(String hrUltimaEntrega) {
		this.hrUltimaEntrega = hrUltimaEntrega;
	}
	public String getHrFinRuta() {
		return hrFinRuta;
	}
	public void setHrFinRuta(String hrFinRuta) {
		this.hrFinRuta = hrFinRuta;
	}
	public long getKmRepartoGlobal() {
		return kmRepartoGlobal;
	}
	public void setKmRepartoGlobal(long kmRepartoGlobal) {
		this.kmRepartoGlobal = kmRepartoGlobal;
	}
	public long getKmRepartoEfectivo() {
		return kmRepartoEfectivo;
	}
	public void setKmRepartoEfectivo(long kmRepartoEfectivo) {
		this.kmRepartoEfectivo = kmRepartoEfectivo;
	}
	public long getKmArrastre() {
		return kmArrastre;
	}
	public void setKmArrastre(long kmArrastre) {
		this.kmArrastre = kmArrastre;
	}
	public String getTiempoRepartoGlobal() {
		return tiempoRepartoGlobal;
	}
	public void setTiempoRepartoGlobal(String tiempoRepartoGlobal) {
		this.tiempoRepartoGlobal = tiempoRepartoGlobal;
	}
	public String getTiempoRepartoEfectivo() {
		return tiempoRepartoEfectivo;
	}
	public void setTiempoRepartoEfectivo(String tiempoRepartoEfectivo) {
		this.tiempoRepartoEfectivo = tiempoRepartoEfectivo;
	}
	public String getTiempoArrastre() {
		return tiempoArrastre;
	}
	public void setTiempoArrastre(String tiempoArrastre) {
		this.tiempoArrastre = tiempoArrastre;
	}
	public String getProduMinutos() {
		return produMinutos;
	}
	public void setProduMinutos(String produMinutos) {
		this.produMinutos = produMinutos;
	}
	public String getProduOrdHr() {
		return produOrdHr;
	}
	public void setProduOrdHr(String produOrdHr) {
		this.produOrdHr = produOrdHr;
	}
	public double getLitros() {
		return litros;
	}
	public void setLitros(double litros) {
		this.litros = litros;
	}
	public double getKmLitros() {
		return kmLitros;
	}
	public void setKmLitros(double kmLitros) {
		this.kmLitros = kmLitros;
	}
	public int getEntPrimeraOrden() {
		return entPrimeraOrden;
	}
	public void setEntPrimeraOrden(int entPrimeraOrden) {
		this.entPrimeraOrden = entPrimeraOrden;
	}
	public int getEntOrdenesEstablecidas() {
		return entOrdenesEstablecidas;
	}
	public void setEntOrdenesEstablecidas(int entOrdenesEstablecidas) {
		this.entOrdenesEstablecidas = entOrdenesEstablecidas;
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
	public void setEntPremios(int entPremios) {
		this.entPremios = entPremios;
	}
	public int getAcpPremios() {
		return acpPremios;
	}
	public void setAcpPremios(int acpPremios) {
		this.acpPremios = acpPremios;
	}
	public int getAcpPrimeraOrden() {
		return acpPrimeraOrden;
	}
	public void setAcpPrimeraOrden(int acpPrimeraOrden) {
		this.acpPrimeraOrden = acpPrimeraOrden;
	}
	public double getAcpTotal() {
		return acpTotal;
	}
	public void setAcpTotal(double acpTotal) {
		this.acpTotal = acpTotal;
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
	public int getCauNoViveAhi() {
		return cauNoViveAhi;
	}
	public void setCauNoViveAhi(int cauNoViveAhi) {
		this.cauNoViveAhi = cauNoViveAhi;
	}
	public int getCauNoPago() {
		return cauNoPago;
	}
	public void setCauNoPago(int cauNoPago) {
		this.cauNoPago = cauNoPago;
	}
	public int getCauNoDejoFicha() {
		return cauNoDejoFicha;
	}
	public void setCauNoDejoFicha(int cauNoDejoFicha) {
		this.cauNoDejoFicha = cauNoDejoFicha;
	}
	public int getCauCambioDom() {
		return cauCambioDom;
	}
	public void setCauCambioDom(int cauCambioDom) {
		this.cauCambioDom = cauCambioDom;
	}
	public int getCauCerradoTotal() {
		return cauCerradoTotal;
	}
	public void setCauCerradoTotal(int cauCerradoTotal) {
		this.cauCerradoTotal = cauCerradoTotal;
	}
	public int getCauDifEnCobro() {
		return cauDifEnCobro;
	}
	public void setCauDifEnCobro(int cauDifEnCobro) {
		this.cauDifEnCobro = cauDifEnCobro;
	}
	public int getCauFueraZona() {
		return cauFueraZona;
	}
	public void setCauFueraZona(int cauFueraZona) {
		this.cauFueraZona = cauFueraZona;
	}
	public int getCauNoMetioPedido() {
		return cauNoMetioPedido;
	}
	public void setCauNoMetioPedido(int cauNoMetioPedido) {
		this.cauNoMetioPedido = cauNoMetioPedido;
	}
	public int getCauDomIncompleto() {
		return cauDomIncompleto;
	}
	public void setCauDomIncompleto(int cauDomIncompleto) {
		this.cauDomIncompleto = cauDomIncompleto;
	}
	public int getCauNoEsperaReparto() {
		return cauNoEsperaReparto;
	}
	public void setCauNoEsperaReparto(int cauNoEsperaReparto) {
		this.cauNoEsperaReparto = cauNoEsperaReparto;
	}
	public int getCauExtravioFicha() {
		return cauExtravioFicha;
	}
	public void setCauExtravioFicha(int cauExtravioFicha) {
		this.cauExtravioFicha = cauExtravioFicha;
	}
	public int getCauDiferencia() {
		return cauDiferencia;
	}
	public void setCauDiferencia(int cauDiferencia) {
		this.cauDiferencia = cauDiferencia;
	}
}
