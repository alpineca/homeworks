package GameBoardObjects.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseEvent;

import GameBoardObjects.Material;
import interfaces.GameConfig;

public class Building extends Material{
	private Color color;
	private boolean isPassable 	= true;
	private boolean isColumn 	= false;
	public Building(int row, int col, Color color) {
		super(row, col, color);
		this.color = color;
	}

	public Building(int row, int col, boolean isColumn, Color color) {
		super(row, col, color);
		this.color = color;
		this.isColumn = isColumn;
	}

	public Building(int row, int col, Color color, boolean isPassable) {
		super(row, col, color);
		this.color = color;
		this.isPassable = isPassable;
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

		if(isColumn == true){
			g.setColor(Color.BLACK);
			g.setFont(new Font("", Font.ITALIC, 20));
			g.drawString("X", tileX + placementCoefficientX, tileY  + placementCoefficientY);
		}
		
	}

	public String colorToString(){
		return getColor().toString();
	}

	public Color getColor(){
		return this.color;
	}
}
