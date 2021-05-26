package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class Tracktorist extends ArmyUnit {


	private static Color color 				= Color.RED;
	private static String identificator 	= "1";
	
	public Tracktorist(int row, int col) {
		super(row, col, color, identificator);
	}


}
