package com.maor.encryptor;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ReverseCipherTest extends AbstractCipherTest {

	@Before
	public void setUp() throws Exception {
		cipher = new ReverseCipher(CipherType.Reverse, new CaesarCipher(CipherType.Caesar));
	}

	@Test
	@Override
	public void testEncrypt() throws IOException
	{
		/*/
		 * Encryption Test 1 
		 * Input: 
		 * Byte array of 10, 20;
		 * The key is : 9
		 * Expected : 10 - 9 = 1 , 20 - 9 = 11;
		 */
		this.setInput(new byte [] {(byte)10,(byte)20});
	    this.setKey(new Key((byte)9));
	    this.setOutput(new byte [] {(byte)1,(byte)11} );
	    super.testEncrypt();
	    
	    /*/
		 * Encryption Test 2
		 * Input: 
		 * Byte array of 1, 2;
		 * The key is : 50
		 * Expected : 1 - 50 = -49 , 2 - 50 = -48;
		 */
		this.setInput(new byte [] {(byte)1,(byte)2});
	    this.setKey(new Key((byte)50));
	    this.setOutput(new byte [] {(byte)(1-50),(byte)(2-50)} );
	    super.testEncrypt();
	   
	}
	
	@Test
	@Override
	public void testDecrypt() throws IOException
	{
	
		/*/
		* Decryption Test 1
		* Input: 
		* Byte array of 1,11;
		* The key is : 9
		* Expected : 1 + 9 = 10 , 11 + 9 = 20;
		*/
		this.setInput(new byte [] {(byte)1,(byte)11});
		this.setKey(new Key((byte)9));
		this.setOutput(new byte [] {(byte)10,(byte)20} );
		super.testDecrypt();
	
	
		/*/
		* Decryption Test 2
		* Input: 
		* Byte array of -49,-48;
		* The key is : 50
		* Expected : -49 + 50 = 1 , -48 + 50 = 2;
		*/
		this.setInput(new byte [] {(byte)-49,(byte)-48});
		this.setKey(new Key((byte)50));
		this.setOutput(new byte [] {(byte)1,(byte)2} );
		super.testDecrypt();
		}


}
