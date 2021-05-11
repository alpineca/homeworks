package com.netitjava.gameboard.pieces;

import com.netitjava.gameboard.GameBoardObject;

public abstract class Obstacle extends GameBoardObject{
	protected int row;
	protected int col;
	protected boolean isBreakable;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isBreakable() {
		return isBreakable;
	}

	public void setBreakable(boolean isBreakable) {
		this.isBreakable = isBreakable;
	}

	public Obstacle(int row, int col) {
		super(row, col);
	}

	//@Override
	//public abstract String render();

}
