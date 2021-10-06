package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workshop")
public class Workshop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int workshopId;

	@Column(length = 100)
	private String fullName;

	@Column(length = 50)
	private String email;

	@Column(length = 10)
	private String mobNumber;

	@Column(length = 200)
	private String courseName;

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

	public Workshop() {
		super();
	}

	public Workshop(String fullName, String email, String mobNumber, String rzrpOrderId, String rzrpPaymentId,
			String rzrpSign, String paymentStatus) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.mobNumber = mobNumber;
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

	public int getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(int workshopId) {
		this.workshopId = workshopId;
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

	public String getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}

	@Override
	public String toString() {
		return "Workshop [workshopId=" + workshopId + ", fullName=" + fullName + ", email=" + email + ", mobNumber="
				+ mobNumber + ", courseName=" + courseName + ", coursePrice=" + coursePrice + ", RzrpOrderId="
				+ RzrpOrderId + ", RzrpPaymentId=" + RzrpPaymentId + ", RzrpSign=" + RzrpSign + ", PaymentStatus="
				+ PaymentStatus + "]";
	}

	

}
