package GameBoardObjects.enemys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import GameBoardObjects.Enemy;
import game.GameBoard;
import interfaces.GameConfig;

public class Petkan extends Enemy{

    protected Color color;
    protected String symbol = "$";
	protected boolean isVisible = false;

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

		if(isVisible == true){
		
			g.setColor(this.color);
			g.fillRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		
			g.setColor(Color.BLACK);
			g.setFont(new Font("", Font.ITALIC, 20));
			g.drawString(GameBoard.fireCounter + "", tileX + placementCoefficientX, tileY  + placementCoefficientY);
		}
		else{		
			g.setColor(Color.DARK_GRAY);
			g.fillRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		}
		
	}

	public void trigger(){
		this.isVisible = true;
		GameBoard.setFireCounter();
	}

	public void fire(){
		System.out.println("\n\nSHOTS FIRED\n\n");
		GameBoard.armyUnits.remove(0);
	}

    
}

