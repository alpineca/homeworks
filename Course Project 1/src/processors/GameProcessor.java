package processors;

import GUI.GUI;
import GameBoardObjects.parrents.GameBoardObject;
import enums.ResultEnum;

public class GameProcessor {
	
	public static void endGame(ResultEnum result) {
		
		GUI changeCondition = GUI.getGUIInstance();
		changeCondition.conditionGameOver(result);
		
	}

}
