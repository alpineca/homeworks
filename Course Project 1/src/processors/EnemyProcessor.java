package processors;

import java.util.ArrayList;
import java.util.Random;

import GameBoardObjects.ArmyUnit;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.enemys.Petkan;
import GameBoardObjects.materials.Ground;
import interfaces.GameConfig;

public class EnemyProcessor {
	protected static int loadGunshot = 0;

    public static void spawnEnemyUnits(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> enemyUnits) {
		boolean isEmptyField = false;
		int row, col;

		while(isEmptyField == false){
			row = rand(GameConfig.rows);
			col = rand(GameConfig.cols);

			if(gameBoard[row][col] instanceof Ground){
                GameBoardObject unit = new Petkan(row, col);
				gameBoard[row][col] = unit;
				enemyUnits.add(unit);
                isEmptyField = true;
			}
		}

	}

    public static void moveEnemy(GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> enemyUnits){
		GameBoardObject enemy 		= enemyUnits.get(0);
		GameBoardObject enemyTemp 	= UnitsProcessor.clone(enemy);
		int enemyRow 				= enemy.getRow();
		int enemyCol 				= enemy.getCol();

		boolean isMoveCorrect 		= false;

		while(isMoveCorrect == false){
			int direction 			= rand(5);

			//Move LEFT
			if(direction == 1){
				int newCol = (enemyTemp.getCol() - 1);
				int newColCheck = newCol < 0 ? 0 : newCol;
				
				if(gameBoard[enemyRow][newColCheck] instanceof Ground){

					enemy.setCol(enemyTemp.getCol() - 1);
					gameBoard[enemyRow][enemyTemp.getCol() - 1] = enemy;
					checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
					gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
	
					isMoveCorrect = true;
					return;
				}

			}
			//Move RIGHT
			if(direction == 2){
				int newCol 		= (enemyTemp.getCol() + 1);
				int maxColBound = GameConfig.cols - 1;
				int newColCheck = newCol > maxColBound ? maxColBound : newCol;

				if(gameBoard[enemyRow][newColCheck] instanceof Ground){

					enemy.setCol(newColCheck);
					gameBoard[enemyRow][newColCheck] = enemy;
					checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
					gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
	
					isMoveCorrect = true;
					return;
				}

			}
			//Move UP
			if(direction == 3){
				int newRow 		= (enemyTemp.getRow() - 1);
				int newRowCheck = newRow < 0 ? 0 : newRow;

				if(gameBoard[newRowCheck][enemyCol] instanceof Ground){
					
					enemy.setRow(enemyTemp.getRow() - 1);
					gameBoard[newRowCheck][enemyCol] = enemy;
					checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
					gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
	
					isMoveCorrect = true;
					return;
				}

			}
			//Move DOWN
			if(direction == 4){
				int newRow 		= (enemyTemp.getRow() + 1);
				int maxRowBound = GameConfig.rows - 1;
				int newRowCheck = newRow > maxRowBound ? maxRowBound : newRow;

				if(gameBoard[newRowCheck][enemyCol] instanceof Ground){

					enemy.setRow(newRowCheck);
					gameBoard[newRowCheck][enemyCol] = enemy;
					checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
					gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
	
					isMoveCorrect = true;
					return;
				}

			} 
			isMoveCorrect = false;
		}
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
						trigger(enemy);
					} 
				} catch (Exception e) {

				}					
			}
		}
    }
	
    private static void trigger(GameBoardObject enemy) {
		((Petkan)enemy).trigger();
    }
	
	private static int rand(int randBound) {
		Random rand = new Random();
		return rand.nextInt(randBound);
		
	}
}