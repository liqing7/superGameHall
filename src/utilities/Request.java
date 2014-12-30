package utilities;

public class Request {
	
	//request option code
	private int opCode;
	
	//user
	private User user;
	
	//game selected
	private String gameSelected;
	
	//table selected
	
	//seat selected
	
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
	
	public String toXML() {
		return XStreamUtil.toXML(this);
	}
}