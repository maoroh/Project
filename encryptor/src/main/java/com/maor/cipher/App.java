/**
 * @author maor
 */
package com.maor.cipher;

import java.io.File;
import java.util.Scanner;

import com.maor.user.Menus;

public class App 
{
	
    public static void main( String[] args ) 
    {
    	Scanner in = new Scanner(System.in);
    	File file = new File("settings.xml");
    	int input;
    	if (file.exists())
    	{
    		System.out.println("Previous settings found... load settings? (1-yes, 2- no)");
    		input = in.nextInt();
    		if(input == 1) 
    			Menus.start(in, false);
    		else Menus.start(in, true);
    	}
    	
    	else Menus.start(in, true);

    	in.close();
    }

}
