package GameBoardObjects.ArmyUnits;

import java.awt.Color;
import java.awt.Graphics;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class Fisherman extends ArmyUnit {

	private static Color color 				= Color.PINK;
	private static String identificator 	= "4";
	
	public Fisherman(int row, int col) {
		super(row, col, color, identificator);
		// TODO Auto-generated constructor stub
	}

}
