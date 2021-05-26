package GameBoardObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import interfaces.GameConfig;

public abstract class ArmyUnit extends GameBoardObject{
	protected String identificator;
//	protected Color color;
//	protected Color borderColor;
	
	public ArmyUnit(int row, int col, Color color, String identificator) {
		super(row, col, color);
		this.identificator = identificator;
		this.color = color;
	}
	
	public int getRow() {
		return super.row;
	}
	public int getCol() {
		return super.col;
	}
	public void setRow(int row) {
		super.row = row;
	}
	public void setCol(int col) {
		super.col = col;
	}
	public void setRowCol(int row, int col) {
		super.row = row;
		super.col = col;
	}

	@Override
	public void render(Graphics g) {
		int tileX = this.col * GameConfig.tileSize; 
		int tileY = this.row * GameConfig.tileSize;
		
		int placementCoefficientX = (GameConfig.tileSize / 2) - 5;
		int placementCoefficientY = (GameConfig.tileSize / 2) + 5;
		
		g.setColor(this.color);
		g.fillRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("", Font.ITALIC, 20));
		g.drawString(this.identificator, tileX + placementCoefficientX, tileY  + placementCoefficientY);
		
	}

}
