package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Font;

import GameBoardObjects.parrents.ArmyUnit;

public class Sniperist extends ArmyUnit {


	private static Color color 				= new Color(127, 153, 50);
	private static int identificator 		= 2;
	private int index;
	
	public Sniperist(int row, int col, int index) {
		super(row, col, color, identificator, index);
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
		// TODO Auto-generated method stub
		
	}

	
}
