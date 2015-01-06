package singlePlayerFiveChess2;

import javax.swing.JPanel;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;

import javax.swing.JOptionPane;

import java.io.InputStream;

import sun.audio.*;
import userInterface.GameResourceLoader;
import utilities.Constant;

import java.awt.event.MouseMotionAdapter;

/**
 * <p>Title: 类说明</p>
 *
 * <p>Description: 棋盘面板</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author goodboy
 * @version 2.1
 */
public class Canvas extends JPanel {
    int size=15; //棋盘大小
    int[][] board=new int[size][size]; //棋盘数据 0:无棋子 1:用户棋子 2:AI棋子
    Color aiColor, userColor; //棋子颜色
    int userX, userY; //用户落子位置
    int lastX=-1, lastY=-1; //AI最后落子位置
    int endX1,endY1,endX2,endY2; //结束成线绘制
    int[] forbidX=new int[5]; //禁手位置横坐标
    int[] forbidY=new int[5]; //禁手位置纵坐标
    String tipText="<html>";

    boolean avail=false; //绘制标志
    boolean drawEndLine=false; //绘制结束直线标志
    boolean forbidStyle=false; //禁手提示标记
    boolean lastAvail=false; //AI最后落子标记
    boolean[] forbidAvail={false,false,false,false,false}; //禁手标记

    public Canvas(){
      try{
        jbInit();
      }
      catch(Exception exception){
        exception.printStackTrace();
      }
    }

    private void jbInit() throws Exception {
      this.setLayout(null);
      this.addMouseListener(new Canvas_this_mouseAdapter(this));
        this.addMouseMotionListener(new Canvas_this_mouseMotionAdapter(this));
    }

    //绘图方法
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      Graphics2D draw = (Graphics2D) g;

      drawBoard(draw); //绘制棋盘

      if(avail){ //绘制图形
        //绘制棋子
        for(int i=0;i<size;i++){
          for(int j=0;j<size;j++){
            if(board[i][j]==1){ //用户棋子
              drawChessman(draw, i*37 + 37, j*37 + 37,userColor);
            }
            else if(board[i][j]==2){ //AI棋子
              drawChessman(draw, i*37 + 37, j*37 + 37,aiColor);
            }
          }
        }
        //突出AI上一个落子位置
        if(lastAvail){
          //draw.setColor(Color.lightGray);
          //draw.fillOval(lastX*40,lastY*40,30,30);
    	  g.drawImage(getChessImage(Constant.WHITE), 
    			  lastX*37 + 37, lastY*37 + 37, null);
        }

        //绘制禁手提示位置
        for(int i=0;i<5;i++){
          if(forbidAvail[i]){
            draw.setColor(Color.magenta);
            draw.drawOval(forbidX[i]*37 + 37,forbidY[i]*37 + 37,30,30);
            draw.drawString("禁手",forbidX[i]*37+5 + 37,forbidY[i]*37 + 37+20);
          }
        }

        //显示结束直线
        if(drawEndLine){
          draw.setColor(Color.magenta);
          draw.drawLine(endX1*37+15 + 37,endY1*37+15 + 37,endX2*37+15 + 37,endY2*37+15 + 37);
        }
      }
    }

    /**
     * <p>Description: 初始化数据</p>
     */
    public void initData(){
      for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
          board[i][j]=0;
        }
      }
      for(int i=0;i<5;i++){
        forbidAvail[i]=false;
      }
      userColor=Color.black;
      aiColor=Color.white;
      lastX=-1;
      lastY=-1;
      drawEndLine=false;
      forbidStyle=false;
      lastAvail=false;
    }

    /**
     * <p>Description: 绘制棋盘</p>
     * @param g Graphics2D对象
     */
    public void drawBoard(Graphics2D g){
      
		g.drawImage(GameResourceLoader.getBackground(), 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(GameResourceLoader.getChessboard_single(), 30, 29, this);
		
		for(int i = 0; i < size; i++) {
			//绘制边框
			g.setColor(Color.blue);
			g.drawLine(15,15+i*37,15+(size-1)*37,15+i*37);
			g.drawLine(15+i*37,15,15+i*37,15+(size-1)*37);
			//绘制标记
			g.setColor(Color.black);
			g.drawString(i+"", 12+i*37, 10);
			g.drawString(i+"", 2, 18+i*37);
		}
    }

    /**
     * <p>Description: 绘制棋子</p>
     * @param g Graphics2D对象
     * @param i 棋子横坐标
     * @param j 棋子纵坐标
     * @param c 棋子颜色
     */
    public void drawChessman(Graphics2D g, int i, int j, Color c){
      g.setColor(c);
      //g.fillOval(i,j,30,30);
      if (c == Color.BLACK) {
    	  g.drawImage(getChessImage(Constant.BLACK), 
  				i, j, null);
      } else {
    	  g.drawImage(getChessImage(Constant.WHITE), 
  				i, j, null);
      }
      
    }

	//Return chess image according to color
	private Image getChessImage(String color) {
		if (color.equals(Constant.BLACK)) return GameResourceLoader.BLACK_CHESS;
		else return GameResourceLoader.WHITE_CHESS;
	}
	
    /**
     * <p>Description: 设置AI最后落子坐标</p>
     * @param i AI最后落子横坐标
     * @param j AI最后落子纵坐标
     */
    public void setLast(int i, int j){
      lastAvail=true;
      lastX=i;
      lastY=j;
    }

    /**
     * <p>Description: 设置结束成线标记</p>
     * @param x1 起点横坐标
     * @param y1 起点纵坐标
     * @param x2 终点横坐标
     * @param y2 终点纵坐标
     */
    public void setLine(int x1, int y1, int x2, int y2){
      drawEndLine=true;
      endX1=x1;
      endY1=y1;
      endX2=x2;
      endY2=y2;
    }

    /**
     * <p>Description: 设置禁手提示位置</p>
     * @param x 提示位置横坐标
     * @param y 提示位置纵坐标
     */
    public void setForbidLocation(int x,int y){
      for(int i=0;i<5;i++){
        if(!forbidAvail[i]){
          forbidX[i]=x;
          forbidY[i]=y;
          forbidAvail[i]=true;
          break;
        }
      }
    }

    /**
     * <p>Description: 设置禁手位置绘制标记</p>
     * @param n 绘制禁手位置标记
     */
    public void setForbid(boolean n){
      for(int i=0;i<5;i++){
        forbidAvail[i]=n;
      }
    }

    /**
     * <p>Description: 播放声音</p>
     * @param index 播放音频文件序号(0-2)
     */
//    public void playSound(int index){
//      try{
//        InputStream stream;
//        AudioStream audio;
//
//        switch(index){
//          case 1:{ //落子声
//            stream=this.getClass().getResourceAsStream("sound/waveUp.wav");
//            break;
//          }
//          case 2:{ //游戏结束声
//            stream=this.getClass().getResourceAsStream("sound/waveEnd.wav");
//            break;
//          }
//          case 3:{ //游戏结束声
//            stream=this.getClass().getResourceAsStream("sound/waveDrop.wav");
//            break;
//          }
//          default:{ //落子声
//            stream=this.getClass().getResourceAsStream("sound/waveUp.wav");
//            break;
//          }
//        }
//
//        audio=new AudioStream(stream);
//        AudioPlayer.player.start(audio);
//      }
//      catch(Exception e1){
//        e1.printStackTrace();
//        JOptionPane.showMessageDialog(null, e1.toString(), "播放音频文件产生异常", JOptionPane.ERROR_MESSAGE);
//      }
//    }

    //鼠标点击事件
    public void this_mousePressed(MouseEvent e) {
      int tempX=e.getX();
      int tempY=e.getY();

      //设置边界
      if(tempX<15){
        tempX=15;
      }
      if(tempY<15){
        tempY=15;
      }
      if(tempX>15+(size-1)*40){
        tempX=15+(size-1)*40;
      }
      if(tempY>15+(size-1)*40){
        tempY=15+(size-1)*40;
      }

      if(e.getButton()==e.BUTTON1){ //鼠标左键点击
    	  if(Frame1.turn==0&&avail&&board[(int)(tempX+5)/40][(int)(tempY+5)/40]==0){ //轮到用户下子
    		  for(int i=0;i<5;i++){
    			  if(forbidAvail[i]){
    				  if(forbidX[i]==(int)(tempX+5)/40&&forbidY[i]==(int)(tempY+5)/40){
    					  forbidStyle=true;
    				  }
    			  }
    		  }

    		  if(forbidStyle){ //检测到禁手
    			  Frame1.forbidStyle++;
    			  forbidStyle=false;
    			  //playSound(3);
    			  JOptionPane.showMessageDialog(null, "<html><b>该点为<font color=blue>禁手</font>位"
    	            +",<font color=blue>游戏规则</font>可以查看<font color=purple>帮助</font>", "落子无效", JOptionPane.INFORMATION_MESSAGE);
    		  }
    		  else{
    			  userX=(int)(tempX+5)/40;
    			  userY=(int)(tempY+5)/40;
    			  //playSound(1);
    			  Frame1.turn=1;
    		  }
    	  }
    	  else if(!avail){ //未选择开始
    		  //playSound(3);
    		  JOptionPane.showMessageDialog(null, "<html><b>请在菜单中单击<font color=purple>开始</font>进行游戏", "开始游戏", JOptionPane.INFORMATION_MESSAGE);
    	  }
    	  else if(board[(int)(tempX+5)/40][(int)(tempY+5)/40]!=0){
    		  // playSound(3);
    	  }
      }
      else if(e.getButton()==e.BUTTON3){ //鼠标右键点击
      }
    }

    //用户鼠标移动
    public void this_mouseMoved(MouseEvent e) {
    }
}


class Canvas_this_mouseAdapter extends MouseAdapter {
    private Canvas adaptee;
    Canvas_this_mouseAdapter(Canvas adaptee) {
        this.adaptee = adaptee;
    }

    public void mousePressed(MouseEvent e) {
        adaptee.this_mousePressed(e);
    }
}


class Canvas_this_mouseMotionAdapter extends MouseMotionAdapter {
    private Canvas adaptee;
    Canvas_this_mouseMotionAdapter(Canvas adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseMoved(MouseEvent e) {
        adaptee.this_mouseMoved(e);
    }
}
