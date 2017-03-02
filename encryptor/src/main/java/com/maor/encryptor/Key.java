package com.maor.encryptor;
import java.io.Serializable;

public class Key implements Serializable {
	private static final long serialVersionUID = 1L;
	byte key1, key2;
	
	public Key(byte key1)
	{
		this.key1 = key1;
	}
	
	public Key(byte key1, byte key2)
	{
		this.key1 = key1;
		this.key2 = key2;
	}
}
