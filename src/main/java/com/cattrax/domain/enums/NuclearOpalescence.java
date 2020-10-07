package com.cattrax.domain.enums;

public enum NuclearOpalescence {

	ABSENT ("Absent"),
	NO1 ("NO1"),
	NO2 ("NO2"),
	NO3 ("NO3"),
	NO4 ("NO4"),
	NO5 ("NO5"),
	NO6 ("NO6");

	private String nucOpalescene;

	NuclearOpalescence(String nucOpalescene) {
		this.nucOpalescene = nucOpalescene;
	}

	public String getNucOpalescene() {
		return nucOpalescene;
	}

}
