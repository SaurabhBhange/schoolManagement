package com.school.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.school.model.StudentPojo;

public interface StudentRepo extends PagingAndSortingRepository<StudentPojo, Integer> {

	public StudentPojo findByAdharNumber(String adharNumber);

	public StudentPojo findByEmail(String email);

	public StudentPojo findByUsername(String username);

	public Set<StudentPojo> findByDateOfBirthLike(String dateOfBirth);

	@Query(value = "select st.user_id,st.address,st.adhar_number,st.blood_group,st.city,st.date_of_birth,st.mothers_name,st.date_of_joining,st.date_of_leaving,st.email,st.first_name,st.gender,st.higher_qualification,st.image,st.last_name,st.middle_name,st.mobile_number,st.parent_number,st.password,st.pincode,st.state,st.username,st.file_name from studenttbl as st join coursebatchmapping as cbm on st.user_id=cbm.user_id where cbm.batch_id=:batch_id", nativeQuery = true)
	public List<StudentPojo> findByBatchId(int batch_id);

//value="select st.userId,st.address,st.adhar_number,st.blood_group,st.city,st.date_of_birth,st.mothers_name,st.date_of_joining,st.date_of_leaving,st.email,st.first_name,st.gender,st.higher_qualification,st.image,st.last_name,st.middle_name,st.mobile_number,st.parent_number,st.password,st.pincode,st.state,st.username,st.batch_id,st.course_id from studenttbl as st where st.batch_id=:batch_id", nativeQuery=true)
	@Query(value = "select st.user_id,st.address,st.adhar_number,st.blood_group,st.city,st.date_of_birth,st.mothers_name,st.date_of_joining,st.date_of_leaving,st.email,st.first_name,st.gender,st.higher_qualification,st.image,st.last_name,st.file_name,st.middle_name,st.mobile_number,st.parent_number,st.password,st.pincode,st.state,st.username from studenttbl as st join coursebatchmapping as cbm on st.user_id=cbm.user_id where cbm.course_id=:course_id and cbm.batch_id=:batch_id", nativeQuery = true)
	public List<StudentPojo> findByCourseAndBatch(int course_id, int batch_id);

	@Query(value = "select st.user_id,st.address,st.adhar_number,st.blood_group,st.city,st.date_of_birth,st.mothers_name,st.date_of_joining,st.date_of_leaving,st.email,st.first_name,st.gender,st.higher_qualification,st.image,st.last_name,st.middle_name,st.mobile_number,st.parent_number,st.password,st.pincode,st.state,st.username,st.file_name from studenttbl as st join coursebatchmapping as cbm on st.user_id=cbm.user_id where st.user_id=:user_id", nativeQuery = true)
	public List<StudentPojo> findByUserId(int user_id);

	@Query(value = "select distinct st.user_id,st.address,st.adhar_number,st.blood_group,st.city,st.date_of_birth,st.mothers_name,st.date_of_joining,st.date_of_leaving,st.email,st.first_name,st.gender,st.higher_qualification,st.image,st.last_name,st.middle_name,st.mobile_number,st.parent_number,st.password,st.pincode,st.state,st.username,st.file_name from studenttbl as st join coursebatchmapping as cbm on st.user_id=cbm.user_id where cbm.course_id=:course_id and cbm.user_id=:user_id", nativeQuery = true)
	public List<StudentPojo> findByUserIdAndCourseId(int user_id, int course_id);

	public Page<StudentPojo> findAllByOrderByUserIdDesc(Pageable paging);

	@Query(value = "select st.user_id,st.address,st.adhar_number,st.blood_group,st.city,st.date_of_birth,st.mothers_name,st.date_of_joining,st.date_of_leaving,st.email,st.first_name,st.gender,st.higher_qualification,st.image,st.last_name,st.middle_name,st.mobile_number,st.parent_number,st.password,st.pincode,st.state,st.username,st.file_name from studenttbl as st join coursebatchmapping as cbm on st.user_id=cbm.user_id where cbm.course_id=:course_id", nativeQuery = true)
	public List<StudentPojo> findByCourse(int course_id);

// @Query(value="select st.userId,st.address,st.adhar_number,st.blood_group,st.city,st.date_of_birth,st.mothers_name,st.date_of_joining,st.date_of_leaving,st.email,st.first_name,st.gender,st.higher_qualification,st.image,st.last_name,st.middle_name,st.mobile_number,st.parent_number,st.password,st.pincode,st.state,st.username from studenttbl as st join coursebatchmapping as cbm on st.userId=cbm.userId where cbm.course_id=:course_id", nativeQuery=true)
//public List<StudentPojo> findByCourse(int course_id);

}
