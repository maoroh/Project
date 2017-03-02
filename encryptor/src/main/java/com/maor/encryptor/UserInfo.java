package com.maor.encryptor;

import java.util.Observable;
import java.util.Observer;

public class UserInfo implements Observer{
	long last = 0;
	@Override
	public void update(Observable o, Object arg) {
		
		EventInfo event = (EventInfo)arg;
		if(event.id == 0)
		{
			last = event.time;
			System.out.println(event.message);
		}
		else System.out.println(event.message + "Process time:  " + (event.time-last) + "ms");
		
	}
	
	
	

}
