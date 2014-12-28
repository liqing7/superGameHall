package server;

import java.io.IOException;

/**
 * Server Start Point
 * @author Qing
 *
 */
public class ServerStartPoint {

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
