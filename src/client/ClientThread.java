package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

import userInterface.GamehallListFrame;
import utilities.Constant;
import utilities.Response;
import utilities.ResponseResCode;
import utilities.User;
import utilities.XStreamUtil;

/**
 * Client thread
 * @author Qing
 *
 */
public class ClientThread extends Thread{

	//user
	private User user;
	
	//user's socket
	private Socket socket;
	
	//input line
	private String line;
	
	//Thread
	private volatile boolean isRun = true;  
	
	public ClientThread(User user, Socket socket) {
		this.user = user;
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			InputStream is;
			is = user.getSocket().getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			System.out.println("Get in thread");
			
			//System.out.println("yes3");
			//System.out.println(reader.readLine());
			//System.out.println("yes2");
			
			while ((this.line = reader.readLine()) != null) {
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
					
					new GamehallListFrame(user);
					break;

				case ResponseResCode.REGISTER_SUCC:
					
					break;
					
				case ResponseResCode.GET_IN_GAMEHALL_SUCC:
					
					break;
					
				case ResponseResCode.GET_IN_SEAT_SUCC:
					
					break;
					
				case ResponseResCode.GAME_READY_SUCC:
					
					break;
					
				case ResponseResCode.GAME_MOVE_SUCC:
					
					break;
				default:
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
