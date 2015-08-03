package com.systran.read;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelReader 
{
	private String Path;
	private HSSFSheet xls;
	private XSSFSheet xlsx;
	
	public void setPath(String path) {
		this.Path = path;

	}
	
	public String[] FindHeader()
	{
		return null;
	}

	
	public XSSFSheet readXLReturnSheet() throws IOException
	{
		FileInputStream file=null;

		file=new FileInputStream(new File(Path));
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheetAt(0);
		
		return sheet;
	}
	public HSSFSheet readXLReturnSheet_xls() throws IOException
	{
		FileInputStream file=null;

		file=new FileInputStream(new File(Path));
		HSSFWorkbook workbook=new HSSFWorkbook(file);
		HSSFSheet sheet=workbook.getSheetAt(0);
		
		return sheet;
	}
}
