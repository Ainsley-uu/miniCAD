package cad;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Gum extends Shape {
    Random r = new Random();
	public Gum(int x1, int y1, int x2, int y2,Color color,int stroke) {
		super(x1, y1, x2, y2, color, stroke);
	}
	public void draw(Graphics2D g){
		g.setStroke(getStroke());
		g.setColor(g.getColor());
		g.drawLine(getX2(),getY2 (),getX2(),getY2());
		for (int i = 0; i < 10; i++) {
			int p = r.nextInt(10);
			int q = r.nextInt(10); 
			g.drawLine(getX2() + p, getY2() + q, getX2() + p, getY2() + q);
		}
	}
	public boolean click(double x, double y){
		return false;
	}
}
