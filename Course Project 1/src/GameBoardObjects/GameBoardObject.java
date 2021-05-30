package GameBoardObjects;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GameBoardObject{
	protected int row;
	protected int col;
	protected Color color;
	protected Color borderColor = Color.DARK_GRAY;
	protected GameBoardObject element;
	
	protected GameBoardObject(int row, int col, Color color) {
		this.row 		= row;
		this.col 		= col;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Color getColor() {
		return color;
	}

	public String colorToString(){
		return getColor().toString();
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public void setRowCol(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public GameBoardObject getInstance() {
		return this;
	}

	
	public abstract int getIndex();
	public abstract void setIndex(int index);
	public abstract void render(Graphics g);
	
	

}
