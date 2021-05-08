package com.netitjava.gameboard.pieces;

public class Barricade extends Obstacle{

	public Barricade(int row, int col) {
		super(row, col);
	}

	@Override
	public String render() {
		return "$";
	}

}
