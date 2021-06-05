package GameBoardObjects.parrents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import interfaces.GameConfig;

public abstract class ArmyUnit extends GameBoardObject{
	protected int identificator;
	protected int index;
	protected boolean isSpecialSkill = false;
	
	
	protected ArmyUnit(int row, int col, Color color, int identificator, int index, boolean isSpecialSkill) {
		super(row, col, color);
		this.identificator 	= identificator;
		this.color 			= color;
		this.index 			= index;
		this.isSpecialSkill = isSpecialSkill;
	}
	protected ArmyUnit(int row, int col, Color color, int identificator, int index) {
		super(row, col, color);
		this.identificator 	= identificator;
		this.color 			= color;
		this.index 			= index;
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
	public boolean isSpecialSkill() {
		return isSpecialSkill;
	}

	@Override
	public void render(Graphics g) {
		int tileX = this.col * GameConfig.tileSize; 
		int tileY = this.row * GameConfig.tileSize;
		
		int placementCoefficientX = (GameConfig.tileSize / 2) - 10;
		int placementCoefficientY = (GameConfig.tileSize / 2) + 7;

		String unitInfo = this.index + "" + this.identificator;
		
		g.setColor(this.color);
		g.fillRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("", Font.ITALIC, 20));
		g.drawString(unitInfo, tileX + placementCoefficientX, tileY  + placementCoefficientY);
		
	}

	public void setIndex(int index){
		this.index = index;
	}
	public int getIndex(){
		return this.index;
	}
	public abstract int getIdentificator();
	public abstract void specialSkill();

}
