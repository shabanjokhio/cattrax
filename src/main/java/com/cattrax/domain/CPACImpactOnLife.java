package com.cattrax.domain;

import com.cattrax.domain.enums.DifficultyLevel;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//@Embeddable
@Entity (name = "LIFE_IMPACT")
public class CPACImpactOnLife extends AbstractDomainEntity{

	@Enumerated(EnumType.ORDINAL)
	private DifficultyLevel q1_socialInteraction;

	@Enumerated(EnumType.ORDINAL)
	private DifficultyLevel q2_personalInteraction;

	@Enumerated(EnumType.ORDINAL)
	private DifficultyLevel q3_fulfillingResponsibilities;

	@Enumerated(EnumType.ORDINAL)
	private DifficultyLevel q4_personalCare;

	@Enumerated(EnumType.ORDINAL)
	private DifficultyLevel q5_personalSafety;

	@Enumerated(EnumType.ORDINAL)
	private DifficultyLevel q6_leisureActivities;

	public DifficultyLevel getQ1_socialInteraction() {
		return q1_socialInteraction;
	}
	public void setQ1_socialInteraction(DifficultyLevel q1_socialInteraction) {
		this.q1_socialInteraction = q1_socialInteraction;
	}
	public DifficultyLevel getQ2_personalInteraction() {
		return q2_personalInteraction;
	}
	public void setQ2_personalInteraction(DifficultyLevel q2_personalInteraction) {
		this.q2_personalInteraction = q2_personalInteraction;
	}

	public DifficultyLevel getQ3_fulfillingResponsibilities() {
		return q3_fulfillingResponsibilities;
	}

	public void setQ3_fulfillingResponsibilities(DifficultyLevel q3_fulfillingResponsibilities) {
		this.q3_fulfillingResponsibilities = q3_fulfillingResponsibilities;
	}

	public DifficultyLevel getQ4_personalCare() {
		return q4_personalCare;
	}

	public void setQ4_personalCare(DifficultyLevel q4_personalCare) {
		this.q4_personalCare = q4_personalCare;
	}

	public DifficultyLevel getQ5_personalSafety() {
		return q5_personalSafety;
	}

	public void setQ5_personalSafety(DifficultyLevel q5_personalSafety) {
		this.q5_personalSafety = q5_personalSafety;
	}

	public DifficultyLevel getQ6_leisureActivities() {
		return q6_leisureActivities;
	}

	public void setQ6_leisureActivities(DifficultyLevel q6_leisureActivities) {
		this.q6_leisureActivities = q6_leisureActivities;
	}

	@Override
	public String toString() {
		return "CPACImpactOnLife{" +
				"q1_socialInteraction=" + q1_socialInteraction +
				", q2_personalInteraction=" + q2_personalInteraction +
				", q3_fulfillingResponsibilities=" + q3_fulfillingResponsibilities +
				", q4_personalCare=" + q4_personalCare +
				", q5_personalSafety=" + q5_personalSafety +
				", q6_leisureActivities=" + q6_leisureActivities +
				'}';
	}
}
