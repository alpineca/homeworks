package com.netitjava.gameboard.pieces;

public class Dworf extends Piece {

	public Dworf(int row, int col) {
		super(row, col, 6, 2, 12, 2, 2);
	}

	@Override
	public boolean isMovePossible(int row, int col) {
		return false;
		
	}

	@Override
	public void isAttackPossible() {
		
		
	}

	@Override
	public void isHealPossible() {
		
		
	}

	@Override
	public String render() {
		return "@";
	}

	@Override
	public String getPieceName() {
		return "Dworf";
	}

	@Override
	public String movementRules() {
		
		return null;
	}
}