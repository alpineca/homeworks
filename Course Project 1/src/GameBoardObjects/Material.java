package GameBoardObjects;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Material extends GameBoardObject{
	public Material(int row, int col, Color color) {
		super(row, col, color);
	}

	@Override
	public abstract void render(Graphics g);
}
