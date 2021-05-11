package com.netitjava.gameboard;

import com.netitjava.gameboard.tiles.GameBoardTile;
import com.netitjava.managers.PlayerManager;
import com.netitjava.util.Console;

public class GameBoard {
	
	private static GameBoard internalInstance = null;
	
	private final int ROW_COUNT = 7;
	private final int COL_COUNT = 9;
	private GameBoardObject[][] collection = null;
	
	private GameBoard() {
		collection = new GameBoardObject[ROW_COUNT][COL_COUNT];
	}
	
	public static GameBoard getInstance() {
		
		if(internalInstance == null) {
			internalInstance = new GameBoard();
		}
		
		return internalInstance;
		
	}
	
	public GameBoardObject getElement(int row, int col) {
		return collection[row][col];
	}
	
	public void setElement(int row, int col, GameBoardObject element) {
		collection[row][col] = element;
	}
	
	
	public void render() {
		drawColNumbers();
		
		for(int row = 0; row < this.ROW_COUNT; row++) {
			
			System.out.print(row + " ");
			for(int col = 0; col < this.COL_COUNT; col++) {
				Console.log(" " +collection[row][col].render() + " ");
			}
			Console.logLn("");
		}
		
		drawColNumbers();
	}

	public void renderForPlacement(PlayerManager player) {
		drawColNumbers();
		
		for(int row = 0; row < this.ROW_COUNT; row++) {
			
			System.out.print(row + " ");
			for(int col = 0; col < this.COL_COUNT; col++) {
				String playerColor = player.getColor().toString();
				
				if(playerColor.equals("BLACK") && isBlacksZone(row) && isFreeTile(row, col)){
					Console.log(" B ");
				}
				if(playerColor.equals("RED") && isRedsZone(row) && isFreeTile(row, col)){
					Console.log(" R ");
				}
				else if(!isFreeTile(row, col)){
					Console.log(" " + collection[row][col].render() + " ");
				}
			}
			Console.logLn("");
		}
		
		drawColNumbers();
	}
	
	private void drawColNumbers() {
		System.out.print("  ");
		for(int col = 0; col < this.COL_COUNT; col++) {
			System.out.print(" " + col + " ");
		}
		System.out.println("");
	}

	private boolean isBlacksZone(int row){
		if(row == 5 || row == 6) return true;
		return false;
	}

	private boolean isRedsZone(int row){
		if(row == 0 || row == 1) return true;
		return false;
	}

	private boolean isFreeTile(int row, int col){
		if(collection[row][col] instanceof GameBoardTile) return true;
		return false;
	}

}
