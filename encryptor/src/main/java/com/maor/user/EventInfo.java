package com.maor.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Report")
@XmlAccessorType (XmlAccessType.FIELD)
public class EventInfo {
	private String fileName;
	private StatusEnum status;
	private EventEnum id; //0-start,1-end
	private Long time;
	private Long totalTime;
	private String execeptionName;
	private String execeptionMessage;
	private String execeptionStackTrace;
	private String message;
	
	public EventInfo()
	{
	
	}
	public EventInfo(long time,EventEnum id,String message,String fileName)
	{
		this.setTime(time);
		this.setId(id);
		this.setMessage(new String(message));
	
		this.setFileName(fileName);
		this.setStatus(StatusEnum.Succeeded);
	}
	
	public EventInfo(String fileName,EventEnum id,String execeptionName, String execeptionMessage,String execeptionStackTrace)
	{
		this.setFileName(fileName);
		this.setId(id);
		this.setExeceptionMessage(execeptionMessage);
		this.setExeceptionName(execeptionName);
		this.setExeceptionStackTrace(execeptionStackTrace);
		this.setStatus(StatusEnum.Failed);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EventEnum getId() {
		return id;
	}

	public void setId(EventEnum  id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public String getExeceptionName() {
		return execeptionName;
	}
	public void setExeceptionName(String execeptionName) {
		this.execeptionName = execeptionName;
	}
	public String getExeceptionMessage() {
		return execeptionMessage;
	}
	public void setExeceptionMessage(String execeptionMessage) {
		this.execeptionMessage = execeptionMessage;
	}
	public String getExeceptionStackTrace() {
		return execeptionStackTrace;
	}
	public void setExeceptionStackTrace(String execeptionStackTrace) {
		this.execeptionStackTrace = execeptionStackTrace;
	}
	public Long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}
}

enum StatusEnum{
	Failed, Succeeded;
}
