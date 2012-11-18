package com.mudzereli.requiem.calculator;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JApplet;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

@SuppressWarnings("serial")
public class Calculator extends JApplet {
	private static Calculator instance;
	private JTextField txtLevel;
	private JPanel panelJobLower;
	private JComboBox<JobUpper> cmbJobUpper;
	private JPanel panelJobUpper;
	private JLabel lblSkillPointsField;
	
	public Calculator() {
		instance = this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{600};
		gridBagLayout.rowHeights = new int[] {800};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panelMain =  new JPanelBackground("com/mudzereli/requiem/resources/art/programbackground.jpg",JPanelBackground.SCALED);
		getContentPane().add(panelMain, new GBC(0,0).setInsets(5).setFill(GBC.BOTH));
		GridBagLayout gbl_panelMain = new GridBagLayout();
		gbl_panelMain.columnWidths = new int[] {300, 300};
		gbl_panelMain.rowHeights = new int[] {300, 500};
		gbl_panelMain.columnWeights = new double[]{1.0, 1.0};
		gbl_panelMain.rowWeights = new double[]{1.0, 1.0};
		panelMain.setLayout(gbl_panelMain);
		
		JPanel panelConfig = new JPanel();
		panelConfig.setOpaque(false);
		panelConfig.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GRAY, Color.DARK_GRAY),BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GRAY, Color.DARK_GRAY)));
		panelConfig.setPreferredSize(new Dimension(150, 150));
		panelMain.add(panelConfig, new GBC(0,0).setInsets(5).setFill(GBC.BOTH));
		GridBagLayout gbl_panelConfig = new GridBagLayout();
		gbl_panelConfig.columnWidths = new int[] {150, 150};
		gbl_panelConfig.rowHeights = new int[] {20, 20, 20, 20, 20, 20};
		gbl_panelConfig.columnWeights = new double[]{1.0, 1.0};
		gbl_panelConfig.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		panelConfig.setLayout(gbl_panelConfig);
		
		JLabel lblRequiemTalentCalculator = new JLabel("REQUIEM SKILL CALC");
		lblRequiemTalentCalculator.setForeground(Color.WHITE);
		lblRequiemTalentCalculator.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		lblRequiemTalentCalculator.setHorizontalAlignment(SwingConstants.LEFT);
		panelConfig.add(lblRequiemTalentCalculator, new GBC(0,0).setInsets(1).setFill(GBC.VERTICAL).setGridWidth(2));
		
		JLabel lblJobUpper = new JLabel("JOB");
		lblJobUpper.setForeground(Color.WHITE);
		lblJobUpper.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		lblJobUpper.setHorizontalAlignment(SwingConstants.LEFT);
		panelConfig.add(lblJobUpper, new GBC(0,1).setInsets(1).setFill(GBC.BOTH));
		
		cmbJobUpper = new JComboBox<JobUpper>();
		panelConfig.add(cmbJobUpper, new GBC(1,1).setInsets(1).setFill(GBC.BOTH));
		cmbJobUpper.setModel(new DefaultComboBoxModel<JobUpper>(JobUpper.values()));
		cmbJobUpper.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				refreshUI();
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		
		JLabel lblLevel = new JLabel("LEVEL");
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		lblLevel.setHorizontalAlignment(SwingConstants.LEFT);
		panelConfig.add(lblLevel, new GBC(0,2).setInsets(1).setFill(GBC.BOTH));
		
		txtLevel = new JTextField();
		txtLevel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		panelConfig.add(txtLevel, new GBC(1,2).setInsets(1).setFill(GBC.BOTH));
		txtLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLevel.setText("1");
		txtLevel.setColumns(2);
		class LevelChangeDocumentListener implements DocumentListener {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				Character.resetSkillPoints();
				Calculator.getInstance().refreshUI();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				Character.resetSkillPoints();
				Calculator.getInstance().refreshUI();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				Character.resetSkillPoints();
				Calculator.getInstance().refreshUI();
			}
		}
		txtLevel.getDocument().addDocumentListener(new LevelChangeDocumentListener());
		/*
		JLabel lblDNAPoints = new JLabel("DNA POINTS");
		lblDNAPoints.setForeground(Color.WHITE);
		lblDNAPoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		panelConfig.add(lblDNAPoints, new GBC(0,4).setInsets(1).setFill(GBC.BOTH));
		lblDNAPoints.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblDNAPointsField = new JLabel("1");
		lblDNAPointsField.setForeground(Color.WHITE);
		lblDNAPointsField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		panelConfig.add(lblDNAPointsField, new GBC(1,4).setInsets(1).setFill(GBC.BOTH));
		lblDNAPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		*/
		
		JLabel lblSkillPoints = new JLabel("SKILL POINTS");
		lblSkillPoints.setForeground(Color.WHITE);
		lblSkillPoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		lblSkillPoints.setHorizontalAlignment(SwingConstants.LEFT);
		panelConfig.add(lblSkillPoints, new GBC(0,3).setInsets(1).setFill(GBC.BOTH));
		
		lblSkillPointsField = new JLabel("0");
		lblSkillPointsField.setForeground(Color.WHITE);
		lblSkillPointsField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		panelConfig.add(lblSkillPointsField, new GBC(1,3).setInsets(1).setFill(GBC.BOTH));
		lblSkillPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panelSkills = new JPanelBackground("com/mudzereli/requiem/resources/art/skillpanelbackground.jpg",JPanelBackground.SCALED);
		panelSkills.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GRAY, Color.DARK_GRAY),BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GRAY, Color.DARK_GRAY)));
		//JPanel panelSkills = new JPanel();
		panelMain.add(panelSkills, new GBC(1,0).setInsets(5).setFill(GBC.BOTH).setGridHeight(2));
		GridBagLayout gbl_panelSkills = new GridBagLayout();
		gbl_panelSkills.columnWidths = new int[]{500};
		gbl_panelSkills.rowHeights = new int[]{35, 450, 35, 300};
		gbl_panelSkills.columnWeights = new double[]{1.0};
		gbl_panelSkills.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		panelSkills.setLayout(gbl_panelSkills);
		
		JLabel dividerLowerJob = new JLabel();
		dividerLowerJob.setOpaque(true);
		dividerLowerJob.setBackground(Color.DARK_GRAY);
		dividerLowerJob.setForeground(Color.WHITE);
		dividerLowerJob.setHorizontalAlignment(SwingConstants.CENTER);
		dividerLowerJob.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		dividerLowerJob.setText("Lower Job");
		dividerLowerJob.setMinimumSize(new Dimension(450,100));
		dividerLowerJob.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GRAY, Color.DARK_GRAY),BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GRAY, Color.DARK_GRAY)));
		panelSkills.add(dividerLowerJob, new GBC(0,0).setInsets(5).setFill(GBC.BOTH));
		
		panelJobLower = new JPanel();
		panelJobLower.setOpaque(false);
		panelJobLower.setPreferredSize(new Dimension(74*6, 74*6));
		panelSkills.add(panelJobLower, new GBC(0,1).setInsets(5).setFill(GBC.BOTH));
		GridBagLayout gbl_panelJobLower = new GridBagLayout();
		gbl_panelJobLower.columnWidths = new int[]{74,74,74,74,74};
		gbl_panelJobLower.rowHeights = new int[]{74,74,74,74,74,74};
		gbl_panelJobLower.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelJobLower.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		panelJobLower.setLayout(gbl_panelJobLower);
		
		JLabel dividerUpperJob = new JLabel();
		dividerUpperJob.setOpaque(true);
		dividerUpperJob.setBackground(Color.DARK_GRAY);
		dividerUpperJob.setForeground(Color.WHITE);
		dividerUpperJob.setHorizontalAlignment(SwingConstants.CENTER);
		dividerUpperJob.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		dividerUpperJob.setText("Upper Job");
		dividerUpperJob.setMinimumSize(new Dimension(450,100));
		dividerUpperJob.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GRAY, Color.DARK_GRAY),BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GRAY, Color.DARK_GRAY)));
		panelSkills.add(dividerUpperJob, new GBC(0,2).setInsets(5).setFill(GBC.BOTH));
		
		panelJobUpper = new JPanel();
		panelJobUpper.setOpaque(false);
		panelJobLower.setPreferredSize(new Dimension(74*4, 74*6));
		panelSkills.add(panelJobUpper, new GBC(0,3).setInsets(5).setFill(GBC.BOTH));
		GridBagLayout gbl_panelJobUpper = new GridBagLayout();
		gbl_panelJobUpper.columnWidths = new int[]{74,74,74,74,74,74};
		gbl_panelJobUpper.rowHeights = new int[]{74,74,74,74};
		gbl_panelJobUpper.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelJobUpper.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		panelJobUpper.setLayout(gbl_panelJobUpper);
		
		
		JPanel panelDNA = new JPanel();
		panelDNA.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GRAY, Color.DARK_GRAY),BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GRAY, Color.DARK_GRAY)));
		panelMain.add(panelDNA, new GBC(0,1).setInsets(5).setFill(GBC.BOTH));
		panelDNA.setOpaque(false);
		GridBagLayout gbl_panelDNA = new GridBagLayout();
		gbl_panelDNA.columnWidths = new int[]{0, 0, 0};
		gbl_panelDNA.rowHeights = new int[]{0, 0, 0};
		gbl_panelDNA.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panelDNA.rowWeights = new double[]{0.0, 0.0, 0.0};
		panelDNA.setLayout(gbl_panelDNA);
		
		refreshUI();
	}
	
	public void refreshUI() {
		if (cmbJobUpper.getSelectedItem() != null) {
			JobUpper selectedUpperJob = (JobUpper) cmbJobUpper.getSelectedItem();
			panelJobLower.removeAll();
			panelJobUpper.removeAll();
			for (Skill skill : Skill.values()) {
				JPanel parentPanel = null;
				if (skill.JOB_REQ == selectedUpperJob.jobLower) {
					parentPanel = panelJobLower;
				} else if (skill.JOB_REQ == selectedUpperJob) {
					parentPanel = panelJobUpper;
				}
				if (parentPanel != null) {
					parentPanel.add(skill.JPANEL,skill.JPANEL.constraints);
					Color borderColor;
					if (Character.getPoints(skill) > 0) {
						borderColor = Color.CYAN;
					} else if (Character.getLevel() > skill.LEVEL_REQ && (skill.getSkillReq() == null || Character.getPoints(skill.getSkillReq()) > 0)) {
						borderColor = Color.YELLOW;
					} else {
						borderColor = Color.RED;
					}
					skill.JPANEL.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,borderColor,borderColor),BorderFactory.createBevelBorder(BevelBorder.LOWERED,borderColor,borderColor)));
				}
				Skill req = skill.getSkillReq();
				if (parentPanel != null && req != null) {
					if (req.JPANEL.constraints.gridx == skill.JPANEL.constraints.gridx) {
						int yLow = req.JPANEL.constraints.gridy;
						int yHigh = skill.JPANEL.constraints.gridy;
						int diff = yHigh - yLow;
						if (diff > 1) {
							JPanelBackground panelBackground = new JPanelBackground("com/mudzereli/requiem/resources/icons/arrowtrans.png",JPanelBackground.SCALED);
							panelBackground.setSize(64,64);
							panelBackground.setOpaque(false);
							parentPanel.add(panelBackground,new GBC(req.JPANEL.constraints.gridx, yLow + 1).setInsets(5).setFill(GBC.BOTH).setGridHeight(diff - 1));
						}
						
						/* MULTIPLE arrowtransS -- REPLACED WITH SINGLE arrowtrans CODE ABOVE
						int yLow = req.JPANEL.constraints.gridy;
						int yHigh = skill.JPANEL.constraints.gridy;
						for (int i = yLow + 1;i < yHigh;i++) {
							JPanelBackground panelBackground = new JPanelBackground("com/mudzereli/requiem/resources/icons/arrowtrans.png",JPanelBackground.SCALED);
							panelBackground.setSize(64,64);
							panelBackground.setOpaque(false);
							parentPanel.add(panelBackground,new GBC(req.JPANEL.constraints.gridx, i).setInsets(5).setFill(GBC.BOTH));
						}
						*/
					}
				}
				panelJobUpper.revalidate();
				panelJobUpper.revalidate();
			}
		}
	}
	
	public JTextField getTxtLevel() {
		return txtLevel;
	}
	
	protected JPanel getPanelJobLower() {
		return panelJobLower;
	}
	protected JComboBox<JobUpper> getCmbJobUpper() {
		return cmbJobUpper;
	}
	protected JPanel getPanelJobUpper() {
		return panelJobUpper;
	}

	public static Calculator getInstance() {
		return instance;
	}
	public JLabel getLblSkillPointsField() {
		return lblSkillPointsField;
	}
}
