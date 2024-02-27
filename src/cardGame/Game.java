package cardGame;


public class Game {
	private GamePanel gamePanel;
	
	public Game() {
		gamePanel = new GamePanel(); 
		new GameWindow(gamePanel);
		
		gamePanel.startGame();
	}
	
}
