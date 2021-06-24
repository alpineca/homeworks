package game;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import GUI.GUI;
import interfaces.GameConfig;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wellcome extends JPanel implements ActionListener{
	
	private static String titleTxt = "Course Project First Semester";
	private static GUI userInterface;

	public Wellcome() {
//		setBackground(new Color(25, 25, 25));
		setBackground(GameConfig.GROUNDCOLOR);
		setLayout(null);
		
		Label title = new Label(titleTxt);
		title.setForeground(new Color(255, 255, 255));
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 31));
		title.setBounds(185, 25, 454, 45);
		add(title);
		
		JLabel myName = new JLabel("Emil Nikolov");
		myName.setForeground(new Color(242, 152, 34));
		myName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 23));
		myName.setBounds(190, 77, 135, 27);
		add(myName);
		
		JLabel rulesTitle = new JLabel("Rules of the game");
		rulesTitle.setForeground(new Color(242, 152, 34));
		rulesTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		rulesTitle.setBounds(51, 217, 191, 25);
		add(rulesTitle);
		
		JLabel rulesText = new JLabel("Destroy all the buidlings, avoid the enemy and win the game.");
		rulesText.setForeground(new Color(255, 255, 255));
		rulesText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rulesText.setBounds(51, 247, 349, 16);
		add(rulesText);
		
		JLabel controlsTitle = new JLabel("Controls");
		controlsTitle.setForeground(new Color(242, 152, 34));
		controlsTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		controlsTitle.setBounds(51, 329, 92, 25);
		add(controlsTitle);
		
		JLabel w = new JLabel("W");
		w.setForeground(SystemColor.textHighlight);
		w.setFont(new Font("Tahoma", Font.BOLD, 17));
		w.setBounds(51, 359, 17, 21);
		add(w);
		
		JLabel lblS = new JLabel("S");
		lblS.setForeground(SystemColor.textHighlight);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblS.setBounds(51, 391, 17, 21);
		add(lblS);
		
		JLabel lblA = new JLabel("A");
		lblA.setForeground(SystemColor.textHighlight);
		lblA.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblA.setBounds(51, 423, 17, 21);
		add(lblA);
		
		JLabel lblD = new JLabel("D");
		lblD.setForeground(SystemColor.textHighlight);
		lblD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblD.setBounds(51, 455, 17, 21);
		add(lblD);
		
		JLabel lblC = new JLabel("C + Right Arrow");
		lblC.setForeground(SystemColor.textHighlight);
		lblC.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblC.setBounds(51, 487, 138, 21);
		add(lblC);
		
		JLabel lblMoveArmyUnits = new JLabel("Move army units UP");
		lblMoveArmyUnits.setForeground(Color.WHITE);
		lblMoveArmyUnits.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMoveArmyUnits.setBounds(78, 363, 113, 16);
		add(lblMoveArmyUnits);
		
		JLabel lblMoveArmyUnits_2 = new JLabel("Move army units DOWN");
		lblMoveArmyUnits_2.setForeground(Color.WHITE);
		lblMoveArmyUnits_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMoveArmyUnits_2.setBounds(79, 394, 135, 16);
		add(lblMoveArmyUnits_2);
		
		JLabel lblMoveArmyUnits_3 = new JLabel("Move army units LEFT");
		lblMoveArmyUnits_3.setForeground(Color.WHITE);
		lblMoveArmyUnits_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMoveArmyUnits_3.setBounds(80, 423, 126, 16);
		add(lblMoveArmyUnits_3);
		
		JLabel lblMoveArmyUnits_4 = new JLabel("Move army units RIGHT");
		lblMoveArmyUnits_4.setForeground(Color.WHITE);
		lblMoveArmyUnits_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMoveArmyUnits_4.setBounds(80, 457, 134, 16);
		add(lblMoveArmyUnits_4);
		
		JLabel lblChangeArmyUnits = new JLabel("Change army units order");
		lblChangeArmyUnits.setForeground(Color.WHITE);
		lblChangeArmyUnits.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChangeArmyUnits.setBounds(202, 491, 142, 16);
		add(lblChangeArmyUnits);
		
		JLabel lblLegend = new JLabel("Army Legend");
		lblLegend.setForeground(new Color(242, 152, 34));
		lblLegend.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblLegend.setBounds(571, 328, 148, 25);
		add(lblLegend);
		
		JButton btnStartGame = new JButton("START GAME");
		btnStartGame.setBackground(Color.DARK_GRAY);
		btnStartGame.setBounds(326, 611, 135, 55);
		btnStartGame.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		btnStartGame.addActionListener(this);
		add(btnStartGame);
		
		JLabel lblSaboteurIcon = new JLabel("SABOTEUR");
		lblSaboteurIcon.setForeground(Color.WHITE);
		lblSaboteurIcon.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblSaboteurIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaboteurIcon.setOpaque(true);
		lblSaboteurIcon.setBackground(GameConfig.SABOTEURUNITCOLOR);
		lblSaboteurIcon.setBounds(571, 364, 138, 33);
		add(lblSaboteurIcon);
		
		JLabel lblSniperistIcon = new JLabel("SNIPERIST");
		lblSniperistIcon.setForeground(Color.WHITE);
		lblSniperistIcon.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblSniperistIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSniperistIcon.setOpaque(true);
		lblSniperistIcon.setBackground(GameConfig.SNIPERISTUNITCOLOR);
		lblSniperistIcon.setBounds(571, 498, 138, 33);
		add(lblSniperistIcon);
		
		JLabel lblTankIcon = new JLabel("TANK");
		lblTankIcon.setForeground(Color.WHITE);
		lblTankIcon.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblTankIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTankIcon.setOpaque(true);
		lblTankIcon.setBackground(GameConfig.TANKUNITCOLOR);
		lblTankIcon.setBounds(571, 454, 138, 33);
		add(lblTankIcon);
		
		JLabel lblSpyIcon = new JLabel("SPY");
		lblSpyIcon.setForeground(Color.WHITE);
		lblSpyIcon.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblSpyIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpyIcon.setOpaque(true);
		lblSpyIcon.setBackground(GameConfig.SPYUNITCOLOR);
		lblSpyIcon.setBounds(571, 410, 138, 33);
		add(lblSpyIcon);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GUI changeCondition = userInterface.getGUIInstance();
		changeCondition.conditionStartGame();
		
	}
}
