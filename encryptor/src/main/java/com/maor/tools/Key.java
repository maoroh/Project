package com.maor.tools;
import java.io.Serializable;

public class Key implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte key=0, key2=0;
	
	public Key(byte key1)
	{
		this.key = key1;
	}
	
	public Key(byte key1, byte key2)
	{
		this.key = key1;
		this.key2 = key2;
	}
	
	public byte getKey1(){
		return this.key;
	}
	
	public byte getKey2(){
		return this.key2;
	}
	
	public void setKey1(byte key){
		this.key = key;
	}
	
}
