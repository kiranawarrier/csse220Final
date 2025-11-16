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

public class Collectable extends Element {

    private static final int COLLECTABLE_WIDTH = 40;
    private static final int COLLECTABLE_HEIGHT = 40;
    private static final Color DEFAULT_COLOR = Color.YELLOW;

    private static final BufferedImage coinSprite;
    private static final boolean isSpriteLoaded;

    static {
        BufferedImage tempSprite = null;
        boolean tempLoaded = false;
        try {
            tempSprite = ImageIO.read(Objects.requireNonNull(Collectable.class.getResource("coin.png")));
            tempLoaded = (tempSprite != null);
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            System.err.println("CRITICAL_ERROR: Failed to load coin.png resource.");
            System.err.println("FAILED: " + Collectable.class.getResource("coin.png"));
        }
        coinSprite = tempSprite;
        isSpriteLoaded = tempLoaded;
    }

    public boolean isVisible = true;

    /**
     * Constructs a collectable at the given coordinates. A sprite is drawn if it can be loaded.
     *
     * @param x
     * @param y
     */

    public Collectable(int x, int y) {
        super(x, y, COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT, DEFAULT_COLOR, coinSprite, isSpriteLoaded);
    }

    /**
     * Draws the collectable sprite or a solid rectangle if the sprite fails to load.
     *
     * @param g2
     */

    @Override
    public void draw(Graphics2D g2) {
        if (isVisible) {
            if (this.spriteLoaded) {
                g2.drawImage(this.sprite, this.x, this.y, this.width, this.height, null);
            } else {
                g2.setColor(this.color);
                g2.fillRect(this.x, this.y, this.width, this.height);
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
     * @return true if the sprite is visible
     */

    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Sets the visibility of the sprite.
     *
     * @param visible
     */

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

}