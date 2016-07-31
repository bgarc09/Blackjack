import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class BlackjackTests {
	private House house;
	private ArrayList<Player> players;
	private Blackjack blackjack;

	//Set Up
	
	//Blackjack Tests
	
	//Card Tests
	@Test
	public void testEqualCard1() {
		Card c1 = new Card("Spade", "Ten", 10);
		Card c2 = new Card("Spade", "Ten", 10);
		assertTrue(c1.equals(c2));
	}
	
	@Test
	public void testEqualCard2() {
		Card c1 = new Card("Heart", "Ten", 10);
		Card c2 = new Card("Spade", "Ten", 10);
		assertFalse(c1.equals(c2));
	}
	
	@Test
	public void testEqualCard3() {
		Card c1 = new Card("Spade", "Ten", 10);
		Card c2 = new Card("Spade", "Queen", 10);
		assertFalse(c1.equals(c2));
	}
	
	@Test
	public void testEqualCard4() {
		Card c1 = new Card("Spade", "Ten", 10);
		Card c2 = new Card("Spade", "Ten", 9);
		assertFalse(c1.equals(c2));
	}
	
	//CardHand Tests
		
	@Test
	public void testAddCard() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10));
		assertEquals(1, hand.getCards().size());
		hand.addCard(new Card("Spade", "Nine", 9));
		assertEquals(2, hand.getCards().size());
		assertTrue(new Card("Spade", "Ten", 10).equals(hand.getCards().get(0)));
		assertTrue(new Card("Spade", "Nine", 9).equals(hand.getCards().get(1)));
		hand.addCard(new Card("Heart", "Eight", 8));
		assertEquals(3, hand.getCards().size());
		assertTrue(new Card("Spade", "Ten", 10).equals(hand.getCards().get(0)));
		assertTrue(new Card("Spade", "Nine", 9).equals(hand.getCards().get(1)));
		assertTrue(new Card("Heart", "Eight", 8).equals(hand.getCards().get(2)));
	}
	
	public void testContains_CardParameter() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains(new Card("Spade", "Ten", 10)));
		assertTrue(hand.contains(new Card("Heart", "Two", 2)));
		assertFalse(hand.contains(new Card("Heart", "Ten", 10)));
	}
	
	public void testContains_StringParameter() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains("Ten"));
		assertTrue(hand.contains("Two"));
		assertFalse(hand.contains("Five"));
	}
	
	public void testContains_IntParameter() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains(10));
		assertTrue(hand.contains(2));
		assertFalse(hand.contains(5));
	}
	
	public void testContains_CardsParameter1() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2)));
		assertFalse(hand.contains(new Card("Spade", "Ten", 10), new Card("Heart", "Three", 3)));
		assertFalse(hand.contains(new Card("Spade", "Ten", 10), new Card("Heart", "Three", 3), new Card("Heart", "Two", 2)));
	}
	
	public void testContains_CardsParameter2() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10));
		assertTrue(hand.contains(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10)));
		assertFalse(hand.contains(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10)));
	}
	
	public void testContains_StringsParameter1() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains("Ten", "Two"));
		assertFalse(hand.contains("Ten", "Three"));
		assertFalse(hand.contains("Ten", "Three", "Two"));
	}
	
	public void testContains_StringsParameter2() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10));
		assertTrue(hand.contains("Ten", "Ten"));
		assertFalse(hand.contains("Ten", "Ten", "Ten"));
	}
	
	public void testContains_IntsParameter1() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains(10, 2));
		assertFalse(hand.contains(10, 3));
		assertFalse(hand.contains(10, 3, 2));
	}
	
	public void testContains_IntsParameter2() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10));
		assertTrue(hand.contains(10, 10));
		assertFalse(hand.contains(10, 10, 10));
	}

	//House Tests
	
	//Player Tests
	
//	@Test
//	public void bustTest1() {
//		setUp(3, 4);
//		Card a = new Card("Spade", "King", 10);
//		Card b = new Card("Spade", "Queen", 10);
//		CardHand hand = new CardHand(a, b);
//		assertFalse(blackjack.bust(hand));
//		Card c = new Card("Heart", "Jack", 10);
//		hand.getCards().add(c);
//		assertTrue(blackjack.bust(hand));
//	}
//	
//	@Test
//	public void bustTest2() {
//		setUp(3, 4);
//		Card a = new Card("Spade", "Ace", 1);
//		Card b = new Card("Spade", "Five", 5);
//		CardHand hand = new CardHand(a, b);
//		assertFalse(blackjack.bust(hand));
//		Card c = new Card("Heart", "Jack", 10);
//		hand.getCards().add(c);
//		assertFalse(blackjack.bust(hand));
//		Card d = new Card("Diamond", "Eight", 8);
//		hand.getCards().add(d);
//		assertTrue(blackjack.bust(hand));
//	}
//	
//	@Test
//	public void dealTest() {
//		setUp(3, 4);
//		assertEquals(blackjack.getShoe().size(), 208);
//		blackjack.deal();
//		assertEquals(blackjack.getShoe().size(), 200);
//		players.size();
//		for(int i = 0; i < players.size(); i++) {
//			assertFalse(blackjack.getShoe().contains(players.get(i).getHand(0).getCards()));
//		}
//		assertFalse(blackjack.getShoe().contains(house.getHidden()));
//		assertFalse(blackjack.getShoe().contains(house.getUpCards()));
//
//	}
//	
//	/**
//	 * Regular hand
//	 */
//	@Test
//	public void handTotalTest1() {
//		Card a = new Card("Spade", "Ten", 10);
//		Card b = new Card("Spade", "Five", 5);
//		CardHand hand = new CardHand(a, b);
//		assertEquals(hand.handTotal(), 15);
//	}
//	
//	/**
//	 * Face Card
//	 */
//	@Test
//	public void handTotalTest2() {
//		Card a = new Card("Spade", "King", 10);
//		Card b = new Card("Spade", "Queen", 10);
//		CardHand hand = new CardHand(a, b);
//		assertEquals(hand.handTotal(), 20);
//	}
//	
//	/**
//	 * Ace and regular card
//	 */
//	@Test
//	public void handTotalTest3() {
//		Card a = new Card("Spade", "Ten", 10);
//		Card b = new Card("Spade", "Ace", 1);
//		CardHand hand = new CardHand(a, b);
//		assertEquals(hand.handTotal(), 21);
//	}
//	
//	/**
//	 * Two Aces 
//	 */
//	@Test
//	public void handTotalTest4() {
//		Card a = new Card("Spade", "Ace", 1);
//		Card b = new Card("Diamond", "Ace", 1);
//		CardHand hand = new CardHand(a, b);
//		assertEquals(hand.handTotal(), 12);
//	}
//	
//	/**
//	 * Over 21 with soft ace
//	 */
//	@Test
//	public void handTotalTest5() {
//		Card a = new Card("Spade", "Four", 4);
//		Card b = new Card("Diamond", "Ace", 1);
//		CardHand hand = new CardHand(a, b);
//		assertEquals(hand.handTotal(), 15);
//		Card c = new Card("Heart", "Jack", 10);
//		assertEquals(hand.handTotal(), 15);
//	}
//	
//	@Test
//	public void hitTest() {
//		setUp(3, 4);
//	}
//	
//	@Test
//	public void initiateRoundTest() {
//		setUp(3, 4);
//		blackjack.playRound(50);
//		for(int i = 0; i < blackjack.getPlayers().size(); i++) {
//			assertEquals(blackjack.getPlayers().get(i).getMoney(), 450);
//			assertEquals(blackjack.getPlayers().get(i).getHand(0).getCards().size(), 2);
//			assertFalse(blackjack.getShoe().contains(blackjack.getPlayers().get(i).getHand(0).getCards()));
//		}
//		assertEquals(blackjack.getDealer().getWinnings(), 150);
//		assertFalse(blackjack.getShoe().contains(blackjack.getDealer().getHidden()));
//		assertFalse(blackjack.getShoe().contains(blackjack.getDealer().getUpCards()));
//		
//	}
//	
//	/**
//	 * One Deck
//	 */
//	@Test
//	public void setUpShoeTest1() {
//		setUp(3, 1);
//		int[] spades = new int[13];
//		int[] hearts = new int[13];
//		int[] diamonds = new int[13];
//		int[] clubs = new int[13];
//
//		for(int i = 0; i < blackjack.getShoe().size(); i++) {
//			if(blackjack.getShoe().get(i).getType().equals("Spade")) {
//				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
//					spades[10]++; //= blackjack.getShoe().get(i);
//				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
//					spades[11]++; //= blackjack.getShoe().get(i);
//				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
//					spades[12]++; //= blackjack.getShoe().get(i);
//				} else {
//					spades[blackjack.getShoe().get(i).getValue() - 1]++; //= blackjack.getShoe().get(i);
//				}
//			} else if(blackjack.getShoe().get(i).getType().equals("Heart")) {
//				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
//					hearts[10]++; // = blackjack.getShoe().get(i);
//				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
//					hearts[11]++; // = blackjack.getShoe().get(i);
//				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
//					hearts[12]++; // = blackjack.getShoe().get(i);
//				} else {
//					hearts[blackjack.getShoe().get(i).getValue() - 1]++; // = blackjack.getShoe().get(i);
//				}
//			} else if(blackjack.getShoe().get(i).getType().equals("Diamond")) {
//				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
//					diamonds[10]++; // = blackjack.getShoe().get(i);
//				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
//					diamonds[11]++; // = blackjack.getShoe().get(i);
//				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
//					diamonds[12]++; // = blackjack.getShoe().get(i);
//				} else {
//					diamonds[blackjack.getShoe().get(i).getValue() - 1]++; // = blackjack.getShoe().get(i);
//				}
//			} else if(blackjack.getShoe().get(i).getType().equals("Club")) {
//				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
//					clubs[10]++; // = blackjack.getShoe().get(i);
//				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
//					clubs[11]++; // = blackjack.getShoe().get(i);
//				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
//					clubs[12]++; // = blackjack.getShoe().get(i);
//				} else {
//					clubs[blackjack.getShoe().get(i).getValue() - 1]++; // = blackjack.getShoe().get(i);
//				}
//			}
//		}
//		for(int i = 0; i < clubs.length; i++) {
//			assertEquals(spades[i], 1);
//			assertEquals(hearts[i], 1);
//			assertEquals(diamonds[i], 1);
//			assertEquals(clubs[i], 1);
//		}
//	}
//	
//	/**
//	 * Four Decks
//	 */
//	@Test
//	public void setUpShoeTest2() {
//		setUp(3, 4);
//		int[] spades = new int[13];
//		int[] hearts = new int[13];
//		int[] diamonds = new int[13];
//		int[] clubs = new int[13];
//
//		for(int i = 0; i < blackjack.getShoe().size(); i++) {
//			if(blackjack.getShoe().get(i).getType().equals("Spade")) {
//				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
//					spades[10]++; 
//				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
//					spades[11]++; 
//				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
//					spades[12]++; 
//				} else {
//					spades[blackjack.getShoe().get(i).getValue() - 1]++; 
//				}
//			} else if(blackjack.getShoe().get(i).getType().equals("Heart")) {
//				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
//					hearts[10]++; 
//				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
//					hearts[11]++; 
//				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
//					hearts[12]++; 
//				} else {
//					hearts[blackjack.getShoe().get(i).getValue() - 1]++; 
//				}
//			} else if(blackjack.getShoe().get(i).getType().equals("Diamond")) {
//				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
//					diamonds[10]++; 
//				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
//					diamonds[11]++; 
//				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
//					diamonds[12]++; 
//				} else {
//					diamonds[blackjack.getShoe().get(i).getValue() - 1]++; 
//				}
//			} else if(blackjack.getShoe().get(i).getType().equals("Club")) {
//				if(blackjack.getShoe().get(i).getName().equals("Jack")) {
//					clubs[10]++; 
//				} else if(blackjack.getShoe().get(i).getName().equals("Queen")) {
//					clubs[11]++; 
//				} else if(blackjack.getShoe().get(i).getName().equals("King")) {
//					clubs[12]++; 
//				} else {
//					clubs[blackjack.getShoe().get(i).getValue() - 1]++; 
//				}
//			}
//		}
//		for(int i = 0; i < clubs.length; i++) {
//			assertEquals(spades[i], 4);
//			assertEquals(hearts[i], 4);
//			assertEquals(diamonds[i], 4);
//			assertEquals(clubs[i], 4);
//		}
//	}
//	
//	private void setUp(int numPlayers, int numDecks) {
//		house = new House();
//		players = new ArrayList<Player>();
//		for(int i = 0; i < numPlayers; i++) {
//			players.add(new Player(500));
//		}
//		blackjack = new Blackjack(house, players, numDecks);
//	}
	
}
