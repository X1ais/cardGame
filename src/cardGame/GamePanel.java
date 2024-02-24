package cardGame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;


public class GamePanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6642625196859423117L;
	private static int FPS = 30;
	private static double drawTime = 1000000000/FPS;
	
	Thread thread;
	
	// Player
	List<Card> playerHand = new ArrayList<>();
	
	int playerPosition;
	// Opponent
	List<Card> oppHand = new ArrayList<>();
	int oppPosition; 
	
	// Window Dimensions
	int windowWidth = 1920;
	int windowHeight = 1080;
	
	// Card Dimensions
	int cardWidth = 140;
	int cardHeight = 196;
	
	JFrame frame = new JFrame("Exceed Fighting System");	
	
	public GamePanel() {
		
	}

	public void newFrame() {
		frame.setVisible(true);
		frame.setSize(windowWidth, windowHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(27,27,27));
		frame.add(this);
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
		long lastTime = System.nanoTime();
		long currentTime;
		double delta = 0;
		
		while(thread != null) {
			
			// Display each frame 1/60 of a second (60FPS)
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawTime;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
//			Image backgroundImg = ImageIO.read(getClass().getResource("/background.png"));
//			g.drawImage(backgroundImg,0,0,windowWidth,windowHeight,null);
			
			for (int i = 0; i < playerHand.size(); i++) {
				Image cardImg = ImageIO.read(getClass().getResource("/blankCard.png"));
				g.drawImage(cardImg,(windowWidth-(i+1)*cardWidth)/2,windowHeight-cardHeight/2,cardWidth, cardHeight,null);
			}
			
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		g.dispose();
		
	}
	
}
