package com.cattrax.domain;

import javax.persistence.*;

@Entity
@Table (name = "CATTRAX_REFERRAL")
public class CattraxReferral extends AbstractDomainEntity {

	//@ManyToOne
	@ManyToOne (optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn (nullable = false)
	private Patient patientReferred;

	//@OneToOne (optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@ManyToOne (optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn (nullable = false)
	private User referredBy;

	@OneToOne (optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinColumn (unique = false, nullable= true) //temporarily
	private CPACImpactOnLife cpacImpactOnLife;

	@OneToOne (optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinColumn (unique = false, nullable= true)
	private CPACScoreMeasurement cpacScoreMeasurement;

	@Embedded
	private CPACScoreResult cpacScoreResult;

	public Patient getPatientReferred() {
		return patientReferred;
	}

	public void setPatientReferred(Patient patientReferred) {
		this.patientReferred = patientReferred;
	}

	public User getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(User referredBy) {
		this.referredBy = referredBy;
	}

	public CPACImpactOnLife getCpacImpactOnLife() {
		return cpacImpactOnLife;
	}

	public void setCpacImpactOnLife(CPACImpactOnLife cpacImpactOnLife) {
		this.cpacImpactOnLife = cpacImpactOnLife;
	}

	public CPACScoreMeasurement getCpacScoreMeasurement() {
		return cpacScoreMeasurement;
	}

	public void setCpacScoreMeasurement(CPACScoreMeasurement cpacScoreMeasurement) {
		this.cpacScoreMeasurement = cpacScoreMeasurement;
	}

	public CPACScoreResult getCpacScoreResult() {
		return cpacScoreResult;
	}

	public void setCpacScoreResult(CPACScoreResult cpacScoreResult) {
		this.cpacScoreResult = cpacScoreResult;
	}

	/*@Override
	public String toString() {
		return "CattraxReferral {" +
				"patientReferred=" + patientReferred +
				", referredBy=" + referredBy +
				", cpacImpactOnLife=" + cpacImpactOnLife +
				", cpacScoreMeasurement=" + cpacScoreMeasurement +
				", cpacScoreResult=" + cpacScoreResult +
				'}';
	}*/
}
