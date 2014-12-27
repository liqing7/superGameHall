package utilities;

/**
 * Request option code used in request object
 * which is sent to server by client 
 * @author Qing
 *
 */
public class RequestOpCode {

	//login in option code
	public final static int LOGIN = 0;
	
	//register option code
	public final static int REGISTER = 1;
	
	//select a game and go into a gamehall
	public final static int GET_IN_GAMEHALL = 2;
	
	//select a seat
	public final static int GET_IN_SEAT = 3;
	
	//get ready for game starting
	public final static int GAME_READY = 4;
	
	//game move
	public final static int GAME_MOVE = 5;
	
}
