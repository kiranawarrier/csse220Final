package project;

import javax.swing.*;

public class App {
    private final JFrame frame = new JFrame("Moving Object — Key Listener");
    private Panel panel = new Panel();
    
    public App() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);   // add our game panel
        frame.pack();                            // size to panel's preferred size
        frame.setLocationRelativeTo(null);       // centered placement
    }

    public void show() {
        frame.setVisible(true);
    }
}