import java.util.ArrayList;

public class CardHand {
	
	public ArrayList<Card> cards;
	
	public CardHand() {
		cards = new ArrayList<Card>();
	}
	
	public CardHand(Card a, Card b) {
		cards = new ArrayList<Card>();
		cards.add(a);
		cards.add(b);
	}
	
	public int cardTotal() {
		int total = 0;
		for(int i = 0; i < cards.size(); i++) {
			total += cards.get(i).getValue();
		}
		return total;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards; 
	}
}
