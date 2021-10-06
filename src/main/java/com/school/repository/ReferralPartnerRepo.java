package com.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.ReferralPartner;

public interface ReferralPartnerRepo extends JpaRepository<ReferralPartner, Integer> {

	public ReferralPartner findByUsername(String username);

	public Page<ReferralPartner> findAllByOrderByReferralidDesc(Pageable paging);

//	@Query(value="select student_offer_price from referralpartner where referralcode=:referralcode",nativeQuery = true)
	
	public ReferralPartner findByReferralcode(String referralcode);

//	@Query(value = "update referralpartner r SET r.bank_name=:bankName,r.account_no=:AccountNo,r.ifsc=:ifsc,r.accholdername=:accholdername where r.referralid=:referralid ", nativeQuery = true)
//	public int updateBankDetails(@Param(value = "bank_name") String bankName,
//			@Param(value = "AccountNo") String accountNo, @Param(value = "ifsc") String ifsc,
//			@Param(value = "accholdername") String accholdername, @Param(value = "referralid") int referralid);

}
