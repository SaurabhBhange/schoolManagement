package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.BlogCommentDTO;
import com.school.model.BlogCommentPost;
import com.school.service.BlogCommentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/blog")
public class BlogCommentController {

	@Autowired
	private BlogCommentService blogCommentService;

	@PostMapping("/save/comment")
	public ResponseEntity<?> saveComment(@RequestParam String name, @RequestParam String emailId,
			@RequestParam String message, @RequestParam Long id) {
		if (id != null) {
			BlogCommentPost bcp = blogCommentService.saveData(name, emailId, message, id);
			return new ResponseEntity<BlogCommentPost>(bcp, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Comment cannot be saved", HttpStatus.OK);
		}
	}

	@GetMapping("/fetch/comments")
	public ResponseEntity<?> fetchData() {
		List<BlogCommentPost> posts = blogCommentService.getData();
		if (posts != null) {
			return new ResponseEntity<List<BlogCommentPost>>(posts, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No blog commentqs cavailable in DB to display", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/blogcomments/{id}")
	public ResponseEntity<?> fetchBlogData(@PathVariable(value = "id") Long id) {

		List<BlogCommentPost> bloglist = blogCommentService.getDataById(id);
		if (bloglist.size() != 0) {
			BlogCommentDTO blogCommentdto = new BlogCommentDTO();
			blogCommentdto.setSize(bloglist.size());
			blogCommentdto.setBlogCommentPost(bloglist);
			return new ResponseEntity<BlogCommentDTO>(blogCommentdto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No data Available", HttpStatus.NOT_FOUND);
		}
	}

	// API For deleting contactid
	@DeleteMapping("/delete/comments/{commentId}")
	public ResponseEntity<?> deleteComments(@PathVariable int commentId) {
		blogCommentService.deleteData(commentId);
		return new ResponseEntity<String>("Comment successfully deleted", HttpStatus.OK);
	}
	
	
	
	

}
