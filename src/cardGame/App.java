package cardGame;

import javax.swing.JFrame;

public class App {

	public static void main(String[] args) {

//		Game exceed = new Game();
//		
//		exceed.openFrame();
//		exceed.startGame();
		GameFrame window = new GameFrame();
		
		GamePanel gamePanel = new GamePanel(200,200,0);
		window.add(gamePanel);
		window.pack();
		
		gamePanel.startGameThread();

	}

}
