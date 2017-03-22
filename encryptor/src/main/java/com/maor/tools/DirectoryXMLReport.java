package com.maor.tools;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.maor.user.EventInfo;

@XmlRootElement(name = "DirectoryReport")
@XmlAccessorType (XmlAccessType.FIELD)
public class DirectoryXMLReport {
	
	@XmlElement(name = "Report")
	private ArrayList <EventInfo> fileReports;
	
	public DirectoryXMLReport()
	{
		fileReports = new ArrayList<EventInfo>();
	}
	
	public void addReport(EventInfo event)
	{
		this.fileReports.add(event);
	}

}
