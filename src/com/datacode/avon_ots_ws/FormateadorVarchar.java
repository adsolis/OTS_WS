/**
 *
 *  @since 16/01/2012
 *
 */
package com.datacode.avon_ots_ws;

/**
 * Clase encargada de dar formato a los
 * valores de tipo varchar.
 * 
 * @author jessica.leon
 * @since 16/01/2012
 *
 */
public class FormateadorVarchar implements FormateadorDato {

	/* (non-Javadoc)
	 * @see com.datacode.avon_ots_ws.FormateadorDato#formatearDato(java.lang.Object)
	 */
	@Override
	public Object formatearDato(Object dato) {
		
		String formato = (String) dato;
		String comillaSimple = "'";
		
		if(formato.contains(comillaSimple)){
			formato = eliminarComillaSimple(formato,comillaSimple);
		}
		
		formato = "'" + formato + "'";
		return formato;
	}	
	
	/**
	 * Método que elimina las comillas simples de una 
	 * cadena de carácteres.
	 * @author jessica.leon
	 * @since 19/09/2012
	 * @param dato
	 * @param caracter
	 * @return
	 *
	 */
	public String eliminarComillaSimple(String dato,String caracter){
		
		String vacio = " ";
		dato = dato.replaceAll(caracter,vacio);
		return dato;
	}
}
