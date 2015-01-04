package server;

import java.io.DataOutputStream;
import java.io.IOException;

import utilities.Request;
import utilities.Response;
import utilities.ResponseResCode;
import utilities.User;
import utilities.Chess;
import utilities.GameUser;
import utilities.Table;
import utilities.XStreamUtil;

public class GameMoveAction implements ServerAction{
	
	//Request
	private Request request;
	
	public GameMoveAction(Request request) {
		this.request = request;
	}
	
	public void execute() {
		
		Chess chess = request.getChess();
		//get the chess pos
		Integer i = chess.getI();
		Integer j = chess.getJ();
		
		//user
		GameUser user = request.getGameUser();
		
		//get chess' color
		String color = chess.getColor();
		//get table		
		Table table = request.getTable();
		//set in server chess board
		Chess[][] chessArray = GameInfo.tableChesses.get(table.getTableNumber());
		chessArray[i][j].setColor(color);
		//send to opponent
		GameUser opponent = table.getAnotherUser(user);
		printToOpponent(opponent, chess, user);
		
		//judge win
		boolean win = validateWin(chessArray, chessArray[i][j]);
		if (opponent == null) win = true;
		
		//game over
		if (win) {
			//tell winner
			tellWin(user);
			
			//tell loser
			tellLost(opponent);
			
			//set ready false
			opponent.setReady(false);
			user.setReady(false);
		}
	}
	
	//send to opponent
	private void printToOpponent(GameUser opponent, Chess chess, GameUser user) {
		
		if (opponent != null) {
			Response response = new Response(ResponseResCode.OPPONENT_MOVED, user);
			response.setChess(chess);
			String resString = response.toXML();
			
			try {
				DataOutputStream outstream = new DataOutputStream(opponent.getServerSocket().getOutputStream());
				outstream.writeBytes(resString + "\n");
				
				System.out.println(resString);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//tell winner
	private void tellWin(GameUser user) {
		
		Response response = new Response(ResponseResCode.GAME_WIN, user);
		String resString = response.toXML();

		DataOutputStream outstream;
		try {
			outstream = new DataOutputStream(user.getServerSocket().getOutputStream());
			outstream.writeBytes(resString + "\n");
			
			System.out.println(resString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//tell loser
	private void tellLost(GameUser opponent) {
		
		Response response = new Response(ResponseResCode.GAME_LOSE, opponent);
		String resString = response.toXML();

		DataOutputStream outstream;
		try {
			outstream = new DataOutputStream(opponent.getServerSocket().getOutputStream());
			outstream.writeBytes(resString + "\n");
			
			System.out.println(resString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//judge if win
	private boolean validateWin(Chess[][] chessArray, Chess chess) {
		boolean win = false;
		if (vertical(chessArray, chess)) win = true;
		if (horizontal(chessArray, chess)) win = true;
		if (bevelUpToDown(chessArray, chess)) win = true;
		if (bevelDownToUp(chessArray, chess)) win = true;
		return win;
	}
	
	//�������
	private boolean vertical(Chess[][] chessArray, Chess chess) {
		//�������ӵ�����
		int count = 0;
		for (int i = 0; i < chessArray.length; i++) {
			if (i == chess.getI()) {
				for (int j = 0; j < chessArray[i].length; j++) {
					Chess c = chessArray[i][j];
					if (c.getColor() != null && c.getColor().equals(chess.getColor())) {
						count++;
					}
				}
			}
		}
		if (count >= GameInfo.LINK_COUNT) return true;
		return false;
	}
	
	//�������
	private boolean horizontal(Chess[][] chessArray, Chess chess) {
		int count = 0;
		for (int j = 0; j < chessArray[0].length; j++) {
			if (j == chess.getJ()) {
				for (int i = 0; i < chessArray.length; i++) {
					Chess c = chessArray[i][j];
					if (c.getColor() != null && c.getColor().equals(chess.getColor())) {
						count++;
					}
				}
			}
		}
		if (count >= GameInfo.LINK_COUNT) return true;
		return false;
	}
	
	//������б�����(������)
	private boolean bevelUpToDown(Chess[][] chessArray, Chess chess) {
		int count = 0;
		//�õ�б�ߵĿ�ʼ
		int min = getMin(chess.getI(), chess.getJ());
		int beginI = chess.getI() - min;
		int beginJ = chess.getJ() - min;
		for (int i = beginI; i < chessArray.length; i++) {
			if (beginJ >= chessArray[0].length) break;
			Chess c = chessArray[i][beginJ];
			if (c.getColor() != null && c.getColor().equals(chess.getColor())) {
				count++;
			}
			beginJ++;
		}
		if (count >= GameInfo.LINK_COUNT) return true;
		return false;
	}
	
	//��i��j�еõ���С��һ��
	private int getMin(int i, int j) {
		if (i > j) return j;
		if (i < j) return i;
		return i;
	}
	
	//������б�����(������)
	private boolean bevelDownToUp(Chess[][] chessArray, Chess chess) {
		int count = 0;
		//�õ���y��ľ���
		int xInstance = chess.getI();
		//�õ���������ά���ֵ�Ĳ�
		int yInstance = chessArray[0].length - 1 - chess.getJ();
		//��������Сֵ
		int min = getMin(xInstance, yInstance);
		//�õ���������ӵ�һά���άֵ
		int beginI = chess.getI() - min;
		int beginJ = chess.getJ() + min;
		for (int i = beginI; i < chessArray.length; i++) {
			if (beginJ < 0) break;
			Chess c = chessArray[i][beginJ];
			if (c.getColor() != null && c.getColor().equals(chess.getColor())) {
				count++;
			}
			beginJ--;
		}
		if (count >= GameInfo.LINK_COUNT) return true;
		return false;
	}
}
