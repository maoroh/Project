package com.maor.user;

import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.maor.tools.DirectoryXMLReport;



public class FileEventHandler implements Observer{
	static final Logger logger = Logger.getLogger(FileEventHandler.class);
	private DirectoryXMLReport directoryReport ;
	
	public FileEventHandler ()
	{	
		BasicConfigurator.configure();
		 directoryReport = new DirectoryXMLReport();
	}
	 
	@Override
	public void update(Observable o, Object arg) {
		
		EventInfo event = (EventInfo)arg;
		
		if(event.getId() == EventEnum.Start)
		{
			
			//System.out.println(event.getMessage());
			logger.info(event.getMessage());
		}
		else if(event.getId() == EventEnum.Finish)
		{
			long time = System.currentTimeMillis() - event.getTime();
			logger.info(event.getMessage() + "Process time:  " + time + " ms");
			
			event.setTime(time);
			directoryReport.addReport(event);
			//System.out.println(event.getMessage() + "Process time:  " + (event.getTime()-lastTime) + " ms");
		}
		
		else if(event.getId() == EventEnum.Error)
		{
			directoryReport.addReport(event);
		}
		
	}
	
	public DirectoryXMLReport getReport()
	{
		return this.directoryReport;
	}

}
