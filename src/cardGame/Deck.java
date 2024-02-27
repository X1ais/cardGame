package cardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck implements Runnable {
	private static final int STARTING_HAND_SIZE = 7;
	private Random random = new Random();
	private List<Card> cards = new ArrayList<>();

	public Deck() {
		
		/*
		 * Initiating a basic hand of cards. 
		 * TODO: create a data structure to hold all cards in the game and create a deck of cards for each player based on their chosen character.
		 */
		for (int i = 0; i<2;i++) {
			cards.add(new Card(0,"grasp",0,1,1,3,7,0,0,false));
			cards.add(new Card(0,"slash",0,1,1,4,5,0,0,false));
			cards.add(new Card(0,"dust",0,2,3,5,3,0,4,false));
			cards.add(new Card(0,"focus",0,1,2,4,1,2,5,false));
			cards.add(new Card(0,"cross",0,1,2,3,6,0,0,false));
			cards.add(new Card(0,"dive",0,1,1,5,4,0,0,false));
			cards.add(new Card(0,"sweep",0,1,3,6,2,0,6,false));
			cards.add(new Card(0,"block",0,0,0,0,0,2,3,false));
		}
	}
	
	public void describe() {		
		System.out.println(cards);
	}
	
	public void shuffle() {
		List<Card> tempDeck = new ArrayList<>();
		int randIndex;
		
		while (cards.size() > 0) {
			randIndex = random.nextInt(cards.size());
			tempDeck.add(cards.remove(randIndex));
		}
		cards = tempDeck;
	}
	
	public Card draw() {
		return cards.remove(cards.size()-1);
	}
	
	public void deal(List<Card> player, List<Card> opp) {
		for (int i = 0; i < STARTING_HAND_SIZE; i++) {
			player.add(draw());
			opp.add(draw());
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
