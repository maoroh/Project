package com.maor.tools;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Random;

import com.maor.cipher.Key;

public final class KeyGenerator {
	
	public static byte generateKey(CipherType type)
	{
		byte [] key = new byte[1];
		if(type == CipherType.Mult)
		{
			do {
				new Random().nextBytes(key);
			} while(key[0] % 2 == 0 || key[0] == 0);
		}
		else new Random().nextBytes(key);
		
		//createKeyFile(k);
		return key[0];
	}
	
	public static void createKeyFile(Key key , String path)
	{
		FileOutputStream out = null;
		 ObjectOutputStream obj_out = null;
		 try {
			 if(path == null) 
			 out = new FileOutputStream("key.bin");
			 else
			 out = new FileOutputStream(path + "/" + "key.bin");
	         obj_out = new ObjectOutputStream(out);
	         obj_out.writeObject(key);

	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
		 
	 finally {
		try {
				obj_out.close();
				out.close();
			}
			catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}

	   }
	}
	
	public static Key readKeyFile(String path)
	{
		FileInputStream in = null;
		ObjectInputStream obj_in = null;
		Key key = null; 
		 try {
	         in = new FileInputStream(path);
	         obj_in = new ObjectInputStream(in);
	         key = (Key) obj_in.readObject();

	      } catch (Exception ex) {
	         ex.printStackTrace();
	         return null;
	      }
	 finally {
		try {
				obj_in.close();
				in.close();
			}
			catch (NullPointerException ex) {
			ex.printStackTrace();
			return null;
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	

	   }
			return key;
	}

}
