package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy{
	private int x,y;
	private static final int WIDTH = 70;
	private static final int HEIGHT = 70;
	
	Color color = Color.RED;
	private int dx = 4;
	 
	public Enemy(int x, int y) {
		this.x = x; this.y = y;
	}
	 
	public void move() {
		x += dx;
	}
	public void drawEnemy(Graphics2D g2) {
	    g2.setColor(color);
	    g2.fillRect(x, y, WIDTH, HEIGHT);
	}
}
