package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class RockTrower extends ArmyUnit {


	private static Color color 				= Color.ORANGE;
	private static Color borderColor 		= Color.DARK_GRAY;
	private static String identificator 	= "2";
	private int index;
	
	public RockTrower(int row, int col, int index) {
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
