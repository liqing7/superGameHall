package singlePlayerFiveChess;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.*; 
public class SinglePlayerFivechess extends JPanel implements ActionListener,MouseListener,MouseMotionListener,ItemListener
{
	int color_Qizi=0;//棋子的颜色标识 0:黑子 1 :白子
	int intGame_Start=0;//游戏开始标志 0未开始 1游戏中
	int intGame_Body[][]=new int[16][16]; //设置棋盘棋子状态 0 无子 1 黑子 2 白子
	int step=0;//判定棋子步数
	
	Scan scanp=new Scan();
	Scan scanc=new Scan();  
	AutoPlay autoPlay=new AutoPlay();
	Evaluate evaluatep=new Evaluate();
	Evaluate evaluatec=new Evaluate();
	Sort sort=new Sort();
	
	Button b1=new Button("游戏开始");
	Button b2=new Button("重置游戏");
	Label lblWin=new Label(" ");
	Label label=new Label(" ");
	Checkbox ckbHB[]=new Checkbox[2];
	CheckboxGroup ckgHB=new CheckboxGroup();
//	public static void main(String args[])
//	{
//		JFrame frame=new JFrame();
//		FiveStone fs=new FiveStone();
//		fs.init();
//		frame.add(fs);
//		frame.setSize(500,500);
//		frame.setResizable(false);
//		frame.setTitle("Single Player Five Chess");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.show();
//	}
//	
//	private SinglePlayerFivechess fs = new SinglePlayerFivechess();
//	
//	
//	public void begin() {
//		JFrame frame=new JFrame();
//		
//		fs.init();
//		frame.add(fs);
//		frame.setSize(500,500);
//		frame.setResizable(false);
//		frame.setTitle("Single Player Five Chess");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.show();
//	}
	
	public void init()
	{
		setLayout(null);
		//ImageIcon icon=new ImageIcon("chessboard.bmp");
		//JLabel jLabel1 = new JLabel(icon);
		addMouseListener(this);
		add(b1);
		b1.setBounds(330,70,80,30);
		b1.addActionListener(this);
		add(b2);
		b2.setBounds(330,110,80,30);
		b2.addActionListener(this);
		
		ckbHB[0]=new Checkbox("人     先",ckgHB,false);
		ckbHB[0].setBounds(320,30,60,30);
		ckbHB[1]=new Checkbox("电脑先",ckgHB,false);
		ckbHB[1].setBounds(380,30,60,30);
		add(ckbHB[0]);	
		add(ckbHB[1]);		

		ckbHB[0].addItemListener(this);
		ckbHB[1].addItemListener(this);
		add(lblWin);
		lblWin.setBounds(330,150,120,30);
		add(label);
		label.setBounds(330,190,120,30);
		Game_start_csh();
	}

	public void itemStateChanged(ItemEvent e)
	{
		if (ckbHB[1].getState()) //选择黑子先还是白子先
		{
			color_Qizi=1;
		}
		else
		{
			color_Qizi=0;
		}	
	}

	public void actionPerformed(ActionEvent e)
	{
		Graphics g=getGraphics();
		if (e.getSource()==b1)
		{
			Game_start();
		}
		else
		{
			Game_re();
		}
	}

	public void mousePressed(MouseEvent e){}

	public void mouseClicked(MouseEvent e)
	{
		Graphics g=getGraphics();
		int x1,y1;
		x1=e.getX();
		y1=e.getY();
		if (e.getX()<20 || e.getX()>300 || e.getY()<20 || e.getY()>300)
		{
			return;
		}

		if (x1%20>10)
		{
			x1+=20;
		}

		if(y1%20>10)
		{
			y1+=20;
		}

		x1=x1/20*20;
		y1=y1/20*20;
		set_Qizi(x1,y1);

	}

	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}

	public void paint(Graphics g)
	{
		draw_qipan(g);
	}

	public void set_Qizi(int x,int y) //落子
	{
		/*//Image image[]=new Image[3];
		//image[0]=this.getToolkit().getImage(ClassLoader.getSystemResource("003.gif"));
  		//image[1]=this.getToolkit().getImage(ClassLoader.getSystemResource("004.gif"));
  		//MediaTracker mt=new MediaTracker(this);
  		//mt.addImage(image[0],1);
  		//mt.addImage(image[1],2);
  		try
  		{
  			mt.waitForAll();
  		}catch(Exception e){}*/
		if (intGame_Start==0) //判断游戏未开始
		{
			return;
		}

		if (intGame_Body[x/20][y/20]!=0)
		{
			return;
		}
		//Judge jd=new Judge();
		if(intGame_Start==1&&intGame_Body[x/20][y/20]==0)
			step++;
		label.setText("这是第"+step+"步");
		Graphics g=getGraphics();
		//Graphics2D g2D = (Graphics2D)g;
		//BufferedImage bimage1=(BufferedImage)this.createImage(this.getWidth(),this.getHeight());
		//Graphics g = bimage1.createGraphics();
		if (color_Qizi==0)//判断黑子还是白子
		{			
			//g.drawImage(image[0],x*28,y*28,28,28,this);
			if(ckbHB[1].getState())g.setColor(Color.white);
			else g.setColor(Color.black);		
			g.fillOval(x-9,y-9,18,18);
			intGame_Body[x/20][y/20]=color_Qizi+1;
			color_Qizi=1;
			Judge(x/20,y/20);
			/*if(jd.judge(intGame_Body,x/20,y/20))			
			{	
				lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");
				intGame_Start=0;
			}*/
		}
		if(color_Qizi==1 && step>2)
		{
			//g.drawImage(image[1],x*28,y*28,28,28,this);
			if(ckbHB[0].getState())g.setColor(Color.white);
			else g.setColor(Color.black);
			//g.fillOval(x-9,y-9,18,18);
			//intGame_Body[x/20][y/20]=color_Qizi+1;		
			scanp.scan(intGame_Body,1);
            scanc.scan(intGame_Body,2);
            sort.sort(scanp.shape);
            sort.sort(scanc.shape);
            evaluatep.evaluate(scanp.shape);
            evaluatec.evaluate(scanc.shape);
            
            if(evaluatep.max>evaluatec.max){
            	//text_2.setText(evaluatep.max_x+" "+evaluatep.max_y+" "+evaluatep.max); 
            	g.fillOval((evaluatep.max_x)*20-9,(evaluatep.max_y)*20-9, 18, 18);
            	intGame_Body[evaluatep.max_x][evaluatep.max_y]=color_Qizi+1;
            	color_Qizi=0;
            	Judge(evaluatep.max_x,evaluatep.max_y);
            	/*if(jd.judge(intGame_Body,evaluatep.max_x,evaluatep.max_y))
            	{	
    				lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");
    				intGame_Start=0;
    			}*/
            	for(int i=0;i<=15;i++)
            		for(int j=0;j<=15;j++) 
            			for(int h=0;h<5;h++)
            			{
            				scanp.shape[i][j][h]=0;
            				scanc.shape[i][j][h]=0;
            			}
            }
            else
            { 
            	//text_2.setText(evaluatec.max_x+" "+evaluatec.max_y+" "+evaluatec.max); 
            	g.fillOval((evaluatec.max_x)*20-9,(evaluatec.max_y)*20-9,18,18);
             
            	intGame_Body[evaluatec.max_x][evaluatec.max_y]=color_Qizi+1;
            	color_Qizi=0;
            	Judge(evaluatec.max_x,evaluatec.max_y);
            	/*if(jd.judge(intGame_Body,evaluatec.max_x,evaluatec.max_y))
        		{	
    				lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");
    				intGame_Start=0;
    			}*/
            	for(int i=0;i<=15;i++)
            		for(int j=0;j<=15;j++) 
            			for(int h=0;h<5;h++)
            			{
            				scanp.shape[i][j][h]=0;
            				scanc.shape[i][j][h]=0;
            			}
            }   
		}
		if(color_Qizi==1 && step<=2) 	
        {
			if(ckbHB[0].getState())g.setColor(Color.white);
			else g.setColor(Color.black);
			autoPlay.autoPlay(intGame_Body,x/20,y/20);		 
			g.fillOval((autoPlay.x)*20-9,(autoPlay.y)*20-9,18,18);
			intGame_Body[autoPlay.x][autoPlay.y]=color_Qizi+1;
			color_Qizi=0;
			Judge(autoPlay.x,autoPlay.y);
			/*if(jd.judge(intGame_Body,autoPlay.x,autoPlay.x))
			{	
				lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");
				intGame_Start=0;
			}*/
        }                  	
	}

	public String Get_qizi_color(int x)
	{
		if (x==0)
		{
			return "五子连珠白棋";
		}	
		else
		{
			return "五子连珠黑棋";
		}
	}
	
	
	
	public String Get_qizi_color1(int x)
	{
		if (x==1)
		{
			return "五子连珠白棋";
		}	
		else
		{
			return "五子连珠黑棋";
		}
	}

	public void draw_qipan(Graphics G) //画棋盘 15*15
	{
		G.setColor(Color.lightGray);
		G.fill3DRect(10,10,500,500,true);
		G.setColor(Color.black);
		for(int i=1;i<16;i++)
		{
			G.drawLine(20,20*i,300,20*i);
			G.drawLine(20*i,20,20*i,300);
		}
	    G.fillOval(158,158,6,6);
	    G.fillOval(77,77,6,6);
	    G.fillOval(77,237,6,6);
	    G.fillOval(237,77,6,6);
	    G.fillOval(237,237,6,6);
	}

	public void Game_start() //游戏开始
	{
		intGame_Start=1;
		Game_btn_enable(false);
		b2.setEnabled(true);
		if(ckbHB[1].getState()){
			Graphics g=getGraphics();
			g.setColor(Color.black);
			g.fillOval(151, 151, 18, 18);
			intGame_Body[8][8]=color_Qizi+1;
			color_Qizi=0;
		}
	}

	public void Game_start_csh() //游戏开始初始化
	{
		intGame_Start=0;
		step=0;
		Game_btn_enable(true);
		b2.setEnabled(false);
		color_Qizi=0;
		ckbHB[0].setState(true);

		for (int i=0;i<=15 ;i++ )
		{
			for (int j=0;j<=15 ;j++ )
			{
				intGame_Body[i][j]=0;
			}
		}	
		for(int i=0;i<=15;i++)
	     	for(int j=0;j<=15;j++) 
	          	for(int h=0;h<5;h++)
	          	{
	          		scanp.shape[i][j][h]=0;
	          		scanc.shape[i][j][h]=0;
	          	} 
		lblWin.setText("");
		label.setText("");
	}

	public void Game_re() //游戏重新开始
	{
		repaint();
		Game_start_csh();
	}

	public void Game_btn_enable(boolean e) //设置组件状态
	{
		b1.setEnabled(e);
		b2.setEnabled(e);
		ckbHB[0].setEnabled(e);
		ckbHB[1].setEnabled(e);
	}

	public void Judge(int a,int b){
		if (Game_win_1(a,b)) //判断输赢
		{ 
			if(ckbHB[0].getState()){
				lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");
				intGame_Start=0;
			}
			else {
				lblWin.setText(Get_qizi_color1(color_Qizi)+"赢了!");
				intGame_Start=0;
			}
		}

		if (Game_win_2(a,b)) //判断输赢
		{
			if(ckbHB[0].getState()){
				lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");
				intGame_Start=0;
			}
			else {
				lblWin.setText(Get_qizi_color1(color_Qizi)+"赢了!");
				intGame_Start=0;
			}
		}

		if (Game_win_3(a,b)) //判断输赢
		{
			if(ckbHB[0].getState()){
				lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");
				intGame_Start=0;
			}
			else {
				lblWin.setText(Get_qizi_color1(color_Qizi)+"赢了!");
				intGame_Start=0;
			}
		}

		if (Game_win_4(a,b)) //判断输赢
		{
			if(ckbHB[0].getState()){
				lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");
				intGame_Start=0;
			}
			else {
				lblWin.setText(Get_qizi_color1(color_Qizi)+"赢了!");
				intGame_Start=0;
			}
		}
	}
	public boolean Game_win_1(int x,int y) //判断输赢 横
	{
		int x1,y1,t=1;
		x1=x;
		y1=y;

		for (int i=1;i<5 ;i++ )
		{
			if (x1>15)
			{
				break;
			}
			if (x+i<=15&&intGame_Body[x1+i][y1]==intGame_Body[x][y])
			{
				t+=1;
			}
			else
			{
				break;
			}

		}

		for (int i=1;i<5 ;i++ )
		{
			if (x1<=0)
			{
				break;
			}

			if(x-i>0&&intGame_Body[x1-i][y1]==intGame_Body[x][y])
			{
				t+=1;
			}
			else
			{
				break;
			}
		}

		if (t>4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean Game_win_2(int x,int y) //判断输赢 竖
	{
		int x1,y1,t=1;
		x1=x;
		y1=y;

		for (int i=1;i<5 ;i++ )
		{
			if (x1>15)
			{
				break;
			}
			if (y+i<=15&&intGame_Body[x1][y1+i]==intGame_Body[x][y] )
			{
				t+=1;
			}
			else
			{
				break;
			}

		}

		for (int i=1;i<5 ;i++ )
		{
			if (x1<=0)
			{
				break;
			}

			if(y-i>0 && intGame_Body[x1][y1-i]==intGame_Body[x][y])
			{
				t+=1;
			}
			else
			{
				break;
			}
		}

		if (t>4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean Game_win_3(int x,int y) //判断输赢 左斜
	{
		int x1,y1,t=1;
		x1=x;
		y1=y;

		for (int i=1;i<5 ;i++ )
		{
			if (x1>15)
			{
				break;
			}
			if (x+i<=15 && y-i>0&& intGame_Body[x1+i][y1-i]==intGame_Body[x][y])
			{
				t+=1;
			}
			else
			{
				break;
			}

		}

		for (int i=1;i<5 ;i++ )
		{
			if (x1<=0)
			{
				break;
			}

			if(x-i>0 && y+i <=15&& intGame_Body[x1-i][y1+i]==intGame_Body[x][y])
			{
				t+=1;
			}
			else
			{
				break;
			}
		}

		if (t>4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean Game_win_4(int x,int y) //判断输赢 右斜
	{
		int x1,y1,t=1;
		x1=x;
		y1=y;

		for (int i=1;i<5 ;i++ )
		{
			if (x1>15)
			{
				break;
			}
			if (x+i<=15&&y+i<=15&& intGame_Body[x1+i][y1+i]==intGame_Body[x][y])
			{
				t+=1;
			}
			else
			{
				break;
			}

		}

		for (int i=1;i<5 ;i++ )
		{
			if (x1<=0)
			{
				break;
			}

			if(x-i>0&&y-i>0&& intGame_Body[x1-i][y1-i]==intGame_Body[x][y])
			{
				t+=1;
			}
			else
			{
				break;
			}
		}

		if (t>4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

