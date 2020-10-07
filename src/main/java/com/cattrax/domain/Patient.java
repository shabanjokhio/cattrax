package com.cattrax.domain;

import com.cattrax.domain.enums.Ethnicity;
import com.cattrax.domain.enums.Gender;
import com.cattrax.domain.enums.SmokingAnswer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity //i have removed the constraint, untill decided which thing will be unique:
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"PATIENT_FIRST_NAMES","LAST_NAME","PATIENT_DOB"}))
public class Patient extends AbstractDomainEntity {

	@Column (name = "PATIENT_FIRST_NAMES", nullable = false)
	private String patientFirstNames;

	@Column (name="LAST_NAME", nullable = false)
	private String lastName;

	@Column ( nullable = false)
	private String patientPhoneNumber;

	@Column (nullable = false)
	private String patientEmail;

	@Column(name = "PATIENT_DOB", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date patientDOB;

	@Column (nullable = false)
	private String patientNHI;

	@Column(nullable = false)
    @Enumerated (EnumType.STRING)
	private Gender patientGender;

	@Column(nullable = false)
    @Enumerated (EnumType.STRING)
	private Ethnicity patientEthnicity;

	private String ethnicityOtherDescription;

	@Embedded
	private Address patientAddress;

	@Column (nullable = false) @Enumerated (EnumType.STRING)
    private SmokingAnswer patientSmoking;

	private String healthInsuranceProvider;

	@ManyToOne //optional should be false
	//@JoinColumn (unique = true, nullable = true, insertable = false, updatable = false)
	//TODO: make optional = false and nullable = false
	private User referredBy; //Many patients referred by one user. This patient is referred by one and only referrer.

	//@ManyToOne //(optional = true)// two or more patients can be registered at same practice, so unique is false
	//@JoinColumn (unique = false, nullable = true, insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) //change the Cascade Type to MERGE. Temporarily committed this:
	//@ManyToOne
	private Practice registeredAt; //We might need this in the transfer functionality:

	@ManyToOne (optional = true) //please note that unique should be false, as 2 or more patients can be
	// referred by same User or optometrist
	//@JoinColumn (unique = false, nullable = true, insertable = true, updatable = true)
	//TODO: make optional = false, note all the above properties are redundant.
	private User registeredBy;

	//I do not think that we need to record, initially how many referrals will the patient have.
	//A patient should have only the very basic attributes, e.g., registeredBy, registeredAt, referredBy:
	@OneToMany (targetEntity = CattraxReferral.class,
			mappedBy = "patientReferred",
			cascade = CascadeType.MERGE,	orphanRemoval = true, fetch = FetchType.EAGER)
	private List<CattraxReferral> referrals = new ArrayList<>();

	//add referral to the patient:
	public void addReferral(CattraxReferral referral){
		this.getReferrals().add(referral); //add this referral to the list of the referrals for this patient
		referral.setPatientReferred(this); //set this patient to the provided referral
	}
	public void removeReferral(CattraxReferral referral){
		referral.setPatientReferred(null);
		this.referrals.remove(referral);
	}

	public User getRegisteredBy() {
		return registeredBy;
	}

	public void setRegisteredBy(User registeredBy) {
		this.registeredBy = registeredBy;
	}

	public String getPatientFirstNames() {
		return patientFirstNames;
	}

	public void setPatientFirstNames(String patientFirstNames) {
		this.patientFirstNames = patientFirstNames;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatientPhoneNumber() {
		return patientPhoneNumber;
	}

	public void setPatientPhoneNumber(String patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public Date getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}

	public String getPatientNHI() {
		return patientNHI;
	}

	public void setPatientNHI(String patientNHI) {
		this.patientNHI = patientNHI;
	}

	public Gender getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(Gender patientGender) {
		this.patientGender = patientGender;
	}

	public Ethnicity getPatientEthnicity() {
		return patientEthnicity;
	}

	public void setPatientEthnicity(Ethnicity patientEthnicity) {
		this.patientEthnicity = patientEthnicity;
	}

	public String getEthnicityOtherDescription() {
		return ethnicityOtherDescription;
	}

	public void setEthnicityOtherDescription(String ethnicityOtherDescription) {
		this.ethnicityOtherDescription = ethnicityOtherDescription;
	}

	public Address getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(Address patientAddress) {
		this.patientAddress = patientAddress;
	}

	public SmokingAnswer getPatientSmoking() {
		return patientSmoking;
	}

	public void setPatientSmoking(SmokingAnswer patientSmoking) {
		this.patientSmoking = patientSmoking;
	}

	public String getHealthInsuranceProvider() {
		return healthInsuranceProvider;
	}

	public void setHealthInsuranceProvider(String healthInsuranceProvider) {
		this.healthInsuranceProvider = healthInsuranceProvider;
	}

	public User getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(User referredBy) {
		this.referredBy = referredBy;
	}

	public List<CattraxReferral> getReferrals() {
		return referrals;
	}

	public void setReferrals(List<CattraxReferral> referrals) {
		this.referrals = referrals;
	}

	public Practice getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Practice registeredAt) {
		this.registeredAt = registeredAt;
	}

	@Override
	public String toString() {
		return "Patient{" +
				"patientFirstNames='" + patientFirstNames + '\'' +
				", lastName='" + lastName + '\'' +
				", patientPhoneNumber='" + patientPhoneNumber + '\'' +
				", patientEmail='" + patientEmail + '\'' +
				", patientDOB=" + patientDOB +
				", patientNHI='" + patientNHI + '\'' +
				", patientGender=" + patientGender +
				", patientEthnicity=" + patientEthnicity +
				", ethnicityOtherDescription='" + ethnicityOtherDescription + '\'' +
				", patientAddress=" + patientAddress +
				", patientSmoking=" + patientSmoking +
				", healthInsuranceProvider='" + healthInsuranceProvider + '\'' +
				", referredBy=" + referredBy +
				", registeredBy=" + registeredBy +
				", registeredAt=" + registeredAt +
				", referrals=" + referrals +
				'}';
	}
}
