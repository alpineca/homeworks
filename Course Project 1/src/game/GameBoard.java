package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import GameBoardObjects.materials.Ground;
import enums.Direction;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.enemys.Petkan;
import interfaces.GameConfig;
import processors.BuildProcessor;
import processors.EnemyProcessor;
import processors.UnitsProcessor;

public class GameBoard extends JPanel implements KeyListener{
	
	public static GameBoardObject[][] gameBoard;
	public static ArrayList<GameBoardObject> armyUnits 		= new ArrayList<>();
	public static ArrayList<GameBoardObject> buildings 		= new ArrayList<>();
	public static ArrayList<GameBoardObject> enemyUnits 	= new ArrayList<>();

	private GameBoardObject unitToMove;

	public static int fireCounter = 1000;

	public GameBoard() {
		this.bootstrap();
	}
	
	private void bootstrap() {
		
		gameBoard = new GameBoardObject[GameConfig.rows][GameConfig.cols];
		
		for(int row = 0; row < GameConfig.rows; row++) {
			for(int col = 0; col < GameConfig.cols; col++) {
				gameBoard[row][col] = new Ground(row, col);
			}
		}
		
		BuildProcessor.spawnBuildings(	gameBoard, buildings);
		UnitsProcessor.spawnArmyUnits(	gameBoard, armyUnits);
		EnemyProcessor.spawnEnemyUnits(	gameBoard, enemyUnits);

		
	}
	
	public void makeMove() {
		fireCountdown();
		EnemyProcessor.moveEnemy(gameBoard, enemyUnits);
		this.unitToMove = null;
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int row = 0; row < GameConfig.rows; row++) {
			for(int col = 0; col < GameConfig.cols; col++) {
				gameBoard[row][col].render(g);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		keyActionProcessor(e.getKeyChar());
		
		this.repaint();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode 		= e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_C) {
			keyActionProcessor(keyCode);
			this.unitToMove = armyUnits.get(0);
		}
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
			keyActionProcessor(keyCode);
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode 		= e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_C) {
			keyActionProcessor(keyCode);
			this.unitToMove = null;
		}
	}
	
	private void keyActionProcessor(int keyCode) {
		
		if(keyCode == 'a' || keyCode == 'A') {
			System.out.println("A");
			UnitsProcessor.move(Direction.LEFT, gameBoard, armyUnits, buildings, enemyUnits);
			this.makeMove();
		}
		if(keyCode == 'd' || keyCode == 'D') {
			System.out.println("D");
			UnitsProcessor.move(Direction.RIGHT, gameBoard, armyUnits, buildings, enemyUnits);
			this.makeMove();
		}
		if(keyCode == 'w' || keyCode == 'W') {
			System.out.println("W");
			UnitsProcessor.move(Direction.UP, gameBoard, armyUnits, buildings, enemyUnits);
			this.makeMove();
		}
		if(keyCode == 's' || keyCode == 'S') {
			System.out.println("S");
			UnitsProcessor.move(Direction.DOWN, gameBoard, armyUnits, buildings, enemyUnits);
			this.makeMove();
		}
		if(keyCode == KeyEvent.VK_RIGHT && unitToMove != null)
		{
			UnitsProcessor.swapUnit(gameBoard, armyUnits, unitToMove);
			this.repaint();
		}
	}
	public static void setFireCounter() {
		if(fireCounter > 10){
			fireCounter = 5;
		} else{
			fireCounter = fireCounter;
		}
	}
	
	private void fireCountdown(){
		if(fireCounter != 0){
			this.fireCounter--;
		} else if(this.fireCounter == 0){
			Petkan enemy =  (Petkan) enemyUnits.get(0);
			enemy.fire();
			fireCounter = 50;
		}
	}

	
	
}
