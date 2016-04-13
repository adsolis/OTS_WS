/**
 *
 *  @since 07/02/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.io.File;

/**
 * @author jessica.leon
 * @since 07/02/2012
 * 
 */
public class ImportadorExcel {

	File file;
	String rutaDirectorio;

	public ImportadorExcel(File file) {
		this.file = file;
	}

	/*public File generarArchivoCSV() {

		File fichero = null;

		if (file.getName().toLowerCase()
				.indexOf(FileType.REPARTO.getFileType()) != -1) {
			String ruta = generarNombreArchivo(file);
			fichero = convertirArchivoCSV(ruta, file.getPath());
		}
		return fichero;
	}*/

	private String generarNombreArchivo(File file) {

		String path = file.getPath();
		String nombreArchivo = path.substring(0, path.length() - 4);
		String rutaCompleta = nombreArchivo + ".csv";
		return rutaCompleta;
	}

	private File convertirArchivoCSV(String rutaCSV, String rutaExcel) {

		ConvertCSV conversor = new ConvertCSV();
		File file = conversor.convertirExcelToCsv(rutaCSV, rutaExcel);
		return file;
	}
}
