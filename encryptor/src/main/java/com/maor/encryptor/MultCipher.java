package com.maor.encryptor;

import java.util.InputMismatchException;

import com.maor.tools.KeyGenerator;

public class MultCipher extends Cipher {
	
	byte decryptKey;
	
	public MultCipher(CipherType type)
	{
		super(type);
		
	}

	@Override
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		return (byte) (content * this.getKeyValue());
	}

	@Override
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		return (byte) (content * this.decryptKey);
	}
	
	public byte findDecryptKey(byte key)
	{
		byte mul = -1;
		if(key % 2 == 0) throw new InputMismatchException("The key is an even number!");
		for(byte i = Byte.MIN_VALUE; i<=Byte.MAX_VALUE; i++)
		{
			mul = (byte) (i * key);
			if(mul  == 1) 
				return i; 
		}
		
		return mul;
	}
	
	@Override
	public void changeKey(byte key)
	{
		this.getKey().setKey1(key);
		decryptKey = findDecryptKey(key);
	}

	@Override
	public void generateKey() {

		this.setKey(new Key(KeyGenerator.generateKey(this.getType())));
		KeyGenerator.createKeyFile(k);
	}
	
	public void setKey(Key k)
	{
		this.k = k;
		decryptKey = findDecryptKey(this.getKeyValue());
	}

}
