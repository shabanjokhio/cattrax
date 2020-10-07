package com.cattrax.domain.enums;

public enum VisualPotential {

	_BETTER_THAN_6By12 ("Better than 6/12"),
	_BETTER_THAN_6By12_TO_6By36 ("Better than 6/12 to 6/36"),
	_WORSE_6By36 ("Worse than 6/36");

	private String visualPotential;

	VisualPotential(String visualPotential) {
		this.visualPotential = visualPotential;
	}

	public String getVisualPotential() {
		return visualPotential;
	}
}
