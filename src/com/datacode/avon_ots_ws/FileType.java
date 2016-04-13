/**
 *
 *  @since 01/02/2012
 *
 */
package com.datacode.avon_ots_ws;

/**
 * @author jessica.leon
 * @since 01/02/2012
 *
 */
public enum FileType {
	
	UNITARIO("unitarios","UNITARIOS"),
	PREMIOS("premios","PREMIOS"),
	//REPARTO("","PROGRAMA DE REPARTO"),
	LLEGADA_PROGRAMADA("sfz","LLEGADA PROGRAMADA"),
	CAMPANIAS("zone_calendar","CAMPAÑAS"),
	RELACION_RUTAS("relacionrutas","RELACION RUTAS");
	
	private String fileType;
	private String descripcion;
	 
    private FileType(String fileType,String descripcion) {
    this.fileType = fileType;
    this.descripcion = descripcion;
    }

	/**
	 * @author jessica.leon
	 * @since 16/01/2012
	 * @return the dataType
	 */
	public String getFileType() {
		return fileType;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
}
