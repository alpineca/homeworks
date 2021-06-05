package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.util.ArrayList;

import GameBoardObjects.buildings.SmallBuilding;
import GameBoardObjects.materials.Brick;
import GameBoardObjects.materials.Column;
import GameBoardObjects.parrents.ArmyUnit;
import GameBoardObjects.parrents.GameBoardObject;
import game.GameBoard;

public class Saboteur extends ArmyUnit {

	private static Color color 				= new Color(212, 108, 34);
	private static int identificator 		= 4;
	private static boolean isSpecialSkill 	= true;
	
	public Saboteur(int row, int col, int index) {
		super(row, col, color, identificator, index, isSpecialSkill);
	}

	@Override
	public int getIndex() {
		return this.index;
	}

	@Override
	public int getIdentificator() {
		return this.identificator;
	}

	@Override
	public void specialSkill() {
		
		ArrayList<GameBoardObject> smallBuilding 	= SmallBuilding.getBuildingElements();
		// ArrayList<Brick> middleBuilding 	= GameBoard.getMiddleBuilding();
		// ArrayList<Brick> largeBuilding 	= GameBoard.getLargeBuilding();

		for(GameBoardObject element : smallBuilding){
			if(element.getRow() == this.row && element.getCol() == this.col && element instanceof Column){
				plantBomb(this.row, this.col);
			}
		}
		// for(GameBoardObject element : middleBuilding){
		// 	if(element.getRow() == this.row && element.getCol() == this.col && element instanceof Column){
		// 		System.out.println("Middle Building Column");
		// 	}
		// }
		// for(GameBoardObject element : largeBuilding){
		// 	if(element.getRow() == this.row && element.getCol() == this.col && element instanceof Column){
		// 		System.out.println("Large Building Column");
		// 	}
		// }
	}

	private void plantBomb(int row, int col) {
		ArrayList<GameBoardObject> smallBuildingElements = SmallBuilding.getBuildingElements();
		for(GameBoardObject element : smallBuildingElements){
			if(element.getRow() == row && element.getCol() == col){
				((Column)element).plantBomb();
			}
		}
	}

}
