package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.ClientThread;
import utilities.Constant;
import utilities.Request;
import utilities.RequestOpCode;
import utilities.User;

/**
 * login frame
 * @author Qing
 *
 */
public class LoginFrame extends JFrame{

	//User name
	private JLabel nameTextLabel;
	private JTextField nameField;
	
	//User password
	private JLabel passwordTextLabel;
	private JTextField passwordField;
	
	//is Remember?
	private JCheckBox isRememberBox;
	
	//login button
	private JButton loginButton;
	
	//Register button
	private JButton registerButton;
	
	//Cancel button
	private JButton cancelButton;
	
	private User user;
	
	private Socket socket;
	
	private DataOutputStream outstream;
	
	public LoginFrame() {
		
		initComponent();
		
		buildLayout();
		
		initListeners();
	}
	
	public void initComponent(){
		//User name
		nameTextLabel = new JLabel("Username: ");
		nameField = new JTextField(20);
			
		//User password
		passwordTextLabel = new JLabel("Password: ");
		passwordField = new JTextField(20);
				
		//Remember user
		isRememberBox = new JCheckBox("Remember Me");
			
		//login
		loginButton = new JButton();
		loginButton.setText("Login");
				
		//register 
		registerButton = new JButton();
		registerButton.setText("Register");
				
		//cancel
		cancelButton = new JButton();
		cancelButton.setText("Cancel");		
		
	}
	
	public void buildLayout() {
		//name box
		Box nameBox = Box.createHorizontalBox();
		nameBox.add(Box.createHorizontalStrut(30));
		nameBox.add(this.nameTextLabel);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(this.nameField);
		nameBox.add(Box.createHorizontalStrut(30));
		
		//password box
		Box passwordBox = Box.createHorizontalBox();
		passwordBox.add(Box.createHorizontalStrut(30));
		passwordBox.add(this.passwordTextLabel);
		passwordBox.add(Box.createHorizontalStrut(20));
		passwordBox.add(this.passwordField);
		passwordBox.add(Box.createHorizontalStrut(30));
		
		//button box
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(this.loginButton);
		buttonBox.add(Box.createHorizontalStrut(20));
		buttonBox.add(this.registerButton);
		buttonBox.add(Box.createHorizontalStrut(20));
		buttonBox.add(this.cancelButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		
		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(nameBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(passwordBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(isRememberBox);
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
		
		this.loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login();
			}
		});
		
		this.registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				register();
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
	
	private void login() {
		//if name is empty
		if (this.nameField.getText().equals("")) {
			JOptionPane.showConfirmDialog(null, "Please Input a Name", "Warning", JOptionPane.OK_CANCEL_OPTION);
					
			return;
		}
		
		//if password is empty
		if (this.passwordField.getText().equals("")) {
			JOptionPane.showConfirmDialog(null, "Please Input a Password", "Warning", JOptionPane.OK_CANCEL_OPTION);
							
			return;
		}
		
		//get user
		user = getUser();
		int opCode = RequestOpCode.LOGIN;
		Request request = new Request(opCode, user);
		String reqString = request.toXML();
		
		try {
			outstream = new DataOutputStream(user.getSocket().getOutputStream());
			
			outstream.writeBytes(reqString + "\n");
			System.out.println(reqString);
		
		
			//Start thread
			ClientThread thread = new ClientThread(user, user.getSocket(), outstream, this);
			thread.start();
			
			System.out.println("1 " + user.getId());
		
			//this.setVisible(false);
			//System.out.println("out");
			//System.out.println(socket.isConnected());
			//System.out.println(socket.isClosed());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println(out.checkError());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private User getUser() {
		
		String username = this.nameField.getText();
		String password = this.passwordField.getText();
		String id = UUID.randomUUID().toString();
		boolean isRemember = this.isRememberBox.isSelected();
		user = new User(id, username, password, isRemember);
		//user.setId(id);
		
		//set socket
		try {
			socket = new Socket(InetAddress.getByName(Constant.IP), Constant.PORT);
			user.setSocket(socket);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	private void register() {
		
	}
}
