package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import utilities.Constant;
import utilities.GameUser;
import utilities.Request;
import utilities.Response;
import utilities.ResponseResCode;
import utilities.Table;
import utilities.User;

public class GetinSeatAction implements ServerAction{
	
	//user
	private User user;
	
	//game user
	private GameUser gameUser;
	
	private Socket socket;
	
	private Request request;
	
	public GetinSeatAction(Request request, Socket socket) {
		
		this.socket = socket;
		this.request = request;
		this.user = request.getUser();
		this.gameUser = request.getGameUser();
	}
	
	public void execute() {
		Table table = request.getTable();
		//update table list
		
		if (table != null) {
			for (int i = 0; i < GameInfo.tables.length; i++) {
				for (int j = 0; j < GameInfo.tables[i].length; j++) {
					if (table.getTableNumber() == GameInfo.tables[i][j]
							.getTableNumber()) {
						GameInfo.tables[i][j] = table;
						System.out.println("Update table " + i + ' ' + j);
						break;
					}
				}
			}
			
			//build success response object
			Response response = new Response(ResponseResCode.GET_IN_SEAT_SUCC, user);
			response.setTable(table);
			response.setGameUser(gameUser);
			String responseString = response.toXML();
			
			DataOutputStream outStream;
			try {
				outStream = new DataOutputStream(
						socket.getOutputStream());
				
				outStream.writeBytes(responseString + "\n");
				System.out.println(responseString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//send message to user in the same game hall
			String gameSeleted = request.getGameSelected();
			//System.out.println(gameSeleted);
			
			//get user list
			Vector<GameUser> list = GameInfo.userList.get(gameSeleted);
			
			for (GameUser user : list) {
				Socket userSocket = user.getServerSocket();
				System.out.println("user name : " + user.getUsername());
				if (userSocket == null) {
					System.out.println("Socket is null");
				}
				response = new Response(ResponseResCode.UPDATE_TABLES, user);
				//set hall tables
				response.setTables(GameInfo.tables);
				responseString = response.toXML();
				
				try {
					outStream = new DataOutputStream(
							userSocket.getOutputStream());
					
					outStream.writeBytes(responseString + "\n");
					System.out.println(responseString);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} else {
			//build fail response object
			
		}
	}
}
