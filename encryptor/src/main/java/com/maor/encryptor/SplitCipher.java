package com.maor.encryptor;

import com.maor.tools.KeyGenerator;

public class SplitCipher extends Cipher {
	Cipher c;
	int index = 0;
	public SplitCipher(CipherType type, Cipher c) 
	{
		super(type);
		this.c = c;
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		index ++;
		if(index % 2 == 0) 
			c.changeKey(this.getKey().getKey2());
		else c.changeKey(this.getKey().getKey1());
		index = 0;
		return c.encryptOperation(content);
		
	}

	@Override
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		index ++ ;
		if(index % 2 == 0) 
			c.changeKey(this.getKey().getKey2());
		else c.changeKey(this.getKey().getKey1());
		index = 0;
			return c.decryptOperation(content);
	}

	@Override
	public void generateKey() {
		// TODO Auto-generated method stub
		this.setKey(new Key(KeyGenerator.generateKey(c.getType()), KeyGenerator.generateKey(c.getType())));
		KeyGenerator.createKeyFile(this.getKey());
	}
	
	@Override
	public void setKey(Key k)
	{
		this.k = k;
		c.setKey(new Key(this.getKeyValue()));
	}
	
	
}
