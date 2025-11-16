package project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


/**
 * Represents an enemy character that moves back and forth horizontally
 * within a defined roaming range.
 */
public class Enemy extends Element {

    /**
     * Default width of the fallback rectangle sprite.
     */
    private static final int WIDTH = 70;
    /**
     * Default height of the fallback rectangle sprite.
     */
    private static final int HEIGHT = 110;
    /**
     * Fallback color used when the sprite cannot be loaded.
     */
    private static final Color DEFAULT_COLOR = Color.RED;

    /**
     * Image used to display the enemy sprite.
     */
    private static final BufferedImage enemySprite;
    /**
     * True if the sprite image was successfully loaded.
     */
    private static final boolean isSpriteLoaded;

    /**
     * Static block to load the enemy sprite resource once.
     */
    static {
        BufferedImage tempSprite = null;
        boolean tempLoaded = false;
        try {
            tempSprite = ImageIO.read(Objects.requireNonNull(Enemy.class.getResource("enemyV2.png")));
            tempLoaded = (tempSprite != null);
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            tempLoaded = false;
            System.out.println(Enemy.class.getResource("enemyV2.png"));
            System.out.println("sprite failed to load");
        }
        enemySprite = tempSprite;
        isSpriteLoaded = tempLoaded;
    }

    /**
     * Distance from the starting x-position that the enemy will roam.
     */
    private int roamRange = 260;
    /**
     * Speed factor controlling how fast the enemy moves within its range.
     */
    private double roamSpeed = 0.1;
    /**
     * The enemy's starting x-position (center of roaming range).
     */
    private final int homeX;
    /**
     * Internal time counter used to calculate sinusoidal movement.
     */
    private double time = 0;

    /**
     * Constructs an Enemy at the specified coordinates.
     *
     * @param x the initial x-position
     * @param y the initial y-position
     */
    public Enemy(int x, int y) {
        super(x, y, WIDTH, HEIGHT, DEFAULT_COLOR, enemySprite, isSpriteLoaded);
        this.homeX = x;
    }

    /**
     * Overloaded Constuctor
     *
     * @param x
     * @param y
     * @param roamRange
     * @param roamSpeed
     */

    public Enemy(int x, int y, int roamRange, double roamSpeed) {
        super(x, y, WIDTH, HEIGHT, DEFAULT_COLOR, enemySprite, isSpriteLoaded);
        this.homeX = x;
        this.roamRange = roamRange;
        this.roamSpeed = roamSpeed;
    }

    /**
     * Updates the enemy's position to create smooth back-and-forth movement.
     */
    public void move() {
        time += roamSpeed;
        x = (int) (homeX + Math.sin(time) * roamRange); // works to keep the enemy instance within a set area as sin goes from 0 -> set value
    }

    /**
     * Draws the enemy to the screen. Uses the sprite if available,
     * otherwise draws a colored rectangle as a fallback.
     *
     * @param g2 the graphics context used for drawing
     */
    @Override
    public void draw(Graphics2D g2) {
        if (spriteLoaded) {
            g2.drawImage(sprite, x, y, 80, 120, null);
        } else {
            g2.setColor(color);
            g2.fillRect(x, y, WIDTH, HEIGHT);
        }
    }
}