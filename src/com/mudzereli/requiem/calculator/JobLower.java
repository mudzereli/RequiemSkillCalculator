package com.mudzereli.requiem.calculator;

public enum JobLower implements Job {
	DEFENDER (Race.TURAN),
	TEMPLAR (Race.TURAN),
	WARRIOR (Race.BARTUK),
	SHAMAN (Race.BARTUK),
	ROGUE (Race.KRUXENA),
	SOUL_HUNTER (Race.KRUXENA),
	HUNTER (Race.XENOA),
	BATTLE_MAGE (Race.XENOA)
	;
	
	public final Race RACE;
	private JobLower (Race race) { 
		this.RACE = race;
	}
}
