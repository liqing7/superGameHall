package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import utilities.Response;
import utilities.ResponseResCode;
import utilities.User;

public class GetinGamehallAction implements ServerAction {
	//user
	private User user;
		
	//server socket
	private Socket socket;

	public GetinGamehallAction(User user, Socket socket) {
		this.user = user;
		this.socket = socket;
	}
		
	public void execute() {
		System.out.println("Message arrived getinGameHallAction execute");
		
		Response response = new Response(ResponseResCode.GET_IN_GAMEHALL_SUCC, user);
		String responseString = response.toXML();
		try {
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			
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