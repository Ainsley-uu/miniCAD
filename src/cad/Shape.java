package cad;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.awt.Color;

public abstract class Shape implements Serializable{
    private int x1, x2, y1, y2;
    private Color color;
    private int stroke;

    public Shape( int x1, int y1, int x2, int y2, Color c, int s ){
        stroke = s;
        color = c;

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public abstract void draw(Graphics2D g);
    public abstract boolean click( double curX, double curY );
    
    public void move( int dx, int dy ){
        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;
    }

    public int getX1(){
        return x1;
    }

    public void setX1(int x1){
        this.x1 = x1;
    }

    public int getX2(){
        return x2;
    }

    public void setX2(int x2){
        this.x2 = x2;
    }

    public int getY1(){
        return y1;
    }

    public void setY1(int y1){
        this.y1 = y1;
    }

    public int getY2(){
        return y2;
    }

    public void setY2(int y2){
        this.y2 = y2;
    }

    public Color getColor(){
        return color;
    }

    public void setColor( Color c){
        color = c;
    }

    public BasicStroke getStroke() {
		return new BasicStroke((float)stroke);
	}
    
    public void setStroke( int s ){
        stroke = s;
    }

    public void addX1(int dx){
        x1 += dx;
    }

    public void addX2(int dx){
        x2 += dx;
    }
    public void addY1(int dy){
        y1 += dy;
    }

    public void addY2(int dy){
        y2 += dy;
    }


    public void enlarge() {
		if(x1 > x2) {
			x1++; 
			x2--;
		} else {
			x1--;
			x2++;
		}
		if(y1 > y2) {
			y1++; 
			y2--;
		} else {
			y1--;
			y2++;
		}
	}

	public void narrow() {
		if(x1 > x2) {
			x1--; 
			x2++;
		} else {
			x1++;
			x2--;
		}
		if(y1 > y2) {
			y1--; 
			y2++;
		} else {
			y1++;
			y2--;
		}
	}
}