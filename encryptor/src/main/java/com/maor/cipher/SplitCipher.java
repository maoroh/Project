package com.maor.cipher;

import com.maor.tools.CipherType;
import com.maor.tools.KeyGenerator;
import com.maor.cipher.Key;

public class SplitCipher extends Cipher {
	Cipher c;
	boolean even = false;
	public SplitCipher(CipherType type, Cipher c) 
	{
		super(type);
		this.c = c;
	
		// TODO Auto-generated constructor stub
	}

	
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		if(even) 
		{
			c.changeKey(2);
			even = false;
		}
		else 
		{
			c.changeKey(1);
			even = true;
		}
		
		return c.encryptOperation(content);
		
	}

	
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		if(even) 
		{
			c.changeKey(2);
			even = false;
		}
		else 
		{
			c.changeKey(1);
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

	@Override
	public Object clone() throws CloneNotSupportedException{  
		SplitCipher c = new SplitCipher(CipherType.Split, (Cipher)this.c.clone());
		if(this.getKey() != null) 
			c.setKey(new Key (this.getKey().getKey1(),this.getKey().getKey2()));
		return c;  
		}

	
	
	
}
