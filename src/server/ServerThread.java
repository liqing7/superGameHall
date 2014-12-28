package server;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;


public class ServerThread extends Thread {
	
	private Socket socket;
	
	private BufferedReader br;
	
	private String line;
	
	private PrintStream ps;
}