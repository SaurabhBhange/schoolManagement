package com.school.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mechmanycourseworkshop")
public class MechManyCourseWorkshop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int workshopid;

	@Column(length = 100)
	private String fullName;

	@Column(length = 50)
	private String email;

	@Column(length = 10)
	private String mobNumber;

	@Column(length = 10)
	private String coursePrice;

	@Column
	private String RzrpOrderId;

	@Column
	private String RzrpPaymentId;

	@Column
	private String RzrpSign;

	@Column
	private String PaymentStatus;

	@JsonIgnore
	@OneToMany(mappedBy = "mechworkshops", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MechCourseWorkshopMapping> mechcourseworkshopmapping;

	public MechManyCourseWorkshop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MechManyCourseWorkshop(String fullName, String email, String mobNumber, String coursePrice,
			String rzrpOrderId, String rzrpPaymentId, String rzrpSign, String paymentStatus) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.mobNumber = mobNumber;
		this.coursePrice = coursePrice;
		RzrpOrderId = rzrpOrderId;
		RzrpPaymentId = rzrpPaymentId;
		RzrpSign = rzrpSign;
		PaymentStatus = paymentStatus;
	}

	public String getRzrpOrderId() {
		return RzrpOrderId;
	}

	public void setRzrpOrderId(String rzrpOrderId) {
		RzrpOrderId = rzrpOrderId;
	}

	public String getRzrpPaymentId() {
		return RzrpPaymentId;
	}

	public void setRzrpPaymentId(String rzrpPaymentId) {
		RzrpPaymentId = rzrpPaymentId;
	}

	public String getRzrpSign() {
		return RzrpSign;
	}

	public void setRzrpSign(String rzrpSign) {
		RzrpSign = rzrpSign;
	}

	public String getPaymentStatus() {
		return PaymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		PaymentStatus = paymentStatus;
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

	public String getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}

	public int getWorkshopid() {
		return workshopid;
	}

	public void setWorkshopid(int workshopid) {
		this.workshopid = workshopid;
	}

	public Set<MechCourseWorkshopMapping> getMechcourseworkshopmapping() {
		return mechcourseworkshopmapping;
	}

	public void setMechcourseworkshopmapping(Set<MechCourseWorkshopMapping> mechcourseworkshopmapping) {
		this.mechcourseworkshopmapping = mechcourseworkshopmapping;
	}

	@Override
	public String toString() {
		return "MechManyCourseWorkshop [workshopid=" + workshopid + ", fullName=" + fullName + ", email=" + email
				+ ", mobNumber=" + mobNumber + ", coursePrice=" + coursePrice + ", RzrpOrderId=" + RzrpOrderId
				+ ", RzrpPaymentId=" + RzrpPaymentId + ", RzrpSign=" + RzrpSign + ", PaymentStatus=" + PaymentStatus
				+ ", mechcourseworkshopmapping=" + mechcourseworkshopmapping + "]";
	}

}
