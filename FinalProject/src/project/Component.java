package project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle; // Import Rectangle for collision detection
import java.util.ArrayList;

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
	public static final int GROUND_Y = 702;
	Player player = new Player(10,592);
	ArrayList<Enemy> E = new ArrayList<>();
	Enemy enemy = new Enemy(1000,592,260,0.1);
	Enemy enemy2 = new Enemy(1260,442,90,0.05);
	{E.add(enemy);}
	{E.add(enemy2);}
	Timer timer;
	ArrayList<Platform> plats = new ArrayList<>();
	Platform plat1 = new Platform(1200, 550);
	Platform plat2 = new Platform(650, 550);
	{plats.add(plat1);}//throws error when curly brackets removed?
	{plats.add(plat2);}
	ArrayList<Collectable> coins = new ArrayList<>();
	Collectable item = new Collectable(350,300);
    Collectable item1 = new Collectable(180,600);
    Collectable item2 = new Collectable(1000,450);
    Collectable item3 = new Collectable(580,600);
    {coins.add(item);}
    {coins.add(item1);}
    {coins.add(item2);}
    {coins.add(item3);}
    Scoreboard score = new Scoreboard();
    Panel panel;
    
    int time = 0; // used to time when to restart game 
    
    Screen screen = new Screen();
    /**
     * Constructs the main game Component and starts the update timer.
     *
     * @param panel reference to the parent Panel for input state
     */
    public Component(Panel panel) {
    	 this.panel = panel;
         score.resetScore();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		timer = new Timer(20, e -> {
			if(score.getLives() == 0) {
				if(time < 3000) {
					time += 20;
				}
				else {
					time = 0;
					score.resetScore();
					player.x = 10;
					player.y = 550;
				}
				//return; //change later to allow restart
			}
		    if (panel.leftPressed)  player.left();
		    if (panel.rightPressed) player.right();
		    if (panel.downPressed) player.fall();
		    enemy.move();
		    enemy2.move();
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
		screen.displayScreen(g2);
		player.paintPlayer(g2);
		for (Enemy e: E) {
			e.drawEnemy(g2);
		}
		for (Platform plat : plats) {
			plat.drawPlatform(g2);
		}
		for (Collectable collectable : coins) {
			collectable.drawCollectable(g2);
        }
        score.displayScore(g2);
        if(time > 0 && time < 1000) {
        	screen.displayEndScreen(g2, 3);
        }else if (time > 0 && time < 2000) {
        	screen.displayEndScreen(g2, 2);
        }
        else if(time  > 0 && time < 3000) {
        	screen.displayEndScreen(g2, 1);
        }
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
        for (Collectable collectable : coins) {
        	 Rectangle itemRect = new Rectangle(collectable.getX(), collectable.getY(), collectable.getWidth(), collectable.getHeight());
             if (collectable.isVisible() && playerRect.intersects(itemRect)) {
            	 collectable.pickup();
                 score.updateScore();
                  }} }
    /**
     * creates collisions for the top and bottom edges of the platforms
     */
    private void platformCollisions() {
    	for(Platform plat : plats) {
    		if(player.getX() + player.getWidth() > plat.getX()  && player.getX() + player.getWidth() < plat.getX() + plat.getWidth() + player.getWidth() && player.getY() + player.getHeight() < plat.getY() + plat.getHeight() && player.getY() + player.getHeight() > plat1.getY()) {
            	player.dy = 0;
            	player.y = plat1.getY() - player.getHeight();
            }
    		else if(player.getX() + player.getWidth() > plat.getX() && player.getX() + player.getWidth() < plat.getX() + plat.getWidth() + player.getWidth() && player.getY() < plat.getY() + plat.getHeight() && player.getY() > plat.getY()) {
            	player.dy = 0;
            }
    	} 
    }
           
    
    /**
     * checks if the player model intersects enemy model and kills the player if it is
     */
    private void enemyCollisions() {
        Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        for (Enemy e:E) {
        Rectangle enemyRect = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
        if (playerRect.intersects(enemyRect)) {
            player.die();
            score.dead();
            System.out.println("You Died");
        }}
    }

	// timer start and stops
	public void start() { timer.start(); }     // NEW
    public void stop()  { timer.stop(); }      // NEW
	
}

