package cad;

import java.util.Stack;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Load extends JDialog implements ActionListener{
    JTextField text;
    JButton button;
    private String file;
    private Stack<Shape> shapes = new Stack<>();
    private Main panel;

    Load(JFrame prentFrame, String title, Stack<Shape> shapes, Main panel )
    {
        super(prentFrame, title, true );
        this.shapes = shapes;
        this.panel = panel;
    
        JPanel p1 = new JPanel();
        JLabel label = new JLabel("请输入文本:");
        p1.add(label);
        text = new JTextField(30);
        text.addActionListener(this);
        p1.add(text);
        getContentPane().add("Center", p1);
    
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
        if ((source == button)) {
            file = text.getText();
            try{
                FileInputStream fis = new FileInputStream(file); 
                ObjectInputStream ois = new ObjectInputStream(fis); 
                shapes.clear();;
                shapes.addAll((Stack<Shape>) ois.readObject()); 
                ois.close();
                panel.repaint();
            } catch(IOException Exc) {
                System.out.println(Exc);
            } catch(ClassNotFoundException Exc) {
                System.out.println(Exc);
            }
        }
        text.selectAll();
        setVisible(false);
    }
}
