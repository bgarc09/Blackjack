import java.util.ArrayList;
import java.util.LinkedList;


public class Player {

	//private CardHand hand;
	private ArrayList<CardHand> hands; //in order to handle splits
	private int money;
	private int currentBet;
	private int previousBet;
	private int lastHandWinnings;
	private int letItRide;
	private int consecutiveWins;
	
	public Player(int money, int letItRide) {
		this.money = money;
		this.letItRide = letItRide;
		hands = new ArrayList<CardHand>();
		//hand = new CardHand();
	}
	
	public Player(int money) {
		this(money, 3);
	}
	
	public void addHand(CardHand newHand) {
		hands.add(newHand);
	}
	
	/**
	 * 
	 * @param amount
	 * @return True if the player has enough money to complete the bet
	 */
	public boolean bet(int amount) {
		if(amount < money) {
			money -= amount;
			currentBet = amount;
			return true;
		}
		return false;
	}
	
	public void collectWinnings(int winnings) {
		money += winnings;
		lastHandWinnings = winnings;
	}
	
	public boolean doubleDown(CardHand hand, Card c) {
		if(currentBet < money) {
			money -= currentBet;
			currentBet = currentBet * 2;
			hit(hand, c);
			return true;
		} else {
			hit(hand, c);
			return false;
		}
	}
	
	public int getConsecutiveWins() {
		return consecutiveWins;
	}
	
	public int getCurrentBet() {
		return currentBet;
	}
	
	//public CardHand getHand() {
	//	return hand;
	//}
	
	public CardHand getHand(int index) {
		return hands.get(index);
	}
	
	public ArrayList<CardHand> getHands() {
		return hands;
	}
	
	public int getLastHandWinnings() {
		return lastHandWinnings;
	}
	
	public int getLetItRide() {
		return letItRide;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void hit(CardHand hand, Card card) {
		hand.addCard(card);
	}
	
	public void playerDecision(CardHand dealerUpCard, CardHand hand, LinkedList<Card> shoe) {
		boolean stay = false;
		while(!stay) {
			if(hand.numCards() == 2 && hand.contains(10,10)) {
				stay = true;
			} else if(hand.numCards() == 2 && ((hand.contains(8,8) || hand.contains("Ace","Ace")))) {
				split(hand, shoe.removeFirst(), shoe.removeFirst());
			} else if(hand.handTotal() >= 5 && hand.handTotal() <=8) {
				hit(hand, shoe.removeFirst());
			} else if(hand.handTotal() >= 17) {
				stay = true;
			} else if(dealerUpCard.getCards().get(0).getValue() == 2) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					stay = true;
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());				
				} else if(hand.handTotal() == 9) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 13) {
					stay = true;
				} else if(hand.handTotal() == 14) {
					stay = true;
				} else if(hand.handTotal() == 15) {
					stay = true;
				} else if(hand.handTotal() == 16) {
					stay = true;
				} 
			} else if(dealerUpCard.getCards().get(0).getValue() == 3) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());			
				} else if(hand.handTotal() == 9) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 13) {
					stay = true;
				} else if(hand.handTotal() == 14) {
					stay = true;
				} else if(hand.handTotal() == 15) {
					stay = true;
				} else if(hand.handTotal() == 16) {
					stay = true;
				}
			} else if(dealerUpCard.getCards().get(0).getValue() == 4) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());				
				} else if(hand.handTotal() == 9) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					stay = true;
				} else if(hand.handTotal() == 13) {
					stay = true;
				} else if(hand.handTotal() == 14) {
					stay = true;
				} else if(hand.handTotal() == 15) {
					stay = true;
				} else if(hand.handTotal() == 16) {
					stay = true;
				}				
			} else if(dealerUpCard.getCards().get(0).getValue() == 5) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					stay = true;		
				} else if(hand.handTotal() == 9) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					stay = true;
				} else if(hand.handTotal() == 13) {
					stay = true;
				} else if(hand.handTotal() == 14) {
					stay = true;
				} else if(hand.handTotal() == 15) {
					stay = true;
				} else if(hand.handTotal() == 16) {
					stay = true;
				}
			} else if(dealerUpCard.getCards().get(0).getValue() == 6) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());			
				} else if(hand.handTotal() == 9) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					stay = true;
				} else if(hand.handTotal() == 13) {
					stay = true;
				} else if(hand.handTotal() == 14) {
					stay = true;
				} else if(hand.handTotal() == 15) {
					stay = true;
				} else if(hand.handTotal() == 16) {
					stay = true;
				}
			} else if(dealerUpCard.getCards().get(0).getValue() == 7) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					stay = true;
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					stay = true;
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());		
				} else if(hand.handTotal() == 9) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 13) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 14) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 15) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 16) {
					hit(hand, shoe.removeFirst());
				}
			} else if(dealerUpCard.getCards().get(0).getValue() == 8) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					stay = true;
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 9) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 13) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 14) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 15) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 16) {
					hit(hand, shoe.removeFirst());
				}
			} else if(dealerUpCard.getCards().get(0).getValue() == 9) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 9) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 13) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 14) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 15) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 16) {
					hit(hand, shoe.removeFirst());
				}
			} else if(dealerUpCard.getCards().get(0).getValue() == 10) {
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					stay = true;
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 9) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 13) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 14) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 15) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 16) {
					hit(hand, shoe.removeFirst());
				}
			} else if(dealerUpCard.getCards().get(0).getName().equals("Ace")) {
				
				if(hand.numCards() == 2 && hand.contains(2,2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(3,3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(4,4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(5,5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					stay = true;
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					hit(hand, shoe.removeFirst());
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 9) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 10) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 11) {
					stay = doubleDown(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 12) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 13) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 14) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 15) {
					hit(hand, shoe.removeFirst());
				} else if(hand.handTotal() == 16) {
					hit(hand, shoe.removeFirst());
				}
			}
		}
	}
	
//	public void playHand(CardHand hand, int bet) {
//		money -= bet;
//		this.hand = hand;
//	}
	
//	public void setCards(CardHand hand) {
//		this.hand = hand;
//	}
	
	public void setHands(ArrayList<CardHand> hands) {
		this.hands = hands;
	}
	
	public void setConsecutiveWins(int consecutiveWins) {
		this.consecutiveWins = consecutiveWins;
	}
	
	public void setCurrentBet(int currentBet) {
		this.currentBet = currentBet;
	}
	
	public void setLastHandWinnings(int lastHandWinnings) {
		this.lastHandWinnings = lastHandWinnings;
	}
	
	public void setMoeny(int money) {
		this.money = money;
	}
	
	public void split(CardHand hand, Card a, Card b) {
		hands.add(new CardHand(hand.remove(1), b));
		hand.addCard(a);
	}
	
	public int takeInsurance(CardHand hand) {
		if(hand.numCards() == 2 && hand.handTotal() == 21) {
			money -= currentBet / 2;
			return(currentBet / 2);
		}
		return(0);
	}
}
