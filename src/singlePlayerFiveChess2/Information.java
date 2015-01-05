package singlePlayerFiveChess2;

import java.awt.*;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Title: 类说明</p>
 *
 * <p>Description: 程序信息面板</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author goodboy
 * @version 2.1
 */
public class Information extends JFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JEditorPane jEditorPane1 = new JEditorPane();
    JPanel jPanel2 = new JPanel();
    JButton jButton1 = new JButton();

    public Information() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.toString(), "信息面板初始化产生异常", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        jButton1.setText("确定");
        jButton1.addActionListener(new Information_jButton1_actionAdapter(this));
        this.setTitle("关于 JAVA 五子棋 v2.1版");
        this.setResizable(false);
        this.setSize(new Dimension(400,300));
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
        jPanel2.add(jButton1);
        jPanel1.add(jLabel1);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jEditorPane1);

        this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jLabel1.setText("JAVA 五子棋 v2.1版");
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setText("<html><center><br>作者：智霖<br><br>"+"发布时间：2006/9/18<br><br>"
                             +"E-mail:goodboy36@tom.com<br><br>"+"<i>你的意见是我改进的最大动力^_^</i>");
    }

    //选择确定
    public void jButton1_actionPerformed(ActionEvent e) {
      jEditorPane1.setText("<html><center><br>作者：智霖<br><br>"+"发布时间：2006/9/18<br><br>"
              +"E-mail:goodboy36@tom.com<br><br>"+"<i>你的意见是我改进的最大动力^_^</i>");
      this.setVisible(false);
    }
}


class Information_jButton1_actionAdapter implements ActionListener {
    private Information adaptee;
    Information_jButton1_actionAdapter(Information adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
