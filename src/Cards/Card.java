package Cards;
public class Card {
	private String name;
	
	public Card(String name) {
		this.name = name;
	}
	
	public int getValue() {
		return 0;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isWasabi () {
		if (name.equals("Wasabi")) {
			return true;
		}
		return false;
	}
	
	public boolean isNigiri() {
		if (name.substring(name.length()-6,name.length()).equals("Nigiri")) {
			return true;
		}
		return false;
	}
	
	public void setIsNigiriOnWasabi(boolean b) {
		return;
	}
	
	public boolean isNigiriOnWasabi() {
		return false;
	}
}