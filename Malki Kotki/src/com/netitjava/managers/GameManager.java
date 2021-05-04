package com.netitjava.managers;

import java.util.ArrayList;

import com.netitjava.enums.GameColorEnum;
import com.netitjava.enums.PlayerColorEnum;
import com.netitjava.exceptions.PlacementNotCorrectException;
import com.netitjava.exceptions.PlacementNotPosibleException;
import com.netitjava.gameboard.GameBoardObject;
import com.netitjava.gameboard.pieces.Barricade;
import com.netitjava.gameboard.pieces.Dworf;
import com.netitjava.gameboard.pieces.Elf;
import com.netitjava.gameboard.pieces.Knight;
import com.netitjava.gameboard.pieces.Piece;
import com.netitjava.gameboard.pieces.Wall;
import com.netitjava.gameboard.tiles.GameBoardTile;
import com.netitjava.util.Console;

public class GameManager {

	private GameBoardObject[][] gameBoard;
	public final int ROW_COUNT 			= 7;
	public final int COL_COUNT 			= 9;
	private final int OBSTACLE_COUNT_BOUND 	= 5;
	
	private int gameStarted 				= 0;
	private int mainGameStarted 			= 0;
	
	private ArrayList<PlayerManager> playerManagerCollection;
	private int activePlayerIndex = 0;
		
	public GameManager() {
		
		this.gameBoard = new GameBoardObject[this.ROW_COUNT][this.COL_COUNT];

		// input
		this.playerManagerCollection = new ArrayList<PlayerManager>(); 
		this.playerManagerCollection.add(new PlayerManager(GameColorEnum.BLACK));
		this.playerManagerCollection.add(new PlayerManager(GameColorEnum.RED));
	}
	
	public void startGame() {
		
		// 0. Bootstrap main game board
		if(gameStarted == 0) {
			this.bootstrap();
			gameStarted++;
		}
		this.render();
		
		if(mainGameStarted == 0) {
			while(gamePhasePiecePlacementRun(this.getActivePlayer())) {
				
				try {
				
					Console.log(this.getActivePlayer().getName() + " turn:");		
					this.gamePhasePiecePlacement(this.getActivePlayer());
					this.endTurn();
					this.render();
				
				}
				catch (Exception e) {
					
				}
			}
			//generateObstacle();
			gamePhaseMainGame(this.getActivePlayer());
		}
		else if(mainGameStarted == 1) {
			gamePhaseMainGame(this.getActivePlayer());
		}
	}
	
	private boolean gamePhasePiecePlacementRun(PlayerManager activePlayer) {
		return activePlayer.hasUnplacedPieces() == false;
	}
	
	private void gamePhasePiecePlacement(PlayerManager activePlayer) throws Exception {

		try {

				Console.log("You have the folowing pieces");
				
				// 1. Select player piece
				ArrayList<Piece> collection = activePlayer.getUnplacedPieceCollection();
				for(int i = 0; i < collection.size(); i++) {
					Console.log("(" + i + ") " + collection.get(i).getPieceName() );
				}
				
				int pieceId 	= Console.promtInt("Which unit you wish to place");
				int pieceRow 	= Console.promtInt("Row: ? "); 
				int pieceCol 	= Console.promtInt("Col: ? ");
				
				// get gameBord tile 
				GameBoardTile activeTile = (GameBoardTile) this.gameBoard[pieceRow][pieceCol];
						
				activePlayer.place(pieceId, activeTile, this.gameBoard);
			} 
			catch (PlacementNotCorrectException e) {
				Console.log(">> Placement is not correct. Yopu must select the right color");
			}			
			catch (PlacementNotPosibleException e) {
				Console.log("%% Placement is not posible. Yopu must select empty tile");
			} 
			catch(Exception ex) {
				Console.log("## Something when wrong");
			}
	}
	
	private void gamePhaseMainGame(PlayerManager activePlayer) {
		Console.clear(20);
		Console.log("\n MAIN GAME IS STARTED \n");
		generateObstacle();
		render();
		Console.log("ITS BATTLE TIME!\n");
		
		Console.log(activePlayer.getName() + ", you have folowing alive pieces");
		
		ArrayList<Piece> collection = activePlayer.getAlivePieceCollection();
		for(int i = 0; i < collection.size(); i++) {
			Console.log("(" + i + ") " + collection.get(i).getPieceName() );
		}
		
		int pieceId 	= Console.promtInt("Which unit you wish to move");
		
		activePlayer.moveSelectedPiece(pieceId, this.gameBoard);
		render();
		
		
		
	}
	
	private PlayerManager getActivePlayer() {
		return this.playerManagerCollection.get(activePlayerIndex);
	}
	
	private GameBoardTile getActiveTile(int row, int col) {
		return (GameBoardTile) this.gameBoard[row][col];
	}
	
	private void endTurn() {
		
		activePlayerIndex++;
		
		if(this.playerManagerCollection.size() == activePlayerIndex) {
			activePlayerIndex = 0;	
		}
	}	
	
	
	private void render() {
		drawColNumbers();
		
		for(int row = 0; row < this.ROW_COUNT; row++) {
			
			System.out.print(row + " ");
			for(int col = 0; col < this.COL_COUNT; col++) {
				System.out.print(" " + this.gameBoard[row][col].render() + " ");
			}
			System.out.println("");
		}
		
		drawColNumbers();
	}

	
	private void bootstrap() {
		
		for(int row = 0; row < this.ROW_COUNT; row++) {
			for(int col = 0; col < this.COL_COUNT; col++) {
				
				GameColorEnum gameTileColor = getGameBordTileColor(row, col); 
				this.gameBoard[row][col] = new GameBoardTile(row, col, gameTileColor);
			}
		}
	}
	
	
	
	private void generateObstacle() {
		int randObstacleCount = Console.randomNumber(0, OBSTACLE_COUNT_BOUND);
		
		int[] randomPosition = new int[randObstacleCount];
		randomPosition = randomPositions(randObstacleCount);
		int randomObstacle = Console.randomNumber(0, 2); 
		
		int randomPositionIndex = 0;
		
		if(randObstacleCount == 1 ) {
			int randomRow = Console.randomNumber(2, 5);
			int randomCol = Console.randomNumber(0, COL_COUNT);
			
			this.gameBoard[randomRow][randomCol] = new Wall(randomRow, randomCol);
		}
		else {
			for(int row = 2; row < 5; row++) {
				for(int col = 0; col < COL_COUNT; col++) {

					for(int i = 0; i < randomPosition.length; i++) {
						if(randomPositionIndex == randomPosition[i]) {
							if(randomObstacle == 0) {
								this.gameBoard[row][col] = new Wall(row, col);
							}
							else if(randomObstacle == 1) {
								this.gameBoard[row][col] = new Barricade(row, col);
							}
						}
					}
					
					randomPositionIndex++;
				}
			}
		}
	}
	private GameColorEnum getGameBordTileColor(int row, int col) {
		
		if(row == 0 || row == 1) {
			return GameColorEnum.RED;
		}
		
		if(row == 5 || row == 6) {
			return GameColorEnum.BLACK;
		}			
		
		return GameColorEnum.NEUTRAL;
	}
	
	private int[] randomPositions(int randObstacleCount) {
		int[] randomPositions = new int[randObstacleCount];
		
		for(int i = 0; i < randObstacleCount; i++) {
			randomPositions[i] = Console.randomNumber(0, 27);
		}
		
		return randomPositions;
	}
	private void drawColNumbers() {
		System.out.print("  ");
		for(int col = 0; col < this.COL_COUNT; col++) {
			System.out.print(" " + col + " ");
		}
		System.out.println("");
	}
}
