package project;

public class Vault {
	private int size;
	private FilePart [] Database;
	
	public Vault(int size){
		this.size = size;
		Database = new FilePart [size];
	}
	
	synchronized void storeFile(String filename,int userID, int partnumber, byte [] encrypted_filepart){
		FilePart fp = new FilePart(filename,userID,partnumber,encrypted_filepart);
		
		for(int i=0;i<size;i++)
		   {
		       if(Database[i] == null)
		    	   Database[i] = fp;
		           System.out.println("The value at " + i + "is" + Database[i].filename);
		           break;
		   }
		
	}
	
	public class FilePart{
		String filename;
		int userID;
		int partnumber;
		byte [] encrypted_filepart;
		
		public FilePart(String Filename, int userID, int partnumber,byte [] encrypted_filepart){
			this.filename = Filename;
			this.userID = userID;
			this.partnumber = partnumber;
			this.encrypted_filepart = encrypted_filepart;
		}
		
		
	}
}
