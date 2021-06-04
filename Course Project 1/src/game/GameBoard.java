package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import GameBoardObjects.materials.Building;
import GameBoardObjects.materials.Ground;
import enums.DirectionsEnum;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.enemys.Petkan;
import interfaces.GameConfig;
import processors.BuildingsProcessor;
import processors.EnemyProcessor;
import processors.UnitsProcessor;

public class GameBoard extends JPanel implements KeyListener{
	
	public static GameBoardObject[][] gameBoard;
	public static ArrayList<GameBoardObject> armyUnits 	= new ArrayList<>();
	public static ArrayList<GameBoardObject> buildings 		= new ArrayList<>();
	public static ArrayList<Building> smallBuilding 	= new ArrayList<>();
	public static ArrayList<Building> middleBuilding 	= new ArrayList<>();
	public static ArrayList<Building> largeBuilding 	= new ArrayList<>();
	
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
	public static void setEnemy(GameBoardObject enemySpawn) {
		enemy = enemySpawn;
	}
	public static GameBoardObject getEnemy(){
		return enemy;
	}
	public static ArrayList<Building> getSmallBuilding() {
		return smallBuilding;
	}
	public static ArrayList<Building> getMiddleBuilding() {
		return middleBuilding;
	}
	public static ArrayList<Building> getLargeBuilding() {
		return largeBuilding;
	}
	public static ArrayList<GameBoardObject> getArmyUnits() {
		return armyUnits;
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
		
		BuildingsProcessor.spawnBuildings(	gameBoard, buildings);
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
			UnitsProcessor.move(DirectionsEnum.LEFT, gameBoard, armyUnits, buildings);
			this.makeMove();
		}
		if(keyCode == 'd' || keyCode == 'D') {
			UnitsProcessor.move(DirectionsEnum.RIGHT, gameBoard, armyUnits, buildings);
			this.makeMove();
		}
		if(keyCode == 'w' || keyCode == 'W') {
			UnitsProcessor.move(DirectionsEnum.UP, gameBoard, armyUnits, buildings);
			this.makeMove();
		}
		if(keyCode == 's' || keyCode == 'S') {
			UnitsProcessor.move(DirectionsEnum.DOWN, gameBoard, armyUnits, buildings);
			this.makeMove();
		}
		if(keyCode == 'f' || keyCode == 'F') {
			UnitsProcessor.specialSkill();
			this.makeMove();
		}
		if(keyCode == KeyEvent.VK_RIGHT && unitToMove != null)
		{
			UnitsProcessor.swapUnit(gameBoard, armyUnits, unitToMove);
			this.repaint();
		}
	}
	

	

	
	
}
