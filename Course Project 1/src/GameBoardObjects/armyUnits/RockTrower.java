package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class RockTrower extends ArmyUnit {


	private static Color color 				= new Color(127, 153, 50);
	private static int identificator 		= 2;
	private int index;
	
	public RockTrower(int row, int col, int index) {
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

	
}
