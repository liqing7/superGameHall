package userInterface;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import utilities.GameUser;
import utilities.Table;
import utilities.XStreamUtil;
import utilities.Request;

public class FivechessGameFrame extends JFrame {

//	//User list
//	private UserTable userTable;
	private JLabel userTableJLabel;
//	
//	//Chat panel
//	private ChatPanel chatPanel;
	private JLabel chatLabel;
	
	//Chess panel
	private ChessPanel gamePanel;

	//table
	private Table table;
	
	//user
	private GameUser gameUser;
	
	//user list
	private Vector<GameUser> users;
	
	public FivechessGameFrame(Table table, GameUser gameUser) {
		this.table = table;
		this.gameUser = gameUser;
		
//		//游戏界面与聊天界面使用的集合
//		this.users = getUsers(table, user);
//		//创建玩家列表
//		this.userTable = new UserTable(this.users, this.user);
//		//创建聊天JPanel
//		this.chatPanel = new ChatPanel(this.users, this.user, CHAT_SERVER_ACTION, 
//				CHAT_CLIENT_ACTION);
		
		//Build Game JPanel
		this.gamePanel = new ChessPanel(this.table, this.gameUser);
		
		//build chat 
		JScrollPane chatScrollPane = new JScrollPane(this.chatLabel);
		chatScrollPane.setMinimumSize(new Dimension(300, 490));
		
		//build user list
		JScrollPane userScrollPane = new JScrollPane(this.userTableJLabel);
		userScrollPane.setMinimumSize(new Dimension(300, 300));
		JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
				userScrollPane, chatScrollPane);
		rightPane.setMinimumSize(new Dimension(300, 400));
		
		//build splitpane
		JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				this.gamePanel, rightPane);
		this.add(mainPane);
		mainPane.setDividerLocation(1024 - 315);
		mainPane.setDividerSize(3);
		rightPane.setDividerLocation(768 - 550);
		rightPane.setDividerSize(3);
		
		//set resizable false
		this.setResizable(false);
		int perfectWidth = 1024;
		int perfectHeight = 768 - 30;
		this.setPreferredSize(new Dimension(perfectWidth, perfectHeight));
		this.pack();

		
		initListeners();
	
	}
	
	private void initListeners() {
		// TODO Auto-generated method stub
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				if (gamePanel.isGaming()) {
					//quit game
					int result = JOptionPane.showConfirmDialog(null, "Are you sure to quit?", "Warning", 
							JOptionPane.OK_CANCEL_OPTION);
					if (result == 1) {
						setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						
					} else {
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						//send give up message to server
						gamePanel.sendLostRequest();
						leave();
					}
				} else {
					leave();
				}
			}
		});
	}

	//a new user in
	public void newUserIn(GameUser user) {
		this.users.add(user);
		refreshUI();
	}
	
	//user quit
	public void userExit(GameUser user) {
		//remove this user in list
		for (Iterator it = this.users.iterator(); it.hasNext();) {
			GameUser u = (GameUser)it.next();
			if (u.getId().equals(user.getId())) {
				it.remove();
			}
		}
		refreshUI();
	}
	
	//refresh ui
	public void refreshUI() {
		this.gamePanel.repaint();
//		this.chatPanel.refreshJComboBox();
//		this.userTable.refresh();
	}
	

	//User leave, send message to server
	public void leave() {
		int tableNumber = this.table.getTableNumber();
		String userId = this.user.getId();
		Request request = new Request("org.crazyit.gamehall.fivechess.server.action.LeaveGameAction", 
				"org.crazyit.gamehall.fivechess.client.action.game.LeaveGameAction");
		request.setParameter("userId", userId);
		request.setParameter("tableNumber", tableNumber);
		//设置对手接收到退出后的处理类
		request.setParameter("exitAction", 
				"org.crazyit.gamehall.fivechess.client.action.game.OpponentExitAction");
		destroyUI();
		//设置玩家准备状态
		this.user.setReady(false);
		this.user.getPrintStream().println(XStreamUtil.toXML(request));
	}
	
	//从桌子对象中得到玩家信息(不包括当前玩家)
	private List<ChessUser> getUsers(Table table, ChessUser user) {
		List<ChessUser> users = new ArrayList<ChessUser>();
		ChessUser u1 = table.getLeftSeat().getUser();
		ChessUser u2 = table.getRightSeat().getUser();
		if (u1 != null) users.add(u1);
		if (u2 != null) users.add(u2);
		//从集合中去掉当前玩家
		for (Iterator it = users.iterator(); it.hasNext();) {
			ChessUser u = (ChessUser)it.next();
			if (u.getId().equals(user.getId())) {
				it.remove();
			}
		}
		return users;
	}
	

}
