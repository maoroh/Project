package com.maor.encryptor;

import java.util.Scanner;

import com.maor.tools.KeyGenerator;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Please enter the path : " );
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        Key k = KeyGenerator.readKeyFile();
        
        UserInfo info = new UserInfo();
        System.out.println(k.key1);
        SplitCipher c = new SplitCipher((byte)40,(byte)60);
        c.addObserver(info);
        c.encrypt( path);
        c.decrypt("t.txt.encrypted");
        in.close();
    }
}
