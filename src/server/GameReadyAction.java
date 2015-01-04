package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import utilities.Constant;
import utilities.GameUser;
import utilities.Request;
import utilities.ResponseResCode;
import utilities.Table;
import utilities.User;
import utilities.Seat;
import utilities.XStreamUtil;
import utilities.Chess;
import utilities.Response;

/**
 * Game ready action
 * @author LiQing
 *
 */
public class GameReadyAction implements ServerAction{
	//user
	private GameUser user;
	
	//request obj
	private Request request;
	
	//server socket
	private Socket serverSocket;
	
	public GameReadyAction(Request request, Socket socket) {

		this.request = request;
		this.serverSocket = socket;
	}
	
	public void execute() {
		
		user = request.getGameUser();
		//get ready user's table
		Table table = GameInfo.getTable(user.getId());
		user.setReady(true);
		
		//judge if the opponent is ready
		Seat seat = table.getUserSeat(user);
		//get opponent
		GameUser opponent = table.getAnotherSeat(seat).getUser();
		if (opponent != null) {
			
			//another seat have a user, then judge if he is ready
			if (opponent.isReady()) {
				
				//both is ready, then create chess board
				createChessArray(table);
				
				//send message to both user, game start
				Response responseUser1 = new Response(ResponseResCode.GAME_START, opponent);
				Socket opponentSocket = opponent.getServerSocket();
				String opponentResponseString = responseUser1.toXML();
				
				DataOutputStream outputStream1;
				try {
					outputStream1 = new DataOutputStream(opponentSocket.getOutputStream());
					outputStream1.writeBytes(opponentResponseString + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			


				//send to me
				Response responseUser2 = new Response(ResponseResCode.GAME_START, user);
				Socket mySocket = user.getServerSocket();
				String myResponseString = responseUser2.toXML();
				
				DataOutputStream outputStream2;
				try {
					outputStream2 = new DataOutputStream(mySocket.getOutputStream());
					outputStream2.writeBytes(myResponseString + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			

				
			}
			
//			//告诉对手自己准备好了, 使用对手接收准备的客户端处理类
//			String opponentAction = (String)request.getParameter("opponentAction");
//			response.setActionClass(opponentAction);
//			response.setData("userId", userId);
//			opponent.getPrintStream().println(XStreamUtil.toXML(response));
		}
		
	}
	
	private void createChessArray(Table table) {
		//opponent is ready, create chess array
		Chess[][] newChess = new Chess[Constant.CHESS_BOARD_X_SIZE][Constant.CHESS_BOARD_Y_SIZE];
		for (int i = 0; i < newChess.length; i++) {
			for (int j = 0; j < newChess[i].length; j++) {
				Chess c = new Chess(i, j, null);
				newChess[i][j] = c;
			}
		}
		//update the chess array in server
		GameInfo.tableChesses.put(table.getTableNumber(), newChess);
	}
}
