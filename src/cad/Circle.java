package cad;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle extends Shape {
 
    public Circle(int a, int b, int c, int d, Color color,
			int s) {
		super(a, b, c, d, color, s);
	}

	public void draw(Graphics2D g) {
		int x1 = Math.min(getX1(), getX2());
		int x2 = Math.max(getX1(), getX2());
		int y1 = Math.min(getY1(), getY2());
		int y2 = Math.max(getY1(), getY2());
		g.setStroke(getStroke());// 画笔大小
		g.setColor(getColor());// 设置画笔颜色
		Ellipse2D circle = new Ellipse2D.Double(x1, y1, x2 - x1, y2 - y1);
		g.draw(circle);
	}

	public boolean click(double x, double y){
		int x1 = Math.min(getX1(), getX2());
		int x2 = Math.max(getX1(), getX2());
		int y1 = Math.min(getY1(), getY2());
		int y2 = Math.max(getY1(), getY2());	
		return ( x >= x1 && x <= x2 && y >= y1 && y <= y2);
	}
    
}
