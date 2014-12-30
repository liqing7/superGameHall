package utilities;


public class Request {
	
	//request option code
	private int opCode;
	
	//user
	private User user;
	
	//game selected
	private String gameSelected;
	
	//table selected
	private Table table;
	
	//seat selected
	private String side;
	
	//isReady
	private boolean isReady;

	public Request(int opCode, User user)
	{
		this.opCode = opCode;
		this.user = user;
	}
	
	public int getOpCode() {
		return opCode;
	}
	
	public void setOpCode(int opCode) {
		this.opCode = opCode;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getGameSelected() {
		return gameSelected;
	}
	
	public void setGameSelected(String gameString) {
		this.gameSelected = gameString;
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	public String toXML() {
		return XStreamUtil.toXML(this);
	}
}