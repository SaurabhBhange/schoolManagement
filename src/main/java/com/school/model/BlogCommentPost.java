package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "blogpostcomments")
public class BlogCommentPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String emailId;

	@Column
	private String message;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private BlogPost blogs;

	@Column(length = 10)
	private String dateOfComment;

	public BlogCommentPost() {
		super();
	}

	public BlogCommentPost(String name, String emailId, String message, BlogPost blogs, String dateOfComment) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.message = message;
		this.blogs = blogs;
		this.dateOfComment = dateOfComment;
	}

	public BlogCommentPost(String name, String emailId, String message, String dateOfComment) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.message = message;
		this.dateOfComment = dateOfComment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateOfComment() {
		return dateOfComment;
	}

	public void setDate(String date) {
		this.dateOfComment = date;
	}

	public BlogPost getBlogs() {
		return blogs;
	}

	public void setBlogs(BlogPost blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "BlogCommentPost [commentId=" + commentId + ", name=" + name + ", emailId=" + emailId + ", message="
				+ message + ", blogs=" + blogs + ", dateOfComment=" + dateOfComment + "]";
	}

}
