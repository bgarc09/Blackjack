
public class Player {

	private CardHand cards;
	private int money;
	
	public Player(int money) {
		this.money = money;
	}
	
	public CardHand getCards() {
		return cards;
	}
	
	public int getMoney() {
		return money;
	}
	
	/**
	 * Deals the player another card and determines if the players busts with said card
	 * @param card Card to be dealt to the player
	 * @return true if the player busts; false otherwise
	 */
	public boolean hit(Card card) {
		cards.getCards().add(card);
		if(cards.cardTotal() > 21) {
			return true;
		}
		return false;
	}
	
	public void playHand(CardHand cards, int bet) {
		money -= bet;
		this.cards = cards;
	}
	
	public void setCards(CardHand cards) {
		this.cards = cards;
	}
	
	public void setMoeny(int money) {
		this.money = money;
	}
	
	public void win(int winnings) {
		money += winnings;
	}
}
