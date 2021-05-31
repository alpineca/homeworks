package processors;

import java.util.ArrayList;
import java.util.Random;

import GameBoardObjects.ArmyUnit;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.enemys.Petkan;
import GameBoardObjects.materials.Ground;
import interfaces.GameConfig;

public class EnemyProcessor {
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
		int direction 				= moveRandomDirection();

        boolean isMoveLeftValid 	= false;
        boolean isMoveRightValid 	= false;
        boolean isMoveUpValid 		= false;
        boolean isMoveDownValid 	= false;

        
        try {
            isMoveLeftValid 	= ((enemyCol - 1) >= 0) && (gameBoard[enemyRow][enemyCol - 1] instanceof Ground);
            isMoveRightValid 	= ((enemyCol + 1) <= GameConfig.cols) && (gameBoard[enemyRow][enemyCol + 1] instanceof Ground);
            isMoveUpValid 		= ((enemyRow - 1) >= 0) && (gameBoard[enemyRow - 1][enemyCol] instanceof Ground);
            isMoveDownValid 	= ((enemyRow + 1) <= GameConfig.rows) && (gameBoard[enemyRow + 1][enemyCol] instanceof Ground);
            
        } catch (Exception e) {
            
        }


		//Move LEFT
		if(direction == 1 && isMoveLeftValid){
            
			enemy.setCol(enemyTemp.getCol() - 1);
			gameBoard[enemyRow][enemyTemp.getCol() - 1] = enemy;
            checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
			gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
			return;
		}
		//Move RIGHT
		if(direction == 2 && isMoveRightValid){
			enemy.setCol(enemyTemp.getCol() + 1);
			gameBoard[enemyRow][enemyTemp.getCol() + 1] = enemy;
            checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
			gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
			return;
		}
		//Move UP
		if(direction == 3 && isMoveUpValid){
			enemy.setRow(enemyTemp.getRow() - 1);
			gameBoard[enemyTemp.getRow() - 1][enemyCol] = enemy;
            checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
			gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
			return;
		}
		//Move DOWN
		if(direction == 4 && isMoveDownValid){
			enemy.setRow(enemyTemp.getRow() + 1);
			gameBoard[enemyTemp.getRow() + 1][enemyCol] = enemy;
            checkForArmyUnits(enemy.getRow(), enemy.getCol(), gameBoard, enemy);
			gameBoard[enemyTemp.getRow()][enemyTemp.getCol()] = new Ground(enemyTemp.getRow(), enemyTemp.getCol());
			return;
		} 
		else{
			moveEnemy(gameBoard, enemyUnits);
		}
	}

    private static void checkForArmyUnits(int row, int col, GameBoardObject[][] gameBoard, GameBoardObject enemy){
        for(int i = (row -1); i < (row + 2); i++){
            for(int j = col -1; j < col + 2; j++){
                boolean isUnderRow = i < 0;
                boolean isUnderCol = j < 0;
                boolean isOverRow  = i > (GameConfig.rows - 1);
                boolean isOverCol  = j > (GameConfig.cols - 1);

                if(isUnderRow)  i = + 1;
                if(isUnderCol)  j = + 1;
                if(isOverRow)   i = - 1;
                if(isOverCol)   j = - 1;

                if(gameBoard[i][j] instanceof ArmyUnit){
                    trigger(enemy);
                } 
            }
        }
    }

    private static void trigger(GameBoardObject enemy) {
        // GameBoardObject enemy = enemyUnits.get(0);
        ((Petkan)enemy).trigger();
    }

    private static int rand(int randBound) {
		Random rand = new Random();
		return rand.nextInt(randBound);
		
	}

	private static int moveRandomDirection() {
		Random rand = new Random();
		int randDirection = rand.nextInt(4);
		randDirection += 1;

		return randDirection;
	}
}
