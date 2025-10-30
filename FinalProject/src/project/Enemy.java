package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Enemy{
	private int x,y;
	private static final int WIDTH = 70;
	private static final int HEIGHT = 70;
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	Color color = Color.RED;
	private int dx = 4;
	 
	public Enemy(int x, int y) {
		this.x = x; this.y = y;
		try {
			sprite = ImageIO.read(Enemy.class.getResource("enemyV1.png"));
			spriteLoaded = (sprite != null);
		} catch (IOException | IllegalArgumentException ex) {
			spriteLoaded = false; // fallback to oval
		}
	}
	 
	public void move() {
		x += dx;
	}
	
	public void drawEnemy(Graphics2D g2){
		int drawX = x;
		int drawY = y;
		if (spriteLoaded) { 
			g2.drawImage(sprite, drawX, drawY, WIDTH, HEIGHT, null);
	}
		else {g2.setColor(color);
	    g2.fillRect(x, y, WIDTH, HEIGHT);}
	}
}
