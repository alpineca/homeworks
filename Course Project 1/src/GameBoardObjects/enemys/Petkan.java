package GameBoardObjects.enemys;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Font;

import GameBoardObjects.Enemy;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.materials.Ground;
import game.GameBoard;
import interfaces.GameConfig;

public class Petkan extends Enemy{

    protected static Color color = Color.WHITE;
    protected String symbol = "$";
	protected boolean isVisible = false;

    public Petkan(int row, int col) {
        super(row, col, color);

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
			g.drawString(this.symbol, tileX + placementCoefficientX, tileY  + placementCoefficientY);
		}
		else{		
			g.setColor(Color.DARK_GRAY);
			g.fillRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(tileX, tileY, GameConfig.tileSize, GameConfig.tileSize);
		}
		
	}

	public void trigger(){
		if(this.isVisible == true){
			// fire();
			System.out.println("FIRE");
			return;
		}
		else if(this.isVisible == false){
			this.isVisible = true;
		}
	}

	public void fire(){
		System.out.println("\nSHOTS FIRED");

		int fireAccuracy 		= rand(25);
		int hitChance 			= 11;
		boolean isAccurateShot 	= (fireAccuracy % hitChance) == 0;

		if(isAccurateShot){
			GameBoardObject armyLeader = GameBoard.armyUnits.get(0);
			if(GameBoard.armyUnits.size() == 1){
				System.exit(0);
			}
			GameBoard.gameBoard[armyLeader.getRow()][armyLeader.getCol()] = new Ground(armyLeader.getRow(), armyLeader.getCol());
			GameBoard.armyUnits.remove(0);

			drinkAndReload();
		}else{
			goToCorner();
		}
	}

	public void goToCorner() {
		int oldRow = this.getRow();
		int oldCol = this.getCol();
		boolean isPlacementDone = false;
		this.isVisible = false;
		
		while(isPlacementDone == false){
			//TOP LEFT
			int row = 0, col = 0;
			int randCorner = (rand(4) + 1);
			//TOP RIGHT
			if(randCorner == 2){
				row = 0;
				col = 14;
			}
			//BOTTOM LEFT
			if(randCorner == 3){
				row = 14;
				col = 0;
			}
			//BOTTOM RIGHT
			if(randCorner == 4){
				row = 14;
				col = 14;
			}

			if(GameBoard.gameBoard[row][col] instanceof Ground){
				if(oldRow != -1 || oldCol != -1){
					GameBoard.gameBoard[oldRow][oldCol] = new Ground(oldRow, oldCol);
				}
				this.setRow(row);
				this.setCol(col);
				GameBoard.gameBoard[row][col] = this;
				isPlacementDone = true;
			}
		}
		
	}

	private void drinkAndReload() {
		int oldRow = this.getRow();
		int oldCol = this.getCol();
		this.isVisible = false;

		GameBoard.gameBoard[oldRow][oldCol] = new Ground(oldRow, oldCol);

		this.setRow(-1);
		this.setCol(-1);

		GameBoard.reloadCounterReset();
	}

	@Override
	public int getIndex() {
		return 0;
	}

	private static int rand(int randBound) {
		Random rand = new Random();
		return rand.nextInt(randBound);
		
	}

	public boolean getVisibility() {
		return this.isVisible;
	}

    
}

