	
	package com.school.model;


	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

		@Entity
		@Table(name="ITEnrollmentInterest")
		public class ITEnrollmentInterest {
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int id;			
			@Column(length = 50)
			private String fullName;
			@Column(length = 35)
			private String email;
			@Column(length = 10)
			private String mobile;	
			@Column(length = 1000)
			private String itEnrollmentInterest;
			@Column
			private String comment;
			@Column
			private String colour;	
			@Column
			private String created;
			
			public ITEnrollmentInterest() {
				super();
			}			
			
			public ITEnrollmentInterest(int id, String fullName, String email, String mobile,
					String itEnrollmentInterest, String comment, String colour, String created) {
				super();
				this.id = id;
				this.fullName = fullName;
				this.email = email;
				this.mobile = mobile;
				this.itEnrollmentInterest = itEnrollmentInterest;
				this.comment = comment;
				this.colour = colour;
				this.created = created;
			}

			public String getComment() {
				return comment;
			}

			public void setComment(String comment) {
				this.comment = comment;
			}

			public String getColour() {
				return colour;
			}

			public void setColour(String colour) {
				this.colour = colour;
			}

			public String getCreated() {
				return created;
			}

			public void setCreated(String created) {
				this.created = created;
			}
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
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
			public String getMobile() {
				return mobile;
			}
			public void setMobile(String mobile) {
				this.mobile = mobile;
			}
			public String getItEnrollmentInterest() {
				return itEnrollmentInterest;
			}
			public void setItEnrollmentInterest(String itEnrollmentInterest) {
				this.itEnrollmentInterest = itEnrollmentInterest;
			}

			@Override
			public String toString() {
				return "ITEnrollmentInterest [fullName=" + fullName + ", email=" + email + ", mobile=" + mobile
						+ ", itEnrollmentInterest=" + itEnrollmentInterest + ", comment=" + comment + ", colour="
						+ colour + ", created=" + created + "]";
			}
}
