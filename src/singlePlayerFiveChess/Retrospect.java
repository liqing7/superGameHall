package singlePlayerFiveChess;

import java.awt.*;

import javax.swing.*;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * <p>Title: 类说明</p>
 *
 * <p>Description: 回顾控制面板</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author goodboy
 * @version 2.1
 */
public class Retrospect extends JFrame {

    public Retrospect() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.toString(), "回顾演示面板初始化产生异常", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(null);
        this.setResizable(false);
        this.setTitle("回顾控制面板");
        this.setSize(new Dimension(260, 190));
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
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setBounds(new Rectangle(18, 17, 217, 130));
        jPanel1.setLayout(null);
        jLabel1.setText("<html><b><font color=blue>演示速度:");
        jLabel1.setBounds(new Rectangle(14, 12, 80, 25));
        jSlider1.setToolTipText("回顾演示速度");
        jSlider1.setBounds(new Rectangle(99, 12, 100, 25));
        jSlider1.addMouseListener(new Retrospect_jSlider1_mouseAdapter(this));
        jButton1.setBounds(new Rectangle(14, 90, 80, 25));
        jButton1.setToolTipText("暂停回顾演示");
        jButton1.setText("暂停演示");
        jButton1.addActionListener(new Retrospect_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(119, 90, 80, 25));
        jButton2.setToolTipText("退出回顾控制面板");
        jButton2.setText("退出");
        jButton2.addActionListener(new Retrospect_jButton2_actionAdapter(this));
        jProgressBar1.setBounds(new Rectangle(14, 56, 185, 15));
        this.getContentPane().add(jPanel1);
        jPanel1.add(jLabel1);
        jPanel1.add(jSlider1);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        jPanel1.add(jProgressBar1);
        jSlider1.setMinimum(0);
        jSlider1.setMaximum(1500);
        jSlider1.setValue(SinglePlayerFrame.retroSpeed);
    }

    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JSlider jSlider1 = new JSlider();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JProgressBar jProgressBar1 = new JProgressBar();

    //控制回顾演示线程
    public void jButton1_actionPerformed(ActionEvent e) {
      if(SinglePlayerFrame.retroSuspend){ //暂停演示线程
        SinglePlayerFrame.retroSuspend=false;
        jButton1.setText("暂停演示");
      }
      else{ //恢复演示线程
        SinglePlayerFrame.retroSuspend=true;
        jButton1.setText("继续演示");
      }
    }

    /**
     * <p>Description: 设置进度条当前值</p>
     * @param n 进度条当前值
     */
    public void showStep(int n){
      jProgressBar1.setValue(n);
    }

    /**
     * <p>Description: 初始化进度条</p>
     * @param n 进度条最大值
     */
    public void initProgressBar(int n){
      jProgressBar1.setMaximum(n);
      jProgressBar1.setMinimum(0);
    }

    //鼠标调节演示速度
    public void jSlider1_mouseReleased(MouseEvent e) {
      SinglePlayerFrame.retroSpeed=jSlider1.getValue();
    }

    //选择退出回顾演示
    public void jButton2_actionPerformed(ActionEvent e) {
      SinglePlayerFrame.retroSuspend=true;
      setVisible(false);
    }
}


class Retrospect_jSlider1_mouseAdapter extends MouseAdapter {
    private Retrospect adaptee;
    Retrospect_jSlider1_mouseAdapter(Retrospect adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseReleased(MouseEvent e) {
        adaptee.jSlider1_mouseReleased(e);
    }
}


class Retrospect_jButton2_actionAdapter implements ActionListener {
    private Retrospect adaptee;
    Retrospect_jButton2_actionAdapter(Retrospect adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class Retrospect_jButton1_actionAdapter implements ActionListener {
    private Retrospect adaptee;
    Retrospect_jButton1_actionAdapter(Retrospect adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
