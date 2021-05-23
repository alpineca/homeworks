package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Key;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import GameBoardObjects.materials.Building;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.armyUnits.Drunker;
import GameBoardObjects.armyUnits.Fisherman;
import GameBoardObjects.armyUnits.RockTrower;
import GameBoardObjects.armyUnits.Tracktorist;
import interfaces.GameConfig;
import processors.BuildProcessor;
import processors.UnitsProcessor;

public class GameBoard extends JPanel implements MouseListener{
	
	GameBoardObject[][] gameBoard;

	public GameBoard() {
		this.bootstrap();
		this.addMouseListener(this);
	}
	
	private void bootstrap() {
		
		this.gameBoard = new GameBoardObject[GameConfig.rows][GameConfig.cols];
		
		for(int row = 0; row < GameConfig.rows; row++) {
			for(int col = 0; col < GameConfig.cols; col++) {
				this.gameBoard[row][col] = new Ground(row, col);
			}
		}
		
		UnitsProcessor.spawnArmyUnits(gameBoard);
		BuildProcessor.spawnBuildings(gameBoard);
		
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
	public void mouseClicked(MouseEvent e) {
		this.bootstrap();
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
