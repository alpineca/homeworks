package GameBoardObjects.materials;

import java.awt.Color;
import java.awt.Graphics;

import GameBoardObjects.parrents.Material;

import java.awt.Font;

import interfaces.GameConfig;

public class Brick extends Material{
	private Color color;
	private boolean isPassable 	= true;
	public boolean isColumn 	= false;
	public boolean isDestroyed 	= false;
	private String infoString = "";
	public Brick(int row, int col, Color color) {
		super(row, col, color);
		this.color = color;
	}

	public Brick(int row, int col, boolean isColumn, Color color) {
		super(row, col, color);
		this.color = color;
		this.isColumn = isColumn;
	}

	public Brick(int row, int col, Color color, boolean isPassable) {
		super(row, col, color);
		this.color = color;
		this.isPassable = isPassable;
	}

	public void setInfoString(String infoString){
		this.infoString = infoString;
	}

	@Override
	public void render(Graphics g) {
		int tileX = this.col * GameConfig.tileSize; 
		int tileY = this.row * GameConfig.tileSize;

		int placementCoefficientX = (GameConfig.tileSize / 2) - 10;
		int placementCoefficientY = (GameConfig.tileSize / 2) + 7;
		
		g.setColor(this.color);
		g.fillRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);

		if(this.isColumn == true && this.infoString.isEmpty()){
			g.setColor(Color.BLACK);
			g.setFont(new Font("", Font.ITALIC, 20));
			g.drawString("X", tileX + placementCoefficientX, tileY  + placementCoefficientY);
		}else{
			g.setColor(Color.BLACK);
			g.setFont(new Font("", Font.ITALIC, 20));
			g.drawString(this.infoString, tileX + placementCoefficientX, tileY  + placementCoefficientY);
		}
		
	}

	@Override
	public Color getColor(){
		return this.color;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getPassability() {
		return this.isPassable;
	}
}
