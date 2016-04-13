/**
 * Mapeo de las propiedades del Objeto de la BD - PW_REPORTE_TIPO_DESTINATARIO
 * @author brenda.estrada
 * @since 10/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 10/01/2012
 */
public class ReporteTipoDestinatario {
	//Atributos de Mapeo del Objeto
	private Integer idReporteTipoDestinatario = 0;
	private String clave = "";
	//Dependencias
	private Integer idTipoDestinatario = 0;
	private String descTipoDestinatario = "";
	private Integer idReporte;
	
	/**
	 * @return the idReporteTipoDestinatario
	 */
	public Integer getIdReporteTipoDestinatario() {
		return idReporteTipoDestinatario;
	}
	/**
	 * @param idReporteTipoDestinatario the idReporteTipoDestinatario to set
	 */
	public void setIdReporteTipoDestinatario(Integer idReporteTipoDestinatario) {
		this.idReporteTipoDestinatario = idReporteTipoDestinatario;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the idTipoDestinatario
	 */
	public Integer getIdTipoDestinatario() {
		return idTipoDestinatario;
	}
	/**
	 * @param idTipoDestinatario the idTipoDestinatario to set
	 */
	public void setIdTipoDestinatario(Integer idTipoDestinatario) {
		this.idTipoDestinatario = idTipoDestinatario;
	}
	/**
	 * @return the descTipoDestinatario
	 */
	public String getDescTipoDestinatario() {
		return descTipoDestinatario;
	}
	/**
	 * @param descTipoDestinatario the descTipoDestinatario to set
	 */
	public void setDescTipoDestinatario(String descTipoDestinatario) {
		this.descTipoDestinatario = descTipoDestinatario;
	}
	/**
	 * @author jessica.leon
	 * @since 24/02/2013
	 * @return the idReporte
	 */
	public Integer getIdReporte() {
		return idReporte;
	}
	/**
	 * @author jessica.leon
	 * @since 24/02/2013
	 * @param idReporte the idReporte to set
	 */
	public void setIdReporte(Integer idReporte) {
		this.idReporte = idReporte;
	}
}
