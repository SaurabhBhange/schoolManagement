package com.school.controller;

import java.util.List;

import com.school.model.Employment;
import com.school.model.InternEnrollemnt;
import com.school.model.WebinarEnrollment;

public class UserPaginationDTO {

	private long totalItems;

	private int totalPages;

	private List<InternEnrollemnt> intern;

	private List<WebinarEnrollment> web;

	private List<Employment> emp;

	public UserPaginationDTO() {
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

	public List<InternEnrollemnt> getIntern() {
		return intern;
	}

	public void setIntern(List<InternEnrollemnt> intern) {
		this.intern = intern;
	}

	public List<WebinarEnrollment> getWeb() {
		return web;
	}

	public void setWeb(List<WebinarEnrollment> web) {
		this.web = web;
	}

	public List<Employment> getEmp() {
		return emp;
	}

	public void setEmp(List<Employment> emp) {
		this.emp = emp;
	}

	public UserPaginationDTO(long totalItems, int totalPages, List<InternEnrollemnt> intern) {
		super();
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.intern = intern;
	}

	public UserPaginationDTO(List<WebinarEnrollment> web, long totalItems, int totalPages) {
		super();
		this.web = web;
		this.totalItems = totalItems;
		this.totalPages = totalPages;

	}

	public UserPaginationDTO(long totalItems, List<Employment> emp, int totalPages) {
		super();
		this.totalItems = totalItems;
		this.emp = emp;
		this.totalPages = totalPages;

	}

	@Override
	public String toString() {
		return "UserPaginationDTO [totalItems=" + totalItems + ", totalPages=" + totalPages + ", intern=" + intern
				+ ", web=" + web + ", emp=" + emp + "]";
	}

}
