package com.cattrax.domain.enums;

public enum PostSubcapsular {

	ABSENT ("Post subcapsular Absent"),
	P1 ("P1"),
	P2 ("P2"),
	P3 ("P3"),
	P4 ("P4"),
	P5 ("P5");

	private String postSubCapsular;
	PostSubcapsular(String postSubCapsular) {
		this.postSubCapsular = postSubCapsular;
	}
	public String getPostSubCapsular() {
		return postSubCapsular;
	}
}
