package project;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Element {

    private int x, y;
    private int width, height;
    private Color color;
    private BufferedImage sprite;
    private boolean spriteLoaded;

    public Element(int x, int y, int width, int height, Color color,  BufferedImage sprite,  boolean spriteLoaded) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.sprite = sprite;
        this.spriteLoaded = spriteLoaded;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }

    public abstract void draw(Graphics2D g2);

}
