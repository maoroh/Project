package com.maor.encryptor;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MultCipherTest extends AbstractCipherTest {
	
	@Before
	public void setUp() throws Exception {
		this.cipher = new MultCipher(CipherType.Mult);
		
	}
	
	@Test
	@Override
	public void testEncrypt() throws IOException
	{
		//Regular Test case
		this.setInput(new byte [] {(byte)3,(byte)5});
	    this.setKey(new Key((byte)9));
	    this.setOutput(new byte [] {(byte)3*9,(byte)5*9} );
	    super.testEncrypt();
	    
	    //Overflow test case
	  	this.setInput(new byte [] {(byte)30,(byte)50});
	    this.setKey(new Key((byte)11));
	    this.setOutput(new byte [] {(byte)(30*11),(byte)(50*11)});
	    super.testEncrypt();
	    
  
	}
	
	@Test
	@Override
	public void testDecrypt() throws IOException
	{
		//Regular Test case
		this.setInput(new byte [] {(byte)3*9,(byte)5*9});
	    this.setKey(new Key((byte)9));
	    this.setOutput(new byte [] {(byte)3,(byte)5} );
	    super.testDecrypt();
	    
	    //Overflow test case
	  	this.setInput(new byte [] {(byte)(30*11),(byte)(50*11)});
	    this.setKey(new Key((byte)11));
	    this.setOutput(new byte [] {(byte)(30),(byte)(50)});
	    super.testDecrypt();
	}

}
