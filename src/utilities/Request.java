package utilities;

public class Request {
	
	//request option code
	private int opCode;
	
	//user
	private User user;
	
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
	
	public String toXML() {
		return XStreamUtil.toXML(this);
	}
}