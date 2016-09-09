import java.util.ArrayList;

public class Blackjack {
	
	private House dealer;
	private ArrayList<Player> players;
	private Shoe shoe;
	private int numDecks;
	
	public Blackjack(House dealer, ArrayList<Player> players, int numDecks) {
		this.dealer = dealer;
		this.players = new ArrayList<Player>(players);
		this.numDecks = numDecks;
		this.shoe = new Shoe(numDecks);
	}
	
	/**
	 * 
	 * @param hand
	 * @return True if the card total exceeds 21
	 */
	public boolean bust(CardHand hand) {
		if(hand.handTotal() > 21) {
			return true;
		}
		return false;
	}
	
	public boolean checkBlackjack(CardHand hand) {
		if(hand.handTotal() == 21 && hand.getCards().size() == 2) {
			return true;
		} else {
			return false; 
		}
	}
	
	public void cutPlayers(int minBet) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getMoney() < minBet) {
				players.remove(i);
			}
		}
	}
	
	/**
	 * Deals two cards to each player and the dealer
	 */
	public void deal() {
		for(int i = 0; i < players.size(); i++) {
			players.get(i).addHand(new CardHand(players.get(i).getCurrentBet(), shoe.removeFirst()));
		}
		dealer.setHidden(shoe.removeFirst());
		for(int i = 0; i < players.size(); i++) {
			players.get(i).getHand(0).addCard(shoe.removeFirst());
		}
		dealer.getUpCards().addCard(shoe.removeFirst());
	}
	
	public void determineWinners() {
		for(int i = 0; i < players.size(); i++) {
			for(int k = 0; k < players.get(i).getHands().size(); k++) {
				int roundEarnings = 0;
				if(checkBlackjack(dealer.getUpCards())) {
					if(checkBlackjack(players.get(i).getHand(k))) {
						dealer.dispenseWinnings(players.get(i).getHand(k).getBet());
						dealer.dispenseWinnings(players.get(i).getHand(k).getInsurance() * 2);
						roundEarnings += players.get(i).getHand(k).getBet();
						roundEarnings += players.get(i).getHand(k).getInsurance() * 2;
					} else {
						dealer.dispenseWinnings(players.get(i).getHand(k).getInsurance() * 2);
						roundEarnings += players.get(i).getHand(k).getInsurance() * 2;
					}
				} else {
					if(checkBlackjack(players.get(i).getHand(k))) {
						dealer.dispenseWinnings(players.get(i).getHand(k).getBet() + (int)(players.get(i).getHand(k).getBet() * 1.5));
						roundEarnings += players.get(i).getHand(k).getBet() + (int)(players.get(i).getHand(k).getBet() * 1.5);
					} else if(dealer.getUpCards().handTotal() == players.get(i).getHand(k).handTotal() && !bust(players.get(i).getHand(k))) {
						dealer.dispenseWinnings(players.get(i).getHand(k).getBet());
						roundEarnings += players.get(i).getHand(k).getBet();
					} else if(dealer.getUpCards().handTotal() < players.get(i).getHand(k).handTotal() && !bust(players.get(i).getHand(k))) {
						dealer.dispenseWinnings(players.get(i).getHand(k).getBet() * 2);
						roundEarnings += players.get(i).getHand(k).getBet() * 2;
					}
				}
				if(roundEarnings > 0) {
					players.get(i).setConsecutiveWins(players.get(i).getConsecutiveWins() + 1);
				} else {
					players.get(i).setConsecutiveWins(0);
				}
				players.get(i).setLastHandWinnings(roundEarnings);
				players.get(i).updateMaxMoney();
			}
		}
	}
	
	public House getDealer() {
		return dealer;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void makeBets(int minBet) {
		for(int i = 0; i < players.size(); i++) {
			dealer.collectBet(players.get(i).determineBet(minBet));
		}
	}
	
	public void playGameTilPlayersBroke(int minBet) {
		int counter = 0;
		while(players.size() > 0) {
			playRound(minBet);
			for(int i = 0; i < players.size(); i++) {
				System.out.println("Player " + i + ": " +players.get(i).getMoney());
			}
			counter++;
		}
		System.out.println("Num Rounds: " + counter);
	}
	
	public void playGameNumRounds(int numRounds, int minBet) {
		for(int round = 0; round < numRounds; round++) {
			playRound(minBet);
			System.out.println("Round " + round);
			for(int i = 0; i < players.size(); i++) {
				System.out.println("Player " + i + ": " +players.get(i).getMoney());
			}
		}
		
	}
	
	/**
	 * Commences a round of blackjack with each player only betting the minimum amount
	 * @param minBet
	 */
	public void playRound(int minBet) {
		cutPlayers(minBet);
		makeBets(minBet);
		deal();
		if(dealer.getUpCards().getCards().get(0).getValue() == 1) {
			dealer.offerInsurance(players);
		}
		for(int i = 0; i < players.size(); i++) {
			for(int k = 0; k < players.get(i).getHands().size(); k++) {
				players.get(i).playerDecision(dealer.getUpCards(), players.get(i).getHand(k), shoe);
			}
		}
		dealer.flipHidden();
		dealer.dealerStrategy(shoe);
		determineWinners();
		if(shoe.getCurrentCard() > shoe.getCutCard()) {
			shoe.setUpShoe(numDecks);
			shoe.setCurrentCard(0);
		}
		removeHands();
	}
	
	public void removeHands() {
		for(int i = 0; i < players.size(); i++) {
			players.get(i).removeHands();
		}
		dealer.setUpCards(new CardHand());
	}
	
	public void setDealer(House dealer) {
		this.dealer = dealer;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}
}
