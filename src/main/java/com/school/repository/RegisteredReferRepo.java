package com.school.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.RegisteredRefer;

public interface RegisteredReferRepo extends JpaRepository<RegisteredRefer, Integer> {

	Page<RegisteredRefer> findAllByOrderByRegisteredreferidDesc(Pageable paging);

	@Query(value = "select * from registeredrefer where coupen=:coupen AND admissionstatus=true", nativeQuery = true)
	public List<RegisteredRefer> getData(String coupen);

	@Query(value = "select * from registeredrefer where extra_coupen=:extra_coupen AND admissionstatus=true ", nativeQuery = true)
	public List<RegisteredRefer> getExtraData(@Param(value = "extra_coupen") String extraCoupen);

	@Query(value = "select * from registeredrefer where coupen=:coupen AND payment_status=false AND admissionstatus=true", nativeQuery = true)
	List<RegisteredRefer> getPendingAmountData(String coupen);

	@Query(value = "select * from registeredrefer where extra_coupen=:extra_coupen AND payment_status=false AND admissionstatus=true", nativeQuery = true)
	List<RegisteredRefer> getCouncellorPendingAmountData(@Param(value = "extra_coupen") String extraCoupen);

	public List<RegisteredRefer> findByCoupen(String coupen);

	public List<RegisteredRefer> findByExtraCoupen(String extraCoupen);

}
