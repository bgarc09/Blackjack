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
	public Blackjack blackjackSetUp() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player a = new Player(100);
		players.add(a);
		Player b = new Player(100);
		players.add(b);
		Player c = new Player(100);
		players.add(c);
		Player d = new Player(100);
		players.add(d);
		House dealer = new House();
		Blackjack result = new Blackjack(dealer, players, 4);
		return(result);
	}
	
	@Test
	public void testBust1() {
		Blackjack blackjack = blackjackSetUp();
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Five", 5));
		assertFalse(blackjack.bust(hand));
		hand.addCard(new Card("Club","Seven", 7));
		assertTrue(blackjack.bust(hand));
	}
	
	@Test
	public void testCheckBlackjack1() {
		Blackjack blackjack = blackjackSetUp();
		CardHand hand = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Five", 5));
		assertFalse(blackjack.checkBlackjack(hand));
		hand.addCard(new Card("Club","Six", 6));
		assertFalse(blackjack.checkBlackjack(hand));
		CardHand hand2 = new CardHand(new Card("Spade", "Ten", 10), new Card("Heart", "Ace", 1));
		assertTrue(blackjack.checkBlackjack(hand2));
	}
	
	@Test
	public void testCutPlayers() {
		Blackjack blackjack = blackjackSetUp();
		blackjack.cutPlayers(10);
		assertEquals(4, blackjack.getPlayers().size());
		for(int i = 0; i < blackjack.getPlayers().size(); i++) {
			blackjack.getPlayers().get(i).bet(50);
		}
		blackjack.cutPlayers(10);
		assertEquals(4, blackjack.getPlayers().size());
		blackjack.getPlayers().get(0).bet(45);
		blackjack.cutPlayers(10);
		assertEquals(3, blackjack.getPlayers().size());
	}
	
	@Test
	public void testDeal1() {
		Blackjack blackjack = blackjackSetUp();
		blackjack.deal();
		for(int i = 0; i < blackjack.getPlayers().size(); i++) {
			assertEquals(2, blackjack.getPlayers().get(i).getHand(0).getCards().size());
		}
	}
	
	@Test
	public void testMakeBets() {
		Blackjack blackjack = blackjackSetUp();
		blackjack.makeBets(10);
		assertEquals(40, blackjack.getDealer().getWinnings());
		for(int i = 0; i < blackjack.getPlayers().size(); i++) {
			assertEquals(90, blackjack.getPlayers().get(i).getMoney());
		}
	}
	
	@Test
	public void testPlayRound() {
		Blackjack blackjack = blackjackSetUp();
		blackjack.playRound(10);
	}
	
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
	public void hardHandTotal() {
		CardHand hand = new CardHand(new Card("Spade", "Ace", 1), new Card("Heart", "Ace", 1));
		assertEquals(2, hand.hardHandTotal());
		hand.addCard(new Card("Diamond", "Queen", 10));
		assertEquals(12, hand.hardHandTotal());
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
	public void testDetermineBet() {
		Player p = new Player(100, 3);
		assertEquals(10, p.determineBet(10));
		p.setConsecutiveWins(2);
		p.setLastHandWinnings(20);
		assertEquals(20, p.determineBet(10));
		p.setConsecutiveWins(3);
		p.setLastHandWinnings(40);
		assertEquals(10, p.determineBet(10));
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
}
