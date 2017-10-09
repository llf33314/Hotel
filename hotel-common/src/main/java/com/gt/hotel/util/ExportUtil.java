package com.gt.hotel.util;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportUtil {
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static HSSFWorkbook getExcel(String fileName, String[] titles, String[] contentName,
			List data, Class _class, ExcelUtil fieldPprocessing) throws IllegalArgumentException, IllegalAccessException {
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
        for(int i=0;i<titles.length;i++){
        	cellTitle = rowTitle.createCell(i);
        	cellTitle.setCellValue(titles[i]);
        	cellTitle.setCellStyle(styleTitle);
        }
        HSSFCellStyle styleCenter = wb.createCellStyle();
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i=0;i<data.size();i++) {
        	_class = (Class) data.get(i).getClass();
            HSSFRow row = sheet.createRow(i + 1);
            for(int j=0;j<titles.length;j++){
            	String c = RecursivelyClass(new ExportUtil().new TempClass("", _class, contentName[j], data.get(i), fieldPprocessing));
            	HSSFCell cell = row.createCell(j);
            	cell.setCellValue(c);
            	cell.setCellStyle(styleCenter);
            }
	        
        }
		return wb;
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private static String RecursivelyClass(String motono, Class cls, String colName, Object colValue, ExcelUtil fieldPprocessing) throws IllegalArgumentException, IllegalAccessException{
		Field[] field = cls.getDeclaredFields();
		for(Field f : field){
    		f.setAccessible(true);
    		if(colName.equals(f.getName())){
    			motono = f.get(colValue) != null ? f.get(colValue).toString() : "";
    			motono = fieldPprocessing.fieldPprocessing(motono, f.getName());
    		}else{
    			Class superClass = cls.getSuperclass();
    			if(superClass != null) motono = RecursivelyClass(new ExportUtil().new TempClass(motono, superClass, colName, colValue, fieldPprocessing));
    		}
    	}
		return motono;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private static String RecursivelyClass(TempClass tempClass) throws IllegalArgumentException, IllegalAccessException{
		String c = tempClass.getMotono();
		Field[] field = tempClass.getCls().getDeclaredFields();
		for(Field f : field){
			f.setAccessible(true);
			if(tempClass.getColName().equals(f.getName())){
				c = f.get(tempClass.getColValue()) != null ? f.get(tempClass.getColValue()).toString() : "";
				c = tempClass.getFieldPprocessing().fieldPprocessing(c, f.getName());
			}else{
				if(tempClass.getCls() != null){
					Class superClass = tempClass.getCls().getSuperclass();
					tempClass.setCls(superClass);
					if(superClass != null) c = RecursivelyClass(tempClass);
				}
			}
		}
		return c;
	}
	
	
	class TempClass {
		String motono;
		@SuppressWarnings("rawtypes")
		Class cls;
		String colName;
		Object colValue;
		ExcelUtil fieldPprocessing;
		
		public TempClass() {
		}

		public TempClass(String motono, @SuppressWarnings("rawtypes") Class cls, String colName, Object colValue, ExcelUtil fieldPprocessing) {
			super();
			this.motono = motono;
			this.cls = cls;
			this.colName = colName;
			this.colValue = colValue;
			this.fieldPprocessing = fieldPprocessing;
		}

		@SuppressWarnings("rawtypes")
		public Class getCls() {
			return cls;
		}

		@SuppressWarnings("rawtypes")
		public void setCls(Class cls) {
			this.cls = cls;
		}

		public String getColName() {
			return colName;
		}

		public void setColName(String colName) {
			this.colName = colName;
		}

		public Object getColValue() {
			return colValue;
		}

		public void setColValue(Object colValue) {
			this.colValue = colValue;
		}

		public ExcelUtil getFieldPprocessing() {
			return fieldPprocessing;
		}

		public void setFieldPprocessing(ExcelUtil fieldPprocessing) {
			this.fieldPprocessing = fieldPprocessing;
		}

		public String getMotono() {
			return motono;
		}

		public void setMotono(String motono) {
			this.motono = motono;
		}
	}
	
}



