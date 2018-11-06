package project;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTask extends RecursiveTask<HashResult> {
	
	final byte[] File;
	int blocklength;
	
	public HashTask(final byte[] file, int LengthOfBlock) {
		this.File = file;
		this.blocklength = LengthOfBlock;
	}

	@Override
	protected HashResult compute() {
		if(File.length <= blocklength){
			//compute the hash
			return computeHash(File);
		}else{
			//recursively sub-divide file
			int pivot = (int) Math.floor((File.length)/2); //halfway point in file
			HashTask left = new HashTask(Arrays.copyOfRange(File, 0, pivot),blocklength);
			left.fork();
			HashTask right = new HashTask(Arrays.copyOfRange(File, (pivot + 1), File.length),blocklength);
			HashResult rightHash = right.compute();
			HashResult leftHash = left.join();
			return new HashResult(leftHash,rightHash);
		}
		
	}
		
		HashResult computeHash(final byte[] file) {
			byte [] hash = new byte [0];
			
			MessageDigest digest;
			try {
				digest = MessageDigest .getInstance( "SHA-256");
				hash = digest.digest(file);
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}		
			return new HashResult(hash);
		}
	
}


