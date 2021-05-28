package GameBoardObjects.armyUnits;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import GameBoardObjects.ArmyUnit;
import interfaces.GameConfig;

public class Drunker extends ArmyUnit {
	private static Color color 				= Color.YELLOW;
	private static int identificator 		= 3;
	private int index;

	
	public Drunker(int row, int col, int index) {
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
	public int getIdentificator() {
		return this.identificator;
	}

}
