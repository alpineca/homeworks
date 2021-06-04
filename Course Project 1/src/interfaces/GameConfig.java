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
    Color groundColor           = new Color(10, 10, 10);
    Color tileBorderColor       = new Color(23, 23, 22);
    
    //Buildings
    Color smallBuildingColor    = new Color(240, 108, 26);
    int smallBuildingRows       = 2;
    int smallBuildingCols       = 2;

    Color middleBuildingColor   = new Color(74, 150, 176);
    int middleBuildingRows       = 2;
    int middleBuildingCols       = 3;

    Color largeBuildingColor    = new Color(74, 176, 128);
    int largeBuildingRows       = 3;
    int largeBuildingCols       = 3;
    
}
