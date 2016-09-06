import java.util.ArrayList;

public class Player {

	private ArrayList<CardHand> hands; //in order to handle splits
	private int money;
	private int currentBet;
	private int lastHandWinnings;
	private int letItRide;
	private int consecutiveWins;
	private int maxMoney;
	
	public Player(int money, int letItRide) {
		this.money = money;
		lastHandWinnings = 0;
		this.letItRide = letItRide;
		consecutiveWins = 0;
		hands = new ArrayList<CardHand>();
	}
	
	public Player(int money) {
		this(money, 3);
	}
	
	public void addHand(CardHand newHand) {
		hands.add(newHand);
	}
	
	//testing purposes
	public boolean bet(int amount) {
		if(amount < money) {
			money -= amount;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param amount
	 * @return True if the player has enough money to complete the bet
	 */
	public boolean bet(int amount, CardHand hand) {
		if(amount < money) {
			money -= amount;
			hand.setBet(amount);
			return true;
		}
		return false;
	}
	
	public void collectWinnings(int winnings) {
		money += winnings;
		lastHandWinnings = winnings;
	}
	
	public boolean decideOnInsurance(CardHand hand) {
		if(hand.handTotal() == 21) {
			return(takeInsurance(hand, hand.getBet() / 2));
		}
		return(false);
	}
	
	public int determineBet(int minBet) {
		if(consecutiveWins == letItRide) {
			consecutiveWins = 0;
			currentBet = minBet;
		} else if(lastHandWinnings != 0){
			currentBet = lastHandWinnings;
		} else {
			currentBet = minBet;
		}
		bet(currentBet);
		return(currentBet);
	}
	
	public boolean doubleDown(CardHand hand, Card c) {
		if(hand.getBet() < money) {
			money -= hand.getBet();
			hand.setBet(hand.getBet() * 2);
			hit(hand, c);
			return(true);
		} 
		return(false);
	}
	
	public int getConsecutiveWins() {
		return consecutiveWins;
	}
	
	public int getCurrentBet() {
		return currentBet;
	}
	
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
	
	public int getMaxMoney() {
		return(maxMoney);
	}
	
	public int getMoney() {
		return money;
	}
	
	public void hit(CardHand hand, Card card) {
		hand.addCard(card);
	}
	
	public void insuranceDecision(CardHand hand) {
		if(hand.handTotal() == 21) {
			this.takeInsurance(hand, hand.getBet() / 2);
		}
	}
	
	public void playerDecision(CardHand dealerUpCard, CardHand hand, Shoe shoe) {
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 10) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 11) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());			
				} else if(hand.handTotal() == 9) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 10) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 11) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());				
				} else if(hand.handTotal() == 9) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 10) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 11) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					stay = true;		
				} else if(hand.handTotal() == 9) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 10) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 11) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(6,6)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(7,7)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(9,9)) {
					split(hand, shoe.removeFirst(), shoe.removeFirst());
				} else if(hand.numCards() == 2 && hand.contains(1, 2)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 3)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 4)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 5)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 6)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && hand.contains(1, 7)) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.numCards() == 2 && (hand.contains(1, 8) || hand.contains(1, 9) || hand.contains(1, 10))) {
					hit(hand, shoe.removeFirst());			
				} else if(hand.handTotal() == 9) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 10) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 11) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 11) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 11) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
				} else if(hand.handTotal() == 11) {
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
					Card card = shoe.removeFirst();
					stay = doubleDown(hand, card);
					if(!stay) {
						hit(hand, card);
					}
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
	
	public CardHand removeHand(int index) {
		return(hands.remove(index));
	}
	
	public void removeHands() {
		hands = new ArrayList<CardHand>();
	}
	
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
	
	public void setMaxMoney(int maxMoney) {
		this.maxMoney = maxMoney;
	}
	
	public void setMoeny(int money) {
		this.money = money;
	}
	
	public boolean split(CardHand hand, Card a, Card b) {
		if(money >= hand.getBet()) {
			hands.add(new CardHand(hand.remove(1), b));
			this.bet(hand.getBet(), hands.get(hands.size() - 1));
			hand.addCard(a);
			return(true);
		}
		return(false);
	}
	
	public boolean takeInsurance(CardHand hand, int amount) {
		if(amount > hand.getBet() / 2) {
			amount = hand.getBet() / 2;
		}
		if(money >= amount) {
			money -= amount;
			hand.setInsurance(amount);
			return(true);
		}
		return(false);
	}
	
	public void updateMaxMoney() {
		if(money > maxMoney) {
			maxMoney = money;
		}
	}
}
