package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.WindowConstants;

import enums.ResultEnum;
import game.GameBoard;
import game.GameOver;
import game.Wellcome;
import interfaces.GameConfig;

public class GUI extends JFrame{
	private static Wellcome wellcomeScreen 	= new Wellcome();
	private static GameBoard gameBoard 		= new GameBoard();
	private static GameOver gameOver;
	private static boolean isGameOver 		= false;
	private static GUI instance;

	public GUI() {
		this.add(wellcomeScreen);		
//		this.add(gameOver);
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
	
	public void conditionRestartGame(){
//		wellcomeScreen.setVisible(false);
		gameBoard = new GameBoard(true);
		this.add(gameBoard);
		gameOver.setVisible(false);
		this.addKeyListener(gameBoard);
		this.requestFocus();
		// this.remove(wellcomeScreen);
		// this.repaint();
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

	public void conditionGameOver(ResultEnum result){
		gameOver = new GameOver(result);
		
		gameBoard.setVisible(false);
//		gameBoard = null;
		this.remove(gameBoard);
		this.add(gameOver);
		gameOver.requestFocus();
		this.repaint();
	}

}
