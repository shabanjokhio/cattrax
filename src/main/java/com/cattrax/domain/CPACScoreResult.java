package com.cattrax.domain;

import com.cattrax.domain.enums.Eye;
import com.cattrax.domain.enums.OverrideCriteria;
import com.cattrax.domain.enums.ReferralSystem;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
//@Entity
public class CPACScoreResult implements Serializable{

	private double cpacScore;
	private double southCrossScore;
	private double otherInsuranceScore;
	private String additionalComments;
	@Enumerated (EnumType.STRING)
	private Eye eyeReferred;

	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn (unique = true, nullable = true)
	private User referredTo;

	@Enumerated (EnumType.STRING)
	private OverrideCriteria cpacOverrideCriteria;

	@Enumerated (EnumType.STRING)
	private ReferralSystem referredUnderSystem;

	public double getCpacScore() {
		return cpacScore;
	}

	public void setCpacScore(double cpacScore) {
		this.cpacScore = cpacScore;
	}

	public double getSouthCrossScore() {
		return southCrossScore;
	}

	public void setSouthCrossScore(double southCrossScore) {
		this.southCrossScore = southCrossScore;
	}

	public double getOtherInsuranceScore() {
		return otherInsuranceScore;
	}

	public void setOtherInsuranceScore(double otherInsuranceScore) {
		this.otherInsuranceScore = otherInsuranceScore;
	}

	public String getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}

	public Eye getEyeReferred() {
		return eyeReferred;
	}

	public void setEyeReferred(Eye eyeReferred) {
		this.eyeReferred = eyeReferred;
	}

	public User getReferredTo() {
		return referredTo;
	}

	public void setReferredTo(User referredTo) {
		this.referredTo = referredTo;
	}

	public OverrideCriteria getCpacOverrideCriteria() {
		return cpacOverrideCriteria;
	}

	public void setCpacOverrideCriteria(OverrideCriteria cpacOverrideCriteria) {
		this.cpacOverrideCriteria = cpacOverrideCriteria;
	}

	public ReferralSystem getReferredUnderSystem() {
		return referredUnderSystem;
	}

	public void setReferredUnderSystem(ReferralSystem referredUnderSystem) {
		this.referredUnderSystem = referredUnderSystem;
	}

	@Override
	public String toString() {
		return "CPACScoreResult{" +
				"cpacScore=" + cpacScore +
				", southCrossScore=" + southCrossScore +
				", otherInsuranceScore=" + otherInsuranceScore +
				", additionalComments='" + additionalComments + '\'' +
				", eyeReferred=" + eyeReferred +
				", referredTo=" + referredTo +
				", cpacOverrideCriteria=" + cpacOverrideCriteria +
				", referredUnderSystem=" + referredUnderSystem +
				'}';
	}
}
