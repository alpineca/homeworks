package processors;

import java.util.ArrayList;
import GameBoardObjects.armyUnits.Spy;
import GameBoardObjects.armyUnits.Saboteur;
import GameBoardObjects.armyUnits.Sniperist;
import GameBoardObjects.armyUnits.Tank;
import GameBoardObjects.enemys.Petkan;
import GameBoardObjects.materials.Brick;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.parrents.ArmyUnit;
import GameBoardObjects.parrents.Enemy;
import GameBoardObjects.parrents.GameBoardObject;
import enums.ArmyUnitsEnum;
import enums.DirectionsEnum;
import game.GameBoard;
import interfaces.GameConfig;

public class UnitsProcessor {
		
	public static void spawnArmyUnits(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> armyUnits) {
		unitToSpawn(14, 11, 1, ArmyUnitsEnum.SPY, gameBoard, armyUnits);
		unitToSpawn(14, 12, 2, ArmyUnitsEnum.SNIPERIST, gameBoard, armyUnits);
		unitToSpawn(14, 13, 3, ArmyUnitsEnum.TANK, gameBoard, armyUnits);
		unitToSpawn(14, 14, 4, ArmyUnitsEnum.SABOTEUR, gameBoard, armyUnits);
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

	public static void move(DirectionsEnum direction, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> armyUnits, ArrayList<GameBoardObject> buildings) {

		GameBoardObject[] armyUnit 	= new GameBoardObject[armyUnits.size()];
		int[] armyUnitRow 			= new int[armyUnits.size()];
		int[] armyUnitCol 			= new int[armyUnits.size()];

		
		boolean isDrunkerOnboard = checkForDrunker();
		
		for(int i = 0; i < armyUnits.size(); i++){
			armyUnitRow[i] = armyUnits.get(i).getRow();
			armyUnitCol[i] = armyUnits.get(i).getCol();
			armyUnit[i]	   = armyUnits.get(i);
		}

		int destRow = armyUnitRow[0];
		int destCol = armyUnitCol[0];

		if(direction.equals(DirectionsEnum.LEFT)){
			destCol--;

			if(destCol < 0 && isDrunkerOnboard){
				destCol = GameConfig.COLS - 1;
			}
		}
		if(direction.equals(DirectionsEnum.RIGHT)){
			destCol++;
			if(destCol > 14 && isDrunkerOnboard){
				destCol = 0;
			}
		}
		if(direction.equals(DirectionsEnum.UP)){
			destRow--;
			if(destRow < 0 && isDrunkerOnboard){
				destRow = GameConfig.ROWS - 1;
			}
		}
		if(direction.equals(DirectionsEnum.DOWN)){
			destRow++;
			if(destRow > 14 && isDrunkerOnboard){
				destRow = 0;
			}
		}

		try {
			GameBoardObject destination = gameBoard[destRow][destCol];
	
			if(destination instanceof ArmyUnit) {
				return;
			}
			if(destination instanceof Enemy) {
				return;
			}
			if(destination instanceof Brick && ((Brick) destination).getPassability() == false) {
				return;
			}
			
		} catch (Exception e) {
			//TODO: handle exception
		}


		for(int i = 0; i < armyUnits.size(); i++){
			if(i == 0){
				gameBoard[destRow][destCol] = gameBoard[armyUnitRow[i]][armyUnitCol[i]];
			}
			if(i == armyUnits.size()-1){
				gameBoard[armyUnitRow[i]][armyUnitCol[i]] = teramorf(armyUnitRow[i], armyUnitCol[i], new Ground(armyUnitRow[i], armyUnitCol[i]));

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

	
	private static void unitToSpawn(int row, int col, int index, ArmyUnitsEnum unit, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> armyUnits) {
		GameBoardObject armyUnit = null;
		if(unit.equals(ArmyUnitsEnum.SPY)) armyUnit 		= new Spy(row, col, index);
		if(unit.equals(ArmyUnitsEnum.SNIPERIST)) armyUnit 	= new Sniperist(row, col, index);
		if(unit.equals(ArmyUnitsEnum.TANK)) armyUnit 		= new Tank(row, col, index);
		if(unit.equals(ArmyUnitsEnum.SABOTEUR)) armyUnit 	= new Saboteur(row, col, index);
		
		gameBoard[row][col] = armyUnit;
		armyUnits.add(gameBoard[row][col]);
	}
	
	private static boolean checkForDrunker(){
		ArrayList<GameBoardObject> armyUnits = GameBoard.getArmyUnits();
		for(int i = 0; i < armyUnits.size(); i++){
			if(armyUnits.get(i) instanceof Spy){
				return true;
			}
		}
		return false;
	}

	private static GameBoardObject teramorf(int row, int col, GameBoardObject newElement){
		for(GameBoardObject element : BuildingsProcessor.allBuildingsElements()){
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
		
		if(unitToClone instanceof Spy){
			return new Spy(row, col, index);
		}
		if(unitToClone instanceof Saboteur){
			return new Saboteur(row, col, index);
		}
		if(unitToClone instanceof Sniperist){
			return new Sniperist(row, col, index);
		}
		if(unitToClone instanceof Tank){
			return new Tank(row, col, index);
		}
		if(unitToClone instanceof Petkan){
			return new Petkan(row, col);
		}
		return null;
		
	}

	public static void specialSkill() {
		ArrayList<GameBoardObject> armyUnits = GameBoard.getArmyUnits();
		ArmyUnit armyLeader = (ArmyUnit)armyUnits.get(0);
		if(armyLeader.isSpecialSkill() == true){
			armyLeader.specialSkill();
		}
	}

}
