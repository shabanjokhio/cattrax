package com.cattrax.domain.enums;

public enum RoleType {

	ADMIN ("Cattrax Admin"),
	REFERRER ("Referrer"),
	PRACTICE_ADMIN ("Practice Admin");

	private String roleType;

	public String getRoleType() {
		return roleType;
	}

	RoleType(String roleType) {
		this.roleType = roleType;
	}
}
