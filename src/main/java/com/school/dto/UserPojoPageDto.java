package com.school.dto;

import java.util.List;

import com.school.model.GraphicEnrollment;
import com.school.model.StudentPojo;
import com.school.model.UserPojo;

public class UserPojoPageDto {

	private long totalItems;

	private int totalPages;

	private List<UserPojo> list;

	private List<StudentPojo> studlist;

	private List<GraphicEnrollment> graphiclist;

	public UserPojoPageDto() {
		super();
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

	public List<UserPojo> getList() {
		return list;
	}

	public void setList(List<UserPojo> list) {
		this.list = list;
	}

	public List<StudentPojo> getStudlist() {
		return studlist;
	}

	public void setStudlist(List<StudentPojo> studlist) {
		this.studlist = studlist;
	}

	public List<GraphicEnrollment> getGraphiclist() {
		return graphiclist;
	}

	public void setGraphiclist(List<GraphicEnrollment> graphiclist) {
		this.graphiclist = graphiclist;
	}

	public UserPojoPageDto(long totalItems, int totalPages, List<UserPojo> list) {
		super();
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.list = list;
	}

	public UserPojoPageDto(List<StudentPojo> studlist, long totalItems, int totalPages) {
		super();

		this.studlist = studlist;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
	}

	public UserPojoPageDto(long totalItems, List<GraphicEnrollment> graphiclist, int totalPages) {
		super();
		this.totalItems = totalItems;
		this.graphiclist = graphiclist;
		this.totalPages = totalPages;

	}

	@Override
	public String toString() {
		return "UserPojoPageDto [totalItems=" + totalItems + ", totalPages=" + totalPages + ", list=" + list + "]";
	}

}
