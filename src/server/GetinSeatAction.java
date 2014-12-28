package server;

import utilities.User;

public class GetinSeatAction implements ServerAction{
	//user
	private User user;
	
	public GetinSeatAction(User user) {
		this.user = user;
	}
	
	public void execute() {
		
	}
}
