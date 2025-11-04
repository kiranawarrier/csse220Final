package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle; // Import Rectangle for collision detection

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
	public static final int GROUND_Y = 702;
	Player player = new Player(10,592);
	Enemy enemy = new Enemy(1000,592);

	Timer timer;
	Platform plat1 = new Platform(1200, 550);
	Platform plat2 = new Platform(650, 550);
    Collectable item1 = new Collectable(180,600);
    Scoreboard score = new Scoreboard(3);
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
		    if (panel.downPressed) player.fall();
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
            if (player.y + player.getHeight() >= GROUND_Y) { // Use getHeight()
                player.y = GROUND_Y - player.getHeight();    // Use getHeight()
                player.dy = 0;
            }
            platformCollisions();
		    enemyCollisions();
            if (panel.spacePressed) itemCollisions();

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
		
		if(player.getY() + player.getHeight() >= GROUND_Y) {
			player.jump();
			repaint();
		}
		if(player.getX() + player.getWidth() > plat1.getX()  && player.getX() + player.getWidth() < plat1.getX() + plat1.getWidth() + player.getWidth()) {
			player.jump();
			repaint();
		}
		if(player.getX() + player.getWidth() > plat2.getX() && player.getX() + player.getWidth() < plat2.getX() + plat2.getWidth() + player.getWidth()) {
			player.jump();
			repaint();
		}
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
    // Checks if there is a collision between the player and the collectable item
    private void itemCollisions() {
        Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        Rectangle itemRect = new Rectangle(item1.getX(), item1.getY(), item1.getWidth(), item1.getHeight());
        
        if (item1.isVisible() && playerRect.intersects(itemRect)) {
            item1.pickup();
            score.updateScore();
            
        }
    }
    
    private void platformCollisions() {
    	if(player.getX() + player.getWidth() > plat1.getX()  && player.getX() + player.getWidth() < plat1.getX() + plat1.getWidth() + player.getWidth() && player.getY() + player.getHeight() < plat1.getY() + plat1.getHeight() && player.getY() + player.getHeight() > plat1.getY()) {
        	player.dy = 0;
        	player.y = plat1.getY() - player.getHeight();
        }
        else if(player.getX() + player.getWidth() > plat2.getX() && player.getX() + player.getWidth() < plat2.getX() + plat2.getWidth() + player.getWidth() && player.getY() + player.getHeight() < plat2.getY() + plat2.getHeight() && player.getY() + player.getHeight() > plat2.getY()) {
        	player.dy = 0;
        	player.y = plat2.getY() - player.getHeight();
        }
        else if(player.getX() + player.getWidth() > plat1.getX() && player.getX() + player.getWidth() < plat1.getX() + plat1.getWidth() + player.getWidth() && player.getY() < plat1.getY() + plat1.getHeight() && player.getY() > plat1.getY()) {
        	player.dy = 0;
        }
        else if(player.getX() + player.getWidth() > plat2.getX() && player.getX() + player.getWidth() < plat2.getX() + plat2.getWidth() + player.getWidth() && player.getY() < plat2.getY() + plat2.getHeight() && player.getY() > plat2.getY()) {
        	player.dy = 0;
        
        }
        
    }
    private void enemyCollisions() {
        Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        if (playerRect.intersects(enemyRect)) {
            player.die();
            score.dead();
            System.out.println("You Died");
        }
    }
    		

	// timer start and stops
	public void start() { timer.start(); }     // NEW
    public void stop()  { timer.stop(); }      // NEW
	
}

