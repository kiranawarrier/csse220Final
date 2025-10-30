package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	int x, y;
	int dx = 5;
	int dy = 0;
	int grav = 5;
	Color color = Color.GREEN;
	private int width = 35;
	private int height = 70;
	
	public Player(int x, int y) {
        this.x = x; this.y = y;
    }
	
	public void left() {
		x -= dx;
	}
	
	public void right() {
		x += dx;
	}
	
	public void updateY() {
		y -= dy;
	}
	
	public void jump() {
		dy += 50;
		gravity();
	}
	
	public void gravity() {
		dy -= grav;
	}
	
	protected void paintPlayer(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}