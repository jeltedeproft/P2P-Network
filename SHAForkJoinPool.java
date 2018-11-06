package project;

import java.util.Date;
import java.io.*;

import java.util.concurrent.ForkJoinPool;

public class SHAForkJoinPool {	
	  /** Procedure to read a file and store it in a byte array.*/ 
	  static byte[] read(String FileName){
		  
	    File file = new File(FileName);
	    System.out.println("File size: " + file.length());
	    byte[] result = new byte[(int)file.length()];
	    try {
	      InputStream input = null;
	      try {
	        int totalBytesRead = 0;
	        input = new BufferedInputStream(new FileInputStream(file));
	        while(totalBytesRead < result.length){
	          int bytesRemaining = result.length - totalBytesRead;
	          int bytesRead = input.read(result, totalBytesRead, bytesRemaining); 
	          if (bytesRead > 0){
	            totalBytesRead = totalBytesRead + bytesRead;
	          }
	        }

	        System.out.println("Num bytes read: " + totalBytesRead);
	      }
	      finally {
	    	  System.out.println("Closing input stream.");
	        input.close();
	      }
	    }
	    catch (FileNotFoundException ex) {
	    	System.out.println("File not found.");
	    }
	    catch (IOException ex) {
	    	System.out.println(ex);
	    }
	    return result;
	  }

	
	public static void main(String[] args) {
	    byte[] file_to_transmit = read("input.txt");
	    
		//how far we split up?
		int partlength = 4194304;		
		
		//#processors
		int p = 5;
				
		long before = new Date().getTime();
		
		//Use fork join to split up the file, compute hashes and rehash the hashes to 1 hash
		ForkJoinPool forkJoinPool = new ForkJoinPool(p);
		byte [] result = forkJoinPool.invoke(new HashTask(file_to_transmit, partlength)).hash;
		
		System.out.println("duration: "+(new Date().getTime()-before)/100.0);
		System.out.println("result: "+result);
	}
}
	

