package gameboardobjects.parents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gameboardobjects.contracts.GameObjectColorEnum;

public abstract class Piece extends GameBoardObject {

	protected int point;
	protected String sign;
	
	public Piece(int row, int col, GameObjectColorEnum color, int point, String sign) {

		super(row, col, color);	
		this.point 	= point;
		this.sign 	= sign; 
	}
		
	public int getScore() {
		return this.point;
	}
	
	protected Color computeGameObjectColor() {
		
		return (this.color == GameObjectColorEnum.BLACK) 
				? Color.RED 
                : Color.ORANGE;   
	}	
	
	
	public String getSign() {
		return this.sign;
	}
	
	
	@Override
	public void render(Graphics g) {
		
		int tileX = this.col * this.size; 
		int tileY = this.row * this.size;
		
		g.setColor(getTileColor(this.row, this.col));
		g.fillRect(tileX, tileY, this.size, this.size);
		g.setFont(new Font("", Font.PLAIN, 18));
		g.setColor(this.computeGameObjectColor());

		g.fillOval(tileX, tileY, this.size, this.size);
//		g.setColor(this.color);
		g.setColor(Color.BLACK);
		g.drawString(this.getSign(), tileX + this.size / 2, tileY   + this.size / 2);		
	}	
	
	
	public int getRow() {
		return this.row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}	
	
	public int getCol() {
		return this.col;
	}	
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public Color getTileColor(int row, int col) {
		
		boolean isRowColEven = (row % 2 == 0) && (col % 2 == 0);
		boolean isRowColOdd  = (row % 2 != 0) && (col % 2 != 0);
		
		if(isRowColEven || isRowColOdd) {
			return Color.BLACK;
		}
		
		return Color.WHITE;
	}
	
	public abstract boolean isMovePosible(int toRow, int toCol);
	
	public abstract boolean isAttackPosible(int toRow, int toCol);
}
