import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class BlackjackTests {
	private House house;
	private ArrayList<Player> players;
	private Blackjack blackjack;

	private void setUp() {
		house = new House();
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(500));
		players.add(new Player(500));
		players.add(new Player(500));
		blackjack = new Blackjack(house, players);
	}
	
	@Test
	public void testSetUpShoe1() {
		setUp();
		blackjack.setUpShoe(1);
		Card[] spades = new Card[13];
		Card[] hearts = new Card[13];
		Card[] diamonds = new Card[13];
		Card[] clubs = new Card[13];

		for(int i = 0; i < blackjack.getShoe().size(); i++) {
			if(blackjack.getShoe().get(i).getType().equals("Spade")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					spades[10] = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					spades[11] = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					spades[12] = blackjack.getShoe().get(i);
				} else {
					spades[blackjack.getShoe().get(i).getValue() - 1] = blackjack.getShoe().get(i);
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Heart")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					hearts[10] = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					hearts[11] = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					hearts[12] = blackjack.getShoe().get(i);
				} else {
					hearts[blackjack.getShoe().get(i).getValue() - 1] = blackjack.getShoe().get(i);
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Diamond")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					diamonds[10] = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					diamonds[11] = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					diamonds[12] = blackjack.getShoe().get(i);
				} else {
					diamonds[blackjack.getShoe().get(i).getValue() - 1] = blackjack.getShoe().get(i);
				}
			} else if(blackjack.getShoe().get(i).getType().equals("Club")) {
				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
					clubs[10] = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
					clubs[11] = blackjack.getShoe().get(i);
				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
					clubs[12] = blackjack.getShoe().get(i);
				} else {
					clubs[blackjack.getShoe().get(i).getValue() - 1] = blackjack.getShoe().get(i);
				}
			}
		}
		for(int i = 0; i < clubs.length; i++) {
			assertNotNull(spades[i]);
			assertNotNull(hearts[i]);
			assertNotNull(diamonds[i]);
			assertNotNull(clubs[i]);
		}
	}
}
