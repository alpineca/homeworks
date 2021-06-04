package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class Fisherman extends ArmyUnit {

	private static Color color 				= new Color(212, 108, 34);
	private static int identificator 		= 4;
	
	public Fisherman(int row, int col, int index) {
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
