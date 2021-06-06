package interfaces;

import java.awt.Color;

public interface GameConfig {

    //Window settings
    int WINDOWSIZEX             = 767;
    int WINDOWSIZEY             = 790;
    String PROGRAMTITLE         = "Course Project 1st Semester";

    
    
    //Game board
    int ROWS 				    = 15;
    int COLS 				    = 15;
    int TILESIZE			    = 50;
    Color GROUNDCOLOR           = new Color(10, 10, 10);
    Color BLOWNBUILDINGCOLOR    = new Color(20, 20, 20);
    Color TILEBORDERCOLOR       = new Color(23, 23, 22);
    
    //Buildings
    Color SMALLBUILDINGCOLOR    = new Color(240, 108, 26);
    int SMALLBUILDINGROWS       = 2;
    int SMALLBUILDINGCOLS       = 2;

    Color MIDDLEBUILDINGCOLOR   = new Color(74, 150, 176);
    int MIDDLEBUILDINGROWS       = 2;
    int MIDDLEBUILDINGCOLS       = 3;

    Color LARGEBUILDINGCOLOR    = new Color(74, 176, 128);
    int LARGEBUILDINGROWS       = 3;
    int LARGEBUILDINGCOLS       = 3;
    
}
