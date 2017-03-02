package com.maor.encryptor;

public class EventInfo {
	long time;
	int id; //0-start,1-end
	String message;
	
	public EventInfo(long time,int id,String message)
	{
		this.time = time;
		this.id=id;
		this.message = new String(message);
	}
}
