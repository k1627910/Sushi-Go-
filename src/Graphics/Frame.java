package Graphics;
import Game.*;
import Cards.*;
import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1035;
	
	public Frame(String name) {
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		add(new Panel());
		setVisible(true);
	}
}
