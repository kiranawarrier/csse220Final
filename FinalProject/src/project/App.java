package project;

import java.awt.Frame;

import javax.swing.*;
// Application entry point that creates and displays the game window. 
public class App {
    private final JFrame frame = new JFrame("Final Project App");
    private Panel panel = new Panel();
    // Sets up the main application window and attaches the game panel. 
    public App() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setContentPane(panel);   // add our game panel
        frame.pack();                            // size to panel's preferred size
        //frame.setLocationRelativeTo(null);       // centered placement
    }
    // Makes the application window visible. 
    public void show() {
        frame.setVisible(true);
    }
}