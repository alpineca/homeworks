package gameboardobjects.piece;

import java.awt.Graphics;

import gameboardobjects.contracts.GameObjectColorEnum;
import gameboardobjects.contracts.PieceScore;
import gameboardobjects.parents.Piece;

public class Knight extends Piece {

	public Knight(int row, int col, GameObjectColorEnum color) {
		super(row, col, color, PieceScore.KNIGHT, "Kn");
	}

	@Override
	public boolean isMovePosible(int toRow, int toCol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAttackPosible(int toRow, int toCol) {
		// TODO Auto-generated method stub
		return false;
	}

}
