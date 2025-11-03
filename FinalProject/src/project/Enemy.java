package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * Represents an enemy character that moves back and forth horizontally
 * within a defined roaming range. 
 */
public class Enemy {
    /** Current x-position of the enemy. */
    private int x;
    /** Current y-position of the enemy. */
    private int y;
    /** Default width of the fallback rectangle sprite. */
    private static final int WIDTH = 70;
    /** Default height of the fallback rectangle sprite. */
    private static final int HEIGHT = 110;
    /** Image used to display the enemy sprite. */
    private BufferedImage sprite;
    /** True if the sprite image was successfully loaded. */
    private boolean spriteLoaded = false;
    /** Fallback color used when the sprite cannot be loaded. */
    Color color = Color.RED;
    /** Distance from the starting x-position that the enemy will roam. */
    private int roamRange = 200;
    /** Speed factor controlling how fast the enemy moves within its range. */
    private double roamSpeed = 0.09;
    /** The enemy's starting x-position (center of roaming range). */
    private int homeX;
    /** Internal time counter used to calculate sinusoidal movement. */
    private double time = 0;
    /**
     * Constructs an Enemy at the specified coordinates.
     *
     * @param x the initial x-position
     * @param y the initial y-position
     */
	public Enemy(int x, int y) {
		this.x = x; this.y = y;
		this.homeX = x; 
		try {
			sprite = ImageIO.read(Enemy.class.getResource("enemyV2.png"));
			spriteLoaded = (sprite != null);
		} catch (IOException | IllegalArgumentException ex) {
			spriteLoaded = false; // fallback to oval
			System.out.println(Enemy.class.getResource("enemyV2.png"));
			System.out.println("sprite failed to load");
		}
	}
	 /**
     * Updates the enemy's position to create smooth back-and-forth movement.
     */
	public void move() {
		time += roamSpeed;
		x = (int)(homeX + Math.sin(time) * roamRange); // works to keep the enemy instance within a set area as sin goes from 0 -> set value
	}
	 /**
     * Draws the enemy to the screen. Uses the sprite if available,
     * otherwise draws a colored rectangle as a fallback.
     *
     * @param g2 the graphics context used for drawing
     */
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
	
	public void drawEnemy(Graphics2D g2){
		int drawX = x;
		int drawY = y;
		if (spriteLoaded) { 
			g2.drawImage(sprite, drawX, drawY, 80, 120, null);
	}
		else {g2.setColor(color);
	    g2.fillRect(x, y, WIDTH, HEIGHT);}
	}
}
