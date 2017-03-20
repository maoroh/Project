/**
 * @author maor
 */
package com.maor.cipher;
import java.util.Scanner;
import com.maor.tools.Menus;


public class App 
{
	
    public static void main( String[] args ) 
    {
    	
    	Scanner in = new Scanner(System.in);
    	Menus.mainMenu(in);
    	in.close();
    }


}
