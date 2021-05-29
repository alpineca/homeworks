package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import GameBoardObjects.materials.Ground;
import enums.Direction;
import GameBoardObjects.GameBoardObject;
import GameBoardObjects.enemys.Petkan;
import interfaces.GameConfig;
import processors.BuildProcessor;
import processors.UnitsProcessor;

public class GameBoard extends JPanel implements KeyListener{
	
	protected static GameBoardObject[][] gameBoard;
	public static ArrayList<GameBoardObject> armyUnits 		= new ArrayList<>();
	public static ArrayList<GameBoardObject> buildings 		= new ArrayList<>();
	public static ArrayList<GameBoardObject> enemyUnits 	= new ArrayList<>();

	public static int fireCounter = 1000;

	public GameBoard() {
		this.bootstrap();
	}
	
	private void bootstrap() {
		
		gameBoard = new GameBoardObject[GameConfig.rows][GameConfig.cols];
		
		for(int row = 0; row < GameConfig.rows; row++) {
			for(int col = 0; col < GameConfig.cols; col++) {
				gameBoard[row][col] = new Ground(row, col);
			}
		}
		
		UnitsProcessor.spawnArmyUnits(	gameBoard, armyUnits);
		UnitsProcessor.spawnEnemyUnits(	gameBoard, enemyUnits);
		BuildProcessor.spawnBuildings(	gameBoard, buildings);

		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int row = 0; row < GameConfig.rows; row++) {
			for(int col = 0; col < GameConfig.cols; col++) {
				gameBoard[row][col].render(g);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char moveInput = e.getKeyChar();
		if(moveInput == 'a' || moveInput == 'A') {
			UnitsProcessor.move(Direction.LEFT, gameBoard, armyUnits, buildings, enemyUnits);
			fireCountdown();
		}
		if(moveInput == 'd' || moveInput == 'D') {
			UnitsProcessor.move(Direction.RIGHT, gameBoard, armyUnits, buildings, enemyUnits);
			fireCountdown();
		}
		if(moveInput == 'w' || moveInput == 'W') {
			UnitsProcessor.move(Direction.UP, gameBoard, armyUnits, buildings, enemyUnits);
			fireCountdown();
		}
		if(moveInput == 's' || moveInput == 'S') {
			UnitsProcessor.move(Direction.DOWN, gameBoard, armyUnits, buildings, enemyUnits);
			fireCountdown();
		}
		
		if(moveInput == 'e' || moveInput == 'E') {
				Petkan enemy = (Petkan) enemyUnits.get(0);
				enemy.trigger();
		}
		this.repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	public static void setFireCounter() {
		if(fireCounter > 10){
			fireCounter = 5;
		} else{
			fireCounter = fireCounter;
		}
	}

	private static void fireCountdown(){
		if(fireCounter != 0){
			fireCounter--;
		} else if(fireCounter == 0){
			Petkan enemy =  (Petkan) enemyUnits.get(0);
			enemy.fire();
			fireCounter = 1000;
		}
	}


}
