package singlePlayerFiveChess;

import java.awt.*;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Five chess rules frame
 * @author Qing
 *
 */
public class Rule extends JFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JEditorPane jEditorPane1 = new JEditorPane();
    JButton jButton1 = new JButton();
    JPanel jPanel2 = new JPanel();
    JLabel jLabel1 = new JLabel();

    public Rule() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.toString(), "��Ϸ��������ʼ�������쳣", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        jButton1.setText("ȷ��");
        jButton1.addActionListener(new Rule_jButton1_actionAdapter(this));
        this.setTitle("���������");
        this.setResizable(false);
        this.setSize(new Dimension(400,300));
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
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
        jLabel1.setText("���������");
        jPanel1.add(jButton1);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
        jPanel2.add(jLabel1);
        jScrollPane1.getViewport().add(jEditorPane1);
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setText("<html><b>����˳��</b><br>"+"<u>����</u>�ȣ�<u>����</u>�󣬴�<i>��Ԫ</i>��ʼ�໥˳�����ӡ�<br>"
                +"<b>�ж�ʤ����</b><br>"+"1.����������<i>����</i>��<i>б��</i>��<i>����</i>�γ�<i>����</i>��<i>��ͬɫ</i><i>��</i>�����ӵ�һ��Ϊ<font color=red>ʤ</font>��<br>"
                +"2.<u>����</u><font color=blue>����</font>��<font color=red>��</font>��<u>����</u>��<font color=blue>����</font>��<br>"
                +"3.<font color=blue>����</font>��<font color=blue>����</font>ͬʱ�γɣ�<i>����</i>Ϊ<font color=red>ʤ</font>��<br>"
                +"4.<u>�ڷ�</u><font color=blue>����</font>�γ�ʱ��<u>�׷�</u>Ӧ����ָ������<u>�׷�</u>δ���ֻ��ֺ�δָ��������<i>Ӧ��</i>��������<u>�ڷ�</u><font color=red>��</font>��<br>"
                +"5.��<i>�վ�</i>ǰ������<u>�׷�</u>��ʱ����<u>�ڷ�</u><font color=blue>��������</font>�㣬<i>ָ��</i>�˵������ʤ������<u>�׷�</u><font color=red>ʤ</font>��<br>"
                +"<b>������ͣ�</b><br>"+"<font color=blue>����</font>�������̵�<i>����һ����</i>�ϣ��γɵ�<i>5������ͬɫ���Ӳ����������</i>��<br>"
                +"<font color=blue>����</font>����������<i>һ��</i>�����γ�<font color=blue>����</font>������<br>"
                +"<font color=blue>����</font>����<i>������</i>����<i>����</i>���ġ�<br>"
                +"<font color=blue>����</font>��ֻ��<i>һ����</i>����<i>����</i>���ġ�<br>"
                +"<font color=blue>����</font>���Ծ������<i>ʹ��</i>������<font color=red>��</font>�������ֶΡ�<br>"
                +"<font color=blue>��������</font>��<u>����</u>һ������<i>ͬʱ</i>�γ�<i>����</i>��<i>��������</i>��<font color=blue>����</font>��<br>"
                +"<font color=blue>���Ľ���</font>��<u>����</u>һ������<i>ͬʱ</i>�γ�<i>����</i>��<i>��������</i>��<font color=blue>����</font>��<font color=blue>����</font>��<br>"
                +"<font color=blue>��������</font>��<u>����</u>һ�������γ�<i>һ��</i>��<i>һ������</i>��<font color=blue>����</font>��");
    }

    //���ȷ����ť
    public void jButton1_actionPerformed(ActionEvent e) {
      jEditorPane1.setText("<html><b>����˳��</b><br>"+"<u>����</u>�ȣ�<u>����</u>�󣬴�<i>��Ԫ</i>��ʼ�໥˳�����ӡ�<br>"
            +"<b>�ж�ʤ����</b><br>"+"1.����������<i>����</i>��<i>б��</i>��<i>����</i>�γ�<i>����</i>��<i>��ͬɫ</i><i>��</i>�����ӵ�һ��Ϊ<font color=red>ʤ</font>��<br>"
            +"2.<u>����</u><font color=blue>����</font>��<font color=red>��</font>��<u>����</u>��<font color=blue>����</font>��<br>"
            +"3.<font color=blue>����</font>��<font color=blue>����</font>ͬʱ�γɣ�<i>����</i>Ϊ<font color=red>ʤ</font>��<br>"
            +"4.<u>�ڷ�</u><font color=blue>����</font>�γ�ʱ��<u>�׷�</u>Ӧ����ָ������<u>�׷�</u>δ���ֻ��ֺ�δָ��������<i>Ӧ��</i>��������<u>�ڷ�</u><font color=red>��</font>��<br>"
            +"5.��<i>�վ�</i>ǰ������<u>�׷�</u>��ʱ����<u>�ڷ�</u><font color=blue>��������</font>�㣬<i>ָ��</i>�˵������ʤ������<u>�׷�</u><font color=red>ʤ</font>��<br>"
            +"<b>������ͣ�</b><br>"+"<font color=blue>����</font>�������̵�<i>����һ����</i>�ϣ��γɵ�<i>5������ͬɫ���Ӳ����������</i>��<br>"
            +"<font color=blue>����</font>����������<i>һ��</i>�����γ�<font color=blue>����</font>������<br>"
            +"<font color=blue>����</font>����<i>������</i>����<i>����</i>���ġ�<br>"
            +"<font color=blue>����</font>��ֻ��<i>һ����</i>����<i>����</i>���ġ�<br>"
            +"<font color=blue>����</font>���Ծ������<i>ʹ��</i>������<font color=red>��</font>�������ֶΡ�<br>"
            +"<font color=blue>��������</font>��<u>����</u>һ������<i>ͬʱ</i>�γ�<i>����</i>��<i>��������</i>��<font color=blue>����</font>��<br>"
            +"<font color=blue>���Ľ���</font>��<u>����</u>һ������<i>ͬʱ</i>�γ�<i>����</i>��<i>��������</i>��<font color=blue>����</font>��<font color=blue>����</font>��<br>"
            +"<font color=blue>��������</font>��<u>����</u>һ�������γ�<i>һ��</i>��<i>һ������</i>��<font color=blue>����</font>��");

      this.setVisible(false);
    }
}


class Rule_jButton1_actionAdapter implements ActionListener {
    private Rule adaptee;
    Rule_jButton1_actionAdapter(Rule adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
