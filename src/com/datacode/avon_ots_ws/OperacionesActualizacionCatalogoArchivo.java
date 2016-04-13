/**
 *
 *  @since 30/01/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.Catalogo;

/**
 * @author jessica.leon
 * @since 30/01/2012
 * 
 */
public class OperacionesActualizacionCatalogoArchivo {

	private TransaccionesCatalogo transaccionesCatalogo;
	private Catalogo catalogo;
	private File file;
	private String rutaDirectorio;
	private String nombreArchivo;
	private String logUltEjecucion;
	private String fecha;
	private List<File> files;

	public OperacionesActualizacionCatalogoArchivo(String rutaDirectorio,
			String nombreArchivo) {

		this.rutaDirectorio = rutaDirectorio;
		this.nombreArchivo = nombreArchivo;
		files = new ArrayList<File>();
	}

	public void realizarActualizacion() {

		transaccionesCatalogo = new TransaccionesCatalogo();
		file = new File(rutaDirectorio + "\\" + nombreArchivo);
		if (file.exists() && verificarExtensionArchivo(file)) {
			agregarArchivo(file);
			identificarArchivo(file);
		}
		/*else if(file.exists() && esArchivoExcel()){
			agregarArchivo(file);
			ImportadorExcel importador = new ImportadorExcel(file);
			File fichero = importador.generarArchivoCSV();
			if(fichero !=null && fichero.exists()){
				agregarArchivo(fichero);
				identificarArchivo(fichero);
			}
		}*/
		eliminarArchivosDeDirectorio();
	}

	private void agregarArchivo(File file){
		files.add(file);
	}
	
	private boolean esArchivoExcel(){
		FilterFile filter = new FilterFile();
		return filter.isExcel(file);
	}
	
	private boolean verificarExtensionArchivo(File file) {
		FileFilter filter = new FilterFile();
		return filter.accept(file);
	}

	private void identificarArchivo(File file) {

		for (FileType type : FileType.values()) {
			if (file.getName().toLowerCase().indexOf(type.getFileType()) != -1) {
				consultarParametrosDeCarga(type.getDescripcion());
				if (catalogo != null) {
					insertarTablaTemporal(catalogo.getNombreDestino(),
							catalogo.getRutaOrigen(), file.getName());
					ejecutarProcedimientoAlmacenado(
							catalogo.getNombreStoreProcedure(),
							catalogo.getIdCarga());
				}
			}
		}
	}

	private void consultarParametrosDeCarga(String descripcion) {
		catalogo = transaccionesCatalogo
				.consultarParametrosCargaPorDescripcion(descripcion);
	}

	private void insertarTablaTemporal(String nombreDestino, String rutaOrigen,
			String nombreArchivo) {
		fecha = Utils.ObtenerFechaActual("yyyy/MM/dd HH:mm:ss");
		logUltEjecucion = "Inicia proceso de insercion de datos en tabla temporal "
				+ nombreDestino;
		transaccionesCatalogo.cerrarCarga(1, logUltEjecucion, fecha);
		transaccionesCatalogo.eliminarRegistrosTablaTemporal(nombreDestino);
		transaccionesCatalogo.ejecutarProcedimientoInsercion(nombreDestino,
				rutaOrigen, nombreArchivo);
	}

	private void ejecutarProcedimientoAlmacenado(String nombreProcedimiento,
			int idCarga) {
		transaccionesCatalogo.ejecutarStoreProcedure(nombreProcedimiento,
				fecha, idCarga,Utils.CargarConfiguracion().getIdLDC(),20);
	}

	private void eliminarArchivosDeDirectorio() {	
		for (File file : files){
			file.delete();
		}
	}
}
