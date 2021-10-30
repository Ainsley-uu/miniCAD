package cad;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class MyString extends Shape{
    private String str;
	private int fontSize;

	public MyString(int x1, int y1, int x2, int y2, Color color, int stroke, String str){
		super(x1, y1, x2, y2, color, stroke);
		this.str = str;
	}

	public boolean click(double x, double y){
		int x1 = Math.min(getX1(), getX2());
		int x2 = Math.max(getX1(), getX2());
		int y1 = Math.min(getY1(), getY2());
		int y2 = Math.max(getY1(), getY2());
		return ( x >= x1 && x <= x2 && y >= y1 && y <= y2);
	}

	public void draw(Graphics2D g){
		int x1 = getX1();
		int x2 = getX2();
		int y1 = getY1();
		int y2 = getY2();
		if( x1 > x2 ){
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if( y1 > y2 ){
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		g.setStroke(getStroke());// 画笔大小
		g.setColor(getColor());//设置画笔颜色
		fontSize = 10;

		Font font = new Font("楷体", Font.BOLD, fontSize);
		g.setFont(font);
		FontMetrics fontMetrics = g.getFontMetrics();
		Rectangle2D StringBounds = fontMetrics.getStringBounds(str, g);
		while(StringBounds.getWidth() > (x2 - x1)) {
			font = new Font("楷体",Font.BOLD,--fontSize);
			g.setFont(font);
			fontMetrics = g.getFontMetrics();
			StringBounds = fontMetrics.getStringBounds(str, g);
		}
		while(StringBounds.getWidth() < (x2 - x1)) {
			font = new Font("楷体",Font.BOLD,++fontSize);
			g.setFont(font);
			fontMetrics = g.getFontMetrics();
			StringBounds = fontMetrics.getStringBounds(str, g);
		}		
		
		g.drawString(str, getX1(), getY2());
	}
}
