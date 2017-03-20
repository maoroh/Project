package com.maor.tools;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.maor.cipher.Cipher;

public class DirectoryTools {
	
	public static void directoryDecryption(String path, Cipher cipher, Key key)
    {
    	File folder = new File(path);
    	File[] listOfFiles = folder.listFiles();
    	Scanner in = new Scanner(System.in);
    	System.out.println("1-Async or 2- Sync ? ");
    	int a = in.nextInt();
    	if(a == 2)
    	{
    		for (int i = 0; i < listOfFiles.length; i++) {
    	      if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("key.bin")) {
    	    	  cipher.decrypt(listOfFiles[i].getPath(), key);
    	      }
    		}
    	}
    	else if(a == 1)
    	{
    		ManageThreads.createThreads(cipher, listOfFiles,Operation.Decrypt,key);
    	}
    	else
    		{
    			in.close();
    			throw new InputMismatchException("Input Error");
    		}
    		
    	in.close();
	}

    
	public static void directoryEncryption(String path, Cipher cipher)
	{
		File folder = new File(path);
    	File[] listOfFiles = folder.listFiles();
    	Scanner in = new Scanner(System.in);
    	System.out.println("1-Async or 2- Sync ? ");
    	int a = in.nextInt();
		
    	if(a == 2)
    	{
    		for (int i = 0; i < listOfFiles.length; i++) {
    	      if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("key.bin")) {
    	    	  cipher.encrypt(listOfFiles[i].getPath());
    	    }
    		}
    	}
    	else if(a == 1) ManageThreads.createThreads(cipher, listOfFiles,Operation.Encrypt,null);
    	else
    	{
    		in.close();
    		throw new InputMismatchException("Input Error");
    		
    	}
    	
    	in.close();
		
	}

}
