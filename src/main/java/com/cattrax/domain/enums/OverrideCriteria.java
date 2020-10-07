package com.cattrax.domain.enums;

public enum OverrideCriteria {

	GLOUCOMIA_DIFFICULT1 ("Gloucomia Difficulty 1"),
	GLOUCOMIA_DIFFICULT2 ("Gloucomia Difficulty 2"),
	ANISOMETROPIA_MORETHAN_40 ("Anisometropia more than 40");

	private String overrideCriteria;
	OverrideCriteria(String overrideCriteria) {
		this.overrideCriteria = overrideCriteria;
	}
	public String getOverrideCriteria() {
		return overrideCriteria;
	}
}
