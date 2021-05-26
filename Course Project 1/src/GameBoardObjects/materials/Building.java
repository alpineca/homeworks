package GameBoardObjects.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.Material;
import interfaces.GameConfig;

public class Building extends Material{
	private Color color;
	public Building(int row, int col, Color color) {
		super(row, col, color);
		this.color = color;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		int tileX = this.col * GameConfig.tileSize; 
		int tileY = this.row * GameConfig.tileSize;
		
		g.setColor(this.color);
		g.fillRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		
	}
}
