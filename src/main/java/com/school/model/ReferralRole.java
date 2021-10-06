package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "referralrole")
public class ReferralRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int referralroleid;

	@Column
	private String referralrolename;

	public ReferralRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReferralRole(String referralrolename) {
		super();
		this.referralrolename = referralrolename;
	}

	public int getReferralroleid() {
		return referralroleid;
	}

	public void setReferralroleid(int referralroleid) {
		this.referralroleid = referralroleid;
	}

	public String getReferralrolename() {
		return referralrolename;
	}

	public void setReferralrolename(String referralrolename) {
		this.referralrolename = referralrolename;
	}

	@Override
	public String toString() {
		return "ReferralRole [referralroleid=" + referralroleid + ", referralrolename=" + referralrolename + "]";
	}

}
