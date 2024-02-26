package cardGame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;


public class GamePanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6642625196859423117L;
	private final int FPS = 60;
	
	private BufferedImage backgroundImg;
	
	
	private Thread thread;
	
	// Player
	List<Card> playerHand = new ArrayList<>();
	
	int playerPosition;
	// Opponent
	List<Card> oppHand = new ArrayList<>();
	int oppPosition; 
	
	// Window Dimensions
	int windowWidth = 1920;
	int windowHeight = 1080;
	
	Dimension size = new Dimension(windowWidth,windowHeight);
	
	// Card Dimensions
	int cardWidth = 140;
	int cardHeight = 196;
	
	JFrame frame = new JFrame("Exceed Fighting System");	
	
	public GamePanel() {
		setPanelSize();
		
		importImg();
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/background2.png");
		
		try {
			backgroundImg = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setPanelSize() {
		this.setPreferredSize(size);

	}

	public void newFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.pack();
		this.setBackground(new Color(27,27,27));
		frame.setVisible(true);

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
		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS;
		long lastFrame = System.nanoTime();
		long lastCheck = System.currentTimeMillis();
		long now;
		double delta = 0.0;
		int frames = 0;
		
		while(thread != null) {
			
			// Display each frame 1/60 of a second (60FPS)
			now = System.nanoTime();
			delta += (now - lastFrame) / timePerFrame;
			//delta += (now - lastFrame) / timePerFrame;
			
			// Something is wrong.
			if (delta >= 1) {
				update();
				repaint();
				lastFrame = now;
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
	
	public void update() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImg,0,0,null);

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
