package com.systran.create;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Vector;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.poi.ss.usermodel.Row;

import com.opencsv.CSVWriter;


public class CreateCSV 
{
	private String path;
	private String encoding="euc-kr";
	
	public void setPath(String path)
	{
		this.path=path+".csv";
	}
	
	public void createHeader()
	{
		
		String category="한국어 문장"+","+"영어 문장"+","+"한글 문장 패턴"+","+"영어 문장 패턴"+","+"패턴 적용 여부";
	
		PrintWriter out=null;
		//FileWriter write=null;
		try {
			out=new PrintWriter(path,encoding);
			out.println(category);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			out.close();
			
		}
	}
	
	public void writeMultipleRows(Vector<String[]> vInput) throws IOException
	{
		FileWriterWithEncoding fww=new FileWriterWithEncoding(path,encoding, true);	
		CSVWriter writer = new CSVWriter(fww);
		for(int i=0;i<vInput.size();i++)
		{
			writer.writeNext(vInput.get(i));
		}
		
		
		writer.close();
		fww.close();
	}
	
	public void writeSingleRow(String[] input) throws IOException
	{
		FileWriterWithEncoding fww=new FileWriterWithEncoding(path,encoding, true);	
		CSVWriter writer = new CSVWriter(fww);
		
		writer.writeNext(input);

		
		
		writer.close();
		fww.close();
	}

}