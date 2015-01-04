package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

import userInterface.ChessPanel;
import userInterface.FivechessGameFrame;
import userInterface.GameHallFrame;
import userInterface.GameResourceLoader;
import userInterface.GamehallListFrame;
import userInterface.LoginFrame;
import utilities.Constant;
import utilities.GameUser;
import utilities.Response;
import utilities.ResponseResCode;
import utilities.Table;
import utilities.User;
import utilities.XStreamUtil;
import utilities.Chess;

/**
 * Client thread
 * @author Qing
 *
 */
public class ClientThread extends Thread{

	//user
	public static User user;
	
	//user's socket
	private Socket socket;
	
	//output stream
	private DataOutputStream outputStream;
	
	//game selected
	public static String gameSelected;
	
	//input line
	private String line;
	
	//Thread
	private volatile boolean isRun = true;  
	
	//login frame
	private LoginFrame loginFrameInstance;
	
	//game list frame
	private GamehallListFrame gamehallListFrameInstance;
	
	//Game hall frame
	private GameHallFrame gamehallFrameInstance;
	
	//five chess game frame
	private FivechessGameFrame fivechessGameFrameInstance;
	
	public ClientThread(User user, Socket socket, DataOutputStream outputStream, LoginFrame loginFrame) {
		ClientThread.user = user;
		System.out.println("In clienct thread constructor user id : " + ClientThread.user.getId());
		
		this.socket = socket;
		this.outputStream = outputStream;
		this.loginFrameInstance = loginFrame;
	}
	
	public void run() {
		
		try {
			InputStream is;
			is = user.getSocket().getInputStream();
			User userBack;
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			System.out.println("Get in thread");
			
			//System.out.println("yes3");
			//System.out.println(reader.readLine());
			//System.out.println("yes2");
			
			while ((this.line = reader.readLine()) != null) {
				System.out.println(this.line);
				Response response = getResponse(this.line);
				System.out.println("Get in while");
				
				if (response.getResCode() % 2 != 0)
				{
					System.out.println("Some errors occur!");
					JOptionPane.showConfirmDialog(null, "Login Error", "Warning", JOptionPane.OK_CANCEL_OPTION);
					stopMyThread();
				}
				
				switch (response.getResCode()) {
				case ResponseResCode.LOGIN_SUCC:
					//build a game hall list frame
					System.out.println("Logging succeed");
					
					//set login frame invisible
					loginFrameInstance.setVisible(false);
					
					userBack = response.getUser();
					
					//build game hall list frame
					gamehallListFrameInstance = new GamehallListFrame(userBack);
					gamehallListFrameInstance.setOutputStream(outputStream);
					
					break;

				case ResponseResCode.REGISTER_SUCC:
					
					break;
					
				case ResponseResCode.GET_IN_GAMEHALL_SUCC:
					
					System.out.println("Get in GameHall succeed");
					
					gameSelected = response.getGameSeleted();
					System.out.println("clientthread gameselected : " + gameSelected);
					//set gamelist frame invisible
					gamehallListFrameInstance.setVisible(false);
					
					userBack = response.getUser();

					//build game hall frame
					Table[][] tables = response.getTables();
					Vector<GameUser> users = response.getUserList();
					GameUser gameUser = new GameUser(userBack, gameSelected);
					gameUser.setSocket(socket);
					gameUser.setServerSocket(userBack.getServerSocket());
//					System.out.println("2 user" + user.getId());
//					System.out.println("2" + gameUser.getId());
//					if (gameUser.getSocket() == null)
//						System.out.println(true);
//					if (user.getSocket() == null)
//						System.out.println(true);
					gamehallFrameInstance = new GameHallFrame(tables, users, gameUser);
					break;
					
				case ResponseResCode.GET_IN_SEAT_SUCC:
					System.out.println("Get in seat succeed");
					Table table = response.getTable();
					GameUser gameUser2 = response.getGameUser();
					//build game frame
					fivechessGameFrameInstance = new FivechessGameFrame(table, gameUser2);
					
					break;
					
				case ResponseResCode.GAME_READY_SUCC:
					
					break;
					
				case ResponseResCode.GAME_MOVE_SUCC:
					
					break;
					
				case ResponseResCode.UPDATE_TABLES:
					System.out.println("Update tables");
					Table[][] tablesUpdate = response.getTables();
					gamehallFrameInstance.setTables(tablesUpdate);
					gamehallFrameInstance.repaint();
					break;
					
				case ResponseResCode.UPDATE_GAMEFRAME:
					System.out.println("Update game frame");
					
					fivechessGameFrameInstance.newUserIn(response.getGameUser());
					fivechessGameFrameInstance.setTable(response.getTable());
					fivechessGameFrameInstance.refreshUI();
					
					break;
					
				case ResponseResCode.GAME_START:
					System.out.println("Client received start command!");
					//Build chess board
					Chess[][] chessArray = new Chess[Constant.CHESS_BOARD_X_SIZE][Constant.CHESS_BOARD_Y_SIZE];
					for (int i = 0; i < chessArray.length; i++) {
						for (int j = 0; j < chessArray[i].length; j++) {
							int beginX = (GameResourceLoader.CHESS_BOARD_X) - (Constant.CHESS_WIDTH/2) 
								+ GameResourceLoader.CHESS_BOARD_CELL_WIDTH * i;
							int beginY = (GameResourceLoader.CHESS_BOARD_Y) - (Constant.CHESS_HEIGHT/2) 
								+ GameResourceLoader.CHESS_BOARD_CELL_HEIGHT * j;
							chessArray[i][j] = new Chess(beginX, beginY, i, j, null);
						}
					}
					
					ChessPanel gamePanel = fivechessGameFrameInstance.getGamePanel();

					gamePanel.setChessArray(chessArray);
					gamePanel.startGame();
					
					break;
					
				case ResponseResCode.GAME_WIN:
					
					gamePanel= fivechessGameFrameInstance.getGamePanel();
					
					gamePanel.win();
					
					break;
					
				case ResponseResCode.GAME_LOSE:
					
					gamePanel= fivechessGameFrameInstance.getGamePanel();
					
					gamePanel.lost();
					
					break;
					
				case ResponseResCode.OPPONENT_MOVED:
					
					gamePanel= fivechessGameFrameInstance.getGamePanel();
					
					Chess chess = response.getChess();
					
					gamePanel.setChessinBoard(chess);
					gamePanel.myTurn();
					
					break;
				default:
					System.out.println("No action will take");
					break;
				}
				
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Convert xml to Response object
	 * @param xml
	 * @return
	 */
	private Response getResponse(String xml) {
		
		return (Response) XStreamUtil.fromXML(xml);
	}
	
	private void stopMyThread() {
        this.isRun = false;
    }
}
