package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.Timer;
/**
 * Main game rendering and update component.
 * Handles drawing game objects, applying gravity, player movement,
 * enemy movement, and maintaining the game loop timer.
 */
@SuppressWarnings("serial")
public class Component extends JComponent{
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final Color BG = new Color(18, 29, 57);
	public static final Color FG = new Color(8, 128, 38);
	public static final int GROUND_Y = 713;
	Player player = new Player(10,550);
	Enemy enemy = new Enemy(1000,592);

	Timer timer;
	Platform plat1 = new Platform(1200, 550);
	Platform plat2 = new Platform(650, 550);
    Collectable item1 = new Collectable(240,240);
    Scoreboard score = new Scoreboard();
    Panel panel;
    /**
     * Constructs the main game Component and starts the update timer.
     *
     * @param panel reference to the parent Panel for input state
     */
    public Component(Panel panel) {
    	 this.panel = panel;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		timer = new Timer(20, e -> {
		    if (panel.leftPressed)  player.left();
		    if (panel.rightPressed) player.right();
		    enemy.move();
		    int w = 1560;
		    if (player.x > w) {
		        player.x = -35;
		    }
		    if (player.x + 35 < 0) {
		        player.x = w;
		    }
		    player.gravity();
		    player.updateY();
		    if (player.y + 120 >= GROUND_Y) {
		        player.y = GROUND_Y - 120;
		        player.dy = 0;
		    }
		    

		    repaint();
		});
	    timer.start();
	}
    /**
     * Draws all game elements to the screen.
     */
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
        score.displayScore(g2);
	}
	// player jumps
	public void playerJump() {
		player.jump();
		repaint();
	}
	// player moves left
	public void playerLeft() {
		player.left();
		repaint();
	}
	// player moves right
	public void playerRight() {
		player.right();
		repaint();
	}
	
	// timer start and stops
	public void start() { timer.start(); }     // NEW
    public void stop()  { timer.stop(); }      // NEW
	
}

