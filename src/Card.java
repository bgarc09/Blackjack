
public class Card {

	private String type;
	private String name;
	private int value;
	
	public Card(String type, String name, int value) {
		this.type = type;
		this.name = name;
		this.value = value;
	}
	
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o instanceof Card) {
			Card c = (Card)o;
			if(this.getName().equals(c.getName()) && 
					this.getType().equals(c.getType()) && 
					this.getValue() == c.getValue()) {
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
