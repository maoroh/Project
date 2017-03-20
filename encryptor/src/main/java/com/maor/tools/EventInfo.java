package com.maor.encryptor;

public class EventInfo {
	private long time;
	private int id; //0-start,1-end
	private String message;
	
	public EventInfo(long time,int id,String message)
	{
		this.setTime(time);
		this.setId(id);
		this.setMessage(new String(message));
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
