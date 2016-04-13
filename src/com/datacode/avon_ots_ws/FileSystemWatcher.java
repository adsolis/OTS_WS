/**
 *
 *  @since 09/02/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.io.IOException;
import java.util.List;

import name.pachler.nio.file.*;

/**
 * @author jessica.leon
 * @since 09/02/2012
 * 
 */
public class FileSystemWatcher {

	private String pathDirectorio;

	private void cargarConfiguraciones() {

		String archivoConfig = "AvonWsApp.properties";
		AccesoArchivos accesoArch = new AccesoArchivos();

		if (accesoArch.CargarArchivoPropiedades(archivoConfig)) {
			pathDirectorio = accesoArch.ObtenerPropiedad("rutaDirectorio")
					.toString();
		}
	}

	private void iniciarMonitoreo() {

		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(pathDirectorio);

		WatchKey key = null;
		try {
			key = path.register(watchService,
					StandardWatchEventKind.ENTRY_CREATE,
					StandardWatchEventKind.ENTRY_DELETE);
			System.err.print("Monitoreo de directorio iniciado");
		} catch (UnsupportedOperationException uox) {
			System.err.println("file watching not supported!");
		} catch (IOException iox) {
			System.err.println("I/O errors");
		}

		for (;;) {

			WatchKey signalledKey;
			try {
				signalledKey = watchService.take();
			} catch (InterruptedException ix) {
				continue;
			} catch (ClosedWatchServiceException cwse) {
				System.err.println("watch service closed, terminating.");
				break;
			}

			List<WatchEvent<?>> list = signalledKey.pollEvents();
			signalledKey.reset();

			for (WatchEvent e : list) {
				String message = "";
				if (e.kind() == StandardWatchEventKind.ENTRY_CREATE) {
					Path context = (Path) e.context();
					message = context.toString() + " created";
					System.err.println(message);
					OperacionesActualizacionCatalogoArchivo actualizacion = new OperacionesActualizacionCatalogoArchivo(
							pathDirectorio, context.toString());
					actualizacion.realizarActualizacion();
					actualizacion = null;
				} else if (e.kind() == StandardWatchEventKind.ENTRY_DELETE) {
					Path context = (Path) e.context();
					message = context.toString() + " deleted";
					System.err.println(message);
				} else if (e.kind() == StandardWatchEventKind.OVERFLOW) {
					message = "OVERFLOW: more changes happened than we could retreive";
					System.err.println(message);
				}
			}
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
}
