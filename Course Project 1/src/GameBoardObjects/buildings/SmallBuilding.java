package GameBoardObjects.buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import GameBoardObjects.materials.Column;
import GameBoardObjects.parrents.GameBoardObject;
import interfaces.GameConfig;

public class SmallBuilding extends GameBoardObject{

    protected static ArrayList<GameBoardObject> theBuildingElements;
    protected static int row;
    protected static int col;
    protected static Color color;
    protected static boolean isDestroyed = false;
    
    public SmallBuilding() {
        super(row, col, color);
    } 

    public void Spawn(int row, int col, GameBoardObject[][] gameBoard, ArrayList<GameBoardObject> buildings) {
        
        Color color = GameConfig.SMALLBUILDINGCOLOR;
		theBuildingElements = new ArrayList<GameBoardObject>();

		for(int i = 0; i < GameConfig.SMALLBUILDINGROWS; i++){
			for(int j = 0; j < GameConfig.SMALLBUILDINGCOLS; j++){
                Column element = new Column(row + i, col + j, true, color);
				gameBoard[row + i][col + j] = element;
				theBuildingElements.add(element);
				buildings.add(element);
			}
		}
    }

    public static ArrayList<GameBoardObject> getBuildingElements(){
        return theBuildingElements;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        
    }
    
}
