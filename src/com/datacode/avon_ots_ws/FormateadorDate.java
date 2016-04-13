/**
 *
 *  @since 07/02/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jessica.leon
 * @since 07/02/2012
 *
 */
public class FormateadorDate implements FormateadorDato{

	/* (non-Javadoc)
	 * @see com.datacode.avon_ots_ws.FormateadorDato#formatearDato(java.lang.Object)
	 */
	@Override
	public Object formatearDato(Object dato) {
		String fecha = String.valueOf(dato);
		fecha = fecha.substring(0,10);
		fecha = fecha.replace(" ", "/");
		return fecha;
	}

}
