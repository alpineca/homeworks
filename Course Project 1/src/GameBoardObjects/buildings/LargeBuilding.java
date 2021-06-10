package GameBoardObjects.buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import GameBoardObjects.materials.Brick;
import GameBoardObjects.materials.Column;
import GameBoardObjects.materials.Ground;
import GameBoardObjects.parrents.GameBoardObject;
import interfaces.GameConfig;

public class LargeBuilding extends GameBoardObject{

    protected static ArrayList<GameBoardObject> theBuildingElements;
    protected static int row;
    protected static int col;
    protected static Color color;
    protected boolean isDestroyed = false;
    private boolean isBuildingDestroyed;
    
    public LargeBuilding() {
        super(row, col, color);
    } 

    public void spawn(int row, int col, GameBoardObject[][] gameBoard) {
        
        Color color = GameConfig.LARGEBUILDINGCOLOR;
		theBuildingElements = new ArrayList<GameBoardObject>();

		for(int i = 0; i < GameConfig.LARGEBUILDINGROWS; i++){
			for(int j = 0; j < GameConfig.LARGEBUILDINGCOLS; j++){
				boolean columnFirstRow 		= i == 0 && (j == 0 || j == 2); 
				boolean columnSeccondRow 	= i == 1 && j == 1;
				boolean columnThirdRow 		= i == 2 && (j == 0 || j == 2);

				if(columnFirstRow || columnSeccondRow || columnThirdRow){
                    Column element = new Column(row + i, col + j, true, color);
					gameBoard[row + i][col + j] = element;
					theBuildingElements.add(element);
				} else{
                    Brick element = new Brick(row + i, col + j, color, true);
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
        int remainingColumns = 0;

        int row = element.getRow();
        int col = element.getCol();

        int elementIndex = theBuildingElements.indexOf(element);
        element = new Ground(row, col);
        theBuildingElements.set(elementIndex, element);
        gameBoard[row][col] = element;

        for(GameBoardObject elements : theBuildingElements){
            try {
                if(elements instanceof Column){
                    remainingColumns++;
                }
            } catch (Exception e) {
                //
            }
        }

        if(remainingColumns == 0){
            destroyTheBuilding(gameBoard);
        }
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
