package com.maor.encryptor;

public class MultCipher extends Cipher {

	@Override
	public byte encryptOperation(byte content, byte key) {
		// TODO Auto-generated method stub
		return (byte) (content * key);
	}

	@Override
	public byte decryptOperation(byte content, byte key) {
		// TODO Auto-generated method stub
		byte decryptKey = findDecryptKey(key);
		return (byte) (content * decryptKey);
	}
	
	public byte findDecryptKey(byte key)
	{
		byte mul=-1;
		for(byte i = Byte.MIN_VALUE; i<=Byte.MAX_VALUE; i++)
		{
			mul = (byte) (i * key);
			if(mul  == 1) 
				return i; 
		}
		
		return mul;
	}

}
