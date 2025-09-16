package Game;
import Cards.*;
import java.util.*;

public class Game {
	private final ArrayList<Player> players;
	private final Deck deck;
	private final String[] names = {"Player 1", "Player 2", "Player 3"};
	private int round;
	
	public Game() {
		players = new ArrayList<>();
		for (int i=0;i<3;i++) {
			players.add(new Player(names[i]));
		}
		deck = new Deck();
		round = 0;
	}
	
	public ArrayList<Player> getPlayers() {
	    return players;
	}
	
	public int getRound() {
		return round;
	}

	
	public void distributeHands() {
		for (int i=0;i<players.size();i++) {
			deck.shuffle();
			ArrayList<Card> temp = new ArrayList<>();
			for (int j=0;j<9;j++) {
				temp.add(deck.draw());
			}
            players.get(i).receiveHand(temp);
		}
	}
	
	public void startNewRound() {
		for (Player p : players) {
			p.clearPlayedCards();
		}
		deck.shuffle();
		round++;
		distributeHands();
	}

    public void playTurn() {
    	for (Player p : players) {
    	    if (p.getWantsToUseChopsticks()) {
    	        Card first = p.getSelectedCard();
    	        Card second = p.getChopsticksSecondCard();
    	        p.playTwoCardsWithChopsticks(first, second);
    	    } 
    	    else {
    	        Card chosen = p.getSelectedCard();
    	        if (chosen != null) {
    	            p.playCard(chosen);
    	        }
    	    }
    	}
    	
    	ArrayList<ArrayList<Card>> newHands = new ArrayList<>(); 
        for (Player p : players) {
            newHands.add(p.getHand());
        }

        for (int i = 0; i < players.size(); i++) {
            ArrayList<Card> nextHand = newHands.get((i + 1) % players.size());
            players.get(i).receiveHand(new ArrayList<>(nextHand));
        }
        
        checkEnding();

    }
    
    
    public void checkEnding() {
    	if (players.get(0).getHandCount() == 1) {
            for (Player p : players) {
                p.clearHand();
            }
            scoreRound();
            if (round == 2) {
                endGame();
            }
        }
    }
    
    public Player endGame() {
    	ArrayList<Integer> totalScores = new ArrayList<>();
    	totalScores.add(players.get(0).getCurrentScore());
    	totalScores.add(players.get(1).getCurrentScore());
    	totalScores.add(players.get(2).getCurrentScore());
    	
    	ArrayList<Integer> puddingCounts = new ArrayList<>();
    	puddingCounts.add(players.get(0).getPuddingCount());
    	puddingCounts.add(players.get(1).getPuddingCount());
    	puddingCounts.add(players.get(2).getPuddingCount());
    	
    	int max = Collections.max(puddingCounts);
    	int min = Collections.min(puddingCounts);
    	
    	int winners = 0;
    	for (int count : puddingCounts) {
    	    if (count == max) winners++;
    	}
    	int awardMax = 6 / winners;
    	for (int i = 0; i < puddingCounts.size(); i++) {
    	    if (puddingCounts.get(i) == max) {
    	        players.get(i).addScore(awardMax);
    	    }
    	}

    	
    	int losers = 0;
    	for (int count : puddingCounts) {
    	    if (count == min) losers++;
    	}
    	int awardMin = -6 / losers;
    	for (int i = 0; i < puddingCounts.size(); i++) {
    	    if (puddingCounts.get(i) == min) {
    	    	players.get(i).addScore(awardMin);
    	    }
    	}
    	
    	ArrayList<Integer> finalScores = new ArrayList<>();
    	for (int i=0;i<3;i++) {
    		finalScores.add(players.get(i).getCurrentScore());
    	}
    	
    	return players.get(finalScores.indexOf(Collections.max(finalScores)));
    	
    }
    
    public void scoreRound() {
    	int[] scores = getRoundScores();
    	players.get(0).addScore(scores[0]);
    	players.get(1).addScore(scores[1]);
    	players.get(2).addScore(scores[2]);
    }
    
    public int[] getRoundScores() {
    	int scores[] = {0,0,0};
    	
    	ArrayList<Integer> makiRolls = new ArrayList<>();
    	makiRolls.add(0);
    	makiRolls.add(0);
    	makiRolls.add(0);
    	
    	for (int i=0;i<scores.length;i++) {
    		Player p = players.get(i);
    		ArrayList<Card> playedCards = p.getPlayedCards();
    		int sashimi = 0;
			int tempura = 0;
			int dumpling = 0;
    		for (int j=0;j<p.getPlayedCardCount();j++) {
    			Card c = playedCards.get(j);
    			if (c.getName().equals("Sashimi")) {
    				sashimi++;
    			}
    			else if (c.getName().equals("Tempura")) {
    				tempura++;
    			}
    			else if (c.getName().equals("Dumpling")) {
    				dumpling++;
    			}
    			else if (c.getName().equals("Maki Roll x1")) {
    				makiRolls.set(i, makiRolls.get(i)+1);
    			}
    			else if (c.getName().equals("Maki Roll x2")) {
    				makiRolls.set(i, makiRolls.get(i)+2);
    			}
    			else if (c.getName().equals("Maki Roll x3")) {
    				makiRolls.set(i, makiRolls.get(i)+3);
    			}
    			else if (c.getName().equals("Egg Nigiri")) {
    				scores[i]+=1;
    			}
    			else if (c.getName().equals("Salmon Nigiri")) {
    				scores[i]+=2;
    			}
    			else if (c.getName().equals("Squid Nigiri")) {
    				scores[i]+=3;
    			}
    			else if (c.getName().equals("Wasabi")) {
    				for (int n=j+1;n<playedCards.size();n++) {
    					if (playedCards.get(n).getName().equals("Egg Nigiri")) {
    						scores[i]+=3;
    						playedCards.remove(n);
    						break;
    					}
    					else if (playedCards.get(n).getName().equals("Salmon Nigiri")) {
    						scores[i]+=6;
    						playedCards.remove(n);
    						break;
    					}
    					else if (playedCards.get(n).getName().equals("Squid Nigiri")) {
    						scores[i]+=9;
    						playedCards.remove(n);
    						break;
    					}
    				}
    			}
    			else if (c.getName().equals("Pudding")) {
    				p.addPuddingCount(1);
    			}
    		}
    		scores[i]+=((sashimi-(sashimi%3))/3)*10;
    		scores[i]+=((tempura-(tempura%2))/2)*5;
    		if (dumpling==1) {
    			scores[i]+=1;
    		}
    		else if (dumpling==2) {
    			scores[i]+=3;
    		}
    		else if (dumpling==3) {
    			scores[i]+=6;
    		}
    		else if (dumpling==4) {
    			scores[i]+=10;
    		}
    		else if (dumpling>=5) {
    			scores[i]+=15;
    		}
    	}
    	int firstMakiRolls = Collections.max(makiRolls);

    	int firstTies = 0;
    	for (int val : makiRolls) {
    	    if (val == firstMakiRolls) firstTies++;
    	}

    	int secondMakiRolls = -1;
    	for (int val : makiRolls) {
    	    if (val < firstMakiRolls) {
    	        secondMakiRolls = Math.max(secondMakiRolls, val);
    	    }
    	}

    	int secondTies = 0;
    	for (int val : makiRolls) {
    	    if (val == secondMakiRolls) secondTies++;
    	}

    	if (firstTies > 1) {
    	    for (int i = 0; i < makiRolls.size(); i++) {
    	        if (makiRolls.get(i) == firstMakiRolls) {
    	            scores[i] += 6 / firstTies;
    	        }
    	    }
    	} else {
    	    for (int i = 0; i < makiRolls.size(); i++) {
    	        if (makiRolls.get(i) == firstMakiRolls) {
    	            scores[i] += 6;
    	        }
    	    }

    	    if (secondMakiRolls != -1 && secondTies > 0) {
    	        for (int i = 0; i < makiRolls.size(); i++) {
    	            if (makiRolls.get(i) == secondMakiRolls) {
    	                scores[i] += 3 / secondTies;
    	            }
    	        }
    	    }
    	}

    	for (int i =0;i<3;i++) {
    		System.out.print("P" + (int)(i+1) + ": " + scores[i] + "\n");
    	}
    	
    	return scores;
    }
}