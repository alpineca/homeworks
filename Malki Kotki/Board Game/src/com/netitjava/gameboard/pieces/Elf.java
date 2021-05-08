package com.netitjava.gameboard.pieces;

public class Elf extends Piece {

	public Elf(int row, int col) {
		super(row, col, 5,1,10,3,3);
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
		return "&";
	}
	

	@Override
	public String getPieceName() {
		return "Elf";
	}

	@Override
	public String movementRules() {
		
		return null;
	}	
}
