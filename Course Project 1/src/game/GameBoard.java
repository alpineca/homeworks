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
		
		UnitsProcessor.spawnArmyUnits(	gameBoard, armyUnits);
		UnitsProcessor.spawnEnemyUnits(	gameBoard, enemyUnits);
		BuildProcessor.spawnBuildings(	gameBoard, buildings);

		
	}
	
	public void makeMove() {
		fireCountdown();
		moveEnemy();
		this.unitToMove = null;
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

	private static void moveEnemy(){
		GameBoardObject enemy 		= enemyUnits.get(0);
		GameBoardObject enemyTemp 	= UnitsProcessor.clone(enemy);
		int enemyRow 				= enemy.getRow();
		int enemyCol 				= enemy.getCol();
		int direction 				= moveRandomDirection();

		boolean isMoveLeftValid 	= ((enemyCol - 1) >= 0) && (gameBoard[enemyRow][enemyCol - 1] instanceof Ground);
		boolean isMoveRightValid 	= ((enemyCol + 1) <= GameConfig.cols) && (gameBoard[enemyRow][enemyCol + 1] instanceof Ground);
		boolean isMoveUpValid 		= ((enemyRow - 1) >= 0) && (gameBoard[enemyRow - 1][enemyCol] instanceof Ground);
		boolean isMoveDownValid 	= ((enemyRow + 1) <= GameConfig.rows) && (gameBoard[enemyRow + 1][enemyCol] instanceof Ground);


		//Move LEFT
		if(direction == 1 && isMoveLeftValid){
			enemy.setCol(enemyTemp.getCol() - 1);
			gameBoard[enemyRow][enemyTemp.getCol() - 1] = enemy;
			gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
			return;
		}
		//Move RIGHT
		if(direction == 2 && isMoveRightValid){
			enemy.setCol(enemyTemp.getCol() + 1);
			gameBoard[enemyRow][enemyTemp.getCol() + 1] = enemy;
			gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
			return;
		}
		//Move UP
		if(direction == 3 && isMoveUpValid){
			enemy.setRow(enemyTemp.getRow() - 1);
			gameBoard[enemyTemp.getRow() - 1][enemy.getCol()] = enemy;
			gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
			return;
		}
		//Move DOWN
		if(direction == 4 && isMoveDownValid){
			enemy.setRow(enemyTemp.getRow() + 1);
			gameBoard[enemyTemp.getRow() + 1][enemy.getCol()] = enemy;
			gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
			return;
		} 
		else{
			moveEnemy();
		}
	}

	private static int moveRandomDirection() {
		Random rand = new Random();
		int randDirection = rand.nextInt(4);
		randDirection += 1;

		return randDirection;
	}
	
	
}
