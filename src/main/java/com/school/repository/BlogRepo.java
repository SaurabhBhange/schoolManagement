package com.school.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.BlogPost;

public interface BlogRepo extends JpaRepository<BlogPost, Integer> {

	public BlogPost findById(Long id);

	BlogPost deleteById(Long id);

	@Query(value = "SELECT * FROM blogpost ORDER BY id DESC", nativeQuery = true)
	public Page<BlogPost> orderby(Pageable paging);

	@Query(value = "select bpc.comment_id,bpc.name,bpc.email_id,bpc.date_of_comment,bpc.message from blogpostcomments bpc join blogpost bp where bpc.id=:id", nativeQuery = true)
	public List<BlogPost> findByBlogId(@Param(value = "id") Long id);

	public Page<BlogPost> findBytypeofblog(String typeofblog, Pageable paging);

	public Page<BlogPost> findAll(Pageable paging);

}
