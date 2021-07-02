package processors;

import java.util.ArrayList;

import GameBoardObjects.buildings.LargeBuilding;
import GameBoardObjects.buildings.MiddleBuilding;
import GameBoardObjects.buildings.SmallBuilding;
import GameBoardObjects.materials.Column;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.parrents.ArmyUnit;
import GameBoardObjects.parrents.GameBoardObject;
import enums.BuildingsEnum;
import enums.ResultEnum;
import game.GameBoard;

public class BombProcessor {

    private static ArrayList<GameBoardObject> armyUnits     = GameBoard.getArmyUnits();
    private static GameBoardObject[][] gameBoard            = GameBoard.getGameBoard();


    public static void bombExplode(){
		ArrayList<GameBoardObject> smallBuildingElements 	= SmallBuilding.getBuildingElements();
		ArrayList<GameBoardObject> middleBuildingElements 	= MiddleBuilding.getBuildingElements();
		ArrayList<GameBoardObject> largeBuildingElements 	= LargeBuilding.getBuildingElements();
		int unexplodedColumns = 0;

		explodeTheBomb(smallBuildingElements, BuildingsEnum.SMALL);
		explodeTheBomb(middleBuildingElements, BuildingsEnum.MIDDLE);
		explodeTheBomb(largeBuildingElements, BuildingsEnum.LARGE);
	
		for(GameBoardObject element : BuildingsProcessor.allBuildingsElements()){
			if(element instanceof Column){
				unexplodedColumns++;
			}
		}		

	}

	private static void explodeTheBomb(ArrayList<GameBoardObject> buildingElements, BuildingsEnum buildingSize) {

		for(GameBoardObject element : buildingElements){

			try {

				boolean isBombPlanted = ((Column) element).isBombPlanted;

				if(isBombPlanted && (buildingSize.equals(BuildingsEnum.SMALL))){
					SmallBuilding.explodeThisColumn(element, gameBoard);
					killNearUnits(element.getRow(), element.getCol());
				}
				if(isBombPlanted && (buildingSize.equals(BuildingsEnum.MIDDLE))){
					MiddleBuilding.explodeThisColumn(element, gameBoard);
					killNearUnits(element.getRow(), element.getCol());
				}
				if(isBombPlanted && (buildingSize.equals(BuildingsEnum.LARGE))){
					LargeBuilding.explodeThisColumn(element, gameBoard);
					killNearUnits(element.getRow(), element.getCol());
				}
				
			} catch (Exception e) {
				//TODO: handle exception
			}

			
		}
	}

    public static void killNearUnits(int row, int col){
		int minRowBound 		= row-1;
		int maxRowBound 		= row+2;

		int minColBound 		= col-1;
		int maxColBound 		= col+2;

		for(int i = minRowBound; i <= maxRowBound; i++){
			for(int j = minColBound; j <= maxColBound; j++){
				try {
					if(isArmyUnit(i, j)){
						armyUnits.remove(armyUnitToKillIndex(i, j));
						gameBoard[i][j] = new Ground(i, j);
					}
					if(armyUnits.size() == 0){
						GameProcessor.endGame(ResultEnum.LOSE);
					}
				} catch (Exception e) {
					//Exception
				}					
			}
		}
	}

	private static boolean isArmyUnit(int row, int col){
		for(GameBoardObject unit : armyUnits){
			if(unit.getRow() == row && unit.getCol() == col){
				return true;
			}
		}
		return false;
	}

	private static int armyUnitToKillIndex(int row, int col){
		int index = 0;
		for(GameBoardObject unit : armyUnits){
			if(unit.getRow() == row && unit.getCol() == col){
				return index;
			}
			index++;
		}
		return index;
	}
}
