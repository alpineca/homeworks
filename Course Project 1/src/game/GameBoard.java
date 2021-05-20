package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import GameBoardObjects.GameBoardObject;
import GameBoardObjects.ArmyUnits.Drunker;
import GameBoardObjects.ArmyUnits.Fisherman;
import GameBoardObjects.ArmyUnits.RockTrower;
import GameBoardObjects.ArmyUnits.Tracktorist;
import GameBoardObjects.materials.Ground;
import interfaces.GameConfig;

public class GameBoard extends JPanel implements KeyListener{
	
	GameBoardObject[][] gameBoard;

	public GameBoard() {
		this.bootstrap();
	}
	
	private void bootstrap() {
		
		this.gameBoard = new GameBoardObject[GameConfig.rows][GameConfig.cols];
		
		for(int row = 0; row < GameConfig.rows; row++) {
			for(int col = 0; col < GameConfig.cols; col++) {
				this.gameBoard[row][col] = new Ground(row, col);
			}
		}
		
		this.gameBoard[14][11] = new Tracktorist(14, 11);
		this.gameBoard[14][12] = new RockTrower(14, 12);
		this.gameBoard[14][13] = new Drunker(14, 13);
		this.gameBoard[14][14] = new Fisherman(14, 14);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int row = 0; row < GameConfig.rows; row++) {
			for(int col = 0; col < GameConfig.cols; col++) {
				this.gameBoard[row][col].render(g);;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
