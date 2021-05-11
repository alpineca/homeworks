package com.netitjava.managers;

import java.util.ArrayList;

import com.netitjava.enums.GameColorEnum;
import com.netitjava.enums.PlayerColorEnum;
import com.netitjava.enums.TileColorEnum;
import com.netitjava.exceptions.PlacementNotCorrectException;
import com.netitjava.exceptions.PlacementNotPosibleException;
import com.netitjava.gameboard.GameBoard;
import com.netitjava.gameboard.GameBoardObject;
import com.netitjava.gameboard.pieces.*;
import com.netitjava.gameboard.tiles.GameBoardTile;
import com.netitjava.util.Console;

public class PlayerManager {

	private GameColorEnum color;
	private int score;
	private ArrayList<Piece> unplacedPieceCollection;
	private ArrayList<Piece> pieceCollection;
	
	public PlayerManager(GameColorEnum color) {

		this.color = color;
		
		this.pieceCollection 			= new ArrayList<>();
		this.unplacedPieceCollection 	= new ArrayList<>();
		this.unplacedPieceCollection.add(new Knight(-1, -1));
		this.unplacedPieceCollection.add(new Knight(-1, -1));
		this.unplacedPieceCollection.add(new Dworf(-1, -1));
		this.unplacedPieceCollection.add(new Dworf(-1, -1));
		this.unplacedPieceCollection.add(new Elf(-1, -1));
		this.unplacedPieceCollection.add(new Elf(-1, -1));				
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
				
		// Select from unplaced piece collection
		Piece unplacedPiece = this.unplacedPieceCollection.get(pieceIndex);
		// 
		if(isPlacementPosible(row, col)) {

			//unplacedPiece.move(row, col);
			unplacedPiece.move(row, col);
			GameBoard.getInstance().setElement(row, col, unplacedPiece);
			this.pieceCollection.add(unplacedPiece);
			this.unplacedPieceCollection.remove(pieceIndex);
		}
		
	}
	
	public void moveSelectedPiece(int pieceIndex) {
		Piece selectedPiece = this.pieceCollection.get(pieceIndex);
		
		String pieceMoveDirection 	= Console.promtString("Select direction (W,S,A,D) to move:");
				
		MoveManager.moveThisPiece(selectedPiece, pieceMoveDirection);
		
	}
	
	
	
	public boolean hasUnplacedPieces() {
		return this.unplacedPieceCollection.size() == 0;
	}
	
	
	private boolean isPlacementPosible(int row, int col) {
		boolean blackPossibleRows 		= (row == 5 || row == 6);
		boolean redPossibleRows 		= (row == 0 || row == 1);
		boolean possibleColumns 		= (col >= 0 || col <= 8);
		
		if((this.color == color.BLACK) 	&& (blackPossibleRows && possibleColumns)) 	return true;
		if((this.color == color.RED) 	&& (redPossibleRows && possibleColumns)) 	return true;
		
		return false;
	}
}
