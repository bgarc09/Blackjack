import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Blackjack {
	
	private House house;
	private ArrayList<Player> players;
	private ArrayList<Card> shoe = new ArrayList<Card>();
	
	public Blackjack(House house, ArrayList<Player> players) {
		this.house = house;
		this.players = new ArrayList<Player>(players);
		//setUpShoe(4);
	}
	
	public ArrayList<Card> getShoe() {
		return shoe;
	}
	
	/**
	 * Deals the player another card and determines if the players busts with said card
	 * @param card Card to be dealt to the player
	 * @return true if the player busts; false otherwise
	 */
	public boolean hit(Player p, Card card) {
		p.getCards().getCards().add(card);
		if(p.getCards().cardTotal() > 21) {
			return true;
		}
		return false;
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
	
	public void setUpShoe(int numDecks) {
		ArrayList<Card> decks = new ArrayList<Card>();
		Random rand = new Random();
		System.out.println("NumDecks: " + numDecks);
		for(int i = 0; i < numDecks; i++) {
			try{
				decks.addAll(readInDeck());
			} catch(FileNotFoundException e) {
				System.err.println("File not found");
			}
		}
		int i = 0;
		System.out.println("Deck Size: " + decks.size());
		while(decks.size() != 0) {
			int index = rand.nextInt(decks.size());
			System.out.println(decks.size());
			//System.out.println(index);
			shoe.add(decks.remove(index));
			//System.out.println(i++);
		}
	}
}
