package project;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.lang.Math;

public class DiscoveryServer {	
	int users = 0;
	List<User> allusers = new ArrayList<User>();
	int port;
	InetAddress serveraddress;
	Socket socket;
	
	//this constructor is called when we first make the discovery server
	//it initializes the ip-address, the port and the socket
	public DiscoveryServer(){
		try { 
			this.port = 4242;
			this.serveraddress = InetAddress.getByName("localhost"); 
			this.socket = new Socket(serveraddress, port);
			}

			catch (IOException io) {
				System.err.println("Client exception : " +io);
			}
	}
	
	//this class represents a user of the system, it has a username and a ip address
	public class User{
		String username;
		InetAddress userip;
		
		public User(String username, InetAddress userip){
			this.username = username;
			this.userip = userip;
		}
	}
	
	public void AddUser(String username,InetAddress ipadres ){
		User user = new User(username,ipadres);
		allusers.add(user);
		users = users + 1;
	}
	
	public void DeleteUser(String username){
		if (FindUser(username) == -1){
			System.err.println("This username is not in our database ");
		}
		else{
			allusers.remove(FindUser(username));
		}
		users = users - 1;
	}
	
	public int FindUser(String username){
		int result = -1;
		for(User user : allusers){
			if(user.username == username){
				result =  allusers.indexOf(user);
			}
		   }
		return result;
	}
	
	public User [] GetRandomUsers(int amountofusers){
		User [] FoundUsers = new User [amountofusers];
		if (users < 3){
			System.out.println("we need more users, please be patient.");
		}
		else{
			for(int i = 0;i<= amountofusers - 1;i++){
				FoundUsers[i] = allusers.get((int )(Math.random() * (users + 1))); //this will generate a random number between 0-#users
			}	
		}
		return FoundUsers;
	}
}

