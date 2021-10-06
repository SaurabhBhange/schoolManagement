package com.school.dto;

import java.util.List;

import com.school.model.Certificate;
import com.school.model.ServiceForm;
import com.school.model.bucketCertificate;

public class ServicePaginationDTO {

	private long totalItems;

	private int totalPages;

	private List<ServiceForm> serviceList;

	private List<bucketCertificate> bucketList;
	
	private List<Certificate> certificateList;

	public ServicePaginationDTO(long totalItems, int totalPages, List<ServiceForm> serviceList) {
		super();
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.serviceList = serviceList;
	}

	public ServicePaginationDTO(long totalItems, List<bucketCertificate> bucketList, int totalPages) {
		super();
		this.totalItems = totalItems;
		this.bucketList = bucketList;
		this.totalPages = totalPages;

	}
	

	public ServicePaginationDTO(List<Certificate> certificateList,long totalItems, int totalPages) {
		super();
		this.certificateList = certificateList;
		this.totalItems = totalItems;
		this.totalPages = totalPages;

	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<ServiceForm> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServiceForm> serviceList) {
		this.serviceList = serviceList;
	}

	public List<bucketCertificate> getBucketList() {
		return bucketList;
	}

	public void setBucketList(List<bucketCertificate> bucketList) {
		this.bucketList = bucketList;
	}

	public List<Certificate> getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(List<Certificate> certificateList) {
		this.certificateList = certificateList;
	}
	
	

}
