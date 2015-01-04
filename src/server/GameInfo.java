package server;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import utilities.Constant;
import utilities.GameNameList;
import utilities.GameUser;
import utilities.Table;
import utilities.Seat;
import utilities.Chess;

public class GameInfo {

	public static Hashtable<String, Vector<GameUser> > userList;
	
	public static Table[][] tables;

	/**
	 * chess array in table 
	 * key is table number
	 */
	public static Map<Integer, Chess[][]> tableChesses = new HashMap<Integer, Chess[][]>();

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
	
	/**
	 * get table according to user id
	 * @param userId
	 * @return
	 */
	public static Table getTable(String userId) {
		for (int i = 0; i < tables.length; i++) {
			for (int j = 0; j < tables[i].length; j++) {
				Table table = tables[i][j];
				Seat ls = table.getLeftSeat();
				if (ls.getUser() != null) {
					GameUser u = ls.getUser();
					if (u.getId().equals(userId)) return table;
				}
				Seat rs = table.getRightSeat();
				if (rs.getUser() != null) {
					GameUser u = rs.getUser();
					if (u.getId().equals(userId)) return table;
				}
			}
		}
		return null;
	}
}
