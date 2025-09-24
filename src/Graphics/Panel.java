package Graphics;
import Cards.*;
import Game.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class Panel extends JPanel implements MouseListener, KeyListener{
	private Game game;
	private ArrayList<Player> players;
	private String gameState;
	private int currentPlayer;
	private BufferedImage table, pudding, chopsticks, dumpling, eggnigiri, makiroll1, makiroll2, makiroll3, salmonnigiri, sashimi, squidnigiri, tempura, wasabi;
	
	public Panel() {
		game = new Game();
		players = game.getPlayers();
		gameState = "welcome";
		
		currentPlayer = 0;
		
		try {
			table = ImageIO.read(Panel.class.getResource("/CardImages/table.png"));
			pudding = ImageIO.read(Panel.class.getResource("/CardImages/pudding.png"));
			chopsticks = ImageIO.read(Panel.class.getResource("/CardImages/chopsticks.png"));
			dumpling = ImageIO.read(Panel.class.getResource("/CardImages/dumpling.png"));
			eggnigiri = ImageIO.read(Panel.class.getResource("/CardImages/eggnigiri.png"));
			makiroll1 = ImageIO.read(Panel.class.getResource("/CardImages/makiroll1.png"));
			makiroll2 = ImageIO.read(Panel.class.getResource("/CardImages/makiroll2.png"));
			makiroll3 = ImageIO.read(Panel.class.getResource("/CardImages/makiroll3.png"));
			salmonnigiri = ImageIO.read(Panel.class.getResource("/CardImages/salmonnigiri.png"));
			sashimi = ImageIO.read(Panel.class.getResource("/CardImages/sashimi.png"));
			squidnigiri = ImageIO.read(Panel.class.getResource("/CardImages/squidnigiri.png"));
			tempura = ImageIO.read(Panel.class.getResource("/CardImages/tempura.png"));
			wasabi = ImageIO.read(Panel.class.getResource("/CardImages/wasabi.png"));
		}
		catch(Exception E) {
			System.out.println("Exception Error!!!");
			return;
		}
		
		addMouseListener(this);
		addKeyListener(this);
	}
	
	public void welcomeScreen(Graphics g) {
		g.setColor(new Color(244, 162, 89));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(76, 76, 71));
		g.setFont(new Font("Arial", Font.BOLD, 200));
		g.drawString("Sushi Go!", getWidth()/2-450, getHeight()/2-100);
		g.fillRect(getWidth()/2-160,650,300,100);
		g.setColor(new Color(244, 162, 89));
		g.setFont(new Font("Arial", Font.PLAIN, 50));
		g.drawString("Start!", getWidth()/2-70, 715);
	}
	
	public void gameCards(Graphics g) {
		g.setColor(new Color(46,163,163));
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Player " + (int)(currentPlayer+1) + ": Select your card!", getWidth()/10, getHeight()/15);		

		ArrayList<Card> cards = players.get(currentPlayer).getHand();
		ArrayList<ArrayList<Card>> playedCards = new ArrayList<>();
		for (int i=0;i<players.size();i++){
		    playedCards.add(players.get(i).getPlayedCards());
		}

		int x = (int)(getWidth()/2-(getWidth()/10*4.5));
		for (int i=0;i<cards.size();i++) {
		    BufferedImage image = getBufferedImage(cards.get(i));
		    int z = (int)(getWidth()/10);
		    g.drawImage(image, x, getHeight()-(getHeight()/3), z, (int)(z*1.54), null);
		    x+=getWidth()/10;
		}

		x = (int)(getWidth()/2-(getWidth()/10*4));
		int y=getHeight()-(getHeight()/10*9);
		for (int i=0;i<playedCards.size();i++) {
			if (playedCards.get(2).size()>=1) {
				g.drawString("P" + (int)(i+1) + ":", x-100, y+70);
			}
		    ArrayList<Card> pile = playedCards.get(i);
		    for (int j=0;j<pile.size();j++) {
		        BufferedImage image = getBufferedImage(pile.get(j));
		        int z = (int)(getWidth()/17);
		        g.drawImage(image, x, y, z, (int)(z*1.54), null);
		        if (j!=pile.size()-1) {
		            x+=getWidth()/17;
		        }
		    }
		    y+=(getWidth()/17)*1.54;
		    x = (int)(getWidth()/2-(getWidth()/10*4));
		}

		y=getHeight()-(getHeight()/10*9);
		x = 8*(int)(getWidth()/2-(getWidth()/10*4));
		for (int i=0;i<players.size();i++) {
		    Player p = players.get(i);
		    for (int j=0;j<p.getPuddingCount();j++) {
		        int z = (int)(getWidth()/17);
		        g.drawImage(pudding, x, y, z, (int)(z*1.54),null);
		        if (j!=p.getPuddingCount()-1) {
		            x+=getWidth()/45;
		        }
		    }
		    y+=(getWidth()/17)*1.54;
		    x = 8*(int)(getWidth()/2-(getWidth()/10*4));
		}

		if (players.get(currentPlayer).getHasChopsticks() && !players.get(currentPlayer).getWantsToUseChopsticks()) {
		    g.setColor(Color.black);
		    g.fillRect((int)((getWidth()/2-(getWidth()/10*4.5))+getWidth()/10*8+20)-10, getHeight()-getHeight()/5-35, 200, 105);
		    g.setColor(new Color(46,163,163));
		    g.setFont(new Font("Arial", Font.BOLD, 23));
		    g.drawString("Click C to", (int)((getWidth()/2-(getWidth()/10*4.5))+getWidth()/10*8+20), getHeight()-getHeight()/5+7);
		    g.drawString("use Chopsticks", (int)((getWidth()/2-(getWidth()/10*4.5))+getWidth()/10*8+20), getHeight()-getHeight()/6+18);
		}
		else if (players.get(currentPlayer).getHasChopsticks() && players.get(currentPlayer).getWantsToUseChopsticks()){
		    g.setColor(new Color(46,163,163));
		    g.fillRect((int)((getWidth()/2-(getWidth()/10*4.5))+getWidth()/10*8+20)-10, getHeight()-getHeight()/5-35, 200, 105);
		    g.setColor(Color.black);
		    g.setFont(new Font("Arial", Font.BOLD, 20));
		    g.drawString("Chopsticks Active", (int)((getWidth()/2-(getWidth()/10*4.5))+getWidth()/10*8+20), getHeight()-getHeight()/5+7);
		    g.drawString("C to disable", (int)((getWidth()/2-(getWidth()/10*4.5))+getWidth()/10*8+20), getHeight()-getHeight()/6+18);
		} 		
	}
	
	public void roundResults(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("Round Results", getWidth() / 2 - 200, 100);

        int y = 200;
        for (Player p : players) {
            g.drawString(p.getName() + ": " + p.getCurrentScore() + " points", 100, y);
            y += 80;
        }
        g.fillRect(getWidth()/2-160,650,300,100);
        g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 50));
		g.drawString("Next Round!", getWidth()/2-150, 715);

        
	}
	
	public BufferedImage getBufferedImage(Card c) {
		if (c.getName().equals("Sashimi")) {
			return sashimi;
		}
		else if (c.getName().equals("Tempura")) {
			return tempura;
		}
		else if (c.getName().equals("Chopsticks")) {
			return chopsticks;
		}
		else if (c.getName().equals("Dumpling")) {
			return dumpling;
		}
		else if (c.getName().equals("Maki Roll x1")) {
			return makiroll1;
		}
		else if (c.getName().equals("Maki Roll x2")) {
			return makiroll2;
		}
		else if (c.getName().equals("Maki Roll x3")) {
			return makiroll3;
		}
		else if (c.getName().equals("Egg Nigiri")) {
			return eggnigiri;
		}
		else if (c.getName().equals("Salmon Nigiri")) {
			return salmonnigiri;
		}
		else if (c.getName().equals("Squid Nigiri")) {
			return squidnigiri;
		}
		else if (c.getName().equals("Wasabi")) {
			return wasabi;
		}
		else if (c.getName().equals("Pudding")) {
			return pudding;
		}
		return null;
	}
	
	public void gameEnd(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("Game Results", getWidth() / 2 - 200, 100);
        
        int y = 200;
        for (Player p : players) {
            g.drawString(p.getName() + ": " + p.getCurrentScore() + " points", 100, y);
            y += 80;
        }
        
        ArrayList<Integer> scores = new ArrayList<>();
        for (Player p : players) {
        	scores.add(p.getCurrentScore());
        }
        String winner = players.get(scores.indexOf(Collections.max(scores))).getName();
        g.drawString(winner + " is the winner!", getWidth()/2-300, getHeight()/2+175);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(table, 0,0,getWidth(),getHeight(),null);
		if (gameState.equals("welcome")) {
			welcomeScreen(g);
		}
		else if(gameState.equals("game cards")) {
			gameCards(g);
		}
		else if(gameState.equals("round results")) {
			roundResults(g);
		}
		else if(gameState.equals("game end")) {
			gameEnd(g);
		}
	}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		System.out.println(c);
		if (players.get(currentPlayer).getHasChopsticks()) {
			System.out.println(c=='c' && players.get(currentPlayer).getWantsToUseChopsticks() && (players.get(currentPlayer).getChopsticksSecondCard() == null));
			if (c=='c' && !players.get(currentPlayer).getWantsToUseChopsticks()) {
				System.out.println("Chopsticks!");
				players.get(currentPlayer).setWantsToUseChopsticks(true);
			}
			else if (c=='c' && players.get(currentPlayer).getWantsToUseChopsticks() && (players.get(currentPlayer).getChopsticksSecondCard() == null)) {
				players.get(currentPlayer).setWantsToUseChopsticks(false);
			}
		}
		repaint();
	}
	
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("(" + x + ", " + y + ")");
		if (gameState.equals("welcome")) {
			if (e.getX()>getWidth()/2-160&&e.getX()<getWidth()/2-160+300 && e.getY()>650 && e.getY()<650+100) {
				System.out.println("Start!");
				gameState = "game cards";
				game.distributeHands();
				repaint();
			}
		}
		else if (gameState.equals("game cards")) {
			int z = (int)(getWidth()/2-(getWidth()/10*4.5));
			Player p = players.get(currentPlayer);
			ArrayList<Card> hand = p.getHand();
			if (!p.getWantsToUseChopsticks()) {
				for (int i=0;i<p.getHand().size();i++) {
					if (x>z&&x<z+getWidth()/10 && y>getHeight()-(getHeight()/3)&&y<getHeight()-(getHeight()/3)+getWidth()/10*1.54) {
						Card c = hand.get(i);
						if (c.getName().equals("Chopsticks")) {
							p.setHasChopsticks(true);
						}
						p.setSelectedCard(c);
						if (currentPlayer==2) {
							game.playTurn();
							currentPlayer=0;
						}
						else {
							currentPlayer++;
						}
						if (p.getHandCount()==0) {
							if (game.getRound()==2) {
								gameState = "game end";
							}
							else {
								gameState = "round results";
							}
						}

			            repaint();
						break;
					}
					else {
						z+=getWidth()/10;
					}
				}
			}
			else if(p.getWantsToUseChopsticks()) {
				if (p.getChopsticksSecondCard()==null) {
					for (int i=0;i<p.getHand().size();i++) {
						System.out.println(z + "," + (z+getWidth()/10) + "---" + (int)(getHeight()-(getHeight()/3)) + "," + ((getHeight()/3)+getWidth()/10*1.54));
						System.out.println(x>z&&x<z+getWidth()/10 && y>getHeight()-(getHeight()/3)&&y<getHeight()-(getHeight()/3)+getWidth()/10*1.54);
						if (x>z&&x<z+getWidth()/10 && y>getHeight()-(getHeight()/3)&&y<getHeight()-(getHeight()/3)+getWidth()/10*1.54) {
							Card c = hand.get(i);
							System.out.println(c);
							p.setChopsticksSecondCard(c);
							repaint();
							break;
						}
						else {
							z+=getWidth()/10;
						}
					}
				}
				else {
					for (int i=0;i<p.getHand().size();i++) {
						if (x>z&&x<z+getWidth()/10 && y>getHeight()-(getHeight()/3)&&y<getHeight()-(getHeight()/3)+getWidth()/10*1.54) {
							Card c = hand.get(i);
							System.out.println(c);
							p.setSelectedCard(c);
							if (currentPlayer==2) {
								game.playTurn();
								currentPlayer=0;
							}
							else {
								currentPlayer++;
							}
							if (p.getHandCount()==0) {
								gameState = "round results";
							}
							
				            repaint();
							break;
						}
						else {
							z+=getWidth()/10;
						}
					}
				}
			}
		}
		else if (gameState.equals("round results")) {
			if (e.getX()>getWidth()/2-160&&e.getX()<getWidth()/2-160+300 && e.getY()>650 && e.getY()<650+100) {
				game.startNewRound();
				gameState = "game cards";
				repaint();
			}
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
}