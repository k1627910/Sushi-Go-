package Game;
import java.util.*;
import Cards.*;

public class Player {
	private String name;
	private ArrayList<Card> hand;
	private ArrayList<Card> playedCards;
	private int totalScore;
	private Card selectedCard;
	private Card chopsticksSecondCard;
	private int puddingCount;
	private boolean wantsToUseChopsticks;
	private boolean hasChopsticks;
	
	public Player(String name) {
		this.name = name;
		playedCards = new ArrayList<>();
		selectedCard = null;
		totalScore = 0;
		puddingCount = 0;
		wantsToUseChopsticks = false;
		hasChopsticks = false;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public void setSelectedCard(Card c) {
		selectedCard = hand.remove(hand.indexOf(c));
	}
	
	public boolean getHasChopsticks() {
		return hasChopsticks;
	}
	
	public void setHasChopsticks(boolean t) {
		hasChopsticks = t;
	}
	
	public Card getSelectedCard() {
		return selectedCard;
	}
	
	public void playCard(Card c) {
		playedCards.add(c);
	}

	public void receiveHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public ArrayList<Card> getPlayedCards(){
		return playedCards;
	}
	
	public int getHandCount() {
		return hand.size();
	}
	
	public int getPlayedCardCount() {
		return playedCards.size();
	}
	
	public void clearHand() {
		hand = new ArrayList<>();
		selectedCard = null;
		chopsticksSecondCard = null;
		wantsToUseChopsticks = false;
		hasChopsticks = false;
		
	}
	
	public void clearPlayedCards() {
		playedCards = new ArrayList<>();
	}
	
	public void addScore(int score) {
		this.totalScore+=score;
	}
	
	public int getCurrentScore() {
		return totalScore;
	}
	
	public void addPuddingCount(int count) {
		this.puddingCount+=count;
	}
	
	public int getPuddingCount() {
		return puddingCount;
	}
	
	public void setChopsticksSecondCard(Card c) {
		chopsticksSecondCard = hand.remove(hand.indexOf(c));
	}
	
	public Card getChopsticksSecondCard() {
		return chopsticksSecondCard;
	}
	
	public boolean getWantsToUseChopsticks() {
		return this.wantsToUseChopsticks;
	}
	
	public void setWantsToUseChopsticks(boolean c) {
		this.wantsToUseChopsticks = c;
	}
	
	public void playTwoCardsWithChopsticks(Card first, Card second) {
		playedCards.add(second);
		playedCards.add(first);
		int indexChopsticks = 0;
		for (int i=0;i<playedCards.size();i++) {
			if (playedCards.get(i).getName().equals("Chopsticks")) {
				indexChopsticks = i;
				break;
			}
		}
		hand.add(playedCards.remove(indexChopsticks));
		hasChopsticks = false;
		this.chopsticksSecondCard = null;
		setWantsToUseChopsticks(false);
	}
	
	public String getPlayedCardsAsString() {
		String x = "";
		for (int i=0;i<playedCards.size();i++) {
			x+=playedCards.get(i).getName() + " ";
		}
		return x;
	}
	
}
