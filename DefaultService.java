package kr.or.kipris.taskservice;



import javafx.concurrent.Service;
import javafx.concurrent.Task;


public class DefaultService extends Service{
	@Override
	protected Task createTask() {
		// TODO Auto-generated method stub
		Task<String> task_service=new Task<String>()
				{
					@SuppressWarnings("restriction")
					@Override
					protected String call() throws Exception {
						// TODO Auto-generated method stub
						
	                    updateValue("finished");
						return "finished";
					}

				};
		return task_service;//task리턴하는거중요함
	}
}