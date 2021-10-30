package cad;

import java.awt.*;
import java.util.Stack;
import javax.swing.*;

public class Main extends JPanel {

    public static void main(String[] args) {
		new Main().init();
	}
    JFrame frame = new JFrame("CAD");
    private Stack<Shape>DoList = new Stack<>();

    public void init(){
        frame.setVisible(true);
        frame.setBounds(80,80,1000, 800);
        frame.setResizable(false); 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Draw dl = new Draw(frame, this, DoList);
        JPanel east = new JPanel();

        east.setPreferredSize(new Dimension(130,800));
        east.setBackground(Color.lightGray);
        frame.add(east, BorderLayout.EAST);

        String[] str = { "铅笔", "刷子", "喷枪", "直线", "圆", "矩形", "文字", "橡皮", "放大", "缩小","加粗", "变细","选择","删除","回退" , "撤销回退", "保存" ,"打开"};
		for (int i = 0; i < str.length; i++) {
			JButton button = new JButton(str[i]);
			east.add(button);
			button.setPreferredSize(new Dimension(110, 30));
			button.addActionListener(dl);
		}

        Color[] colorArray = {  Color.BLACK, Color.lightGray, Color.BLUE, Color.GREEN, Color.RED,
            Color.ORANGE, Color.PINK,
            new Color(123, 100, 132) };
        // 循环遍历colorArray数组，根据数组中的元素来实例化按钮对象
        for (int i = 0; i < colorArray.length; i++) {
            JButton button = new JButton();
            button.setBackground(colorArray[i]);
            button.setPreferredSize(new Dimension(50, 25));
            button.addActionListener(dl);
            east.add(button);
        }

        frame.add(this, BorderLayout.CENTER);
        frame.setBackground(Color.WHITE);

        frame.setVisible(true);
        Graphics g = this.getGraphics();
        
        this.addMouseListener(dl);
        this.addMouseMotionListener(dl);

        dl.setG(g);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 900, 800);

        for( Shape shape : DoList ){
            shape.draw((Graphics2D)g);
        }
    }
}
