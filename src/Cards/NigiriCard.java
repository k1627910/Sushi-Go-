package Cards;
public class NigiriCard extends Card {
	private int value;
	
	public NigiriCard(String name, int value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public int getValue() {
		return value;
	}
}
