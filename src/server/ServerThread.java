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
	
//	private Vector<String> gameNameList;
//		
//	public Vector<String> getGameNameList() {
//		return gameNameList;
//	}
//
//	public void setGameNameList(Vector<String> gameNameList) {
//		this.gameNameList = gameNameList;
//	}

	public ServerThread(Socket socket) {
		//getGameList();
		this.socket = socket;

	}
	
//	//get all game name
//	private void getGameList() {
//		
//		gameNameList = new Vector<String>();
//		
//		gameNameList.add("Five Chess");
//		gameNameList.add("Shooting");
//		gameNameList.add("Single Five Chess");
//
//	}
	
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
					new GetinSeatAction(request, socket).execute();
					break;
					
				case RequestOpCode.GAME_READY:
					new GameReadyAction(request, socket).execute();
					break;
					
				case RequestOpCode.GAME_MOVE:
					new GameMoveAction(request).execute();
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