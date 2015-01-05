package singlePlayerFiveChess2;

import java.awt.*;

import javax.swing.*;
import java.awt.Rectangle;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

/**
 * <p>Title: ��˵��</p>
 *
 * <p>Description: ��Ϸ�����ʾ���</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author goodboy
 * @version 2.1
 */
public class Result extends JFrame {
    String[] column={"ͳ����Ŀ","�û�","AI"}; //�����
    String[][] data={{"��Ϸ���","",""},{"���岽��","",""},{"4�ӳ�����","",""},{"3�ӳ�����","",""},
                    {"���߳�����","",""},{"���ִ���","",""},{"�������","",""}}; //�������
    TableModel model=new DefaultTableModel(data,column); //���model

    public Result() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.toString(), "����ͳ������ʼ�������쳣", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                white, new Color(165, 163, 151)), "����ͳ��");
        getContentPane().setLayout(null);
        this.setResizable(false);
        this.setTitle("��Ϸ����");
        this.setSize(340,270);
        this.setLocation(200,200);
        jPanel1.setBorder(titledBorder1);
        jPanel1.setBounds(new Rectangle(15, 15, 301, 195));
        jPanel1.setLayout(borderLayout1);
        jButton1.setText("ȷ��");
        jButton1.addActionListener(new Result_jButton1_actionAdapter(this));
        this.getContentPane().add(jPanel1);
        jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);
        jPanel2.add(jButton1);
        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jScrollPane1.getViewport().add(jTable1);
    }

    JPanel jPanel1 = new JPanel();
    TitledBorder titledBorder1 = new TitledBorder("");
    JButton jButton1 = new JButton();
    JTable jTable1 = new JTable(model);
    JScrollPane jScrollPane1 = new JScrollPane();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel2 = new JPanel();

    //ѡ��ȷ��
    public void jButton1_actionPerformed(ActionEvent e) {
      this.setVisible(false);
    }

    /**
     * <p>Description: ������ʾ����</p>
     */
    public void updateData(){
      //ͳ����Ϸ���
      switch(Frame1.winner){
        case 0:{
          break;
        }
        case 1:{
          model.setValueAt("ʤ��",0,1);
          model.setValueAt("ʧ��",0,2);
          break;
        }
        case 2:{
          model.setValueAt("ʧ��",0,1);
          model.setValueAt("ʤ��",0,2);
          break;
        }
        case 3:{
          model.setValueAt("ƽ��",0,1);
          model.setValueAt("ƽ��",0,2);
          break;
        }
      }

      //ͳ�����岽��
      model.setValueAt(Frame1.userStep,1,1);
      model.setValueAt(Frame1.aiStep,1,2);

      //ͳ��4�ӳ�����
      model.setValueAt(Frame1.userFourLine,2,1);
      model.setValueAt(Frame1.aiFourLine,2,2);

      //ͳ��3�ӳ�����
      model.setValueAt(Frame1.userThreeLine,3,1);
      model.setValueAt(Frame1.aiThreeLine,3,2);

      //ͳ�ƶ��߳�����
      model.setValueAt(Frame1.userMultiLine,4,1);
      model.setValueAt(Frame1.aiMultiLine,4,2);

      //ͳ�ƽ��ִ���
      model.setValueAt(Frame1.forbidStyle,5,1);
      model.setValueAt(0,5,2);

      //ͳ�ƻ������
      model.setValueAt(Frame1.regretTime,6,1);
      model.setValueAt(0,6,2);
    }

}


class Result_jButton1_actionAdapter implements ActionListener {
    private Result adaptee;
    Result_jButton1_actionAdapter(Result adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
