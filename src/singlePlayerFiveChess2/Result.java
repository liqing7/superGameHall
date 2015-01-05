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
 * <p>Title: 类说明</p>
 *
 * <p>Description: 游戏结果显示面板</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author goodboy
 * @version 2.1
 */
public class Result extends JFrame {
    String[] column={"统计项目","用户","AI"}; //表格列
    String[][] data={{"游戏结果","",""},{"行棋步数","",""},{"4子成线数","",""},{"3子成线数","",""},
                    {"多线成型数","",""},{"禁手次数","",""},{"悔棋次数","",""}}; //表格数据
    TableModel model=new DefaultTableModel(data,column); //表格model

    public Result() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.toString(), "数据统计面板初始化产生异常", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                white, new Color(165, 163, 151)), "数据统计");
        getContentPane().setLayout(null);
        this.setResizable(false);
        this.setTitle("游戏结束");
        this.setSize(340,270);
        this.setLocation(200,200);
        jPanel1.setBorder(titledBorder1);
        jPanel1.setBounds(new Rectangle(15, 15, 301, 195));
        jPanel1.setLayout(borderLayout1);
        jButton1.setText("确定");
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

    //选择确定
    public void jButton1_actionPerformed(ActionEvent e) {
      this.setVisible(false);
    }

    /**
     * <p>Description: 更新显示数据</p>
     */
    public void updateData(){
      //统计游戏结果
      switch(Frame1.winner){
        case 0:{
          break;
        }
        case 1:{
          model.setValueAt("胜利",0,1);
          model.setValueAt("失败",0,2);
          break;
        }
        case 2:{
          model.setValueAt("失败",0,1);
          model.setValueAt("胜利",0,2);
          break;
        }
        case 3:{
          model.setValueAt("平局",0,1);
          model.setValueAt("平局",0,2);
          break;
        }
      }

      //统计行棋步数
      model.setValueAt(Frame1.userStep,1,1);
      model.setValueAt(Frame1.aiStep,1,2);

      //统计4子成线数
      model.setValueAt(Frame1.userFourLine,2,1);
      model.setValueAt(Frame1.aiFourLine,2,2);

      //统计3子成线数
      model.setValueAt(Frame1.userThreeLine,3,1);
      model.setValueAt(Frame1.aiThreeLine,3,2);

      //统计多线成型数
      model.setValueAt(Frame1.userMultiLine,4,1);
      model.setValueAt(Frame1.aiMultiLine,4,2);

      //统计禁手次数
      model.setValueAt(Frame1.forbidStyle,5,1);
      model.setValueAt(0,5,2);

      //统计悔棋次数
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
