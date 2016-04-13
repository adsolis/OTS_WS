/**
 *
 *  @since 31/01/2012
 *
 */
package com.datacode.avon_ots_ws;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;

/**
 * @author jessica.leon
 * @since 31/01/2012
 * 
 */
public class MonitoreoDirectorio{

	private String pathDirectorio;
	private int threadSleepTime;
	private int mascara;
	private boolean watchSubtree;
	private int watchID;

	private void cargarConfiguraciones() {

		String archivoConfig = "AvonWsApp.properties";
		AccesoArchivos accesoArch = new AccesoArchivos();

		if (accesoArch.CargarArchivoPropiedades(archivoConfig)) {

			pathDirectorio = accesoArch.ObtenerPropiedad("rutaDirectorio").toString();
			watchSubtree = Boolean.valueOf(accesoArch.ObtenerPropiedad("watchSubtree"));
			threadSleepTime = Integer.valueOf(accesoArch.ObtenerPropiedad("threadSleepTime"));
		}
		mascara = JNotify.FILE_CREATED | JNotify.FILE_DELETED | JNotify.FILE_MODIFIED;
	}

	private void iniciarMonitoreo() throws Exception {

		watchID = JNotify.addWatch(pathDirectorio, mascara, watchSubtree,new ListenerMonitoreo());
		System.err.print("Observador agregado--> " + watchID);
		Thread.sleep(threadSleepTime);
		System.err.print("Observador descansando--> " + watchID);
		Thread.currentThread();
	}
	
	public void detenerCargaCatalogos(){
		try {
			watchID = 1;
			boolean removido = JNotify.removeWatch(watchID);
			if(removido){
				System.err.print("Observador removido--> " + watchID);
			}
			else{
				System.err.print("El observador no ha sido removido--> " + watchID);
			}
				

		} catch (JNotifyException e) {
			e.printStackTrace();
		}
	}

	public void iniciarCargaCatalogos() {
		try {
			cargarConfiguraciones();
			iniciarMonitoreo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new MonitoreoDirectorio().iniciarCargaCatalogos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ListenerMonitoreo implements IMonitoreo {
		public void fileRenamed(int wd, String rootPath, String oldName,
				String newName) {
			print("renamed " + rootPath + " : " + oldName + " -> " + newName);
		}

		public void fileModified(int wd, String rootPath, String name) {
			print("modified " + rootPath + " : " + name);
		}

		public void fileDeleted(int wd, String rootPath, String name) {
			print("deleted " + rootPath + " : " + name);
		}

		public void fileCreated(int wd, String rootPath, String name) {
			print("created " + rootPath + " : " + name);
			OperacionesActualizacionCatalogoArchivo actualizacion = new OperacionesActualizacionCatalogoArchivo(rootPath,name);
			actualizacion.realizarActualizacion();
			actualizacion = null;
		}

		void print(String msg) {
			System.err.println(msg);
		}
	}
}
