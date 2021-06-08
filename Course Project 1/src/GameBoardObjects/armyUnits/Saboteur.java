package GameBoardObjects.armyUnits;

import java.awt.Color;

import GameBoardObjects.materials.Column;
import GameBoardObjects.parrents.ArmyUnit;
import GameBoardObjects.parrents.GameBoardObject;
import processors.BuildingsProcessor;

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

		for(GameBoardObject element : BuildingsProcessor.allBuildingsElements()){
			if(element.getRow() == this.row && element.getCol() == this.col && element instanceof Column){
				plantBomb(this.row, this.col);
			}
		}
	}

	private void plantBomb(int row, int col) {
		for(GameBoardObject element : BuildingsProcessor.allBuildingsElements()){
			if(element.getRow() == row && element.getCol() == col){
				((Column)element).plantBomb();
			}
		}
	}

}
