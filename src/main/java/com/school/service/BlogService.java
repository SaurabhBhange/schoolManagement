package com.school.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.BlogPost;
import com.school.repository.BlogRepo;

@Service
public class BlogService {

	@Autowired
	private BlogRepo blogRepo;

	// for creating blog
	public BlogPost createPost(BlogPost post) {
		String dateOfPost = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		post.setCreatedOn(dateOfPost);
		try {
			return blogRepo.save(post);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Page<BlogPost> getAllUsersWithType(Integer pageNo, Integer pageSize, String typeofblog) {

		Pageable paging = PageRequest.of(pageNo, pageSize);
		try {
			return blogRepo.findBytypeofblog(typeofblog, paging);
		} catch (RecordNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Page<BlogPost> getAllUsers(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		try {
			return blogRepo.findAll(paging);
		} catch (RecordNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// get post by id
	public BlogPost getPostById(Long id) {
		try {

			return blogRepo.findById(id);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;
		}
	}

//Edit or Update Blog

	public BlogPost UpdatePost(BlogPost entity) throws RecordNotFoundException {
		return blogRepo.save(entity);
	}

	// delete post by id
	@Transactional
	public BlogPost deletePost(Long id) {
		try {

			return blogRepo.deleteById(id);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;
		}

	}

	public BlogPost findById(long id) {

		return blogRepo.findById(id);
	}
}
