package project;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The Panel class acts as a container for the game canvas and manages
 * keyboard input to control player movement.
 */
@SuppressWarnings("serial")
public class Panel extends JPanel {
    // The main game rendering and update component.
    private final Component canvas = new Component(this);
    // Tracks whether the left movement key is pressed.
    boolean leftPressed = false;
    // Tracks whether the right movement key is pressed.
    boolean rightPressed = false;
    boolean downPressed = false;
    boolean spacePressed = false;
    boolean h_pressed = false;

    /**
     * Constructs the Panel and sets up input listeners for player control.
     */
    public Panel() {

        setLayout(new java.awt.BorderLayout());
        add(canvas, java.awt.BorderLayout.CENTER);
        this.add(canvas);
        // Key listener for controlling player movement
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> leftPressed = true;
                    case KeyEvent.VK_RIGHT -> rightPressed = true;
                    case KeyEvent.VK_UP -> canvas.playerJump();
                    case KeyEvent.VK_DOWN -> downPressed = true;
                    case KeyEvent.VK_SPACE -> spacePressed = true;
                    case KeyEvent.VK_H -> h_pressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> leftPressed = false;
                    case KeyEvent.VK_RIGHT -> rightPressed = false;
                    case KeyEvent.VK_DOWN -> downPressed = false;
                    case KeyEvent.VK_SPACE -> spacePressed = false;
                    case KeyEvent.VK_H -> h_pressed = false;
                }
            }
        });
    }

    //Ensures the canvas is ready to receive keyboard input once displayed.
    @Override
    public void addNotify() {
        super.addNotify();
        canvas.requestFocusInWindow();
    }
}
	

