package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	int x,y;
	int dx = 5;
	int dy = 1;
	Color color = Color.GREEN;
	private int width = 35;
	private int height = 70;
	
	public Player(int x, int y) {
        this.x = x; this.y = y;
    }

	protected void paintPlayer(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	public void left() {
		x -= dx;
		
	}
	public void right() {
		x += dx;
	}
	public void jump() {
		y -= dy; // need to fix
	}
}