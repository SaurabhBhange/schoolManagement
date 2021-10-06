package com.school.dto;

import java.util.List;

import com.school.model.BlogPost;
import com.school.model.ReferralPartner;
import com.school.model.RegisteredRefer;

public class BlogPaginationDTO {

	private long totalItems;

	private int totalPages;

	private List<BlogPost> blogs;

	private List<ReferralPartner> referlist;

	private List<RegisteredRefer> registeredList;

	public BlogPaginationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogPaginationDTO(long totalItems, int totalPages, List<BlogPost> blogs) {
		super();
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.blogs = blogs;
	}

	public BlogPaginationDTO(long totalItems, List<ReferralPartner> referlist, int totalPages) {
		super();
		this.totalItems = totalItems;
		this.referlist = referlist;
		this.totalPages = totalPages;

	}

	public BlogPaginationDTO(List<RegisteredRefer> registeredList, long totalItems, int totalPages) {
		super();
		this.registeredList = registeredList;
		this.totalItems = totalItems;
		this.totalPages = totalPages;

	}

	public List<RegisteredRefer> getRegisteredList() {
		return registeredList;
	}

	public void setRegisteredList(List<RegisteredRefer> registeredList) {
		this.registeredList = registeredList;
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

	public List<BlogPost> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<BlogPost> blogs) {
		this.blogs = blogs;
	}

	public List<ReferralPartner> getReferlist() {
		return referlist;
	}

	public void setReferlist(List<ReferralPartner> referlist) {
		this.referlist = referlist;
	}

	@Override
	public String toString() {
		return "BlogPaginationDTO [totalItems=" + totalItems + ", totalPages=" + totalPages + ", blogs=" + blogs
				+ ", referlist=" + referlist + ", registeredList=" + registeredList + "]";
	}

}
