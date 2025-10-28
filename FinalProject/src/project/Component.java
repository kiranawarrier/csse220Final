package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Component extends JComponent{
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final Color BG = new Color(18, 29, 57);
	public static final Color FG = new Color(8, 128, 38);
	
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
		
		
	}

	public void playerJump() {
		
	}

	public void movePlayer() {
		
	}
}
