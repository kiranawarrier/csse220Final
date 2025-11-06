package project;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a collectable item that can be picked up by the player.
 */

public class Collectable {

    public static final int RENDERED_WIDTH = 40, WIDTH = 40;
    public static final int RENDERED_HEIGHT = 40, HEIGHT = 40;
    private final int x;
    private final int y;
    public boolean isVisible = true;
    Color color = Color.YELLOW;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;

    /**
     * Constructs a collectable at the given coordinates. A sprite is drawn if it can be loaded.
     *
     * @param x
     * @param y
     */

    public Collectable(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            sprite = ImageIO.read(Objects.requireNonNull(Player.class.getResource("coin.png")));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false;
            System.out.println("FAILED: " + Player.class.getResource("coin.png"));
            System.out.print("  coin failed to load");
        }
    }

    /**
     * Draws the collectable sprite or a solid rectangle if the sprite fails to load.
     *
     * @param g2
     */

    public void drawCollectable(Graphics2D g2) {
        if (isVisible) {
            if (spriteLoaded) {
                g2.drawImage(sprite, x, y, RENDERED_WIDTH, RENDERED_HEIGHT, null);
            } else {
                g2.setColor(color);
                g2.fillRect(x, y, WIDTH, HEIGHT);
            }
        }
    }

    /**
     * The logic for picking up the collectable.
     * When collected, the sprite is hidden and a sound effect plays.
     */

    public void pickup() {
        this.isVisible = false;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Collectable.class.getResource("coin_sound.wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Coin sound failed");
        }

    }

    /**
     * @return int x location
     */

    public int getX() {
        return x;
    }

    /**
     * @return int y location
     */

    public int getY() {
        return y;
    }

    /**
     * @return the width of the sprite
     */

    public int getWidth() {
        if (spriteLoaded) {
            return RENDERED_WIDTH;
        }
        return WIDTH;
    }

    /**
     * @return the height of the sprite
     */

    public int getHeight() {
        if (spriteLoaded) {
            return RENDERED_HEIGHT;
        }
        return HEIGHT;
    }

    /**
     * @return true if the sprite is visible
     */

    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Sets the visibility of the sprite.
     * @param visible
     */

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

}
