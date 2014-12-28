package server;

import utilities.User;

public class GameReadyAction implements ServerAction{
	//user
	private User user;
	
	public GameReadyAction(User user) {
		this.user = user;
	}
	
	public void execute() {
		
	}
}
