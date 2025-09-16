package Cards;
public class MakiRollCard extends Card{ 
	private int value;
	
	public MakiRollCard(String name, int value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public int getValue() {
		return value;
	}
}
