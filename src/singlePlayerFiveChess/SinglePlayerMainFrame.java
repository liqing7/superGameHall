package singlePlayerFiveChess;

import javax.swing.JFrame;

/**
 * Single player five chess main frame
 * @author LiQing
 *
 */
public class SinglePlayerMainFrame {

	public SinglePlayerMainFrame() {
		JFrame frame=new JFrame();
		SinglePlayerFivechess fs=new SinglePlayerFivechess();
		fs.init();
		frame.add(fs);
		frame.setSize(500,500);
		frame.setResizable(false);
		frame.setTitle("Single Player Five Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}
