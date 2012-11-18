package com.mudzereli.requiem.calculator;

public enum JobUpper implements Job {
	COMMANDER (JobLower.DEFENDER),
	PROTECTOR (JobLower.DEFENDER),
	TEMPEST (JobLower.TEMPLAR),
	RADIANT (JobLower.TEMPLAR),
	BERSERKER (JobLower.WARRIOR),
	WARLORD (JobLower.WARRIOR),
	FORSAKER (JobLower.SHAMAN),
	MYSIC (JobLower.SHAMAN),
	SHADOW_RUNNER (JobLower.ROGUE),
	ASSASSIN (JobLower.ROGUE),
	DOMINATOR (JobLower.SOUL_HUNTER),
	DEFILER (JobLower.SOUL_HUNTER),
	RANGER (JobLower.HUNTER),
	AVENGER (JobLower.HUNTER),
	ELEMENTALIST (JobLower.BATTLE_MAGE),
	DRUID (JobLower.BATTLE_MAGE);
	
	public final JobLower jobLower;
	
	private JobUpper(JobLower job) {
		this.jobLower = job;
	}
}
