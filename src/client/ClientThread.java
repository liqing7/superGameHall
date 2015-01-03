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

import userInterface.GameHallFrame;
import userInterface.GamehallListFrame;
import userInterface.LoginFrame;
import utilities.Constant;
import utilities.GameUser;
import utilities.Response;
import utilities.ResponseResCode;
import utilities.Table;
import utilities.User;
import utilities.XStreamUtil;

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
					
					//build game frame
					
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
