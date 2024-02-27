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
	
	// Player
	private List<Card> playerHand = new ArrayList<>();
	private List<Image> playerHandImgs = new ArrayList<>();
	private int playerPosition;
	
	// Opponent
	private List<Card> oppHand = new ArrayList<>();
	private List<Image> oppHandImgs = new ArrayList<>();
	private int oppPosition; 
	
	// Window Dimensions
	private int windowWidth = 1920;
	private int windowHeight = 1080;
	
	Dimension size = new Dimension(windowWidth,windowHeight);
	
	// Card Dimensions
	private int cardWidth = 140;
	private int cardHeight = 196;

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
		Deck deck = new Deck();
		
		deck.shuffle();
		
		System.out.println("Starting Deck: ");
		deck.describe();
		
		// Deal each player a starting hand.
		deck.deal(playerHand, oppHand);
	
		System.out.println("Starting Hands:");
		System.out.println("Player:");
		System.out.println(playerHand);
		System.out.println("Opponent:");
		System.out.println(oppHand);
		
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(bgImage,0,0,null);
		
		for (int i = 0; i < playerHand.size(); i++) {
			g.drawImage(playerHand.get(i).getCardImage(),100 + 50*i,100,cardWidth, cardHeight, null);
		}

//		try {
//			
//			for (int i = 0; i < playerHand.size(); i++) {
//				Image cardImg = ImageIO.read(getClass().getResource("/blankCard.png"));
//				g.drawImage(cardImg,(windowWidth-(i+1)*cardWidth)/2,windowHeight-cardHeight/2,cardWidth, cardHeight,null);
//			}
//			
//			
//		} catch(IOException e) {
//			System.out.println(e.getMessage());
//		}
		g.dispose();
		
	}
	
}
