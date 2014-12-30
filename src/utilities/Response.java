package utilities;

import java.util.Vector;

/**
 * Response object
 * @author LiQing
 *
 */
public class Response {
	
	//response code
	private int resCode;
	
	//user
	private User user;
	
	//game selected;
	private String gameSeleted;
	
	//user list
	private Vector<GameUser> userList;
	
	private Table[][] tables;

	public Response(int resCode, User user) {
		this.resCode = resCode;
		this.user = user;
	}
	
	public int getResCode() {
		return resCode;
	}
	
	public void setResCode(int resCode) {
		this.resCode = resCode;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getGameSeleted() {
		return gameSeleted;
	}

	public void setGameSeleted(String gameSeleted) {
		this.gameSeleted = gameSeleted;
	}
	
	public Table[][] getTables() {
		return tables;
	}

	public void setTables(Table[][] tables) {
		this.tables = tables;
	}

	public Vector<GameUser> getUserList() {
		return userList;
	}

	public void setUserList(Vector<GameUser> userList) {
		this.userList = userList;
	}
	
	public String toXML() {
		return XStreamUtil.toXML(this);
	}
}