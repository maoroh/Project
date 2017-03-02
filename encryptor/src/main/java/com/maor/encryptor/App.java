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
        Key k = Key.generateKey(CipherType.Caesar);
     
        MultCipher c = new MultCipher();
        c.encrypt((byte)3, path);
        c.decrypt((byte)3, "t.txt.encrypted");
       // in.close();
    }
}
