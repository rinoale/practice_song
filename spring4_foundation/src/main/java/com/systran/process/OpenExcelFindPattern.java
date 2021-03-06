package com.systran.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.systran.read.ExcelReader;
import com.systran.vo.TbPatternDataVo;

public class OpenExcelFindPattern {
	public List<TbPatternDataVo> getPatternData(String excelfilepath)
	{
		List<TbPatternDataVo> tb_pattern_data_vo_list=new ArrayList<TbPatternDataVo>();
		List<String> org_pattern_list=new ArrayList<String>();
		List<String> trnst_pattern_list=new ArrayList<String>();
		
		ExcelReader xlreader=new ExcelReader();
		xlreader.setPath(excelfilepath);
		
		Sheet sheet=null;
		try {
			sheet=xlreader.readXLReturnSheet();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Row> row_iter=sheet.rowIterator();
		
		row_iter.next();// 헤더 빼기
		
		int index=0;
		while(row_iter.hasNext())
		{
			TbPatternDataVo tb_pattern_data_vo=new TbPatternDataVo();
			Row row=row_iter.next();
			
			String org_pattern=null;
			String trnst_pattern=null;
			
			
			
			Cell org_pattern_cell=row.getCell(2,Row.CREATE_NULL_AS_BLANK);
			if(org_pattern_cell!=null)
			{		
				org_pattern_cell.setCellType(Cell.CELL_TYPE_STRING);
				org_pattern=org_pattern_cell.getStringCellValue();
				
			}
			Cell trnst_pattern_cell=row.getCell(3,Row.CREATE_NULL_AS_BLANK);
			if(trnst_pattern_cell!=null)
			{
				trnst_pattern_cell.setCellType(Cell.CELL_TYPE_STRING);
				trnst_pattern=trnst_pattern_cell.getStringCellValue();
				
			}
			
			if(trnst_pattern!=null && !trnst_pattern.equals("") && org_pattern!=null && !org_pattern.equals(""))
			{

				if(!(trnst_pattern_list.contains(trnst_pattern) && org_pattern_list.contains(org_pattern)))
				{
					trnst_pattern_list.add(trnst_pattern);
					org_pattern_list.add(org_pattern);
					index++;
					tb_pattern_data_vo.setSEQ(index);
					tb_pattern_data_vo.setTRNST_PTTRN(trnst_pattern);
					tb_pattern_data_vo.setORG_PTTRN(org_pattern);
					tb_pattern_data_vo_list.add(tb_pattern_data_vo);
				}
			}
		}
		
		return tb_pattern_data_vo_list;
	}
	
	public Vector<String[]> getContPatternData(String excelfilepath)
	{
		Vector<String[]> vContPatternData=new Vector<String[]>();
		
		ExcelReader xlreader=new ExcelReader();
		xlreader.setPath(excelfilepath);
		
		Sheet sheet=null;
		try {
			sheet=xlreader.readXLReturnSheet();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Row> row_iter=sheet.rowIterator();
		
		row_iter.next();// 헤더 빼기
		
		while(row_iter.hasNext())
		{
			Row row=row_iter.next();
			
			String org_cont=null;
			
			
			Cell org_cont_cell=row.getCell(0,Row.CREATE_NULL_AS_BLANK);
			if(org_cont_cell!=null)
			{		
				org_cont_cell.setCellType(Cell.CELL_TYPE_STRING);
				org_cont=org_cont_cell.getStringCellValue();
				
			}
			
			if(org_cont!=null && !org_cont.equals(""))
			{
				String[] ContPatternData=new String[5];
				ContPatternData[0]=org_cont;

				
				vContPatternData.add(ContPatternData);
			}
		}
		
		return vContPatternData;
	}
}
