package com.school.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.UserPojo;

public interface UserRepo extends JpaRepository<UserPojo, Integer> {
	public UserPojo findByUsername(String username);

	public UserPojo findByAdharNumber(String adharNumber);

	public UserPojo findByEmail(String email);

	// @Query(value="select * from user s where s.date_of_birth like :dateOfBirth",
	// nativeQuery=true)
	// public Set<UserPojo> findByDateOfBirth(String dateOfBirth);

	public Set<UserPojo> findByDateOfBirthLike(String dateOfBirth);

	public Page<UserPojo> findAllByOrderByUserIdDesc(Pageable paging);

}
