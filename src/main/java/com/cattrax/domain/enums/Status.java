package com.cattrax.domain.enums;

public enum Status {

	ACTIVE ("Active"),
	INACTIVE ("Inactive"),
	BLOCKED ("Blocked");

	private String userStatus;

	public String getUserStatus() {
		return userStatus;
	}

	Status(String userStatus) {
		this.userStatus = userStatus;
	}
}
