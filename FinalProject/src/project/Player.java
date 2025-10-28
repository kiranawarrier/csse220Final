package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player {
	int x,y;
	Color color = Color.GREEN;
	
	protected void paintPlayer(Graphics g) {
	//super.paintPlayer(g);
	Graphics2D g2 = (Graphics2D) g;
	g.setColor(color);
	g.drawRect(x, y, y, x);
	
}
}