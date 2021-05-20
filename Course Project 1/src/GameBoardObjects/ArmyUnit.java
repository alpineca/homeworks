package GameBoardObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import interfaces.GameConfig;

public class ArmyUnit extends GameBoardObject{
	protected String identificator;
	protected Color color;
	public ArmyUnit(int row, int col, Color color, String identificator) {
		super(row, col, color);
		this.identificator = identificator;
		this.color = color;
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
