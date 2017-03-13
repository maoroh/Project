package com.maor.encryptor;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class XORCipherTest extends AbstractCipherTest {
	
	 
		@Before
		public void setUp() throws Exception {
			this.cipher = new XORCipher(CipherType.XOR);
			
		}
		
		@Test
		@Override
		public void testEncrypt() throws IOException
		{
		//Regular Test case 
		this.setInput(new byte [] {(byte)7,(byte)8});
	    this.setKey(new Key((byte)2));
	    this.setOutput(new byte [] {(byte)5,(byte)10} );
	    super.testEncrypt();
	    
	    //Test case with overflow
	    this.setInput(new byte [] {(byte)128,(byte)20});
	    this.setKey(new Key((byte)100));
	    this.setOutput(new byte [] {(byte)-28,(byte)112} );
	    System.out.println((byte)228);
	    super.testEncrypt();
	    
	  
		}
		
		@Test
		@Override
		public void testDecrypt() throws IOException
		{
		this.setInput(new byte [] {(byte)5,(byte)10});
		this.setKey(new Key((byte)2));
		this.setOutput(new byte [] {(byte)7,(byte)8} );
		super.testDecrypt();
		
		 //Test case with overflow
	    this.setInput(new byte [] {(byte)-28,(byte)112});
	    this.setKey(new Key((byte)100));
	    this.setOutput(new byte [] {(byte)128,(byte)20} );
	    super.testDecrypt();
	    
		    
	    
	   
		}


}
