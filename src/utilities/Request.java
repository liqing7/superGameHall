package utilities;


public class Request {
	
	//request option code
	private int opCode;
	
	//user
	private User user;
	
	//Game user
	private GameUser gameUser;
	
	//game selected
	private String gameSelected;
	
	//table selected
	private Table table;
	
	//seat selected
	private String side;
	
	//isReady
	private boolean isReady;
	
	//chess(used in game_move)
	private Chess chess;

	public GameUser getGameUser() {
		return gameUser;
	}

	public void setGameUser(GameUser gameUser) {
		this.gameUser = gameUser;
	}

	public Chess getChess() {
		return chess;
	}

	public void setChess(Chess chess) {
		this.chess = chess;
	}

	public Request(int opCode, User user)
	{
		this.opCode = opCode;
		this.user = user;
	}
	
	public Request(int opCode, GameUser user)
	{
		this.opCode = opCode;
		this.gameUser = user;
	}
	
	public int getOpCode() {
		return opCode;
	}
	
	public void setOpCode(int opCode) {
		this.opCode = opCode;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getGameSelected() {
		return gameSelected;
	}
	
	public void setGameSelected(String gameString) {
		this.gameSelected = gameString;
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	public String toXML() {
		return XStreamUtil.toXML(this);
	}
}