package GUI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Key;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.GameBoard;
import interfaces.GameConfig;

public class GUI extends JFrame{

	public GUI() {
		
		GameBoard gameBoard = new GameBoard();
		this.add(gameBoard);
		// this.addMouseListener(gameBoard);
		this.addKeyListener(gameBoard);
		
		this.setSize(GameConfig.WindowSizeX, GameConfig.WindowSizeY);
		this.setVisible(true);
		this.setResizable(true);
        this.setTitle(GameConfig.ProgramTitle);
        
        this.setLocation(0,0);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
