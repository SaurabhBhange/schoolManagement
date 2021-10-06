//package com.school.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.school.dto.CommentDto;
//import com.school.model.StudentAttendance;
//
//public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Integer> {
//
//	@Query(value = "select sam.detail from studentattendancedetails as sad \r\n"
//			+ "join studentattendancemapping sam  \r\n" + "join studenttbl as st \r\n" + "on\r\n"
//			+ "sad.student_id=st.userId and  sad.sad_id=sam.id\r\n" + "where st.batch_id=:id", nativeQuery = true)
//	public CommentDto findDetailByid(int id);
//	
////	@Query(value="select * from studentattendancemapping where sad_id=:sad_id",nativeQuery = true)
////	public StudentAttendance getDataById(@Param(value="sad_id")int id);
//	
//	
//
//	/*
//	 * @Modifying
//	 * 
//	 * @Transactional // Make sure to import
//	 * org.springframework.transaction.annotation.Transactional public void
//	 * deleteByCreatedOnBefore(Date expiryDate);
//	 */
//}