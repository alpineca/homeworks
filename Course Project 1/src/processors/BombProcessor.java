package processors;

import java.util.ArrayList;

import GameBoardObjects.buildings.LargeBuilding;
import GameBoardObjects.buildings.MiddleBuilding;
import GameBoardObjects.buildings.SmallBuilding;
import GameBoardObjects.materials.Column;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.parrents.ArmyUnit;
import GameBoardObjects.parrents.GameBoardObject;
import game.GameBoard;

public class BombProcessor {

    private static ArrayList<GameBoardObject> armyUnits     = GameBoard.getArmyUnits();
    private static GameBoardObject[][] gameBoard            = GameBoard.getGameBoard();


    public static void bombExplode(){
		ArrayList<GameBoardObject> smallBuildingElements 	= SmallBuilding.getBuildingElements();
		ArrayList<GameBoardObject> middleBuildingElements 	= MiddleBuilding.getBuildingElements();
		ArrayList<GameBoardObject> largeBuildingElements 	= LargeBuilding.getBuildingElements();
		int unexplodedColumns = 0;

		for(GameBoardObject element : smallBuildingElements){
			try {
				boolean isBombPlanted = ((Column) element).isBombPlanted;

				if(isBombPlanted){
					SmallBuilding.explodeThisColumn(element, gameBoard);
					BombProcessor.killNearUnits(element.getRow(), element.getCol());
				}
			} catch (Exception e) {
				//TODO: handle exception
			}
			
		}
		for(GameBoardObject element : middleBuildingElements){

			try {

				boolean isBombPlanted = ((Column) element).isBombPlanted;

				if(isBombPlanted){
					MiddleBuilding.explodeThisColumn(element, gameBoard);
					BombProcessor.killNearUnits(element.getRow(), element.getCol());
				}
				
			} catch (Exception e) {
				//TODO: handle exception
			}

			
		}
		for(GameBoardObject element : largeBuildingElements){

			try {

				boolean isBombPlanted = ((Column) element).isBombPlanted;

				if(isBombPlanted){
					LargeBuilding.explodeThisColumn(element, gameBoard);
					BombProcessor.killNearUnits(element.getRow(), element.getCol());
				}
				
			} catch (Exception e) {
				//TODO: handle exception
			}

			
		}

		for(GameBoardObject element : BuildingsProcessor.allBuildingsElements()){
			if(element instanceof Column){
				unexplodedColumns++;
			}
		}
		if(unexplodedColumns == 0){
			// GUI.conditionGameOver();
		}


		// this.repaint();

		

	}

    public static void killNearUnits(int row, int col){
		int minRowBound 		= row-1;
		int maxRowBound 		= row+1;

		int minColBound 		= col-1;
		int maxColBound 		= col+1;

		int unitToKillRow 		= -1;
		int unitToKillCol 		= -1;
		boolean isUnitToKill 	= false;

		for(int i = minRowBound; i <= maxRowBound; i++){
			for(int j = minColBound; j <= maxColBound; j++){
				try {
					if(gameBoard[i][j] instanceof ArmyUnit){
						unitToKillRow 	= i;
						unitToKillCol 	= j;
						isUnitToKill 	= true;
						break;
					} 
				} catch (Exception e) {

				}					
			}
		}

		if(unitToKillRow > -1 && unitToKillCol > -1 && isUnitToKill == true){
			for(GameBoardObject unit : armyUnits){
				try {
					if(unit.getRow() == unitToKillRow && unit.getCol() == unitToKillCol){
						armyUnits.remove(unit);
						gameBoard[unitToKillRow][unitToKillCol] = new Ground(unitToKillRow, unitToKillCol);
						// this.repaint();
					}
				} catch (Exception e) {

				}			
			}
		}
	}
}
