package processors;

import java.awt.Color;
import java.util.Random;

import GameBoardObjects.GameBoardObject;
import GameBoardObjects.materials.Building;
import interfaces.GameConfig;

public class BuildProcessor {
	
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
		
	
	public static void spawnBuildings(GameBoardObject[][] gameBoard) {
		calculatePossitions();
				
		createSmallBuilding(smallBuildingPosRow, smallBuildingPosCol, gameBoard);
		createMiddleBuilding(middleBuildingPosRow, middleBuildingPosCol, gameBoard);
		createLargeBuilding(largeBuildingPosRow, largeBuildingPosCol, gameBoard);
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

	private static void createSmallBuilding(int row, int col, GameBoardObject[][] gameBoard) {
		Color color = Color.GREEN;
		
		gameBoard[row][col] 			= new Building(row, col, color);
		gameBoard[row][col + 1] 		= new Building(row, col + 1, color);
		gameBoard[row + 1][col] 		= new Building(row + 1, col, color);
		gameBoard[row + 1][col + 1] 	= new Building(row + 1, col + 1, color);
	}
	private static void createMiddleBuilding(int row, int col, GameBoardObject[][] gameBoard) {
		Color color = Color.BLUE;
		
		gameBoard[row][col] 			= new Building(row, col, color);
		gameBoard[row][col + 1] 		= new Building(row, col + 1, color);
		gameBoard[row][col + 2] 		= new Building(row, col + 2, color);
		gameBoard[row + 1][col] 		= new Building(row + 1, col, color);
		gameBoard[row + 1][col + 1] 	= new Building(row + 1, col + 1, color);
		gameBoard[row + 1][col + 2] 	= new Building(row + 1, col + 2, color);
	}
	private static void createLargeBuilding(int row, int col, GameBoardObject[][] gameBoard) {
		Color color = Color.MAGENTA;
		
		gameBoard[row][col] 			= new Building(row, col, color);
		gameBoard[row][col + 1] 		= new Building(row, col + 1, color);
		gameBoard[row][col + 2] 		= new Building(row, col + 2, color);
		gameBoard[row + 1][col] 		= new Building(row + 1, col, color);
		gameBoard[row + 1][col + 1] 	= new Building(row + 1, col + 1, color);
		gameBoard[row + 1][col + 2] 	= new Building(row + 1, col + 2, color);
		gameBoard[row + 2][col] 		= new Building(row + 2, col, color);
		gameBoard[row + 2][col + 1] 	= new Building(row + 2, col + 1, color);
		gameBoard[row + 2][col + 2] 	= new Building(row + 2, col + 2, color);
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
