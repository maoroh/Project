package com.maor.cipher;

import com.maor.tools.CipherType;
import com.maor.tools.KeyGenerator;

public class CaesarCipher extends Cipher {

	public CaesarCipher(CipherType type) {
		super(type);
		
	}


	@Override
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		if(content + this.getKeyValue()> Byte.MAX_VALUE)
			content = (byte) ((content + this.getKeyValue() - Byte.MAX_VALUE - 1) + Byte.MIN_VALUE); 
		else content += this.getKeyValue();
		return (byte)content;
	}


	@Override
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		if(content - this.getKeyValue() < Byte.MIN_VALUE)
			content = (byte) ((content - this.getKeyValue() - Byte.MIN_VALUE + 1) + Byte.MAX_VALUE); 
		else content -= this.getKeyValue();
		return (byte)content;
	}


	@Override
	
	public void generateKey(String path) {
		this.setKey(new Key(KeyGenerator.generateKey(this.getType())));
		KeyGenerator.createKeyFile(this.getKey() , path);
	
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{ 
		CaesarCipher c = new CaesarCipher(CipherType.Caesar);
		if(this.getKey() != null) 
			c.setKey(new Key (this.getKeyValue()));
		return c; }


	
}
