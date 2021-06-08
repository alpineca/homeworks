package GameBoardObjects.buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import GameBoardObjects.materials.BlownBuilding;
import GameBoardObjects.materials.Brick;
import GameBoardObjects.materials.Column;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.parrents.GameBoardObject;
import interfaces.GameConfig;

public class MiddleBuilding extends GameBoardObject{

    protected static ArrayList<GameBoardObject> theBuildingElements;
    protected static int row;
    protected static int col;
    protected static Color color;
    protected boolean isDestroyed = false;
    private boolean isBuildingDestroyed;
    
    public MiddleBuilding() {
        super(row, col, color);
    } 

    public void spawn(int row, int col, GameBoardObject[][] gameBoard) {
        
        Color color = GameConfig.MIDDLEBUILDINGCOLOR;
		theBuildingElements = new ArrayList<GameBoardObject>();

		for(int i = 0; i < GameConfig.MIDDLEBUILDINGROWS; i++){
			for(int j = 0; j < GameConfig.MIDDLEBUILDINGCOLS; j++){
				if((i == 0 && j == 1) || (i == 1 && j == 1)){
                    Brick element = new Brick(row + i, col + j, color, false);
					gameBoard[row + i][col + j] = element;
					theBuildingElements.add(element);

				} else{
                    Column element = new Column(row + i, col + j, true, color);
					gameBoard[row + i][col + j] = element;
					theBuildingElements.add(element);
				}

			}
		}
    }

    public static ArrayList<GameBoardObject> getBuildingElements(){
        return theBuildingElements;
    }

    @Override
    public void render(Graphics g) {
                
    }

    public static void explodeThisColumn(GameBoardObject element, GameBoardObject[][] gameBoard) {
        destroyTheBuilding(gameBoard);
    }

    public boolean isBuildingDestroyed(){
        return isBuildingDestroyed;
    }

    public static void destroyTheBuilding(GameBoardObject[][] gameBoard){
        int i = 0;
        for(GameBoardObject element : theBuildingElements){
            int row = element.getRow();
            int col = element.getCol();

            element = new Ground(row, col);
            gameBoard[row][col] = element;
            theBuildingElements.set(i, element);
            i++;
        }
    }

    @Override
    public int getIndex() {
        return 0;
    }
}
