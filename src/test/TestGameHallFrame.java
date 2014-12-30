package test;

import java.util.Vector;

import userInterface.GameHallFrame;
import utilities.Constant;
import utilities.GameUser;
import utilities.Table;

/**
 * Test Game Hall Frame
 * @author Qing
 *
 */
public class TestGameHallFrame {

	public static void main(String[] args) {
		//init tables
		Table[][] tables = new Table[Constant.TABLE_COLUMN_SIZE][Constant.TABLE_ROW_SIZE];
		int tableNumber = 0;
		for (int i = 0; i < tables.length; i++) {
			for (int j = 0; j < tables[i].length; j++) {
				Table table = new Table(Constant.DEFAULT_IMAGE_WIDTH*i, 
						Constant.DEFAULT_IMAGE_HEIGHT*j, tableNumber);
				tables[i][j] = table;
				tableNumber++;
			}
		}
		Vector<GameUser> users = new Vector<GameUser>();
		GameUser u1 = new GameUser("User1", "aaa", true);
		
		GameUser u2 = new GameUser("User2", "aaa", true);
		
		users.add(u1);
		users.add(u2);
		
		GameUser user = new GameUser("me", "aaa", true);

		GameHallFrame g = new GameHallFrame(tables, users, user);
	}

}
