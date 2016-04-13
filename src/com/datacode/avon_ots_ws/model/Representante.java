/**
 *
 *  @since 04/01/2012
 *
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jessica.leon
 * @since 04/01/2012
 *
 */
public class Representante {

	private int idRepresentante;
	private int idZona;
	private String registro;
	private String nombre;
	private String domicilio;
	private String estado;
	private String estatus;
	private double montoActual;
	private double montoPrevio;
	private String telefono;
	private byte pagoEfectivo;
	private byte trabaja;
	private String lastUpdTs;
	private String fechaActualizado;
	private String usuarioActualiza;
	private String poblacion;
	private String descZona = "";
	private Integer idEstatus = 0;
	private String coPostal = "";
	private String siPagoEfectivo = "", siTrabaja = "", cveRuta = "";
	private RepresentantesPorRuta representantePorRuta;
	private String domicilioAlterno = "";
	private Boolean domIncorrecto = false;
	
	public String getDomicilioAlterno() {
		return domicilioAlterno;
	}

	public void setDomicilioAlterno(String domicilioAlterno) {
		this.domicilioAlterno = domicilioAlterno;
	}

	public Representante(){
		representantePorRuta = new RepresentantesPorRuta();
	}
	
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the idRepresentante
	 */
	public int getIdRepresentante() {
		return idRepresentante;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param idRepresentante the idRepresentante to set
	 */
	public void setIdRepresentante(int idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the idZona
	 */
	public int getIdZona() {
		return idZona;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param idZona the idZona to set
	 */
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param registro the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the montoActual
	 */
	public double getMontoActual() {
		return montoActual;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param montoActual the montoActual to set
	 */
	public void setMontoActual(double montoActual) {
		this.montoActual = montoActual;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the montoPrevio
	 */
	public double getMontoPrevio() {
		return montoPrevio;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param montoPrevio the montoPrevio to set
	 */
	public void setMontoPrevio(double montoPrevio) {
		this.montoPrevio = montoPrevio;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the pagoEfectivo
	 */
	public byte getPagoEfectivo() {
		return pagoEfectivo;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param pagoEfectivo the pagoEfectivo to set
	 */
	public void setPagoEfectivo(byte pagoEfectivo) {
		this.pagoEfectivo = pagoEfectivo;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the trabaja
	 */
	public byte getTrabaja() {
		return trabaja;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param trabaja the trabaja to set
	 */
	public void setTrabaja(byte trabaja) {
		this.trabaja = trabaja;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the lastUpdTs
	 */
	public String getLastUpdTs() {
		return lastUpdTs;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param lastUpdTs the lastUpdTs to set
	 */
	public void setLastUpdTs(String lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the fechaActualizado
	 */
	public String getFechaActualizado() {
		return fechaActualizado;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param fechaActualizado the fechaActualizado to set
	 */
	public void setFechaActualizado(String fechaActualizado) {
		this.fechaActualizado = fechaActualizado;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param usuarioActualiza the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}
	/**
	 * @author jessica.leon
	 * @since 04/01/2012
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
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
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}
	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	/**
	 * @return the coPostal
	 */
	public String getCoPostal() {
		return coPostal;
	}
	/**
	 * @param coPostal the coPostal to set
	 */
	public void setCoPostal(String coPostal) {
		this.coPostal = coPostal;
	}
	/**
	 * @return the siPagoEfectivo
	 */
	public String getSiPagoEfectivo() {
		return siPagoEfectivo;
	}
	/**
	 * @param siPagoEfectivo the siPagoEfectivo to set
	 */
	public void setSiPagoEfectivo(String siPagoEfectivo) {
		this.siPagoEfectivo = siPagoEfectivo;
	}
	/**
	 * @return the siTrabaja
	 */
	public String getSiTrabaja() {
		return siTrabaja;
	}
	/**
	 * @param siTrabaja the siTrabaja to set
	 */
	public void setSiTrabaja(String siTrabaja) {
		this.siTrabaja = siTrabaja;
	}
	/**
	 * @return the cveRuta
	 */
	public String getCveRuta() {
		return cveRuta;
	}
	/**
	 * @param cveRuta the cveRuta to set
	 */
	public void setCveRuta(String cveRuta) {
		this.cveRuta = cveRuta;
	}
	/**
	 * @author jessica.leon
	 * @since 20/01/2012
	 * @return the representantePorRuta
	 */
	public RepresentantesPorRuta getRepresentantePorRuta() {
		return representantePorRuta;
	}
	/**
	 * @author jessica.leon
	 * @since 20/01/2012
	 * @param representantePorRuta the representantePorRuta to set
	 */
	public void setRepresentantePorRuta(RepresentantesPorRuta representantePorRuta) {
		this.representantePorRuta = representantePorRuta;
	}

	public Boolean getDomIncorrecto() {
		return domIncorrecto;
	}

	public void setDomIncorrecto(Boolean domIncorrecto) {
		this.domIncorrecto = domIncorrecto;
	}
	
}
