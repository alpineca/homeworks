package processors;

import java.awt.Color;
import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

import GameBoardObjects.buildings.SmallBuilding;
import GameBoardObjects.materials.Brick;
import GameBoardObjects.parrents.GameBoardObject;
import game.GameBoard;
import interfaces.GameConfig;

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
		
	
	public static void spawnBuildings(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
		calculatePossitions();
				
		(new SmallBuilding()).Spawn(smallBuildingPosRow, smallBuildingPosCol, gameBoard, buildings);
		createMiddleBuilding(middleBuildingPosRow, middleBuildingPosCol, gameBoard, buildings);
		createLargeBuilding(largeBuildingPosRow, largeBuildingPosCol, gameBoard, buildings);
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

	private static void createMiddleBuilding(int row, int col, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
		Color color = Color.ORANGE;
		ArrayList<GameBoardObject> middleBuilding = GameBoard.getMiddleBuilding();
		
		for(int i = 0; i < GameConfig.MIDDLEBUILDINGROWS; i++){
			for(int j = 0; j < GameConfig.MIDDLEBUILDINGCOLS; j++){

				if((i == 0 && j == 1) || (i == 1 && j == 1)){
					gameBoard[row + i][col + j] = new Brick(row + i, col + j, color, false);
					middleBuilding.add(new Brick(row + i, col + j, color, false));
					buildings.add(new Brick(row + i, col + j, color, false));

				} else{
					gameBoard[row + i][col + j] = new Brick(row + i, col + j, true, color);
					middleBuilding.add(new Brick(row + i, col + j, true, color));
					buildings.add(new Brick(row + i, col + j, true, color));
				}

			}
		}
	}
	private static void createLargeBuilding(int row, int col, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
		Color color = GameConfig.LARGEBUILDINGCOLOR;
		ArrayList<GameBoardObject> largeBuilding = GameBoard.getLargeBuilding();
		
		for(int i = 0; i < GameConfig.LARGEBUILDINGROWS; i++){
			for(int j = 0; j < GameConfig.LARGEBUILDINGCOLS; j++){
				boolean columnFirstRow 		= i == 0 && (j == 0 || j == 2); 
				boolean columnSeccondRow 	= i == 1 && j == 1;
				boolean columnThirdRow 		= i == 2 && (j == 0 || j == 2);

				if(columnFirstRow || columnSeccondRow || columnThirdRow){
					gameBoard[row + i][col + j] = new Brick(row + i, col + j, true, color);
					largeBuilding.add(new Brick(row + i, col + j, true, color));
					buildings.add(new Brick(row + i, col + j, true, color));
				} else{
					gameBoard[row + i][col + j] = new Brick(row + i, col + j, color, true);
					largeBuilding.add(new Brick(row + i, col + j, color, true));
					buildings.add(new Brick(row + i, col + j, color, true));
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
