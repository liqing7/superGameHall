package userInterface;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import utilities.GameUser;
import utilities.Table;
import utilities.User;

/**
 * Game hall Frame
 * @author Qing
 *
 */
public class GameHallFrame extends JFrame{

	//hall panel
	private HallPanel hallPanel;
	
	//chat panel
	private JLabel chatLabel;
	
	//user list
	private JLabel userListJLabel;
	
	//user
	private GameUser gameUser;
	
	//tables
	private Table[][] tables;
	
	//user list
	private Vector<GameUser> userlist;
	
	public GameHallFrame(Table[][] tables, Vector<GameUser> userlist, GameUser gameUser) {
		// TODO Auto-generated constructor stub
		this.tables = tables;
		this.userlist = userlist;
		this.gameUser = gameUser;
				
		initComponent();
		
		buildLayout();
	}

	private void initComponent() {
		// TODO Auto-generated method stub
		this.hallPanel = new HallPanel(tables, gameUser, userlist, gameUser.getGameName());
		this.chatLabel = new JLabel("Chat");
		this.userListJLabel = new JLabel("User list");
	}

	private void buildLayout() {
		// TODO Auto-generated method stub
		
		JScrollPane hallScrollPane = new JScrollPane(this.hallPanel);
		hallScrollPane.setMinimumSize(new Dimension(750, 1024 - 305));
		JScrollPane chatScrollPane = new JScrollPane(this.chatLabel);
		chatScrollPane.setMinimumSize(new Dimension(300, 490));
		JScrollPane userScrollPane = new JScrollPane(this.userListJLabel);
		userScrollPane.setMinimumSize(new Dimension(300, 300));
		
		//创建右边的上下分隔
		JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
				userScrollPane, chatScrollPane);
		rightPane.setMinimumSize(new Dimension(300, 400));
		rightPane.setDividerLocation(768 - 550);
		rightPane.setDividerSize(3);
		//创建整个界面的左右分隔
		JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, hallScrollPane, 
				rightPane);
		mainPane.setDividerSize(3);
		mainPane.setDividerLocation(1024 - 305);
		this.add(mainPane);
		
		this.setResizable(false);
		int perfectWidth = 1024;
		int perfectHeight = 768 - 30;
		this.setPreferredSize(new Dimension(perfectWidth, perfectHeight));
		this.pack();
		this.setTitle("Game Hall");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
	}

}
