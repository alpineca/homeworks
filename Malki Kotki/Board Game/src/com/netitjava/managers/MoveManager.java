package com.netitjava.managers;

import java.util.Scanner;

import com.netitjava.gameboard.GameBoard;
import com.netitjava.gameboard.GameBoardObject;
import com.netitjava.gameboard.pieces.Piece;

public class MoveManager {
	
	public static boolean moveThisPiece(Piece selectedPiece, String pieceMoveDirection) {
		int currentRow 				= selectedPiece.getRow();
		int currentCol 				= selectedPiece.getCol();
		
		pieceMoveDirection 			= pieceMoveDirection.toLowerCase();
		
		boolean upOrDown 			= (pieceMoveDirection.equals("w") ||
									   pieceMoveDirection.equals("s"));
		boolean leftOrRight 		= (pieceMoveDirection.equals("a") ||
									   pieceMoveDirection.equals("d"));		
		if(upOrDown) {
			currentRow 		 	= newCoordinatesRow(currentRow, pieceMoveDirection);
		} 
		else if(leftOrRight) {
			currentCol 			= newCoordinatesRow(currentRow, pieceMoveDirection);
		}
		
		
		try {
			
			GameBoard.getInstance().setElement(currentRow, currentCol, selectedPiece);
			
			selectedPiece.setRow(currentRow);
			selectedPiece.setCol(currentCol);
			return true;
			
		}
		catch(Exception e){
			return false;
		}
		
		
		
		//return false;
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

}
