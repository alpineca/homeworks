package com.netitjava.gameboard.pieces;

public class Wall extends Obstacle {

	public Wall(int row, int col) {
		super(row, col);
	}

	@Override
	public String render() {
		return "#";
	}

}
