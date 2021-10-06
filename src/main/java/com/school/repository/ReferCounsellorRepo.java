package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.model.ReferralCounsellor;

public interface ReferCounsellorRepo extends JpaRepository<ReferralCounsellor, Integer> {

	@Query(value = "select * from referralcounsellor where username=:username AND password=:password", nativeQuery = true)
	ReferralCounsellor getCounsellorDetails(String username, String password);

}
