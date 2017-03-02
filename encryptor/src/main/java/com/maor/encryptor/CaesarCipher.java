package com.maor.encryptor;

public class CaesarCipher extends Cipher {

	@Override
	public byte encryptOperation(byte content,byte key) {
		// TODO Auto-generated method stub
		if(content + key > Byte.MAX_VALUE)
			content = (byte) ((content + key - Byte.MAX_VALUE - 1) + Byte.MIN_VALUE); 
		else content += key;
		return (byte)content;
	}


	@Override
	public byte decryptOperation(byte content,byte key) {
		// TODO Auto-generated method stub
		if(content - key < Byte.MIN_VALUE)
			content = (byte) ((content - key - Byte.MIN_VALUE + 1) + Byte.MAX_VALUE); 
		else content -= key;
		return (byte)content;
	}
}
