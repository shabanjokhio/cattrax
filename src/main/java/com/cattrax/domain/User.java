package com.cattrax.domain;

import com.cattrax.domain.enums.RoleType;
import com.cattrax.domain.enums.Status;
import com.cattrax.domain.enums.Title;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CATTRAX_USER")
public class User extends AbstractDomainEntity {

	@Column(nullable = false) @Enumerated (EnumType.STRING)
	private Title userTitle;

	@Column (name = "FIRST_NAMES", nullable = false)
	private String firstNames;

	@Column (name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "PHONE_NUMBER", nullable = false)
	private String phoneNumber;

	@Column (name = "USER_EMAIL" , nullable = false)
	private String userEmail;

	@Column (name = "USER_PASSWORD", nullable = false)
	private String userPassword;

	@Embedded private Address userAddress;

	@Temporal (TemporalType.TIMESTAMP)
	private Date lastLogin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PASSWORD_CHANGED_DATE")
	private Date lastPasswordUpdated;

	@Column(name = "FAIL_ATTEMPTS")
	private int loginFailedAttempts;

	@Enumerated(EnumType.STRING)
	private Status userStatus;

	@Embedded @Column (name = "REGISTRATION")
	private Registration professionalRegistration;

	@ManyToMany
	@JoinTable @LazyCollection(LazyCollectionOption.FALSE)
	private List<Practice> worksIn = new ArrayList<>();

	@OneToOne
	private Practice currentPractice; //currently working at practice

	@OneToMany(mappedBy = "referredBy", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<Patient> patientsReferred = new ArrayList<>();

	@OneToMany (mappedBy = "registeredBy", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Patient> patientsRegistered= new ArrayList<>();

	@OneToMany (mappedBy = "referredBy", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<CattraxReferral> referralsIssued = new ArrayList<>();

	//refactored. we do not really need a separate roletable, we have only few roles, only roleType will work.
	@Column(name = "ROLE_TYPE") @Enumerated(EnumType.STRING)
	private RoleType roleType;

	public Practice getCurrentPractice() {
		return currentPractice;
	}

	public void setCurrentPractice(Practice currentPractice) {
		this.currentPractice = currentPractice;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	//ADD METHOD:
	public void addPractice(Practice newPracticeToJoin){
		//For this user, add new user:
		if(!this.getWorksIn().contains(newPracticeToJoin)){
			this.getWorksIn().add(newPracticeToJoin);
		}
		//For this practice, add user to employed list
		if(!newPracticeToJoin.getEmployedUsers().contains(this)){
			newPracticeToJoin.getEmployedUsers().add(this);
		}
	}
	//REMOVE METHOD:
	public void removePractice(Practice practiveToLeave){
		this.getWorksIn().remove(practiveToLeave);
		practiveToLeave.getEmployedUsers().remove(this);
	}
	//Though we are not going to use this functionality ever.
	public void addReferralIssued(CattraxReferral referral){
		//add the given referral to this user and set this user as referredBy for given referral
		if(!this.getReferralsIssued().contains(referral)){
			this.getReferralsIssued().add(referral);
			referral.setReferredBy(this);
		}
	}
	public void removeReferralIssued(CattraxReferral referral){
		referral.setReferredBy(null); //remove null referred By for this referral
		this.referralsIssued.remove(referral);//remove referral given referral from the list of referralIssued for this user:
	}

	public void addPatientReferred(Patient patientToBeReferred){
		//TODO: implement this.
		//add the patient in the list of the patients referred by this User, if it is already not there.
		if(!this.getPatientsReferred().contains(patientToBeReferred)){
			this.getPatientsReferred().add(patientToBeReferred);
			patientToBeReferred.setReferredBy(this); //set this user as the one who referrs this user:
		}
	}
	public void removePatientReferred(Patient patient){
		//TODO: implement this
		//remove referredBy null for this patient and remove patient from the list of patients referred.
		patient.setReferredBy(null);
		this.getPatientsReferred().remove(patient);
	}

	public void addPatientRegistered(Patient newPatientToRegister){
		if(!this.getPatientsRegistered().contains(newPatientToRegister)){
			this.getPatientsRegistered().add(newPatientToRegister);
			newPatientToRegister.setRegisteredBy(this); //set this user as one who registers.
			newPatientToRegister.setRegisteredAt(this.getCurrentPractice());
		}
	}

	public void removePatientRegistered(Patient patientToRemove){
		//TODO: implement this:
		patientToRemove.setRegisteredBy(null);//not registered by anyone
		patientToRemove.setRegisteredAt(null); // not registered at a practice
		this.getPatientsRegistered().remove(patientToRemove); // remove from the list of registered patients for this user:
	}

	public void transferPatientToMyself(Patient patient){
		Practice currentPractice = patient.getRegisteredAt();
		//remove from current practice:
		patient.setRegisteredAt(null);
		currentPractice.getRegisteredPatients().remove(patient);

		//add to the new practice:
		Practice newPractice = this.getCurrentPractice(); //get the current practice of this user
		patient.setRegisteredAt(newPractice);
		newPractice.getRegisteredPatients().add(patient);
	}

	public void removePatientFromMyself(Patient patient){
		Practice currentPractice = this.getCurrentPractice();
		patient.setRegisteredAt(null);
		currentPractice.getRegisteredPatients().remove(patient);
	}

	public Title getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(Title userTitle) {
		this.userTitle = userTitle;
	}

	public String getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}


	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastPasswordUpdated() {
		return lastPasswordUpdated;
	}

	public void setLastPasswordUpdated(Date lastPasswordUpdated) {
		this.lastPasswordUpdated = lastPasswordUpdated;
	}

	public int getLoginFailedAttempts() {
		return loginFailedAttempts;
	}

	public void setLoginFailedAttempts(int loginFailedAttempts) {
		this.loginFailedAttempts = loginFailedAttempts;
	}

	public Status getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Status userStatus) {
		this.userStatus = userStatus;
	}

	public Registration getProfessionalRegistration() {
		return professionalRegistration;
	}

	public void setProfessionalRegistration(Registration professionalRegistration) {
		this.professionalRegistration = professionalRegistration;
	}

	public List<Practice> getWorksIn() {
		return worksIn;
	}

	public void setWorksIn(List<Practice> worksIn) {
		this.worksIn = worksIn;
	}

	public List<Patient> getPatientsReferred() {
		return patientsReferred;
	}

	public void setPatientsReferred(List<Patient> patientsReferred) {
		this.patientsReferred = patientsReferred;
	}

	public List<Patient> getPatientsRegistered() {
		return patientsRegistered;
	}

	public void setPatientsRegistered(List<Patient> patientsRegistered) {
		this.patientsRegistered = patientsRegistered;
	}

	public List<CattraxReferral> getReferralsIssued() {
		return referralsIssued;
	}

	public void setReferralsIssued(List<CattraxReferral> referralsIssued) {
		this.referralsIssued = referralsIssued;
	}

	@Override
	public String toString() {
		return "User{" +
				"userTitle=" + userTitle +
				", firstNames='" + firstNames + '\'' +
				", lastName='" + lastName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", userEmail='" + userEmail + '\'' +
				", userPassword='" + userPassword + '\'' +
				", userAddress=" + userAddress +
				", lastLogin=" + lastLogin +
				", lastPasswordUpdated=" + lastPasswordUpdated +
				", loginFailedAttempts=" + loginFailedAttempts +
				", userStatus=" + userStatus +
				", professionalRegistration=" + professionalRegistration +
				", worksIn=" + worksIn +
				", patientsReferred=" + patientsReferred +
				", patientsRegistered=" + patientsRegistered +
				", referralsIssued=" + referralsIssued +
				", roleType=" + roleType +
				'}';
	}
}
