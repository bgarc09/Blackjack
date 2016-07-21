import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Blackjack {
	
	private House dealer;
	private ArrayList<Player> players;
	private LinkedList<Card> shoe = new LinkedList<Card>();
	
	public Blackjack(House dealer, ArrayList<Player> players, int numDecks) {
		this.dealer = dealer;
		this.players = new ArrayList<Player>(players);
		setUpShoe(numDecks);
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
	
	/**
	 * Deals two cards to each player and the dealer
	 */
	public void deal() {
		dealer.setHidden(shoe.removeFirst());
		for(int i = 0; i < players.size(); i++) {
			players.get(i).addHand(new CardHand(shoe.removeFirst()));
		}
		dealer.getUpCards().addCard(shoe.remove(0));
		for(int i = 0; i < players.size(); i++) {
			players.get(i).getHand(0).addCard(shoe.removeFirst());
		}
	}
	
	public House getDealer() {
		return dealer;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public LinkedList<Card> getShoe() {
		return shoe;
	}
	
//	/**
//	 * Gives the player a new card for their hand
//	 * @param p 
//	 */
//	public void hit(Player p) {
//		p.getHand().getCards().add(shoe.remove(0));
//	}
	
	public void playGameTilPlayersBroke() {
		int counter = 0;
		while(players.size() > 0) {
			counter++;
		}
		System.out.println("Num Rounds: " + counter);
	}
	
	public void playGameNumRounds(int numRounds) {
		for(int i = 0; i < players.size(); i++) {
			System.out.println("Player " + i + ": " +players.get(i).getMoney());
		}
	}
	
	/**
	 * Commences a round of blackjack with each player only betting the minimum amount
	 * @param minBet
	 */
	public void playRound(int minBet) {
		boolean dealerBlackjack = false;
		//ArrayList<Player> playersInRound = new ArrayList<Player>();
		//betting *********put into a method maybe
		playRouundMakeBets(minBet);
		
		deal();
		if(dealer.getUpCards().contains("Ace")) {
			int[] insurance = dealer.offerInsurance(players);
			if(dealer.getHidden().getValue() == 10) {
				dealerBlackjack = true;
				for(int i = 0; i < insurance.length; i++) {
					players.get(i).setMoeny(players.get(i).getMoney() + insurance[i] * 2);
				}
			} else {
				for(int i = 0; i < insurance.length; i++) {
					dealer.collectBet(insurance[i]);
				}
			}
		} 
		if(!dealerBlackjack) {
			for(int i = 0; i < players.size(); i++) {
				for(int k = 0; k < players.get(i).getHands().size(); k++) {
					players.get(i).playerDecision(dealer.getUpCards(), players.get(i).getHand(k), shoe);
				}
			}
		} 
		
				
		//logic of blackjack for player (if dealer doesn't have blackjack a.k.a. hidden card hidden) ********** put into method maybe 
		//rules of blackjack for house *******put into method maybe 
		
		//playRoundCheckBlackjack();
		
		//deciding who wins ****************put into method maybe
		dealer.flipHidden();
		playRoundDetermineWinnersAndPay();
	}
	
	public void playRoundDetermineWinnersAndPay() {
		for(int i = 0; i < players.size(); i++) {
			for(int k = 0; k < players.get(i).getHands().size(); k++) {
				if(bust(dealer.getUpCards())) {
					if(!bust(players.get(i).getHand(k))) {
						players.get(i).collectWinnings(players.get(i).getCurrentBet() * 2);
						players.get(i).setConsecutiveWins((players.get(i).getConsecutiveWins() + 1) % players.get(i).getLetItRide());
					} else {
						players.get(i).setConsecutiveWins(0);
						players.get(i).setLastHandWinnings(0);
					}
				} else {
					if(!bust(players.get(i).getHand(k))) {
						if(players.get(i).getHand(k).handTotal() > dealer.handTotal()) {
							players.get(i).collectWinnings(players.get(i).getCurrentBet() * 2);
							players.get(i).setConsecutiveWins((players.get(i).getConsecutiveWins() + 1) % players.get(i).getLetItRide());
						} else if(players.get(i).getHand(i).handTotal() == dealer.handTotal()) {
							players.get(i).collectWinnings(players.get(i).getCurrentBet());
							players.get(i).setConsecutiveWins(0);
						} else {
							players.get(i).setConsecutiveWins(0);
							players.get(i).setLastHandWinnings(0);
						}
					} else {
						players.get(i).setConsecutiveWins(0);
						players.get(i).setLastHandWinnings(0);
					}
				}
			}
		}
	}
	
	public void playRouundMakeBets(int minBet) {
		for(int i = 0; i < players.size(); i++) {
			//let it ride system
			if(players.get(i).getConsecutiveWins() > 0) {
				players.get(i).bet(players.get(i).getLastHandWinnings());
				dealer.collectBet(players.get(i).getLastHandWinnings());
			} else {
				if(!players.get(i).bet(minBet)) {
					players.remove(i);
				} else {
					dealer.collectBet(minBet);
				}
			}
		}
	}

	public ArrayList<Card> readInDeck() throws FileNotFoundException {
		ArrayList<Card> deck = new ArrayList<Card>();
		String type = null;
		String name = null;
		String value = null;
		Scanner scanner = new Scanner(new File("StandardDeck.csv"));
		scanner.useDelimiter(Pattern.compile("[\\r\\n,]+"));
		while(scanner.hasNext()){
			if(type == null) {
				type = scanner.next();
			} else if(name == null) {
				name = scanner.next();
			} else if(value == null) {
				value = scanner.next();
				deck.add(new Card(type, name, Integer.parseInt(value)));
				type = null;
				name = null;
				value = null;
			} 
		}
		scanner.close();
		return deck;
	}
	
	public void setDealer(House dealer) {
		this.dealer = dealer;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void setShoe(LinkedList<Card> shoe) {
		this.shoe = shoe;
	}
	
	public void setUpShoe(int numDecks) {
		ArrayList<Card> decks = new ArrayList<Card>();
		Random rand = new Random();
		for(int i = 0; i < numDecks; i++) {
			try{
				decks.addAll(readInDeck());
			} catch(FileNotFoundException e) {
				System.err.println("File not found");
			}
		}
		while(decks.size() != 0) {
			int index = rand.nextInt(decks.size());
			shoe.add(decks.remove(index));
		}
	}
}
