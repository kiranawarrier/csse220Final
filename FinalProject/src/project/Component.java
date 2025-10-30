package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Component extends JComponent{
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final Color BG = new Color(18, 29, 57);
	public static final Color FG = new Color(8, 128, 38);
	Player player = new Player(200,630);
	Enemy enemy = new Enemy(1000,630);

	Timer timer;
	Platform plat1 = new Platform(1200, 550);
	Platform plat2 = new Platform(650, 550);
    Collectable item1 = new Collectable(240,240);
	
	public Component() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
			
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(BG);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(FG);
		g.fillRect(0, 700, WIDTH, HEIGHT);
		player.paintPlayer(g2);
		enemy.drawEnemy(g2);
		plat1.drawPlatform(g2);
		plat2.drawPlatform(g2);
        item1.drawCollectable(g2);
	}

	public void playerJump() {
		player.jump();
		repaint();
	}

	//public void movePlayer() {
		//player.left();
		//player.right();
		//repaint();
	//}

	public void playerLeft() {
		player.left();
		repaint();
	}
	
	public void playerRight() {
		player.right();
		repaint();
	}
	
	public void playerGravity() {
		player.gravity();
	}
	
	public void start() { timer.start(); }     // NEW
    public void stop()  { timer.stop(); }      // NEW
	
}

