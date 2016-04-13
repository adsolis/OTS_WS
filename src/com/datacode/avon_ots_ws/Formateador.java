/**
 *
 *  @since 16/01/2012
 *
 */
package com.datacode.avon_ots_ws;

/**
 * @author jessica.leon
 * @since 16/01/2012
 * 
 */
public class Formateador {

	public String formatearDato(String tipoDato,String dato) {
		
		String datoFormateado = dato;

		for (DataType type : DataType.values()) {
			if (type.getDataType().equals(tipoDato)) {
				datoFormateado = aplicarFormato(type,dato);
			}
		}
		
		return datoFormateado;
	}

	public String aplicarFormato(DataType type, String dato) {

		FormateadorDato format = null;
		String datoFormateado = null;

		if (isDatoNulo(dato)) {
			return null;
		} else {
			format = FormateadorFactory.getInstance(type);
			datoFormateado = String.valueOf(format.formatearDato(dato));
			return datoFormateado;
		}
	}

	public boolean isDatoNulo(String dato) {
		boolean isNull = false;
		if (dato == null) {
			isNull = true;
		}
		return isNull;
	}
}
