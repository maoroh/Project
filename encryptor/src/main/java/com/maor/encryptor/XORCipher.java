package com.maor.encryptor;

public class XORCipher extends Cipher{

	public XORCipher(byte key) {
		super(key);
	}

	@Override
	public byte encryptOperation(byte content, byte key) {
		// TODO Auto-generated method stub
		return (byte) (content ^ key);
	}

	@Override
	public byte decryptOperation(byte content, byte key) {
		// TODO Auto-generated method stub
		return (byte) (content ^ key);
	}
	
	

}
