package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class Collectable {

    private int x, y;
    private static int WIDTH = 20;
    private static int HEIGHT = 20;

    Color color = Color.BLACK;

    public Collectable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawCollectable(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(y, x, WIDTH, HEIGHT);
    }

}
