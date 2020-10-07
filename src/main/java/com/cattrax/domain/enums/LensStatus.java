package com.cattrax.domain.enums;

public enum LensStatus {

	PHAKIC ("Phakic"),
	PSEUDO_PHAKIC ("Pseudophakic"),
	PSEUDO_PHAKIC_WITH_PCO("Pseudophakic With PCO"),
	APHAKIC("Aphakic");

	private String lensStatus;

	LensStatus(String lensStatus){
		this.lensStatus=lensStatus;
	}

	public String getLensStatus() {
		return lensStatus;
	}
}
