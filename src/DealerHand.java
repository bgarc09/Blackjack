import java.util.ArrayList;

public class DealerHand extends CardHand{

	private Card cardHidden;
	
	public DealerHand(Card cardShown, Card cardHidden) {
		super();
		cards.add(cardShown);
		this.cardHidden = cardHidden;
	}
	
	public void flipHiddenCard() {
		cards.add(cardHidden);
	}
	
	public Card getCardHidden() {
		return cardHidden;
	}
	
	public void setCardHidden(Card cardHidden) {
		this.cardHidden = cardHidden;
	}
}
