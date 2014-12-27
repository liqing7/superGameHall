package utilities;

import java.io.ObjectInputStream.GetField;
import java.net.Socket;

/**
 * User object
 * @author Qing
 *
 */
public class User {
	
	//user id
	private int id;
	
	//user name
	private String username;
	
	//password
	private String password;
	
	//sex
	private int sex;
	
	//remember?
	private boolean isRemember;
	
	//user's socket
	private Socket socket;
	
	//Constructor
	User(int id, String username, String password, int sex, boolean isRemember) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.isRemember = isRemember;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public boolean getIsRemember() {
		return isRemember;
	}
	
	public void setIsRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}
	
}