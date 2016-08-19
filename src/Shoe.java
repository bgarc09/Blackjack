import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Shoe {
	private LinkedList<Card> shoe;
	private int cutCard;
	private int currentCard;

	public Shoe() {
		shoe = new LinkedList<Card>();
	}
	
	public Shoe(int numDecks) {
		this();
		setUpShoe(numDecks);
		currentCard = 0;
		cutCard = shoe.size() / 4;
	}
	
	public boolean add(Card card) {
		return(shoe.add(card));
	}
	
	public int getCurrentCard() {
		return(currentCard);
	}
	
	public int getCutCard() {
		return(cutCard);
	}
	
	public LinkedList<Card> getShoe() {
		return(shoe);
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
	
	public Card remove() {
		return(shoe.remove());
	}
	
	public Card remove(int index) {
		return(shoe.remove(index));
	}
	
	public Card removeFirst() {
		currentCard++;
		return(shoe.removeFirst());
	}
	
	public void setCurrentCard(int currentCard) {
		this.currentCard = currentCard;
	}
	
	public void setCutCard(int cutCard) {
		this.cutCard = cutCard;
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
	
	public void shufleShoe(ArrayList<Card> decks) {
		Random rand = new Random();
		while(decks.size() != 0) {
			int index = rand.nextInt(decks.size());
			shoe.add(decks.remove(index));
		}
	}
}
