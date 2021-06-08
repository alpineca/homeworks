package GameBoardObjects.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.parrents.Material;
import interfaces.GameConfig;

public class BlownBuilding extends Material{
	// private static Color color;
	private boolean isTrigger = false;
	
	public BlownBuilding(int row, int col, Color color) {
		super(row, col, color);
	}

	@Override
	public void render(Graphics g) {
		int tileX = this.col * GameConfig.TILESIZE; 
		int tileY = this.row * GameConfig.TILESIZE;
		
		g.setColor(this.color);
		g.fillRect(tileX, tileY, GameConfig.TILESIZE, GameConfig.TILESIZE);
		g.setColor(this.borderColor);
		g.drawRect(tileX, tileY, GameConfig.TILESIZE, GameConfig.TILESIZE);
		
	}

	public void trigger(){
		if(this.isTrigger == false) this.isTrigger = true;
		else this.isTrigger = false;
	}
	public boolean getTrigger(){
		return this.isTrigger;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getPassability() {
		// TODO Auto-generated method stub
		return false;
	}


}
