package com.school.dto;

import java.io.Serializable;
import java.util.List;

import com.school.model.BlogCommentPost;

public class BlogCommentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int size;

	private List<BlogCommentPost> blogCommentPost;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<BlogCommentPost> getBlogCommentPost() {
		return blogCommentPost;
	}

	public void setBlogCommentPost(List<BlogCommentPost> blogCommentPost) {
		this.blogCommentPost = blogCommentPost;
	}

}
