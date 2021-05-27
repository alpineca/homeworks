package processors;

import java.awt.Color;
import java.io.Console;
import java.util.Random;

import GameBoardObjects.GameBoardObject;
import GameBoardObjects.materials.Building;
import game.GameBoard;
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
		Color color = GameConfig.smallBuildingColor;

		for(int i = 0; i < GameConfig.smallBuildingRows; i++){
			for(int j = 0; j < GameConfig.smallBuildingCols; j++){
				gameBoard[row + i][col + j] = new Building(row + i, col + j, true, color);
			}
		}
	}
	private static void createMiddleBuilding(int row, int col, GameBoardObject[][] gameBoard) {
		Color color = Color.ORANGE;
		
		for(int i = 0; i < GameConfig.middleBuildingRows; i++){
			for(int j = 0; j < GameConfig.middleBuildingCols; j++){

				if((i == 0 && j == 1) || (i == 1 && j == 1)){
					gameBoard[row + i][col + j] = new Building(row + i, col + j, color, false);
				} else{
					gameBoard[row + i][col + j] = new Building(row + i, col + j, true, color);
				}

			}
		}
	}
	private static void createLargeBuilding(int row, int col, GameBoardObject[][] gameBoard) {
		Color color = GameConfig.largeBuildingColor;
		
		for(int i = 0; i < GameConfig.largeBuildingRows; i++){
			for(int j = 0; j < GameConfig.largeBuildingCols; j++){
				boolean columnFirstRow 		= i == 0 && (j == 0 || j == 2); 
				boolean columnSeccondRow 	= i == 1 && j == 1;
				boolean columnThirdRow 		= i == 2 && (j == 0 || j == 2);

				if(columnFirstRow || columnSeccondRow || columnThirdRow){
					gameBoard[row + i][col + j] = new Building(row + i, col + j, true, color);
				} else{
					gameBoard[row + i][col + j] = new Building(row + i, col + j, color, false);
				}
			}
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
