package cardGame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;


public class Game {
	
	// Player
	List<Card> playerHand = new ArrayList<>();
	
	int playerPosition;
	// Opponent
	List<Card> oppHand = new ArrayList<>();
	int oppPosition; 
	
	// Window Dimensions
	int windowWidth = 1920;
	int windowHeight = 1080;
	
	JFrame frame = new JFrame("Exceed Fighting System");
	JPanel gamePanel = new JPanel();
	
	
	public Game() {
		
	}

	public void openFrame() {
		frame.setVisible(true);
		frame.setSize(windowWidth, windowHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setBackground(new Color(27,27,27));
		frame.add(gamePanel);
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
		
	}
	
}
