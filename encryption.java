package project;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class encryption {
	
	private static final byte[] keyValue = new byte[] { 'I', 'w', 'i', 'l', 'l', 'P', 'a',
		's', 's', 'g', 'o','o', 'd', 'g', 'r', 'a', 'd', 'e', 's' };
	
	public static byte[] encrypt(byte [] file) throws Exception{
	    Key key = generateKey();
	    
	    Cipher c = Cipher.getInstance("AES");
	    c.init(Cipher.ENCRYPT_MODE, key);
	    
	    byte[] encVal = c.doFinal(file);
	    return encVal;
	}

	public static byte[] decrypt(byte[] encryptedData) throws Exception {
	    Key key = generateKey();
	    
	    Cipher c = Cipher.getInstance("AES");
	    c.init(Cipher.DECRYPT_MODE, key);

	    byte[] decValue = c.doFinal(encryptedData);
	    return decValue;
	}

	private static Key generateKey() throws Exception {
	    Key key = new SecretKeySpec(keyValue, "AES");
	    return key;
	}
}
