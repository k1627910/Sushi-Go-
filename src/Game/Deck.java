package Game;
import Cards.*;

import java.util.*;

public class Deck {
	private ArrayList<Card> deck;
	
	public Deck(){
		deck = new ArrayList<>();
		try {
			
			for (int i=0;i<14;i++) {
				deck.add(new TempuraCard("Tempura"));
				deck.add(new SashimiCard("Sashimi"));
				deck.add(new DumplingCard("Dumpling"));
			}
			for (int i=0;i<12;i++) {
				deck.add(new MakiRollCard("Maki Roll x2",2));
			}
			for (int i=0;i<10;i++) {
				deck.add(new NigiriCard("Salmon Nigiri", 2));
				deck.add(new PuddingCard("Pudding"));
			}
			for (int i=0;i<8;i++) {
				deck.add(new MakiRollCard("Maki Roll x3",3));
			}
			for (int i=0;i<6;i++) {
				deck.add(new MakiRollCard("Maki Roll x1", 1));
				deck.add(new WasabiCard("Wasabi"));
			}
			for (int i=0;i<5;i++) {
				deck.add(new NigiriCard("Squid Nigiri", 3));
				deck.add(new NigiriCard("Egg Nigiri", 1));
			}
			for (int i=0;i<4;i++) {
				deck.add(new ChopsticksCard("Chopsticks"));
			}
		}
		catch(Exception E) {
			System.out.println("Exception Error");
			throw new RuntimeException(E);
		}
	}
	
	public void shuffle() {
		Collections.shuffle(deck); 
	}
	
	public Card draw() {
		return deck.remove(0);
	}
}
