package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.util.ArrayList;
import GameBoardObjects.ArmyUnit;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.materials.Building;
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
		//Намира ли се единицата върху място за поставяне на бомба
		ArrayList<Building> smallBuilding 	= GameBoard.getSmallBuilding();
		ArrayList<Building> middleBuilding 	= GameBoard.getMiddleBuilding();
		ArrayList<Building> largeBuilding 	= GameBoard.getLargeBuilding();

		for(Building element : smallBuilding){
			if(element.getRow() == this.row && element.getCol() == this.col && element.isColumn == true){
				System.out.println("Small Building Column");
			}
		}
		for(Building element : middleBuilding){
			if(element.getRow() == this.row && element.getCol() == this.col && element.isColumn == true){
				System.out.println("Middle Building Column");
			}
		}
		for(Building element : largeBuilding){
			if(element.getRow() == this.row && element.getCol() == this.col && element.isColumn == true){
				System.out.println("Large Building Column");
			}
		}
		//Ако се намира поставя бомба
	}

}
