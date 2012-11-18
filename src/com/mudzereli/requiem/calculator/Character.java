package com.mudzereli.requiem.calculator;


public class Character {
	
	private Character() {
		//TODO : ENSURE THIS IS ALL ACCURATE
		resetSkillPoints();
	}
	
	public static void increment(Skill skill) {
		if (canLevel(skill)) {
			setPoints(skill, getPoints(skill) + 1);
		}
	}
	
	public static void decrement(Skill skill) {
		setPoints(skill, getPoints(skill) - 1);
	}
	
	public static int getPoints(Skill skill) {
		int points = 0;
		try {
			points = Integer.parseInt(skill.JPANEL.getLblPoints().getText());
		} catch (Exception e) {
			System.out.println("Invalid number in SKILL POINTS number!");
		}
		return points;
	}
	
	public static void setPoints(Skill skill, int points) {
		if (points >= 0 && points <= 10) {
			int pointDifference = points - getPoints(skill);
			if (getUnusedSkillPoints() - pointDifference >= 0) {
				skill.JPANEL.setLblPoints(points);
				setUnusedSkillPoints(getUnusedSkillPoints() - pointDifference);
			}
		}
	}
	
	public static int getLevel() {
		int level = 0;
		try {
			level = Integer.valueOf(Calculator.getInstance().getTxtLevel().getText());
		} catch (Exception e) {
			System.out.println("Invalid number in LEVEL number!");
		}
		return level > 89 ? 89 : level < 0 ? 0 : level;
	}
	
	public static void setLevel(int level) {
		level = level > 89 ? 89 : level < 0 ? 0 : level;
		Calculator.getInstance().getTxtLevel().setText(String.valueOf(level));
	}
	
	public static void resetSkillPoints() {
		int level = getLevel();
		int points = 0;
		points = level > 1 ? level - 1 : points;
		points = level >= 10 ? points + 2 * (level / 10) : points;
		points = level >= 50 ? points + 2 : points;
		setUnusedSkillPoints(points);
		for (Skill skill : Skill.values()) {
			setPoints(skill,0);
		}
	}
	
	public static int getUnusedSkillPoints() {
		int points = 0;
		try {
			points = Integer.valueOf(Calculator.getInstance().getLblSkillPointsField().getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return points;
	}
	
	public static void setUnusedSkillPoints(int points) {
		Calculator.getInstance().getLblSkillPointsField().setText(String.valueOf(points));
	}

	public static boolean canLevel(Skill skill) {
		return getLevel() >= (skill.LEVEL_REQ + (3 * getPoints(skill))) && (skill.getSkillReq() == null || getPoints(skill.getSkillReq()) > 0);
	}
}
