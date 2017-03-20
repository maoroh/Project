package com.maor.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;

import com.maor.tools.KeyGenerator;

public abstract class Cipher extends Observable
{
	protected Key k;
	protected CipherType type;
	
	public Cipher(CipherType type)
	{
		this.type = type;
		this.k = null;
	}
	
	public void encrypt(String path)
	{
		if(k == null)
			generateKey();
		File file = new File(path);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		setChanged();
		this.notifyObservers(new EventInfo(System.currentTimeMillis(),0,"Encryption is starting..."));
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(path+".encrypted");
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());

			int content;
			while ((content = fis.read()) != -1) {
				fos.write(encryptOperation((byte)content));
			}

		} catch (IOException e) {
			System.out.println("Error : File Not Found!");
			e.printStackTrace();
		
		} finally {
			try {
					fis.close();
					fos.close();
				}
				catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			setChanged();
			this.notifyObservers(new EventInfo(System.currentTimeMillis(),1,"Encryption ended successfuly..."));
			
		}
	}
	
	public void decrypt(String path, Key k)
	{
		this.setKey(k);
		File file = new File(path);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		setChanged();
		this.notifyObservers(new EventInfo(System.currentTimeMillis(),0,"Decryption is starting..."));
		try {
			fis = new FileInputStream(file);
			String [] split = path.split("\\.");
			fos = new FileOutputStream(split[0]+"_decrypted."+ split[1]);
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());

			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				fos.write(decryptOperation((byte)content));
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
					fis.close();
					fos.close();
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		setChanged();
		this.notifyObservers(new EventInfo(System.currentTimeMillis(),1,"Decryption ended successfuly..."));
	}
	
	protected void changeKey(byte key)
	{
		this.k.setKey1(key);
	}

	protected CipherType getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	

	public abstract void generateKey();
	
	protected void setKey(Key k)
	{
		this.k = k;
	}
	
	protected Key getKey()
	{
		return this.k;
	}
	
	protected byte getKeyValue()
	{
		return this.getKey().getKey1();
	}
	
	
	protected abstract byte encryptOperation(byte content);
	protected abstract byte decryptOperation(byte content);

}

