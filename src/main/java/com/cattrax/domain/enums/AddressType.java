package com.cattrax.domain.enums;

public enum AddressType {

	PHYSICAL ("Physical"),
	POSTAL ("Postal");

	private String addressType;
	AddressType(String addressType){
		this.addressType=addressType;
	}
	public String getAddressType() {
		return addressType;
	}
}
