package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import GameBoardObjects.GameBoardObject;
import GameBoardObjects.ArmyUnits.Drunker;
import GameBoardObjects.ArmyUnits.Fisherman;
import GameBoardObjects.ArmyUnits.RockTrower;
import GameBoardObjects.ArmyUnits.Tracktorist;
import GameBoardObjects.materials.Building;
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
		
		this.createSmallBuilding(2, 2);
		this.createMiddleBuilding(4, 7);
		this.createLargeBuilding(8, 1);
		
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
	
	private void createSmallBuilding(int row, int col) {
		Color color = Color.GREEN;
		
		this.gameBoard[row][col] 			= new Building(row, col, color);
		this.gameBoard[row][col + 1] 		= new Building(row, col + 1, color);
		this.gameBoard[row + 1][col] 		= new Building(row + 1, col, color);
		this.gameBoard[row + 1][col + 1] 	= new Building(row + 1, col + 1, color);
	}
	private void createMiddleBuilding(int row, int col) {
		Color color = Color.BLUE;
		
		this.gameBoard[row][col] 			= new Building(row, col, color);
		this.gameBoard[row][col + 1] 		= new Building(row, col + 1, color);
		this.gameBoard[row][col + 2] 		= new Building(row, col + 2, color);
		this.gameBoard[row + 1][col] 		= new Building(row + 1, col, color);
		this.gameBoard[row + 1][col + 1] 	= new Building(row + 1, col + 1, color);
		this.gameBoard[row + 1][col + 2] 	= new Building(row + 1, col + 2, color);
	}
	private void createLargeBuilding(int row, int col) {
		Color color = Color.LIGHT_GRAY;
		
		this.gameBoard[row][col] 			= new Building(row, col, color);
		this.gameBoard[row][col + 1] 		= new Building(row, col + 1, color);
		this.gameBoard[row][col + 2] 		= new Building(row, col + 2, color);
		this.gameBoard[row + 1][col] 		= new Building(row + 1, col, color);
		this.gameBoard[row + 1][col + 1] 	= new Building(row + 1, col + 1, color);
		this.gameBoard[row + 1][col + 2] 	= new Building(row + 1, col + 2, color);
		this.gameBoard[row + 2][col] 		= new Building(row + 2, col, color);
		this.gameBoard[row + 2][col + 1] 	= new Building(row + 2, col + 1, color);
		this.gameBoard[row + 2][col + 2] 	= new Building(row + 2, col + 2, color);
	}

}
