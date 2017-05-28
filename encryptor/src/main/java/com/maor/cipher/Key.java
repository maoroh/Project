package com.maor.cipher;
import java.io.Serializable;

public class Key implements Serializable {
	private static final long serialVersionUID = 1L;
	private Byte key = null,key1=null, key2=null;
	
	
	public Key(byte key1)
	{
		this.setKey(key1);
		this.key1 = key1;
	}
	
	public Key(byte key1, byte key2)
	{
		this.setKey(key1);
		this.key1 = key1;
		this.key2 = key2;
	}
	
	public Byte getKey1(){
		return this.key1;
	}
	
	public Byte getKey2(){
		return this.key2;
	}
	

	public Byte getKey() {
		return key;
	}

	public void setKey(byte key) {
		this.key = key;
	}
	
}
