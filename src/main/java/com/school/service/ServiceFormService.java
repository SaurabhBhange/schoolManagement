package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.model.ServiceForm;
import com.school.repository.ServiceFormRepo;

@Service
public class ServiceFormService {

	@Autowired
	private ServiceFormRepo serviceformRepo;

	public ServiceForm createService(ServiceForm services) {

		return serviceformRepo.save(services);

	}
	
	// Service for getting all users
	public Page<ServiceForm> getAllUsers(int pageNo, int pageSize) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			return serviceformRepo.findAllByOrderByServiceIdDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	public void deleteServiceForm(int serviceId) {

		serviceformRepo.deleteById(serviceId);

	}

}
