/**
 *
 *  @since 03/02/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.io.FileFilter;

/**
 * @author jessica.leon
 * @since 03/02/2012
 * 
 */
public class FilterFile implements FileFilter {

	private final String[] extensionesPermitidas = new String[] { ".csv" };
	private final String extensionXls = ".xls";

	@Override
	public boolean accept(java.io.File file) {

		for (String extension : extensionesPermitidas) {
			if (file.getName().toLowerCase().endsWith(extension)) {
				return true;
			}
		}
		return false;
	}

	public boolean isExcel(java.io.File file) {
		if (file.getName().toLowerCase().endsWith(extensionXls)) {
			return true;
		}
		return false;
	}
}
