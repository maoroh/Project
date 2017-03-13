package com.maor.encryptor;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;


public class CaesarCipherTest extends AbstractCipherTest {
	 
	@Before
	public void setUp() throws Exception {
		this.cipher = new CaesarCipher(CipherType.Caesar);
		
	}
	
	@Test
	@Override
	public void testEncrypt() throws IOException
	{
		this.setInput(new byte [] {(byte)7,(byte)8});
	    this.setKey(new Key((byte)100));
	    this.setOutput(new byte [] {(byte)107,(byte)108} );
	    super.testEncrypt();
	    
	    this.setInput(new byte [] {(byte)100,(byte)120});
	    this.setKey(new Key((byte)30));
	    this.setOutput(new byte [] {(byte)-126,(byte)-106} );
	    super.testEncrypt();
	}
	
	@Test
	@Override
	public void testDecrypt() throws IOException
	{
		this.setInput(new byte [] {(byte)107,(byte)108});
	    this.setKey(new Key((byte)100));
	    this.setOutput(new byte [] {(byte)7,(byte)8} );
	    super.testDecrypt();
	    
	    this.setInput(new byte [] {(byte)-126,(byte)-106} );
	    this.setKey(new Key((byte)30));
	    this.setOutput(new byte [] {(byte)100,(byte)120});
	    super.testDecrypt();
	    
   
	}

}
