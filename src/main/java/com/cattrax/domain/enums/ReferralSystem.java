package com.cattrax.domain.enums;

public enum ReferralSystem {

	PUBLIC_SYSTEM ("Public System"),
	PRIVATE_SOUTHER_CROSS ("Private Southern Cross"),
	PRIVATE_OTHER_INSURANCE ("Private Other Insurance"),
	PRIVATE_NO_INSURANCE ("Private No Insurance");

	private String referralSystem;

	ReferralSystem(String referralSystem) {
		this.referralSystem = referralSystem;
	}

	public String getReferralSystem() {
		return referralSystem;
	}

}
