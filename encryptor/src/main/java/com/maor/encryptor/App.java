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
        Key k = Key.generateKey(CipherType.Mult);
        UserInfo info = new UserInfo();
        System.out.println(k.key.get(0));
        MultCipher c = new MultCipher();
        c.addObserver(info);
        c.encrypt(k.key.get(0), path);
        c.decrypt(k.key.get(0), "neta.png.encrypted");
        in.close();
    }
}
