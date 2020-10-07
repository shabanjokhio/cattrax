package com.cattrax.domain.enums;

public enum Ethnicity {

	NZ_EUROPEAN ("New Zealander European"),
	MAORI ("Maori"),
	SAMOAN ("Samoan"),
	COOK_ISLAND_MAORI ("Cook Island Moari"),
	TONGAN ("Tongan"),
	NIUEAN ("Niuean"),
	CHINESE ("Chinese"),
	INDIAN ("Indian"),
	OTHER ("Other");

	private final String value;

	Ethnicity(String value){
		this.value=value;
	}

	public String getAsValue(){
		return value;
	}
}
