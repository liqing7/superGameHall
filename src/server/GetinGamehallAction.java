package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import utilities.GameUser;
import utilities.Response;
import utilities.ResponseResCode;
import utilities.User;

public class GetinGamehallAction implements ServerAction {
	//user
	private User user;
		
	//server socket
	private Socket socket;

	private String gameName;

	public GetinGamehallAction(User user, Socket socket, String gameString) {
		this.user = user;
		this.socket = socket;
		this.gameName = gameString;
	}
		
	public void execute() {
		System.out.println("Message arrived getinGameHallAction execute");
		
		if (judge(gameName)) {
			//add in user list
			Vector<GameUser> list = ServerThread.userList.get(gameName);
			GameUser gameUser = new GameUser(user, gameName);
			list.add(gameUser);
			
			//update online user list
			ServerThread.userList.put(gameName, list);
			
			Response response = new Response(
					ResponseResCode.GET_IN_GAMEHALL_SUCC, user);
			//set user list
			response.setUserList(list);
			//set hall tables
			response.setTables(ServerThread.tables);
			String responseString = response.toXML();
			
			try {
				DataOutputStream outStream = new DataOutputStream(
						socket.getOutputStream());

				//System.out.println(user.getSocket().isClosed());
				//PrintWriter out = new PrintWriter(user.getSocket().getOutputStream(), true);
				//System.out.println(out.checkError());

				outStream.writeBytes(responseString + "\n");
				System.out.println(responseString);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private boolean judge(String gameName) {
		// TODO Auto-generated method stub
		return true;
	}
}