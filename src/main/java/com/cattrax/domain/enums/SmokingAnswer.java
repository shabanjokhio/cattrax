package com.cattrax.domain.enums;

public enum SmokingAnswer {

	YES ("Yes"),
	NO ("No"),
	EX_28_DAYS_AGO("28 days ago");

	private String smokingAnswer;

	SmokingAnswer(String smokingAnswer){
		this.smokingAnswer=smokingAnswer;
	}

	String getSmokingAnswer() {
		return smokingAnswer;
	}
}
