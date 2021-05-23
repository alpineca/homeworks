package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Graphics;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class Drunker extends ArmyUnit {
	private static Color color 				= Color.YELLOW;
	private static String identificator 	= "3";
	
	public Drunker(int row, int col) {
		super(row, col, color, identificator);
	}

}
