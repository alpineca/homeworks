package GameBoardObjects.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.Material;
import interfaces.GameConfig;

public class Ground extends Material{
	private static Color color = Color.BLACK;
	private boolean isTrigger = false;
	
	public Ground(int row, int col) {
		super(row, col, color);
	}

	@Override
	public void render(Graphics g) {
		int tileX = this.col * GameConfig.tileSize; 
		int tileY = this.row * GameConfig.tileSize;
		
		g.setColor(this.color);
		g.fillRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		g.setColor(this.borderColor);
		g.drawRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		
	}

	public void trigger(){
		if(this.isTrigger == false) this.isTrigger = true;
		else this.isTrigger = false;
	}
	public boolean getTrigger(){
		return this.isTrigger;
	}


}
