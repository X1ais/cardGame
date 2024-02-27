package cardGame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = -4082948699749879632L;
	private ImageIcon imgIcon = new ImageIcon("/icons/icon32x32.png");
	

	public GameWindow(GamePanel gamePanel) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Exceed Fighting System");
		this.setIconImage(imgIcon.getImage());
		this.add(gamePanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
	}
}
