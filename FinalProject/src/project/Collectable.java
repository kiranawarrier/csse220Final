package project;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Collectable {

    private int x, y;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    public boolean isVisible = false;

    private BufferedImage sprite;
    private boolean spriteLoaded = false;

    public static final int RENDERED_WIDTH = 40;
    public static final int RENDERED_HEIGHT = 40;

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
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return spriteLoaded ? RENDERED_WIDTH : WIDTH;
    }

    public int getHeight() {
        return spriteLoaded ? RENDERED_HEIGHT : HEIGHT;
    }

    public boolean isVisible() {
        return isVisible;
    }

}
