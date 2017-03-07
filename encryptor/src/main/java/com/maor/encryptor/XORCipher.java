package com.maor.encryptor;

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
	public void generateKey() {
		// TODO Auto-generated method stub
		this.setKey(new Key(KeyGenerator.generateKey(this.getType())));
		KeyGenerator.createKeyFile(this.getKey());
	}
	
	

}
