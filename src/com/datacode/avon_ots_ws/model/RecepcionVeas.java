/**
 *
 *  @since 29/12/2011
 *
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author jessica.leon
 * @since 29/12/2011
 *
 */
public class RecepcionVeas {
	
	private String idCampania;
	private String idZona;
	private String idUsuario;
	private String fechaRecepcionVeas;
    
	public RecepcionVeas(){
		
		idCampania = null;
		idZona = null;
		idUsuario = null;
		fechaRecepcionVeas = null;			
	}

	/**
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @return the idCampania
	 */
	public String getIdCampania() {
		return idCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @param idCampania the idCampania to set
	 */
	public void setIdCampania(String idCampania) {
		this.idCampania = idCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @return the idZona
	 */
	public String getIdZona() {
		return idZona;
	}

	/**
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @param idZona the idZona to set
	 */
	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}

	/**
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @return the fechaRecepcionVeas
	 */
	public String getFechaRecepcionVeas() {
		return fechaRecepcionVeas;
	}

	/**
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @param fechaRecepcionVeas the fechaRecepcionVeas to set
	 */
	public void setFechaRecepcionVeas(String fechaRecepcionVeas) {
		this.fechaRecepcionVeas = fechaRecepcionVeas;
	}
	
	
}
