package com.maor.encryptor;

import java.util.Scanner;

import com.maor.tools.KeyGenerator;


public class App 
{
    public static void main( String[] args )
    {
    	Scanner in = new Scanner(System.in);
    	mainMenu(in);
    	in.close();	
    }
    
    public static void mainMenu(Scanner in)
    {
    	boolean encrypt = false;
    	Cipher c = null;
    	
    	System.out.println("Choose 1-Encryption or 2-Decryption: ");
    	int a = in.nextInt();
    	in.nextLine();
    	if(a == 1) encrypt = true;
        System.out.println( "Please enter the path : " );
        String path = in.nextLine();
        
        System.out.println("Choose type of cipher : ");
        System.out.println("1- Caesar , 2- XOR, 3-Mult, 4-Double, 5-Reverse, 6- Split");
        a = in.nextInt();
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
        		System.out.println("Input Error");
        }
        
        UserInfo info = new UserInfo();
        c.addObserver(info);
        if(encrypt) 
        {
        	c.encrypt(path);
        	
        }
        else 
        {
        	System.out.println("Enter the key path  : ");
        	Scanner in2 = new Scanner(System.in);
        	String path1  = in2.nextLine();
        	Key k2 = KeyGenerator.readKeyFile(path1);
        	c.decrypt(path,k2);
        	in2.close();
        }

      
    }
    
    
    
    public static Cipher subMenu(Scanner in)
    {
    	Cipher c ;
        System.out.println("1- Caesar , 2- XOR, 3-Mult");
        in.reset();
        int a = in.nextInt();
      
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
          		c=null;
    }
    	 
    	  return c;
    	
    }
}
