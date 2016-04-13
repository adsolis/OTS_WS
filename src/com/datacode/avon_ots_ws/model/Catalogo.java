/**
 *
 *  @since 13/01/2012
 *
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jessica.leon
 * @since 13/01/2012
 *
 */
public class Catalogo {
	
	private int idCarga;
	private String descripcion;
	private String comentarios;
	private String cadenaConexOrigen;
	private String cadenaConexDestino;
	private int tipoOrigen;
	private String nombreOrigen;
	private String rutaOrigen;
	private String nombreDestino;
	private String lastUpdTs;
	private String fechaEjecucion;
	private String estatusEjecucion;
	private String logUltimaEjecucion;
	private String nombreStoreProcedure;
	
	public Catalogo() {
		
		descripcion = "";
		comentarios = "";
		cadenaConexOrigen = "";
		cadenaConexDestino = "";
		nombreOrigen = "";
		rutaOrigen = "";
		nombreDestino = "";
		lastUpdTs = "";
		fechaEjecucion ="";
		logUltimaEjecucion = "";
		
	}
	
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the idCarga
	 */
	public int getIdCarga() {
		return idCarga;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param idCarga the idCarga to set
	 */
	public void setIdCarga(int idCarga) {
		this.idCarga = idCarga;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the cadenaConexOrigen
	 */
	public String getCadenaConexOrigen() {
		return cadenaConexOrigen;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param cadenaConexOrigen the cadenaConexOrigen to set
	 */
	public void setCadenaConexOrigen(String cadenaConexOrigen) {
		this.cadenaConexOrigen = cadenaConexOrigen;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the cadenaConexDestino
	 */
	public String getCadenaConexDestino() {
		return cadenaConexDestino;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param cadenaConexDestino the cadenaConexDestino to set
	 */
	public void setCadenaConexDestino(String cadenaConexDestino) {
		this.cadenaConexDestino = cadenaConexDestino;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the tipoOrigen
	 */
	public int getTipoOrigen() {
		return tipoOrigen;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param tipoOrigen the tipoOrigen to set
	 */
	public void setTipoOrigen(int tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the nombreOrigen
	 */
	public String getNombreOrigen() {
		return nombreOrigen;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param nombreOrigen the nombreOrigen to set
	 */
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the rutaOrigen
	 */
	public String getRutaOrigen() {
		return rutaOrigen;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param rutaOrigen the rutaOrigen to set
	 */
	public void setRutaOrigen(String rutaOrigen) {
		this.rutaOrigen = rutaOrigen;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the nombreDesttino
	 */
	public String getNombreDestino() {
		return nombreDestino;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param nombreDesttino the nombreDesttino to set
	 */
	public void setNombreDestino(String nombreDesttino) {
		this.nombreDestino = nombreDesttino;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the lastUpdTs
	 */
	public String getLastUpdTs() {
		return lastUpdTs;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param lastUpdTs the lastUpdTs to set
	 */
	public void setLastUpdTs(String lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the fechaEjecucion
	 */
	public String getFechaEjecucion() {
		return fechaEjecucion;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param fechaEjecucion the fechaEjecucion to set
	 */
	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the estatusEjecucion
	 */
	public String getEstatusEjecucion() {
		return estatusEjecucion;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param estatusEjecucion the estatusEjecucion to set
	 */
	public void setEstatusEjecucion(String estatusEjecucion) {
		this.estatusEjecucion = estatusEjecucion;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the logUltimaEjecucion
	 */
	public String getLogUltimaEjecucion() {
		return logUltimaEjecucion;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param logUltimaEjecucion the logUltimaEjecucion to set
	 */
	public void setLogUltimaEjecucion(String logUltimaEjecucion) {
		this.logUltimaEjecucion = logUltimaEjecucion;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @return the nombreStoreProcedure
	 */
	public String getNombreStoreProcedure() {
		return nombreStoreProcedure;
	}
	/**
	 * @author jessica.leon
	 * @since 13/01/2012
	 * @param nombreStoreProcedure the nombreStoreProcedure to set
	 */
	public void setNombreStoreProcedure(String nombreStoreProcedure) {
		this.nombreStoreProcedure = nombreStoreProcedure;
	}	
}
