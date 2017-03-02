package com.maor.tools;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Random;

import com.maor.encryptor.CipherType;
import com.maor.encryptor.Key;

public final class KeyGenerator {
	
	public static Key generateKey(CipherType type)
	{
		byte [] key = new byte[1];
		if(type == CipherType.Mult)
		{
			do {
				new Random().nextBytes(key);
			} while(key[0] % 2 == 0 || key[0] == 0);
		}
		else new Random().nextBytes(key);

		Key k = new Key(key[0]);
		createKeyFile(k);
		return k;
	}
	
	public static void createKeyFile(Key key)
	{
		FileOutputStream out = null;
		 ObjectOutputStream obj_out = null;
		 try {
	         out = new FileOutputStream("key.bin");
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
	
	public static Key readKeyFile()
	{
		FileInputStream in = null;
		ObjectInputStream obj_in = null;
		Key key = null; 
		 try {
	         in = new FileInputStream("key.bin");
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
		}
	

	   }
			return key;
	}

}
