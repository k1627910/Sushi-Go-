package Cards;
public class NigiriCard extends Card {
	private int value;
	private boolean isNigiriOnWasabi;
	
	public NigiriCard(String name, int value) {
		super(name);
		this.value = value;
		this.isNigiriOnWasabi=false;
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public void setIsNigiriOnWasabi(boolean b) {
		isNigiriOnWasabi=b;
	}
	
	@Override
	public boolean isNigiriOnWasabi() {
		return isNigiriOnWasabi;
	}
}
