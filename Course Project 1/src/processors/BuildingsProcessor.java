package processors;

import java.util.ArrayList;
import java.util.Random;

import GameBoardObjects.buildings.LargeBuilding;
import GameBoardObjects.buildings.MiddleBuilding;
import GameBoardObjects.buildings.SmallBuilding;
import GameBoardObjects.materials.Column;
import GameBoardObjects.parrents.GameBoardObject;
import enums.ResultEnum;

public class BuildingsProcessor {
	
	private static final int ROWS_BETWEEN_BUILDINGS 	= rand(2,3);
	private static final int COLS_BETWEEN_BUILDINGS 	= rand(2,3);
	private static final int BUILDING_BORDER_OFFSET 	= 1;
	
	private static final int SMALL_BUILDING_ROWS 		= 2;
	private static final int SMALL_BUILDING_COLS 		= 2;
	private static int smallBuildingPosRow;
	private static int smallBuildingPosCol;
	
	private static final int MIDDLE_BUILDING_ROWS 		= 2;
	private static final int MIDDLE_BUILDING_COLS 		= 3;
	private static int middleBuildingPosRow;
	private static int middleBuildingPosCol;
	
	
	private static final int LARGE_BUILDING_ROWS 		= 3;
	private static final int LARGE_BUILDING_COLS 		= 3;
	private static int largeBuildingPosRow;
	private static int largeBuildingPosCol;

	ArrayList<GameBoardObject> buildings;
		
	
	public static void spawnBuildings(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
		calculatePossitions();
				
		(new SmallBuilding()).spawn(smallBuildingPosRow, smallBuildingPosCol, gameBoard);
		(new MiddleBuilding()).spawn(middleBuildingPosRow, middleBuildingPosCol, gameBoard);
		(new LargeBuilding()).spawn(largeBuildingPosRow, largeBuildingPosCol, gameBoard);
	}

	public static ArrayList<GameBoardObject> allBuildingsElements(){
		ArrayList<GameBoardObject> allElements = new ArrayList<>();

		ArrayList<GameBoardObject> smallBuildingElements 	= SmallBuilding.getBuildingElements();
		ArrayList<GameBoardObject> middleBuildingElements 	= MiddleBuilding.getBuildingElements();
		ArrayList<GameBoardObject> largeBuildingElements 	= LargeBuilding.getBuildingElements();

		allElements.addAll(smallBuildingElements);
		allElements.addAll(middleBuildingElements);
		allElements.addAll(largeBuildingElements);

		return allElements;
	}
	
	public static void checkForRemainingBuildings() {
		int remainingColumns = 0;
		
		for(GameBoardObject element : allBuildingsElements()) {
			if(element instanceof Column) remainingColumns++;
		}
		
		if(remainingColumns == 0) {
			GameProcessor.endGame(ResultEnum.WIN);
		}
	}
	
	private static void calculatePossitions() {
		int firstGeneratedBuilding 	= rand(2)+1;
		
		if(firstGeneratedBuilding == 1) {
			largeBuildingPosRow = rand(1,6);
			largeBuildingPosCol = rand(1,5);
			
			smallBuildingPosRow = (largeBuildingPosRow + LARGE_BUILDING_ROWS) + ROWS_BETWEEN_BUILDINGS;
			smallBuildingPosCol = rand(1,6);
			
			middleBuildingPosRow = rand(1,8);
			middleBuildingPosCol = (largeBuildingPosCol + MIDDLE_BUILDING_COLS) + COLS_BETWEEN_BUILDINGS;
		}
		else if(firstGeneratedBuilding == 2) {
			middleBuildingPosRow = rand(1,7);
			middleBuildingPosCol = rand(1,5);
			
			smallBuildingPosRow = (middleBuildingPosRow + MIDDLE_BUILDING_ROWS) + ROWS_BETWEEN_BUILDINGS;
			smallBuildingPosCol = rand(1,5);
			
			largeBuildingPosRow = rand(1,8);
			largeBuildingPosCol = (middleBuildingPosCol + MIDDLE_BUILDING_COLS) + COLS_BETWEEN_BUILDINGS;
		}
		else if(firstGeneratedBuilding == 3) {
			smallBuildingPosRow = rand(1,7);
			smallBuildingPosCol = rand(1,5);
			
			middleBuildingPosRow = (smallBuildingPosRow + SMALL_BUILDING_ROWS) + ROWS_BETWEEN_BUILDINGS;
			middleBuildingPosCol = rand(1,7);
			
			largeBuildingPosRow = rand(1,4);
			largeBuildingPosCol = (smallBuildingPosCol + MIDDLE_BUILDING_COLS) + COLS_BETWEEN_BUILDINGS;
		}
		
		
		
	}
	
	private static int rand(int randBound) {
		Random rand = new Random();
		return rand.nextInt(randBound);
		
	}
	private static int rand(int randMin, int randMax) {
		int randomNumber;
		
		Random rand		= new Random();
		randomNumber 	= rand.nextInt(randMax);
		
		while(randomNumber < randMin) {
			randomNumber 	= rand.nextInt(randMax);
		}
		
		return (randomNumber) + 1;
		
	}

}
