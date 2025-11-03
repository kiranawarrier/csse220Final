package project;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Represents a solid rectangular platform that the player and enemies
 * can stand or move on.
 */
public class Platform {
	 /** The x-position of the platform. */
    private int x;
    /** The y-position of the platform. */
    private int y;
    /** The default color of the platform. */
    Color color = Color.BLACK;
    /** The width of the platform. */
    private static final int WIDTH = 200;
    /** The height of the platform. */
    private static final int HEIGHT = 25;
    /**
     * Constructs a Platform at the given coordinates.
     * @param x the x-position
     * @param y the y-position
     */
	public Platform(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	 /**
     * Draws the platform as a filled rectangle.
     * @param g2 the graphics context used for drawing
     */
	public void drawPlatform(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect(x, y, WIDTH, HEIGHT);
	}
}
