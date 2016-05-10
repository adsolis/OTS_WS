package com.datacode.avon_ots_ws;

import java.util.Properties;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import javax.activation.MimetypesFileTypeMap;
/**
 * Clase de m�todos para acceso a archivos escritura/lectura
 * @author jorge.torner
 * @since 22-12-2011 
 */
public class AccesoArchivos {
	//Propiedades que se cargan al usar el m�todo CargarArchivoPropiedades
	Properties propiedades = null;
	//Nombre del archivo de propiedades cargado
	String archivoPropiedades = null;
	
	/**
	 * M�todo que carga un archivo de propiedades agreg�ndolas al objeto propiedades
	 * @author jorge.torner
	 * @since 22-12-2011
	 * @param nombreArchivo -Nombre del archivo a cargar con ruta si es necesario.
	 * @return Boolean Verdadero si se carga exitosamente el archivo
	 */
	public Boolean CargarArchivoPropiedades(String nombreArchivo){
		propiedades = new Properties();
		archivoPropiedades = nombreArchivo;

		//Tratamos de cargar el properties primero con getResourceAsStream, �ste se usa ya en el deploy del aar
		try{
			propiedades.load(this.getClass().getResourceAsStream("/" + nombreArchivo));
		}
		catch(NullPointerException p){
			//Si no se logra cargar entonces se intenta con InputStream
			InputStream fis = null;
			try{
				fis = new FileInputStream(nombreArchivo);
			}
			catch(FileNotFoundException e){
				System.out.println("Error al intentar leer el archivo de configuraci�n: " + e.getMessage());
				return false;
			}
			try{
				propiedades.load(fis);
				fis.close();
			}
			catch(IOException e){
				System.out.println("Error al intentar leer el archivo de configuraci�n: " + e.getMessage());
				return false;
			}
		} catch (IOException e) {
			System.out.println("Error al intentar leer el archivo de configuraci�n: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * M�todo para obtener una propiedad de las propiedades cargadas anteriormente con el m�todo CargarArchivoPropiedades
	 * @author jorge.torner
	 * @since 22-12-2011 
	 * @param propiedad -Nombre de la propiedad a obtener su valor
	 * @return String Valor de la propiedad
	 */
	public String ObtenerPropiedad(String propiedad){
		if(propiedades != null){
			String valor = propiedades.getProperty(propiedad);
			if(valor == null)
			{
				System.out.println("No existe la propiedad " + propiedad + " en el archivo " + archivoPropiedades);
			}
			return valor;
		}
		else
			return null;
	}
	
	/**
	 * Lee un archivo desde una ruta espec�fica y lo devuelve en arreglo de bytes.
	 * @author jorge.torner
	 * @since 27-12-2011
	 * @param rutaArchivo Ruta y nombre del archivo
	 * @return Regresa el archivo en arreglo de bytes
	 */
	public static byte[] LeerArchivoArreglo(String rutaArchivo){
		byte[] fileContent = null;
		File file = new File(rutaArchivo);

		try
		{
			//create FileInputStream object
			FileInputStream fin = new FileInputStream(file);

			/*
			 * Create byte array large enough to hold the content of the file.
			 * Use File.length to determine size of the file in bytes.
			 */
			fileContent = new byte[(int)file.length()];

			/*
			 * To read content of the file in byte array, use
			 * int read(byte[] byteArray) method of java FileInputStream class.
			 *
			 */
			fin.read(fileContent);

		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found" + e);
		}
		catch(IOException ioe)
		{
			System.out.println("Exception while reading the file " + ioe);
		}
		
		return fileContent;
	}
	
	/**
	 * M�todo para obtener el mime type de un archivo para el env�o por correo electr�nico o cualquier manejo posterior
	 * @author jorge.torner
	 * @since 27-12-2011
	 * @param rutaArchivo Ruta y nombre del archivo
	 * @return Regresa una cadena con el mime type
	 */
	public static String ObtenerMimeType(String rutaArchivo) {
	    File f = new File(rutaArchivo);
	    
	    return new MimetypesFileTypeMap().getContentType(f);
	  }
	

	public static void GuardaArchivoLog(String texto){
		AccesoArchivos accesoArch = new AccesoArchivos();
		if(accesoArch.CargarArchivoPropiedades("AvonWsApp.properties")){
			String ruta = accesoArch.ObtenerPropiedad("rutaArchivoLog");

			try{
				// Create file 
				FileWriter fstream = new FileWriter(ruta + "/AvonWsLog.txt", true);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(Utils.ObtenerFechaActual(Utils.formatoFechaCortaHoraLarga) + " - " +  texto);
				out.newLine();
				//Close the output stream
				out.close();
			}catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		
		}
	}
}
