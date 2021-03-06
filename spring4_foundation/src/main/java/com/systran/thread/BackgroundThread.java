package com.systran.thread;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.systran.thread.ReadFileInsertDB;

@Component
public class BackgroundThread implements Runnable {
	final static private int STATE_INIT = 0x1;
	final static private int STATE_STARTED = 0x1 << 1;
	final static private int STATE_SUSPENDED = 0x1 << 2;
	final static private int STATE_STOPPED = 0x1 << 3; 
	
	private Thread itself;
	private volatile int stateCode = STATE_INIT;
	
	private Thread fileInsertThread=null;
	private Thread fileTransThread=null;
	
//	@Inject
	private SimpMessagingTemplate template;
	
	@Autowired
	public BackgroundThread(SimpMessagingTemplate template)
	{
		this.template=template;
	}
//	

	
//	public void start() {
//		System.out.println("Entered in start[Thread]");
//		synchronized (this) {
//			if(stateCode != STATE_INIT){
//				throw new IllegalStateException(" already activated...");
//			}
//			itself = new Thread(this);
//			itself.start();
//			stateCode = STATE_STARTED;
//		}
//		//synchronized 는 멀티 스레드 환경을 고려한 설계.
//	}
	
	public void pause() {
		System.out.println("\nEntered in pause[Thread]");
		synchronized (this) {
			System.out.println("This one is synchronized...");
			if(stateCode == STATE_SUSPENDED) return;
			System.out.println("state code is not suspend...");
			if(stateCode == STATE_INIT)
				throw new IllegalStateException(" not yet started.....");
			System.out.println("state code is not init...");
			if(stateCode == STATE_STOPPED)
				throw new IllegalStateException(" already dead........");
			System.out.println("state code is not stop...");
			stateCode = STATE_SUSPENDED;
			System.out.println("state has been changed to [" + stateCode + "]");
		}
	}
	
	public void resume() {
		System.out.println("\nEntered in resume[Thread]");
		synchronized (this) {
			if(stateCode == STATE_INIT || stateCode == STATE_STARTED) return;
			System.out.println("state code is not init...");
			if(stateCode == STATE_STOPPED)
				throw new IllegalStateException(" already dead........");
			System.out.println("state code is not stop...");
			stateCode = STATE_STARTED;
			itself.interrupt();
//			Thread.currentThread().interrupt();
		}
	}
	
	public void doJob(int i) {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
		int sleeptime=10000;
		
		for(int j=0;j<threadArray.length;j++)
		{
			
			String threadName=threadArray[j].getName();
			if(threadName.contains("fileOpenAndInsert"))
			{
				String filename=threadName.replace("fileOpenAndInsert", "");
//				System.out.println(filename);
	
				fileInsertThread=threadArray[j];
				ReadFileInsertDB rfid=(ReadFileInsertDB)fileInsertThread;
				int index=rfid.getIndex();
				int totalCount=rfid.getTotalCount();
				System.out.println(index+"/"+totalCount);
				
				String text = "{\"filename\":\""+filename+"\", \"InsertIndex\":\""+index+"\", \"totalCount\":\""+totalCount+"\"}";
				template.convertAndSend("/topic/greetings", text);
				
				sleeptime=1000;
//				System.out.println(threadName+" running");

			}
			
			if(threadName.contains("fileOpenAndTrans"))
			{
				String filename=threadName.replace("fileOpenAndTrans", "");
//				System.out.println(filename);
	
				fileTransThread=threadArray[j];
				ReadFileTransOutputCSV rftoc=(ReadFileTransOutputCSV)fileTransThread;
				int index=rftoc.getIndex();
				int totalCount=rftoc.getTotalCount();
				System.out.println(index+"/"+totalCount);
			
				String text = "{\"filename\":\""+filename+"\", \"TransIndex\":\""+index+"\", \"totalCount\":\""+totalCount+"\"}";
				template.convertAndSend("/topic/greetings", text);
				
				sleeptime=1000;
//				System.out.println(threadName+" running");

			}
			
		}
		
		if(fileInsertThread!=null)
		{
//			System.out.println("파일입력스레드의상태"+fileInsertThread.getState());
			if(fileInsertThread.getState()==Thread.State.TERMINATED)
			{
				String text = "{\"content\":\"파일입력 완료\"}";
				template.convertAndSend("/topic/greetings", text);
			}
			fileInsertThread=null;
		}
		
//		System.out.println("THREAD OBSERVING");
//		System.out.println("================");
	
		try {
			Thread.sleep(sleeptime);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		try{
//			File filesInPath=new File(file_save_path);
//			String[] files=filesInPath.list();
//			for(int j=0;j<files.length;j++)
//			{
//				System.out.println(files[j]);
//			}
//			System.out.println("Thread is runinng :" + i);
//			Thread.sleep(2*1000);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		itself = Thread.currentThread();
		if(stateCode != STATE_INIT){
			throw new IllegalStateException(" already activated...");
		}
		stateCode = STATE_STARTED;

//		while(true)
//		{
//			Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//			Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//			
//			for(int i=0;i<threadArray.length;i++)
//			{
//				String threadName=threadArray[i].getName();
//				if(threadName.equals("fileOpenAndTrans"))
//				{
//					System.out.println(threadName+" running");
//				}
//			}
//			
//			System.out.println("THREAD OBSERVING");
//		}

		
		int i  = 0;
		while(true){
//			System.out.println("State Code is [" + stateCode + "]");
			//=========suspend
			while(stateCode == STATE_SUSPENDED){
				try{
					System.out.println("Thread suspending...");
					itself.sleep(24 * 60 * 60 * 1000);
//					Thread.currentThread().sleep(24 * 60 * 60 * 1000);
				}catch(InterruptedException e){
					if(stateCode != STATE_SUSPENDED){							
						System.out.println("Thread resuming....");
						break;
					}
				}
			}	
			if(stateCode == STATE_STOPPED){
				System.out.println("Thread is dead.....");
				break;
			}
			doJob(++i);
		}
	}	
};