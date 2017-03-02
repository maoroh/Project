package com.maor.encryptor;

public class ReverseCipher extends Cipher {
	Cipher c;
	public ReverseCipher(byte key1 ) {
		super(key1);
		c = new CaesarCipher(key1);
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
	
	
}
