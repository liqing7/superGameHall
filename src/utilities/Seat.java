package utilities;

import java.awt.Rectangle;

/**
 * A seat in hall table
 * @author Qing
 *
 */
public class Seat {

	//user in the seat
	private User user;
	
	//seat range
	private Rectangle range;
	
	//seat side
	private String side;

	public Seat(User user, Rectangle range, String side) {
		
		this.user = user;
		this.range = range;
		this.side = side;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Rectangle getRange() {
		return range;
	}

	public void setRange(Rectangle range) {
		this.range = range;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

}
