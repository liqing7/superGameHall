package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import client.ClientThread;
import utilities.Request;
import utilities.RequestOpCode;
import utilities.User;

/**
 * 
 * @author Qing
 *
 */
public class GamehallListFrame extends JFrame{
	
	//game list
	private Vector<String> gameNameList;
	
	//user
	private User user;
	
	//data output stream
	private DataOutputStream outputStream;
	
	//user info
	private JLabel username;
	
	//Game selection
	private Vector<JRadioButton> gameRadioList;
	
	private ButtonGroup gameButtonGroup;
	
	//Confirm button
	private JButton confirmButton;
	
	//Cancel button
	private JButton cancelButton;
	
	//Constructor
	public GamehallListFrame(User user) {
		
		this.user = user;
		
		initComponent();
		
		buildLayout();
		
		initListeners();
	}
	
	//get all game name
	private void getGameList() {
		
		gameNameList = new Vector<String>();
		
		gameNameList.add("Three Chess");
		gameNameList.add("Shooting");
		gameNameList.add("Five Chess");
		gameNameList.add("Null");
		gameNameList.add("Null");
	}
	
	private void initComponent(){
		
		//get username
		username = new JLabel("Welcome User : " + user.getUsername());
		
		//get game list
		getGameList();
		
		gameButtonGroup = new ButtonGroup();
		gameRadioList = new Vector<JRadioButton>();
		
		for (String gameName : gameNameList) {
			JRadioButton tempButton = new JRadioButton(gameName);
			
			gameRadioList.add(tempButton);
			gameButtonGroup.add(tempButton);
		}
				
		//confirm
		confirmButton = new JButton();
		confirmButton.setText("Confirm");
				
		//cancel
		cancelButton = new JButton();
		cancelButton.setText("Cancel");		
		
	}
	
	private void buildLayout() {
		//user name box
		Box nameBox = Box.createHorizontalBox();
		nameBox.createHorizontalStrut(20);
		nameBox.add(username);
		
		//game list box
		Box radioBox = Box.createVerticalBox();
		//radioBox.add(Box.createHorizontalStrut(30));
		
		for (JRadioButton tempButton : gameRadioList){
			radioBox.add(tempButton);
			radioBox.add(Box.createVerticalStrut(10));
		}
		
		radioBox.add(Box.createHorizontalStrut(30));
				
		//button box
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalStrut(20));
		buttonBox.add(this.confirmButton);
		buttonBox.add(Box.createHorizontalStrut(20));
		buttonBox.add(this.cancelButton);
		buttonBox.add(Box.createHorizontalStrut(20));
		
		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(nameBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(radioBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(buttonBox);
		mainBox.add(Box.createVerticalStrut(20));
		
		this.add(mainBox);
		this.setLocation(300, 200);
		this.setTitle("GameHall Login");
		this.pack();
		this.setVisible(true);
	}
	
	//init button listener
	private void initListeners() {
		//Close
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		
		this.confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Getin();
			}
		});
		this.cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	
	public DataOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(DataOutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	private void Getin() {

		for (JRadioButton tempButton : gameRadioList){
			if (tempButton.isSelected()) {
				String selectedGame = tempButton.getText();
				System.out.println(selectedGame);				
				
				int opCode = RequestOpCode.GET_IN_GAMEHALL;
				Request request = new Request(opCode, user);
				request.setGameSelected(selectedGame);
				String reqString = request.toXML();

				try {
					outputStream.writeBytes(reqString + "\n");
					
					System.out.println(reqString);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				break;
			}
		}
	}
}
