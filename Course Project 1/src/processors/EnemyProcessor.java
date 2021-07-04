package processors;
import java.util.Random;

import GameBoardObjects.enemys.Petkan;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.parrents.ArmyUnit;
import GameBoardObjects.parrents.GameBoardObject;
import enums.DirectionsEnum;
import game.GameBoard;
import interfaces.GameConfig;

public class EnemyProcessor {
	protected static int loadGunshot = 0;

    public static void spawnEnemyUnits(GameBoardObject[][] gameBoard) {
		boolean isGroundField = false;
		int row, col;

		while(isGroundField == false){
			row = rand(GameConfig.ROWS);
			col = rand(GameConfig.COLS);

			if(gameBoard[row][col] instanceof Ground){
				GameBoardObject enemy = new Petkan(row, col);
                GameBoard.setEnemy(enemy);
				gameBoard[row][col] = enemy;
                isGroundField = true;
			}
		}

	}

    public static void moveEnemy(GameBoardObject[][] gameBoard, GameBoardObject enemy){
		GameBoardObject enemyTemp 	= UnitsProcessor.clone(enemy);
		int enemyRow 				= enemy.getRow();
		int enemyCol 				= enemy.getCol();

		boolean isMoveCorrect 		= false;
		boolean enemyTriggered 		= ((Petkan)enemy).getVisibility();

		while(isMoveCorrect == false && enemyTriggered == false){
			DirectionsEnum direction = pickDirection();

			if(direction.equals(DirectionsEnum.LEFT)){
				int newCol = (enemyTemp.getCol() - 1);
				int newColCheck = newCol < 0 ? 0 : newCol;
				
				if(gameBoard[enemyRow][newColCheck] instanceof Ground){

					moveOnColumn(gameBoard, enemy, enemyTemp, enemyRow, newColCheck);
					isMoveCorrect = true;
					return;
				}

			}
			if(direction.equals(DirectionsEnum.RIGHT)){
				int newCol 		= (enemyTemp.getCol() + 1);
				int maxColBound = GameConfig.COLS - 1;
				int newColCheck = newCol > maxColBound ? maxColBound : newCol;

				if(gameBoard[enemyRow][newColCheck] instanceof Ground){

					moveOnColumn(gameBoard, enemy, enemyTemp, enemyRow, newColCheck);	
					isMoveCorrect = true;
					return;
				}

			}
			if(direction.equals(DirectionsEnum.UP)){
				int newRow 		= (enemyTemp.getRow() - 1);
				int newRowCheck = newRow < 0 ? 0 : newRow;

				if(gameBoard[newRowCheck][enemyCol] instanceof Ground){
					
					moveOnRow(gameBoard, enemy, enemyTemp, enemyCol, newRowCheck);
					isMoveCorrect = true;
					return;
				}

			}
			if(direction.equals(DirectionsEnum.DOWN)){
				int newRow 		= (enemyTemp.getRow() + 1);
				int maxRowBound = GameConfig.ROWS - 1;
				int newRowCheck = newRow > maxRowBound ? maxRowBound : newRow;

				if(gameBoard[newRowCheck][enemyCol] instanceof Ground){

					moveOnRow(gameBoard, enemy, enemyTemp, enemyCol, newRowCheck);
					isMoveCorrect = true;
					return;
				}

			} 
			isMoveCorrect = false;
		}
	}

	private static void moveOnRow(GameBoardObject[][] gameBoard, GameBoardObject enemy, GameBoardObject enemyTemp,
			int enemyCol, int newRowCheck) {

		enemy.setRow(newRowCheck);
		gameBoard[newRowCheck][enemyCol] = enemy;
		checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
		gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());

	}

	private static void moveOnColumn(GameBoardObject[][] gameBoard, GameBoardObject enemy, GameBoardObject enemyTemp,
			int enemyRow, int newColCheck) {

		enemy.setCol(newColCheck);
		gameBoard[enemyRow][newColCheck] = enemy;
		checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
		gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());

	}

    private static void checkForArmyUnits(int row, int col, GameBoardObject[][] gameBoard, GameBoardObject enemy){
        int minRowBound = row-1;
		int maxRowBound = row+1;

		int minColBound = col-1;
		int maxColBound = col+1;

		for(int i = minRowBound; i <= maxRowBound; i++){
			for(int j = minColBound; j <= maxColBound; j++){
				try {
					if(gameBoard[i][j] instanceof ArmyUnit){
						((Petkan)enemy).trigger();
						break;
					} 
				} catch (Exception e) {

				}					
			}
		}
    }

	private static DirectionsEnum pickDirection(){
		int randomDirection = rand(3) + 1;
		if(randomDirection == 1) return DirectionsEnum.LEFT;
		if(randomDirection == 2) return DirectionsEnum.RIGHT;
		if(randomDirection == 3) return DirectionsEnum.UP;
		return DirectionsEnum.DOWN;
	}
	
	private static int rand(int randBound) {
		Random rand = new Random();
		return rand.nextInt(randBound);
		
	}
}