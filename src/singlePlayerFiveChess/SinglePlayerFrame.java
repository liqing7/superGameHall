package singlePlayerFiveChess;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JOptionPane;
import java.io.InputStream;
import sun.audio.*;
import java.net.URL;
import java.util.Random;

/**
 * Single Player Game Main Frame
 * @author Qing
 *
 */
public class SinglePlayerFrame extends JFrame {
    int size=16; //���̴�С
    int[][] board=new int[size][size]; //�������� 0:������ 1:�û����� 2:AI����
    int time=0;; //��Ϸʱ��
    boolean suspend=true; //�Ƿ���ͣ
    int aiX, aiY; //AI��������
    Random rand=new Random(); //���������
    String aiText1, aiText2, aiText3, aiText4, aiText5, aiText6, aiText7, aiText8, aiText9
            , aiText10, aiText11, aiText12, aiText13, aiText14, aiText15, aiText16, aiText17
            , aiText18, aiText19, aiText20; //AI����˵������

    //��¼���ӵ���ʷλ��
    ArrayList userLastX=new ArrayList(); //�û����Ӻ�����
    ArrayList userLastY=new ArrayList(); //�û�����������
    ArrayList aiLastX=new ArrayList(); //AI���Ӻ�����
    ArrayList aiLastY=new ArrayList(); //AI����������
    ArrayList lastText=new ArrayList(); //AI�����ı���Ϣ
    int backStep=0; //���Ի���Ĳ���
    String tempText=""; //AI��������ʱ�ı���Ϣ

    static int turn=0; //Ŀǰ���ӷ�
    static int winner=0; //��Ϸ��� 1:�û�ʤ 2:AIʤ 3:ƽ��
    static int userStep=0; //�û����岽��
    static int aiStep=0; //AI���岽��
    static int userFourLine=0; //�û�4�ӳ�����
    static int aiFourLine=0; //AI4�ӳ�����
    static int userThreeLine=0; //�û�3�ӳ�����
    static int aiThreeLine=0; //AI3�ӳ�����
    static int userMultiLine=0; //�û����߳�����
    static int aiMultiLine=0; //AI���߳�����
    static int forbidStyle=0; //���ִ���
    static int regretTime=0; //�������
    static int retroSpeed=1000; //�ع���ʾ���ٶ�
    static boolean retroSuspend=true; //�ع���ʾ�̱߳��

    ControlThread thread1=new ControlThread();//�����̶߳���
    RetroThread thread2=new RetroThread(); //�ع���ʾ�߳�
    UserChess user=new UserChess(); //�û�����
    AIChess ai=new AIChess(); //AI����
    AIShow show=new AIShow();//AI������ʾ����
    Result result=new Result(); //�����ʾ����
    //Information info=new Information(); //������Ϣ����
    Rule rule=new Rule(); //������Ϣ����
    Retrospect retro=new Retrospect(); //�ع���ʾ���ƶ���
    URL url; //��Դλ�ö���

    JPanel contentPane;
    Canvas canvas = new Canvas();
    JPanel jPanel1 = new JPanel();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenu jMenu2 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenuItem jMenuItem2 = new JMenuItem();
    JMenuItem jMenuItem3 = new JMenuItem();
    JMenuItem jMenuItem4 = new JMenuItem();
    JScrollPane jScrollPane1 = new JScrollPane();
    JEditorPane jEditorPane1 = new JEditorPane();
    JScrollPane jScrollPane2 = new JScrollPane();
    JEditorPane jEditorPane2 = new JEditorPane();
    JMenuItem jMenuItem5 = new JMenuItem();
    JMenuItem jMenuItem6 = new JMenuItem();
    JSplitPane jSplitPane1 = new JSplitPane();
    JMenuItem jMenuItem8 = new JMenuItem();
    JMenuItem jMenuItem7 = new JMenuItem();
    public SinglePlayerFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
            thread1.start();
            thread2.start();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.toString(), "��������ʼ�������쳣", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Component initialization.
     *
     * @throws java.lang.Exception
     */
    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        this.setJMenuBar(jMenuBar1);
        this.setResizable(false);
        setSize(new Dimension(670, 715));
        setTitle("Single Player Five Chess");
        canvas.setBounds(new Rectangle(15, 15, 630, 630));
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setBounds(new Rectangle(660, 30, 135, 601));
        jPanel1.setLayout(null);
        jMenu1.setText("Game");
        jMenu2.setText("Help");
//        jMenuItem1.setToolTipText("������Ϣ");
//        jMenuItem1.setText("����");
//        jMenuItem1.addActionListener(new SinglePlayerFrame_jMenuItem1_actionAdapter(this));
        jMenuItem2.setToolTipText("Start");
        jMenuItem2.setText("Start");
        jMenuItem2.addActionListener(new SinglePlayerFrame_jMenuItem2_actionAdapter(this));
        jMenuItem3.setToolTipText("Quit");
        jMenuItem3.setText("End");
        jMenuItem3.addActionListener(new SinglePlayerFrame_jMenuItem3_actionAdapter(this));
        jMenuItem4.setToolTipText("Rules");
        jMenuItem4.setText("Rules");
        jMenuItem4.addActionListener(new SinglePlayerFrame_jMenuItem4_actionAdapter(this));
        jMenuItem6.setEnabled(false);
        jMenuItem6.setToolTipText("Withdraw");
        jMenuItem6.setText("Withdraw");
        jMenuItem6.addActionListener(new SinglePlayerFrame_jMenuItem6_actionAdapter(this));
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setDividerSize(10);
        jSplitPane1.setOneTouchExpandable(true);
        jSplitPane1.setBounds(new Rectangle(10, 9, 115, 580));
        //jEditorPane2.setToolTipText("AI����");
        //jEditorPane1.setToolTipText("AI����");
        jMenuItem8.setEnabled(false);
        jMenuItem8.setToolTipText("Review");
        jMenuItem8.setText("Review");
        jMenuItem8.addActionListener(new SinglePlayerFrame_jMenuItem8_actionAdapter(this));
        jMenuItem7.setToolTipText("Show AI analysis data");
        jMenuItem7.setText("AI data");
        jMenuItem7.addActionListener(new SinglePlayerFrame_jMenuItem7_actionAdapter(this));
        contentPane.add(canvas, null);
        //contentPane.add(jPanel1);
        //jPanel1.add(jSplitPane1);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenu2.add(jMenuItem7);
        jMenu2.add(jMenuItem4);
        //jMenu2.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem6);
        jMenu1.add(jMenuItem8);
        jMenu1.add(jMenuItem3);
//        jSplitPane1.add(jScrollPane2, JSplitPane.TOP);
//        jSplitPane1.add(jScrollPane1, JSplitPane.BOTTOM);
//        jScrollPane1.getViewport().add(jEditorPane1);
//        jScrollPane2.getViewport().add(jEditorPane2);
//        jEditorPane1.setContentType("text/html");
//        jEditorPane2.setContentType("text/html");
//        jSplitPane1.setDividerLocation(100);
//        jEditorPane1.setText("<html><b>AI����</b>");

        //ͼƬ��Դ
//        url=this.getClass().getResource("pic/nb.gif");
//        aiText1="<html><center><img src="+url+"><br><font color=purple>������һ�ְ�</font>";
//        url=this.getClass().getResource("pic/find.gif");
//        aiText2="<html><center><img src="+url+"><br><font color=purple>�������뿴</font>";
//        url=this.getClass().getResource("pic/vic.gif");
//        aiText3="<html><center><img src="+url+"><br><font color=purple>�������Ͱ�</font>";
//        url=this.getClass().getResource("pic/dk.gif");
//        aiText4="<html><center><img src="+url+"><br><font color=purple>����һ��~</font>";
//        url=this.getClass().getResource("pic/gongxi.gif");
//        aiText5="<html><center><img src="+url+"><br><font color=purple>�������ƹ�</font>";
//        url=this.getClass().getResource("pic/why.gif");
//        aiText6="<html><center><img src="+url+"><br><font color=purple>���ֲ���</font>";
//        url=this.getClass().getResource("pic/music.gif");
//        aiText7="<html><center><img src="+url+"><br><font color=purple>��Ϣһ��</font>";
//        url=this.getClass().getResource("pic/daxiao.gif");
//        aiText8="<html><center><img src="+url+"><br><font color=purple>����~</font>";
//        url=this.getClass().getResource("pic/bomb.gif");
//        aiText9="<html><center><img src="+url+"><br><font color=purple>����~</font>";
//        url=this.getClass().getResource("pic/kubi.gif");
//        aiText10="<html><center><img src="+url+"><br><font color=purple>����</font>";
//        url=this.getClass().getResource("pic/mon.gif");
//        aiText11="<html><center><img src="+url+"><br><font color=purple>ò�����Ѷ�</font>";
//        url=this.getClass().getResource("pic/anwei.gif");
//        aiText12="<html><center><img src="+url+"><br><font color=purple>��������</font>";
//        url=this.getClass().getResource("pic/df.gif");
//        aiText13="<html><center><img src="+url+"><br><font color=purple>����~</font>";
//        url=this.getClass().getResource("pic/smile.gif");
//        aiText14="<html><center><img src="+url+"><br><font color=purple>just for fun</font>";
//        url=this.getClass().getResource("pic/yumen.gif");
//        aiText15="<html><center><img src="+url+"><br><font color=purple>������̫����</font>";
//        url=this.getClass().getResource("pic/kiss.gif");
//        aiText16="<html><center><img src="+url+"><br><font color=purple>��С����</font>";
//        url=this.getClass().getResource("pic/kk.gif");
//        aiText17="<html><center><img src="+url+"><br><font color=purple>ϣ��ûbug</font>";
//        url=this.getClass().getResource("pic/shy.gif");
//        aiText18="<html><center><img src="+url+"><br><font color=purple>�һ��~</font>";
//        url=this.getClass().getResource("pic/no.gif");
//        aiText19="<html><center><img src="+url+"><br><font color=purple>��~��~</font>";
//        url=this.getClass().getResource("pic/yun.gif");
//        aiText20="<html><center><img src="+url+"><br><font color=purple>���̿�����</font>";
//        jEditorPane2.setText(aiText1);
    }

    /**
     * Control Thread
     * 
     * @author Qing
     *
     */
    class ControlThread extends Thread{
      public void run(){
        try{
          while(true){
            if(!suspend){ //δѡ����ͣ
              if(turn==1){ //�ֵ�AI����
                board[canvas.userX][canvas.userY]=1;
                canvas.board=board;
                canvas.repaint();
                userStep++;
                jMenuItem6.setEnabled(true);

                if(checkLine()){ //�û�ʤ��
                  suspend=true;
                  jMenuItem6.setEnabled(false);
                  jMenuItem8.setEnabled(true);
                  result.updateData();
                  result.setVisible(true);
                  //playSound(2);
                  jEditorPane2.setText(aiText4);
                }
                else if(checkEqual()){ //ƽ��
                  suspend=true;
                  jMenuItem6.setEnabled(false);
                  jMenuItem8.setEnabled(true);
                  result.updateData();
                  result.setVisible(true);
                  //playSound(2);
                  jEditorPane2.setText(aiText5);
                }
                else{
                  clearTable(canvas.userX,canvas.userY);
                  refreshTable(1); //�����û�Ԥ���
                  refreshTable(2); //����AIԤ���

                  select();
                  board[aiX][aiY]=2;
                  canvas.board=board;
                  canvas.setLast(aiX,aiY);
                  canvas.repaint();
                  aiStep++;

                  if(checkLine()){ //aiʤ��
                    suspend=true;
                    jMenuItem6.setEnabled(false);
                    jMenuItem8.setEnabled(true);
                    result.updateData();
                    result.setVisible(true);
                   // playSound(2);
                    jEditorPane2.setText(aiText3);
                  }
                  else if(checkEqual()){ //ƽ��
                    suspend=true;
                    jMenuItem6.setEnabled(false);
                    jMenuItem8.setEnabled(true);
                    result.updateData();
                    result.setVisible(true);
                    //playSound(2);
                    jEditorPane2.setText(aiText5);
                  }
                  else{ //�ֵ��û�����
                    clearTable(aiX,aiY);
                    refreshTable(1); //�����û�Ԥ���
                    refreshTable(2); //����AIԤ���

                    //���ּ��
                    canvas.setForbid(false);
                    if(user.maxValue2>1){ //���4,4����(�����ȼ�)
                      for(int i=0; i<user.number2; i++){
                        canvas.setForbidLocation(Integer.parseInt(user.maxX2.get(i).toString()), Integer.parseInt(user.maxY2.get(i).toString()));
                      }
                      jEditorPane2.setText(aiText6);
                    }
                    if(user.maxValue3>1){ //���4,4����(�����ȼ�)
                      for(int i=0; i<user.number3; i++){
                        canvas.setForbidLocation(Integer.parseInt(user.maxX3.get(i).toString()), Integer.parseInt(user.maxY3.get(i).toString()));
                      }
                      jEditorPane2.setText(aiText6);
                    }
                    if(user.maxValue4>1){ //���3,3����
                      for(int i=0; i<user.number4; i++){
                        canvas.setForbidLocation(Integer.parseInt(user.maxX4.get(i).toString()), Integer.parseInt(user.maxY4.get(i).toString()));
                      }
                      jEditorPane2.setText(aiText6);
                    }

                    //��¼������ʷ
                    userLastX.add(canvas.userX);
                    userLastY.add(canvas.userY);
                    aiLastX.add(aiX);
                    aiLastY.add(aiY);
                    lastText.add(tempText);
                    backStep++;

                    turn=0;
                  }
                }
              }
            }
            thread1.sleep(250);
          }
        }
        catch(Exception e1){
          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, e1.toString(), "�����̲߳����쳣", JOptionPane.ERROR_MESSAGE);
        }
      }
    }

    /**
     * Retro Thread
     * @author Qing
     *
     */
    class RetroThread extends Thread{
      int index=0;
      boolean black=true;
      public void run(){
        try{
          while(true){
            if(!retroSuspend){
              if(index<backStep){
                if(black){ //��ʾ�û�����
                  board[Integer.parseInt(userLastX.get(index).toString())][Integer.parseInt(userLastY.get(index).toString())]=1;
                  canvas.board=board;
                  canvas.repaint();
                  //playSound(1);
                  black=false;
                  retro.showStep(index*2+1);
                }
                else{ //��ʾAI����
                  board[Integer.parseInt(aiLastX.get(index).toString())][Integer.parseInt(aiLastY.get(index).toString())]=2;
                  canvas.board=board;
                  canvas.repaint();
                 // playSound(1);
                  black=true;
                  retro.showStep(index*2+2);
                  index++;
                }
              }
              else{
                index=0;
                black=true;
                retroSuspend=true;
                retro.setVisible(false);
              }
            }
            thread2.sleep(2000-retroSpeed);
          }
        }
        catch(Exception e2){
          e2.printStackTrace();
          JOptionPane.showMessageDialog(null, e2.toString(), "�ع���ʾ�̲߳����쳣", JOptionPane.ERROR_MESSAGE);
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

      canvas.initData();
      user.initData();
      ai.initData();
      canvas.aiColor=ai.color;
      canvas.userColor=user.color;
      canvas.avail=true;
      canvas.repaint();

      userLastX.clear();
      userLastY.clear();
      aiLastX.clear();
      aiLastY.clear();
      lastText.clear();

      time=0;
      turn=0;
      backStep=0;
      retroSuspend=true;
      winner=0;
      userStep=0;
      aiStep=0;
      userFourLine=0;
      aiFourLine=0;
      userThreeLine=0;
      aiThreeLine=0;
      userMultiLine=0;
      aiMultiLine=0;
      forbidStyle=0;
      regretTime=0;

      jMenuItem8.setEnabled(false);
      jEditorPane2.setText(aiText2);

      suspend=false;
    }

    /**
     * <p>Description: Ԥ��ɨ��</p>
     * @param preBoard Ԥ�����ݱ�
     * @param i ���Ӻ�����
     * @param j ����������
     * @param n ���ӿ�����(1:�û� 2:AI)
     * @return ����Ԥ��Ȩֵ
     */
    public int[] preCheck(int[][] preBoard, int i, int j, int n){
      int[] value; //��ʱ����

      preBoard[i][j]=n;
      value=preCheckLine(preBoard, i, j, n);
      preBoard[i][j]=0;

      return value;
    }

    /**
     * <p>Description: ֱ��Ԥ��ɨ��</p>
     * @param preBoard Ԥ�����ݱ�
     * @param i ���Ӻ�����
     * @param j ����������
     * @param n ���ӿ�����(1:�û� 2:AI)
     * @return ����Ԥ��Ȩֵ
     */
    public int[] preCheckLine(int[][] preBoard, int i, int j, int n){
      int maxNumber=0; //�������������
      int[] result={0, 0, 0, 0, 0, 0, 0}; //������ֵ
      int[] label={0, 0, 0, 0, 0, 0, 0}; //���߱��
      int min=0; //����߽�
      int max=0; //����߽�

      //�������
      for(int k=4; k>=0; k--){
        if(j-k>=0){
          min=j-k;
          break;
        }
      }
      for(int k=4; k>=0; k--){
        if(j+k<size){
          max=j+k+1;
          break;
        }
      }
      for(int k=min; k<max; k++){
        if(preBoard[i][k]==n){ //���Ӽ���
          maxNumber++;
        }
        else{
          maxNumber=0;
        }

        if(maxNumber==1){ //����1�ӹ���
          if(k+4<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==n&&preBoard[i][k+3]==0&&preBoard[i][k+4]==n){ //�Ƿ�Ϊ1-1-1����
            label[4]++;
          }
          if(k-1>=min&&k+3<max&&preBoard[i][k-1]==0&&preBoard[i][k+1]==0&&preBoard[i][k+2]==n&&preBoard[i][k+3]==0){ //�Ƿ�Ϊ-1-1-����
            label[6]++;
          }
          if((k-1>=min&&k+3<max&&preBoard[i][k-1]==0&&preBoard[i][k+1]==0&&preBoard[i][k+2]==0&&preBoard[i][k+3]==n)
             ||(k+4<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==0&&preBoard[i][k+3]==n&&preBoard[i][k+4]==0)){ //�Ƿ�Ϊ-1--1���ͻ�1--1-����
            label[6]++;
          }
          if((k+4<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==n&&preBoard[i][k+3]==0&&preBoard[i][k+4]==0)
             ||(k-2>=min&&k+2<max&&preBoard[i][k-2]==0&&preBoard[i][k-1]==0&&preBoard[i][k+1]==0&&preBoard[i][k+2]==n)){ //�Ƿ�Ϊ1-1--���ͻ�--1-1����
            label[6]++;
          }
          if(k+4<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==0&&preBoard[i][k+3]==0&&preBoard[i][k+4]==n){ //�Ƿ�Ϊ1---1����
            label[6]++;
          }
        }
        else if(maxNumber==2){ //����2�ӹ���
          if(k+3<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==n&&preBoard[i][k+3]==n){ //�Ƿ�Ϊ2-2����
            label[2]++;
          }
          if((k-4>=min&&k+1<max&&preBoard[i][k-2]==0&&preBoard[i][k-3]==n&&preBoard[i][k-4]==0&&preBoard[i][k+1]==0)
             ||(k-2>=min&&k+3<max&&preBoard[i][k-2]==0&&preBoard[i][k+2]==n&&preBoard[i][k+3]==0&&preBoard[i][k+1]==0)){ //�Ƿ�Ϊ-1-2-���ͻ�-2-1-����
            label[3]++;
          }
          if((k+3<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==n&&preBoard[i][k+3]==0)
             ||(k-4>=min&&preBoard[i][k-4]==0&&preBoard[i][k-3]==n&&preBoard[i][k-2]==0)){ //�Ƿ�Ϊ2-1-���ͻ�-1-2����
            label[4]++;
          }
          if((k+3<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==0&&preBoard[i][k+3]==n)
             ||(k-4>=min&&preBoard[i][k-4]==n&&preBoard[i][k-3]==0&&preBoard[i][k-2]==0)){ //�Ƿ�Ϊ2--1���ͻ�1--2����
            label[4]++;
          }
          if((k-2>=min&&k+2<max&&preBoard[i][k-2]==0&&preBoard[i][k+1]==0&&preBoard[i][k+2]==n)
             ||(k-3>=min&&k+1<max&&preBoard[i][k-3]==n&&preBoard[i][k-2]==0&&preBoard[i][k+1]==0)){ //�Ƿ�Ϊ-2-1���ͻ�1-2-����
            label[4]++;
          }
          if((k-3>=min&&k+1<max&&preBoard[i][k-3]==0&&preBoard[i][k-2]==0&&preBoard[i][k+1]==0)
             ||(k-2>=min&&k+2<max&&preBoard[i][k-2]==0&&preBoard[i][k+1]==0&&preBoard[i][k+2]==0)){ //�Ƿ�Ϊ--2-���ͻ�-2--����
            label[5]++;
          }
          if((k-4>=min&&preBoard[i][k-4]==0&&preBoard[i][k-3]==0&&preBoard[i][k-2]==0)
             ||(k+3<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==0&&preBoard[i][k+3]==0)){ //�Ƿ�Ϊ---2���ͻ�2---����
            label[6]++;
          }
        }
        else if(maxNumber==3){ //����3�ӹ���
          if((k-4>=min&&preBoard[i][k-3]==0&&preBoard[i][k-4]==n)
             ||(k+2<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==n)){ //�Ƿ�Ϊ1-3���ͻ�3-1����
            label[2]++;
          }
          if((k-4>=min&&k+1<max&&preBoard[i][k-4]==0&&preBoard[i][k-3]==0&&preBoard[i][k+1]==0)
             ||(k-3>=min&&k+2<max&&preBoard[i][k-3]==0&&preBoard[i][k+1]==0&&preBoard[i][k+2]==0)){ //�Ƿ�Ϊ--3-���ͻ�-3--����
            label[3]++;
          }
          if((k-4>=min&&preBoard[i][k-4]==0&&preBoard[i][k-3]==0)
             ||(k+2<max&&preBoard[i][k+1]==0&&preBoard[i][k+2]==0)){ //�Ƿ�Ϊ--3���ͻ�3--����
            label[4]++;
          }
          if(k-3>=min&&k+1<max&&preBoard[i][k-3]==0&&preBoard[i][k+1]==0){ //�Ƿ�Ϊ-3-����
            label[4]++;
          }
        }
        else if(maxNumber==4){ //����4�ӹ���
          if(k-4>=min&&k+1<max&&preBoard[i][k-4]==0&&preBoard[i][k+1]==0){ //�Ƿ�Ϊ-4-����
            label[1]++;
          }
          if((k-4>=min&&preBoard[i][k-4]==0)||k+1<max&&preBoard[i][k+1]==0){ //�Ƿ�Ϊ-4���ͻ�4-����
            label[2]++;
          }
        }
        else if(maxNumber>=5){ //����5�ӹ���
          label[0]++;
        }
      }
      //�ָ�����
      maxNumber=0;
      for(int k=0; k<7; k++){
        if(label[k]>0){
          result[k]++;
          label[k]=0;
        }
      }

      //������
      for(int k=4; k>=0; k--){
        if(i-k>=0){
          min=i-k;
          break;
        }
      }
      for(int k=4; k>=0; k--){
        if(i+k<size){
          max=i+k+1;
          break;
        }
      }
      for(int k=min; k<max; k++){
        if(preBoard[k][j]==n){ //���Ӽ���
          maxNumber++;
        }
        else{
          maxNumber=0;
        }

        if(maxNumber==1){ //����1�ӹ���
          if(k+4<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n&&preBoard[k+3][j]==0&&preBoard[k+4][j]==n){ //�Ƿ�Ϊ1-1-1����
            label[4]++;
          }
          if(k-1>=min&&k+3<max&&preBoard[k-1][j]==0&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n&&preBoard[k+3][j]==0){ //�Ƿ�Ϊ-1-1-����
            label[6]++;
          }
          if((k-1>=min&&k+3<max&&preBoard[k-1][j]==0&&preBoard[k+1][j]==0&&preBoard[k+2][j]==0&&preBoard[k+3][j]==n)
             ||(k+4<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==0&&preBoard[k+3][j]==n&&preBoard[k+4][j]==0)){ //�Ƿ�Ϊ-1--1���ͻ�1--1-����
            label[6]++;
          }
          if((k+4<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n&&preBoard[k+3][j]==0&&preBoard[k+4][j]==0)
             ||(k-2>=min&&k+2<max&&preBoard[k-2][j]==0&&preBoard[k-1][j]==0&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n)){ //�Ƿ�Ϊ1-1--���ͻ�--1-1����
            label[6]++;
          }
          if(k+4<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==0&&preBoard[k+3][j]==0&&preBoard[k+4][j]==n){ //�Ƿ�Ϊ1---1����
            label[6]++;
          }
        }
        else if(maxNumber==2){ //����2�ӹ���
          if(k+3<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n&&preBoard[k+3][j]==n){ //�Ƿ�Ϊ2-2����
            label[2]++;
          }
          if((k-4>=min&&k+1<max&&preBoard[k-2][j]==0&&preBoard[k-3][j]==n&&preBoard[k-4][j]==0&&preBoard[k+1][j]==0)
             ||(k-2>=min&&k+3<max&&preBoard[k-2][j]==0&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n&&preBoard[k+3][j]==0)){ //�Ƿ�Ϊ-1-2-���ͻ�-2-1-����
            label[3]++;
          }
          if((k+3<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n&&preBoard[k+3][j]==0)
             ||(k-4>=min&&preBoard[k-4][j]==0&&preBoard[k-3][j]==n&&preBoard[k-2][j]==0)){ //�Ƿ�Ϊ2-1-���ͻ�-1-2����
            label[4]++;
          }
          if((k+3<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==0&&preBoard[k+3][j]==n)
             ||(k-4>=min&&preBoard[k-4][j]==n&&preBoard[k-3][j]==0&&preBoard[k-2][j]==0)){ //�Ƿ�Ϊ2--1���ͻ�1--2����
            label[4]++;
          }
          if((k-2>=min&&k+2<max&&preBoard[k-2][j]==0&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n)
             ||(k-3>=min&&k+1<max&&preBoard[k-3][j]==n&&preBoard[k-2][j]==0&&preBoard[k+1][j]==0)){ //�Ƿ�Ϊ-2-1���ͻ�1-2-����
            label[4]++;
          }
          if((k-3>=min&&k+1<max&&preBoard[k-3][j]==0&&preBoard[k-2][j]==0&&preBoard[k+1][j]==0)
             ||(k-2>=min&&k+2<max&&preBoard[k-2][j]==0&&preBoard[k+1][j]==0&&preBoard[k+2][j]==0)){ //�Ƿ�Ϊ--2-���ͻ�-2--����
            label[5]++;
          }
          if((k-4>=min&&preBoard[k-4][j]==0&&preBoard[k-3][j]==0&&preBoard[k-2][j]==0)
             ||(k+3<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==0&&preBoard[k+3][j]==0)){ //�Ƿ�Ϊ---2���ͻ�2---����
            label[6]++;
          }
        }
        else if(maxNumber==3){ //����3�ӹ���
          if((k-4>=min&&preBoard[k-3][j]==0&&preBoard[k-4][j]==n)
             ||(k+2<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==n)){ //�Ƿ�Ϊ1-3���ͻ�3-1����
            label[2]++;
          }
          if((k-4>=min&&k+1<max&&preBoard[k-4][j]==0&&preBoard[k-3][j]==0&&preBoard[k+1][j]==0)
             ||(k-3>=min&&k+2<max&&preBoard[k-3][j]==0&&preBoard[k+1][j]==0&&preBoard[k+2][j]==0)){ //�Ƿ�Ϊ--3-���ͻ�-3--����
            label[3]++;
          }
          if((k-4>=min&&preBoard[k-4][j]==0&&preBoard[k-3][j]==0)
             ||(k+2<max&&preBoard[k+1][j]==0&&preBoard[k+2][j]==0)){ //�Ƿ�Ϊ--3���ͻ�3--����
            label[4]++;
          }
          if(k-3>=min&&k+1<max&&preBoard[k-3][j]==0&&preBoard[k+1][j]==0){ //�Ƿ�Ϊ-3-����
            label[4]++;
          }
        }
        else if(maxNumber==4){ //����4�ӹ���
          if(k-4>=min&&k+1<size&&preBoard[k-4][j]==0&&preBoard[k+1][j]==0){ //�Ƿ�Ϊ-4-����
            label[1]++;
          }
          if((k-4>=min&&preBoard[k-4][j]==0)||(k+1<max&&preBoard[k+1][j]==0)){ //�Ƿ�Ϊ-4���ͻ�4-����
            label[2]++;
          }
        }
        else if(maxNumber>=5){ //����5�ӹ���
          label[0]++;
        }
      }
      //�ָ�����
      maxNumber=0;
      for(int k=0; k<7; k++){
        if(label[k]>0){
          result[k]++;
          label[k]=0;
        }
      }

      //�����б��
      for(int k=4; k>=0; k--){
        if(i-k>=0&&j-k>=0){
          min=k;
          break;
        }
      }
      for(int k=4; k>=0; k--){
        if(i+k<size&&j+k<size){
          max=k+1;
          break;
        }
      }
      for(int k=-min; k<max; k++){
        if(preBoard[i+k][j+k]==n){ //���Ӽ���
          maxNumber++;
        }
        else{
          maxNumber=0;
        }

        if(maxNumber==1){ //����1�ӹ���
          if(i+k+4<i+max&&j+k+4<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==n&&preBoard[i+k+3][j+k+3]==0&&preBoard[i+k+4][j+k+4]==n){ //�Ƿ�Ϊ1-1-1����
            label[4]++;
          }
          if(i+k-1>=i-min&&j+k-1>=j-min&&i+k+3<i+max&&j+k+3<j+max&&preBoard[i+k-1][j+k-1]==0&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==n&&preBoard[i+k+3][j+k+3]==0){ //�Ƿ�Ϊ-1-1-����
            label[6]++;
          }
          if((i+k-1>=i-min&&j+k-1>=j-min&&i+k+3<i+max&&j+k+3<j+max&&preBoard[i+k-1][j+k-1]==0&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==0&&preBoard[i+k+3][j+k+3]==n)
             ||(i+k+4<i+max&&j+k+4<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==0&&preBoard[i+k+3][j+k+3]==n&&preBoard[i+k+4][j+k+4]==0)){ //�Ƿ�Ϊ-1--1���ͻ�1--1-����
            label[6]++;
          }
          if((i+k+4<i+max&&j+k+4<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==n&&preBoard[i+k+3][j+k+3]==0&&preBoard[i+k+4][j+k+4]==0)
             ||(i+k-2>=i-min&&j+k-2>=j-min&&i+k+2<i+max&&j+k+2<j+max&&preBoard[i+k-2][j+k-2]==0&&preBoard[i+k-1][j+k-1]==0&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==n)){ //�Ƿ�Ϊ1-1--���ͻ�--1-1����
            label[6]++;
          }
          if(i+k+4<i+max&&j+k+4<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==0&&preBoard[i+k+3][j+k+3]==0&&preBoard[i+k+4][j+k+4]==n){ //�Ƿ�Ϊ1---1����
            label[6]++;
          }
        }
        else if(maxNumber==2){ //����2�ӹ���
          if(i+k+3<i+max&&j+k+3<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==n&&preBoard[i+k+3][j+k+3]==n){ //�Ƿ�Ϊ2-2����
            label[2]++;
          }
          if((i+k-4>=i-min&&j+k-4>=j-min&&i+k+1<i+max&&j+k+1<j+max&&preBoard[i+k-4][j+k-4]==0&&preBoard[i+k-3][j+k-3]==n&&preBoard[i+k-2][j+k-2]==0&&preBoard[i+k+1][j+k+1]==0)
             ||(i+k-2>=i-min&&j+k-2>=j-min&&i+k+3<i+max&&j+k+3<j+max&&preBoard[i+k-2][j+k-2]==0&&preBoard[i+k+2][j+k+2]==n&&preBoard[i+k+3][j+k+3]==0&&preBoard[i+k+1][j+k+1]==0)){ //�Ƿ�Ϊ-1-2-���ͻ�-2-1-����
            label[3]++;
          }
          if((i+k+3<i+max&&j+k+3<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==n&&preBoard[i+k+3][j+k+3]==0)
             ||(i+k-4>=i-min&&j+k-4>=j-min&&preBoard[i+k-4][j+k-4]==0&&preBoard[i+k-3][j+k-3]==n&&preBoard[i+k-2][j+k-2]==0)){ //�Ƿ�Ϊ2-1-���ͻ�-1-2����
            label[4]++;
          }
          if((i+k+3<i+max&&j+k+3<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==0&&preBoard[i+k+3][j+k+3]==n)
             ||(i+k-4>=i-min&&j+k-4>=j-min&&preBoard[i+k-4][j+k-4]==n&&preBoard[i+k-3][j+k-3]==0&&preBoard[i+k-2][j+k-2]==0)){ //�Ƿ�Ϊ2--1���ͻ�1--2����
            label[4]++;
          }
          if((i+k-2>=i-min&&j+k-2>=j-min&&i+k+2<i+max&&j+k+2<j+max&&preBoard[i+k-2][j+k-2]==0&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==n)
             ||(i+k-3>=i-min&&j+k-3>=j-min&&i+k+1<i+max&&j+k+1<j+max&&preBoard[i+k-3][j+k-3]==n&&preBoard[i+k-2][j+k-2]==0&&preBoard[i+k+1][j+k+1]==0)){ //�Ƿ�Ϊ-2-1���ͻ�1-2-����
            label[4]++;
          }
          if((i+k-3>=i-min&&j+k-3>=j-min&&i+k+1<i+max&&j+k+1<j+max&&preBoard[i+k-3][j+k-3]==0&&preBoard[i+k-2][j+k-2]==0&&preBoard[i+k+1][j+k+1]==0)
             ||(i+k-2>=i-min&&j+k-2>=j-min&&i+k+2<i+max&&j+k+2<j+max&&preBoard[i+k-2][j+k-2]==0&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==0)){ //�Ƿ�Ϊ--2-���ͻ�-2--����
            label[5]++;
          }
          if((i+k-4>=i-min&&j+k-4>=j-min&&preBoard[i+k-4][j+k-4]==0&&preBoard[i+k-3][j+k-3]==0&&preBoard[i+k-2][j+k-2]==0)
             ||(i+k+3<i+max&&j+k+3<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==0&&preBoard[i+k+3][j+k+3]==0)){ //�Ƿ�Ϊ---2���ͻ�2---����
            label[6]++;
          }
        }
        else if(maxNumber==3){ //����3�ӹ���
          if((i+k-4>=i-min&&j+k-4>=j-min&&preBoard[i+k-3][j+k-3]==0&&preBoard[i+k-4][j+k-4]==n)
             ||(i+k+2<i+max&&j+k+2<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==n)){ //�Ƿ�Ϊ1-3���ͻ�3-1����
            label[2]++;
          }
          if((i+k-4>=i-min&&j+k-4>=j-min&&i+k+1<i+max&&j+k+1<j+max&&preBoard[i+k-4][j+k-4]==0&&preBoard[i+k-3][j+k-3]==0&&preBoard[i+k+1][j+k+1]==0)
             ||(i+k-3>=i-min&&j+k-3>=j-min&&i+k+2<i+max&&j+k+2<j+max&&preBoard[i+k-3][j+k-3]==0&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==0)){ //�Ƿ�Ϊ--3-���ͻ�-3--����
            label[3]++;
          }
          if((i+k-4>=i-min&&j+k-4>=j-min&&preBoard[i+k-4][j+k-4]==0&&preBoard[i+k-3][j+k-3]==0)
             ||(i+k+2<i+max&&j+k+2<j+max&&preBoard[i+k+1][j+k+1]==0&&preBoard[i+k+2][j+k+2]==0)){ //�Ƿ�Ϊ--3���ͻ�3--����
            label[4]++;
          }
          if(i+k-3>=i-min&&j+k-3>=j-min&&i+k+1<i+max&&j+k+1<j+max&&preBoard[i+k-3][j+k-3]==0&&preBoard[i+k+1][j+k+1]==0){ //�Ƿ�Ϊ-3-����
            label[4]++;
          }
        }
        else if(maxNumber==4){ //����4�ӹ���
          if(i+k-4>=i-min&&j+k-4>=j-min&&i+k+1<i+max&&j+k+1<j+max&&preBoard[i+k-4][j+k-4]==0&&preBoard[i+k+1][j+k+1]==0){ //�Ƿ�Ϊ-4-����
            label[1]++;
          }
          if((i+k-4>=i-min&&j+k-4>=j-min&&preBoard[i+k-4][j+k-4]==0)||(i+k+1<i+max&&j+k+1<j+max&&preBoard[i+k+1][j+k+1]==0)){ //�Ƿ�Ϊ-4���ͻ�4-����
            label[2]++;
          }
        }
        else if(maxNumber>=5){ //����5�ӹ���
          label[0]++;
        }
      }
      //�ָ�����
      maxNumber=0;
      for(int k=0; k<7; k++){
        if(label[k]>0){
          result[k]++;
          label[k]=0;
        }
      }

      //�����б��
      for(int k=4; k>=0; k--){
        if(i-k>=0&&j+k<size){
          min=k;
          break;
        }
      }
      for(int k=4; k>=0; k--){
        if(i+k<size&&j-k>=0){
          max=k+1;
          break;
        }
      }
      for(int k=-min; k<max; k++){
        if(preBoard[i+k][j-k]==n){ //���Ӽ���
          maxNumber++;
        }
        else{
          maxNumber=0;
        }

        if(maxNumber==1){ //����1�ӹ���
          if(i+k+4<i+max&&j-k-4>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==n&&preBoard[i+k+3][j-k-3]==0&&preBoard[i+k+4][j-k-4]==n){ //�Ƿ�Ϊ1-1-1����
            label[4]++;
          }
          if(i+k-1>=i-min&&j-k-3>=j-max+1&&i+k+3<i+max&&j-k+1<j+min+1&&preBoard[i+k-1][j-k+1]==0&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==n&&preBoard[i+k+3][j-k-3]==0){ //�Ƿ�Ϊ-1-1-����
            label[6]++;
          }
          if((i+k-1>=i-min&&j-k-3>=j-max+1&&i+k+1<i+max&&j-k+3<j+min+1&&preBoard[i+k-1][j-k+1]==0&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==0&&preBoard[i+k+3][j-k-3]==n)
             ||(i+k+4<i+max&&j-k-4>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==0&&preBoard[i+k+3][j-k-3]==n&&preBoard[i+k+4][j-k-4]==0)){ //�Ƿ�Ϊ-1--1���ͻ�1--1-����
            label[6]++;
          }
          if((i+k+4<i+max&&j-k-4>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==n&&preBoard[i+k+3][j-k-3]==0&&preBoard[i+k+4][j-k-4]==0)
             ||(i+k-2>=i-min&&j-k-2>=j-max+1&&i+k+2<i+max&&j-k+2<j+min+1&&preBoard[i+k-2][j-k+2]==0&&preBoard[i+k-1][j-k+1]==0&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==n)){ //�Ƿ�Ϊ1-1--���ͻ�--1-1����
            label[6]++;
          }
          if(i+k+4<i+max&&j-k-4>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==0&&preBoard[i+k+3][j-k-3]==0&&preBoard[i+k+4][j-k-4]==n){ //�Ƿ�Ϊ1---1����
            label[6]++;
          }
        }
        else if(maxNumber==2){ //����2�ӹ���
          if(i+k+3<i-min&&j-k-3>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==n&&preBoard[i+k+3][j-k-3]==n){ //�Ƿ�Ϊ2-2����
            label[2]++;
          }
          if((i+k-4>=i-min&&j-k+4<j+min+1&&i+k+1<i+max&&j-k-1>=j-max+1&&preBoard[i+k-2][j-k+2]==0&&preBoard[i+k-3][j-k+3]==n&&preBoard[i+k-4][j-k+4]==0&&preBoard[i+k+1][j-k-1]==0)
             ||(i+k-2>=i-min&&j-k-3>=j-max+1&&i+k+2<i+max&&j-k+2<j+min+1&&preBoard[i+k-2][j-k+2]==0&&preBoard[i+k+2][j-k-2]==n&&preBoard[i+k+3][j-k-3]==0&&preBoard[i+k+1][j-k-1]==0)){ //�Ƿ�Ϊ-1-2-���ͻ�-2-1-����
            label[3]++;
          }
          if((i+k+3<i+max&&j-k-3>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==n&&preBoard[i+k+3][j-k-3]==0)
             ||(i+k-4>=i-min&&j-k+4<j+min+1&&preBoard[i+k-4][j-k+4]==0&&preBoard[i+k-3][j-k+3]==n&&preBoard[i+k-2][j-k+2]==0)){ //�Ƿ�Ϊ2-1-���ͻ�-1-2����
            label[4]++;
          }
          if((i+k+3<i+max&&j-k-3>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==0&&preBoard[i+k+3][j-k-3]==n)
             ||(i+k-4>=i-min&&j-k+4<j+min+1&&preBoard[i+k-4][j-k+4]==n&&preBoard[i+k-3][j-k+3]==0&&preBoard[i+k-2][j-k+2]==0)){ //�Ƿ�Ϊ2--1���ͻ�1--2����
            label[4]++;
          }
          if((i+k-2>=i-min&&j-k+2<j+min+1&&i+k+2<i+max&&j-k-2>=j-max+1&&preBoard[i+k-2][j-k+2]==0&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==n)
             ||(i+k-3>=i-min&&j-k+3<j+min+1&&i+k+1<i+max&&j-k-1>=j-max+1&&preBoard[i+k-3][j-k+3]==n&&preBoard[i+k-2][j-k+2]==0&&preBoard[i+k+1][j-k-1]==0)){ //�Ƿ�Ϊ-2-1���ͻ�1-2-����
            label[4]++;
          }
          if((i+k-3>=i-min&&j-k+3<j+min+1&&preBoard[i+k-3][j-k+3]==0&&preBoard[i+k-2][j-k+2]==0&&preBoard[i+k-1][j-k+1]==0)
             ||(i+k-2>=i-min&&j-k-2>=j-max+1&&i+k+2<i+max&&j-k+2<j+min+1&&preBoard[i+k-2][j-k+2]==0&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==0)){ //�Ƿ�Ϊ--2-���ͻ�-2--����
            label[5]++;
          }
          if((i+k-4>=i-min&&j-k+4<j+min+1&&preBoard[i+k-4][j-k+4]==0&&preBoard[i+k-3][j-k+3]==0&&preBoard[i+k-2][j-k+2]==0)
             ||(i+k+3<i+max&&j-k-3>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==0&&preBoard[i+k+3][j-k-3]==0)){ //�Ƿ�Ϊ---2���ͻ�2---����
            label[6]++;
          }
        }
        else if(maxNumber==3){ //����3�ӹ���
          if((i+k-4>=i-min&&j-k+4<j+min+1&&preBoard[i+k-3][j-k+3]==0&&preBoard[i+k-4][j-k+4]==n)
             ||(i+k+2<i+max&&j-k-2>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==n)){ //�Ƿ�Ϊ1-3���ͻ�3-1����
            label[2]++;
          }
          if((i+k-4>=i-min&&j-k-1>=j-max+1&&i+k+1<i+max&&j-k+4<j+min+1&&preBoard[i+k-4][j-k+4]==0&&preBoard[i+k-3][j-k+3]==0&&preBoard[i+k+1][j-k-1]==0)
             ||(i+k-3>=i-min&&j-k-2>=j-max+1&&i+k+2<i+max&&j-k+3<j+min+1&&preBoard[i+k-3][j-k+3]==0&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==0)){ //�Ƿ�Ϊ--3-���ͻ�-3--����
            label[3]++;
          }
          if((i+k-4>=i-min&&j-k+4<j+min+1&&preBoard[i+k-4][j-k+4]==0&&preBoard[i+k-3][j-k+3]==0)
             ||(i+k+2<i+max&&j-k-2>=j-max+1&&preBoard[i+k+1][j-k-1]==0&&preBoard[i+k+2][j-k-2]==0)){ //�Ƿ�Ϊ--3���ͻ�3--����
            label[4]++;
          }
          if(i+k-3>=i-min&&j-k-1>=j-max+1&&i+k+1<i+max&&j-k+3<j+min+1&&preBoard[i+k-3][j-k+3]==0&&preBoard[i+k+1][j-k-1]==0){ //�Ƿ�Ϊ-3-����
            label[4]++;
          }
        }
        else if(maxNumber==4){ //����4�ӹ���
          if(i+k-4>=i-min&&j-k+4<j+min+1&&i+k+1<i+max&&j-k-1>=j-max+1&&preBoard[i+k-4][j-k+4]==0&&preBoard[i+k+1][j-k-1]==0){ //�Ƿ�Ϊ-4-����
            label[1]++;
          }
          if((i+k-4>=i-min&&j-k+4<j+min+1&&preBoard[i+k-4][j-k+4]==0)||(i+k+1<i+max&&j-k-1>=j-max+1&&preBoard[i+k+1][j-k-1]==0)){ //�Ƿ�Ϊ-4���ͻ�4-����
            label[2]++;
          }
        }
        else if(maxNumber>=5){ //����5�ӹ���
          label[0]++;
        }
      }
      //�ָ�����
      maxNumber=0;
      for(int k=0; k<7; k++){
        if(label[k]>0){
          result[k]++;
          label[k]=0;
        }
      }

      return result;
    }

    /**
     * <p>Description: ֱ��ɨ��</p>
     * @return ��Ϸ�������
     */
    public boolean checkLine(){
      int state=0; //��ǰ״̬
      int maxNumber=0; //�������������

      //�������
      for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
          if(maxNumber>4){ //����5�ӹ���
            winner=state;
            //����ֱ����ʾ
            if(j==0){
              canvas.setLine(i-1,size-5,i-1,size-1);
            }
            else{
              canvas.setLine(i,j-5,i,j-1);
            }
            return true;
          }
          if(board[i][j]==state&&state!=0){
            maxNumber++;
          }
          if(board[i][j]!=state){
            if(board[i][j]==0){
              maxNumber=0;
            }
            else{
              maxNumber=1;
            }
            state=board[i][j];
          }
        }
        //��ʼ��һ��ɨ��
        if(maxNumber<5){
          state=0;
          maxNumber=0;
        }
      }

      //������
      for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
          if(maxNumber>4){ //����5�ӹ���
            winner=state;
            //����ֱ����ʾ
            if(j==0){
              canvas.setLine(size-5,i-1,size-1,i-1);
            }
            else{
              canvas.setLine(j-5,i,j-1,i);
            }
            return true;
          }
          if(board[j][i]==state&&state!=0){
            maxNumber++;
          }
          if(board[j][i]!=state){
            if(board[j][i]==0){
              maxNumber=0;
            }
            else{
              maxNumber=1;
            }
            state=board[j][i];
          }
        }
        //��ʼ��һ��ɨ��
        if(maxNumber<5){
          state=0;
          maxNumber=0;
        }
      }

      //�����б��
      for(int i=0;i<size;i++){ //����ϰ벿��
        for(int j=0;j<size-i;j++){
          if(maxNumber > 4){ //����5�ӹ���
            winner=state;
            //����ֱ����ʾ
            if(j==0){
              canvas.setLine((i-1)+size-(i-1)-5,size-(i-1)-5,(i-1)+size-(i-1)-1,size-(i-1)-1);
            }
            else{
              canvas.setLine(i+j-5,j-5,i+j-1,j-1);
            }
            return true;
          }
          if(board[i+j][j]==state&&state!=0){
            maxNumber++;
          }
          if(board[i+j][j]!=state){
            if(board[i+j][j]==0){
              maxNumber=0;
            }
            else{
              maxNumber=1;
            }
            state=board[i+j][j];
          }
        }
        //��ʼ��һб��ɨ��
        if(maxNumber<5){
          state=0;
          maxNumber=0;
        }
      }
      for(int i=1;i<size;i++){ //����°벿��
        for(int j=0;j<size-i;j++){
          if(maxNumber>4){ //����5�ӹ���
            winner=state;
            //����ֱ����ʾ
            if(j==0){
              canvas.setLine(size-(i-1)-5,(i-1)+size-(i-1)-5,size-(i-1)-1,(i-1)+size-(i-1)-1);
            }
            else{
              canvas.setLine(j-5,i+j-5,j-1,i+j-1);
            }
            return true;
          }
          if(board[j][i+j]==state&&state!=0){
            maxNumber++;
          }
          if(board[j][i+j]!=state){
            if(board[j][i+j]==0){
              maxNumber=0;
            }
            else{
              maxNumber=1;
            }
            state=board[j][i+j];
          }
        }
        //��ʼ��һб��ɨ��
        if(maxNumber<5){
          state=0;
            maxNumber=0;
          }
        }

      //�����б��
      for(int i=0;i<size;i++){ //����°벿��
        for(int j=0;j<size-i;j++) {
          if(maxNumber>4) { //����5�ӹ���
            winner=state;
            //����ֱ����ʾ
            if(j==0){
              canvas.setLine((i-1)+size-(i-1)-5,size+4-(size-(i-1)),(i-1)+size-(i-1)-1,size-(size-(i-1)));
            }
            else{
              canvas.setLine(i+j-5,size+4-j,i+j-1,size-j);
            }
            return true;
          }
          if(board[i+j][size-1-j]==state&&state!=0){
            maxNumber++;
          }
          if(board[i+j][size-1-j]!=state){
            if(board[i+j][size-1-j]==0){
              maxNumber=0;
            }
            else{
              maxNumber=1;
            }
            state=board[i+j][size-1-j];
          }
        }
        //��ʼ��һб��ɨ��
        if(maxNumber<5){
          state=0;
          maxNumber=0;
        }
      }
      for(int i=1;i<size;i++){ //����ϰ벿��
        for(int j=0;j<size-i;j++){
          if(maxNumber>4) { //����5�ӹ���
            winner=state;
            //����ֱ����ʾ
            if(j==0){
              canvas.setLine(size-(i-1)-5,size+4-(i-1)-(size-(i-1)),size-(i-1)-1,size-(i-1)-(size-(i-1)));
            }
            else{
              canvas.setLine(j-5,size+4-i-j,j-1,size-i-j);
            }
            return true;
          }
          if(board[j][size-1-i-j]==state&&state!=0){
            maxNumber++;
          }
          if(board[j][size-1-i-j]!=state){
            if(board[j][size-1-i-j]==0){
              maxNumber=0;
            }
            else{
              maxNumber=1;
            }
            state=board[j][size-1-i-j];
          }
        }
        //��ʼ��һб��ɨ��
        if(maxNumber<5){
          state=0;
          maxNumber=0;
        }
      }

      return false;
    }

    /**
     * <p>Description: ����Ԥ���</p>
     * @param n ���ӿ�����(1:�û� 2:AI)
     */
    public void refreshTable(int n){
      int[] result;
      int[][] preBoard=new int[size][size];
      for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
          preBoard[i][j]=board[i][j];
        }
      }

      for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
          if(board[i][j]==0){
            result=preCheck(preBoard, i, j, n);
            if(n==1){
              user.preBoard1[i][j]=result[0];
              user.preBoard2[i][j]=result[1];
              user.preBoard3[i][j]=result[2];
              user.preBoard4[i][j]=result[3];
              user.preBoard5[i][j]=result[4];
              user.preBoard6[i][j]=result[5];
              user.preBoard7[i][j]=result[6];

              show.userPreBoard1[i][j]=result[0];
              show.userPreBoard2[i][j]=result[1];
              show.userPreBoard3[i][j]=result[2];
              show.userPreBoard4[i][j]=result[3];
              show.userPreBoard5[i][j]=result[4];
              show.userPreBoard6[i][j]=result[5];
              show.userPreBoard7[i][j]=result[6];

              if(result[0]>0){
                userFourLine++;
              }
              if(result[1]+result[2]>0){
                userThreeLine++;
              }
              if(result[0]+result[1]+result[2]>1){
                userMultiLine++;
              }
            }
            else{
              ai.preBoard1[i][j]=result[0];
              ai.preBoard2[i][j]=result[1];
              ai.preBoard3[i][j]=result[2];
              ai.preBoard4[i][j]=result[3];
              ai.preBoard5[i][j]=result[4];
              ai.preBoard6[i][j]=result[5];
              ai.preBoard7[i][j]=result[6];

              show.aiPreBoard1[i][j]=result[0];
              show.aiPreBoard2[i][j]=result[1];
              show.aiPreBoard3[i][j]=result[2];
              show.aiPreBoard4[i][j]=result[3];
              show.aiPreBoard5[i][j]=result[4];
              show.aiPreBoard6[i][j]=result[5];
              show.aiPreBoard7[i][j]=result[6];

              if(result[0]>0){
                aiFourLine++;
              }
              if(result[1]+result[2]>0){
                aiThreeLine++;
              }
              if(result[0]+result[1]+result[2]>1){
                aiMultiLine++;
              }
            }
          }
        }
      }

      //�������ֵ����
      user.refreshMax(1);
      user.refreshMax(2);
      user.refreshMax(3);
      user.refreshMax(4);
      user.refreshMax(5);
      user.refreshMax(6);
      user.refreshMax(7);

      ai.refreshMax(1);
      ai.refreshMax(2);
      ai.refreshMax(3);
      ai.refreshMax(4);
      ai.refreshMax(5);
      ai.refreshMax(6);
      ai.refreshMax(7);
    }

    /**
     * <p>Description: �������Ȩֵ</p>
     * @param i ���Ӻ�����
     * @param j ����������
     */
    public void clearTable(int i, int j){
      //��ոõ�Ԥ������
      user.preBoard1[i][j]=0;
      user.preBoard2[i][j]=0;
      user.preBoard3[i][j]=0;
      user.preBoard4[i][j]=0;
      user.preBoard5[i][j]=0;
      user.preBoard6[i][j]=0;
      user.preBoard7[i][j]=0;

      ai.preBoard1[i][j]=0;
      ai.preBoard2[i][j]=0;
      ai.preBoard3[i][j]=0;
      ai.preBoard4[i][j]=0;
      ai.preBoard5[i][j]=0;
      ai.preBoard6[i][j]=0;
      ai.preBoard7[i][j]=0;

      show.userPreBoard1[i][j]=0;
      show.userPreBoard2[i][j]=0;
      show.userPreBoard3[i][j]=0;
      show.userPreBoard4[i][j]=0;
      show.userPreBoard5[i][j]=0;
      show.userPreBoard6[i][j]=0;
      show.userPreBoard7[i][j]=0;

      show.aiPreBoard1[i][j]=0;
      show.aiPreBoard2[i][j]=0;
      show.aiPreBoard3[i][j]=0;
      show.aiPreBoard4[i][j]=0;
      show.aiPreBoard5[i][j]=0;
      show.aiPreBoard6[i][j]=0;
      show.aiPreBoard7[i][j]=0;
    }

    /**
     * <p>Description: AIѡ������λ��</p>
     */
    public void select(){
      int[] label={0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //���
      String text="<html><b>Ԥ��</b>"; //AI������ʾ�ַ�
      show.resetSelection(); //����AIѡ�����ݱ�����

      if(ai.maxValue1>0){ //AI��һ�����߻����5������
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number1+"</font>��λ��, ����<font color=maroon>"+ai.maxValue1
                +"</font>��ֱ��<font color=blue>5�ӳ���</font>"+"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number1>1){ //AI��һ�����λ��5������
          label=getWeight(ai.maxX1, ai.maxY1, ai.number1, 2);
        }
        else{ //AI��һ��ֻ��һ��λ��5������
          label=getLabel(ai.maxX1, ai.maxY1);
        }

        //��ʾAI����
        jEditorPane2.setText(aiText8);
      }
      else if(user.maxValue1>0){ //�û���һ�����߻����5������
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number1+"</font>��λ��, ����<font color=maroon>"+user.maxValue1
                +"</font>��ֱ��<font color=blue>5�ӳ���</font>"+"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number1>1){ //�û���һ�����λ��5������
          label=getWeight(user.maxX1, user.maxY1, user.number1, 1);
        }
        else{ //�û���һ��ֻ��һ��λ��5������
          label=getLabel(user.maxX1, user.maxY1);
        }

        //��ʾAI����
        jEditorPane2.setText(aiText9);
      }
      else if(ai.maxValue2>1){ //AI��һ������4������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number2+"</font>��λ��, ����<font color=maroon>"+ai.maxValue2
                +"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number2>1){ //ai��һ���ж��λ��4������
          label=getWeight(ai.maxX2, ai.maxY2, ai.number2, 2);
          label[0]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��4������
          label=getLabel(ai.maxX2, ai.maxY2);
          label[0]=0;
        }

        //��ʾAI����
        jEditorPane2.setText(aiText10);
      }
      else if(user.maxValue2>1){ //�û���һ������4������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number2+"</font>��λ��, ����<font color=maroon>"+user.maxValue2
                  +"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                  +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number2>1){ //�û���һ���ж��λ��4������
          label=getWeight(user.maxX2, user.maxY2, user.number2, 1);
          label[1]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��4������
          label=getLabel(user.maxX2, user.maxY2);
          label[1]=0;
        }

        //��ʾAI����
        jEditorPane2.setText(aiText11);
      }
      else if(ai.maxValue3>1){ //AI��һ������4������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number3+"</font>��λ��, ����<font color=maroon>"+ai.maxValue3
                  +"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                  +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number3>1){ //ai��һ���ж��λ��4������
          label=getWeight(ai.maxX3, ai.maxY3, ai.number3, 2);
          label[4]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��4������
          label=getLabel(ai.maxX3, ai.maxY3);
          label[4]=0;
        }

        //��ʾAI����
        jEditorPane2.setText(aiText10);
      }
      else if(user.maxValue3>1){ //�û���һ������4������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number3+"</font>��λ��, ����<font color=maroon>"+user.maxValue3
                    +"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                    +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number3>1){ //�û���һ���ж��λ��4������
          label=getWeight(user.maxX3, user.maxY3, user.number3, 1);
          label[5]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��4������
          label=getLabel(user.maxX3, user.maxY3);
          label[5]=0;
        }

        //��ʾAI����
        jEditorPane2.setText(aiText11);
      }
      else if(ai.maxValue2>0){ //AI��һ������4������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number2+"</font>��λ��, ����<font color=maroon>"+ai.maxValue2
                  +"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                  +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number2>1){ //ai��һ���ж��λ��4������
          label=getWeight(ai.maxX2, ai.maxY2, ai.number2, 2);
          label[0]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��4������
          label=getLabel(ai.maxX2, ai.maxY2);
          label[0]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue2>0){ //�û���һ������4������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number2+"</font>��λ��, ����<font color=maroon>"+user.maxValue2
                  +"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                  +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number2>1){ //�û���һ���ж��λ��4������
          label=getWeight(user.maxX2, user.maxY2, user.number2, 1);
          label[1]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��4������
          label=getLabel(user.maxX2, user.maxY2);
          label[1]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(ai.maxValue4>1){ //AI��һ������3������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number4+"</font>��λ��, ����<font color=maroon>"+ai.maxValue4
                  +"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                  +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number4>1){ //AI��һ�����λ��3������
          label=getWeight(ai.maxX4, ai.maxY4, ai.number4, 2);
          label[2]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��3������
          label=getLabel(ai.maxX4, ai.maxY4);
          label[2]=0;
        }

        //��ʾAI����
        jEditorPane2.setText(aiText10);
      }
      else if(user.maxValue4>1){ //�û���һ������3������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number4+"</font>��λ��, ����<font color=maroon>"+user.maxValue4
                  +"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                  +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number4>1){ //�û���һ�����λ��3������
          label=getWeight(user.maxX4, user.maxY4, user.number4, 1);
          label[3]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��3������
          label=getLabel(user.maxX4, user.maxY4);
          label[3]=0;
        }

        //��ʾAI����
        jEditorPane2.setText(aiText11);
      }
      else if(ai.maxValue4>0){ //AI��һ������3������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number4+"</font>��λ��, ����<font color=maroon>"+ai.maxValue4
                  +"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                  +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number4>1){ //AI��һ�����λ��3������
          label=getWeight(ai.maxX4, ai.maxY4, ai.number4, 2);
          label[2]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��3������
          label=getLabel(ai.maxX4, ai.maxY4);
          label[2]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(ai.maxValue3>0){ //AI��һ������4������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number3+"</font>��λ��, ����<font color=maroon>"+ai.maxValue3
                    +"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                    +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number3>1){ //ai��һ���ж��λ��4������
          label=getWeight(ai.maxX3, ai.maxY3, ai.number3, 2);
          label[4]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��4������
          label=getLabel(ai.maxX3, ai.maxY3);
          label[4]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue4>0){ //�û���һ������3������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number4+"</font>��λ��, ����<font color=maroon>"+user.maxValue4
                  +"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                  +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number4>1){ //�û���һ�����λ��3������
          label=getWeight(user.maxX4, user.maxY4, user.number4, 1);
          label[3]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��3������
          label=getLabel(user.maxX4, user.maxY4);
          label[3]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue3>0){ //�û���һ������4������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number3+"</font>��λ��, ����<font color=maroon>"+user.maxValue3
                    +"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                    +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number3>1){ //�û���һ���ж��λ��4������
          label=getWeight(user.maxX3, user.maxY3, user.number3, 1);
          label[5]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��4������
          label=getLabel(user.maxX3, user.maxY3);
          label[5]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(ai.maxValue5>1){ //AI��һ������3������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number5+"</font>��λ��, ����<font color=maroon>"+ai.maxValue5
                    +"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                    +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number5>1){ //AI��һ�����λ��3������
          label=getWeight(ai.maxX5, ai.maxY5, ai.number5, 2);
          label[8]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��3������
          label=getLabel(ai.maxX5, ai.maxY5);
          label[8]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue5>1){ //�û���һ������3������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number5+"</font>��λ��, ����<font color=maroon>"+user.maxValue5
                      +"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                      +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number5>1){ //�û���һ�����λ��3������
          label=getWeight(user.maxX5, user.maxY5, user.number5, 1);
          label[9]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��3������
          label=getLabel(user.maxX5, user.maxY5);
          label[9]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(ai.maxValue6>1){ //AI��һ������2������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number6+"</font>��λ��, ����<font color=maroon>"+ai.maxValue6
                      +"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                      +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number6>1){ //AI��һ�����λ��2������
          label=getWeight(ai.maxX6, ai.maxY6, ai.number6, 2);
          label[6]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��2������
          label=getLabel(ai.maxX6, ai.maxY6);
          label[6]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue6>1){ //�û���һ������2������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number6+"</font>��λ��, ����<font color=maroon>"+user.maxValue6
                        +"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                        +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number6>1){ //�û���һ�����λ��2������
          label=getWeight(user.maxX6, user.maxY6, user.number6, 1);
          label[7]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��2������
          label=getLabel(user.maxX6, user.maxY6);
          label[7]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(ai.maxValue6>0){ //AI��һ������2������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number6+"</font>��λ��, ����<font color=maroon>"+ai.maxValue6
                        +"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                        +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number6>1){ //AI��һ�����λ��2������
          label=getWeight(ai.maxX6, ai.maxY6, ai.number6, 2);
          label[6]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��2������
          label=getLabel(ai.maxX6, ai.maxY6);
          label[6]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue6>0){ //�û���һ������2������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number6+"</font>��λ��, ����<font color=maroon>"+user.maxValue6
                        +"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                        +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number6>1){ //�û���һ�����λ��2������
          label=getWeight(user.maxX6, user.maxY6, user.number6, 1);
          label[7]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��2������
          label=getLabel(user.maxX6, user.maxY6);
          label[7]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(ai.maxValue5>0){ //AI��һ������3������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number5+"</font>��λ��, ����<font color=maroon>"+ai.maxValue5
                        +"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                        +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number5>1){ //AI��һ�����λ��3������
          label=getWeight(ai.maxX5, ai.maxY5, ai.number5, 2);
          label[8]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��3������
          label=getLabel(ai.maxX5, ai.maxY5);
          label[8]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue5>0){ //�û���һ������3������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number5+"</font>��λ��, ����<font color=maroon>"+user.maxValue5
                          +"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                          +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number5>1){ //�û���һ�����λ��3������
          label=getWeight(user.maxX5, user.maxY5, user.number5, 1);
          label[9]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��3������
          label=getLabel(user.maxX5, user.maxY5);
          label[9]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(ai.maxValue7>1){ //AI��һ������2������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number7+"</font>��λ��, ����<font color=maroon>"+ai.maxValue7
                          +"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                          +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number7>1){ //AI��һ�����λ��2������
          label=getWeight(ai.maxX7, ai.maxY7, ai.number7, 2);
          label[10]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��2������
          label=getLabel(ai.maxX7, ai.maxY7);
          label[10]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue7>1){ //�û���һ������2������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number7+"</font>��λ��, ����<font color=maroon>"+user.maxValue7
                            +"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                            +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number7>1){ //�û���һ�����λ��2������
          label=getWeight(user.maxX7, user.maxY7, user.number7, 1);
          label[11]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��2������
          label=getLabel(user.maxX7, user.maxY7);
          label[11]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(ai.maxValue7>0){ //AI��һ������2������(�����ȼ�)
        text+="<u>AI</u>��һ����<font color=maroon>"+ai.number7+"</font>��λ��, ����<font color=maroon>"+ai.maxValue7
                            +"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                            +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(ai.number7>1){ //AI��һ�����λ��2������
          label=getWeight(ai.maxX7, ai.maxY7, ai.number7, 2);
          label[10]=0;
        }
        else{ //AI��һ��ֻ��һ��λ��2������
          label=getLabel(ai.maxX7, ai.maxY7);
          label[10]=0;
        }

        //��ʾAI����
        showFace();
      }
      else if(user.maxValue7>0){ //�û���һ������2������(�����ȼ�)
        text+="<u>�û�</u>��һ����<font color=maroon>"+user.number7+"</font>��λ��, ����<font color=maroon>"+user.maxValue7
                            +"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>"
                            +"<br><br><b>ѡ��</b><font color=blue>AI������ѵ�</font>";

        if(user.number7>1){ //�û���һ�����λ��2������
          label=getWeight(user.maxX7, user.maxY7, user.number7, 1);
          label[11]=0;
        }
        else{ //�û���һ��ֻ��һ��λ��2������
          label=getLabel(user.maxX7, user.maxY7);
          label[11]=0;
        }

        //��ʾAI����
        showFace();
      }
      else{ //�޳���λ��
        text+="δ��⵽����λ��";

        point1:
        for(int i=0; i<size; i++){
          for(int j=0; j<size; j++){
            if(board[i][j]==0){
              board[i][j]=2;
              break point1;
            }
          }
        }

        //��ʾAI����
        showFace();
      }

      //��ʾAIѡ����Ϣ
      if(label[0]>0){
        text+=",    <u>AI</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[0]+"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[1]>0){
        text+=",    <u>�û�</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[1]+"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[2]>0){
        text+=",    <u>AI</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[2]+"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[3]>0){
        text+=",    <u>�û�</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[3]+"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[4]>0){
        text+=",    <u>AI</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[4]+"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[5]>0){
        text+=",    <u>�û�</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[5]+"</font>��ֱ��<font color=blue>4�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[6]>0){
        text+=",    <u>AI</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[6]+"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[7]>0){
        text+=",    <u>�û�</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[7]+"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[8]>0){
        text+=",    <u>AI</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[8]+"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[9]>0){
        text+=",    <u>�û�</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[9]+"</font>��ֱ��<font color=blue>3�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[10]>0){
        text+=",    <u>AI</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[10]+"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      if(label[11]>0){
        text+=",    <u>�û�</u>��<i>��λ��</i>��һ��<font color=maroon>"+label[11]+"</font>��ֱ��<font color=blue>2�ӳ���</font><font color=purple>(�����ȼ�)</font>";
      }
      jEditorPane1.setText(text);

      tempText=text;
    }

    /**
     * <p>Description: ����Ƿ�ƽ��</p>
     * @return ƽ�ֱ��
     */
    public boolean checkEqual(){
      for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
          if(board[i][j]==0){
            return false;
          }
        }
      }

      winner=3;
      return true;
    }

    /**
     * <p>Description: ѡ�����ӿռ�����λ��</p>
     * @param i ���Ӻ�����
     * @param j ����������
     * @param n ���ӿ�����(1:�û� 2:AI)
     * @return ���ӿռ�Ȩֵ
     */
    public int checkSpace(int i, int j, int n){
      int result=0;

      if(i-1>=0&&board[i-1][j]==n){
        result++;
      }
      if(i+1<size&&board[i+1][j]==n){
        result++;
      }
      if(j-1>=0&&board[i][j-1]==n){
        result++;
      }
      if(j+1<size&&board[i][j+1]==n){
        result++;
      }
      if(i-1>=0&&j-1>=0&&board[i-1][j-1]==n){
        result++;
      }
      if(i+1<size&&j+1<size&&board[i+1][j+1]==n){
        result++;
      }
      if(i-1>=0&&j+1<size&&board[i-1][j+1]==n){
        result++;
      }
      if(i+1<size&&j-1>=0&&board[i+1][j-1]==n){
        result++;
      }

      return result;
    }

    /**
     * <p>Description: �����ʾAI����</p>
     */
    public void showFace(){
      int index=rand.nextInt(8);
      switch(index){
        case 0:{
          jEditorPane2.setText(aiText12);
          break;
        }
        case 1:{
          jEditorPane2.setText(aiText13);
          break;
        }
        case 2:{
          jEditorPane2.setText(aiText14);
          break;
        }
        case 3:{
          jEditorPane2.setText(aiText15);
          break;
        }
        case 4:{
          jEditorPane2.setText(aiText16);
          break;
        }
        case 5:{
          jEditorPane2.setText(aiText17);
          break;
        }
        case 6:{
          jEditorPane2.setText(aiText18);
          break;
        }
        case 7:{
          jEditorPane2.setText(aiText19);
          break;
        }
      }
    }

    /**
     * <p>Description: ���AIѡ��</p>
     * @param x ���Ӻ���������
     * @param y ��������������
     * @return AIѡ����
     */
    public int[] getLabel(ArrayList x, ArrayList y){
      int[] result=new int[12];

      aiX=Integer.parseInt(x.get(0).toString());
      aiY=Integer.parseInt(y.get(0).toString());
      show.updateSelection(aiX, aiY, 0);

      //���AIѡ��
      result[0]=ai.preBoard2[aiX][aiY];
      result[1]=user.preBoard2[aiX][aiY];
      result[2]=ai.preBoard4[aiX][aiY];
      result[3]=user.preBoard4[aiX][aiY];
      result[4]=ai.preBoard3[aiX][aiY];
      result[5]=user.preBoard3[aiX][aiY];
      result[6]=ai.preBoard6[aiX][aiY];
      result[7]=user.preBoard6[aiX][aiY];
      result[8]=ai.preBoard5[aiX][aiY];
      result[9]=user.preBoard5[aiX][aiY];
      result[10]=ai.preBoard7[aiX][aiY];
      result[11]=user.preBoard7[aiX][aiY];

      return result;
    }

    /**
     * <p>Description: ��������Ȩֵ</p>
     * @param x ���Ӻ���������
     * @param y ��������������
     * @param length ������
     * @param index ���ӿ�����(1:�û� 2:AI)
     * @return AIѡ����
     */
    public int[] getWeight(ArrayList x, ArrayList y, int length, int index){
      int[] result=new int[12];
      int i=0, j=0;
      int tempValue=-1;
      int weight;

      for(int n=0; n<length; n++){
        i=Integer.parseInt(x.get(n).toString());
        j=Integer.parseInt(y.get(n).toString());
        weight=ai.preBoard2[i][j]*8000000+user.preBoard2[i][j]*1000000+ai.preBoard4[i][j]*100000+user.preBoard4[i][j]*80000
                +ai.preBoard3[i][j]*10000+user.preBoard3[i][j]*8000+ai.preBoard6[i][j]*1000
                +user.preBoard6[i][j]*800+ai.preBoard5[i][j]*100+user.preBoard5[i][j]*80
                +ai.preBoard7[i][j]*10+user.preBoard7[i][j]*8+checkSpace(i, j, index);
        show.updateSelection(i, j, weight);

        if(weight>tempValue){
          tempValue=weight;
          aiX=i;
          aiY=j;

          //���AIѡ��
          result[0]=ai.preBoard2[i][j];
          result[1]=user.preBoard2[i][j];
          result[2]=ai.preBoard4[i][j];
          result[3]=user.preBoard4[i][j];
          result[4]=ai.preBoard3[i][j];
          result[5]=user.preBoard3[i][j];
          result[6]=ai.preBoard6[i][j];
          result[7]=user.preBoard6[i][j];
          result[8]=ai.preBoard5[i][j];
          result[9]=user.preBoard5[i][j];
          result[10]=ai.preBoard7[i][j];
          result[11]=user.preBoard7[i][j];
        }
      }

      return result;
    }

    /**
     * <p>Description: ��������</p>
     * @param index �����ļ����
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

    //ѡ��ʼ��ť
    public void jMenuItem2_actionPerformed(ActionEvent e) {
      initData();
    }

    //ѡ�������ť
    public void jMenuItem3_actionPerformed(ActionEvent e) {
      System.exit(0);
    }

//    //ѡ��鿴������Ϣ
//    public void jMenuItem1_actionPerformed(ActionEvent e) {
//      info.setVisible(true);
//    }

    //ѡ��鿴����
    public void jMenuItem4_actionPerformed(ActionEvent e) {
      rule.setVisible(true);
    }

    //ѡ�����
    public void jMenuItem6_actionPerformed(ActionEvent e) {
      int userBackX, userBackY; //�û���������
      int aiBackX, aiBackY; //AI��������

      if(backStep>0){
        backStep--;
        userStep--;
        aiStep--;

        userBackX=Integer.parseInt(userLastX.remove(backStep).toString());
        userBackY=Integer.parseInt(userLastY.remove(backStep).toString());
        aiBackX=Integer.parseInt(aiLastX.remove(backStep).toString());
        aiBackY=Integer.parseInt(aiLastY.remove(backStep).toString());
        lastText.remove(backStep);

        board[userBackX][userBackY]=0;
        board[aiBackX][aiBackY]=0;
        clearTable(userBackX,userBackY);
        clearTable(aiBackX,aiBackY);
        refreshTable(1);
        refreshTable(2);

        if(backStep-1>=0){
          canvas.setLast(Integer.parseInt(aiLastX.get(backStep-1).toString()),Integer.parseInt(aiLastY.get(backStep-1).toString()));
          jEditorPane1.setText(lastText.get(backStep-1).toString());
        }
        else{
          canvas.lastAvail=false;
        }
        canvas.board=board;
        canvas.repaint();

        regretTime++;
        if(backStep==0){
          jMenuItem6.setEnabled(false);
          jEditorPane1.setText("<html><b>AI����</b>");
        }
      }
    }

    //ѡ��鿴AIλ�÷���
    public void jMenuItem7_actionPerformed(ActionEvent e) {
      show.setVisible(true);
    }

    //ѡ��ع����
    public void jMenuItem8_actionPerformed(ActionEvent e) {
      //��ʼ������״̬
      for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
          board[i][j]=0;
        }
      }
      canvas.initData();
      canvas.repaint();

      thread2.black=true;
      thread2.index=0;
      retroSuspend=false;
      retro.initProgressBar(backStep*2);
      retro.setVisible(true);
      jEditorPane2.setText(aiText7);
    }

    //ѡ��鿴AIԤ���
    public void jMenu4_actionPerformed(ActionEvent e) {
      show.setVisible(true);
    }
}


class SinglePlayerFrame_jMenu4_actionAdapter implements ActionListener {
    private SinglePlayerFrame adaptee;
    SinglePlayerFrame_jMenu4_actionAdapter(SinglePlayerFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenu4_actionPerformed(e);
    }
}


class SinglePlayerFrame_jMenuItem8_actionAdapter implements ActionListener {
    private SinglePlayerFrame adaptee;
    SinglePlayerFrame_jMenuItem8_actionAdapter(SinglePlayerFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem8_actionPerformed(e);
    }
}


class SinglePlayerFrame_jMenuItem7_actionAdapter implements ActionListener {
    private SinglePlayerFrame adaptee;
    SinglePlayerFrame_jMenuItem7_actionAdapter(SinglePlayerFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {

        adaptee.jMenuItem7_actionPerformed(e);
    }
}


class SinglePlayerFrame_jMenuItem6_actionAdapter implements ActionListener {
    private SinglePlayerFrame adaptee;
    SinglePlayerFrame_jMenuItem6_actionAdapter(SinglePlayerFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem6_actionPerformed(e);
    }
}


class SinglePlayerFrame_jMenuItem4_actionAdapter implements ActionListener {
    private SinglePlayerFrame adaptee;
    SinglePlayerFrame_jMenuItem4_actionAdapter(SinglePlayerFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem4_actionPerformed(e);
    }
}


//class SinglePlayerFrame_jMenuItem1_actionAdapter implements ActionListener {
//    private SinglePlayerFrame adaptee;
//    SinglePlayerFrame_jMenuItem1_actionAdapter(SinglePlayerFrame adaptee) {
//        this.adaptee = adaptee;
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        adaptee.jMenuItem1_actionPerformed(e);
//    }
//}


class SinglePlayerFrame_jMenuItem3_actionAdapter implements ActionListener {
    private SinglePlayerFrame adaptee;
    SinglePlayerFrame_jMenuItem3_actionAdapter(SinglePlayerFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem3_actionPerformed(e);
    }
}


class SinglePlayerFrame_jMenuItem2_actionAdapter implements ActionListener {
    private SinglePlayerFrame adaptee;
    SinglePlayerFrame_jMenuItem2_actionAdapter(SinglePlayerFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem2_actionPerformed(e);
    }
}
