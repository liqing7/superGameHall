package test;

import userInterface.FivechessGameFrame;
import utilities.GameUser;
import utilities.Table;

/**
 * 
 * @author LiQing
 *
 */
public class TestFiveChessGameFrame {

	public static void main(String arg[]) {
		
		Table table = new Table(0, 0, 0);
		GameUser gameUser = new GameUser("1", "Qing", "aa", true);
		
		new FivechessGameFrame(table, gameUser).setVisible(true);;
		
	}
}
