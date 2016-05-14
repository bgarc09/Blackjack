
public class House {

	private int winnings;
	private DealerHand cards; 
	
	public House() {
		winnings = 0;
	}
	
	public DealerHand getCards() {
		return cards;
	}
	
	public int getWinnings() {
		return winnings;
	}
	
	public void setWinnings(int winnings) {
		this.winnings = winnings;
	}

}
