package processors;

import GameBoardObjects.GameBoardObject;
import GameBoardObjects.armyUnits.Drunker;
import GameBoardObjects.armyUnits.Fisherman;
import GameBoardObjects.armyUnits.RockTrower;
import GameBoardObjects.armyUnits.Tracktorist;

public class UnitsProcessor {
	
	
	public static void spawnArmyUnits(GameBoardObject[][] gameBoard) {
		gameBoard[14][11] = new Tracktorist(14, 11);
		gameBoard[14][12] = new RockTrower(14, 12);
		gameBoard[14][13] = new Drunker(14, 13);
		gameBoard[14][14] = new Fisherman(14, 14);
	}

}
