package com.maor.encryptor;

import java.util.Scanner;

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
        SplitCipher c = new SplitCipher((byte)40,(byte)60);
        c.addObserver(info);
        c.encrypt( path);
        c.decrypt("t.txt.encrypted");
        in.close();
    }
}
