package cardGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 6642625196859423117L;
	
	private BufferedImage bgImage;
	private Thread gameThread;
	
	private final int FPS = 120;
	private double delta;
	
	boolean startSetup;
	
	// Player
	private List<Card> playerHand = new ArrayList<>();
	private int playerPosition;
	
	// Opponent
	private List<Card> opponentHand = new ArrayList<>();
	private int oppPosition; 
	
	// Deck of Cards
	private static Deck playerDeck;
	private static Deck opponentDeck;
	
	// Window Dimensions
	private int windowWidth = 1280;
	private int windowHeight = 720;
	
	Dimension size = new Dimension(windowWidth,windowHeight);
	
	// Card Dimensions
	private int cardWidth = 165;
	private int cardHeight = 231;

	private String bgPath = "/background2.png";
	
	
	/*
	 * 
	 */
	
	
	
	public GamePanel() {
		setPanelSize();
		bgImage = importImg(bgPath);
	}
	
	/*
	 * 
	 */

	private BufferedImage importImg(String path) {
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream(path);
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

	private void setPanelSize() {
		this.setPreferredSize(size);

	}
	
	public void update() {
		
	}
	
	public void startGame() {
		startSetup = true;
		playerDeck = new Deck();
		opponentDeck = new Deck();
		
		playerDeck.shuffle();
		opponentDeck.shuffle();
		
		
		// Deal each player a starting hand.
		// deck.deal(playerHand, oppHand);
		for (int i = 0; i < 5; i++) {
			playerHand.add(playerDeck.draw());
			opponentHand.add(opponentDeck.draw());
		}

	
		System.out.println("Starting Hands:");
		System.out.println("Player:");
		System.out.println(playerHand);
		System.out.println("Opponent:");
		System.out.println(opponentHand);
		
		// Set players' starting position
		playerPosition = 2;
		oppPosition = 6;
		
		//Print players' positions
		System.out.println();
		System.out.println("———————————————————————————");
		for (int i = 0; i < 9; i++) {
			System.out.print("[");
			if (i == playerPosition) {
				System.out.print("P");
			} else if (i == oppPosition) {
				System.out.print("O");
			} else {
				System.out.print(" ");
			}
			System.out.print("]");
		}
		System.out.println();
		System.out.println("———————————————————————————");
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		double timePerFrame = 1000000000.0 / FPS;
		long now = System.nanoTime();
		long lastCheck = System.currentTimeMillis();
		int frames = 0;
		long previousTime = System.nanoTime();
	
		
		while(gameThread != null) {
			now = System.nanoTime();
			
			delta += (now - previousTime) / timePerFrame;
			previousTime = now;
			


			if (delta >= 1) {
				update();
				repaint();
				frames++;
				delta--;
			}

			
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}
	
	public static Deck getPlayerDeck() {
		return playerDeck;
	}
	
	public static Deck getOpponentDeck() {
		return opponentDeck;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		
		g2.drawImage(bgImage,0,0,windowWidth,windowHeight,null);
		g2.translate((windowWidth-cardWidth)/2, windowHeight-cardHeight);
		g2.rotate(Math.toRadians(-2*(1+playerHand.size())),cardWidth/2,3*cardHeight);

		for (int i = 0; i < playerHand.size(); i++) {
			Image cardImage = playerHand.get(i).getCardImage();
			g2.rotate(Math.toRadians(4),cardWidth/2,3*cardHeight);
			g2.drawImage(cardImage,cardWidth/8*((1-playerHand.size())/2 + i),cardHeight/4,cardWidth, cardHeight, null);
		}


		g2.dispose();
		
	}
	
}
