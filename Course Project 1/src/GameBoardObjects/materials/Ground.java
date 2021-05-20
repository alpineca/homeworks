package GameBoardObjects.materials;

import java.awt.Color;
import java.awt.Graphics;

import GameBoardObjects.Material;
import interfaces.GameConfig;

public class Ground extends Material{
//	private static Color color;
	private static Color color = Color.BLACK;
	
	public Ground(int row, int col) {
		super(row, col, color);
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
