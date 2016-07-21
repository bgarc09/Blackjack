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
			return(hidden.getValue() + upCards.handTotal());
		}
	}
	
	public int[] offerInsurance(ArrayList<Player> players) {
		int[] takesInsurance = new int[players.size()];
		for(int i = 0; i < players.size(); i++) {
			for(int k = 0; k < players.get(i).getHands().size(); k++) {
				takesInsurance[i] = players.get(i).takeInsurance(players.get(i).getHand(k));
			}
		}
		return(takesInsurance);
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
