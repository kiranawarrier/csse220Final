package project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a solid rectangular platform that the player and enemies
 * can stand or move on.
 * <p>
 * This class inherits all its state (x, y, width, height, etc.)
 * from the abstract Element class.
 */
public class Platform extends Element {

    private static final int PLATFORM_WIDTH = 200;
    private static final int PLATFORM_HEIGHT = 25;
    private static final Color DEFAULT_COLOR = Color.BLACK;

    private static final BufferedImage platformSprite;
    private static final boolean isSpriteLoaded;

    /**
     * Static initializer block.
     * This code runs exactly ONCE when the Platform class is first loaded,
     * ensuring the sprite is loaded efficiently.
     */
    static {
        BufferedImage tempSprite = null;
        boolean tempLoaded = false;
        try {
            tempSprite = ImageIO.read(Objects.requireNonNull(Platform.class.getResource("platform.png")));
            tempLoaded = (tempSprite != null);
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            System.err.println("CRITICAL_ERROR: Failed to load platform.png resource.");
        }

        platformSprite = tempSprite;
        isSpriteLoaded = tempLoaded;
    }

    /**
     * Constructs a Platform at the given coordinates.
     * All other properties (width, height, sprite) are set
     * from the static constants.
     *
     * @param x the x-position
     * @param y the y-position
     */
    public Platform(int x, int y) {

        super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT, DEFAULT_COLOR, platformSprite, isSpriteLoaded);
    }

    /**
     * Draws the platform.
     * This method is required by the abstract Element class.
     *
     * @param g2 the graphics context used for drawing
     */
    @Override
    public void draw(Graphics2D g2) {
        if (isSpriteLoaded) {
            g2.drawImage(platformSprite, super.getX(), super.getY() - 3, PLATFORM_WIDTH, PLATFORM_HEIGHT + 10, null);
        } else {
            g2.setColor(super.getColor());
            g2.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }
    }
}