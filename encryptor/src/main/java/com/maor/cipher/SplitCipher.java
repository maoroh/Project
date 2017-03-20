package com.maor.cipher;

import com.maor.tools.CipherType;
import com.maor.tools.Key;
import com.maor.tools.KeyGenerator;

public class SplitCipher extends Cipher {
	Cipher c;
	boolean even = false;
	public SplitCipher(CipherType type, Cipher c) 
	{
		super(type);
		this.c = c;
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		if(even) 
		{
			c.changeKey(this.getKey().getKey2());
			even = false;
		}
		else 
		{
			c.changeKey(this.getKey().getKey1());
			even = true;
		}
		
		return c.encryptOperation(content);
		
	}

	@Override
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		if(even) 
		{
			c.changeKey(this.getKey().getKey2());
			even = false;
		}
		else 
		{
			c.changeKey(this.getKey().getKey1());
			even = true;
		}
			return c.decryptOperation(content);
	}

	@Override
	public void generateKey(String path) {
		// TODO Auto-generated method stub
		this.setKey(new Key(KeyGenerator.generateKey(c.getType()), KeyGenerator.generateKey(c.getType())));
		KeyGenerator.createKeyFile(this.getKey() , path);
		
	}
	
	@Override
	public void setKey(Key k)
	{
		this.k = k;
		c.setKey(new Key(this.getKey().getKey1(), this.getKey().getKey2()));
	}
	
	
}
