package utilities;

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
	
	public String toXML() {
		return XStreamUtil.toXML(this);
	}
}