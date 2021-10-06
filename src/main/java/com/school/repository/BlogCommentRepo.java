package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.school.model.BlogCommentPost;

public interface BlogCommentRepo extends JpaRepository<BlogCommentPost, Integer> {

	@Query(value = "select DISTINCT bpc.comment_id,bpc.name,bpc.email_id,bpc.date_of_comment,bpc.message,bpc.id from blogpostcomments bpc join blogpost bp  where bpc.id=:id Order By bpc.comment_id DESC", nativeQuery = true)
	public List<BlogCommentPost> findByData(@Param(value = "id") Long id);
	
	@Modifying
	@Transactional
	public void deleteByCommentId(int commentId);

	@Query(value = "select count(*) id from blogpostcomments where id=:id", nativeQuery = true)
	public int getTotalBlogCommentCount(Long id);

	
}