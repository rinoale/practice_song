package com.systran.thread;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Component;

import com.systran.create.CreateCSV;
import com.systran.create.CreateFile;
import com.systran.create.CreateManager;
import com.systran.process.InputOrgContGetTrans;
import com.systran.process.OpenExcelFindPattern;
import com.systran.vo.TbPatternDataVo;

@Component
public class ReadFileTransOutputCSV extends Thread
{
	private File dest;
	private int totalCount;
	private int index;
	
	@Autowired
	@Qualifier("brokerMessagingTemplate")
	private MessageSendingOperations brokerMessagingTemplate;
	
	@Override
	public void run()
	{
		String filepath=dest.getAbsolutePath();
		String filename=dest.getName().substring(0, dest.getName().lastIndexOf("."));
		
		OpenExcelFindPattern openExcelFindPattern=new OpenExcelFindPattern();
		Vector<String[]> vContPatternData=openExcelFindPattern.getContPatternData(filepath);
		
		CreateCSV createCSV=new CreateCSV();
		createCSV.setPath("C:/util/dev/tomcat8/webapps/data/output/"+filename);
		
		
		totalCount=vContPatternData.size();
		for(int i=0;i<vContPatternData.size();i++)
		{
			index=i;
			String[] ContPatternData=vContPatternData.get(i);
			InputOrgContGetTrans inputOrgContGetTrans=new InputOrgContGetTrans();
			try {
				ContPatternData[3]=inputOrgContGetTrans.UsePatternTranslator(ContPatternData[0]);
				ContPatternData[1]=inputOrgContGetTrans.getOrg_pttrn();
				ContPatternData[2]=inputOrgContGetTrans.getTrnst_pttrn();
				ContPatternData[4]=inputOrgContGetTrans.isApplyPattern() ? "패턴 적용" : "패턴 미적용";
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		createCSV.createHeader();
		try {
			createCSV.writeMultipleRows(vContPatternData);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try
		{
			String text = "{\"ProcessDone\":\"Trans done\"}";
			brokerMessagingTemplate.convertAndSend("/topic/greetings", text);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setDest(File dest) {
		this.dest = dest;
	}
}
