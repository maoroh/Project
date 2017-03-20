package com.maor.tools;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.maor.cipher.Cipher;

public class ManageThreads {
	
	final static int NUM_OF_THREADS = 4;
	
	
	public static void createThreads(Cipher cipher, File [] listOfFiles , Operation operation , Key key)
	{
			//Create a pool with 4 threads maximum in parallel.
			ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREADS);
			
			for(int i = 0; i < listOfFiles.length; i++)
			{
				if(!listOfFiles[i].getName().equals("key.bin")){
				Runnable worker = new CipherTask(cipher , listOfFiles[i].getPath(),operation,key);
	            executor.execute(worker);}
			}
			
			executor.shutdown();
			try {
				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Error while waiting...");
				e.printStackTrace();
			}
	}
}

final class CipherTask implements Runnable{
	
	private Cipher cipher;
	private String path;
	private Key key;
	private Operation operation;
	
	public CipherTask(Cipher cipher, String path, Operation operation , Key key)
	{
		this.cipher = cipher;
		this.path = path;
		this.operation = operation;
		this.key = key;
	}
	
	@Override
	public void run()
	{
		if(operation == Operation.Encrypt)
			this.cipher.encrypt(path);
		else this.cipher.decrypt(path,key);
	}
	
}

