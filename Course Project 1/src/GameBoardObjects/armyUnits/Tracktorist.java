package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class Tracktorist extends ArmyUnit {


	private static Color color 				= Color.RED;
	private static String identificator 	= "1";
	
	public Tracktorist(int row, int col) {
		super(row, col, color, identificator);
	}
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
