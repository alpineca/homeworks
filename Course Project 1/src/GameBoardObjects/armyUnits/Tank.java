package GameBoardObjects.armyUnits;

import java.awt.Color;

import GameBoardObjects.parrents.ArmyUnit;
import interfaces.GameConfig;

public class Tank extends ArmyUnit {


	private static Color color 				= GameConfig.TANKUNITCOLOR;
	private static int identificator 		= 1;
	private int index;
	
	public Tank(int row, int col, int index) {
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
