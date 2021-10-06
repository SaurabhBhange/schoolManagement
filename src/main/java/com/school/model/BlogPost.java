package com.school.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "blogpost")
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Lob
	@Column
	private String content;

	@Column(length = 10)
	private String createdOn;

	@Column(length = 50)
	private String username;

	@Column(name = "typeofblog")
	private String typeofblog;

	@Column
	private String totalComment;

	@JsonIgnore
	@OneToMany(mappedBy = "blogs", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BlogCommentPost> comments;

	public BlogPost() {
		super();
	}

	public BlogPost(String title, String content, String createdOn, String username, String typeofblog,
			String totalComment, Set<BlogCommentPost> comments) {
		super();
		this.title = title;
		this.content = content;
		this.createdOn = createdOn;
		this.username = username;
		this.typeofblog = typeofblog;
		this.totalComment = totalComment;
		this.comments = comments;
	}

	public String getTypeofblog() {
		return typeofblog;
	}

	public void setTypeofblog(String typeofblog) {
		this.typeofblog = typeofblog;
	}

	public Set<BlogCommentPost> getComments() {
		return comments;
	}

	public void setComments(Set<BlogCommentPost> comments) {
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(String totalComment) {
		this.totalComment = totalComment;
	}

	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", title=" + title + ", content=" + content + ", createdOn=" + createdOn
				+ ", username=" + username + ", totalComment=" + totalComment + ", comments=" + comments + "]";
	}

}
