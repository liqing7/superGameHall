package userInterface;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utilities.Constant;
import utilities.ErrorCode;
import utilities.GameUser;
import utilities.ImageUtility;
import utilities.RequestOpCode;
import utilities.Seat;
import utilities.Table;
import utilities.User;
import utilities.Request;
import utilities.XStreamUtil;


/**
 * Hall panel
 * @author Qing
 *
 */
public class HallPanel extends JPanel {

	private Table[][] tables;
	
	private static Image tableImage;
	
	private static Image seatSeletingImage;
	
	private static Image seatSelectedImage;
	
	//hand cursor
	private final static Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	
	//default cursor
	private final static Cursor DEFAULT_CURSOR = Cursor.getDefaultCursor();
	
	//This user
	private GameUser user;
	
	//Other user
	private Vector<GameUser> userList;
	
	//game selected
	private String gameSelected;
	
	public HallPanel(Table[][] tables, GameUser user, Vector<GameUser> users, String gameString) {
		
		this.tables = tables;
		this.user = user;
		this.userList = users;
		this.gameSelected = gameString;
		
		initComponent();
		
	    this.setPreferredSize(new Dimension(Constant.TABLE_COLUMN_SIZE * Constant.DEFAULT_IMAGE_WIDTH, 
	    		Constant.TABLE_ROW_SIZE * Constant.DEFAULT_IMAGE_HEIGHT));
	    this.revalidate();
	   	
//		buildLayout();
		
		initListeners();
	}

	private void initComponent() {
		// TODO Auto-generated method stub
		tableImage = ImageUtility.getImage("resource/Gamehall/table.png");
		
		seatSeletingImage = ImageUtility.getImage("resource/Gamehall/selecting.png");
		
		seatSelectedImage = ImageUtility.getImage("resource/Gamehall/selected.png");
		
	}
	
//	private void buildLayout() {
//		// TODO Auto-generated method stub
//		
//	}
	
	private void initListeners() {
		// TODO Auto-generated method stub
	    this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				moveMouse(e);
			}
	    });
	    
	    this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sitDown(e);
			}
	    });
	}

	//Sit down
	private void sitDown(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		//get table
		Table table = getTable(x, y);
		if (table != null) {
			//get seat
			Seat seat = getSeat(table, x, y);
			if (seat != null) {
				//Judge if the user sit down
				if (this.user.hasSitDown(this.tables)) {
					JOptionPane.showConfirmDialog(null, ErrorCode.HAS_SIT_DOWN, "Warning", 
							JOptionPane.OK_CANCEL_OPTION);
					
					return;
				}
				
				if (seat.getUser() != null) {
					//There is already a user in seat
					JOptionPane.showConfirmDialog(null, ErrorCode.SEAT_HAS_USER, "Warning", 
							JOptionPane.OK_CANCEL_OPTION);
					
				} else {
					seat.setUser(this.user);
					//can sit, then send message to server
					sendServerSitDown(table, seat.getSide());
				}
			}
		}
	}
	
	//get seat on (x,y)
	private Seat getSeat(Table table, int x, int y) {
		if (table.getLeftSeat().getRange().contains(x, y)) {
			return table.getLeftSeat();
		} else if (table.getRightSeat().getRange().contains(x, y)) {
			return table.getRightSeat();
		}
		return null;
	}
	
	//get table on (x,y)
	private Table getTable(int x, int y) {
		for (int i = 0; i < this.tables.length; i++) {
			for (int j = 0; j < this.tables[i].length; j++) {
				Table t = this.tables[i][j];
				if (t.getRangeRectangle().contains(x, y)) return t;
			}
		}
		return null;
	}
	
	//Send message to server to sit down
	private void sendServerSitDown(Table table, String side) {
		//Build request object
		Request request = new Request(RequestOpCode.GET_IN_SEAT, user);

		request.setGameSelected(gameSelected);
		request.setTable(table);
		request.setSide(side);
			
		try {
			String reqString;
			reqString = request.toXML();
			DataOutputStream outstream = new DataOutputStream(user.getSocket().getOutputStream());
			
			outstream.writeBytes(reqString + "\n");
			
			System.out.println(reqString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//repaint
		this.repaint();
	}

	//Move mouse
	private void moveMouse(MouseEvent e) {
		// TODO Auto-generated method stub
		Graphics g = this.getGraphics();
		int x = e.getX();
		int y = e.getY();
		//get table
		Table table = getTable(x, y);
		if (table != null) {
			if (table.getLeftSeat().getRange().contains(x, y)) {
				
				//left seat
				this.setCursor(HAND_CURSOR);
				//if no user, then change image
				if (table.getLeftSeat().getUser() == null) {
					g.drawImage(seatSeletingImage, table.getLeftSeatBeginX(), 
							table.getLeftSeatBeginY(), this);
				}
			} else if (table.getRightSeat().getRange().contains(x, y)) {
				//right seat
				this.setCursor(HAND_CURSOR);
				//if no user, then change image
				if (table.getRightSeat().getUser() == null) {
					g.drawImage(seatSeletingImage, table.getRightSeatBeginX(),
							table.getRightSeatBeginY(), this);
				}
			} else {
				this.setCursor(DEFAULT_CURSOR);
				this.repaint();
			}
		}
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < this.tables.length; i++) {
			for (int j = 0; j < this.tables[i].length; j++) {
				
				Table table = this.tables[i][j];
				Seat leftSeat = table.getLeftSeat();
				Seat rightSeat = table.getRightSeat();
				
				//Draw table
				g.drawImage(tableImage, table.getBeginX(), 
						table.getBeginY(), this);
				
				//Draw left seat
				if (leftSeat.getUser() != null) {
					Image occupied = seatSelectedImage;
					g.drawImage(occupied, table.getLeftSeatBeginX(),
							table.getLeftSeatBeginY(), this);
				}
				
				//Draw right seat
				if (rightSeat.getUser() != null) {
					Image occupied = seatSelectedImage;
					g.drawImage(occupied, table.getRightSeatBeginX(),
							table.getRightSeatBeginY(), this);
				}
			}
		}
	}

}
