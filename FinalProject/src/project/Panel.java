package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The Panel class acts as a container for the game canvas and manages
 * keyboard input to control player movement.
 */
@SuppressWarnings("serial")

public class Panel extends JPanel {

    private final Component canvas = new Component(this);
    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean downPressed = false;
    boolean spacePressed = false;
    boolean h_pressed = false;
    boolean restart = false;
    boolean nextlvl = false;

    /**
     * Constructs the Panel and sets up input listeners for player control.
     */
    public Panel() {
        setLayout(new java.awt.BorderLayout());
        add(canvas, java.awt.BorderLayout.CENTER);
        this.add(canvas);
        this.buildKeys();
        this.add(buildControls(), BorderLayout.SOUTH);

    }

    private JComponent buildControls() {
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 6));
        JButton left = new JButton("restart");
        JButton right = new JButton("level 2");

        left.addActionListener(e -> restart = true);
        right.addActionListener(e -> nextlvl = true);

        controls.add(left);
        controls.add(right);
        return controls;
    }

    private void buildKeys() {
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
                    case KeyEvent.VK_R -> restart = true;
                    case KeyEvent.VK_L -> nextlvl = true;
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
                    case KeyEvent.VK_R -> restart = false;
                    case KeyEvent.VK_L -> nextlvl = false;
                }
            }
        });

    }

    @Override
    public void addNotify() {
        super.addNotify();
        canvas.requestFocusInWindow();
    }
}
	

