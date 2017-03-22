package com.maor.cipher;

import java.util.InputMismatchException;

import com.maor.tools.CipherType;
import com.maor.tools.KeyGenerator;

public class MultCipher extends Cipher {
	
	Byte decryptKey=null,decryptKey1=null,decryptKey2=null;
	
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
	public void changeKey(int n)
	{
		if(n==1) decryptKey = decryptKey1;
		else if(n==2) decryptKey = decryptKey2;
		super.changeKey(n);	
	}

	@Override
	public void generateKey(String path) {

		this.setKey(new Key(KeyGenerator.generateKey(this.getType())));
		KeyGenerator.createKeyFile(this.getKey() , path);
		decryptKey1 = findDecryptKey(this.getKey().getKey1());
		if(this.getKey().getKey2() != null )
			decryptKey2 = findDecryptKey(this.getKey().getKey2());
		decryptKey = decryptKey1;
	}
	
	public void setKey(Key k)
	{
		this.k = k;
		decryptKey1 = findDecryptKey(this.getKey().getKey1());
		if(this.getKey().getKey2() != null )
			decryptKey2 = findDecryptKey(this.getKey().getKey2());
		decryptKey = decryptKey1;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{ 
		MultCipher c = new MultCipher(CipherType.Mult);
		if(this.getKey() != null) 
			c.setKey(new Key (this.getKeyValue()));
		return c; }


}
