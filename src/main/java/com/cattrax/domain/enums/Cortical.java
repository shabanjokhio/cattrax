package com.cattrax.domain.enums;

public enum Cortical {

	ABSENT ("Absent"),
	C1("C1"),
	C2("C2"),
	C3 ("C3"),
	C4("C4"),
	C5("C5");

	private String value;
	Cortical(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}
}
