package cad;

import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shape {
    public Line(int x1, int y1, int x2, int y2, Color color,
            int stroke) {
        super(x1, y1, x2, y2, color, stroke);
    }

    public void draw( Graphics2D g){
        g.setStroke(getStroke());
        g.setColor(getColor());
        g.drawLine(getX1(), getY1(), getX2(), getY2());
    }

    public boolean click(double x, double y){
        int x1 = Math.min(getX1(), getX2());
		int x2 = Math.max(getX1(), getX2());
		int y1 = Math.min(getY1(), getY2());
		int y2 = Math.max(getY1(), getY2());
        return ( x >= x1 && x <= x2 && y >= y1 && y <= y2);
    }
}
