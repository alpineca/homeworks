package game;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import GUI.GUI;
import interfaces.GameConfig;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Wellcome extends JPanel{
	
	private static String titleTxt = "Course Project First Semester";
	private static GUI userInterface;

	//FONTS
	private static Font titleFont 			= new Font("Arial Rounded MT Bold", Font.BOLD, 31);
	private static Font subTitleFont 		= new Font("Arial Rounded MT Bold", Font.PLAIN, 23);
	private static Font infoTitleFont 		= new Font("Tahoma", Font.BOLD | Font.ITALIC, 20);
	private static Font infoTextFont 		= new Font("Tahoma", Font.PLAIN, 13);
	private static Font controlsLegendFont 	= new Font("Tahoma", Font.BOLD, 17);
	private static Font unitsFont 			= new Font("Arial Rounded MT Bold", Font.PLAIN, 15);

	public Wellcome() {
		setBorder(null);
		setBackground(GameConfig.GROUNDCOLOR);
		setLayout(null);
		
		Label title = new Label(titleTxt);
		title.setForeground(new Color(255, 255, 255));
		title.setAlignment(Label.CENTER);
		title.setFont(titleFont);
		title.setBounds(185, 25, 454, 45);
		add(title);
		
		JLabel myName = new JLabel("Emil Nikolov");
		myName.setForeground(new Color(242, 152, 34));
		myName.setFont(subTitleFont);
		myName.setBounds(190, 77, 135, 27);
		add(myName);
		
		JLabel rulesTitle = new JLabel("Rules of the game");
		rulesTitle.setForeground(new Color(242, 152, 34));
		rulesTitle.setFont(infoTitleFont);
		rulesTitle.setBounds(51, 217, 191, 25);
		add(rulesTitle);
		
		JLabel rulesText = new JLabel("Destroy all the buidlings, avoid the enemy and win the game.");
		rulesText.setForeground(new Color(255, 255, 255));
		rulesText.setFont(infoTextFont);
		rulesText.setBounds(51, 247, 349, 16);
		add(rulesText);
		
		JLabel controlsTitle = new JLabel("Controls");
		controlsTitle.setForeground(new Color(242, 152, 34));
		controlsTitle.setFont(infoTitleFont);
		controlsTitle.setBounds(51, 329, 92, 25);
		add(controlsTitle);
		
		JLabel w = new JLabel("W");
		w.setForeground(SystemColor.textHighlight);
		w.setFont(controlsLegendFont);
		w.setBounds(51, 359, 17, 21);
		add(w);
		
		JLabel lblS = new JLabel("S");
		lblS.setForeground(SystemColor.textHighlight);
		lblS.setFont(controlsLegendFont);
		lblS.setBounds(51, 391, 17, 21);
		add(lblS);
		
		JLabel lblA = new JLabel("A");
		lblA.setForeground(SystemColor.textHighlight);
		lblA.setFont(controlsLegendFont);
		lblA.setBounds(51, 423, 17, 21);
		add(lblA);
		
		JLabel lblD = new JLabel("D");
		lblD.setForeground(SystemColor.textHighlight);
		lblD.setFont(controlsLegendFont);
		lblD.setBounds(51, 455, 17, 21);
		add(lblD);
		
		JLabel lblC = new JLabel("C + Right Arrow");
		lblC.setForeground(SystemColor.textHighlight);
		lblC.setFont(controlsLegendFont);
		lblC.setBounds(51, 487, 138, 21);
		add(lblC);
		
		JLabel lblMoveArmyUnits = new JLabel("Move army units UP");
		lblMoveArmyUnits.setForeground(Color.WHITE);
		lblMoveArmyUnits.setFont(infoTextFont);
		lblMoveArmyUnits.setBounds(78, 363, 113, 16);
		add(lblMoveArmyUnits);
		
		JLabel lblMoveArmyUnits_2 = new JLabel("Move army units DOWN");
		lblMoveArmyUnits_2.setForeground(Color.WHITE);
		lblMoveArmyUnits_2.setFont(infoTextFont);
		lblMoveArmyUnits_2.setBounds(79, 394, 135, 16);
		add(lblMoveArmyUnits_2);
		
		JLabel lblMoveArmyUnits_3 = new JLabel("Move army units LEFT");
		lblMoveArmyUnits_3.setForeground(Color.WHITE);
		lblMoveArmyUnits_3.setFont(infoTextFont);
		lblMoveArmyUnits_3.setBounds(80, 423, 126, 16);
		add(lblMoveArmyUnits_3);
		
		JLabel lblMoveArmyUnits_4 = new JLabel("Move army units RIGHT");
		lblMoveArmyUnits_4.setForeground(Color.WHITE);
		lblMoveArmyUnits_4.setFont(infoTextFont);
		lblMoveArmyUnits_4.setBounds(80, 457, 134, 16);
		add(lblMoveArmyUnits_4);
		
		JLabel lblChangeArmyUnits = new JLabel("Change army units order");
		lblChangeArmyUnits.setForeground(Color.WHITE);
		lblChangeArmyUnits.setFont(infoTextFont);
		lblChangeArmyUnits.setBounds(202, 491, 142, 16);
		add(lblChangeArmyUnits);
		
		JLabel lblLegend = new JLabel("Army Legend");
		lblLegend.setForeground(new Color(242, 152, 34));
		lblLegend.setFont(infoTitleFont);
		lblLegend.setBounds(571, 328, 148, 25);
		add(lblLegend);
		
		JLabel lblSaboteurIcon = new JLabel("SABOTEUR");
		lblSaboteurIcon.setForeground(Color.WHITE);
		lblSaboteurIcon.setFont(unitsFont);
		lblSaboteurIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaboteurIcon.setOpaque(true);
		lblSaboteurIcon.setBackground(GameConfig.SABOTEURUNITCOLOR);
		lblSaboteurIcon.setBounds(571, 364, 138, 33);
		add(lblSaboteurIcon);
		
		JLabel lblSniperistIcon = new JLabel("SNIPERIST");
		lblSniperistIcon.setForeground(Color.WHITE);
		lblSniperistIcon.setFont(unitsFont);
		lblSniperistIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSniperistIcon.setOpaque(true);
		lblSniperistIcon.setBackground(GameConfig.SNIPERISTUNITCOLOR);
		lblSniperistIcon.setBounds(571, 498, 138, 33);
		add(lblSniperistIcon);
		
		JLabel lblTankIcon = new JLabel("TANK");
		lblTankIcon.setForeground(Color.WHITE);
		lblTankIcon.setFont(unitsFont);
		lblTankIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTankIcon.setOpaque(true);
		lblTankIcon.setBackground(GameConfig.TANKUNITCOLOR);
		lblTankIcon.setBounds(571, 454, 138, 33);
		add(lblTankIcon);
		
		JLabel lblSpyIcon = new JLabel("SPY");
		lblSpyIcon.setForeground(Color.WHITE);
		lblSpyIcon.setFont(unitsFont);
		lblSpyIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpyIcon.setOpaque(true);
		lblSpyIcon.setBackground(GameConfig.SPYUNITCOLOR);
		lblSpyIcon.setBounds(571, 410, 138, 33);
		add(lblSpyIcon);
		
		JLabel lblNewLabel = new JLabel("START NEW GAME");
		lblNewLabel.setForeground(new Color(250, 25, 89));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setForeground(new Color(250, 187, 205));
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 52));
				lblNewLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setForeground(new Color(250, 25, 89));
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
				lblNewLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI changeCondition = userInterface.getGUIInstance();
				changeCondition.conditionStartGame();
			}
			
		});
		lblNewLabel.setBounds(140, 600, 489, 77);
		add(lblNewLabel);
		

	}
}
