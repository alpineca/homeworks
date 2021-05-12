package com.netitjava.managers;

import java.util.ArrayList;

import com.netitjava.enums.*;
import com.netitjava.exceptions.*;
import com.netitjava.gameboard.*;
import com.netitjava.gameboard.pieces.*;
import com.netitjava.gameboard.tiles.GameBoardTile;
import com.netitjava.util.Console;

public class PlayerManager {

	private GameColorEnum color;
	private int score;
	private int distance;
	private int movesLeft;
	private ArrayList<Piece> unplacedPieceCollection;
	private ArrayList<Piece> pieceCollection;
	
	public PlayerManager(GameColorEnum color) {

		this.color		= color;
		this.distance 	= 0;
		this.movesLeft 	= 0;
		
		this.pieceCollection 			= new ArrayList<>();
		this.unplacedPieceCollection 	= new ArrayList<>();
//		this.unplacedPieceCollection.add(new Knight(-1, -1));
//      this.unplacedPieceCollection.add(new Knight(-1, -1));
//		this.unplacedPieceCollection.add(new Dworf(-1, -1));
//		this.unplacedPieceCollection.add(new Dworf(-1, -1));
//		this.unplacedPieceCollection.add(new Elf(-1, -1));
		this.unplacedPieceCollection.add(new Elf(-1, -1));				
	}
	
	
	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public int getMovesLeft() {
		return movesLeft;
	}


	public void setMovesLeft(int movesLeft) {
		this.movesLeft = movesLeft;
	}


	public String getName() {

		if(this.color == GameColorEnum.BLACK	) return "@Black";
		if(this.color == GameColorEnum.RED		) return "@Red";
		return "Colourless";
	}

	public GameColorEnum getColor() {
		return this.color;
	}
	
	public ArrayList<Piece> getUnplacedPieceCollection() {
		return this.unplacedPieceCollection;
	}
	
	public ArrayList<Piece> getAlivePieceCollection() {
		return this.pieceCollection;
	}
	 
	public void place(int pieceIndex, GameBoardTile tile) 
			throws PlacementNotCorrectException, PlacementNotPosibleException {
		
		if(tile == null) {
			throw new PlacementNotPosibleException();
		}
		
		int row = tile.getRow();
		int col = tile.getCol();
		boolean isPlacementCorrect = tile.getColor() == this.color;
			
		if(!isPlacementCorrect) {
			throw new PlacementNotCorrectException();
		}
				
		Piece unplacedPiece = this.unplacedPieceCollection.get(pieceIndex);
		
		if(isPlacementPosible(row, col)) {

			unplacedPiece.move(row, col);
			GameBoard.getInstance().setElement(row, col, unplacedPiece);
			this.pieceCollection.add(unplacedPiece);
			this.unplacedPieceCollection.remove(pieceIndex);
		}
		
	}
	
	public void moveSelectedPiece(int pieceIndex) {
		Piece selectedPiece 	= this.pieceCollection.get(pieceIndex);
		this.distance 			= selectedPiece.getDistance();
		this.movesLeft 			= selectedPiece.getSpeed();
		
		
		while(this.movesLeft > 0) {
			
			String pieceMoveDirection 	= Console.promtString("Select direction (W,S,A,D) to move:");
			MoveManager.moveThisPiece(selectedPiece, pieceMoveDirection);
			this.movesLeft--;
			
			if(this.movesLeft == 0) break;
			
			GameBoard.getInstance().render();
			Console.logLn("You have " + this.movesLeft + " moves left");
			
			Console.logLn("Please Choose:");
			Console.logLn("[1] Move again");
			Console.logLn("[2] End turn");
			int menuChoice 	= Console.promtInt();
			if(menuChoice == 2) break;
		}
				
		
	}
	
	
	
	public boolean hasUnplacedPieces() {
		return this.unplacedPieceCollection.size() == 0;
	}
	
	
	private boolean isPlacementPosible(int row, int col) {
		boolean blackPossibleRows 		= (row == 5 || row == 6);
		boolean redPossibleRows 		= (row == 0 || row == 1);
		boolean possibleColumns 		= (col >= 0 || col <= 8);
		
		if((this.color == GameColorEnum.BLACK) 	&& (blackPossibleRows 	&& possibleColumns)) 	return true;
		if((this.color == GameColorEnum.RED) 	&& (redPossibleRows 	&& possibleColumns)) 	return true;
		
		return false;
	}
}
