package userInterface;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Rectangle;

import utilities.ImageUtility;

/**
 * Game resource loader
 * @author LiQing
 *
 */
public class GameResourceLoader {

	public static Image getChessboard() {
		return chessboard;
	}

	public static void setChessboard(Image chessboard) {
		GameResourceLoader.chessboard = chessboard;
	}

	public static Image getBackground() {
		return background;
	}

	public static void setBackground(Image background) {
		GameResourceLoader.background = background;
	}

	public static Image getBLACK_DISK() {
		return BLACK_DISK;
	}

	public static void setBLACK_DISK(Image bLACK_DISK) {
		BLACK_DISK = bLACK_DISK;
	}

	public static Image getWHITE_DISK() {
		return WHITE_DISK;
	}

	public static void setWHITE_DISK(Image wHITE_DISK) {
		WHITE_DISK = wHITE_DISK;
	}

	public static Image getReady() {
		return ready;
	}

	public static void setReady(Image ready) {
		GameResourceLoader.ready = ready;
	}

	public static Image getTool_begin() {
		return tool_begin;
	}

	public static void setTool_begin(Image tool_begin) {
		GameResourceLoader.tool_begin = tool_begin;
	}

	public static Image getTool_drawAndLost() {
		return tool_drawAndLost;
	}

	public static void setTool_drawAndLost(Image tool_drawAndLost) {
		GameResourceLoader.tool_drawAndLost = tool_drawAndLost;
	}

	public static Image getTool_ready() {
		return tool_ready;
	}

	public static void setTool_ready(Image tool_ready) {
		GameResourceLoader.tool_ready = tool_ready;
	}

	public static Image getBlackChess() {
		return BLACK_CHESS;
	}

	public static Image getWhiteChess() {
		return WHITE_CHESS;
	}

	//Chess board image
	private static Image chessboard = ImageUtility.getImage("resource/Fivechess/fiveStoneBoard.jpg");
	
	//background
	private static Image background = ImageUtility.getImage("resource/Fivechess/background.png");
	
	//chess disk
	private static Image BLACK_DISK = ImageUtility.getImage("resource/Fivechess/blackDisk.gif");
	
	private static Image WHITE_DISK = ImageUtility.getImage("resource/Fivechess/whiteDisk.gif");
	
	//black chess
	public final static Image BLACK_CHESS = ImageUtility.getImage("resource/Fivechess/black.gif");
	
	//white chess
	public final static Image WHITE_CHESS = ImageUtility.getImage("resource/Fivechess/white.gif");
	
	//ready
	private static Image ready = ImageUtility.getImage("resource/Fivechess/ready.gif");
	
	//start
	private static Image tool_begin = ImageUtility.getImage("resource/Fivechess/Toolbar_begin.png");
	
	//draw
	private static Image tool_drawAndLost = ImageUtility.getImage("resource/Fivechess/Toolbar_Draw.png");
	
	//bar ready
	private static Image tool_ready = ImageUtility.getImage("resource/Fivechess/Toolbar_ready.png");
	
	//棋盘边缘X坐标
	public final static int CHESS_BOARD_X = 85 + 23;
	
	//棋盘边缘Y坐标
	public final static int CHESS_BOARD_Y = 80 + 23;
	
	//棋盘单元格的宽
	public final static int CHESS_BOARD_CELL_WIDTH = 35;
	//棋盘单元格的高
	public final static int CHESS_BOARD_CELL_HEIGHT = 35;
	
	//手形的鼠标指针
	private final static Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	
	//默认的鼠标指针
	private final static Cursor DEFAULT_CURSOR = Cursor.getDefaultCursor();
	
	
	public static int getChessBoardX() {
		return CHESS_BOARD_X;
	}

	public static int getChessBoardY() {
		return CHESS_BOARD_Y;
	}

	public static int getChessBoardCellWidth() {
		return CHESS_BOARD_CELL_WIDTH;
	}

	public static int getChessBoardCellHeight() {
		return CHESS_BOARD_CELL_HEIGHT;
	}

	public static Cursor getHandCursor() {
		return HAND_CURSOR;
	}

	public static Cursor getDefaultCursor() {
		return DEFAULT_CURSOR;
	}

	//下棋前的黑色棋选择图片
	private final static Image BLACK_SELECT_PLAY_IMAGE = ImageUtility.getImage("images/fivechess/black-play-select.png");
	
	public static Image getBlackSelectPlayImage() {
		return BLACK_SELECT_PLAY_IMAGE;
	}

	public static Image getWhiteSelectPlayImage() {
		return WHITE_SELECT_PLAY_IMAGE;
	}

	//下棋前的白色棋选择图片
	private final static Image WHITE_SELECT_PLAY_IMAGE = ImageUtility.getImage("images/fivechess/white-play-select.png");
	


}
