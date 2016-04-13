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
public enum DataType {
	
	NUMERIC("numeric"),
	VARCHAR("varchar"),
	CHAR("char"),
	DATETIME("datetime"),
	DATE("Date");
	
    
	private String dataType;
 
    private DataType(String dataType) {
    this.dataType = dataType;
    }

	/**
	 * @author jessica.leon
	 * @since 16/01/2012
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
}
