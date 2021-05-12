package com.netitjava.managers;

import java.util.Scanner;

import com.netitjava.gameboard.*;
import com.netitjava.gameboard.pieces.*;
import com.netitjava.gameboard.tiles.GameBoardTile;

public class MoveManager {
	
	public static void moveThisPiece(Piece selectedPiece, String pieceMoveDirection) {
		int currentRow 				= selectedPiece.getRow();
		int currentCol 				= selectedPiece.getCol();
		
		int destinationRow = currentRow;
		int destinationCol = currentCol;
		
		pieceMoveDirection 			= pieceMoveDirection.toLowerCase();
		
		boolean upOrDown 			= (pieceMoveDirection.equals("w") ||
									   pieceMoveDirection.equals("s"));
		boolean leftOrRight 		= (pieceMoveDirection.equals("a") ||
									   pieceMoveDirection.equals("d"));		
		if(upOrDown) {
			destinationRow 		 	= newCoordinatesRow(currentRow, pieceMoveDirection);
		} 
		else if(leftOrRight) {
			destinationCol 			= newCoordinatesCol(currentCol, pieceMoveDirection);
		}
		
		if(destinationRow > GameManager.ROW_COUNT - 1 || destinationRow < 0) 						return;
		if(destinationCol > GameManager.COL_COUNT - 1 || destinationCol < 0) 						return;
		
		if(GameBoard.getInstance().getElement(destinationRow, destinationCol) instanceof Piece) 	return;
		if(GameBoard.getInstance().getElement(destinationRow, destinationCol) instanceof Obstacle) 	return;
		
		
		try {
			
			selectedPiece.move(destinationRow, destinationCol);
			GameBoard.getInstance().setElement(destinationRow, destinationCol, selectedPiece);
			GameBoard.getInstance().setElement(currentRow, currentCol, new GameBoardTile(currentRow, currentCol, null));
			
		}
		catch(Exception e){
			
		}
		
	}

	public static String moveDirectionChar(String string) {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	private static int newCoordinatesRow(int currentRow, String pieceMoveDirection) {
		
		if(pieceMoveDirection.equals("w")) {
			currentRow = currentRow - 1;
			return currentRow;
		}
		if(pieceMoveDirection.equals("s")){
			currentRow = currentRow + 1;
			return currentRow;
		}
		
		return currentRow;
	}
	private static int newCoordinatesCol(int currentCol, String pieceMoveDirection) {
		if(pieceMoveDirection.equals("a")) {
			currentCol = currentCol - 1;
			return currentCol;
		}
		if(pieceMoveDirection.equals("d")) {
			currentCol = currentCol + 1;
			return currentCol;
		}
		
		return currentCol;
	}
	
	private static void clearTile(int row, int col) {

		GameBoard.getInstance().setElement(row, col, new GameBoardTile(row, col, null));
		
	}

}
