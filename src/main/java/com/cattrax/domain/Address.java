package com.cattrax.domain;

import com.cattrax.domain.enums.AddressType;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ConfigurationPropertiesBinding
public class Address implements Serializable{
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressLine4;
	private String addressLine5;
	private int postCode;
	private String city;
	private String country;
	private AddressType type;

	public  Address(){}

	public Address(String addressLine1, String addressLine2, String addressLine3, String addressLine4, String addressLine5, int postCode, String city, String country, AddressType type) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.addressLine4 = addressLine4;
		this.addressLine5 = addressLine5;
		this.postCode = postCode;
		this.city = city;
		this.country = country;
		this.type = type;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	public String getAddressLine5() {
		return addressLine5;
	}

	public void setAddressLine5(String addressLine5) {
		this.addressLine5 = addressLine5;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Address{" +
				"addressLine1='" + addressLine1 + '\'' +
				", addressLine2='" + addressLine2 + '\'' +
				", addressLine3='" + addressLine3 + '\'' +
				", addressLine4='" + addressLine4 + '\'' +
				", addressLine5='" + addressLine5 + '\'' +
				", postCode=" + postCode +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", type=" + type +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Address)) return false;

		Address address = (Address) o;

		if (getPostCode() != address.getPostCode()) return false;
		if (getAddressLine1() != null ? !getAddressLine1().equals(address.getAddressLine1()) : address.getAddressLine1() != null)
			return false;
		if (getAddressLine2() != null ? !getAddressLine2().equals(address.getAddressLine2()) : address.getAddressLine2() != null)
			return false;
		if (getAddressLine3() != null ? !getAddressLine3().equals(address.getAddressLine3()) : address.getAddressLine3() != null)
			return false;
		if (getAddressLine4() != null ? !getAddressLine4().equals(address.getAddressLine4()) : address.getAddressLine4() != null)
			return false;
		if (getAddressLine5() != null ? !getAddressLine5().equals(address.getAddressLine5()) : address.getAddressLine5() != null)
			return false;
		if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;

		return getCountry() == address.getCountry();
		//return //getType() == address.getType();
	}

	@Override
	public int hashCode() {
		int result = getAddressLine1() != null ? getAddressLine1().hashCode() : 0;
		result = 31 * result + (getAddressLine2() != null ? getAddressLine2().hashCode() : 0);
		result = 31 * result + (getAddressLine3() != null ? getAddressLine3().hashCode() : 0);
		result = 31 * result + (getAddressLine4() != null ? getAddressLine4().hashCode() : 0);
		result = 31 * result + (getAddressLine5() != null ? getAddressLine5().hashCode() : 0);
		result = 31 * result + getPostCode();
		result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
		result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
		result = 31 * result + (getType() != null ? getType().hashCode() : 0);
		return result;
	}
}
