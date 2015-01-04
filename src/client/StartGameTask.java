package client;

import java.util.TimerTask;

import userInterface.ChessPanel;

/**
 * timer class using in start game image
 * @author Qing
 *
 */
public class StartGameTask extends TimerTask {

	private ChessPanel gamePanel;
	
	public StartGameTask(ChessPanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		this.gamePanel.showStartImage();
	}
	
}