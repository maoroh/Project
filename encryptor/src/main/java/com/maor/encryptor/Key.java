package com.maor.encryptor;

import java.util.ArrayList;
import java.util.Random;

public class Key {
	ArrayList <Byte> key;
	
	public Key(byte [] key)
	{
		this.key = new ArrayList<Byte>();
		for(int i=0; i<key.length; i++)
			this.key.add(key[i]);
	}
	
	public static Key generateKey(CipherType type)
	{
		byte [] key = new byte[1];
		if(type == CipherType.Mult)
		{
			do {
				new Random().nextBytes(key);
			} while(key[0] % 2 == 0 || key[0] == 0);
		}
		else new Random().nextBytes(key);
		return new Key(key);
	}
	
	
}
