package com.maor.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class Cipher
{
	
	public void encrypt(byte key, String path)
	{
		File file = new File(path);
		FileInputStream fis = null;
		FileOutputStream fos = null;
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
		}
	}
	
	public void decrypt(byte key, String path)
	{
		File file = new File(path);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);
			String [] split = path.split("\\.");
			System.out.println(split[0]);
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
		
	}


	public abstract byte encryptOperation(byte content,byte key);
	public abstract byte decryptOperation(byte content,byte key);
}

