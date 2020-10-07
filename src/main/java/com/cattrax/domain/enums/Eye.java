package com.cattrax.domain.enums;

public enum Eye {
	OD("OD","Right Eye"),
	OS("OS","Left Eye"),
	OU("OU","Both Eyes");

	private String eyeType;
	private String typeDescription;

	Eye(String eyeType, String typeDescription){
		this.eyeType=eyeType;
		this.typeDescription=typeDescription;
	}

	public String getEyeType() {
		return eyeType;
	}

	public String getTypeDescription() {
		return typeDescription;
	}
}
