package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.model.ReferralAdmin;

public interface ReferralAdminRepo extends JpaRepository<ReferralAdmin, Integer> {

	@Query(value="select * from referraladmin where username=:username AND password=:password",nativeQuery = true)
	public ReferralAdmin getDetailsAdmin(String username,String password);

}
