package com.maor.encryptor;

public class DoubleCipher extends Cipher {
	Cipher c1,c2;
	public DoubleCipher(byte key1 , byte key2) {
		super(key1);
		this.key2 = key2;
		c1 = new CaesarCipher(key1);
		c2 = new XORCipher(key2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		return c2.encryptOperation(c1.encryptOperation(content));
	}

	@Override
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		return c1.decryptOperation(c2.decryptOperation(content));
	}
	
	

}
