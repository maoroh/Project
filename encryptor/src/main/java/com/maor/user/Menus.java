package com.maor.tools;

import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.maor.cipher.CaesarCipher;
import com.maor.cipher.Cipher;
import com.maor.cipher.DoubleCipher;
import com.maor.cipher.MultCipher;
import com.maor.cipher.ReverseCipher;
import com.maor.cipher.SplitCipher;
import com.maor.cipher.XORCipher;

public class Menus {
	
	
	public static void mainMenu(Scanner in)
    {
		Map <Integer, CipherType> ciphers = new HashMap<Integer,CipherType>();
		ciphers.put(1, CipherType.Caesar);
		ciphers.put(2, CipherType.XOR);
		ciphers.put(3, CipherType.Mult);
		ciphers.put(4, CipherType.Double);
		ciphers.put(5, CipherType.Reverse);
		ciphers.put(6, CipherType.Split);
		
		
    	boolean encrypt = false;

    	
    	Cipher c = null;
   
    	System.out.println("Choose 1-Encryption or 2-Decryption: ");
    	int a = in.nextInt();
    	in.nextLine();
    	if(a == 1) encrypt = true;
        System.out.println( "Please enter the path of folder or a file : " );
        String path = in.nextLine();
        
        System.out.println("Choose type of cipher  : ");
        System.out.println("1- Caesar , 2- XOR, 3-Mult, 4-Double, 5-Reverse, 6- Split");
        a = in.nextInt();
        boolean err = true;
        
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
        		case 4:
        			System.out.println("Choose the first cipher : ");
        			Cipher cDouble1 = subMenu(in);
        			System.out.println("Choose the second cipher : ");
        			Cipher cDouble2 = subMenu(in);
        			c = new DoubleCipher(CipherType.Double,cDouble1,cDouble2);
        		
        			break;
        		case 5:
        			System.out.println("Choose the cipher : ");
        			Cipher cReversed = subMenu(in);
        			c = new ReverseCipher(CipherType.Reverse, cReversed);
        			break;
        		case 6:
        			System.out.println("Choose the cipher : ");
        			Cipher cSplit = subMenu(in);
        			c = new SplitCipher(CipherType.Split, cSplit);
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
        
        UserInfo info = new UserInfo();
        c.addObserver(info);
        if(encrypt) 
        {
        	File f = new File(path);
        	if(f.isDirectory()) DirectoryTools.directoryEncryption(path,c);
        	else c.encrypt(path);
        	
        }
        else 
        {
        	
        	System.out.println("Enter the key path  : ");
        	Scanner in2 = new Scanner(System.in);
        	String path1  = in2.nextLine();
        	Key k2 = KeyGenerator.readKeyFile(path1);
        	//System.out.println(k2.getKey1());
        	File f = new File(path);
        	if(f.isDirectory()) DirectoryTools.directoryDecryption(path,c,k2);
        	else c.decrypt(path,k2);
        	in2.close();
        }
        
      

      
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
}
