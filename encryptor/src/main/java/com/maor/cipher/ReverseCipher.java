package com.maor.cipher;

import com.maor.tools.CipherType;
import com.maor.tools.KeyGenerator;

public class ReverseCipher extends Cipher {
	Cipher c;
	public ReverseCipher(CipherType type, Cipher c) {
		super(type);
		this.c = c;
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		return c.decryptOperation(content);
	}

	@Override
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		return c.encryptOperation(content);
	}

	@Override
	public void generateKey(String path) {
		// TODO Auto-generated method stub
		this.setKey(new Key(KeyGenerator.generateKey(c.getType())));
		KeyGenerator.createKeyFile(this.getKey() , path);
	}
	
	@Override
	public void setKey(Key k)
	{
		this.k = k;
		this.c.setKey(new Key(this.getKeyValue()));
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{  
		ReverseCipher c = new ReverseCipher(CipherType.Reverse, (Cipher)this.c.clone());
		if(this.getKey() != null) 
			c.setKey(new Key (this.getKeyValue()));
		return c;  
		}
	
	
	
}
