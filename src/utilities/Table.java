package utilities;

import java.awt.Rectangle;

/**
 * Table class
 * @author Qing
 *
 */
public class Table {

	//table begin x pos 
	private int beginX;
	
	//table begin y pos
	private int beginY;
	
	//table image
	private String tableImage;
	
	//table no.
	private int tableNumber;
	
	//Table range
	private Rectangle rangeRectangle;
	
	//left seat
	private Seat leftSeat;
	
	//right seat
	private Seat rightSeat;
	
	public Table(int beginX, int beginY, int tableNumber) {
		this.beginX = beginX;
		this.beginY = beginY;
		
		this.tableNumber = tableNumber;
		this.rangeRectangle = new Rectangle(beginX, beginY, Constant.DEFAULT_IMAGE_WIDTH, Constant.DEFAULT_IMAGE_HEIGHT);
		
		this.leftSeat = new Seat(null, new Rectangle(getLeftSeatBeginX(), getLeftSeatBeginY(), 
				Constant.SEAT_WIDTH, Constant.SEAT_HEIGHT), Constant.LEFT);
		this.rightSeat = new Seat(null, new Rectangle(getRightSeatBeginX(), getRightSeatBeginY(), 
				Constant.SEAT_WIDTH, Constant.SEAT_HEIGHT), Constant.RIGHT);
	}

	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}

	public String getTableImage() {
		return tableImage;
	}

	public void setTableImage(String tableImage) {
		this.tableImage = tableImage;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Rectangle getRangeRectangle() {
		return rangeRectangle;
	}

	public void setRangeRectangle(Rectangle rangeRectangle) {
		this.rangeRectangle = rangeRectangle;
	}

	public Seat getLeftSeat() {
		return leftSeat;
	}

	public void setLeftSeat(Seat leftSeat) {
		this.leftSeat = leftSeat;
	}

	public Seat getRightSeat() {
		return rightSeat;
	}

	public void setRightSeat(Seat rightSeat) {
		this.rightSeat = rightSeat;
	}
	
	//get right seat begin x pos
	public int getRightSeatBeginX() {
		return this.beginX + 106;
	}
	
	//get right seat begin y pos
	public int getRightSeatBeginY() {
		return this.beginY + 56;
	}

	//get left seat begin x pos
	public int getLeftSeatBeginX() {
		return this.beginX + 4;
	}
	
	//get left seat begin y pos
	public int getLeftSeatBeginY() {
		return this.beginY + 56;
	} 
	
//	/**
//	 * 得到桌子的左边或者右边的位置
//	 * @param side
//	 * @return
//	 */
//	public Seat getSeat(String side) {
//		if (Seat.LEFT.equals(side)) return this.leftSeat;
//		else return this.rightSeat;
//	}
//		
	//get Table according to table number
	public static Table getTable(Integer tableNumber, Table[][] tables) {
		for (int i = 0; i < tables.length; i++) {
			for (int j = 0; j < tables[i].length; j++) {
				Table table = tables[i][j];
				if (tableNumber == table.getTableNumber()) return table;
			}
		}
		return null;
	}
	
	/**
	 * get user's table
	 * @param user
	 * @return
	 */
	public Seat getUserSeat(GameUser user) {
		if (this.leftSeat.getUser() != null) {
			if (this.leftSeat.getUser() != null) {
				if (user.getId().equals(this.leftSeat.getUser().getId())) {
					return this.leftSeat;
				}
			}
		}
		if (this.rightSeat.getUser() != null) {
			if (this.rightSeat.getUser() != null) {
				if (user.getId().equals(this.rightSeat.getUser().getId())) {
					return this.rightSeat;
				}
			}
		}
		return null;
	}
	
	/**
	 * get the other side seat
	 * @param seat
	 * @return
	 */
	public Seat getAnotherSeat(Seat seat) {
		if (seat.getSide().equals(Constant.LEFT)) return this.rightSeat;
		else return this.leftSeat;
	}
	
//	/**
//	 * 得到对手
//	 * @param user
//	 * @return
//	 */
//	public ChessUser getAnotherUser(ChessUser user) {
//		Seat seat = getUserSeat(user);
//		if (seat == null) return null;
//		Seat otherSeat = getAnotherSeat(seat);
//		return otherSeat.getUser();
//	}
}
