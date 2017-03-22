package com.maor.cipher;

import com.maor.tools.CipherType;
import com.maor.tools.KeyGenerator;

public class XORCipher extends Cipher{

	public XORCipher(CipherType type) {
		super(type);
		
	}

	@Override
	public byte encryptOperation(byte content) {
		// TODO Auto-generated method stub
		return (byte) (content ^ this.getKeyValue());
	}

	@Override
	public byte decryptOperation(byte content) {
		// TODO Auto-generated method stub
		return (byte) (content ^ this.getKeyValue());
	}

	@Override
	public void generateKey(String path) {
		// TODO Auto-generated method stub
		this.setKey(new Key(KeyGenerator.generateKey(this.getType())));
		KeyGenerator.createKeyFile(this.getKey() , path);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{ 
		XORCipher c = new XORCipher(CipherType.XOR);
		if(this.getKey() != null) 
			c.setKey(new Key (this.getKeyValue()));
		return c; }

	

}
