package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import GameBoardObjects.buildings.LargeBuilding;
import GameBoardObjects.buildings.MiddleBuilding;
import GameBoardObjects.buildings.SmallBuilding;
import GameBoardObjects.enemys.Petkan;
import GameBoardObjects.materials.BlownBuilding;
import GameBoardObjects.materials.Column;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.parrents.ArmyUnit;
import GameBoardObjects.parrents.GameBoardObject;
import enums.DirectionsEnum;
import interfaces.GameConfig;
import processors.BuildingsProcessor;
import processors.EnemyProcessor;
import processors.UnitsProcessor;

public class GameBoard extends JPanel implements KeyListener{
	
	public static GameBoardObject[][] gameBoard;
	public static ArrayList<GameBoardObject> armyUnits 	= new ArrayList<>();
	public static ArrayList<GameBoardObject> buildings 		= new ArrayList<>();
	public static ArrayList<GameBoardObject> smallBuilding 	= new ArrayList<>();
	public static ArrayList<GameBoardObject> middleBuilding 	= new ArrayList<>();
	public static ArrayList<GameBoardObject> largeBuilding 	= new ArrayList<>();
	
	private static GameBoardObject enemy;
	private GameBoardObject unitToMove;

	private static int enemyReloadCounter 	= 0;
	private static int buildingBombCounter 	= 0;

	private static boolean isEnemyOnTheMap 	= true;
	private static boolean isBombPlantedOnBuilding 	= false;

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
	public static ArrayList<GameBoardObject> getSmallBuilding() {
		return smallBuilding;
	}
	public static ArrayList<GameBoardObject> getMiddleBuilding() {
		return middleBuilding;
	}
	public static ArrayList<GameBoardObject> getLargeBuilding() {
		return largeBuilding;
	}
	public static ArrayList<GameBoardObject> getBuildings() {
		return buildings;
	}
	public static ArrayList<GameBoardObject> getArmyUnits() {
		return armyUnits;
	}
	
	public static GameBoardObject[][] getGameBoard() {
		return gameBoard;
	}


	public GameBoard() {
		this.bootstrap();
	}
	
	private void bootstrap() {
		
		gameBoard = new GameBoardObject[GameConfig.ROWS][GameConfig.COLS];
		
		for(int row = 0; row < GameConfig.ROWS; row++) {
			for(int col = 0; col < GameConfig.COLS; col++) {
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
		this.counters();
		this.unitToMove = null;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		System.out.println("Paint");
		for(int row = 0; row < GameConfig.ROWS; row++) {
			for(int col = 0; col < GameConfig.COLS; col++) {
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

	public static void bombPlantedCounterReset() {
		if(enemyReloadCounter == 0 && isBombPlantedOnBuilding == true){
			enemyReloadCounter = 7;
			setEnemyOnTheMap(false);
		}
	}

	public static void plantBomb() {
		isBombPlantedOnBuilding 		= true;
		buildingBombCounter = 6;

	}
	
	private void bombExplode(){
		ArrayList<GameBoardObject> smallBuildingElements 	= SmallBuilding.getBuildingElements();
		ArrayList<GameBoardObject> middleBuildingElements 	= MiddleBuilding.getBuildingElements();
		ArrayList<GameBoardObject> largeBuildingElements 	= LargeBuilding.getBuildingElements();

		for(GameBoardObject element : smallBuildingElements){
			try {
				boolean isBombPlanted = ((Column) element).isBombPlanted;

				if(isBombPlanted){
					killNearUnits(element.getRow(), element.getCol());
					SmallBuilding.explodeThisColumn(element, gameBoard);
				}
			} catch (Exception e) {
				//TODO: handle exception
			}
			
		}
		for(GameBoardObject element : middleBuildingElements){

			try {

				boolean isBombPlanted = ((Column) element).isBombPlanted;

				if(isBombPlanted){
					killNearUnits(element.getRow(), element.getCol());
					MiddleBuilding.explodeThisColumn(element, gameBoard);
				}
				
			} catch (Exception e) {
				//TODO: handle exception
			}

			
		}
		for(GameBoardObject element : largeBuildingElements){

			try {

				boolean isBombPlanted = ((Column) element).isBombPlanted;

				if(isBombPlanted){
					killNearUnits(element.getRow(), element.getCol());
					LargeBuilding.explodeThisColumn(element, gameBoard);
				}
				
			} catch (Exception e) {
				//TODO: handle exception
			}

			
		}


		this.repaint();

		

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
	
	private void countdownEnemyRespawn(){
		if(enemyReloadCounter > 0 && isEnemyOnTheMap == false){
			enemyReloadCounter--;
		}
		if(enemyReloadCounter == 0 && isEnemyOnTheMap == false){
			setEnemyOnTheMap(true);
			((Petkan)enemy).goToCorner();
		}
	}

	private void countdownBombExplode(){
		if(buildingBombCounter > 0 && isBombPlantedOnBuilding == true){
			buildingBombCounter--;
		}
		if(buildingBombCounter == 0 && isBombPlantedOnBuilding == true){
			bombExplode();
			isBombPlantedOnBuilding = false;
		}
	}

	private void counters(){
		this.countdownEnemyRespawn();
		this.countdownBombExplode();
	}

	private void killNearUnits(int row, int col){
		int minRowBound 		= row-1;
		int maxRowBound 		= row+1;

		int minColBound 		= col-1;
		int maxColBound 		= col+1;

		int unitToKillRow 		= -1;
		int unitToKillCol 		= -1;
		boolean isUnitToKill 	= false;

		for(int i = minRowBound; i <= maxRowBound; i++){
			for(int j = minColBound; j <= maxColBound; j++){
				try {
					if(gameBoard[i][j] instanceof ArmyUnit){
						unitToKillRow 	= i;
						unitToKillCol 	= j;
						isUnitToKill 	= true;
						break;
					} 
				} catch (Exception e) {

				}					
			}
		}

		if(unitToKillRow > -1 && unitToKillCol > -1 && isUnitToKill == true){
			for(GameBoardObject unit : armyUnits){
				try {
					if(unit.getRow() == unitToKillRow && unit.getCol() == unitToKillCol){
						armyUnits.remove(unit);
						gameBoard[unitToKillRow][unitToKillCol] = new Ground(unitToKillRow, unitToKillCol);
						this.repaint();
					}
				} catch (Exception e) {

				}			
			}
		}
	}
	
	

	

	
	
}
