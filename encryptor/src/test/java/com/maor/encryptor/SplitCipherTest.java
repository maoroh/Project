package com.maor.encryptor;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class SplitCipherTest extends AbstractCipherTest{
	@Before
	public void setUp() throws Exception {
		cipher = new SplitCipher(CipherType.Split, new CaesarCipher(CipherType.Caesar));
	}

	@Test
	@Override
	public void testEncrypt() throws IOException
	{
		/*/
		 * Encryption Test 1 
		 * Input: 
		 * Byte array of 10, 20 , 34, 42;
		 * The key is : 10 ,30
		 * Expected : 10 + 10 = 20 , 20  + 30 = 50 , 34 + 10 = 44, 42 + 30 = 72;
		 */
		this.setInput(new byte [] {(byte)10,(byte)20,(byte)34,(byte)42});
	    this.setKey(new Key(((byte)10),((byte) 30)));
	    this.setOutput(new byte [] {(byte)20,(byte)50,(byte)44,(byte)72} );
	    super.testEncrypt();  
	}
	
	@Test
	@Override
	public void testDecrypt() throws IOException
	{
		/*/
		 * Decryption Test 1 
		 * Input: 
		 * Byte array of 20, 50 , 44 , 72;
		 * The key is : 10 ,30
		 * Expected : 20 - 10 = 10 , 50 - 30 = 20 , 44 - 10 = 34, 72 - 30 = 42;
		 */
		this.setInput(new byte [] {(byte)20,(byte)50,(byte)44,(byte)72});
	    this.setKey(new Key(((byte)10),((byte) 30)));
	    this.setOutput(new byte [] {(byte)10,(byte)20,(byte)34,(byte)42} );
	    super.testDecrypt();  
		
	}



}
