package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import utilities.Constant;
import utilities.Response;
import utilities.ResponseResCode;
import utilities.User;

/**
 * User Login Action
 * @author Qing
 *
 */
public class LoginAction implements ServerAction{

	//login user
	private User user;
	
	//server socket
	private Socket socket;
	
	public LoginAction(User user, Socket socket) {
		this.user = user;
		this.socket = socket;
	}
	
	public void execute() {
		System.out.println("Message arrived login execute");
		
		if (judgeUser())
		{
			//set server socket
			user.setServerSocket(socket);
			Response response = new Response(ResponseResCode.LOGIN_SUCC, user);
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
	
	private boolean judgeUser() {
		
		return true;
	}
}
