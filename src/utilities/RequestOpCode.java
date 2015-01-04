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
	
	//want to draw
	public final static int GAME_DRAW = 6;
	
	//agree to draw
	public final static int AGREE_TO_DRAW = 7;
	
	//refuse to draw
	public final static int REFUSE_TO_DRAW = 8;
	
	//give up
	public final static int GIVE_UP = 9;
	
	//leave
	public final static int LEAVE = 10;
}
