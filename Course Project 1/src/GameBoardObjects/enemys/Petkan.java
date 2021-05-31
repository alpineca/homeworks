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
		fire();
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
			this.isVisible = false;
			drinkAndReload();
		}else{
			this.isVisible = false;
			goToCorner();
		}
	}

	private void drinkAndReload() {
	}

	private void goToCorner() {
		int randCorner = (rand(4) + 1);
		int oldRow = this.getRow();
		int oldCol = this.getCol();
		int row, col;

		//TOP LEFT
		if(randCorner == 1){
			row = 0;
			col = 0;
			if(GameBoard.gameBoard[row][col] instanceof Ground){
				GameBoard.gameBoard[oldRow][oldCol] = new Ground(oldRow, oldCol);
				this.setRow(row);
				this.setCol(col);
				GameBoard.gameBoard[row][col] = this;
			}
			else{
				goToCorner();
			}
		}
		//TOP RIGHT
		if(randCorner == 2){
			row = 0;
			col = 14;
			if(GameBoard.gameBoard[row][col] instanceof Ground){
				GameBoard.gameBoard[oldRow][oldCol] = new Ground(oldRow, oldCol);
				this.setRow(row);
				this.setCol(col);
				GameBoard.gameBoard[row][col] = this;
			}
			else{
				goToCorner();
			}
		}
		//BOTTOM LEFT
		if(randCorner == 3){
			row = 14;
			col = 0;
			if(GameBoard.gameBoard[row][col] instanceof Ground){
				GameBoard.gameBoard[oldRow][oldCol] = new Ground(oldRow, oldCol);
				this.setRow(row);
				this.setCol(col);
				GameBoard.gameBoard[row][col] = this;
			}
			else{
				goToCorner();
			}
		}
		//BOTTOM RIGHT
		if(randCorner == 4){
			row = 14;
			col = 14;
			if(GameBoard.gameBoard[row][col] instanceof Ground){
				GameBoard.gameBoard[oldRow][oldCol] = new Ground(oldRow, oldCol);
				this.setRow(row);
				this.setCol(col);
				GameBoard.gameBoard[row][col] = this;
			}
			else{
				goToCorner();
			}
		}
	}

	@Override
	public int getIndex() {
		return 0;
	}

	private static int rand(int randBound) {
		Random rand = new Random();
		return rand.nextInt(randBound);
		
	}

    
}

