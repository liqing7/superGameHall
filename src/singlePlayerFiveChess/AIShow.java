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
    int size=16; //����С
    String[][] data={{"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""},
                    {"","","","","","","","","","","","","","","",""},{"","","","","","","","","","","","","","","",""}}; //Ԥ�������
    String[] column={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"}; //Ԥ�������
    String[][] seData={{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}}; //AIѡ������
    String[] seColumn={"x����","y����","Ȩֵ"}; //AIѡ����������
    int showIndex=0; //��ʾ�����
    int selectionIndex=0; //AIѡ���������
    TableModel model1=new DefaultTableModel(data, column); //Ԥ�����model
    TableModel model2=new DefaultTableModel(seData, seColumn); //AIѡ�����ݱ��model
    String[] list1={"AIԤ���1","AIԤ���2","AIԤ���3","AIԤ���4","AIԤ���5","AIԤ���6","AIԤ���7"}; //�б�����
    String[] list2={"�û�Ԥ���1","�û�Ԥ���2","�û�Ԥ���3","�û�Ԥ���4","�û�Ԥ���5","�û�Ԥ���6","�û�Ԥ���7"}; //�б�����

    //�û�Ԥ���
    int[][] userPreBoard1=new int[size][size];
    int[][] userPreBoard2=new int[size][size];
    int[][] userPreBoard3=new int[size][size];
    int[][] userPreBoard4=new int[size][size];
    int[][] userPreBoard5=new int[size][size];
    int[][] userPreBoard6=new int[size][size];
    int[][] userPreBoard7=new int[size][size];

    //AIԤ���
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
        this.setTitle("AI����");
        this.setResizable(false);
        this.setSize(new Dimension(457, 520));
        //�����������
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
        jLabel1.setText("AIԤ���");
        jLabel2.setText("�û�Ԥ���");
        jButton1.setText("ȷ��");
        jButton1.addActionListener(new AIShow_jButton1_actionAdapter(this));
        jComboBox1.addActionListener(new AIShow_jComboBox1_actionAdapter(this));
        jComboBox2.addActionListener(new AIShow_jComboBox2_actionAdapter(this));
        jLabel3.setText("<html><b><font color=blue>��ѡ��鿴��Ԥ���<blue>");
        jTable1.setRowSelectionAllowed(false);
        jTable1.addMouseListener(new AIShow_jTable1_mouseAdapter(this));
        jPanel1.setBounds(new Rectangle( -1, 441, 450, 33));
        jScrollPane1.setBounds(new Rectangle(0, 33, 450, 281));
        jPanel2.setBounds(new Rectangle(0, 0, 450, 33));
        jScrollPane2.setBounds(new Rectangle(0, 347, 450, 87));
        jPanel3.setBounds(new Rectangle(0, 315, 450, 25));
        jLabel4.setText("<html><b><font color=blue>��ѡ������");
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
     * <p>Description: ����AIѡ�����ݱ�����</p>
     */
    public void resetSelection(){
      while(selectionIndex>0){
        selectionIndex--;
        model2.setValueAt("", selectionIndex, 0);
        model2.setValueAt("", selectionIndex, 1);
        model2.setValueAt("", selectionIndex, 2);
      }
      jLabel4.setText("<html><b><font color=blue>��ѡ������");
    }

    /**
     * <p>Description: ����AIѡ�����ݱ�����</p>
     * @param x ���Ӻ�����
     * @param y ����������
     * @param weight ����Ȩֵ
     */
    public void updateSelection(int x, int y, int weight){
      if(selectionIndex<8){
        model2.setValueAt(x, selectionIndex, 0);
        model2.setValueAt(y, selectionIndex, 1);
        model2.setValueAt(weight, selectionIndex, 2);
        selectionIndex++;
      }
    }

    //ѡ��رմ���
    public void jButton1_actionPerformed(ActionEvent e) {
      setVisible(false);
    }

    //ѡ��鿴AIԤ���
    public void jComboBox1_actionPerformed(ActionEvent e) {
      int index=jComboBox1.getSelectedIndex();
      switch(index){
        case 0:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard1[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AIԤ���1<blue>");
          break;
        }
        case 1:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard2[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AIԤ���2<blue>");
          break;
        }
        case 2:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard3[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AIԤ���3<blue>");
          break;
        }
        case 3:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard4[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AIԤ���4<blue>");
          break;
        }
        case 4:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard5[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AIԤ���5<blue>");
          break;
        }
        case 5:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard6[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AIԤ���6<blue>");
          break;
        }
        case 6:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(aiPreBoard7[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>AIԤ���7<blue>");
          break;
        }
      }
    }

    //ѡ��鿴�û�Ԥ���
    public void jComboBox2_actionPerformed(ActionEvent e) {
      int index=jComboBox2.getSelectedIndex();
      switch(index){
        case 0:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard1[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>�û�Ԥ���1<blue>");
          break;
        }
        case 1:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard2[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>�û�Ԥ���2<blue>");
          break;
        }
        case 2:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard3[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>�û�Ԥ���3<blue>");
          break;
        }
        case 3:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard4[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>�û�Ԥ���4<blue>");
          break;
        }
        case 4:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard5[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>�û�Ԥ���5<blue>");
          break;
        }
        case 5:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard6[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>�û�Ԥ���6<blue>");
          break;
        }
        case 6:{
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              model1.setValueAt(userPreBoard7[i][j],j,i);
            }
          }
          jLabel3.setText("<html><b><font color=blue>�û�Ԥ���7<blue>");
          break;
        }
      }
    }

    //ѡ�в鿴����
    public void jTable1_mouseClicked(MouseEvent e) {
      jLabel4.setText("<html><b><font color=blue>ѡ�е�����: x: "+jTable1.getSelectedRow()+",y: "+jTable1.getSelectedColumn());
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
