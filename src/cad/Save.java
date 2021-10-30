package cad;

import java.util.Stack;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Save extends JDialog implements ActionListener{
    JTextField text;
    JButton button;
    private String file;
    private Stack<Shape>DoList = new Stack<>();

    Save( JFrame prentFrame, String title, Stack<Shape> shapes){
        super(prentFrame, title, true );
        DoList = shapes;

        JPanel p1 = new JPanel();
        JLabel label = new JLabel("请输入文本:");
        p1.add(label);
        text = new JTextField(30);
        text.addActionListener(this);
        p1.add(text);
        getContentPane().add("Center", p1);
 
        // 添加确定和取消按钮
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("取 消");
        cancelButton.addActionListener(this);
        button = new JButton("确 定");
        button.addActionListener(this);
        p2.add(button);
        p2.add(cancelButton);
        getContentPane().add("South", p2);
 
        // 调整对话框布局大小
        pack();
    }
    
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();
        if( source == button){
            file = text.getText();
            try{
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(DoList);
                oos.close();
            }catch(IOException e){
                System.out.println(e+"----");
            }
        }
        text.selectAll();
        setVisible(false);
    }
}
