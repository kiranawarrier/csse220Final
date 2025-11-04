package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Screen {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	public static final Color BG = new Color(18, 29, 57);
	public static final Color FG = new Color(8, 128, 38);
	
	
	ImageIcon endScreen = new ImageIcon("endScreen.png");
    JLabel endScreenLabel = new JLabel(endScreen);
    Font topFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 100);

    Font bottomFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 30);
    
    public Screen() {
    	
    }
    
    public void displayScreen(Graphics2D g2){
    	g2.setColor(BG);
    	g2.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	g2.setColor(FG);
    	g2.fillRect(0, 700, WIDTH, HEIGHT - 700);
    }
    
    public void displayEndScreen(Graphics2D g2, int timeLeft) {
    	g2.setColor(Color.black);
    	g2.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	g2.setColor(Color.red);
    	g2.setFont(topFont);
    	g2.drawString("Game Over",500,400);
    	
    	g2.setColor(Color.white);
    	g2.setFont(bottomFont);
    	g2.drawString("Restarting in: " + timeLeft + " seconds", 553, 450);
    }
}
