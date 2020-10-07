package com.cattrax.domain.enums;

public enum DifficultyLevel {
	NO_DIFFICULTY		(1, 0),
	LITTLE_DIFFICULTY	(2, 0.436),
	SOME_DIFFICULTY 	(3, 0.872),
	QUITE_DIFFICULT 	(4, 1.308),
	VERY_DIFFICULT 		(5, 1.7344),
	EXTREMELY_DIFFICULT (6, 2.18);

	private Integer difficultyValue;
	private double difficultyScore;

	DifficultyLevel (Integer difficultyValue, double difficultyScore){
		this.difficultyValue=difficultyValue;
		this.difficultyScore=difficultyScore;
	}

	public Integer getDifficultyValue() {
		return difficultyValue;
	}

	public double getDifficultyScore() {
		return difficultyScore;
	}

}
