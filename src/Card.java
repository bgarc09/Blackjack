
public class Card {

	private String type;
	private String name;
	private int value;
	
	public Card(String type, String name, int value) {
		this.type = type;
		this.name = name;
		this.value = value;
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
	
	public void setName() {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setValue() {
		this.value = value;
	}
}
