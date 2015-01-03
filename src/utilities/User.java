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
	private String id;
	
	//user name
	private String username;
	
	//password
	private String password;
	
	//sex
	private int sex;
	
	//remember?
	private boolean isRemember;
	
	//user's client socket
	private Socket socket;
	
	//user's server socket
	private Socket serverSocket;

	//Register Constructor
	public User(String id, String username, String password, int sex) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
	}
	
	public User(String id, String username, String password, boolean isRemember) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.isRemember = isRemember;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
	
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Socket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(Socket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void setRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}
}