package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import utilities.Constant;
import utilities.User;

/**
 * server class
 * @author LiQing
 *
 */
public class Server {
	
	//server socket
	private static ServerSocket serverSocket;
	
	//client socket-user list
	private Hashtable<Socket, OutputStream> socketOutstreamList;
	
	//constructor
	public Server() throws IOException {
		try {
			socketOutstreamList = new Hashtable<Socket, OutputStream>();
			
			serverSocket = new ServerSocket(Constant.PORT);
			System.out.println("Server is created and waiting Client to connect...");
			
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client IP = " + socket.getInetAddress().getHostAddress());
				
				DataOutputStream outStream = new 
						DataOutputStream(socket.getOutputStream());
				socketOutstreamList.put(socket, outStream);
				System.out.println("yes");
				new ServerThread(socket).start();
			}
			
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
	}
}

