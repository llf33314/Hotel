package com.gt.hotel.util;

import org.apache.poi.hssf.usermodel.*;

import java.lang.reflect.Field;
import java.util.List;

public class ExportUtil {

    @SuppressWarnings({ "rawtypes", "deprecation" })
    public static HSSFWorkbook getExcel(String fileName, String[] titles, String[] contentName, List data, Class _class, ExcelUtil fieldPprocessing)
	throws IllegalArgumentException, IllegalAccessException {
	HSSFWorkbook wb = new HSSFWorkbook();
	HSSFSheet sheet = wb.createSheet(fileName);
	HSSFRow rowTitle = sheet.createRow(0);
	HSSFCellStyle styleTitle = wb.createCellStyle();
	styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	HSSFFont fontTitle = wb.createFont();
	fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	fontTitle.setFontName("宋体");
	fontTitle.setFontHeight((short) 200);
	styleTitle.setFont(fontTitle);
	HSSFCell cellTitle = null;
	for (int i = 0; i < titles.length; i++) {
	    cellTitle = rowTitle.createCell(i);
	    cellTitle.setCellValue(titles[i]);
	    cellTitle.setCellStyle(styleTitle);
	}
	HSSFCellStyle styleCenter = wb.createCellStyle();
	styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	for (int i = 0; i < data.size(); i++) {
	    _class = (Class) data.get(i).getClass();
	    Field[] field = _class.getDeclaredFields();
	    HSSFRow row = sheet.createRow(i + 1);
	    for (int j = 0; j < titles.length; j++) {
		String c = new String();
		for (Field f : field) {
		    f.setAccessible(true);
		    if (contentName[j].equals(f.getName())) {
			c = f.get(data.get(i)) != null ? f.get(data.get(i)).toString() : "";
			c = fieldPprocessing.fieldPprocessing(c, f.getName());
		    } else {
			//
			Class _superClass = _class.getSuperclass();
			Field[] hyperField = _superClass.getDeclaredFields();
			for (Field hf : hyperField) {
			    hf.setAccessible(true);
			    if (contentName[j].equals(hf.getName())) {
				c = hf.get(data.get(i)) != null ? hf.get(data.get(i)).toString() : "";
				c = fieldPprocessing.fieldPprocessing(c, hf.getName());
			    }
			}
			//
		    }
		}
		HSSFCell cell = row.createCell(j);
		cell.setCellValue(c);
		cell.setCellStyle(styleCenter);
	    }

	}
	return wb;
    }

}
