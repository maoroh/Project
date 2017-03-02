package com.maor.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;

public abstract class Cipher extends Observable
{
	byte key;
	
	public Cipher(byte key)
	{
		this.key = key;
	}
	
	public void encrypt(String path)
	{
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
				fos.write(encryptOperation((byte)content,key));
			}

		} catch (IOException e) {
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
	
	public void decrypt(String path)
	{
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
				fos.write(decryptOperation((byte)content,key));
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
	


	public abstract byte encryptOperation(byte content,byte key);
	public abstract byte decryptOperation(byte content,byte key);
}

