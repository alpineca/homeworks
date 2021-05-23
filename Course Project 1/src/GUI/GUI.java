package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.GameBoard;
import interfaces.GameConfig;

public class GUI extends JFrame implements MouseListener {

	public GUI() {
		
		GameBoard gameBoard = new GameBoard();
		this.add(gameBoard);
		
		this.setSize(GameConfig.WindowSizeX, GameConfig.WindowSizeY);
		this.setVisible(true);
		this.setResizable(true);
        this.setTitle(GameConfig.ProgramTitle);
        
        this.setLocation(0,0);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		new GUI();
		this.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
