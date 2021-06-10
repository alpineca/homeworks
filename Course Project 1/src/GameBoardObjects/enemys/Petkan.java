package GameBoardObjects.enemys;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;

import GameBoardObjects.armyUnits.Sniperist;
import GameBoardObjects.armyUnits.Tank;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.parrents.Enemy;
import GameBoardObjects.parrents.GameBoardObject;
import enums.ArmyUnitsEnum;
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
		int tileX = this.col * GameConfig.TILESIZE; 
		int tileY = this.row * GameConfig.TILESIZE;
	
		int placementCoefficientX = (GameConfig.TILESIZE / 2) - 10;
		int placementCoefficientY = (GameConfig.TILESIZE / 2) + 7;

		if(isVisible == true){
		
			g.setColor(this.color);
			g.fillRect(tileX, tileY, GameConfig.TILESIZE, GameConfig.TILESIZE);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(tileX, tileY, GameConfig.TILESIZE, GameConfig.TILESIZE);
		
			g.setColor(Color.BLACK);
			g.setFont(new Font("", Font.ITALIC, 20));
			g.drawString(this.symbol, tileX + placementCoefficientX, tileY  + placementCoefficientY);
		}
		else{		
			g.setColor(Color.DARK_GRAY);
			g.fillRect(tileX, tileY, GameConfig.TILESIZE, GameConfig.TILESIZE);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(tileX, tileY, GameConfig.TILESIZE, GameConfig.TILESIZE);
		}
		
	}

	public void trigger(){
		if(this.isVisible == true){
			// fire();
			return;
		}
		else if(this.isVisible == false){
			this.isVisible = true;
		}
	}

	public void fire(){
		ArrayList<GameBoardObject> armyUnits = GameBoard.getArmyUnits();
		boolean isTankAvailable 		= checkForUnit(ArmyUnitsEnum.TANK);
		boolean isSniperistAvailable 	= checkForUnit(ArmyUnitsEnum.SNIPERIST);

		int fireAccuracy 		= rand(25);
		int hitChance 			= 11;
		boolean isAccurateShot 	= (fireAccuracy % hitChance) == 0;

		if(isSniperistAvailable == true){
			int extraHitChance 	= rand(2);
			isAccurateShot 	= ((fireAccuracy % hitChance) == 0) && extraHitChance == 1;
		}

		if(isAccurateShot){
			GameBoardObject armyLeader = armyUnits.get(0);
			if(armyUnits.size() == 1){
				System.exit(0);
			}
			if(isTankAvailable == true){
				for(GameBoardObject element : armyUnits){
					if(element instanceof Tank){
						GameBoard.gameBoard[element.getRow()][element.getCol()] = new Ground(element.getRow(), element.getCol());
						armyUnits.remove(element);
					}
				}
			}
			else{
				GameBoard.gameBoard[armyLeader.getRow()][armyLeader.getCol()] = new Ground(armyLeader.getRow(), armyLeader.getCol());
				armyUnits.remove(0);
			}

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
			// int randCorner = (rand(4) + 1);
			int randCorner = 3;
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

	private boolean checkForUnit(ArmyUnitsEnum searchForUnit) {
		ArrayList<GameBoardObject> armyUnits = GameBoard.getArmyUnits();

		for(GameBoardObject element : armyUnits){
			if(element instanceof Tank && searchForUnit == ArmyUnitsEnum.TANK){
				return true;
			}
			if(element instanceof Sniperist && searchForUnit == ArmyUnitsEnum.SNIPERIST){
				return true;
			}
		}
		return false;
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

