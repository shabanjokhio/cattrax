package com.cattrax.domain;

import com.cattrax.domain.enums.PracticeType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Practice extends AbstractDomainEntity{
	@Column(nullable = false) 	private String practiceName;
	@Column (nullable = false) 	private String practiceEmail;
	@Column (nullable = false) 	private String practicePhoneNumber;
	@Column (nullable = false)	private String practiceFaxNumber;
	@Embedded 					private Address practiceAddress;
	@OneToMany(mappedBy = "registeredAt", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Patient> registeredPatients= new ArrayList<>(); //TODO: This is the new variable needed for transferPatients functionality

	@Enumerated (EnumType.STRING) 	private PracticeType practiceType;
	@ManyToMany
	@JoinTable
	private List<User> employedUsers = new ArrayList<>();

	//adding new registered patient to the practice:
	public void addRegisteredPatient(Patient newPatient){
		if(!this.registeredPatients.contains(newPatient)){
			this.registeredPatients.add(newPatient);
			newPatient.setRegisteredAt(this);
		}
	}
	//remove a registered patient from this practice:
	public void removeRegisteredPatient(Patient patient){
		patient.setRegisteredAt(null);
		this.registeredPatients.remove(patient);
	}

	//adding new User as a new employee in this practice:
	public void addUser(User newUser){
		//add new User to this practice:
		if(!this.employedUsers.contains(newUser)){
			this.employedUsers.add(newUser);
		}
		//add this practice worksIn for this new user
		if(!newUser.getWorksIn().contains(this)){
			newUser.getWorksIn().add(this);
		}
	}
	//Remove method:
	public void removeUser(User userToRemove){
		this.employedUsers.remove(userToRemove); //remove user from this practice
		userToRemove.getWorksIn().remove(this); //remove this practice for the user:
	}

	public List<User> getEmployedUsers() {
		return employedUsers;
	}

	public void setEmployedUsers(List<User> employedUsers) {
		this.employedUsers = employedUsers;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public String getPracticeEmail() {
		return practiceEmail;
	}

	public void setPracticeEmail(String practiceEmail) {
		this.practiceEmail = practiceEmail;
	}

	public String getPracticePhoneNumber() {
		return practicePhoneNumber;
	}

	public void setPracticePhoneNumber(String practicePhoneNumber) {
		this.practicePhoneNumber = practicePhoneNumber;
	}

	public String getPracticeFaxNumber() {
		return practiceFaxNumber;
	}

	public void setPracticeFaxNumber(String practiceFaxNumber) {
		this.practiceFaxNumber = practiceFaxNumber;
	}

	public Address getPracticeAddress() {
		return practiceAddress;
	}

	public void setPracticeAddress(Address practiceAddress) {
		this.practiceAddress = practiceAddress;
	}

	public PracticeType getPracticeType() {
		return practiceType;
	}

	public void setPracticeType(PracticeType practiceType) {
		this.practiceType = practiceType;
	}

	public List<Patient> getRegisteredPatients() {
		return registeredPatients;
	}

	public void setRegisteredPatients(List<Patient> registeredPatients) {
		this.registeredPatients = registeredPatients;
	}

	@Override
	public String toString() {
		return "Practice{" +
				", practiceName='" + practiceName + '\'' +
				", practiceEmail='" + practiceEmail + '\'' +
				", practicePhoneNumber='" + practicePhoneNumber + '\'' +
				", practiceFaxNumber='" + practiceFaxNumber + '\'' +
				", practiceAddresses=" + practiceAddress +
				", practiceType=" + practiceType +
				'}';
	}
}
