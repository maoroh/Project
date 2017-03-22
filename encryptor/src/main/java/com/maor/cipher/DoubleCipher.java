package com.maor.cipher;

import com.maor.tools.CipherType;
import com.maor.tools.KeyGenerator;

public class DoubleCipher extends Cipher {
	private Cipher c1,c2;
	public DoubleCipher(CipherType type, Cipher c1, Cipher c2) {
		super(type);
		this.c1 = c1;
		this.c2 = c2;
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

	@Override
	public void generateKey(String path) {
		// TODO Auto-generated method stub
	
		this.setKey(new Key(KeyGenerator.generateKey(this.c1.getType()), KeyGenerator.generateKey(this.c2.getType())));
		KeyGenerator.createKeyFile(this.getKey() , path);
	}
	
	@Override
	public void setKey(Key k)
	{
		this.k = k;
		c1.setKey(new Key(k.getKey1()));
		c2.setKey(new Key(k.getKey2()));
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{  
		DoubleCipher c = new DoubleCipher(CipherType.Double, (Cipher)this.c1.clone(), (Cipher)this.c2.clone());
		if(this.getKey() != null) 
			c.setKey(new Key (this.getKey().getKey1(),this.getKey().getKey2()));
		return c;  
		}
	
	

}
