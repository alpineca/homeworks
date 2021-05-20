package GameBoardObjects;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GameBoardObject {
	protected int row;
	protected int col;
	protected Color color;
	
	public GameBoardObject(int row, int col, Color color) {
		this.row = row;
		this.col = col;
	}
	
	public abstract void render(Graphics g);

}
