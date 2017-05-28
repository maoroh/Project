package com.maor.user;

import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.maor.cipher.CaesarCipher;
import com.maor.cipher.Cipher;
import com.maor.cipher.DoubleCipher;
import com.maor.cipher.Key;
import com.maor.cipher.MultCipher;
import com.maor.cipher.ReverseCipher;
import com.maor.cipher.SplitCipher;
import com.maor.cipher.XORCipher;
import com.maor.tools.CipherType;
import com.maor.tools.DirectoryTools;
import com.maor.tools.JAXBTools;
import com.maor.tools.KeyGenerator;

public class Menus {
	
	
	public static void start(Scanner in , boolean mode)
    {
    	boolean encrypt = false;
    	File f = null;
    	Cipher c = null;
    	System.out.println("Choose 1-Encryption or 2-Decryption: ");
    	int a = in.nextInt();
    	in.nextLine();
    	if(a == 1) encrypt = true;
        System.out.println( "Please enter the path of folder or a file : " );
        String path = in.nextLine();
        
        if(mode)
        	 c = Menu(in);
        else c = loadSettings();
        
        
        FileEventHandler info = new FileEventHandler();
        c.addObserver(info);
        
        f = new File(path);
    	while(!f.exists())
    	{
    		System.out.println("Invalid file path , Please enter the path again: ");
    		in.nextLine();
    		path = in.nextLine();
    		f = new File(path);
    	} 
        
        if(encrypt) 
        {
        	if(f.isDirectory()) DirectoryTools.directoryEncryption(path,c,info);
        	else c.encrypt(path);
        	
        }
        else 
        {
        	
        	System.out.println("Enter the key path  : ");
        	Scanner in2 = new Scanner(System.in);
        	String path1  = in2.nextLine();
        	Key k2 = null;
        	while((k2 = KeyGenerator.readKeyFile(path1)) == null)
        	{
        		System.out.println("Invalid key path , please enter path again  : ");
        		path1  = in2.nextLine();
        	}
        
        	if(f.isDirectory()) DirectoryTools.directoryDecryption(path,c,k2,info);
        	else c.decrypt(path,k2);
        	in2.close();
        }
        
      
    }
	
	public static Cipher Menu(Scanner in)
	{
		
    	Cipher c = null;
    	File f = null;
    	CipherType type = null,subType1=null,subType2=null;
		System.out.println("Choose type of cipher  : ");
        System.out.println("1- Caesar , 2- XOR, 3-Mult, 4-Double, 5-Reverse, 6- Split");
        int a = in.nextInt();
        boolean err = true;
        
        while(err)
        {
        	try 
        	{
        		switch (a){
        		case 1:
        			type = CipherType.Caesar;
        			c = new CaesarCipher(type);
        			break;
        		case 2:
        			type = CipherType.XOR;
        			c = new XORCipher(type);
        			break;
        		case 3:
        			type = CipherType.Mult;
        			c = new MultCipher(type);
        			break;
        		case 4:
        			type = CipherType.Double;
        			System.out.println("Choose the first cipher : ");
        			Cipher cDouble1 = subMenu(in);
        			System.out.println("Choose the second cipher : ");
        			Cipher cDouble2 = subMenu(in);
        			c = new DoubleCipher(type,cDouble1,cDouble2);
        			subType1 = cDouble1.getType();
        			subType2 = cDouble2.getType();
        		
        			break;
        		case 5:
        			type = CipherType.Reverse;
        			System.out.println("Choose the cipher : ");
        			Cipher cReversed = subMenu(in);
        			subType1 = cReversed.getType();
        			c = new ReverseCipher(type, cReversed);
        			break;
        		case 6:
        			type = CipherType.Split;
        			System.out.println("Choose the cipher : ");
        			Cipher cSplit = subMenu(in);
        			subType1 = cSplit.getType();
        			c = new SplitCipher(type, cSplit);
        			break;
        		default:   
        			throw new InputMismatchException("Error : Wrong Input");
        			
        		}
        		err = false;
        		break;
        
        	} catch(InputMismatchException e){
        		System.out.println(e.getMessage());
        	}
        	a = in.nextInt();
        	
        }
        UserSettings settings = new UserSettings(type, subType1, subType2);
        JAXBTools.createSettingsXML(settings);
        return c;
	}
    
    
    
    
	public static Cipher subMenu(Scanner in)
    {
    	Cipher c = null;
    	boolean err = true;
        System.out.println("1 - Caesar , 2 - XOR, 3 - Mult");
        in.reset();
        int a = in.nextInt();
      
        while(err)
        {
        try
        {
    	  switch (a){
          case 1:
          	c = new CaesarCipher(CipherType.Caesar);
          	break;
          case 2:
          	c = new XORCipher(CipherType.XOR);
          	break;
          case 3:
          	c = new MultCipher(CipherType.Mult);
          	break;
          	default:
          		throw new InputMismatchException("Error : Wrong Input");
    	  }
    	  err = false;
    	  break;
        }
    	  catch(InputMismatchException e)
    	  {
    		  System.out.println(e.getMessage());
    	  }
        //Read next
        a = in.nextInt();
        }
    
    	 
    	  return c;
    	
    }
	
	
	public static Cipher loadSettings()
	{
		UserSettings settings = JAXBTools.getSettingsFromXML();
		Cipher cipher = null;
		switch(settings.getType())
		{
		case Caesar:
			cipher = new CaesarCipher(CipherType.Caesar);
			break;
		case XOR:
			cipher = new XORCipher(CipherType.XOR);
			break;
		case Mult:
			cipher = new MultCipher(CipherType.Mult);
			break;
		case Double:
			Cipher cDouble1 = getCipherByType(settings.getSubType1());
			Cipher cDouble2 = getCipherByType(settings.getSubType2());
			cipher = new DoubleCipher(CipherType.Double, cDouble1 , cDouble2);
			break;
		case Reverse:
			Cipher cReversed = getCipherByType(settings.getSubType1());
			cipher = new ReverseCipher(CipherType.Reverse, cReversed);
			break;
		case Split:
			Cipher cSplit = getCipherByType(settings.getSubType1());
			cipher = new SplitCipher(CipherType.Split, cSplit);
			break;
		}
		return cipher;
		
		
	}
	
	public static Cipher getCipherByType(CipherType type)
	{
	
		Cipher cipher = null;
		switch(type)
		{
		case Caesar:
			cipher = new CaesarCipher(CipherType.Caesar);
			break;
		case XOR:
			cipher = new XORCipher(CipherType.XOR);
			break;
		case Mult:
			cipher = new MultCipher(CipherType.Mult);
			break;
		default:
			break;
		}
		
		return cipher;
	}
}
