package com.netitjava.managers;

import java.util.ArrayList;
import com.netitjava.enums.*;
import com.netitjava.exceptions.*;
import com.netitjava.gameboard.*;
import com.netitjava.gameboard.pieces.*;
import com.netitjava.gameboard.tiles.GameBoardTile;
import com.netitjava.util.*;

public class GameManager {

	public static final int ROW_COUNT 					= 7;
	public static final int COL_COUNT 					= 9;
	private static final int OBSTACLE_COUNT_BOUND 		= 5;
	
	private int gameStarted 					= 0;
	private int mainGameStarted 				= 0;
	private int announced 						= 0;
	
	private ArrayList<PlayerManager> playerManagerCollection;
	private int activePlayerIndex = 0;
		
	public GameManager() {
		this.playerManagerCollection = new ArrayList<PlayerManager>(); 
		this.playerManagerCollection.add(new PlayerManager(GameColorEnum.BLACK));
		this.playerManagerCollection.add(new PlayerManager(GameColorEnum.RED));
	}
	
	public void startGame() {
		
		if(gameStarted == 0) {
			this.bootstrap();
			gameStarted++;
			GameBoard.getInstance().renderForPlacement(this.getActivePlayer());
		}
		
		if(mainGameStarted == 0) {
			while(gamePhasePiecePlacementRun(this.getActivePlayer())) {
				
				try {
				
					Console.log(this.getActivePlayer().getName() + " turn:\n");		
					this.gamePhasePiecePlacement(this.getActivePlayer());
					this.endTurn();
					GameBoard.getInstance().renderForPlacement(this.getActivePlayer());
				
				}
				catch (Exception e) {
					
				}
			}
			mainGameStarted++;
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

				Console.logLn("You have the folowing pieces\n");
				
				ArrayList<Piece> collection = activePlayer.getUnplacedPieceCollection();
				for(int i = 0; i < collection.size(); i++) {
					Console.logLn("(" + i + ") " + collection.get(i).getPieceName() );
				}
				
				int pieceId 	= Console.promtInt("\nWhich unit you wish to place");
				int pieceRow 	= Console.promtInt("Row: ? "); 
				int pieceCol 	= Console.promtInt("Col: ? ");
				
				GameBoardTile activeTile = (GameBoardTile) GameBoard.getInstance().getElement(pieceRow, pieceCol);
						
				activePlayer.place(pieceId, activeTile);
			} 
			catch (PlacementNotCorrectException e) {
				Console.logLn(">> Placement is not correct. Yopu must select the right color");
			}			
			catch (PlacementNotPosibleException e) {
				Console.logLn("%% Placement is not posible. Yopu must select empty tile");
			} 
			catch(Exception ex) {
				Console.logLn("## Something when wrong");
			}
	}
	
	private void gamePhaseMainGame(PlayerManager activePlayer) {
		if(announced == 0) announcement();
		
		Console.logLn(activePlayer.getName() + ", you have folowing alive pieces\n");
		
		ArrayList<Piece> collection = activePlayer.getAlivePieceCollection();
		for(int i = 0; i < collection.size(); i++) {
			Console.logLn("(" + i + ") " + collection.get(i).getPieceName() );
		}
		
		int pieceId;
		try {
			pieceId = Console.promtInt("\nWhich unit you wish to move");
			activePlayer.moveSelectedPiece(pieceId);
		} catch (Exception e) {
			
		}
		
		GameBoard.getInstance().render();
		this.endTurn();
		gamePhaseMainGame(this.getActivePlayer());
		
		
		
	}

	private void announcement() {
		Console.logLn("\n MAIN GAME IS STARTED \n");
		generateObstacle();
		GameBoard.getInstance().render();
		Console.logLn("ITS BATTLE TIME!\n");
		announced++;
		
	}
	
	private PlayerManager getActivePlayer() {
		return this.playerManagerCollection.get(activePlayerIndex);
	}

	private void endTurn() {
		
		activePlayerIndex++;
		
		if(this.playerManagerCollection.size() == activePlayerIndex) {
			activePlayerIndex = 0;	
		}
	}	
	
	private void bootstrap() {
		
		for(int row = 0; row < this.ROW_COUNT; row++) {
			for(int col = 0; col < this.COL_COUNT; col++) {
				
				GameColorEnum gameTileColor = getGameBordTileColor(row, col);
				GameBoard.getInstance().setElement(row, col, new GameBoardTile(row, col, gameTileColor));
			}
		}
	}
	
	private void generateObstacle() {
		int randObstacleCount = Console.randomNumber(0, OBSTACLE_COUNT_BOUND);
		
		int[] randomPosition = new int[randObstacleCount];
		randomPosition = randomPositions(randObstacleCount);
		// int randomObstacle = Console.randomNumber(0, 2); 
		
		int randomPositionIndex = 0;
		
		if(randObstacleCount == 1 ) {
			int randomRow = Console.randomNumber(2, 4);
			int randomCol = Console.randomNumber(0, COL_COUNT);
			
			if(randomCol == COL_COUNT-1) {
				GameBoard.getInstance().setElement(randomRow, randomCol, new Wall(randomRow, randomCol));
				GameBoard.getInstance().setElement(randomRow, randomCol--, new Wall(randomRow, randomCol));
			} else {
				GameBoard.getInstance().setElement(randomRow, randomCol, new Wall(randomRow, randomCol));
				GameBoard.getInstance().setElement(randomRow, randomCol++, new Wall(randomRow, randomCol));
			}
		}
		else {
			for(int row = 2; row < 5; row++) {
				for(int col = 0; col < COL_COUNT; col++) {

					for(int i = 0; i < randomPosition.length; i++) {
						if(randomPositionIndex == randomPosition[i]) {
							int randomObstacle = Console.randomNumber(0, 1);

							if(randomObstacle == 0) {
								if(col == COL_COUNT-1) {
									GameBoard.getInstance().setElement(row, col, 	new Wall(row, col));
									GameBoard.getInstance().setElement(row, col--, 	new Wall(row, col));
								} else {
									GameBoard.getInstance().setElement(row, col, 	new Wall(row, col));
									GameBoard.getInstance().setElement(row, col++, 	new Wall(row, col));
								}
							}
							else if(randomObstacle == 1) {
								if(col == COL_COUNT-1) {
									GameBoard.getInstance().setElement(row, col, 	new Barricade(row, col));
									GameBoard.getInstance().setElement(row, col--, 	new Barricade(row, col));
								} else {
									GameBoard.getInstance().setElement(row, col, 	new Barricade(row, col));
									GameBoard.getInstance().setElement(row, col++, 	new Barricade(row, col));
								}
							}
						}
					}
					
					randomPositionIndex++;
					col++;
				}
			}
		}
	}
	
	private int[] randomPositions(int randObstacleCount) {
		int[] randomPositions = new int[randObstacleCount];
		
		for(int i = 0; i < randObstacleCount; i++) {
			randomPositions[i] = Console.randomNumber(0, 27);
		}
		
		return randomPositions;
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

}
