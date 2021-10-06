package com.school.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.ServiceForm;

public interface ServiceFormRepo extends JpaRepository<ServiceForm, Integer> {
	
	public Page<ServiceForm> findAllByOrderByServiceIdDesc(Pageable paging);


}
