package com.school.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.BlogCommentPost;
import com.school.model.BlogPost;
import com.school.repository.BlogCommentRepo;
import com.school.repository.BlogRepo;

@Service
public class BlogCommentService {

	@Autowired
	private BlogRepo blogRepo;

	@Autowired
	private BlogCommentRepo blogCommentRepo;



	public BlogCommentPost saveData(String name, String emailId, String message, Long id) {
		BlogPost blog = blogRepo.findById(id);
		if (id == blog.getId()) {
			String dateOfComment = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			BlogCommentPost comment = new BlogCommentPost(name, emailId, message, blog, dateOfComment);
			BlogCommentPost blogs = blogCommentRepo.save(comment);
			return blogs;
		} else {
			return null;
		}
	}

	public List<BlogCommentPost> getData() {
		return blogCommentRepo.findAll();
	}

	public List<BlogCommentPost> getDataById(Long id) {
		try {
			List<BlogCommentPost> list = blogCommentRepo.findByData(id);
			return list;
		} catch (RecordNotFoundException e) {
			return null;
		}
	}

	public void deleteData(int commentId) {
		try {
			blogCommentRepo.deleteById(commentId);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

}
