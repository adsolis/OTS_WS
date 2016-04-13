/**
 *
 *  @since 01/02/2012
 *
 */
package com.datacode.avon_ots_ws;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

/**
 * @author jessica.leon
 * @since 01/02/2012
 * 
 */
public class ConvertCSV {

	public static final int SHEET = 0;

	public File convertirExcelToCsv(String archivoCSV, String archivoExcel) {

		File file = new File(archivoCSV);
		Formateador formateador = new Formateador();

		try {

			OutputStream os = (OutputStream) new FileOutputStream(file);
			String encoding = "UTF8";
			OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
			BufferedWriter bw = new BufferedWriter(osw);
			String vacio = "";

			String filename = archivoExcel;
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(filename), ws);
			String datoFormateado = null;

			Sheet s = w.getSheet(SHEET);
			Cell[] row = null;
			int numRows = verificarRenglonesConInformacion(s);

			for (int i = 0; i < numRows; i++) {
				row = s.getRow(i);
				if (row.length > 0) {
					if (!vacio.equals(row[0].getContents())) {
						bw.write(row[0].getContents());
					}
					for (int j = 1; j < row.length; j++) {
						if (!vacio.equals(row[j].getContents())) {
							datoFormateado = formateador
									.formatearDato(row[j].getType().toString(),
											row[j].getContents());
							bw.write(',');
							bw.write(datoFormateado);
						} else {
							datoFormateado = "";
							bw.write(',');
							bw.write(datoFormateado);
						}
					}
				}
				bw.newLine();
			}
			bw.flush();
			bw.close();

		} catch (UnsupportedEncodingException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		} catch (Exception e) {
			System.err.println(e.toString());
		}

		return file;
	}

	private int verificarRenglonesConInformacion(Sheet sheet) {

		int totalRowsconInformacion = 0;
		int columnasNoVacias = 0;
		Cell[] row = null;
		String vacio = "";

		for (int i = 0; i < sheet.getRows(); i++) {
			row = sheet.getRow(i);
			if (row.length > 0) {
				for (int j = 0; j < row.length; j++) {
					if (!vacio.equals(row[j].getContents())) {
						columnasNoVacias++;
					}
				}
			}
			if (columnasNoVacias > 0) {
				totalRowsconInformacion++;
			}
			columnasNoVacias = 0;
		}
		return totalRowsconInformacion;
	}
}
