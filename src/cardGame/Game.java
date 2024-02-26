package cardGame;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		
		GamePanel exceed = new GamePanel();
		Thread thread;
		exceed.newFrame();
		exceed.startGame();

	}
	


}
