package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registeredrefer")
public class RegisteredRefer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int registeredreferid;

	@Column(length = 100)
	private String fullName;

	@Column(length = 50)
	private String email;

	@Column(length = 10)
	private String mobNumber;

	@Column
	private String courseName;

	@Column
	private String coupen;

	@Column
	private int discount = 0;

	@Column
	private String extraCoupen;

	@Column
	private int extraDiscount = 0;

	@Column
	private int totalDiscount = 0;

	@Column
	private String RegistrationDate;

	@Column
	private String AdmissionDate;

	@Column
	private Boolean paymentStatus;

	@Column
	private String paymentdate;

	@Column
	private Boolean Admissionstatus;

	@Column
	private String messgae;

	public RegisteredRefer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisteredRefer(String fullName, String email, String mobNumber, String courseName, String coupen,
			int discount, String extraCoupen, int extraDiscount, int totalDiscount, String registrationDate,
			String admissionDate, Boolean paymentStatus, String paymentdate, Boolean admissionstatus, String messgae) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.mobNumber = mobNumber;
		this.courseName = courseName;
		this.coupen = coupen;
		this.discount = discount;
		this.extraCoupen = extraCoupen;
		this.extraDiscount = extraDiscount;
		this.totalDiscount = totalDiscount;
		RegistrationDate = registrationDate;
		AdmissionDate = admissionDate;
		this.paymentStatus = paymentStatus;
		this.paymentdate = paymentdate;
		Admissionstatus = admissionstatus;
		this.messgae = messgae;
	}

	public int getRegisteredreferid() {
		return registeredreferid;
	}

	public void setRegisteredreferid(int registeredreferid) {
		this.registeredreferid = registeredreferid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCoupen() {
		return coupen;
	}

	public void setCoupen(String coupen) {
		this.coupen = coupen;
	}

	public String getExtraCoupen() {
		return extraCoupen;
	}

	public void setExtraCoupen(String extraCoupen) {
		this.extraCoupen = extraCoupen;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getExtraDiscount() {
		return extraDiscount;
	}

	public void setExtraDiscount(int extraDiscount) {
		this.extraDiscount = extraDiscount;
	}

	public int getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(int totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public String getRegistrationDate() {
		return RegistrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		RegistrationDate = registrationDate;
	}

	public String getAdmissionDate() {
		return AdmissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		AdmissionDate = admissionDate;
	}

	public Boolean getAdmissionstatus() {
		return Admissionstatus;
	}

	public void setAdmissionstatus(Boolean admissionstatus) {
		Admissionstatus = admissionstatus;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	@Override
	public String toString() {
		return "RegisteredRefer [registeredreferid=" + registeredreferid + ", fullName=" + fullName + ", email=" + email
				+ ", mobNumber=" + mobNumber + ", courseName=" + courseName + ", coupen=" + coupen + ", discount="
				+ discount + ", extraCoupen=" + extraCoupen + ", extraDiscount=" + extraDiscount + ", totalDiscount="
				+ totalDiscount + ", RegistrationDate=" + RegistrationDate + ", AdmissionDate=" + AdmissionDate
				+ ", paymentStatus=" + paymentStatus + ", paymentdate=" + paymentdate + ", Admissionstatus="
				+ Admissionstatus + ", messgae=" + messgae + "]";
	}

}
