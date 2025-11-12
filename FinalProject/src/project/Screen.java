package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Screen {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	public static final Color BG = new Color(18, 29, 57);
	public static final Color MG = new Color (0, 75, 68);
	public static final Color FG = new Color(8, 128, 38);
	
	private BufferedImage background;
	private boolean bgLoaded;
	
	
	ImageIcon endScreen = new ImageIcon("endScreen.png");
    JLabel endScreenLabel = new JLabel(endScreen);
    Font topFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 100);

    Font bottomFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 30);
    
    public Screen() {
    	try {
    		background = ImageIO.read(Objects.requireNonNull(Screen.class.getResource("FinalProjectBackground.png")));
    		bgLoaded = (background != null);
    	}catch(IOException | IllegalArgumentException ex){
    		bgLoaded = false;
    		System.out.println("FAILED: " + Screen.class.getResource("FinalProjectBackground.png"));
            System.out.print("  background failed to load");
    	}
    }
    
    public void displayScreen(Graphics2D g2){
    	if(bgLoaded) {
    		g2.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
    	}
    	else {
			g2.setColor(BG);
	    	g2.fillRect(0, 0, WIDTH, HEIGHT);
	    	
	    	g2.setColor(MG); //draw all the middle ground objects
	    	g2.fillRoundRect(300, 400, 250, 700, 250, 250);
	    	g2.fillRoundRect(100, 600, 50, 700, 50, 50);
	    	g2.fillRoundRect(200, 500, 50, 700, 50, 50);
	    	
	    	g2.fillRoundRect(900, 200, 250, 700, 250, 250);
	    	g2.fillRoundRect(800, 300, 50, 700, 50, 50);
	    	g2.fillRoundRect(700, 450, 50, 700, 50, 50);
	    	g2.fillRoundRect(600, 400, 50, 700, 50, 50);
	    	
	    	g2.setColor(FG);
	    	g2.fillRect(0, 700, WIDTH, HEIGHT - 700);
    	}
    }
    public void displayWinScreen(Graphics2D g2, int timeLeft, String level) {
    	g2.setColor(Color.BLUE);
    	g2.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	g2.setColor(Color.GREEN);
    	g2.setFont(topFont);
    	g2.drawString("You Win " + level, 500,400);
    	
    	g2.setColor(Color.white);
    	g2.setFont(bottomFont);
    	g2.drawString("Restarting in: " + timeLeft + " seconds", 553, 450);
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
