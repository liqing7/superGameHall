package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.text.GapContent;

import utilities.Constant;
import utilities.GameUser;
import utilities.Request;
import utilities.RequestOpCode;
import utilities.Seat;
import utilities.XStreamUtil;
import utilities.Table;

/**
 * server thread
 * @author Qing
 *
 */
public class ServerThread extends Thread {
	
	private Socket socket;
	
	private BufferedReader br;
	
	private String line;
	
	private PrintStream ps;
	
	private Vector<String> gameNameList;
	
	public static Hashtable<String, Vector<GameUser> > userList;
	
	public static Table[][] tables;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
		this.userList = new Hashtable<String, Vector<GameUser>>(gameNameList.capacity());
		
		for (String gameName : gameNameList)
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
	
	//get all game name
	private void getGameList() {
		
		gameNameList = new Vector<String>();
		
		gameNameList.add("Three Chess");
		gameNameList.add("Shooting");
		gameNameList.add("Five Chess");

	}
	
	public void run() {
		try {
			this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			//System.out.println("yes3");
			//System.out.println(br.readLine());
			//System.out.println("yes2");
			while ((this.line = br.readLine()) != null) {
				//Obtain request this line			
				System.out.println(this.line);
				Request request = getRequest(this.line);
				
				if (request == null) {
					System.out.println("Request is null");
					break;
				}
				switch (request.getOpCode()) {
				case RequestOpCode.LOGIN:
					new LoginAction(request.getUser(), socket).execute();
					break;

				case RequestOpCode.REGISTER:
					new RegisterAction(request.getUser()).execute();
					break;
					
				case RequestOpCode.GET_IN_GAMEHALL:
					String gameSeleted = request.getGameSelected();
					new GetinGamehallAction(request.getUser(), socket, gameSeleted).execute();
					break;
					
				case RequestOpCode.GET_IN_SEAT:
					new GetinSeatAction(request.getUser()).execute();
					break;
					
				case RequestOpCode.GAME_READY:
					new GameReadyAction(request.getUser()).execute();
					break;
					
				case RequestOpCode.GAME_MOVE:
					new GameMoveAction(request.getUser()).execute();
					break;
					
				default:
					break;
				} 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				System.out.println("SOCKET CLOSE!!!!!!!!!");
				socket.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	//
	private Request getRequest(String xml) {
		try {
			Request r = (Request)XStreamUtil.fromXML(xml);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}