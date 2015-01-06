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
            JOptionPane.showMessageDialog(null, exception.toString(), "游戏规则面板初始化产生异常", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        jButton1.setText("确定");
        jButton1.addActionListener(new Rule_jButton1_actionAdapter(this));
        this.setTitle("五子棋规则");
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
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
        jLabel1.setText("五子棋规则");
        jPanel1.add(jButton1);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
        jPanel2.add(jLabel1);
        jScrollPane1.getViewport().add(jEditorPane1);
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setText("<html><b>行棋顺序：</b><br>"+"<u>黑棋</u>先，<u>白棋</u>后，从<i>天元</i>开始相互顺序落子。<br>"
                +"<b>判断胜负：</b><br>"+"1.最先在棋盘<i>横向</i>，<i>斜向</i>，<i>竖向</i>形成<i>连续</i>的<i>相同色</i><i>五</i>个棋子的一方为<font color=red>胜</font>。<br>"
                +"2.<u>黑棋</u><font color=blue>禁手</font>判<font color=red>负</font>，<u>白棋</u>无<font color=blue>禁手</font>。<br>"
                +"3.<font color=blue>五连</font>与<font color=blue>禁手</font>同时形成，<i>先五</i>为<font color=red>胜</font>。<br>"
                +"4.<u>黑方</u><font color=blue>禁手</font>形成时，<u>白方</u>应立即指出。若<u>白方</u>未发现或发现后未指明而继续<i>应子</i>，则不能判<u>黑方</u><font color=red>负</font>。<br>"
                +"5.在<i>终局</i>前，无论<u>白方</u>何时发现<u>黑方</u><font color=blue>长连禁手</font>点，<i>指出</i>此点而宣布胜利，判<u>白方</u><font color=red>胜</font>。<br>"
                +"<b>术语解释：</b><br>"+"<font color=blue>长连</font>：在棋盘的<i>任意一条线</i>上，形成的<i>5个以上同色棋子不间隔地相连</i>。<br>"
                +"<font color=blue>活三</font>：本方再走<i>一步</i>可以形成<font color=blue>活四</font>的三。<br>"
                +"<font color=blue>活四</font>：有<i>两个点</i>可以<i>成五</i>的四。<br>"
                +"<font color=blue>冲四</font>：只有<i>一个点</i>可以<i>成五</i>的四。<br>"
                +"<font color=blue>禁手</font>：对局中如果<i>使用</i>将被判<font color=red>负</font>的行棋手段。<br>"
                +"<font color=blue>三三禁手</font>：<u>黑棋</u>一子落下<i>同时</i>形成<i>两个</i>或<i>两个以上</i>的<font color=blue>活三</font>。<br>"
                +"<font color=blue>四四禁手</font>：<u>黑棋</u>一子落下<i>同时</i>形成<i>两个</i>或<i>两个以上</i>的<font color=blue>冲四</font>或<font color=blue>活四</font>。<br>"
                +"<font color=blue>长连禁手</font>：<u>黑棋</u>一子落下形成<i>一个</i>或<i>一个以上</i>的<font color=blue>长连</font>。");
    }

    //点击确定按钮
    public void jButton1_actionPerformed(ActionEvent e) {
      jEditorPane1.setText("<html><b>行棋顺序：</b><br>"+"<u>黑棋</u>先，<u>白棋</u>后，从<i>天元</i>开始相互顺序落子。<br>"
            +"<b>判断胜负：</b><br>"+"1.最先在棋盘<i>横向</i>，<i>斜向</i>，<i>竖向</i>形成<i>连续</i>的<i>相同色</i><i>五</i>个棋子的一方为<font color=red>胜</font>。<br>"
            +"2.<u>黑棋</u><font color=blue>禁手</font>判<font color=red>负</font>，<u>白棋</u>无<font color=blue>禁手</font>。<br>"
            +"3.<font color=blue>五连</font>与<font color=blue>禁手</font>同时形成，<i>先五</i>为<font color=red>胜</font>。<br>"
            +"4.<u>黑方</u><font color=blue>禁手</font>形成时，<u>白方</u>应立即指出。若<u>白方</u>未发现或发现后未指明而继续<i>应子</i>，则不能判<u>黑方</u><font color=red>负</font>。<br>"
            +"5.在<i>终局</i>前，无论<u>白方</u>何时发现<u>黑方</u><font color=blue>长连禁手</font>点，<i>指出</i>此点而宣布胜利，判<u>白方</u><font color=red>胜</font>。<br>"
            +"<b>术语解释：</b><br>"+"<font color=blue>长连</font>：在棋盘的<i>任意一条线</i>上，形成的<i>5个以上同色棋子不间隔地相连</i>。<br>"
            +"<font color=blue>活三</font>：本方再走<i>一步</i>可以形成<font color=blue>活四</font>的三。<br>"
            +"<font color=blue>活四</font>：有<i>两个点</i>可以<i>成五</i>的四。<br>"
            +"<font color=blue>冲四</font>：只有<i>一个点</i>可以<i>成五</i>的四。<br>"
            +"<font color=blue>禁手</font>：对局中如果<i>使用</i>将被判<font color=red>负</font>的行棋手段。<br>"
            +"<font color=blue>三三禁手</font>：<u>黑棋</u>一子落下<i>同时</i>形成<i>两个</i>或<i>两个以上</i>的<font color=blue>活三</font>。<br>"
            +"<font color=blue>四四禁手</font>：<u>黑棋</u>一子落下<i>同时</i>形成<i>两个</i>或<i>两个以上</i>的<font color=blue>冲四</font>或<font color=blue>活四</font>。<br>"
            +"<font color=blue>长连禁手</font>：<u>黑棋</u>一子落下形成<i>一个</i>或<i>一个以上</i>的<font color=blue>长连</font>。");

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
