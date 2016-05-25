
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
