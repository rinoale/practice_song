package com.systran.thread;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.systran.dao.TbPatternDataDao;
import com.systran.process.OpenExcelFindPattern;
import com.systran.vo.TbPatternDataVo;

@Component
public class ReadFileInsertDB extends Thread
{
	private File dest;
	
	private int totalCount;
	private int index;
	
	public int getTotalCount() {
		return totalCount;
	}

	public int getIndex() {
		return index;
	}

	@Inject
	private TbPatternDataDao tb_pattern_data_Dao;
	
	@Autowired
	@Qualifier("brokerMessagingTemplate")
	private MessageSendingOperations brokerMessagingTemplate;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		OpenExcelFindPattern openExcelFindPattern=new OpenExcelFindPattern();
		List<TbPatternDataVo> tb_pattern_data_vo_list=openExcelFindPattern.getPatternData(dest.getAbsolutePath());
		
		long startTime=System.nanoTime();
		totalCount=tb_pattern_data_vo_list.size();
		for(int i=0;i<tb_pattern_data_vo_list.size();i++)
		{
			TbPatternDataVo tb_pattern_data_Vo=tb_pattern_data_vo_list.get(i);
			
			tb_pattern_data_Dao.insert(tb_pattern_data_Vo);
			index=i;
		}
		
		long endTime=System.nanoTime();
		
		long dur=(endTime-startTime)/1000000000;
		
		System.out.println("걸린시간 : "+dur);
		
//		dest.delete();
		
		try
		{
			String text = "{\"ProcessDone\":\"Insert Done\"}";
			brokerMessagingTemplate.convertAndSend("/topic/greetings", text);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public File getDest() {
		return dest;
	}

	public void setDest(File dest) {
		this.dest = dest;
	}

}
