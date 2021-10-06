package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "referralpartner")
public class ReferralPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int referralid;

	@Column(length = 100)
	private String firstName;

	@Column(length = 100)
	private String lastName;

	@Column
	private String referralcode;

	@Column
	private int partnerComission;

	@Column
	private int studentOfferPrice;

	@Column(length = 50)
	private String username;

	@Column(length = 10)
	private String mobNumber;

	@Column
	private String city;

	@Column
	private String profession;

	@Column
	private String bankName;

	@Column
	private String AccountNo;

	@Column
	private String ifsc;

	@Column
	private String accholdername;

	@Column
	private Boolean isEnabled;

	@Column
	private int totalRevenue = 0;

	@Column
	private int pendingamount = 0;

	@Column
	private int paidamount = 0;

	private String role;

	public ReferralPartner() {
		super();

	}

	public ReferralPartner(String firstName, String lastName, String referralcode, int partnerComission,
			int studentOfferPrice, String username, String mobNumber, String city, String profession, String bankName,
			String accountNo, String ifsc, String accholdername, Boolean isEnabled, int totalRevenue, int pendingamount,
			int paidamount, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.referralcode = referralcode;
		this.partnerComission = partnerComission;
		this.studentOfferPrice = studentOfferPrice;
		this.username = username;
		this.mobNumber = mobNumber;
		this.city = city;
		this.profession = profession;
		this.bankName = bankName;
		AccountNo = accountNo;
		this.ifsc = ifsc;
		this.accholdername = accholdername;
		this.isEnabled = isEnabled;
		this.totalRevenue = totalRevenue;
		this.pendingamount = pendingamount;
		this.paidamount = paidamount;
		this.role = role;
	}

	public int getReferralid() {
		return referralid;
	}

	public void setReferralid(int referralid) {
		this.referralid = referralid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getReferralcode() {
		return referralcode;
	}

	public void setReferralcode(String referralcode) {
		this.referralcode = referralcode;
	}

	public int getPartnerComission() {
		return partnerComission;
	}

	public void setPartnerComission(int partnerComission) {
		this.partnerComission = partnerComission;
	}

	public int getStudentOfferPrice() {
		return studentOfferPrice;
	}

	public void setStudentOfferPrice(int studentOfferPrice) {
		this.studentOfferPrice = studentOfferPrice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAccholdername() {
		return accholdername;
	}

	public void setAccholdername(String accholdername) {
		this.accholdername = accholdername;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public int getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(int totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public int getPendingamount() {
		return pendingamount;
	}

	public void setPendingamount(int pendingamount) {
		this.pendingamount = pendingamount;
	}

	public int getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(int paidamount) {
		this.paidamount = paidamount;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ReferralPartner [referralid=" + referralid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", referralcode=" + referralcode + ", partnerComission=" + partnerComission + ", studentOfferPrice="
				+ studentOfferPrice + ", username=" + username + ", mobNumber=" + mobNumber + ", city=" + city
				+ ", profession=" + profession + ", bankName=" + bankName + ", AccountNo=" + AccountNo + ", ifsc="
				+ ifsc + ", accholdername=" + accholdername + ", isEnabled=" + isEnabled + ", totalRevenue="
				+ totalRevenue + ", pendingamount=" + pendingamount + ", paidamount=" + paidamount + ", role=" + role
				+ "]";
	}

}
