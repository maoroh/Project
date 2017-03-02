package com.maor.encryptor;

public class SplitCipher extends Cipher {
	Cipher c;
	int index = 0;
	int index1 = 0;
	public SplitCipher(byte key1, byte key2) {
		super(key1);
		this.key2 = key2;
		c = new CaesarCipher(key1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		index ++ ;
		if(index % 2 == 0) 
			c.changeKey(key2);
		else c.changeKey(key);
		return c.encryptOperation(content);
		
	}

	@Override
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		index ++ ;
		if(index % 2 == 0) 
			c.changeKey(key2);
		else c.changeKey(key);
			return c.decryptOperation(content);
	}

}
