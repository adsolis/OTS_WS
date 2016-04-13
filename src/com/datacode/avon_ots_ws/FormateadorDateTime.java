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
public class FormateadorDateTime implements FormateadorDato {

	/* (non-Javadoc)
	 * @see com.datacode.avon_ots_ws.FormateadorDato#formatearDato(java.lang.Object)
	 */
	@Override
	public Object formatearDato(Object dato) {
		String formato = null;
		formato = "CONVERT(datetime,'" + dato + "',121)";
		return formato;
	}

}
