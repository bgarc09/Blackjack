import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class BlackjackTests {
	private House house;
	private ArrayList<Player> players;
	private Blackjack blackjack;

	private void setUp(int numPlayers, int numDecks) {
		house = new House();
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < numPlayers; i++) {
			players.add(new Player(500));
		}
		blackjack = new Blackjack(house, players, numDecks);
	}
	
	@Test
	public void testSetUpShoe1Deck() {
		setUp(3, 1);
		int[] spades = new int[13];
		int[] hearts = new int[13];
		int[] diamonds = new int[13];
		int[] clubs = new int[13];

		for(int i = 0; i < blackjack.getShoe().size(); i++) {
			if(blackjack.getShoe().get(i).getType().equals("Spade")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					spades[10]++; //= blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					spades[11]++; //= blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					spades[12]++; //= blackjack.getShoe().get(i);
				} else {
					spades[blackjack.getShoe().get(i).getValue() - 1]++; //= blackjack.getShoe().get(i);
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Heart")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					hearts[10]++; // = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					hearts[11]++; // = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					hearts[12]++; // = blackjack.getShoe().get(i);
				} else {
					hearts[blackjack.getShoe().get(i).getValue() - 1]++; // = blackjack.getShoe().get(i);
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Diamond")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					diamonds[10]++; // = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					diamonds[11]++; // = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					diamonds[12]++; // = blackjack.getShoe().get(i);
				} else {
					diamonds[blackjack.getShoe().get(i).getValue() - 1]++; // = blackjack.getShoe().get(i);
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Club")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					clubs[10]++; // = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					clubs[11]++; // = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					clubs[12]++; // = blackjack.getShoe().get(i);
				} else {
					clubs[blackjack.getShoe().get(i).getValue() - 1]++; // = blackjack.getShoe().get(i);
				}
			}
		}
		for(int i = 0; i < clubs.length; i++) {
			assertEquals(spades[i], 1);
			assertEquals(hearts[i], 1);
			assertEquals(diamonds[i], 1);
			assertEquals(clubs[i], 1);
		}
	}
	
	@Test
	public void testSetUpShoe4Decks() {
		setUp(3, 4);
		int[] spades = new int[13];
		int[] hearts = new int[13];
		int[] diamonds = new int[13];
		int[] clubs = new int[13];

		for(int i = 0; i < blackjack.getShoe().size(); i++) {
			if(blackjack.getShoe().get(i).getType().equals("Spade")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					spades[10]++; 
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					spades[11]++; 
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					spades[12]++; 
				} else {
					spades[blackjack.getShoe().get(i).getValue() - 1]++; 
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Heart")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					hearts[10]++; 
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					hearts[11]++; 
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					hearts[12]++; 
				} else {
					hearts[blackjack.getShoe().get(i).getValue() - 1]++; 
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Diamond")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					diamonds[10]++; 
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					diamonds[11]++; 
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					diamonds[12]++; 
				} else {
					diamonds[blackjack.getShoe().get(i).getValue() - 1]++; 
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Club")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					clubs[10]++; 
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					clubs[11]++; 
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					clubs[12]++; 
				} else {
					clubs[blackjack.getShoe().get(i).getValue() - 1]++; 
				}
			}
		}
		for(int i = 0; i < clubs.length; i++) {
			assertEquals(spades[i], 4);
			assertEquals(hearts[i], 4);
			assertEquals(diamonds[i], 4);
			assertEquals(clubs[i], 4);
		}
	}
}
