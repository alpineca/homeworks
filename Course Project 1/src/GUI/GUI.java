package GUI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import interfaces.GameConfig;

public class GUI extends JFrame {
    public GUI() {
		
		this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setTitle(GameConfig.ProgramTitle);
		this.setSize(GameConfig.WindowSizeX, GameConfig.WindowSizeY);
        this.setResizable(false);
        
        this.setBackground(Color.BLACK);
        this.setLocation(0,0);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
