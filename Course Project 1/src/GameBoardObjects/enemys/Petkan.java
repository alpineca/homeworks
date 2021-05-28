package GameBoardObjects.enemys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import GameBoardObjects.Enemy;
import interfaces.GameConfig;

public class Petkan extends Enemy{

    protected Color color;
    protected String symbol = "$";

    public Petkan(int row, int col, Color color) {
        super(row, col, color);
		this.color = color;

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
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("", Font.ITALIC, 20));
		g.drawString(this.symbol, tileX + placementCoefficientX, tileY  + placementCoefficientY);
		
	}

    
}

