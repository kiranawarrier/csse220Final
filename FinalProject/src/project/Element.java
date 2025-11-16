package project;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Element {

    protected int x, y;
    protected int width, height;
    protected Color color;
    protected BufferedImage sprite;
    protected boolean spriteLoaded;

    public Element(int x, int y, int width, int height, Color color, BufferedImage sprite, Boolean spriteLoaded) {
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
