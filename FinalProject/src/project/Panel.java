package project;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel{
	boolean leftPressed = false;
	boolean rightPressed = false;
	private final Component canvas = new Component(this);
	
	public Panel() {
		
		setLayout(new java.awt.BorderLayout());
		add(canvas, java.awt.BorderLayout.CENTER);
		this.add(canvas);
		canvas.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        switch (e.getKeyCode()) {
		            case KeyEvent.VK_LEFT  -> leftPressed = true;
		            case KeyEvent.VK_RIGHT -> rightPressed = true;
		            case KeyEvent.VK_UP    -> canvas.playerJump();
		        }
		    }
		    @Override
		    public void keyReleased(KeyEvent e) {
		        switch (e.getKeyCode()) {
		            case KeyEvent.VK_LEFT  -> leftPressed = false;
		            case KeyEvent.VK_RIGHT -> rightPressed = false;
		        }
		    }
		});
	}
	@Override
	public void addNotify() {
	    super.addNotify();
	    canvas.requestFocusInWindow(); // NOW it works
	}
}
	

