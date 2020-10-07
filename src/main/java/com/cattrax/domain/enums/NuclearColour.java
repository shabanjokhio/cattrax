package com.cattrax.domain.enums;

public enum NuclearColour {

	ABSENT ("Absent"),
	NC1 ("NC1"),
	NC2 ("NC2"),
	NC3 ("NC3"),
	NC4 ("NC4"),
	NC5 ("NC5"),
	NC6 ("NC6")
	;

	private String nucColour;

	NuclearColour(String nucColour) {
		this.nucColour = nucColour;
	}

	public String getNucColour() {
		return nucColour;
	}
}
