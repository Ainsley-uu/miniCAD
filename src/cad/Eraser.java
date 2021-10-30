package cad;


import java.awt.Color;
import java.awt.Graphics2D;

public class Eraser extends Shape {

    public Eraser(int x1, int y1, int x2, int y2, Color color, int s) {
		super(x1, y1, x2, y2,color,s);
	}

    public void draw(Graphics2D g){
    	g.setStroke(getStroke());// 橡皮大小
		g.setColor(Color.white);
		g.drawLine(getX1(),getY1(),getX2(),getY2());
    }

	public boolean click(double x, double y){
		return false;
	}
}
