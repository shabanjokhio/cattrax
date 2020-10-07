package com.cattrax.domain.enums;

public enum RegistrationType {

	OPTOMETRIST ("Optometrist"),
	GP ("GP"),
	OPHTHALMOGIST ("Ophthalmogist");

	private String regType;

	RegistrationType(String regType) {
		this.regType = regType;
	}

	public String getRegType() {
		return regType;
	}

}
