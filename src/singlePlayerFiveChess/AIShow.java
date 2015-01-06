package singlePlayerFiveChess;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * Show AI
 * @author Qing
 *
 */
public class AIShow extends JFrame {
    int size=16; //表格大小
    String[][] data={{"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""}}; //预测表数据
    String[] column={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"}; //预测表列名
    String[][] seData={{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}}; //AI选择数据
    String[] seColumn={"x坐标","y坐标","权值"}; //AI选择数据列名
    int showIndex=0; //显示表序号
    int selectionIndex=0; //AI选择数据序号
    TableModel model1=new DefaultTableModel(data, column); //预测表表格model
    TableModel model2=new DefaultTableModel(seData, seColumn); //AI选择数据表格model
    String[] list1={"AI预测表1","AI预测表2","AI预测表3","AI预测表4","AI预测表5","AI预测表6","AI预测表7"}; //列表内容
    String[] list2={"用户预测表1","用户预测表2","用户预测表3","用户预测表4","用户预测表5","用户预测表6","用户预测表7"}; //列表内容

    //用户预测表
    int[][] userPreBoard1=new int[size][size];
    int[][] userPreBoard2=new int[size][size];
    int[][] userPreBoard3=new int[size][size];
    int[][] userPreBoard4=new int[size][size];
    int[][] userPreBoard5=new int[size][size];
    int[][] userPreBoard6=new int[size][size];
    int[][] userPreBoard7=new int[size][size];

    //AI预测表
    int[][] aiPreBoard1=new int[size][size];
    int[][] aiPreBoard2=new int[size][size];
    int[][] aiPreBoard3=new int[size][size];
    int[][] aiPreBoard4=new int[size][size];
    int[][] aiPreBoard5=new int[size][size];
    int[][] aiPreBoard6=new int[size][size];
    int[][] aiPreBoard7=new int[size][size];

    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable(model1);
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JComboBox jComboBox1 = new JComboBox(list1);
    JLabel jLabel2 = new JLabel();
    JComboBox jComboBox2 = new JComboBox(list2);
    JButton jButton1 = new JButton();
    JLabel jLabel3 = new JLabel();
    JScrollPane jScrollPane2 = new JScrollPane();
    JTable jTable2 = new JTable(model2);
    JPanel jPanel3 = new JPanel();
    JLabel jLabel4 = new JLabel();

    public AIShow() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(null);
        this.setTitle("AI分析");
        this.setResizable(false);
        this.setSize(new Dimension(457, 520));
        //窗体放在中央
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        jLabel1.setText("AI预测表");
        jLabel2.setText("用户预测表");
        jButton1.setText("确定");
        jButton1.addActionListener(new AIShow_jButton1_actionAdapter(this));
        jComboBox1.addActionListener(new AIShow_jComboBox1_actionAdapter(this));
        jComboBox2.addActionListener(new AIShow_jComboBox2_actionAdapter(this));
        jLabel3.setText("<html><b><font color=blue>请选择查看的预测表<blue>");
        jTable1.setRowSelectionAllowed(false);
        jTable1.addMouseListener(new AIShow_jTable1_mouseAdapter(this));
        jPanel1.setBounds(new Rectangle( -1, 441, 450, 33));
        jScrollPane1.setBounds(new Rectangle(0, 33, 450, 281));
        jPanel2.setBounds(new Rectangle(0, 0, 450, 33));
        jScrollPane2.setBounds(new Rectangle(0, 347, 450, 87));
        jPanel3.setBounds(new Rectangle(0, 315, 450, 25));
        jLabel4.setText("<html><b><font color=blue>优选点数据");
        jScrollPane1.getViewport().add(jTable1);
        jPanel2.add(jLabel3);
        jPanel2.add(jLabel1);
        jPanel2.add(jComboBox1);
        jPanel2.add(jLabel2);
        jPanel2.add(jComboBox2);
        this.getContentPane().add(jPanel2, null);
        this.getContentPane().add(jScrollPane1, null);
        jPanel3.add(jLabel4);
        this.getContentPane().add(jScrollPane2);
        this.getContentPane().add(jPanel1, null);
        jPanel1.add(jButton1);
        jScrollPane2.getViewport().add(jTable2);
        this.getContentPane().add(jPanel3);
    }

    /**
     * <p>Description: 重设AI选择数据表数据</p>
     */
    public void resetSelection(){
      while(selectionIndex>0){
        selectionIndex--;
        model2.setValueAt("", selectionIndex, 0);
        model2.setValueAt("", selectionIndex, 1);
        model2.setValueAt("", selectionIndex, 2);
      }
      jLabel4.setText("<html><b><font color=blue>优选点数据");
    }

    /**
     * <p>Description: 更新AI选择数据表数据</p>
     * @param x 棋子横坐标
     * @param y 棋子纵坐标
     * @param weight 棋子权值
     */
    public void updateSelection(int x, int y, int weight){
      if(selectionIndex<8){
        model2.setValueAt(x, selectionIndex, 0);
        model2.setValueAt(y, selectionIndex, 1);
        model2.setValueAt(weight, selectionIndex, 2);
        selectionIndex++;
      }
    }

    //选择关闭窗口
    public void jButton1_actionPerformed(ActionEvent e) {
      setVisible(false);
    }

    //选择查看AI预测表
    public void jComboBox1_actionPerformed(ActionEvent e) {
      int index=jComboBox1.getSelectedIndex();
      switch(index){
        case 0:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard1[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AI预测表1<blue>");
          break;
        }
        case 1:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard2[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AI预测表2<blue>");
          break;
        }
        case 2:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard3[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AI预测表3<blue>");
          break;
        }
        case 3:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard4[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AI预测表4<blue>");
          break;
        }
        case 4:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard5[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AI预测表5<blue>");
          break;
        }
        case 5:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard6[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AI预测表6<blue>");
          break;
        }
        case 6:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard7[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AI预测表7<blue>");
          break;
        }
      }
    }

    //选择查看用户预测表
    public void jComboBox2_actionPerformed(ActionEvent e) {
      int index=jComboBox2.getSelectedIndex();
      switch(index){
        case 0:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard1[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>用户预测表1<blue>");
          break;
        }
        case 1:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard2[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>用户预测表2<blue>");
          break;
        }
        case 2:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard3[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>用户预测表3<blue>");
          break;
        }
        case 3:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard4[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>用户预测表4<blue>");
          break;
        }
        case 4:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard5[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>用户预测表5<blue>");
          break;
        }
        case 5:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard6[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>用户预测表6<blue>");
          break;
        }
        case 6:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard7[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>用户预测表7<blue>");
          break;
        }
      }
    }

    //选中查看坐标
    public void jTable1_mouseClicked(MouseEvent e) {
      jLabel4.setText("<html><b><font color=blue>选中点坐标: x: "+jTable1.getSelectedRow()+",y: "+jTable1.getSelectedColumn());
    }

}


class AIShow_jTable1_mouseAdapter extends MouseAdapter {
    private AIShow adaptee;
    AIShow_jTable1_mouseAdapter(AIShow adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jTable1_mouseClicked(e);
    }
}


class AIShow_jComboBox2_actionAdapter implements ActionListener {
    private AIShow adaptee;
    AIShow_jComboBox2_actionAdapter(AIShow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jComboBox2_actionPerformed(e);
    }
}


class AIShow_jComboBox1_actionAdapter implements ActionListener {
    private AIShow adaptee;
    AIShow_jComboBox1_actionAdapter(AIShow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jComboBox1_actionPerformed(e);
    }
}


class AIShow_jButton1_actionAdapter implements ActionListener {
    private AIShow adaptee;
    AIShow_jButton1_actionAdapter(AIShow adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
