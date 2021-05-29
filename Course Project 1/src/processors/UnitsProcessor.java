package processors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.lang.model.element.Element;

import GameBoardObjects.ArmyUnit;
import GameBoardObjects.Enemy;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.armyUnits.Drunker;
import GameBoardObjects.armyUnits.Fisherman;
import GameBoardObjects.armyUnits.RockTrower;
import GameBoardObjects.armyUnits.Tracktorist;
import GameBoardObjects.enemys.Petkan;
import GameBoardObjects.materials.Ground;
import enums.Direction;
import interfaces.GameConfig;

public class UnitsProcessor {
		
	public static void spawnArmyUnits(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> armyUnits) {
		gameBoard[14][11] = new Tracktorist(14, 11, 1);
		armyUnits.add(gameBoard[14][11]);
		
		gameBoard[14][12] = new RockTrower(14, 12, 2);
		armyUnits.add(gameBoard[14][12]);
		
		gameBoard[14][13] = new Drunker(14, 13, 3);
		armyUnits.add(gameBoard[14][13]);
		
		gameBoard[14][14] = new Fisherman(14, 14, 4);
		armyUnits.add(gameBoard[14][14]);
	}
	public static void spawnEnemyUnits(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> enemyUnits) {
		boolean isEmptyField = false;
		int row, col;

		while(isEmptyField == false){
			row = rand(GameConfig.rows);
			col = rand(GameConfig.cols);

			if(gameBoard[row][col] instanceof Ground){
				isEmptyField = true;
				GameBoardObject unit = new Petkan(row, col, Color.WHITE);
				gameBoard[row][col] = unit;
				enemyUnits.add(unit);

				try {
					for(int i = row -1; i < row + 2; i++){
						for(int j = col -1; j < col + 2; j++){
							if((i != row && j != col) && (gameBoard[i][j] instanceof Ground)){
								Ground element = (Ground) gameBoard[i][j];
								element.trigger();
							}
						}
					}
				} catch (Exception e) {
					spawnEnemyUnits(gameBoard, enemyUnits);
				}

			}
		}

	}

	public static void move(Direction direction, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> armyUnits, ArrayList<GameBoardObject> buildings, ArrayList<GameBoardObject> enemyUnits) {

		GameBoardObject[] armyUnit 	= new GameBoardObject[armyUnits.size()];
		int[] armyUnitRow 			= new int[armyUnits.size()];
		int[] armyUnitCol 			= new int[armyUnits.size()];

		
		boolean isDrunkerOnboard = checkForDrunker(armyUnits);
		
		for(int i = 0; i < armyUnits.size(); i++){
			armyUnitRow[i] = armyUnits.get(i).getRow();
			armyUnitCol[i] = armyUnits.get(i).getCol();
			armyUnit[i]	   = armyUnits.get(i);
		}

		int destRow = armyUnitRow[0];
		int destCol = armyUnitCol[0];

		if(direction.equals(Direction.LEFT)){
			destCol--;

			if(destCol < 0 && isDrunkerOnboard){
				destCol = GameConfig.cols - 1;
			}
		}
		if(direction.equals(Direction.RIGHT)){
			destCol++;
			if(destCol > 14 && isDrunkerOnboard){
				destCol = 0;
			}
		}
		if(direction.equals(Direction.UP)){
			destRow--;
			if(destRow < 0 && isDrunkerOnboard){
				destRow = GameConfig.rows - 1;
			}
		}
		if(direction.equals(Direction.DOWN)){
			destRow++;
			if(destRow > 14 && isDrunkerOnboard){
				destRow = 0;
			}
		}

		if(gameBoard[destRow][destCol] instanceof ArmyUnit) {
			return;
		}
		if(gameBoard[destRow][destCol] instanceof Enemy) {
			return;
		}
		if((gameBoard[destRow][destCol] instanceof Ground)) {
			Ground destTile =  (Ground) gameBoard[destRow][destCol];
			if(destTile.getTrigger() == true){
				Petkan enemy = (Petkan) enemyUnits.get(0);
				enemy.trigger();
			}
		}

		for(int i = 0; i < armyUnits.size(); i++){
			if(i == 0){
				gameBoard[destRow][destCol] = gameBoard[armyUnitRow[i]][armyUnitCol[i]];
			}
			if(i == armyUnits.size()-1){
				gameBoard[armyUnitRow[i]][armyUnitCol[i]] = teramorf(armyUnitRow[i], armyUnitCol[i], new Ground(armyUnitRow[i], armyUnitCol[i]), buildings);

			}else{
				gameBoard[armyUnitRow[i]][armyUnitCol[i]] = gameBoard[armyUnitRow[i+1]][armyUnitCol[i+1]];
			}

		}
		for(int i = armyUnits.size() -1; i >= 0; i--){
			if(i==0){
				armyUnit[i].setRowCol(destRow, destCol);
			}else{
				armyUnit[i].setRowCol(armyUnitRow[i-1], armyUnitCol[i-1]);
			}
		}

		for(int i = 0; i< armyUnits.size(); i++){
			armyUnits.set(i, armyUnit[i]);
		}

	}
	
	private static boolean checkForDrunker(ArrayList<GameBoardObject> armyUnits){
		
		for(int i = 0; i < armyUnits.size(); i++){
			if(armyUnits.get(i) instanceof Drunker){
				return true;
			}
		}
		return false;
	}

	private static GameBoardObject teramorf(int row, int col, GameBoardObject newElement, ArrayList<GameBoardObject> buildings){
		for(GameBoardObject element : buildings){
			if(element.getRow() == row && element.getCol() == col){
				return element;
			}
		}
		return newElement;
	}

	private static int rand(int randBound) {
		Random rand = new Random();
		return rand.nextInt(randBound);
		
	}


}
