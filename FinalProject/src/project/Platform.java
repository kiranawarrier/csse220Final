package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class Platform {
	private int x, y;
	Color color = Color.BLACK;
	private static final int WIDTH = 200;
	private static final int HEIGHT = 25;
	
	public Platform(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void drawPlatform(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect(x, y, WIDTH, HEIGHT);
	}
}
