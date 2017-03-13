package com.maor.encryptor;

import java.util.Observable;
import java.util.Observer;

public class UserInfo implements Observer{
	private long lastTime = 0;
	@Override
	public void update(Observable o, Object arg) {
		
		EventInfo event = (EventInfo)arg;
		if(event.getId() == 0)
		{
			lastTime = event.getTime();
			System.out.println(event.getMessage());
		}
		else System.out.println(event.getMessage() + "Process time:  " + (event.getTime()-lastTime) + " ms");
		
	}
	
	
	

}
