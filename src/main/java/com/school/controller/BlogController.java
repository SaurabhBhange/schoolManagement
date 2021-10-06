package com.school.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.BlogPaginationDTO;
import com.school.exception.RecordNotFoundException;
import com.school.model.BlogPost;
import com.school.repository.BlogCommentRepo;
import com.school.repository.BlogRepo;
import com.school.service.BlogService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private BlogCommentRepo commentRepo;

	@Autowired
	private BlogRepo blogRepo;


	// API For creating Blog
	@PostMapping("/createblog")
	public ResponseEntity<?> createBlog(@RequestBody BlogPost post) {
		BlogPost blog = blogService.createPost(post);

		if (blog != null) {
			return new ResponseEntity<BlogPost>(blog, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("blog can not be posted", HttpStatus.NO_CONTENT);
		}
	}

	// API For get Allavailable blogs
	@GetMapping("/blogs/{typeofblog}")
	public ResponseEntity<?> getAllUsers(@PathVariable String typeofblog,@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<BlogPost> page = blogService.getAllUsersWithType(pageNo, pageSize, typeofblog);
		List<BlogPost> bloglist = page.getContent();
		List<BlogPost> updatedList = new ArrayList<BlogPost>();
		if (bloglist.size() != 0) {
			bloglist.forEach(e -> {
				int count = commentRepo.getTotalBlogCommentCount(e.getId());
				e.setTotalComment(count + "");
				updatedList.add(e);
			});
		}
		if (page != null) {
			return new ResponseEntity<BlogPaginationDTO>(
					new BlogPaginationDTO(page.getTotalElements(), page.getTotalPages(), updatedList), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No blogposts are available in DB to display", HttpStatus.NO_CONTENT);
		}
	}

	
	// API For updating blod
	@PutMapping("updateblog/{id}")
	public BlogPost updatePost(@PathVariable("id") long id, @RequestBody BlogPost entity)
			throws RecordNotFoundException {
		BlogPost blog = blogRepo.findById(id);
		if (blog == null) {
			throw new RecordNotFoundException("Record not found for this id :: " + id);
		} else {
			blog.setTitle(entity.getTitle());
			blog.setContent(entity.getContent());
			blog.setUsername(entity.getUsername());
			blog.setTypeofblog(entity.getTypeofblog());
			DateFormat df = new SimpleDateFormat("dd/MM/yy");
			Date date = new Date();
			blog.setCreatedOn(df.format(date));

			return blogService.UpdatePost(blog);
		}
	}

	// API For getting blog according to id
	@GetMapping("/getpost/{id}")
	public ResponseEntity<BlogPost> getPost(@PathVariable Long id) {

		blogService.getPostById(id);

		return new ResponseEntity<>(blogService.getPostById(id), HttpStatus.OK);
	}

	// API For deleting blog according to id
	@SuppressWarnings("unused")
	@DeleteMapping("/deleteblog/{id}")
	public ResponseEntity<String> DeleteBlog(@PathVariable Long id) {

		BlogPost post = new BlogPost();
		if (post != null) {
			blogService.deletePost(id);

			return new ResponseEntity<String>("Blog deleted Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("no blogs present", HttpStatus.OK);

		}
	}
	
	// API For get Allavailable blogs
		@GetMapping("/blogs")
		public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
				@RequestParam(defaultValue = "10") Integer pageSize) {
			Page<BlogPost> page = blogService.getAllUsers(pageNo, pageSize);
			List<BlogPost> bloglist = page.getContent();
			List<BlogPost> updatedList = new ArrayList<BlogPost>();
			if (bloglist.size() != 0) {
				bloglist.forEach(e -> {
					int count = commentRepo.getTotalBlogCommentCount(e.getId());
					e.setTotalComment(count + "");
					updatedList.add(e);
				});
			}
			if (page != null) {
				return new ResponseEntity<BlogPaginationDTO>(
						new BlogPaginationDTO(page.getTotalElements(), page.getTotalPages(), updatedList), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No blogposts are available in DB to display", HttpStatus.NO_CONTENT);
			}
		}


	
}