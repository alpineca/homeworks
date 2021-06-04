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
	private static GameBoardObject enemy;

	private GameBoardObject unitToMove;

	private static int enemyReloadCounter 	= 0;
	private static boolean isEnemyOnTheMap 	= true;

	public static boolean isEnemyOnTheMap() {
		return isEnemyOnTheMap;
	}
	public static void setEnemyOnTheMap(boolean isEnemyOnTheMap) {
		GameBoard.isEnemyOnTheMap = isEnemyOnTheMap;
	}
	public GameBoardObject getEnemy(){
		return this.enemy;
	}
	public static void setEnemy(GameBoardObject enemyy){
		enemy = enemyy;
	}

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
		EnemyProcessor.spawnEnemyUnits(	gameBoard);

		
	}
	
	public void makeMove() {
		if(((Petkan) enemy).getVisibility() == true){
			((Petkan) enemy).fire();
		}
		if(isEnemyOnTheMap() == true){
			EnemyProcessor.moveEnemy(gameBoard, enemy);
		}
		countdown();
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

	public static void reloadCounterReset() {
		if(enemyReloadCounter == 0 && isEnemyOnTheMap == true){
			enemyReloadCounter = 5;
			setEnemyOnTheMap(false);
		}
	}

	private void countdown(){
		if(enemyReloadCounter > 0 && isEnemyOnTheMap == false){
			enemyReloadCounter--;
		}
		if(enemyReloadCounter == 0 && isEnemyOnTheMap == false){
			setEnemyOnTheMap(true);
			((Petkan)enemy).goToCorner();
		}
	}
	
	private void keyActionProcessor(int keyCode) {
		
		if(keyCode == 'a' || keyCode == 'A') {
			System.out.println("A");
			UnitsProcessor.move(Direction.LEFT, gameBoard, armyUnits, buildings);
			this.makeMove();
		}
		if(keyCode == 'd' || keyCode == 'D') {
			System.out.println("D");
			UnitsProcessor.move(Direction.RIGHT, gameBoard, armyUnits, buildings);
			this.makeMove();
		}
		if(keyCode == 'w' || keyCode == 'W') {
			System.out.println("W");
			UnitsProcessor.move(Direction.UP, gameBoard, armyUnits, buildings);
			this.makeMove();
		}
		if(keyCode == 's' || keyCode == 'S') {
			System.out.println("S");
			UnitsProcessor.move(Direction.DOWN, gameBoard, armyUnits, buildings);
			this.makeMove();
		}
		if(keyCode == KeyEvent.VK_RIGHT && unitToMove != null)
		{
			UnitsProcessor.swapUnit(gameBoard, armyUnits, unitToMove);
			this.repaint();
		}
	}

	

	
	
}
