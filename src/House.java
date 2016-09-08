import java.util.ArrayList;

public class House {

	private int winnings;
	private Card hidden;
	private CardHand upCards;
	
	public House() {
		winnings = 0;
		upCards = new CardHand();
	}
	
	public void collectBet(int amount) {
		winnings += amount;
	}
	
	public void dealerStrategy(Shoe s) {
		boolean complete = false;
		while(!complete) {
			if(upCards.handTotal() < 17) {
				upCards.addCard(s.removeFirst());
			} else if(upCards.contains("Ace") && upCards.hardHandTotal() == 7) {
				upCards.addCard(s.removeFirst());
			} else {
				complete = true;
			}
		}
	}
	
	public void dispenseWinnings(int amount) {
		winnings -= amount;
	}
	
	public void flipHidden() {
		upCards.addCard(hidden);
		hidden = null;
	}
	
	public CardHand getUpCards() {
		return upCards;
	}
	
	public Card getHidden() {
		return hidden;
	}
	
	public int getWinnings() {
		return winnings;
	}
	
	public int handTotal() {
		if(hidden == null) {
			return(upCards.handTotal());
		} else {
			CardHand temp = new CardHand(upCards, hidden);
			return(temp.handTotal());
		}
	}
	
	public void offerInsurance(ArrayList<Player> players) {
		for(int i = 0; i < players.size(); i++) {
			for(int k = 0; k < players.get(i).getHands().size(); k++) {
				players.get(i).insuranceDecision(players.get(i).getHand(k));
			}
		}
	}
	
	public void setWinnings(int winnings) {
		this.winnings = winnings;
	}
	
	public void setUpCards(CardHand upCards) {
		this.upCards = upCards;
	}
	
	public void setHidden(Card hidden) {
		this.hidden = hidden;
	}

}
