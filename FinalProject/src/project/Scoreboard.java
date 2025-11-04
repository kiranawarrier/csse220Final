package project;

import java.awt.*;

/**
 * Represents the scoreboard that displays the player's score and lives left.
 */

public class Scoreboard {

    private int score = 0;
    private int livesLeft = 3;

    /**
     * Constructor for the scoreboard.
     * @param livesLeft
     */

    public Scoreboard(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    /**
     * Default constructor.
     */

    public Scoreboard() {
    }

    /**
     * Increments the score by 1.
     */

    public void updateScore() {
        score++;
    }

    /**
     * Decrements the lives left by 1.
     */

    public void dead() {
        livesLeft--;
    }

    /**
     * Gets the lives left.
     * @return the integer value of lives left
     */

    public int getLives() {
        return livesLeft;
    }

    /**
     * Displays the score and lives left.
     * @param g2
     */

    public void displayScore(Graphics2D g2) {
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + score, 10, 20);
        g2.drawString("Lives Left: " + livesLeft, 10, 40);
    }

}
