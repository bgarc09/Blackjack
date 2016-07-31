import java.util.ArrayList;

public class CardHand {
	
	private ArrayList<Card> cards;
	
	public CardHand() {
		cards = new ArrayList<Card>();
	}
	
	public CardHand(Card a, Card b) {
		cards = new ArrayList<Card>();
		cards.add(a);
		cards.add(b);
	}
	
	public CardHand(Card a) {
		cards = new ArrayList<Card>();
		cards.add(a);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public boolean contains(Card c) {
		return(cards.contains(c));
	}
	
	public boolean contains(String cardName) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getName().equals(cardName)) {
				return(true);
			}
		}
		return(false);
	}
	
	public boolean contains(int cardValue) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getValue() == cardValue) {
				return(true);
			}
		}
		return(false);
	}
	
	public boolean contains(Card...c) {
		boolean[] foundCard = new boolean[c.length];
		int foundCounter = 0;
		for(int i = 0; i < cards.size(); i++) {
			for(int k = 0; k < foundCard.length; k++) {
				if(cards.get(i).equals(c[k]) && !foundCard[k]) {
					foundCard[k] = true;
					foundCounter++;
				}
			}
		}
		return(foundCounter == foundCard.length);
	}
	
	public boolean contains(String...cardNames) {
		boolean[] foundCard = new boolean[cardNames.length];
		int foundCounter = 0;
		for(int i = 0; i < cards.size(); i++) {
			for(int k = 0; k < foundCard.length; k++) {
				if(cards.get(i).getName().equals(cardNames[k]) && !foundCard[k]) {
					foundCard[k] = true;
					foundCounter++;
				}
			}
		}
		return(foundCounter == foundCard.length);
	}
	
	public boolean contains(int...cardValues) {
		boolean[] foundCard = new boolean[cardValues.length];
		int foundCounter = 0;
		for(int i = 0; i < cards.size(); i++) {
			for(int k = 0; k < foundCard.length; k++) {
				if(cards.get(i).getValue() == (cardValues[k]) && !foundCard[k]) {
					foundCard[k] = true;
					foundCounter++;
				}
			}
		}
		return(foundCounter == foundCard.length);
	}
	
	public boolean equals(Object o) {
		if(o == null) {
			return(false);
		}
		if(o instanceof CardHand) {
			boolean result = true;
			CardHand c = (CardHand)o;
			for(int i = 0; i < c.getCards().size(); i++) {
				if(!this.getCards().get(i).equals(c.getCards().get(i))) {
					result = false;
				}
			}
			return(result);
		}
		return(false);
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public int handTotal() {
		int total = 0;
		boolean hasAce = false;
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getName().equals("Ace")) {
				//aceCount++;
				hasAce = true;
			}
			total += cards.get(i).getValue();
		}
		if(hasAce && total + 10 <= 21) {
			total += 10;
		}
		return total;
	}
	
	public int numCards() {
		return cards.size();
	}
	
	public Card remove(int index) {
		return cards.remove(index);
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards; 
	}
}
