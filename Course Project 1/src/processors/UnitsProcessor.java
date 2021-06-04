package processors;

import java.util.ArrayList;
import java.util.Random;

import GameBoardObjects.ArmyUnit;
import GameBoardObjects.Enemy;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.armyUnits.Drunker;
import GameBoardObjects.armyUnits.Fisherman;
import GameBoardObjects.armyUnits.RockTrower;
import GameBoardObjects.armyUnits.Tracktorist;
import GameBoardObjects.enemys.Petkan;
import GameBoardObjects.materials.Building;
import GameBoardObjects.materials.Ground;
import enums.Direction;
import interfaces.GameConfig;

public class UnitsProcessor {
		
	public static void spawnArmyUnits(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> armyUnits) {
		gameBoard[14][11] = new Drunker(14, 11, 1);
		armyUnits.add(gameBoard[14][11]);
		
		gameBoard[14][12] = new RockTrower(14, 12, 2);
		armyUnits.add(gameBoard[14][12]);
		
		gameBoard[14][13] = new Tracktorist(14, 13, 3);
		armyUnits.add(gameBoard[14][13]);
		
		gameBoard[14][14] = new Fisherman(14, 14, 4);
		armyUnits.add(gameBoard[14][14]);
	}

	public static void swapUnit(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> armyUnits, GameBoardObject unitToMove) {
		int unitIndex = 0;

		while(unitIndex < armyUnits.size()){
			GameBoardObject currentUnit = armyUnits.get(unitIndex);
			if(unitToMove.equals(currentUnit)){
				break;
			}
			unitIndex++;
		}

		if(unitIndex < (armyUnits.size() - 1)){
			int nextUnitPos 				= unitIndex + 1;

			GameBoardObject unitToMoveTemp 	= clone(armyUnits.get(unitIndex));
			GameBoardObject nextUnit 		= clone(armyUnits.get(nextUnitPos));

			int nextUnitRow 				= nextUnit.getRow();
			int nextUnitCol 				= nextUnit.getCol();

			int unitToMoveTempRow 			= unitToMoveTemp.getRow();
			int unitToMoveTempCol 			= unitToMoveTemp.getCol();

			unitToMove.setRow(nextUnitRow);
			unitToMove.setCol(nextUnitCol);

			nextUnit.setRow(unitToMoveTempRow);
			nextUnit.setCol(unitToMoveTempCol);

			armyUnits.set(nextUnitPos, unitToMove);
			armyUnits.set(unitIndex, nextUnit);
		}

		for(int i = 0; i < armyUnits.size(); i++){
			GameBoardObject unit = armyUnits.get(i);
			((ArmyUnit)unit).setIndex(i + 1);
			int row = unit.getRow();
			int col = unit.getCol();

			gameBoard[row][col] = unit;

		}
			
	}

	public static void move(Direction direction, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> armyUnits, ArrayList<GameBoardObject> buildings) {

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

		GameBoardObject destination = gameBoard[destRow][destCol];

		if(destination instanceof ArmyUnit) {
			return;
		}
		if(destination instanceof Enemy) {
			return;
		}
		if(destination instanceof Building && ((Building) destination).getPassability() == false) {
			return;
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

	
	public static GameBoardObject clone(GameBoardObject unitToClone){
		int row = unitToClone.getRow();
		int col = unitToClone.getCol();
		int index = unitToClone.getIndex();
		
		if(unitToClone instanceof Drunker){
			return new Drunker(row, col, index);
		}
		if(unitToClone instanceof Fisherman){
			return new Fisherman(row, col, index);
		}
		if(unitToClone instanceof RockTrower){
			return new RockTrower(row, col, index);
		}
		if(unitToClone instanceof Tracktorist){
			return new Tracktorist(row, col, index);
		}
		if(unitToClone instanceof Petkan){
			return new Petkan(row, col);
		}
		return null;
		
	}
	
	private static int rand(int randBound) {
		Random rand = new Random();
		return rand.nextInt(randBound);
		
	}

}
