package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Represents the player character, including movement, gravity, and rendering.
 */
public class Player {
    private BufferedImage sprite;
    private boolean spriteLoaded = false;
    int x, y;
    int dx = 15;
    double dy = 0;
    int grav = 5;
    Color color = Color.GREEN;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 110;

    /**
     * Creates a player at the given coordinates and attempts to load the sprite.
     *
     * @param x location, y location
     *
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            sprite = ImageIO.read(Player.class.getResource("characterV2.png"));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false;
            System.out.println("FAILED: " + Player.class.getResource("characterV2.png"));
            System.out.print("  character failed to load");
        }
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

    // jump and print where for debugging location
    public void jump() {
        if (dy == 0) {
            dy = -35;
            System.out.println(this.x + "x on right " + this.y);
            try{
                AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(
                        Player.class.getResource("jumpsound.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
            catch(Exception ex)
            {System.out.println(" jump sound failed");
            }
        }
        
    }

    // gravity
    public void gravity() {
        dy += 3;
    }

    // paint and draw the sprite of player
    protected void paintPlayer(Graphics2D g2) {
        int drawX = x;
        int drawY = y;
        if (spriteLoaded) {
            g2.drawImage(sprite, drawX, drawY, 80, 120, null);
        } else {
            g2.setColor(color);
            g2.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    // starting logic for resetting on death
    public void die() {
        x = 10;
        y = 550;


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
