package project;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * Represents the scoreboard that displays and saves the player's score and lives.
 */
public class Scoreboard {

    private final String file = "score.txt";
    private final Font gameFont;
    private int score; // ERROR 1: This was missing
    private int livesLeft;
    private int highScore;

    /**
     * Default constructor.
     * Loads the last saved state from the file.
     * If no file exists, starts with 0 score and 3 lives.
     */
    public Scoreboard() {
        Font gameFont1;
        loadState();

        try {
            InputStream is = Scoreboard.class.getResourceAsStream("PressStart2P-Regular.ttf");

            if (is == null) {
                throw new IOException("Font not found!!!!");
            }

            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(baseFont);

            gameFont1 = baseFont.deriveFont(Font.BOLD, 24f);
        } catch (IOException | FontFormatException e) {
            System.err.println("Custom font failed to load. Using fallback.");
            gameFont1 = new Font("Monospaced", Font.BOLD, 24);
        }
        this.gameFont = gameFont1;
    }

    /**
     * Writes a single line to the file, OVERWRITING the previous content.
     *
     * @param filename The file to write to.
     * @param line     The line to write.
     */
    public static void writeLine(String filename, String line) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename, false))) {
            out.println(line);
        } catch (IOException e) {
            System.err.println("Write error: " + e.getMessage());
        }
    }

    /**
     * Gets the current score.
     * @return the current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Increments the score by 1 and saves the new state.
     */
    public void updateScore() {
        this.score++; // Use 'this.score'
        if (this.score > this.highScore) {
            this.highScore = this.score;
        }
        saveState();
    }

    /**
     * Decrements the score by 1 and saves the new state
     */
    public void decrementScore() {
        this.score--;
        saveState();
    }

    /**
     * Decrements the lives left by 1 and saves the new state.
     */
    public void dead() {
        this.livesLeft--;
        saveState();
    }
   
    /**
     * Gets the lives left.
     *
     * @return the integer value of lives left
     */
    public int getLives() {
        return this.livesLeft;
    }

    /**
     * Displays the current score and lives left.
     *
     * @param g2
     * @param currentLevel
     */
    public void displayScore(Graphics2D g2, int currentLevel) {
        g2.setFont(this.gameFont);
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + this.score, 20, 45);
        g2.drawString("Lives: " + this.livesLeft, 20, 75);
        g2.drawString("High Score: " + this.highScore, 20, 105);
        g2.drawString("Level: " + currentLevel, 1250,40);
        g2.drawString("Coins Left: " + (7 - this.score), 20,140);
    }

    /**
     * Saves the current score and lives to the file.
     */
    private void saveState() {
        writeLine(this.file, this.score + " " + this.livesLeft + " " + this.highScore);
    }

    /**
     * Loads the score and lives from the file.
     * If the file is missing or corrupt, sets default values.
     */
    private void loadState() {
        File f = new File(this.file);

        if (!f.exists()) {
            this.score = 0;
            this.livesLeft = 3;
            this.highScore = 0;
            saveState();
            return;
        }

        try (Scanner scanner = new Scanner(f)) {
            if (scanner.hasNextInt()) {
                this.score = scanner.nextInt();
            } else {
                this.score = 0;
            }
            if (scanner.hasNextInt()) {
                this.livesLeft = scanner.nextInt();
            } else {
                this.livesLeft = 3;
            }
            if (scanner.hasNextInt()) {
                this.highScore = scanner.nextInt();
            } else {
                this.highScore = 0;
            }
            saveState();

        } catch (IOException e) {
            System.err.println("Read error: " + e.getMessage());
            this.score = 0;
            this.livesLeft = 3;
            this.highScore = 0;
            saveState();
        }
    }

    /**
     * Resets the score to 0 and lives to 3.
     */
    public void resetScore() {
        this.score = 0;
        this.livesLeft = 3;
        saveState();
    }

    /**
     * Gets the high score.
     *
     * @return the high score
     */
    public int getHighScore() {
        return this.highScore;
    }

    /**
     * Resets the high score to 0.
     */
    public void resetHighScore() {
        this.highScore = 0;
        saveState();
    }
}