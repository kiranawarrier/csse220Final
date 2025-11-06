package project;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * Main game rendering and update component.
 * Handles drawing game objects, applying gravity, player movement,
 * enemy movement, and maintaining the game loop timer.
 */
@SuppressWarnings("serial")

public class Component extends JComponent {

    private int WIDTH = 1500, HEIGHT = 1080;
    public static final int GROUND_Y = 702;
    int time = 0; // used to time when to restart game

    Timer timer;
    Panel panel;

    Player player;
    ArrayList<Enemy> E = new ArrayList<>();
    ArrayList<Platform> plats = new ArrayList<>();
    ArrayList<Collectable> coins = new ArrayList<>();

    Scoreboard score = new Scoreboard();
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

        try {

            InputStream is = Component.class.getResourceAsStream("resources/levels/level1.txt");
            if (is == null) {
                throw new IOException("Level file not found!");
            }
            parseLevel(is, "level1.txt");
        } catch (IOException e) {
            System.err.println("Failed to load level:");
            e.printStackTrace();
        }

        timer = new Timer(20, e -> tick());
        timer.start();
    }

    private void tick() {
			if(score.getLives() == 0) {
				if(time < 3000) {
					time += 20;
				}
				else {
					time = 0;
					score.resetScore();
					player.x = 10;
					player.y = 550;
					for(Collectable coin : coins) {
						coin.setVisible(true);
					}
				}
				//return; //change later to allow restart
			}
			if (score.getScore() == 4) {
				score.resetScore();
				player.die();
				// TEMPORARY CODE FOR WHEN SCORE IS MAX
				if (panel.nextlvl) {System.out.println("next level");

				}
				if (panel.restart) { System.out.println("restart");

				}

			}
		    if (panel.leftPressed)  player.left();
		    if (panel.rightPressed) player.right();
		    if (panel.downPressed) player.fall();
		    enemy.move();
		    enemy2.move();
		    int w = 1500;
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

        if (panel.h_pressed) {
            score.resetHighScore();
        }
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
        for (Enemy e : E) {
            e.drawEnemy(g2);
        }
        for (Platform plat : plats) {
            plat.drawPlatform(g2);
        }
        for (Collectable collectable : coins) {
            collectable.drawCollectable(g2);
        }
        score.displayScore(g2);
        if ((time > 0 && time < 1000)) {
            screen.displayEndScreen(g2, 3);
        } else if (time > 0 && time < 2000) {
            screen.displayEndScreen(g2, 2);
        } else if (time > 0 && time < 3000) {
            screen.displayEndScreen(g2, 1);
        }
    }

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

    /**
     * Checks if the player model intersects collectable model and adds points to the score if it is.
     */
    private void itemCollisions() {
        Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        for (Collectable collectable : coins) {
            Rectangle itemRect = new Rectangle(collectable.getX(), collectable.getY(), collectable.getWidth(), collectable.getHeight());
            if (collectable.isVisible() && playerRect.intersects(itemRect)) {
                collectable.pickup();
                score.updateScore();
            }
        }
    }
    /**
     * creates collisions for the top and bottom edges of the platforms
     */
    private void platformCollisions() {
        for (Platform plat : plats) {
            if (player.getX() + player.getWidth() > plat.getX() && player.getX() + player.getWidth() < plat.getX() + plat.getWidth() + player.getWidth() && player.getY() + player.getHeight() < plat.getY() + plat.getHeight() && player.getY() + player.getHeight() > plat.getY()) {
                player.dy = 0;
                player.y = plat.getY() - player.getHeight();
            } else if (player.getX() + player.getWidth() > plat.getX() && player.getX() + player.getWidth() < plat.getX() + plat.getWidth() + player.getWidth() && player.getY() < plat.getY() + plat.getHeight() && player.getY() > plat.getY()) {
                player.dy = 0;
            }
        }
    }


    /**
     * checks if the player model intersects enemy model and kills the player if it is
     */
    private void enemyCollisions() {
        Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        for (Enemy e : E) {
            Rectangle enemyRect = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            if (playerRect.intersects(enemyRect)) {
                player.die();
                score.dead();
                System.out.println("You Died");
            }
        }
    }

    /**
     * Parses the level
     * @param in
     * @param levelName
     * @throws java.io.IOException
     */
    private void parseLevel(java.io.InputStream in, String levelName) throws java.io.IOException {
        String name = levelName;

        int W = WIDTH, H = HEIGHT;

        try (var br = new java.io.BufferedReader(new java.io.InputStreamReader(in, java.nio.charset.StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.strip();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] t = line.split("\\s+");
                switch (t[0]) {

                    case "PLAYER" -> {
                        int x1 = Integer.parseInt(t[1]);
                        int y1 = Integer.parseInt(t[2]);
                        player = new Player(x1, y1);
                    }

                    case "WINDOW" -> {
                        W = Integer.parseInt(t[1]);
                        H = Integer.parseInt(t[2]);
                    }
                    case "ENEMY1", "ENEMY2" -> {
                        int x1 = Integer.parseInt(t[1]);
                        int y1 = Integer.parseInt(t[2]);
                        int roamRange1 = Integer.parseInt(t[3]);
                        double roamSpeed1 = Double.parseDouble(t[4]);
                        E.add(new Enemy(x1, y1, roamRange1, roamSpeed1));
                    }

                    case "PLATFORM1", "PLATFORM2" -> {
                        int x1 = Integer.parseInt(t[1]);
                        int y1 = Integer.parseInt(t[2]);
                        plats.add(new Platform(x1, y1));
                    }

                    case "ITEM1", "ITEM2", "ITEM3", "ITEM4" -> {
                        int x1 = Integer.parseInt(t[1]);
                        int y1 = Integer.parseInt(t[2]);
                        coins.add(new Collectable(x1, y1));
                    }

                    default -> System.err.println("Unknown token: " + t[0]);
                }
            }
        }
        WIDTH = W;
        HEIGHT = H;
    }

    // timer start and stops
    public void start() {
        timer.start();
    }     // NEW

    public void stop() {
        timer.stop();
    }      // NEW

}