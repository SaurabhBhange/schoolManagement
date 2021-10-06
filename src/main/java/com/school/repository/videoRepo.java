package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.VideoUpload;

public interface videoRepo extends JpaRepository<VideoUpload, Integer>{

//	@Query(value = "select st.userId,st.address,st.adhar_number,st.blood_group,st.city,st.date_of_birth,st.mothers_name,st.date_of_joining,st.date_of_leaving,st.email,st.first_name,st.gender,st.higher_qualification,st.image,st.last_name,st.middle_name,st.mobile_number,st.parent_number,st.password,st.pincode,st.state,st.username from studenttbl as st join coursebatchmapping as cbm on st.userId=cbm.userId where cbm.course_id=:course_id and cbm.batch_id=:batch_id ", nativeQuery = true)
//	public VideoUpload findByvideoCourseId(int videoCourseId);
	
	public VideoUpload findById(int videoId);
	
	@Query(value = "select st.video_id,st.title,st.description,st.thumbnail,st.thumbnail_name,st.video,st.video_filename,st.video_course_id from video_upload st join uservideo usrv on st.video_course_id=usrv.video_course_id where usrv.user_id=:user_id", nativeQuery = true)
	public List<VideoUpload> getSubscribedCourses(@Param(value = "user_id") int user_id);
	
}
