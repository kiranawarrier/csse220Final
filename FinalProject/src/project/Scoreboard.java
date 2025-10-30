package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Scoreboard {

    private int score = 0;
    private int livesLeft = 3;

    public Scoreboard(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    public Scoreboard() {
    }

    public void updateScore() {
        score++;
    }

    public void displayScore(Graphics2D g2) {
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + score, 10, 20);
        g2.drawString("Lives Left: " + livesLeft, 10, 40);
    }


}
