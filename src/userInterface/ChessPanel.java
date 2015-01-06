package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utilities.Chess;
import utilities.Constant;
import utilities.GameUser;
import utilities.ImageUtility;
import utilities.RequestOpCode;
import utilities.Table;
import utilities.Seat;
import utilities.XStreamUtil;
import utilities.Request;
import client.StartGameTask;


/**
 * chess panel
 * @author LiQing
 *
 */
public class ChessPanel extends JPanel {

	//chess array
	private Chess[][] chessArray;
	
	private Table table;
	
	private GameUser user;
		
	//current toolbar image
	private Image currentToolImage;
	
	//start button range
	private Rectangle startRange = new Rectangle(185, 630, 73, 40);
	
	//draw button range
	private Rectangle drawRange = new Rectangle(318, 630, 73, 40);
	
	//lost button range
	private Rectangle lostRange = new Rectangle(460, 630, 73, 40);
	
	//tool bar range
	private Rectangle toolRange = new Rectangle(160, 630, 400, 40);
	
	//left user
	private JLabel leftUserName;
	
	//right
	private JLabel rightUserName;
	
	//is in game?
	private boolean gaming;
	
	public Rectangle chessBoardRange;
	
	//is my turn?
	private boolean myTurn;
	
	//selected chess
	private Image selectImage;
	
	//selected chess x pos
	private int selectImageX = 0;
	
	//selected chess y pos
	private int selectImageY = 0;
	
	//start image
	private Image gameStartImage;
	
	//start image timer
	private StartGameTask startGameTask;
	
	private Timer timer;
	
	public ChessPanel(Table table, GameUser user) {
		this.table = table;
		this.user = user;
		this.setMaximumSize(new Dimension(710, 700));
		this.setMinimumSize(new Dimension(710, 700));
		this.currentToolImage = GameResourceLoader.getTool_begin();
		this.chessBoardRange  = new Rectangle(85, 80, 535, 536);
		this.myTurn = false;
		
		initListeners();
		

	}

	private void initListeners() {
		// TODO Auto-generated method stub
	    this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				mouseMove(e);
			}


	    });
	    this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseClick(e);
			}
	    });
	}
	
	protected void mouseClick(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		if (this.toolRange.contains(x, y)) {
			if (this.gaming) {
				//game already started
				//judge if it is draw option
				if (this.drawRange.contains(x, y)) requestDraw();
				//judge if it is give up option
				if (this.lostRange.contains(x, y)) requestLost();
			} else {
				//game not star
				//judge if it is ready
				if (this.startRange.contains(x, y)) ready();
			}
		}
		if (this.gaming) {
			//判断是否轮到自己下棋
			if (this.myTurn) {
				//判断是否鼠标点击了棋盘
				if (this.chessBoardRange.contains(x, y)) takeChess(x, y);
			}
		}
	}

	//mouse move
	private void mouseMove(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		//判断是否游戏状态
		if (this.gaming) {
			setCursor(this.drawRange.contains(x, y) || this.lostRange.contains(x, y));
			//判断是否轮到自己下棋
			if (this.myTurn) {
				//Judge if the mouse is in the chess board
				if (this.chessBoardRange.contains(x, y)) {
					this.selectImageX = x - Constant.CHESS_WIDTH / 2;
					this.selectImageY = y - Constant.CHESS_HEIGHT / 2;
					this.repaint();
				}
			}
		} else {
			//游戏没有开始, 判断玩家是否已经准备
			if (!this.user.isReady()) setCursor(this.startRange.contains(x, y));
			else setCursor(false);
		}

	}
	
	//set cursor
	private void setCursor(boolean state) {
		if (state) setCursor(GameResourceLoader.getHandCursor());
		else setCursor(GameResourceLoader.getDefaultCursor());
	}
	
	//make move
	private void takeChess(int x, int y) {
		Chess chess = getSelectChess(x, y);
		if (chess != null) {
			//there is already a chess 
			if (chess.getColor() != null) {
				JOptionPane.showConfirmDialog(null, "There is already a chess!", "Message", 
						JOptionPane.OK_CANCEL_OPTION);

			} else {
				chess.setColor(getChessColor());
				this.myTurn = false;
				this.selectImage = null;
				requestTakeChess(chess);
				this.repaint();
			}
		}
	}
	
	//get chess in (x, y)
	private Chess getSelectChess(int x, int y) {
		for (int i = 0; i < this.chessArray.length; i++) {
			for (int j = 0; j < this.chessArray[i].length; j++) {
				Chess c = this.chessArray[i][j];
				if (c.getRange().contains(x, y)) {
					return c;
				}
			}
		}
		return null;
	}
	
	//update chess board
	public void setChessinBoard(Chess chess) {
		int i = chess.getI();
		int j = chess.getJ();
		
		chessArray[i][j].setColor(chess.getColor());
		
	}
	
	//send server move request 
	private void requestTakeChess(Chess chess) {
		
		Request request = new Request(RequestOpCode.GAME_MOVE, user);
		
		request.setTable(table);
		request.setChess(chess);
		
		//send message to server to be ready
		String reqString = request.toXML();
		
		DataOutputStream outstream;
		try {
			outstream = new DataOutputStream(user.getSocket().getOutputStream());
			outstream.writeBytes(reqString + "\n");
			
			System.out.println(reqString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
//		Request request = new Request(
//			"org.crazyit.gamehall.fivechess.server.action.TakeChessAction", 
//			"org.crazyit.gamehall.fivechess.client.action.game.TakeChessAction");
//		request.setParameter("i", chess.getI());
//		request.setParameter("j", chess.getJ());
//		request.setParameter("userId", this.user.getId());
//		request.setParameter("tableNumber", this.table.getTableNumber());
//		request.setParameter("color", chess.getColor());
//		//设置处理胜利的Action
//		request.setParameter("winAction", 
//				"org.crazyit.gamehall.fivechess.client.action.game.WinAction");
//		//设置处理输的Action
//		request.setParameter("lostAction", 
//				"org.crazyit.gamehall.fivechess.client.action.game.LostAction");
//		this.user.getPrintStream().println(XStreamUtil.toXML(request));
	}
	
	//win
	public void win() {
		endGame();
		JOptionPane.showConfirmDialog(null, "You Win!", "Game End", 
				JOptionPane.OK_CANCEL_OPTION);

	}
	
	//lose
	public void lost() {
		endGame();
		JOptionPane.showConfirmDialog(null, "You Lose!", "Game End", 
				JOptionPane.OK_CANCEL_OPTION);
	}
	
	//end game
	private void endGame() {
		this.selectImage = null;
		this.gaming = false;
		this.myTurn = false;
		this.user.setReady(false);
		this.currentToolImage = GameResourceLoader.getTool_begin();
		GameUser opponent = getOtherChessUser();
		if (opponent != null) {
			opponent.setReady(false);
		}
		this.repaint();
	}
	
	/**
	 * 返回对手
	 * @return
	 */
	public GameUser getOtherChessUser() {
		Seat seat = this.table.getUserSeat(this.user);
		Seat otherSeat = this.table.getAnotherSeat(seat);
		return otherSeat.getUser();
	}
 	
	//user is ready
	private void ready() {
		if (this.user.isReady()) return;
		this.user.setReady(true);
		this.currentToolImage = GameResourceLoader.getTool_ready();
		this.repaint();
		//send message to server to be ready
		Request request = new Request(RequestOpCode.GAME_READY, user);
		request.setTable(table);
		request.setReady(true);
		String reqString = request.toXML();
		
		DataOutputStream outstream;
		try {
			outstream = new DataOutputStream(user.getSocket().getOutputStream());
			outstream.writeBytes(reqString + "\n");
			
			System.out.println(reqString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}
	
	//show start image
	public void showStartImage() {
		if (this.startImageY >= 330) {
			this.timer.cancel();
			this.timer = null;
			this.gameStartImage = null;
			this.startImageY = 230;
		}
		this.startImageY += 2;
		this.repaint();
	}
	
	//request to draw
	private void requestDraw() {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure to draw?", "Ask", 
				JOptionPane.YES_NO_OPTION);
		
		if (result == 0) {
			//send to server to draw
//			Request request = new Request(
//					"org.crazyit.gamehall.fivechess.server.action.DrawAction", 
//			"org.crazyit.gamehall.fivechess.client.action.game.DrawAction");
//			request.setParameter("userId", this.user.getId());
//			request.setParameter("tableNumber", this.table.getTableNumber());
//			this.user.getPrintStream().println(XStreamUtil.toXML(request));
		} else {
			return;
		}
	}
	
	//send to give up
	public void sendLostRequest() {
//		Request request = new Request(
//		"org.crazyit.gamehall.fivechess.server.action.LostAction", 
//		"org.crazyit.gamehall.fivechess.client.action.game.OpponentLostAction");
//		request.setParameter("userId", this.user.getId());
//		request.setParameter("tableNumber", this.table.getTableNumber());
//		this.user.getPrintStream().println(XStreamUtil.toXML(request));
	}
	
	//opponent want to draw
	public void opponentRequestDraw(String userName) {
		
		int result = JOptionPane.showConfirmDialog(null, userName + " want to draw, agree?", "Ask", 
				JOptionPane.YES_NO_OPTION);
		if (result == 0) agreeDraw();
		if (result == 1) refuseDraw();
	}
	
	//opponent refused to draw
	public void handleRefuseDraw(String userName) {
		JOptionPane.showConfirmDialog(null, "Opponent refused", "Note", 
				JOptionPane.OK_CANCEL_OPTION);
	
	}
	
	//refuse to draw
	private void refuseDraw() {
//		//告诉服务器拒绝和棋
//		Request request = new Request("org.crazyit.gamehall.fivechess.server.action.RefuseDrawAction", 
//				"org.crazyit.gamehall.fivechess.client.action.game.RefuseDrawAction");
//		request.setParameter("userId", this.user.getId());
//		request.setParameter("tableNumber", this.table.getTableNumber());
//		this.user.getPrintStream().println(XStreamUtil.toXML(request));
	}
	
	//agree to draw
	private void agreeDraw() {
		draw();
//		//告诉服务器同意求和
//		Request request = new Request("org.crazyit.gamehall.fivechess.server.action.AgreeDrawAction", 
//				"org.crazyit.gamehall.fivechess.client.action.game.AgreeDrawAction");
//		request.setParameter("userId", this.user.getId());
//		request.setParameter("tableNumber", this.table.getTableNumber());
//		this.user.getPrintStream().println(XStreamUtil.toXML(request));
	}
	
	//draw
	public void draw() {
		JOptionPane.showConfirmDialog(null, "Draw", "Game End", 
				JOptionPane.OK_CANCEL_OPTION);
		endGame();
	}
	
	//Request to give up
	private void requestLost() {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure to give up?", "Sure?", 
				JOptionPane.YES_NO_OPTION);
		if (result == 0) {
			lost();
			sendLostRequest();
		}
	}
	
	//opponent lost
	public void opponentLost() {
		win();
	}
	
	public Table getTable() {
		return this.table;
	}
	
	//start image y pos
	private int startImageY = 250;
	
	//set game state
	public void startGame() {
		System.out.println("Game start");
		
		this.gaming = true;
		this.currentToolImage = GameResourceLoader.getTool_drawAndLost();
		//start game
		if (getUserSide().equals(Constant.LEFT)) {
			//i first
			this.myTurn = true;
			this.gameStartImage = ImageUtility.getImage("resource/fivechess/I_first.png");
		} else {
			//opponent first
			this.gameStartImage = ImageUtility.getImage("resource/fivechess/opponent_first.png");
		}
		this.selectImage = getSelectImage();
		this.startGameTask = new StartGameTask(this);
		this.timer = new Timer();
		timer.schedule(this.startGameTask, 0, 20);
	}
	
	//my turn
	public void myTurn() {
		this.selectImage = getSelectImage();
		this.myTurn = true;
		this.repaint();
	}
	
	//get selected chess image
	private Image getSelectImage() {
		String side = getUserSide();
		
		if (side.equals(Constant.LEFT)) 
			return GameResourceLoader.getBlackSelectPlayImage();
		else 
			return GameResourceLoader.getWhiteSelectPlayImage();
	}
	
	//get user chess color
	private String getChessColor() {
		String side = getUserSide();
		
		if (side.equals(Constant.LEFT)) 
			return Constant.BLACK;
		else 
			return Constant.WHITE;
	}
	
	//get user side
	private String getUserSide() {
		Seat seat = this.table.getUserSeat(this.user);
		
		return seat.getSide();
	}
	

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(GameResourceLoader.getBackground(), 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(GameResourceLoader.getChessboard(), 85, 80, this);
		GameUser lu = this.table.getLeftSeat().getUser();
		GameUser ru = this.table.getRightSeat().getUser();

		//draw left user
		drawLeftUser(g, lu);
		//draw right user
		drawRightUser(g, ru);
		//draw tool bar
		g.drawImage(this.currentToolImage, 160, 630, this);
		//draw start image
		g.drawImage(this.gameStartImage, 208, this.startImageY, this);
		//draw chess board
		paintChessBoard(g);
		//judge if my turn
		if (this.myTurn) {
			g.drawImage(this.selectImage, selectImageX, selectImageY, this);
		}
	}
	
	public Chess[][] getChessArray() {
		return chessArray;
	}

	public void setChessArray(Chess[][] chessArray) {
		this.chessArray = chessArray;
	}

	public GameUser getUser() {
		return user;
	}

	public void setUser(GameUser user) {
		this.user = user;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setGaming(boolean gaming) {
		this.gaming = gaming;
	}

	//paint chess board
	private void paintChessBoard(Graphics g) {
		if (this.chessArray != null) {
			for (int i = 0; i < this.chessArray.length; i++) {
				for (int j = 0; j < this.chessArray[i].length; j++) {
					Chess chess = this.chessArray[i][j];
					if (chess.getColor() != null) {
						g.drawImage(getChessImage(chess.getColor()), 
								chess.getBeginX(), chess.getBeginY(), null);
					}
				}
			}
		}
	}
	
	//Return chess image according to color
	private Image getChessImage(String color) {
		if (color.equals(Constant.BLACK)) return GameResourceLoader.BLACK_CHESS;
		else return GameResourceLoader.WHITE_CHESS;
	}
	
	//Draw left user
	private void drawLeftUser(Graphics g, GameUser lu) {
		if (lu != null) {
			Font myFont = new Font("Baseline", Font.BOLD, 20);
			g.setFont(myFont);
			g.setColor(Color.YELLOW);
			g.drawString(lu.getUsername(), 30, 360);//draw user name

		}
		//draw black chess disk
		g.drawImage(GameResourceLoader.getBLACK_DISK(), 25, 230, this);
	}
	
	//Draw right user
	private void drawRightUser(Graphics g, GameUser ru) {
		if (ru != null) {
			Font myFont = new Font("Baseline", Font.BOLD, 20);
			g.setFont(myFont);
			g.setColor(Color.YELLOW);
			g.drawString(ru.getUsername(), 645, 360);//draw user name

		}
		//draw white chess disk
		g.drawImage(GameResourceLoader.getWHITE_DISK(), 640, 230, this);
	}
	
	public boolean isGaming() {
		return this.gaming;
	}

	
}
