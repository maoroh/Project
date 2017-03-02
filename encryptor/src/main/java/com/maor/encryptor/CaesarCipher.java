package com.maor.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CaesarCipher {
	
	public static void encrypt(int key, String path)
	{
		File file = new File(path);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream("out.txt");
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());

			int content;
			while ((content = fis.read()) != -1) {
				if(content + key > Byte.MAX_VALUE)
					content = (content + key - Byte.MAX_VALUE - 1) + Byte.MIN_VALUE; 
				else content += key;
				fos.write(content);
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
	
	
	public static void decrypt(int key, String path)
	{
		File file = new File(path);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream("out2.txt");
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());

			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				if(content - key < Byte.MIN_VALUE)
					content = (content - key - Byte.MIN_VALUE + 1) + Byte.MAX_VALUE; 
				else content -= key;
				fos.write(content);
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
}
