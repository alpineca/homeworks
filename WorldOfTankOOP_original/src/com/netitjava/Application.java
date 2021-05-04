package com.netitjava;

import com.netitjava.level.manager.LevelManager;
import com.netitjava.level.parent.Level;
import com.netitjava.manager.GameBoardManager;
import com.netitjava.util.Console;

public class Application {
	
	public static String transformNumberDificultyToStringDificulty(int dificultyIndex) {
		if(dificultyIndex == 1) return LevelManager.EASY_LEVEL;
		if(dificultyIndex == 2) return LevelManager.NORMAL_LEVEL;
		if(dificultyIndex == 3) return LevelManager.HARD_LEVEL;
		return "normal";
	}
	
	public static void main(String[] args) {
		
		Console.lognl("Please select game dificulty");
		Console.lognl("(1)	Easy");
		Console.lognl("(2)	Normal");
		Console.lognl("(3)	Hard");
		int dificultyIndex = Console.inputInt();
		
		String dificultyComand = transformNumberDificultyToStringDificulty(dificultyIndex);
		
		(new GameBoardManager()).start(dificultyComand);
		
		
	}	
}
