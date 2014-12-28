package server;

import utilities.User;

public class GameMoveAction implements ServerAction{
	//login user
	private User user;
	
	public GameMoveAction(User user) {
		this.user = user;
	}
	
	public void execute() {
		
	}
}
