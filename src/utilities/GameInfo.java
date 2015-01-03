package utilities;

import java.util.Hashtable;
import java.util.Vector;

public class GameInfo {

	public static Hashtable<String, Vector<GameUser> > userList;
	
	public static Table[][] tables;

	static {
		userList = new Hashtable<String, Vector<GameUser>>(GameNameList.gameNameList.capacity());
		
		for (String gameName : GameNameList.gameNameList)
		{
			userList.put(gameName, new Vector<GameUser>());
		}
		
		tables = new Table[Constant.TABLE_COLUMN_SIZE][Constant.TABLE_ROW_SIZE];
		int tableNumber = 0;
		for (int i = 0; i < tables.length; i++) {
			for (int j = 0; j < tables[i].length; j++) {
				Table table = new Table(Constant.DEFAULT_IMAGE_WIDTH * i, 
						Constant.DEFAULT_IMAGE_HEIGHT * j, tableNumber);
				tables[i][j] = table;
				tableNumber++;
			}
		}
	}
}
