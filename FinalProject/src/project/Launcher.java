package project;

import javax.swing.SwingUtilities;

public class Launcher {
// run main launcher and show app
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new App().show());
	}
}

  
