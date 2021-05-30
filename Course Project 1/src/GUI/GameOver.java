package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Key;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import game.GameBoard;
import interfaces.GameConfig;

public class GameOver extends JPanel{

	public GameOver() {
        Font font = new Font("", Font.ITALIC, 20);
		JLabel endGameText = new JLabel();
        this.add(endGameText);
		
		this.setSize(GameConfig.WindowSizeX, GameConfig.WindowSizeY);
        this.setBackground(Color.BLACK);
		this.setVisible(true);
        
        this.setLocation(0,0);
	}

}