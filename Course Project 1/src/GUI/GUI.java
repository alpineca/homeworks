package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.WindowConstants;

import game.GameBoard;
import game.GameOver;
import game.Wellcome;
import interfaces.GameConfig;

public class GUI extends JFrame{
	private static Wellcome wellcomeScreen 	= new Wellcome();
	private static GameBoard gameBoard 		= new GameBoard();
	private static GameOver gameOver 		= new GameOver();
	private static boolean isGameOver 		= false;
	private static GUI instance;

	public GUI() {
		this.add(wellcomeScreen);		
		this.setSize(GameConfig.WINDOWSIZEX, GameConfig.WINDOWSIZEY);
		this.setVisible(true);
		this.setResizable(false);
        this.setTitle(GameConfig.PROGRAMTITLE);
        
        this.setLocation(0,0);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.instance = this;
	}

	public static GUI getGUIInstance(){
		return instance;
	}

	public void conditionStartGame(){
		wellcomeScreen.setVisible(false);
		this.add(gameBoard);
		this.addKeyListener(gameBoard);
		this.requestFocus();
		// this.setFocusable(true);
		// this.remove(wellcomeScreen);
		// this.repaint();
	}

	public void conditionGameOver(){
		gameBoard.setVisible(false);
		this.add(gameOver);
		this.repaint();
	}

}
