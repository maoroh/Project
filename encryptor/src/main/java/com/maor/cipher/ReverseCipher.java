package com.maor.encryptor;

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
	public void generateKey() {
		// TODO Auto-generated method stub
		this.setKey(new Key(KeyGenerator.generateKey(c.getType())));
		KeyGenerator.createKeyFile(k);
	}
	
	@Override
	public void setKey(Key k)
	{
		this.k = k;
		this.c.setKey(new Key(this.getKeyValue()));
	}
	
	
	
}
