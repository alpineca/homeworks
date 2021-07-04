package processors;

import java.util.ArrayList;
import java.util.Random;

import GameBoardObjects.buildings.LargeBuilding;
import GameBoardObjects.buildings.MiddleBuilding;
import GameBoardObjects.buildings.SmallBuilding;
import GameBoardObjects.materials.Column;
import GameBoardObjects.parrents.GameBoardObject;
import enums.ResultEnum;
import interfaces.GameConfig;

public class BuildingsProcessor {
	
	private static final int ROWS_BETWEEN_BUILDINGS 	= rand(2,3);
	private static final int COLS_BETWEEN_BUILDINGS 	= rand(2,3);
	
	private static int smallBuildingPosRow;
	private static int smallBuildingPosCol;
	
	private static int middleBuildingPosRow;
	private static int middleBuildingPosCol;
	
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
		int firstGeneratedBuilding 	= rand(3)+1;
		
		if(firstGeneratedBuilding == 1) {
			largeBuildingPossition(rand(1,6), rand(1,5));
			smallBuildingPossition(((largeBuildingPosRow + GameConfig.LARGEBUILDINGROWS) + ROWS_BETWEEN_BUILDINGS), rand(1,6));
			middleBuildingPossition(rand(1,8), ((largeBuildingPosCol + GameConfig.MIDDLEBUILDINGCOLS) + COLS_BETWEEN_BUILDINGS));
		}
		else if(firstGeneratedBuilding == 2) {
			middleBuildingPossition(rand(1,7), rand(1,5));
			smallBuildingPossition(((middleBuildingPosRow + GameConfig.MIDDLEBUILDINGROWS) + ROWS_BETWEEN_BUILDINGS), rand(1,5));
			largeBuildingPossition(rand(1,8), ((middleBuildingPosCol + GameConfig.MIDDLEBUILDINGCOLS) + COLS_BETWEEN_BUILDINGS));
		}
		else if(firstGeneratedBuilding == 3) {
			smallBuildingPossition(rand(1,7), rand(1,5));
			middleBuildingPossition(((smallBuildingPosRow + GameConfig.SMALLBUILDINGROWS) + ROWS_BETWEEN_BUILDINGS), rand(1,7));
			largeBuildingPossition(rand(1,4), (smallBuildingPosCol + GameConfig.MIDDLEBUILDINGCOLS) + COLS_BETWEEN_BUILDINGS);
		}
	}

	private static void smallBuildingPossition(int row, int col){
		smallBuildingPosRow = row;
		smallBuildingPosCol = col;
	}

	private static void middleBuildingPossition(int row, int col){
		middleBuildingPosRow = row;
		middleBuildingPosCol = col;
	}
	private static void largeBuildingPossition(int row, int col){
		largeBuildingPosRow = row;
		largeBuildingPosCol = col;
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
