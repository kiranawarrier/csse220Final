package project;
import java.awt.Color;
import java.awt.Graphics2D;
public class Enemy{
 int x,y;
 
 Color color = Color.RED;
 private int dx = 1;
 private int dy = 2;
 public Enemy(int x, int y) {
	 this.x = x; this.y = y;
 }
 
 public void Move(int width, int height) {
	 // add random movements using dx 
	 
 }
 public void draw(Graphics2D g2) {
     g2.setColor(color);
     g2.fillRect(x, y, x,y);
 }
}
