package server;

import utilities.User;

/**
 * User Login Action
 * @author Qing
 *
 */
public class LoginAction implements ServerAction{

	//login user
	private User user;
	
	public LoginAction(User user) {
		this.user = user;
	}
	
	public void execute() {
		
	}
}
