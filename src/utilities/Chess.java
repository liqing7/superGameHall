package utilities;

import java.awt.Rectangle;

public class Chess {

	//chess start x pos
	private int beginX;

	//chess start y pos
	private int beginY;
	
	//chess is in chess board ith row
	private int i;
	
	//chess is in chess board jth col
	private int j;
	
	//chess's color
	private String color;
	
	//Rectange of chess
	private Rectangle range;

	//Client used constructor
	public Chess(int beginX, int beginY, int i, int j, String color) {
		this.beginX = beginX;
		this.beginY = beginY;
		this.i = i;
		this.j = j;
		this.color = color;
		this.range = new Rectangle(beginX, beginY, Constant.CHESS_WIDTH, Constant.CHESS_HEIGHT);
	}
	
	//server used constructor
	public Chess(int i, int j, String color) {
		this.i = i;
		this.j = j;
		this.color = color;
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

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Rectangle getRange() {
		return range;
	}

	public void setRange(Rectangle range) {
		this.range = range;
	}

}
