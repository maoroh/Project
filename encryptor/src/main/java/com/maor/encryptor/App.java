package com.maor.encryptor;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Please enter the path : " );
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        CaesarCipher.encrypt(120, path);
        CaesarCipher.decrypt(120, "out.txt");
        in.close();
    }
}
