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
 * Represents the player character, including movement, gravity, and rendering.
 */
public class Player extends Element {

    private static final int WIDTH = 70;
    private static final int HEIGHT = 110;
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private static final BufferedImage playerSprite;
    private static final boolean isSpriteLoaded;

    /**
     * Static block to load the player sprite once.
     */
    static {
        BufferedImage tempSprite = null;
        boolean tempLoaded = false;
        try {
            tempSprite = ImageIO.read(Objects.requireNonNull(Player.class.getResource("characterV2.png")));
            tempLoaded = (tempSprite != null);
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            System.err.println("CRITICAL_ERROR: Failed to load characterV2.png resource.");
            System.err.println("FAILED: " + Player.class.getResource("characterV2.png"));
        }
        playerSprite = tempSprite;
        isSpriteLoaded = tempLoaded;
    }

    int dx = 15;
    double dy = 0;
    int grav = 5;

    /**
     * Creates a player at the given coordinates and attempts to load the sprite.
     *
     * @param x location, y location
     *
     */
    public Player(int x, int y) {
        super(x, y, WIDTH, HEIGHT, DEFAULT_COLOR, playerSprite, isSpriteLoaded);
    }

    // move left
    public void left() {
        x -= dx;
    }

    // move right
    public void right() {
        x += dx;
    }

    // update the y level of player
    public void updateY() {
        y += dy;
    }

    public void fall() {
        dy = 60;
    }

    // jump and print where for debugging location
    public void jump() {
        if (dy == 0) {
            dy = -35;
            System.out.println(this.x + "x on right " + this.y);
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Player.class.getResource("jumpsound.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                System.out.println(" jump sound failed");
            }
        }

    }

    // gravity
    public void gravity() {
        dy += 3;
    }

    // paint and draw the sprite of player
    @Override
    public void draw(Graphics2D g2) {
        if (this.spriteLoaded) {
            g2.drawImage(this.sprite, this.x, this.y, 80, 120, null);
        } else {
            g2.setColor(this.color);
            g2.fillRect(this.x, this.y, WIDTH, HEIGHT);
        }
    }

    // starting logic for resetting on death
    public void die() {
        new Thread(() -> {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Player.class.getResource("deathscream.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(1900);
                clip.stop();
                clip.close();
            } catch (Exception ex) {
                System.out.println("FAILED: " + Player.class.getResource("deathscream.wav"));
                ex.printStackTrace();
            }
        }).start();

        x = 10;
        y = 550;

    }

    public double getDy() {
        return dy;
    }
}
