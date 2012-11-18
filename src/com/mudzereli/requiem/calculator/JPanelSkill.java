package com.mudzereli.requiem.calculator;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.metal.MetalToolTipUI;

@SuppressWarnings("serial")
public class JPanelSkill extends JPanelBackground {
	public final GBC constraints;
	private JPanel panelIncrementLevel;
	private JPanel panelDecrementLevel;
	private JLabel lblPoints;
	private Skill skill;
	
    public JPanelSkill(final Skill skill, int xCoordinate, int yCoordinate) {
    	super(skill.IMAGE_ICON.getImage());
    	this.skill = skill;
    	this.setToolTipText(skill.name().replaceAll("_"," "));
		this.constraints = new GBC(xCoordinate,yCoordinate).setFill(GBC.BOTH).setInsets(5);
		this.setSize(new Dimension(64,64));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.GRAY, Color.DARK_GRAY),BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GRAY, Color.DARK_GRAY)));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{22,20,22};
		layout.rowHeights = new int[]{22,20,22};
		layout.columnWeights = new double[]{1.0, 1.0, 1.0};
		layout.rowWeights = new double[]{1.0, 1.0, 1.0};
		setLayout(layout);
		panelIncrementLevel = new JPanelBackground("com/mudzereli/requiem/resources/icons/plus.png",SCALED);
		panelIncrementLevel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Character.increment(skill);
				Calculator.getInstance().refreshUI();
			}
			@Override
			public void mouseEntered(MouseEvent e){};
			@Override
			public void mouseExited(MouseEvent e) {};
			@Override
			public void mousePressed(MouseEvent e) {};
			@Override
			public void mouseReleased(MouseEvent e) {};
		});
		this.add(panelIncrementLevel, new GBC(2,0).setFill(GBC.BOTH));
		lblPoints = new JLabel();
		lblPoints.setForeground(Color.WHITE);
		lblPoints.setText("0");
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoints.setFont(new Font("Tahoma", Font.BOLD, 12));
		JPanel panelPoints = new JPanelBackground("com/mudzereli/requiem/resources/icons/pointbox.png",SCALED);
		panelPoints.setLayout(new GridBagLayout());
		panelPoints.add(lblPoints, new GBC(0,0).setFill(GBC.NONE));
		this.add(panelPoints, new GBC(0,0).setFill(GBC.BOTH));
		panelDecrementLevel = new JPanelBackground("com/mudzereli/requiem/resources/icons/minus.png",SCALED);
		panelDecrementLevel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Character.decrement(skill);
				Calculator.getInstance().refreshUI();
			}
			@Override
			public void mouseEntered(MouseEvent e){};
			@Override
			public void mouseExited(MouseEvent e) {};
			@Override
			public void mousePressed(MouseEvent e) {};
			@Override
			public void mouseReleased(MouseEvent e) {};
		});
		this.add(panelDecrementLevel, new GBC(2,2).setFill(GBC.BOTH));
    }
    
	public JPanelSkill(JComponent callback, Skill skill, int xCoordinate, int yCoordinate) {
		this(skill, xCoordinate, yCoordinate);
		callback.add(this, constraints);
	}
	
	@Override
	public JToolTip createToolTip() {
		JToolTip tip = new SkillToolTip();
		tip.setComponent(this);
		return tip;
	}

	@Override
	public Point getToolTipLocation(MouseEvent event) {
        Point p = event.getPoint();
        p.y += 15;
        return p;
	}

	@Override
	public String getToolTipText(MouseEvent event) {
		return skill.name().replaceAll("_"," ");
	}

	public JLabel getLblPoints() {
		return lblPoints;
	}
	
	public void setLblPoints(int points) {
		lblPoints.setText(String.valueOf(points));
	}
	
	private class SkillToolTip extends JToolTip {
		protected SkillToolTip() {
			setUI(new SkillToolTipUI());
		}
		
		private class SkillToolTipUI extends MetalToolTipUI {
			@Override
			public Dimension getPreferredSize(JComponent c) {
				return new Dimension(210, 80);
			}
	
			@Override
			public void paint(Graphics g, JComponent c) {
				super.paint(g, c);
				c.setOpaque(true);
				c.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
				c.setBackground(Color.DARK_GRAY);
				c.setForeground(Color.WHITE);
			}
		}
		
	}
}
