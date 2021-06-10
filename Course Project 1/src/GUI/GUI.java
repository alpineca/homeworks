package GUI;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.GameBoard;
import interfaces.GameConfig;

public class GUI extends JFrame{

	public GUI() {
		
		GameBoard gameBoard = new GameBoard();
		this.add(gameBoard);
		this.addKeyListener(gameBoard);
		
		this.setSize(GameConfig.WINDOWSIZEX, GameConfig.WINDOWSIZEY);
		this.setVisible(true);
		this.setResizable(false);
        this.setTitle(GameConfig.PROGRAMTITLE);
        
        this.setLocation(0,0);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
