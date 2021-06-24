package GameBoardObjects.armyUnits;

import java.awt.Color;

import GameBoardObjects.parrents.ArmyUnit;
import interfaces.GameConfig;

public class Spy extends ArmyUnit {
	private static Color color 				= GameConfig.SPYUNITCOLOR;
	private static int identificator 		= 3;
	private int index;

	
	public Spy(int row, int col, int index) {
		super(row, col, color, identificator, index);
	}

	@Override
	public int getIndex() {
		return this.index;
	}
	public int getIdentificator() {
		return this.identificator;
	}

	@Override
	public void specialSkill() {
		// TODO Auto-generated method stub
		
	}

}
