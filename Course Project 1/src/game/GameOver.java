package game;

import javax.swing.JPanel;

import GUI.GUI;
import enums.ResultEnum;
import interfaces.GameConfig;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOver extends JPanel {
	
	private static GUI userInterface;

	public GameOver(ResultEnum condition) {
		setFont(new Font("Tahoma", Font.PLAIN, 36));
		setForeground(Color.GREEN);
		setBackground(GameConfig.GROUNDCOLOR);
		setLayout(null);
		
		JLabel lblYouWin = new JLabel("YOU WIN");
		lblYouWin.setFont(new Font("Tahoma", Font.BOLD, 74));
		lblYouWin.setForeground(Color.GREEN);
		lblYouWin.setBounds(190, 179, 353, 90);
		
		JLabel lblYouLose = new JLabel("YOU LOSE");
		lblYouLose.setFont(new Font("Tahoma", Font.BOLD, 74));
		lblYouLose.setForeground(Color.RED);
		lblYouLose.setBounds(190, 179, 389, 90);
		
		JLabel lblStartNewGame = new JLabel("START NEW GAME");
		lblStartNewGame.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
		lblStartNewGame.setForeground(Color.ORANGE);
		lblStartNewGame.setBounds(49, 567, 312, 40);
		lblStartNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblStartNewGame.setForeground(new Color(252, 157, 3));
				lblStartNewGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				GUI changeCondition = userInterface.getGUIInstance();
				changeCondition.conditionRestartGame();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblStartNewGame.setForeground(Color.ORANGE);
				lblStartNewGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(lblStartNewGame);
		
		JLabel lblClose = new JLabel("CLOSE");
		lblClose.setForeground(Color.ORANGE);
		lblClose.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
		lblClose.setBounds(477, 567, 133, 40);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClose.setForeground(new Color(252, 157, 3));
				lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblClose.setForeground(Color.ORANGE);
				lblClose.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(lblClose);
		
		if(condition.equals(ResultEnum.WIN)) {
			add(lblYouWin);
		}
		else {
			add(lblYouLose);
			lblStartNewGame.setText("TRY AGAIN");
		}
		
	}

}
