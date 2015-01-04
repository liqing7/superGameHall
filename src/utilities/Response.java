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
	
	//game user
	private GameUser gameUser;
	
	public GameUser getGameUser() {
		return gameUser;
	}

	public void setGameUser(GameUser gameUser) {
		this.gameUser = gameUser;
	}

	//game selected;
	private String gameSeleted;
	
	//user's table
	private Table table;
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	//user list
	private Vector<GameUser> userList;
	
	private Table[][] tables;

	public Response(int resCode, User user) {
		this.resCode = resCode;
		this.user = user;
	}
	
	public Response(int resCode, GameUser user) {
		this.resCode = resCode;
		this.gameUser = user;
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