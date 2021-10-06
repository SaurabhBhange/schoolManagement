package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.uservideomapping;

public interface UserVideoRepo extends JpaRepository<uservideomapping, Integer> {

	// @Query(value = "select * from video_upload where
	// video_course_id=:user_id",nativeQuery = true)
//	@Query(value = "select st.video_id,st.title,st.description,st.thumbnail,st.thumbnail_name,st.video,st.video_filename,st.video_course_id from video_upload st join uservideo usrv on st.video_course_id=usrv.video_course_id where usrv.user_id=:user_id", nativeQuery = true)
//	public List<VideoUpload> getSubscribedCourses(@Param(value = "user_id") int user_id);
}
