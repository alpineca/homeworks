package com.netitjava.units.child;

import com.netitjava.level.manager.GameKeyManager;
import com.netitjava.model.RowCol;
import com.netitjava.units.parent.Unit;

public class PlayerTank extends Unit  {
	
	public PlayerTank(int row, int col) {

		this.setSimbol("%");
		this.setRow(row);
		this.setCol(col);
		this.setBreakable(true);
	}
	
	public RowCol getDestination(String keyCode) {
		int destinationRow = this.getDestinationRow(row, keyCode)
		
		return null;
		
	}
	
	private int getDestinationRow(int currentRow, String direction) {
		
		if(direction.equals(GameKeyManager.FORWARD)) return this.getRow() - 1;
		if(direction.equals(GameKeyManager.BACKWARD)) return this.getRow() + 1;
		return currentRow;
	}
	
	private int getDestinationCol(int currentCol, String direction) {
		
		if(direction.equals(GameKeyManager.LEFT)) return this.getCol() - 1;
		if(direction.equals(GameKeyManager.RIGHT)) return this.getCol() + 1;
		return currentCol;		
	}
}
