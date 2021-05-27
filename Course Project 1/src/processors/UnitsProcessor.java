package processors;

import java.awt.Color;
import java.util.ArrayList;

import GameBoardObjects.ArmyUnit;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.armyUnits.Drunker;
import GameBoardObjects.armyUnits.Fisherman;
import GameBoardObjects.armyUnits.RockTrower;
import GameBoardObjects.armyUnits.Tracktorist;
import GameBoardObjects.materials.Ground;
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
	
	public static void focus(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard) {
		for(int i = 0; i < armyUnits.size(); i++) {
			GameBoardObject unit = armyUnits.get(i);
			if(!(unit.getColor().equals(Color.RED))) {
				unit.setColor(Color.RED);
				gameBoard[unit.getRow()][unit.getCol()].setColor(Color.RED);
				armyUnits.set(i, unit);
			}
			else if(unit.getColor().equals(Color.RED)){
				unit.setColor(Color.GREEN);
				gameBoard[unit.getRow()][unit.getCol()].setColor(Color.GREEN);
				armyUnits.set(i, unit);
			}
		}
	}
	
	public static void moveLeft(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard) {
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
		gameBoard[armyForthRow][armyForthCol] = new Ground(armyForthRow, armyForthCol);
		
		armyForth.setRowCol(armyThirdRow, armyThirdCol);
		armyThird.setRowCol(armySeccondRow, armySeccondCol);
		armySeccond.setRowCol(armyFirstRow, armyFirstCol);
		armyFirst.setRowCol(destRow, destCol);
		
		armyUnits.set(0, armyFirst);
		armyUnits.set(1, armySeccond);
		armyUnits.set(2, armyThird);
		armyUnits.set(3, armyForth);

	}
	
	public static void moveRight(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard) {
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
		gameBoard[armyForthRow][armyForthCol] = new Ground(armyForthRow, armyForthCol);
		
		armyForth.setRowCol(armyThirdRow, armyThirdCol);
		armyThird.setRowCol(armySeccondRow, armySeccondCol);
		armySeccond.setRowCol(armyFirstRow, armyFirstCol);
		armyFirst.setRowCol(destRow, destCol);
		
		armyUnits.set(0, armyFirst);
		armyUnits.set(1, armySeccond);
		armyUnits.set(2, armyThird);
		armyUnits.set(3, armyForth);
		
		
	}
	
	public static void moveUp(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard) {
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
		gameBoard[armyForthRow][armyForthCol] = new Ground(armyForthRow, armyForthCol);
		
		armyForth.setRowCol(armyThirdRow, armyThirdCol);
		armyThird.setRowCol(armySeccondRow, armySeccondCol);
		armySeccond.setRowCol(armyFirstRow, armyFirstCol);
		armyFirst.setRowCol(destRow, destCol);
		
		armyUnits.set(0, armyFirst);
		armyUnits.set(1, armySeccond);
		armyUnits.set(2, armyThird);
		armyUnits.set(3, armyForth);
		
		
	}
	
	public static void moveDown(ArrayList<GameBoardObject> armyUnits, GameBoardObject[][] gameBoard) {
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
		gameBoard[armyForthRow][armyForthCol] = afterUnits;
		
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

}
