package com.school.model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "studenttbl")
@JsonIgnoreProperties(ignoreUnknown = true)

public class StudentPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(length = 32, unique = true, nullable = false)
	private String firstName;
	@Column(length = 32)
	private String middleName;
	@Column(length = 32)
	private String lastName;
	@Column(length = 10)
	private String gender;
	@Column(length = 32)
	private String mothersName;
	@Column(length = 10)
	@Min(value = 10)
	private String mobileNumber;
	@Column(length = 10)
	@Min(value = 10)
	private String parentNumber;
	@Column(length = 250)
	private String address;
	@Column(length = 6)
	private String pincode;
	@Column(length = 20)
	private String city;
	@Column(length = 20)
	private String state;
	@Column(length = 12, unique = true)
	@Min(value = 12)
	private String adharNumber;
	@Column(length = 50)
	private String dateOfBirth;
	@Column(length = 50)
	private String higherQualification;
	@Column(length = 12)
	private String dateOfJoining;
	@Column(length = 12)
	private String dateOfLeaving;
	@Column(length = 40, unique = true, nullable = false)
	private String email;
	@Column(length = 40)
	private String username;
	@Column(length = 200)
	private String password;
	@Column(length = 10)
	private String bloodGroup;
	@Lob
	@Column(length = 10000)
	private byte[] image;

	@Column(length = 100)
	private String fileName;

	@JsonIgnore
	@OneToMany(mappedBy = "studenttbl", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CourseBatchMapping> coursebatchmapping;

	public Set<CourseBatchMapping> getCoursebatchmapping() {
		return coursebatchmapping;
	}

	public void setCoursebatchmapping(Set<CourseBatchMapping> coursebatchmapping) {
		this.coursebatchmapping = coursebatchmapping;
	}

	public StudentPojo() {
		super();

	}

	public StudentPojo(String userName, String password, List studList) {
		this.username = userName;
		this.password = password;
	}

	public StudentPojo(String firstName, String middleName, String lastName, String gender, String mothersName,
			String mobileNumber, String parentNumber, String address, String pincode, String city, String state,
			String adharNumber, String dateOfBirth, String higherQualification, String dateOfJoining,
			String dateOfLeaving, String email, String username, String password, String bloodGroup, byte[] image,
			String fileName) {
		super();

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.mothersName = mothersName;
		this.mobileNumber = mobileNumber;
		this.parentNumber = parentNumber;
		this.address = address;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.adharNumber = adharNumber;
		this.dateOfBirth = dateOfBirth;
		this.higherQualification = higherQualification;
		this.dateOfJoining = dateOfJoining;
		this.dateOfLeaving = dateOfLeaving;
		this.email = email;
		this.username = username;
		this.password = password;
		this.bloodGroup = bloodGroup;
		this.image = image;
		this.fileName = fileName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getParentNumber() {
		return parentNumber;
	}

	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdharNumber() {
		return adharNumber;
	}

	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHigherQualification() {
		return higherQualification;
	}

	public void setHigherQualification(String higherQualification) {
		this.higherQualification = higherQualification;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDateOfLeaving() {
		return dateOfLeaving;
	}

	public void setDateOfLeaving(String dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "StudentPojo [userId=" + userId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", gender=" + gender + ", mothersName=" + mothersName + ", mobileNumber="
				+ mobileNumber + ", parentNumber=" + parentNumber + ", address=" + address + ", pincode=" + pincode
				+ ", city=" + city + ", state=" + state + ", adharNumber=" + adharNumber + ", dateOfBirth="
				+ dateOfBirth + ", higherQualification=" + higherQualification + ", dateOfJoining=" + dateOfJoining
				+ ", dateOfLeaving=" + dateOfLeaving + ", email=" + email + ", username=" + username + ", password="
				+ password + ", bloodGroup=" + bloodGroup + ", image=" + Arrays.toString(image) + "]";
	}

}
