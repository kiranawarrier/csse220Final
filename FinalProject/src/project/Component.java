package project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The class that draws the game
 */
@SuppressWarnings("serial")

public class Component extends JComponent {

    private static final int maxLevel = 2;
    private int WIDTH = 1500, HEIGHT = 1080;
    public static final int GROUND_Y = 702;
    int time = 0;
    int currentLevel = 1;
    int totalCoinsInLevel = 0;
    boolean isLevelComplete = false;

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
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        score.resetScore();

        timer = new Timer(20, e -> tick());

        try {
            String levelPath = "resources/levels/level" + currentLevel + ".txt";
            loadLevel(levelPath);

        } catch (RuntimeException e) {
            System.err.println("Failed to load level:");
            timer.stop();
        }

        timer.start();
    }

    private void tick() {

        if (isLevelComplete) {
            if (panel.nextlvl) {
                if (currentLevel <= maxLevel) {
                    currentLevel++;
                    String nextLevel = "resources/levels/level" + currentLevel + ".txt";
                    loadLevel(nextLevel);
                } else {
                    System.out.println("Congrats!, you have completed the game!!!");
                    System.exit(0);
                }

            } else if (panel.restart) {
                System.out.println("restart");
                loadLevel("resources/levels/level" + currentLevel + ".txt");
            }

        } else if (score.getLives() == 0) {
            if (time < 3000) {
                time += 20;
            } else {
                time = 0;
                loadLevel("resources/levels/level" + currentLevel + ".txt"); // Reload level
            }

        } else if (player != null) {
            if (panel.spacePressed) itemCollisions();

            if (score.getScore() >= totalCoinsInLevel && totalCoinsInLevel > 0) {
                isLevelComplete = true;
            } else {
                if (panel.leftPressed) player.left();
                if (panel.rightPressed) player.right();
                if (panel.downPressed) player.fall();

                for (Enemy en : E) {
                    en.move();
                }

                int w = 1500;
                if (player.x > w) {
                    player.x = -35;
                }
                if (player.x + 35 < 0) {
                    player.x = w;
                }
                player.gravity();
                player.updateY();
                if (player.y + player.getHeight() >= GROUND_Y) {
                    player.y = GROUND_Y - player.getHeight();
                    player.dy = 0;
                }
                platformCollisions();
                enemyCollisions();

                if (panel.h_pressed) {
                    score.resetHighScore();
                }
            }
        }
        repaint();
    }

    /**
     * Draws all game elements to the screen.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        screen.displayScreen(g2);

        if (player != null) {
            player.paintPlayer(g2);
        }
        for (Enemy e : E) {
            e.drawEnemy(g2);
        }
        for (Platform plat : plats) {
            plat.drawPlatform(g2);
        }
        for (Collectable collectable : coins) {
            collectable.drawCollectable(g2);
        }
        score.displayScore(g2, this.currentLevel);

        if (isLevelComplete) { // Add a Level complete screen here
            currentLevel++;
            loadLevel("resources/levels/level" + currentLevel + ".txt");

        } else if (score.getLives() == 0) {
            if ((time > 0 && time < 1000)) {
                screen.displayEndScreen(g2, 3);
            } else if (time > 0 && time < 2000) {
                screen.displayEndScreen(g2, 2);
            } else if (time > 0 && time < 3000) {
                screen.displayEndScreen(g2, 1);
            }
        }
    }

    public void playerJump() {
        if (player != null) player.jump();
        repaint();
    }

    /**
     * Checks if the player model intersects collectable model and adds points to the score if it is.
     */
    private void itemCollisions() {
        if (player == null) return;
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
        if (player == null) return;
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
        if (player == null) return;
        Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        for (Enemy e : E) {
            Rectangle enemyRect = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            if (playerRect.intersects(enemyRect)) {
                totalCoinsInLevel--;
                player.die();
                score.decrementScore();
                score.dead();
                System.out.println("You Died");
            }
        }
    }

    /**
     * Loads a new level from a file.
     *
     * @param levelFilename
     */
    private void loadLevel(String levelFilename) {
        try {
            Level newLevel = LevelIO.loadLevel(levelFilename);

            this.player = newLevel.getPlayer();
            this.E = newLevel.getEnemies();
            this.plats = newLevel.getPlatforms();
            this.coins = newLevel.getCoins();
            this.WIDTH = newLevel.getWidth();
            this.HEIGHT = newLevel.getHeight();
            this.totalCoinsInLevel = this.coins.size();

            this.score.resetScore();

            this.isLevelComplete = false;
            this.time = 0;

            setPreferredSize(new Dimension(WIDTH, HEIGHT));

        } catch (RuntimeException e) {
            System.err.println("Failed to load level " + levelFilename + ": " + e.getMessage());
            if (timer != null) {
                timer.stop();
            }
        }
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

}