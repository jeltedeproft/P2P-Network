package project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashResult{
	
	byte [] hash;
	
	public HashResult(byte [] hash) {
		this.hash = hash;
	}

	
	public HashResult(HashResult left, HashResult right){
		//combine 2 hashes
		byte [] combined = new byte[left.hash.length + right.hash.length];
		
		for (int i = 0; i < combined.length; ++i)
		{
		    combined[i] = i < left.hash.length ? left.hash[i] : right.hash[i - left.hash.length];
		}
		//hash them again
		byte [] hash = new byte [0];
		MessageDigest digest;
		try {
			digest = MessageDigest .getInstance( "SHA-256");
			hash = digest.digest(combined);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.hash= hash;
	}
}
