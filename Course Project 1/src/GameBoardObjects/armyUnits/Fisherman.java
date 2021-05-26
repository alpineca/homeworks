package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class Fisherman extends ArmyUnit {

	private static Color color 				= Color.PINK;
	private static Color borderColor 		= Color.DARK_GRAY;
	private static String identificator 	= "4";
	
	public Fisherman(int row, int col, int index) {
		super(row, col, color, identificator, index);
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
		
	}

	@Override
	public int getIndex() {
		return this.index;
	}

}
