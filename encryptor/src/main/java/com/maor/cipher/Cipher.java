package com.maor.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Observable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.maor.tools.CipherType;
import com.maor.tools.KeyGenerator;
import com.maor.user.EventEnum;
import com.maor.user.EventInfo;
import com.maor.user.FileEventHandler;

public abstract class Cipher extends Observable implements Cloneable
{
	protected Key k;
	protected CipherType type;
	static final Logger logger = Logger.getLogger(Cipher.class);

	
	public Cipher(CipherType type)
	{
		this.type = type;
		this.k = null;
	}
	
	public void encrypt(String sourcePath)
	{
		File file = new File(sourcePath);
		long time;
		EventInfo event;
			if(k == null)
				generateKey(file.getParent());
			
		FileInputStream fis = null;
		FileOutputStream fos = null;
		time  = System.currentTimeMillis();
		event = new EventInfo(time,EventEnum.Start,"Encryption is starting for " +file.getName() +"...",file.getName());
		setChanged();
		this.notifyObservers(event);

		try {
			File dir;
			if(file.getParent() != null)
				dir = new File(file.getParent() + "/EncryptedFiles");
			else dir = new File("EncryptedFiles");
			dir.mkdir();
			fis = new FileInputStream(file);
			fos = new FileOutputStream(dir.getPath() +"/" + file.getName()+  ".encrypted");
			int content;
			while ((content = fis.read()) != -1) {
				fos.write(encryptOperation((byte)content));
				
			}
			
			setChanged();
			this.notifyObservers(new EventInfo(time ,EventEnum.Finish,"Encryption ended successfuly for "+file.getName() +"...",file.getName()));

		} catch (FileNotFoundException e) {
			logger.error("Encryption failed: File not Found");
			errorEvent(file,e);
			e.printStackTrace();
			
			
		} catch (IOException e) {
			logger.error("Encryption failed: IO error");
			errorEvent(file,e);
			e.printStackTrace();
		
		} finally {
			try {
					fis.close();
					fos.close();
				}
				catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
	}
	
	private void errorEvent(File file, Exception e) {
		// TODO Auto-generated method stub
		setChanged();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		this.notifyObservers(new EventInfo(file.getName(),EventEnum.Error, e.getClass().getName(),e.getMessage(),sw.toString()));
	}


	public void decrypt(String sourcePath , Key k)
	{
		long time;
		EventInfo event;
		this.setKey(k);
		File file = new File(sourcePath);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		time  = System.currentTimeMillis();
		event = new EventInfo(time,EventEnum.Start,"Decryption is starting for " +file.getName() +"...",file.getName());
		setChanged();
		this.notifyObservers(event);
		
		try {
			fis = new FileInputStream(file);
			String [] split =sourcePath.split("\\.");
			fos = new FileOutputStream(split[0]+"_decrypted."+ split[1]);

			int content;
			while ((content = fis.read()) != -1) {
				fos.write(decryptOperation((byte)content));
				
				
			}
		
			setChanged();
			this.notifyObservers(new EventInfo(time ,EventEnum.Finish , "Decryption ended successfuly for " + file.getName() + "...",file.getName()));
			
			
		} catch (FileNotFoundException e) {
			logger.error("Decryption failed: File not Found");
			errorEvent(file,e);
			e.printStackTrace();
			
		} catch (IOException e) {
			logger.error("Decryption failed: IO Error");
			errorEvent(file,e);
			e.printStackTrace();
		} finally {
			try {
					fis.close();
					fos.close();
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	
	}
	
	protected void changeKey(int n)
	{
		if(n==1) this.k.setKey(this.k.getKey1());
		else if(n==2) this.k.setKey(this.k.getKey2());
	}

	public CipherType getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	
	public abstract void generateKey(String path);
	
	public void setKey(Key k)
	{
		this.k = k;
	}
	
	protected Key getKey()
	{
		return this.k;
	}
	
	protected byte getKeyValue()
	{
		return this.getKey().getKey();
	}
	
	public Object clone() throws CloneNotSupportedException{ 
		
		return super.clone();  }
	
	protected abstract byte encryptOperation(byte content);
	protected abstract byte decryptOperation(byte content);
	
	
	

}

