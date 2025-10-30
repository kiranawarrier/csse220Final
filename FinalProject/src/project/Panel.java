package project;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Panel extends JPanel{
	private final Component canvas = new Component();
	
	public Panel() {
		this.add(canvas);
		
		this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT  -> canvas.playerLeft(); 
                    case KeyEvent.VK_RIGHT -> canvas.playerRight();
                    case KeyEvent.VK_UP -> canvas.playerJump();
                }
                
            }
        });
	}
	
}
