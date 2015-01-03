package utilities;


/**
 * Game user object
 * @author Qing
 *
 */
public class GameUser extends User {

	//game name
	private String gameName;

	//is the user ready?
	private boolean isReady;
	
	//is the user sit?
	private boolean isSit;
	
	public GameUser(String id, String username, String password, boolean isRemember) {
		super(id, username, password, isRemember);
		// TODO Auto-generated constructor stub
	}
	
	public GameUser(User user, String gameName) {
		super(user.getId(), user.getUsername(), user.getPassword(), user.getIsRemember());
	
		this.gameName = gameName;
	}
		
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}	
	
	public boolean isSit() {
		return isSit;
	}

	public void setSit(boolean isSit) {
		this.isSit = isSit;
	}

	//Judge if the user sit down
	public boolean hasSitDown(Table[][] tables) {
		for (int i = 0; i < tables.length; i++) {
			for (int j = 0; j < tables[i].length; j++) {
				Table t = tables[i][j];
				Seat ls = t.getLeftSeat();
				Seat rs = t.getRightSeat();
				if (ls.getUser() != null) {
					if (this.getId().equals(ls.getUser().getId())) return true;
				}
				if (rs.getUser() != null) {
					if (rs.getUser().getId() == null) {
						System.out.println("Rs user ID is null");
					}
					if (this.getId().equals(rs.getUser().getId())) return true;
				}
			}
		}
		return false;
	}
}
