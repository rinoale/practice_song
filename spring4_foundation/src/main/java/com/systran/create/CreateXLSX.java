package com.systran.create;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateXLSX
{
	private String Path=null;
	private String err="";
	
	public void createXlsx()
	{
		XSSFWorkbook myWorkBook=new XSSFWorkbook();
		Sheet sheet=myWorkBook.createSheet("test");
		
		sheet.setColumnWidth(2, 5000);

		
		Cell[] cell=new Cell[12];
		String[] category={ "출원번호", "미국 출원번호", "미국 KINDCODE", "IPC", "출원일자", "공개번호", "공개일자", "등록번호",
				"공고일자", "영어 문장", "한글 문장", "미등록어"};
		
		int rowIndex=0;
		Row row=sheet.createRow(rowIndex++);
		
		for(int i=0;i<cell.length;i++)
		{
			cell[i]=row.createCell(i);
		}
		

		
		for(int i=0;i<category.length;i++)
		{
			cell[i].setCellValue(category[i]);
		}
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(Path);
			myWorkBook.write(fos);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	public void writeXlsx(String[] str)
	{
		File excel =  new File (Path);
        XSSFWorkbook myWorkBook=null;
        FileInputStream fis;
		try {
			 fis = new FileInputStream(excel);
			 myWorkBook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Sheet sheet = myWorkBook.getSheet("test");
        
        Cell[] cell=new Cell[3];
		try 
		{

			try 
			{

				int nextRow=sheet.getLastRowNum()+1;

		        Row row=sheet.createRow(nextRow);
		        row.setHeight((short) 1200);

				for(int j=0;j<20;j++)
				{
					cell[j]=row.createCell(j);
				}

				for(int j=0;j<str.length;j++)
				{
					cell[j].setCellValue(str[j]);
				}
				FileOutputStream fos=null;
				try {
					fos=new FileOutputStream(Path);
					myWorkBook.write(fos);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					fos.close();
				}
				System.out.println("입력완료");
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}  
	}

	
	public void writeXlsx(Vector<String[]> vInput)
	{
		
		String[] arrData = null;
		File excel =  new File (Path);
        XSSFWorkbook myWorkBook=null;
        FileInputStream fis;
		try {
			 fis = new FileInputStream(excel);
			 myWorkBook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Sheet sheet = myWorkBook.getSheet("test");
        Cell[] cell=new Cell[12];
		try 
		{

			try 
			{
				for (int inx = 0; inx < vInput.size(); inx++) {
					arrData = vInput.get(inx);
					
					int nextRow=sheet.getLastRowNum()+1;
	
			        Row row=sheet.createRow(nextRow);
			        //row.setHeight((short) 1200);
	
					for(int j=0;j<cell.length;j++)
					{
						cell[j]=row.createCell(j);
					}
	
					for(int j=0;j<arrData.length;j++)
					{
						cell[j].setCellValue(arrData[j]);
					}
				
				}
				
				FileOutputStream fos=null;
				try {
					fos=new FileOutputStream(Path);
					myWorkBook.write(fos);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					fos.close();
				}
				System.out.println("입력완료");
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}  
	}
	
	public void setPath(String Path)
	{
		this.Path=Path+".xlsx";
	}
	public String getErr()
	{
		return err;
	}
}