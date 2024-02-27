package cardGame;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameWindow extends JFrame {
	private ImageIcon imgIcon = new ImageIcon("/icons/icon32x32.png");
	

	public GameWindow(GamePanel gamePanel) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Exceed Fighting System");
		this.setIconImage(imgIcon.getImage());
		this.add(gamePanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
}
