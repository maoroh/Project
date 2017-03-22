package com.maor.encryptor;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.maor.cipher.CaesarCipher;
import com.maor.cipher.DoubleCipher;
import com.maor.cipher.Key;
import com.maor.cipher.MultCipher;
import com.maor.tools.CipherType;

public class DoubleCipherTest extends AbstractCipherTest {
	@Before
	public void setUp() throws Exception {
		cipher = new DoubleCipher(CipherType.Double, new CaesarCipher(CipherType.Caesar),
				new MultCipher(CipherType.Mult));
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
		 * Expected :(10 + 10) * 33  , (20 + 10) * 33, (34 + 10)*33 , (42+10) * 33 ;
		 */
		this.setInput(new byte [] {(byte)10,(byte)20,(byte)34,(byte)42});
	    this.setKey(new Key(((byte)10),((byte) 33)));
	    this.setOutput(new byte [] {(byte)(20*33),(byte)(30*33),(byte)(44*33),(byte)(52*33)} );
	    super.testEncrypt();  
	}
	
	@Test
	@Override
	public void testDecrypt() throws IOException
	{
		/*/
		 * Decryption Test 1 
		 * Input: 
		 * Byte array of 660  , 990, 1452 , 1716;
		 * The key is : 10 ,30
		 * Expected :10,20,34,42;
		 */
		this.setInput(new byte [] {(byte)660,(byte)990,(byte)1452,(byte)1716});
	    this.setKey(new Key(((byte)10),((byte) 33)));
	    this.setOutput(new byte [] {(byte)10,(byte)20,(byte)34,(byte)42} );
	    super.testDecrypt(); 
		
	}

}
