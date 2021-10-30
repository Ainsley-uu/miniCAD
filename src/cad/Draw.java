package cad;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

public class Draw implements ActionListener, MouseListener, MouseMotionListener  {
    private String type;
    private Color color;
    private Graphics graphic;
    private int x1, y1, x2, y2;
    private Shape shape;
    private Main panel;
	private JFrame frame;
    private StringShow strdialog;
	private Save savedialog;
	private Load loaddialog;
    private Stack<Shape>DoList = new Stack<>();
    private Stack<Shape>TodoList = new Stack<>();
    Random r = new Random();

    int stroke = 1;

    public Draw(JFrame f, Main p, Stack<Shape> s){
        type = "选择";
        color = Color.BLACK;
        panel = p;
        DoList = s;
        frame = f;
    }

    public void setG(Graphics g){
        graphic = g;
    }

    public void actionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if( text == null || text.equals("") ){
            color = button.getBackground();
            if(shape != null ){
                shape.setColor(color);
                panel.repaint();
            }
        }else{
            type = button.getText();
            if( type.equals("选择")){
                shape = null;
            }else if( type.equals("删除")){
                if( shape != null ){
                    DoList.remove(shape);
                    panel.repaint();
                }
                type = "选择";
            }else if(type.equals("保存")){
                if(savedialog == null ){
                    savedialog = new Save(frame, "输入对话框", DoList);
                }
                savedialog.setBounds(400, 200, 350, 150);				
				savedialog.setVisible(true);
				type = "选择";
            }else if( type.equals("打开")){
                if(loaddialog == null) {
					loaddialog = new Load(frame, "输入对话框", DoList, panel);
				}
				loaddialog.setBounds(400, 200, 350, 150);				
				loaddialog.setVisible(true);
				type = "选择";		
            }else if(type.equals("文字")){
                if(strdialog == null) {
					strdialog = new StringShow(frame, "输入对话框");
				}
				strdialog.setBounds(400, 200, 350, 150);
				strdialog.setVisible(true);
            }else if(type.equals("回退") && !DoList.isEmpty()){
                TodoList.push(DoList.pop());
                panel.repaint();
                type = "选择";
            }else if(type.equals("撤销回退")){
                DoList.push(TodoList.pop());
                panel.repaint();
                type = "选择";
            }else if(type.equals("放大")){
                shape.enlarge();
                panel.repaint();
            }else if(type.equals("加粗")){
                if( shape != null ){
                    float width = shape.getStroke().getLineWidth();
                    shape.setStroke((int)++width);
                    panel.repaint();
                }
            }else if( type.equals("变细")){
                if( shape != null ){
                    float width = shape.getStroke().getLineWidth();
                    shape.setStroke((int)--width);
                    panel.repaint();
                }
            }else if( type.equals("缩小")){
                shape.narrow();
                panel.repaint();
            }
        }
    }


    public void mousePressed(MouseEvent e){
        x1 = e.getX();
        y1 = e.getY();

        Point p = e.getPoint();
        shape = null;
        if(type.equals("选择")){
            for( Shape temp : DoList ){
                if( temp.click(p.getX(), p.getY()) ){
                    shape = temp;
                    break;
                }
            }
        }else if( type.equals("文字")){
			shape = new MyString(x1, y1, x1, y1, color, stroke, strdialog.getStr());
			DoList.push(shape);
        }else if( type.equals("直线")){
            shape = new Line(x1, y1, x1, y1, color, 3);
            DoList.push(shape);
        }else if( type.equals("矩形")){
            shape = new Rect(x1, y1, x1, y1, color,3);
            DoList.push(shape);
        }else if( type.equals("圆") ){
            shape = new Circle(x1, y1, x1, y1, color, 3);
            DoList.push(shape);
        }
    }

    public void mouseReleased(MouseEvent e){
        x2 = e.getX();
        y2 = e.getY();
    }

    public void mouseDragged(MouseEvent e){
        x2 = e.getX();
        y2 = e.getY();
		Graphics2D g2d = (Graphics2D)graphic;
        if( type.equals("铅笔")){
            shape = new Line(x1, y1, x2, y2, color, 2);
            shape.draw(g2d);
            DoList.push(shape);
            x1 = x2;
            y1 = y2;
        }else if( type.equals("刷子")){
            shape = new Line(x1, y1, x2, y2, color, 10);
            shape.draw(g2d);
            DoList.push(shape);
			x1 = x2;
			y1 = y2;
        }else if (type.equals("喷枪")) {
			g2d.setColor(color);
			shape = new Gum(x1, y1, x2, y2, color, stroke);
			shape.draw(g2d);
            DoList.push(shape);

			x1 = x2;
			y1 = y2;
        }else if (type.equals("橡皮")) {
			shape = new Eraser(x1, y1, x2, y2, color, 10);
			shape.draw(g2d);
            DoList.push(shape);
			x1 = x2;
			y1 = y2;
        }else if( type.equals("选择")){
            if( shape != null ){
                shape.addX1(x2-x1);
                shape.addX2(x2-x1);
                shape.addY1(y2-y1);
                shape.addY2(y2-y1);
                panel.repaint();
            }
            x1 = x2;
            y1 = y2;
        }else if( type.equals("圆") || type.equals("直线") || type.equals("矩形") || type.equals("文字")){
            shape.setX2(x2);
            shape.setY2(y2);
            panel.repaint();
        }
    }

    public void mouseMoved(MouseEvent e){}
    public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    
}
