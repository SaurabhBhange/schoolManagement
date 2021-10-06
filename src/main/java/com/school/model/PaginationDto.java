package com.school.model;

import java.util.List;

public class PaginationDto {
	
	private long totalItems;

	private int totalPages;
	
	private List<Contactus> contactList;
	
	private List<EnrollmentInterest> mechanicalEnrollment;
	
	private List<ITEnrollmentInterest> itEnrollment;

	public PaginationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Contactus> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contactus> contactList) {
		this.contactList = contactList;
	}

	public List<EnrollmentInterest> getMechanicalEnrollment() {
		return mechanicalEnrollment;
	}

	public void setMechanicalEnrollment(List<EnrollmentInterest> mechanicalEnrollment) {
		this.mechanicalEnrollment = mechanicalEnrollment;
	}

	public List<ITEnrollmentInterest> getItEnrollment() {
		return itEnrollment;
	}

	public void setItEnrollment(List<ITEnrollmentInterest> itEnrollment) {
		this.itEnrollment = itEnrollment;
	}

	public PaginationDto(long totalItems, int totalPages, List<Contactus> contactList) {
		super();
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.contactList = contactList;
	}

	public PaginationDto(long totalItems, List<EnrollmentInterest> mechanicalEnrollment,int totalPages) {
		super();
		this.totalItems = totalItems;
		this.mechanicalEnrollment = mechanicalEnrollment;
		this.totalPages = totalPages;
		
	}

	public PaginationDto(List<ITEnrollmentInterest> itEnrollment,long totalItems, int totalPages) {
		super();
		this.itEnrollment = itEnrollment;
		this.totalItems = totalItems;
		this.totalPages = totalPages;		
	}
	

}
