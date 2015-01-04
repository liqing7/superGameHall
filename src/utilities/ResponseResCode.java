package utilities;

/**
 * Response result code used in response object
 * which is sent to client by server 
 * @author Qing
 *
 */
public class ResponseResCode {

	//succeed in logging in
	public final static int LOGIN_SUCC = 0;
	
	//fail in logging in
	public final static int LOGIN_FAIL = 1;
	
	//succeed in register
	public final static int REGISTER_SUCC = 2;
		
	//fail in register
	public final static int REGISTER_FAIL = 3;
	
	//succeed in getting into gamehall
	public final static int GET_IN_GAMEHALL_SUCC = 4;
	
	//succeed in getting into gamehall
	public final static int GET_IN_GAMEHALL_FAIL = 5;
		
	//select a seat
	public final static int GET_IN_SEAT_SUCC = 6;
	
	//select a seat
	public final static int GET_IN_SEAT_FAIL = 7;
	
	//get ready for game starting
	public final static int GAME_READY_SUCC = 8;
	
	//get ready for game starting
	public final static int GAME_READY_FAIL = 9;
	
	//game move
	public final static int GAME_MOVE_SUCC = 10;
	
	//game move
	public final static int GAME_MOVE_FAIL = 11;
	
	//update tables
	public final static int UPDATE_TABLES = 20;
	
	//game start
	public final static int GAME_START = 22;
	
	//update game frame
	public final static int UPDATE_GAMEFRAME = 24;
	
}
