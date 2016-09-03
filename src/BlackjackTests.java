import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class BlackjackTests {

	//Set Up
	public ArrayList<Player> playersSetUp() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player a = new Player(100);
		a.addHand(new CardHand(new Card("Spade", "Ace", 1), new Card("Spade", "King", 10)));
		players.add(a);
		Player b = new Player(100);
		b.addHand(new CardHand(new Card("Spade", "Five", 5), new Card("Spade", "Four", 4)));
		players.add(b);
		Player c = new Player(100);
		c.addHand(new CardHand(new Card("Spade", "Ace", 1), new Card("Spade", "Nine", 9)));
		players.add(c);
		return(players);
	}
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
	
	@Test
	public void testContains_CardParameter() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains(new Card("Spade", "Ten", 10)));
		assertTrue(hand.contains(new Card("Heart", "Two", 2)));
		assertFalse(hand.contains(new Card("Heart", "Ten", 10)));
	}
	
	@Test
	public void testContains_StringParameter() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains("Ten"));
		assertTrue(hand.contains("Two"));
		assertFalse(hand.contains("Five"));
	}
	
	@Test
	public void testContains_IntParameter() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains(10));
		assertTrue(hand.contains(2));
		assertFalse(hand.contains(5));
	}
	
	@Test
	public void testContains_CardsParameter1() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2)));
		assertFalse(hand.contains(new Card("Spade", "Ten", 10), new Card("Heart", "Three", 3)));
		assertFalse(hand.contains(new Card("Spade", "Ten", 10), new Card("Heart", "Three", 3), new Card("Heart", "Two", 2)));
	}
	
	@Test
	public void testContains_CardsParameter2() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10));
		assertTrue(hand.contains(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10)));
		assertFalse(hand.contains(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10)));
	}
	
	@Test
	public void testContains_StringsParameter1() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains("Ten", "Two"));
		assertFalse(hand.contains("Ten", "Three"));
		assertFalse(hand.contains("Ten", "Three", "Two"));
	}
	
	@Test
	public void testContains_StringsParameter2() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10));
		assertTrue(hand.contains("Ten", "Ten"));
		assertFalse(hand.contains("Ten", "Ten", "Ten"));
	}
	
	@Test
	public void testContains_IntsParameter1() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.contains(10, 2));
		assertFalse(hand.contains(10, 3));
		assertFalse(hand.contains(10, 3, 2));
	}
	
	@Test
	public void testContains_IntsParameter2() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Spade", "Ten", 10));
		assertTrue(hand.contains(10, 10));
		assertFalse(hand.contains(10, 10, 10));
	}
	
	@Test
	public void testEquals() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertTrue(hand.equals(new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2))));
		assertFalse(hand.equals(new CardHand(new Card("Spade", "Ten", 10), new Card("Club", "Two", 2))));
	}
	
	@Test
	public void testHandTotal1() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Two", 2));
		assertEquals(12, hand.handTotal());
		hand.addCard(new Card("Spade", "Five", 5));
		assertEquals(17, hand.handTotal());
		hand.addCard(new Card("Heart", "Ace", 1));
		assertEquals(18, hand.handTotal());
		hand.addCard(new Card("Diamond", "King", 10));
		assertEquals(28, hand.handTotal());
	}
	
	@Test
	public void testHandTotal2() {
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Ace", 1));
		assertEquals(21, hand.handTotal());
		hand.addCard(new Card("Diamond", "Queen", 10));
		assertEquals(21, hand.handTotal());
	}
	
	@Test
	public void testHandTotal3() {
		CardHand hand = new CardHand(new Card("Spade", "Ace", 1), new Card("Heart", "Ace", 1));
		assertEquals(12, hand.handTotal());
		hand.addCard(new Card("Diamond", "Ace", 1));
		assertEquals(13, hand.handTotal());
		hand.addCard(new Card("Diamond", "Five", 5));
		assertEquals(18, hand.handTotal());
		hand.addCard(new Card("Diamond", "Queen", 10));
		assertEquals(18, hand.handTotal());
	}
	
	@Test
	public void testRemoveCard() {
		CardHand hand = new CardHand(new Card("Spade", "Ace", 1), new Card("Heart", "Ace", 1));
		hand.remove(0);
		assertEquals(hand.numCards(), 1);
		assertTrue(hand.getCards().get(0).equals(new Card("Heart", "Ace", 1)));
		hand.addCard(new Card("Diamond", "Queen", 10));
		assertEquals(hand.numCards(), 2);
		assertTrue(hand.getCards().get(1).equals(new Card("Diamond", "Queen", 10)));
		hand.addCard(new Card("Diamond", "Jack", 10));
		assertEquals(hand.numCards(), 3);
		assertTrue(hand.getCards().get(2).equals(new Card("Diamond", "Jack", 10)));
		hand.remove(0);
		assertEquals(hand.numCards(), 2);
		assertTrue(hand.getCards().get(0).equals(new Card("Diamond", "Queen", 10)));
		hand.remove(1);
		assertEquals(hand.numCards(), 1);
		assertTrue(hand.getCards().get(0).equals(new Card("Diamond", "Queen", 10)));
	}

	//House Tests
	@Test
	public void testCollectBets1() {
		House dealer = new House();
		dealer.collectBet(50);
		assertEquals(50, dealer.getWinnings());
		dealer.collectBet(100);
		assertEquals(150, dealer.getWinnings());
	}
	
	@Test
	public void testDispenseWinnings1() {
		House dealer = new House();
		dealer.dispenseWinnings(50);
		assertEquals(-50, dealer.getWinnings());
		dealer.collectBet(100);
		assertEquals(50, dealer.getWinnings());
		dealer.dispenseWinnings(45);
		assertEquals(5, dealer.getWinnings());
	}
	
	@Test
	public void testFlipHidden1() {
		House dealer = new House();
		dealer.setHidden(new Card("Spade", "Five", 5));
		assertEquals(0, dealer.getUpCards().getCards().size());
		dealer.flipHidden();
		assertEquals(1, dealer.getUpCards().getCards().size());
		assertTrue(new CardHand(new Card("Spade", "Five", 5)).equals(dealer.getUpCards()));
	}
	
	@Test
	public void testFlipHidden2() {
		House dealer = new House();
		dealer.setHidden(new Card("Spade", "Five", 5));
		dealer.setUpCards(new CardHand(new Card("Spade", "Six", 6)));
		dealer.flipHidden();
		assertEquals(2, dealer.getUpCards().getCards().size());
		assertTrue(dealer.getUpCards().equals(new CardHand(new Card("Spade", "Six", 6), new Card("Spade", "Five", 5))));
	}
	
	@Test
	public void testDealerHandTotal1() {
		//no ace
		House dealer = new House();
		dealer.setHidden(new Card("Spade", "Five", 5));
		dealer.setUpCards(new CardHand(new Card("Spade", "Six", 6)));
		assertEquals(dealer.handTotal(), 11);
		dealer.getUpCards().addCard(new Card("Spade", "Two", 2));
		assertEquals(dealer.handTotal(), 13);
		dealer.flipHidden();
		assertEquals(dealer.handTotal(), 13);
	}
	
	@Test
	public void testOfferInsurance() {
		House dealer = new House();
		dealer.setHidden(new Card("Spade", "Five", 5));
		dealer.setUpCards(new CardHand(new Card("Spade", "Ace", 1)));
		ArrayList<Player> players = playersSetUp();
		for(int i = 0; i < players.size(); i++) {
			players.get(i).bet(50, players.get(i).getHand(0));
		}
		dealer.offerInsurance(players);
		assertEquals(25, players.get(0).getHand(0).getInsurance());
		assertEquals(0, players.get(1).getHand(0).getInsurance());
		assertEquals(0, players.get(2).getHand(0).getInsurance());
		assertEquals(25, players.get(0).getMoney());
		assertEquals(50, players.get(1).getMoney());
		assertEquals(50, players.get(2).getMoney());
	}
	
	//Player Tests
	@Test
	public void testAddHand1() {
		Player p = new Player(100);
		assertEquals(0, p.getHands().size());
		p.addHand(new CardHand());
		assertEquals(1, p.getHands().size());
		p.addHand(new CardHand());
		assertEquals(2, p.getHands().size());
	}
	
	public void testBet1() {
		Player p = new Player(100);
		assertTrue(p.bet(25));
		assertEquals(75, p.getMaxMoney());
		assertFalse(p.bet(100));
		assertEquals(75, p.getMaxMoney());
		assertTrue(p.bet(50));
		assertEquals(25, p.getMaxMoney());
		assertTrue(p.bet(25));
		assertEquals(0, p.getMaxMoney());
		assertFalse(p.bet(25));
		assertEquals(0, p.getMaxMoney());
	}
	
	@Test
	public void testCollectWinnings1() {
		Player p = new Player(100);
		p.collectWinnings(100);
		assertEquals(200, p.getMoney());
		p.collectWinnings(0);
		assertEquals(200, p.getMoney());
	}
	
	@Test
	public void testDoubleDown1() {
		Player p = new Player(100);
		p.bet(10);
		CardHand hand = new CardHand(new Card("Spade", "Nine", 9), new Card("Spade", "Two", 2), 10);
		assertTrue(p.doubleDown(hand, new Card("Heart", "King", 10)));
		assertEquals(80, p.getMoney());
		assertEquals(20, hand.getBet());
		assertEquals(3, hand.getCards().size());
		CardHand result = new CardHand(new Card("Spade", "Nine", 9), new Card("Spade", "Two", 2), new Card("Heart", "King", 10) );
		assertTrue(hand.equals(result));
	}
	
	@Test
	public void testDoubleDown2() {
		Player p = new Player(100);
		p.bet(60);
		CardHand hand = new CardHand(new Card("Spade", "Nine", 9), new Card("Spade", "Two", 2), 60);
		assertFalse(p.doubleDown(hand, new Card("Heart", "King", 10)));
		assertEquals(40, p.getMoney());
		assertEquals(60, hand.getBet());
		assertEquals(2, hand.getCards().size());
		CardHand result = new CardHand(new Card("Spade", "Nine", 9), new Card("Spade", "Two", 2));
		assertTrue(hand.equals(result));
	}
	
	@Test
	public void testHit1() {
		Player p = new Player(100);
		CardHand hand = new CardHand(new Card("Spade", "Nine", 9), new Card("Spade", "Two", 2));
		p.addHand(hand);
		p.hit(p.getHand(0), new Card("Heart", "King", 10));
		assertEquals(3, p.getHand(0).getCards().size());
		CardHand result = new CardHand(new Card("Spade", "Nine", 9), new Card("Spade", "Two", 2), new Card("Heart", "King", 10) );
		assertTrue(p.getHand(0).equals(result));
		CardHand hand2 = new CardHand(new Card("Spade", "Nine", 9), new Card("Spade", "Two", 2));
		p.addHand(hand2);
		assertEquals(2, p.getHand(1).getCards().size());
		p.hit(p.getHand(1), new Card("Heart", "Queen", 10));
		assertEquals(3, p.getHand(1).getCards().size());
		CardHand result2 = new CardHand(new Card("Spade", "Nine", 9), new Card("Spade", "Two", 2), new Card("Heart", "Queen", 10) );
		assertTrue(p.getHand(1).equals(result2));
	}
	
	public void testSplit1() {
		Player p = new Player(100);
		p.bet(30);
		assertEquals(70, p.getMoney());
		CardHand hand = new CardHand(new Card("Spade", "Ace", 1), new Card("Heart", "Ace", 1), 30);
		p.addHand(hand);
		p.split(p.getHand(0), new Card("Heart", "Nine", 9), new Card("Club", "Eight", 8));
		assertEquals(40, p.getMoney());
		assertEquals(p.getHand(0), new CardHand(new Card("Spade", "Ace", 1), new Card("Heart", "Nine", 9)));
		assertEquals(p.getHand(1), new CardHand(new Card("Heart", "Ace", 1), new Card("Club", "Eight", 8)));
	}
	
	public void testSplit2() {
		Player p = new Player(100);
		p.bet(60);
		assertEquals(70, p.getMoney());
		CardHand hand = new CardHand(new Card("Spade", "Ace", 1), new Card("Heart", "Ace", 1), 30);
		p.addHand(hand);
		p.split(p.getHand(0), new Card("Heart", "Nine", 9), new Card("Club", "Eight", 8));
		assertEquals(p.getHand(0), new CardHand(new Card("Spade", "Ace", 1), new Card("Heart", "Ace", 1)));
		assertEquals(40, p.getMoney());
	}
	
	//Shoe Tests
	@Test
	public void testSeUpShoe() {
		Shoe s = new Shoe(4);
		assertEquals(208, s.getShoe().size());
		ArrayList<Card> deck1 = new ArrayList<Card>();
		try{
			deck1 = s.readInDeck();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		for(int i = 0; i < 4; i++) {
			for(int k = 0; k < deck1.size(); k++) {
				s.getShoe().remove(deck1.get(k));
			}
		}
		assertEquals(0, s.getShoe().size());
	}
	
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
