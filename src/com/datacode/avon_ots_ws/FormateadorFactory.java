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
public class FormateadorFactory {

	public static FormateadorDato getInstance(DataType type) {

		FormateadorDato format = null;

		switch (type) {

		case NUMERIC:
			format = new FormateadorInt();
			break;
		case VARCHAR:
			format = new FormateadorVarchar();
			break;
		case DATETIME:
			format = new FormateadorDateTime();
			break;
		case CHAR:
			format = new FormateadorVarchar();
			break;
		case DATE:
			format = new FormateadorDate();
			break;
		}
		return format;
	}
}
