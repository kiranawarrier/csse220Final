package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class Collectable {

    private int x, y;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    public boolean isVisible = true;


    Color color = Color.YELLOW;

    public Collectable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawCollectable(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x, y, WIDTH, HEIGHT);
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
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
