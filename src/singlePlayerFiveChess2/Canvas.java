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
 * <p>Title: ��˵��</p>
 *
 * <p>Description: �������</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author goodboy
 * @version 2.1
 */
public class Canvas extends JPanel {
    int size=15; //���̴�С
    int[][] board=new int[size][size]; //�������� 0:������ 1:�û����� 2:AI����
    Color aiColor, userColor; //������ɫ
    int userX, userY; //�û�����λ��
    int lastX=-1, lastY=-1; //AI�������λ��
    int endX1,endY1,endX2,endY2; //�������߻���
    int[] forbidX=new int[5]; //����λ�ú�����
    int[] forbidY=new int[5]; //����λ��������
    String tipText="<html>";

    boolean avail=false; //���Ʊ�־
    boolean drawEndLine=false; //���ƽ���ֱ�߱�־
    boolean forbidStyle=false; //������ʾ���
    boolean lastAvail=false; //AI������ӱ��
    boolean[] forbidAvail={false,false,false,false,false}; //���ֱ��

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

    //��ͼ����
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      Graphics2D draw = (Graphics2D) g;

      drawBoard(draw); //��������

      if(avail){ //����ͼ��
        //��������
        for(int i=0;i<size;i++){
          for(int j=0;j<size;j++){
            if(board[i][j]==1){ //�û�����
              drawChessman(draw, i*37 + 37, j*37 + 37,userColor);
            }
            else if(board[i][j]==2){ //AI����
              drawChessman(draw, i*37 + 37, j*37 + 37,aiColor);
            }
          }
        }
        //ͻ��AI��һ������λ��
        if(lastAvail){
          //draw.setColor(Color.lightGray);
          //draw.fillOval(lastX*40,lastY*40,30,30);
    	  g.drawImage(getChessImage(Constant.WHITE), 
    			  lastX*37 + 37, lastY*37 + 37, null);
        }

        //���ƽ�����ʾλ��
        for(int i=0;i<5;i++){
          if(forbidAvail[i]){
            draw.setColor(Color.magenta);
            draw.drawOval(forbidX[i]*37 + 37,forbidY[i]*37 + 37,30,30);
            draw.drawString("����",forbidX[i]*37+5 + 37,forbidY[i]*37 + 37+20);
          }
        }

        //��ʾ����ֱ��
        if(drawEndLine){
          draw.setColor(Color.magenta);
          draw.drawLine(endX1*37+15 + 37,endY1*37+15 + 37,endX2*37+15 + 37,endY2*37+15 + 37);
        }
      }
    }

    /**
     * <p>Description: ��ʼ������</p>
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
     * <p>Description: ��������</p>
     * @param g Graphics2D����
     */
    public void drawBoard(Graphics2D g){
      
		g.drawImage(GameResourceLoader.getBackground(), 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(GameResourceLoader.getChessboard_single(), 30, 29, this);
		
		for(int i = 0; i < size; i++) {
			//���Ʊ߿�
			g.setColor(Color.blue);
			g.drawLine(15,15+i*37,15+(size-1)*37,15+i*37);
			g.drawLine(15+i*37,15,15+i*37,15+(size-1)*37);
			//���Ʊ��
			g.setColor(Color.black);
			g.drawString(i+"", 12+i*37, 10);
			g.drawString(i+"", 2, 18+i*37);
		}
    }

    /**
     * <p>Description: ��������</p>
     * @param g Graphics2D����
     * @param i ���Ӻ�����
     * @param j ����������
     * @param c ������ɫ
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
     * <p>Description: ����AI�����������</p>
     * @param i AI������Ӻ�����
     * @param j AI�������������
     */
    public void setLast(int i, int j){
      lastAvail=true;
      lastX=i;
      lastY=j;
    }

    /**
     * <p>Description: ���ý������߱��</p>
     * @param x1 ��������
     * @param y1 ���������
     * @param x2 �յ������
     * @param y2 �յ�������
     */
    public void setLine(int x1, int y1, int x2, int y2){
      drawEndLine=true;
      endX1=x1;
      endY1=y1;
      endX2=x2;
      endY2=y2;
    }

    /**
     * <p>Description: ���ý�����ʾλ��</p>
     * @param x ��ʾλ�ú�����
     * @param y ��ʾλ��������
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
     * <p>Description: ���ý���λ�û��Ʊ��</p>
     * @param n ���ƽ���λ�ñ��
     */
    public void setForbid(boolean n){
      for(int i=0;i<5;i++){
        forbidAvail[i]=n;
      }
    }

    /**
     * <p>Description: ��������</p>
     * @param index ������Ƶ�ļ����(0-2)
     */
//    public void playSound(int index){
//      try{
//        InputStream stream;
//        AudioStream audio;
//
//        switch(index){
//          case 1:{ //������
//            stream=this.getClass().getResourceAsStream("sound/waveUp.wav");
//            break;
//          }
//          case 2:{ //��Ϸ������
//            stream=this.getClass().getResourceAsStream("sound/waveEnd.wav");
//            break;
//          }
//          case 3:{ //��Ϸ������
//            stream=this.getClass().getResourceAsStream("sound/waveDrop.wav");
//            break;
//          }
//          default:{ //������
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
//        JOptionPane.showMessageDialog(null, e1.toString(), "������Ƶ�ļ������쳣", JOptionPane.ERROR_MESSAGE);
//      }
//    }

    //������¼�
    public void this_mousePressed(MouseEvent e) {
      int tempX=e.getX();
      int tempY=e.getY();

      //���ñ߽�
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

      if(e.getButton()==e.BUTTON1){ //���������
    	  if(Frame1.turn==0&&avail&&board[(int)(tempX+5)/40][(int)(tempY+5)/40]==0){ //�ֵ��û�����
    		  for(int i=0;i<5;i++){
    			  if(forbidAvail[i]){
    				  if(forbidX[i]==(int)(tempX+5)/40&&forbidY[i]==(int)(tempY+5)/40){
    					  forbidStyle=true;
    				  }
    			  }
    		  }

    		  if(forbidStyle){ //��⵽����
    			  Frame1.forbidStyle++;
    			  forbidStyle=false;
    			  //playSound(3);
    			  JOptionPane.showMessageDialog(null, "<html><b>�õ�Ϊ<font color=blue>����</font>λ"
    	            +",<font color=blue>��Ϸ����</font>���Բ鿴<font color=purple>����</font>", "������Ч", JOptionPane.INFORMATION_MESSAGE);
    		  }
    		  else{
    			  userX=(int)(tempX+5)/40;
    			  userY=(int)(tempY+5)/40;
    			  //playSound(1);
    			  Frame1.turn=1;
    		  }
    	  }
    	  else if(!avail){ //δѡ��ʼ
    		  //playSound(3);
    		  JOptionPane.showMessageDialog(null, "<html><b>���ڲ˵��е���<font color=purple>��ʼ</font>������Ϸ", "��ʼ��Ϸ", JOptionPane.INFORMATION_MESSAGE);
    	  }
    	  else if(board[(int)(tempX+5)/40][(int)(tempY+5)/40]!=0){
    		  // playSound(3);
    	  }
      }
      else if(e.getButton()==e.BUTTON3){ //����Ҽ����
      }
    }

    //�û�����ƶ�
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
