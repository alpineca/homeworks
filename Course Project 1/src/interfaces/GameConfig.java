package interfaces;

import java.awt.Color;

public interface GameConfig {

    //Window settings
    int WindowSizeX             = 767;
    int WindowSizeY             = 790;
    String ProgramTitle         = "Course Project 1st Semester";
    

    //Game board
    int rows 				    = 15;
    int cols 				    = 15;
    int tileSize			    = 50;

    //Buildings
    Color smallBuildingColor    = Color.GREEN;
    int smallBuildingRows       = 2;
    int smallBuildingCols       = 2;

    Color middleBuildingColor   = Color.BLUE;
    int middleBuildingRows       = 2;
    int middleBuildingCols       = 3;

    Color largeBuildingColor    = Color.MAGENTA;
    int largeBuildingRows       = 3;
    int largeBuildingCols       = 3;
    
}
