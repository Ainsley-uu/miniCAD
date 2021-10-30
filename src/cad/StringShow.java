package cad;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StringShow extends JDialog implements ActionListener {
    JTextField text;
	JButton button;
	String string;

	StringShow(JFrame prentFrame, String title){
		super(prentFrame, title, true);

		JPanel p = new JPanel();
		JLabel l = new JLabel("请输入:");
		p.add(l);
		text = new JTextField(30);
		text.addActionListener(this);
		p.add(text);
		getContentPane().add("Center", p);

		JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("取 消");
        cancelButton.addActionListener(this);
        button = new JButton("确 定");
        button.addActionListener(this);
        p2.add(button);
        p2.add(cancelButton);
        getContentPane().add("South", p2);
		pack();
	}

	public void actionPerformed(ActionEvent event) {
 
        Object source = event.getSource();
        if ((source == button)) {
            // 如果确定按钮被按下，则将文本矿的文本添加到父窗体的文本域中
            string = text.getText();
        }
        text.selectAll();
        // 隐藏对话框
        setVisible(false);
    }

    public String getStr() {
        return string;
    }
}

