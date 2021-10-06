//package com.school.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.school.model.StudentAttendanceDetails;
//
//public interface AttendanceDetailsRepo extends JpaRepository<StudentAttendanceDetails, Integer> {
//	
////	@Query(value="select sad from studentattendancedetails as sad join coursebatchmapping as cbm where sad.user_id=:user_id and cbm.course_id",nativeQuery = true)
//	
//@Query(value="select st.sadetails_id,st.status,st.user_id,st.batch_id,st.course_id,sam.date,sam.detail from studentattendancedetails as st inner join studentattendancemapping as sam on st.sad_id=sam.id where user_id=:userId and course_id=:course_id",nativeQuery = true)
//public List<StudentAttendanceDetails> getListOfAttendance(int userId,int course_id); 
//	
//}
