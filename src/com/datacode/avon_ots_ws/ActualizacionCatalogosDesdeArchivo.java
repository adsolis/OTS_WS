/**
 *
 *  @since 10/02/2012
 *
 */
package com.datacode.avon_ots_ws;

/**
 * @author jessica.leon
 * @since 10/02/2012
 *
 */
public class ActualizacionCatalogosDesdeArchivo {
	
	public void iniciarActualizacionViaMonitoreo(){
		new FileSystemWatcher().iniciarCargaCatalogos();
	}
	
	public static void main(String[] args) {
		new ActualizacionCatalogosDesdeArchivo().iniciarActualizacionViaMonitoreo();
	}
}
