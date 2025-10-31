package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	int x, y;
	int dx = 15;
	int dy = 0;
	int grav = 5;
	Color color = Color.GREEN;
	private static final int WIDTH = 40;
	private static final int HEIGHT = 70;
	
	public Player(int x, int y) {
        this.x = x; this.y = y;
        try {
			sprite = ImageIO.read(Player.class.getResource("characterV1.png"));
			spriteLoaded = (sprite != null);
		} catch (IOException | IllegalArgumentException ex) {
			spriteLoaded = false; 
			System.out.println(Player.class.getResource("characterV1.png")); // just put this line here to trouble shoot because sometimes character doesnt show up
			System.out.println("character failed to load");
		}
	}
    
	
	public void left() {
		x -= dx;
	}
	
	public void right() {
		x += dx;
	}
	
	public void updateY() {
		y += dy;
	}
	
	public void jump() {
		 if (dy == 0) {
		        dy = -30;
		System.out.println(this.x + "x on right " + this.y);}
	}
	
	public void gravity() {
		dy += 2;
	}
	
	protected void paintPlayer(Graphics2D g2) {
		int drawX = x;
		int drawY = y;
		if (spriteLoaded) { 
			g2.drawImage(sprite, drawX, drawY, 80, 120, null);
	}
		else {g2.setColor(color);
	    g2.fillRect(x, y, WIDTH, HEIGHT);}
	}
	}
