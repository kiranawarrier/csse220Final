package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
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
    
    private BufferedImage sprite;
    private boolean spriteLoaded;
    /**
     * Constructs a Platform at the given coordinates.
     * @param x the x-position
     * @param y the y-position
     */
	public Platform(int x, int y) {
		this.x = x;
		this.y = y;
		
		try {
            sprite = ImageIO.read(Objects.requireNonNull(Player.class.getResource("platform.png")));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false;
            System.out.println("FAILED: " + Player.class.getResource("platform.png"));
            System.out.print("  platform failed to load");
        }
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
		if(spriteLoaded) {
			g2.drawImage(sprite, x, y - 3, WIDTH, HEIGHT + 10, null);
		}
		else {
			g2.setColor(color);
			g2.fillRect(x, y, WIDTH, HEIGHT);
		}
	}
}
