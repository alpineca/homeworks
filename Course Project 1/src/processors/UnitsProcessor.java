package processors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import GameBoardObjects.ArmyUnit;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.armyUnits.Drunker;
import GameBoardObjects.armyUnits.Fisherman;
import GameBoardObjects.armyUnits.RockTrower;
import GameBoardObjects.armyUnits.Tracktorist;
import GameBoardObjects.enemys.Petkan;
import GameBoardObjects.materials.Building;
import GameBoardObjects.materials.Ground;
import interfaces.GameConfig;

public class UnitsProcessor {
	
	private static ArrayList<GameBoardObject> overBuilding = new ArrayList<>();
	
	
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
	public static void spawnEnemyUnits(GameBoardObject[][] gameBoard) {
		boolean isEmptyField = false;
		int row, col;

		while(isEmptyField == false){
			row = rand(GameConfig.rows);
			col = rand(GameConfig.cols);

			if(gameBoard[row][col] instanceof Ground){
				isEmptyField = true;
				gameBoard[row][col] = new Petkan(row, col, Color.WHITE);
			}
		}

	}

	public static void moveLeft(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
		GameBoardObject armyFirst 	= armyUnits.get(0);
		GameBoardObject armySeccond	= armyUnits.get(1);
		GameBoardObject armyThird 	= armyUnits.get(2);
		GameBoardObject armyForth 	= armyUnits.get(3);

		boolean isDrunkerOnboard = checkForDrunker(armyUnits);
		
		int armyFirstRow = armyFirst.getRow();
		int armyFirstCol = armyFirst.getCol();
		
		int armySeccondRow = armySeccond.getRow();
		int armySeccondCol = armySeccond.getCol();
		
		int armyThirdRow = armyThird.getRow();
		int armyThirdCol = armyThird.getCol();
		
		int armyForthRow = armyForth.getRow();
		int armyForthCol = armyForth.getCol();
		
		int destRow = armyFirstRow;
		int destCol = armyFirstCol - 1;
		
		//First
		if(destCol < 0 && isDrunkerOnboard){
			destCol = GameConfig.cols - 1;
		}
		if(gameBoard[destRow][destCol] instanceof ArmyUnit) {
			return;
		}

		gameBoard[destRow][destCol] = gameBoard[armyFirstRow][armyFirstCol];
		gameBoard[armyFirstRow][armyFirstCol] = gameBoard[armySeccondRow][armySeccondCol];
		gameBoard[armySeccondRow][armySeccondCol] = gameBoard[armyThirdRow][armyThirdCol];
		gameBoard[armyThirdRow][armyThirdCol] = gameBoard[armyForthRow][armyForthCol];
		
		gameBoard[armyForthRow][armyForthCol] = teramorf(armyForthRow, armyForthCol, new Ground(armyForthRow, armyForthCol), buildings);
		
		armyForth.setRowCol(armyThirdRow, armyThirdCol);
		armyThird.setRowCol(armySeccondRow, armySeccondCol);
		armySeccond.setRowCol(armyFirstRow, armyFirstCol);
		armyFirst.setRowCol(destRow, destCol);
		
		armyUnits.set(0, armyFirst);
		armyUnits.set(1, armySeccond);
		armyUnits.set(2, armyThird);
		armyUnits.set(3, armyForth);

	}
	
	public static void moveRight(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
		GameBoardObject armyFirst 	= armyUnits.get(0);
		GameBoardObject armySeccond	= armyUnits.get(1);
		GameBoardObject armyThird 	= armyUnits.get(2);
		GameBoardObject armyForth 	= armyUnits.get(3);

		boolean isDrunkerOnboard = checkForDrunker(armyUnits);
		
		int armyFirstRow = armyFirst.getRow();
		int armyFirstCol = armyFirst.getCol();
		
		int armySeccondRow = armySeccond.getRow();
		int armySeccondCol = armySeccond.getCol();
		
		int armyThirdRow = armyThird.getRow();
		int armyThirdCol = armyThird.getCol();
		
		int armyForthRow = armyForth.getRow();
		int armyForthCol = armyForth.getCol();
		
		int destRow = armyFirstRow;
		int destCol = armyFirstCol + 1;
		
		//First
		if(destCol > (GameConfig.cols - 1) && isDrunkerOnboard){
			destCol = 0;
		}

		if(gameBoard[destRow][destCol] instanceof ArmyUnit) {
			return;
		}
		gameBoard[destRow][destCol] = gameBoard[armyFirstRow][armyFirstCol];
		gameBoard[armyFirstRow][armyFirstCol] = gameBoard[armySeccondRow][armySeccondCol];
		gameBoard[armySeccondRow][armySeccondCol] = gameBoard[armyThirdRow][armyThirdCol];
		gameBoard[armyThirdRow][armyThirdCol] = gameBoard[armyForthRow][armyForthCol];
		gameBoard[armyForthRow][armyForthCol] = teramorf(armyForthRow, armyForthCol, new Ground(armyForthRow, armyForthCol), buildings);
		
		armyForth.setRowCol(armyThirdRow, armyThirdCol);
		armyThird.setRowCol(armySeccondRow, armySeccondCol);
		armySeccond.setRowCol(armyFirstRow, armyFirstCol);
		armyFirst.setRowCol(destRow, destCol);
		
		armyUnits.set(0, armyFirst);
		armyUnits.set(1, armySeccond);
		armyUnits.set(2, armyThird);
		armyUnits.set(3, armyForth);
		
		
	}
	
	public static void moveUp(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
		GameBoardObject armyFirst 	= armyUnits.get(0);
		GameBoardObject armySeccond	= armyUnits.get(1);
		GameBoardObject armyThird 	= armyUnits.get(2);
		GameBoardObject armyForth 	= armyUnits.get(3);

		boolean isDrunkerOnboard = checkForDrunker(armyUnits);
		
		int armyFirstRow = armyFirst.getRow();
		int armyFirstCol = armyFirst.getCol();
		
		int armySeccondRow = armySeccond.getRow();
		int armySeccondCol = armySeccond.getCol();
		
		int armyThirdRow = armyThird.getRow();
		int armyThirdCol = armyThird.getCol();
		
		int armyForthRow = armyForth.getRow();
		int armyForthCol = armyForth.getCol();
		
		int destRow = armyFirstRow - 1;
		int destCol = armyFirstCol;
		
		//First
		if(destRow < 0 && isDrunkerOnboard){
			destRow = GameConfig.rows - 1;
		}

		if(gameBoard[destRow][destCol] instanceof ArmyUnit) {
			return;
		}
		gameBoard[destRow][destCol] = gameBoard[armyFirstRow][armyFirstCol];
		gameBoard[armyFirstRow][armyFirstCol] = gameBoard[armySeccondRow][armySeccondCol];
		gameBoard[armySeccondRow][armySeccondCol] = gameBoard[armyThirdRow][armyThirdCol];
		gameBoard[armyThirdRow][armyThirdCol] = gameBoard[armyForthRow][armyForthCol];
		gameBoard[armyForthRow][armyForthCol] = teramorf(armyForthRow, armyForthCol, new Ground(armyForthRow, armyForthCol), buildings);
		
		armyForth.setRowCol(armyThirdRow, armyThirdCol);
		armyThird.setRowCol(armySeccondRow, armySeccondCol);
		armySeccond.setRowCol(armyFirstRow, armyFirstCol);
		armyFirst.setRowCol(destRow, destCol);
		
		armyUnits.set(0, armyFirst);
		armyUnits.set(1, armySeccond);
		armyUnits.set(2, armyThird);
		armyUnits.set(3, armyForth);
		
		
	}
	
	public static void moveDown(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
		GameBoardObject armyFirst 	= armyUnits.get(0);
		GameBoardObject armySeccond	= armyUnits.get(1);
		GameBoardObject armyThird 	= armyUnits.get(2);
		GameBoardObject armyForth 	= armyUnits.get(3);

		boolean isDrunkerOnboard = checkForDrunker(armyUnits);
	
		int armyFirstRow = armyFirst.getRow();
		int armyFirstCol = armyFirst.getCol();
		
		int armySeccondRow = armySeccond.getRow();
		int armySeccondCol = armySeccond.getCol();
		
		int armyThirdRow = armyThird.getRow();
		int armyThirdCol = armyThird.getCol();
		
		int armyForthRow = armyForth.getRow();
		int armyForthCol = armyForth.getCol();
		
		int destRow = armyFirstRow + 1;
		int destCol = armyFirstCol;
		
		GameBoardObject afterUnits  = new Ground(armyForthRow, armyForthCol);
		
		//First
		if(destRow > (GameConfig.rows - 1) && isDrunkerOnboard){
			destRow = 0;
		}

		if(gameBoard[destRow][destCol] instanceof ArmyUnit) {
			return;
		}

		gameBoard[destRow][destCol] = gameBoard[armyFirstRow][armyFirstCol];
		gameBoard[armyFirstRow][armyFirstCol] = gameBoard[armySeccondRow][armySeccondCol];
		gameBoard[armySeccondRow][armySeccondCol] = gameBoard[armyThirdRow][armyThirdCol];
		gameBoard[armyThirdRow][armyThirdCol] = gameBoard[armyForthRow][armyForthCol];
		gameBoard[armyForthRow][armyForthCol] = teramorf(armyForthRow, armyForthCol, new Ground(armyForthRow, armyForthCol), buildings);
		
		armyForth.setRowCol(armyThirdRow, armyThirdCol);
		armyThird.setRowCol(armySeccondRow, armySeccondCol);
		armySeccond.setRowCol(armyFirstRow, armyFirstCol);
		armyFirst.setRowCol(destRow, destCol);
		
		armyUnits.set(0, armyFirst);
		armyUnits.set(1, armySeccond);
		armyUnits.set(2, armyThird);
		armyUnits.set(3, armyForth);
		
		
	
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
