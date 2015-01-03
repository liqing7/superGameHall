package test;

import userInterface.GamehallListFrame;
import utilities.User;

/**
 * test game hall list frame
 * @author Qing
 *
 */
public class TestGamehallListFrame {

	public static void main(String args[]) {
		
		User user = new User("id1", "Test", "Test", true);
		
		new GamehallListFrame(user);
	}
}
