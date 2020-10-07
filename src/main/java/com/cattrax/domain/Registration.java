package com.cattrax.domain;

import com.cattrax.domain.enums.RegistrationType;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Registration implements Serializable{
	@Transient
	private static final long serialVersionUID = 1L;

	@Column(name = "REGISTRATION_NUMBER")
	private String regNumber;

	@Enumerated(EnumType.STRING)
	private RegistrationType regType;

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public RegistrationType getRegType() {
		return regType;
	}

	public void setRegType(RegistrationType regType) {
		this.regType = regType;
	}

	@Override
	public String toString() {
		return "Registration{" +
				"regNumber='" + regNumber + '\'' +
				", regType=" + regType +
				'}';
	}
}
