package project;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Collectable {

    private int x, y;
    public boolean isVisible = false;

    private BufferedImage sprite;
    private boolean spriteLoaded = false;

    public static final int RENDERED_WIDTH = 40, WIDTH = 40;
    public static final int RENDERED_HEIGHT = 40, HEIGHT = 40;

    Color color = Color.YELLOW;

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

    public void drawCollectable(Graphics2D g2) {
        int drawX = x;
        int drawY = y;

        if (spriteLoaded) {
            g2.drawImage(sprite, drawX, drawY, RENDERED_WIDTH, RENDERED_HEIGHT, null);
        } else {
            g2.setColor(color);
            g2.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public void pickup() {
        this.isVisible = false;
        try{
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(
                            Objects.requireNonNull(Collectable.class.getResource("coin_sound.wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
            catch(Exception ex)
        {System.out.println(" coin sound failed");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        if (spriteLoaded) {
            return RENDERED_WIDTH;
        }
        return WIDTH;
    }

    public int getHeight() {
        if (spriteLoaded) {
            return RENDERED_HEIGHT;
        }
        return HEIGHT;
    }

    public boolean isVisible() {
        return isVisible;
    }

}
