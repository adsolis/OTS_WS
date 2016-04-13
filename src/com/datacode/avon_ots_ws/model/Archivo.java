package com.datacode.avon_ots_ws.model;

/**
 * Clase que define propiedades de un archivo para uso del envío de adjuntos en correo electrónico y otros fines
 * @author jorge.torner
 * @since 26-12-2011 
 */
public class Archivo {
	private String nombre;
	private byte[] archivo;
	private String tipoContenido;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public byte[] getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	public String getTipoContenido() {
		return tipoContenido;
	}
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	
}
