package com.netitjava.gameboard.tiles;

import com.netitjava.enums.*;
import com.netitjava.gameboard.GameBoardObject;

public class GameBoardTile extends GameBoardObject {

	protected int row;
	protected int col;
	protected GameColorEnum color;
	
	public GameBoardTile(int row, int col, GameColorEnum color) {
		super(row, col);
		this.color = color;
	}

	@Override
	public String render() {
		return " ";
	}
	
	public GameColorEnum getColor() {
		return this.color;
	}
	
	
}
