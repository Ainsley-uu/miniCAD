package cad;


import java.awt.Color;
import java.awt.Graphics2D;

public class Rect extends Shape {
    public Rect(int x1, int y1, int x2, int y2, Color color, int s) {
        super(x1, y1, x2, y2, color, s);
    }

    public void draw(Graphics2D g){
        g.setStroke(getStroke());// 设置画笔的粗细
		g.setColor(getColor());// 设置画笔的颜色
        int x1 = Math.min(getX1(), getX2());
		int x2 = Math.max(getX1(), getX2());
		int y1 = Math.min(getY1(), getY2());
		int y2 = Math.max(getY1(), getY2());
		g.drawRect(x1, y1, x2-x1, y2-y1);// 绘制矩形
    }

    public boolean click(double x, double y){
        int x1 = Math.min(getX1(), getX2());
		int x2 = Math.max(getX1(), getX2());
		int y1 = Math.min(getY1(), getY2());
		int y2 = Math.max(getY1(), getY2());
		return ( x >= x1 && x <= x2 && y >= y1 && y <= y2);
    }
    
}
