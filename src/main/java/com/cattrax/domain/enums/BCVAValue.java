package com.cattrax.domain.enums;

public enum BCVAValue {

	_4_8 (4.8),
	_6 (6),
	_7_5(7.5),
	_9 (9),
	_15(15),
	_18(18),
	_21(21),
	_24(24),
	_36 (36),
	_48 (48),
	_60 (60),
	_120(120),
	CF_30_CM (125), //TODO: Need to confirm following values from James. But for implementation should be OK.
	HM_30CM (135),
	PL (140),
	NPL (145);

	private double bcvaValue;

	BCVAValue (double bcvaValue){
		this.bcvaValue=bcvaValue;
	}

	public double getBcvaValue() {
		return bcvaValue;
	}
}
