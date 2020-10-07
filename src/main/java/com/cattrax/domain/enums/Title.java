package com.cattrax.domain.enums;

public enum Title {
	MR ("Mr."),
	MS ("Ms."),
	MRS("Mrs."),
	DR ("Dr."),
	PROF ("Prof.");

	private String title;
	Title(String title){
		this.title=title;
	}

	public String getTitle() {
		return title;
	}

}
