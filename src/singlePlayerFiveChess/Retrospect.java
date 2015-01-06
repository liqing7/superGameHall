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
 * <p>Title: ��˵��</p>
 *
 * <p>Description: �ع˿������</p>
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
            JOptionPane.showMessageDialog(null, exception.toString(), "�ع���ʾ����ʼ�������쳣", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(null);
        this.setResizable(false);
        this.setTitle("�ع˿������");
        this.setSize(new Dimension(260, 190));
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
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setBounds(new Rectangle(18, 17, 217, 130));
        jPanel1.setLayout(null);
        jLabel1.setText("<html><b><font color=blue>��ʾ�ٶ�:");
        jLabel1.setBounds(new Rectangle(14, 12, 80, 25));
        jSlider1.setToolTipText("�ع���ʾ�ٶ�");
        jSlider1.setBounds(new Rectangle(99, 12, 100, 25));
        jSlider1.addMouseListener(new Retrospect_jSlider1_mouseAdapter(this));
        jButton1.setBounds(new Rectangle(14, 90, 80, 25));
        jButton1.setToolTipText("��ͣ�ع���ʾ");
        jButton1.setText("��ͣ��ʾ");
        jButton1.addActionListener(new Retrospect_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(119, 90, 80, 25));
        jButton2.setToolTipText("�˳��ع˿������");
        jButton2.setText("�˳�");
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

    //���ƻع���ʾ�߳�
    public void jButton1_actionPerformed(ActionEvent e) {
      if(SinglePlayerFrame.retroSuspend){ //��ͣ��ʾ�߳�
        SinglePlayerFrame.retroSuspend=false;
        jButton1.setText("��ͣ��ʾ");
      }
      else{ //�ָ���ʾ�߳�
        SinglePlayerFrame.retroSuspend=true;
        jButton1.setText("������ʾ");
      }
    }

    /**
     * <p>Description: ���ý�������ǰֵ</p>
     * @param n ��������ǰֵ
     */
    public void showStep(int n){
      jProgressBar1.setValue(n);
    }

    /**
     * <p>Description: ��ʼ��������</p>
     * @param n ���������ֵ
     */
    public void initProgressBar(int n){
      jProgressBar1.setMaximum(n);
      jProgressBar1.setMinimum(0);
    }

    //��������ʾ�ٶ�
    public void jSlider1_mouseReleased(MouseEvent e) {
      SinglePlayerFrame.retroSpeed=jSlider1.getValue();
    }

    //ѡ���˳��ع���ʾ
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
